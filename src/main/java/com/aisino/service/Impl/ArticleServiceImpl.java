package com.aisino.service.Impl;

import com.aisino.mapper.ArticleMapper;
import com.aisino.model.ArticleDetailVO;
import com.aisino.model.ArticleListVO;
import com.aisino.service.ArticleService;
import com.aisino.tools.JacksonTools;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 文章服务类
 * Created by gangchaopan on 2017/7/17.
 */
@Service(value = "ArticleService")
public class ArticleServiceImpl implements ArticleService {

    @Resource
    private ArticleMapper articleMapper;

    private Logger logger  = LoggerFactory.getLogger(ArticleServiceImpl.class);
    /**
     * 保存发表文章
     *
     * @param map 文章类信息信息
     */
    public boolean save(Map map) {

        try {
            articleMapper.add(map);
            return true;
        }catch (Exception e){
            logger.info("保存文章出错，异常信息{}",e);
        }
        return false;

    }


    /**
     *
     * @param offset 从第几条数据开始访问
     * @param rows 需要访问的记录数
     * @return 文章列表页
     */
    public String listArticle(int offset,int rows) {

        Map<String,Object> param  = new HashMap<String,Object>();
        param.put("offset",offset);
        param.put("rows",rows);
        List<ArticleListVO> list = articleMapper.list(param);

        Map<String,Object> map = new HashMap<String,Object>();
        if(list.isEmpty()){
            map.put("list",null);
            map.put("status",201);
            map.put("message","没有数据了");
        }else{
            map.put("list",list);
            map.put("status",200);
            map.put("message","获取数据成功");
        }
        return JacksonTools.getJson(map);
    }

    /**
     * @param id 文章id
     * @return 返回文章详细信息
     */
    public String getArticleByArticleId(long id) {

        Map<String,Object> map = new HashMap<String, Object>();

        if(id < 0){
            map.put("status",202);
            map.put("message","文章id参数有问题");
            map.put("article",null);
            return JacksonTools.getJson(map);
        }

        ArticleDetailVO articleDetailVO =  articleMapper.getByArticleId(id);
        if(articleDetailVO==null){
            map.put("status",201);
            map.put("message","搜索的文章不存在");
            map.put("article",null);
        }else{
            map.put("status",200);
            map.put("message","搜索成功");
            map.put("article",articleDetailVO);
        }

        return JacksonTools.getJson(map);
    }
}
