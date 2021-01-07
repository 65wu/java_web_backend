package com.java_web.backend.dao.News;

import com.java_web.backend.model.dto.News.NewsBasic;
import com.java_web.backend.model.po.News;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.ArrayList;

@Mapper
public interface NewsManager {
    @Select("""
            SELECT content,
                   type,
                   publish_time
            FROM news
            INNER JOIN news_type USING(type_id);
            """)
    ArrayList<NewsBasic> getNewsAll();
}
