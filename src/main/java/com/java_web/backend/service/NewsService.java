package com.java_web.backend.service;

import com.java_web.backend.dao.News.NewsManager;
import com.java_web.backend.dao.News.NewsRepository;
import com.java_web.backend.model.dto.News.NewsBasic;
import com.java_web.backend.model.po.News;
import com.java_web.backend.util.MyResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Service
public class NewsService {
    @Autowired
    private NewsRepository newsRepository;
    @Autowired
    private NewsManager newsManager;
    public MyResponse GetAll() {
        ArrayList<NewsBasic> newsList = newsManager.getNewsAll();
        Map<String, Object> result = new HashMap<>();
        result.put("news_list", newsList);
        return new MyResponse(
                1,
                "获取成功",
                result
        );
    }
}
