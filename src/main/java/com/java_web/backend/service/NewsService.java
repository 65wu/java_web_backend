package com.java_web.backend.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.java_web.backend.dao.News.NewsManager;
import com.java_web.backend.dao.News.NewsRepository;
import com.java_web.backend.model.dto.News.NewsBasic;
import com.java_web.backend.model.dto.News.NewsDetail;
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
    public MyResponse GetAll(int pageNo, int pageSize) {
        PageHelper.startPage(pageNo,pageSize);
        ArrayList<NewsBasic> newsList = newsManager.getNewsAll();
        PageInfo<NewsBasic> pageInfo = new PageInfo<>(newsList);
        Map<String, Object> result = new HashMap<>();
        result.put("news_list", pageInfo);
        return new MyResponse(
                1,
                "获取成功",
                result
        );
    }
    public MyResponse GetDetail(Integer newsId) {
        NewsDetail newsDetail = newsManager.getNewsDetail(newsId);
        Map<String, Object> result = new HashMap<>();
        result.put("news_detail", newsDetail);
        return new MyResponse(
                1,
                "获取成功",
                result
        );
    }
}
