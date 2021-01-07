package com.java_web.backend.dao.News;

import com.java_web.backend.model.dto.News.NewsBasic;
import com.java_web.backend.model.dto.News.NewsDetail;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.ArrayList;

@Mapper
public interface NewsManager {
    @Select("""
            SELECT news_id,
                   content,
                   type,
                   publish_time
            FROM news
            INNER JOIN news_type USING(type_id)
            """)
    ArrayList<NewsBasic> getNewsAll();
    @Select("""
            SELECT title,
                   content,
                   publish_time,
                   type,
                   user_id,
                   nickname
            FROM news
            INNER JOIN user USING(user_id)
            INNER JOIN news_type USING(type_id)
            WHERE news_id = #{newsId};
            """)
    NewsDetail getNewsDetail(@Param("newsId") Integer newsId);
}
