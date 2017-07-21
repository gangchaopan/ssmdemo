package com.aisino.controller;

import com.aisino.service.ArticleService;
import com.aisino.tools.JacksonTools;
import com.aisino.tools.JsonWebTokenBuild;
import com.aisino.tools.NotEmptyString;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * Created by gangchaopan on 2017/7/17.
 */
@Api(value = "article",description = "文章管理",produces = MediaType.APPLICATION_JSON_VALUE)
@Controller
@RequestMapping(value = "/article")
public class ArticleController {


    private Logger logger = LoggerFactory.getLogger(ArticleController.class);

    @Autowired
    private ArticleService articleService;

    /**
     * 发表文章
     * @param request
     * @param title
     * @param author
     * @param content
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/save.json",method = RequestMethod.POST,produces="application/json;charset=UTF-8")
    @ApiOperation(value = "发表文章",httpMethod = "post",notes = "发表成功返回成功或失败")
    public  String publish(HttpServletRequest request, @RequestParam(value = "title")  String title,
                           @RequestParam(value = "author") String author, @RequestParam(value = "content") String content,
                           @RequestParam(value = "picture") String picture, @RequestParam(value = "summary") String summary,
                           @RequestParam(value = "styleId") String styleId, @RequestParam(value = "tag") String tag){
        Map<String, Object> map = new HashMap<String, Object>();

        if(!NotEmptyString.isNotBlank(title)|| !NotEmptyString.isNotBlank(author)|| !NotEmptyString.isNotBlank(content)){
            map.put("status", "201");
            map.put("message", "数据不完整");
            return JacksonTools.getJson(map);
        }
        //获取当前用户的id
        String  token= request.getParameter("token");
        String uid = JsonWebTokenBuild.getUId(token);
        if(uid==null){
            map.put("status", "203");
            map.put("message", "token异常，重新登录");
            return JacksonTools.getJson(map);
        }
        logger.info("获取当前用的id{}",uid);
        Map <String,Object> articleMap = new HashMap<String, Object>();
        articleMap.put("title",title);
        articleMap.put("author",author);
        articleMap.put("content",content);
        articleMap.put("userid",Long.valueOf(uid));
        articleMap.put("picture",picture);

        boolean save_is_ok = articleService.save(articleMap);
        if (save_is_ok){
            map.put("status", "200");
            map.put("message", "文章发布正常，等待审核");
        }else{
            map.put("status","202");
            map.put("message", "文章发布异常，请重新发布");
        }

        return JacksonTools.getJson(map);
    }


    /**
     * 获取文章列表页
     * @param offset 上一条数据库记录值
     * @param rows 要获取的文章条数
     * @return 返回文章列表页
     */
    @ResponseBody
    @RequestMapping(value = "/list.json",method = RequestMethod.POST,produces="application/json;charset=UTF-8")
    @ApiResponses(value = {
            @ApiResponse(code = 200,message = "获取文章成功"),
            @ApiResponse(code = 201,message = "没有数据")
    })
    public String listArticle(@RequestParam(value = "offset") int offset,
                              @RequestParam(value = "rows") int rows){
       return  articleService.listArticle(offset,rows);
    }


    /**
     * 根据文章id来获去详细文章信息
     * @param articleId 文章id
     * @return 文章详细信息
     */
    @ResponseBody
    @RequestMapping(value = "/detail",method = RequestMethod.POST,produces = "application/json;charset=UTF-8")
    @ApiResponses(value = {
            @ApiResponse(code = 200,message = "获取文章成功过"),
            @ApiResponse(code=201,message = "获取文章失败")
    })
    public String getArticleById(@RequestParam(value = "articleId") long articleId){
       return articleService.getArticleByArticleId(articleId);
    }


}
