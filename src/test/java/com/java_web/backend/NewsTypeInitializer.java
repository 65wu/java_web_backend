package com.java_web.backend;

import com.java_web.backend.dao.NewsTypeRepository;
import com.java_web.backend.model.po.News;
import com.java_web.backend.model.po.NewsType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.parameters.P;

import java.util.ArrayList;


@SpringBootTest
public class NewsTypeInitializer {
    @Autowired
    private NewsTypeRepository newsTypeRepository;
    @Test
    void addRecord() {
        ArrayList<NewsType> newTypeList = new ArrayList<NewsType>() {{
            add(new NewsType(0, "国内"));
            add(new NewsType(1, "国际"));
            add(new NewsType(2, "经济"));
            add(new NewsType(3, "军事"));
        }};
        for(NewsType n : newTypeList) {
            newsTypeRepository.save(n);
        }
    }
}
