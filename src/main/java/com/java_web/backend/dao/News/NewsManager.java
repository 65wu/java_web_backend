package com.java_web.backend.dao.News;

import com.java_web.backend.model.dto.News.NewsBasic;
import com.java_web.backend.model.dto.News.NewsDetail;
import org.apache.ibatis.annotations.*;

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
    @Update("UPDATE news SET title = #{title}, content = #{content}, type_id = #{typeId} WHERE news_id = #{newsId}")
    void updateNews(
            @Param("title") String title,
            @Param("content") String content,
            @Param("typeId") Integer typeId,
            @Param("newsId") Integer newsId
    );
    @Delete("DELETE FROM news WHERE news_id=#{newsId}")
    void deleteNews(
            @Param("newsId") Integer newsId
    );
}
