package com.aisino.mapper;


import com.aisino.model.ArticleDetailVO;
import com.aisino.model.ArticleListVO;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by gangchaopan on 2017/7/17.
 */
@Repository
public interface ArticleMapper {
    /**
     * 添加文章
     * @param map
     */
    void add(Map<String,Object> map);

    /**
     * 获取文章
     * @param  map
     * @return
     */
    List<ArticleListVO> list(Map<String,Object> map);

    /**
     * 根据id获取文章信息
     * @param articleId
     * @return
     */
    ArticleDetailVO getByArticleId(Long articleId);
}
