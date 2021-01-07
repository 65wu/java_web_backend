package com.java_web.backend.service;

import com.java_web.backend.dao.News.NewsManager;
import com.java_web.backend.dao.News.NewsRepository;
import com.java_web.backend.model.po.News;
import com.java_web.backend.util.MyResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class NewsService {
    @Autowired
    private NewsRepository newsRepository;
    @Autowired
    private NewsManager newsManager;
    public MyResponse GetAll() {
        ArrayList<News> newsList = newsManager.getNewsAll();
        return new MyResponse(
                1,
                "获取成功",
                newsList
        );
    }
}
