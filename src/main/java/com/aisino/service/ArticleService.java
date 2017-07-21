package com.aisino.service;

import com.aisino.model.ArticleDetailVO;
import com.aisino.model.ArticleListVO;

import java.util.List;
import java.util.Map;

/**
 * Created by gangchaopan on 2017/7/17.
 */
public interface ArticleService {
    /**
     * 保存发表文章
     * @param map
     */
    public boolean save(Map map);


    /**
     * 获取文章列表
     * @param offset,rows
     * @return
     */
    String listArticle(int offset,int rows);

    /**
     *
     * @param id
     * @return
     */
    String  getArticleByArticleId(long id);


}
