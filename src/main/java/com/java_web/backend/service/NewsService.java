package com.java_web.backend.service;

import com.java_web.backend.dao.NewsRepository;
import com.java_web.backend.model.po.News;
import com.java_web.backend.util.MyResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class NewsService {
    @Autowired
    private NewsRepository newsRepository;
    public MyResponse GetAll() {
        ArrayList<News> newsList = (ArrayList<News>) newsRepository.findAll();
        return new MyResponse(
                1,
                "获取成功",
                newsList
        );
    }
}
