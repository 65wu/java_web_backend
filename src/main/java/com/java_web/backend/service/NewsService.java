package com.java_web.backend.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.java_web.backend.dao.News.NewsManager;
import com.java_web.backend.dao.News.NewsRepository;
import com.java_web.backend.model.dto.News.NewsBasic;
import com.java_web.backend.model.dto.News.NewsDetail;
import com.java_web.backend.model.po.News;
import com.java_web.backend.util.MyResponse;
import com.java_web.backend.util.TokenHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class NewsService {
    @Autowired
    private NewsRepository newsRepository;
    @Autowired
    RedisTemplate<String, String> redisTemplate;
    @Autowired
    private NewsManager newsManager;
    @Autowired
    private TokenHelper tokenHelper;
    public MyResponse GetAll(int pageNo, int pageSize) {
        PageHelper.startPage(pageNo,pageSize);
        ArrayList<NewsBasic> newsList = newsManager.getNewsAll();
        PageInfo<NewsBasic> pageInfo = new PageInfo<>(newsList);
        return new MyResponse(
                1,
                "获取成功",
                pageInfo
        );
    }
    public MyResponse GetDetail(String token, Integer newsId) {
        Integer loginUserId = tokenHelper.getUserId(token);
        // 默认查询的新闻不是该登录用户
        int own = 0;
        // 通过news id找到对应的作者id
        Optional<News> optionalNews = newsRepository.findById(newsId);
        if (optionalNews.isPresent()) {
            News news = optionalNews.get();
            Integer ownerUserId = news.getUser().getUserId();
            if (loginUserId.equals(ownerUserId))
                own = 1;
            NewsDetail newsDetail = newsManager.getNewsDetail(newsId);
            Map<String, Object> result = new HashMap<>();
            result.put("news_detail", newsDetail);
            result.put("own", own);
            return new MyResponse(
                    1,
                    "获取成功",
                    result
            );
        }
        return new MyResponse(
                0,
                "获取失败"
        );
    }
    public MyResponse edit(String token, Integer newsId, String title, String content, Integer typeId) {
        Integer loginUserId = tokenHelper.getUserId(token);
        // 通过news id找到对应的作者id
        Optional<News> optionalNews = newsRepository.findById(newsId);
        if (optionalNews.isPresent()) {
            News news = optionalNews.get();
            Integer ownerUserId = news.getUser().getUserId();
            if(loginUserId.equals(ownerUserId)) {
                try {
                    newsManager.updateNews(title, content, typeId, newsId);
                    return new MyResponse(
                            1,
                            "新闻修改成功"
                    );
                } catch (Exception e) {
                    e.printStackTrace();
                    return new MyResponse(
                            0,
                            "新闻修改失败"
                    );
                }
            }
            return new MyResponse(
                    0,
                    "您没有修改该新闻的权限"
            );
        }
        return new MyResponse(
                0,
                "新闻不存在"
        );
    }
    public MyResponse delete(String token, Integer newsId) {
        Integer loginUserId = tokenHelper.getUserId(token);
        // 通过news id找到对应的作者id
        Optional<News> optionalNews = newsRepository.findById(newsId);
        if (optionalNews.isPresent()) {
            News news = optionalNews.get();
            Integer ownerUserId = news.getUser().getUserId();
            if(loginUserId.equals(ownerUserId)) {
                try {
                    newsManager.deleteNews(newsId);
                    return new MyResponse(
                            1,
                            "新闻删除成功"
                    );
                } catch (Exception e) {
                    e.printStackTrace();
                    return new MyResponse(
                            0,
                            "新闻删除失败"
                    );
                }
            }
            return new MyResponse(
                    0,
                    "您没有删除该新闻的权限"
            );
        }
        return new MyResponse(
                0,
                "新闻不存在"
        );
    }
    public MyResponse publish(String token, String content, String title, Integer typeId) {
        Integer userId = tokenHelper.getUserId(token);
        Date date = new Date();
        try {
            newsManager.publishNews(
                    content,
                    date,
                    title,
                    typeId,
                    userId
            );
            return new MyResponse(
                    1,
                    "发布成功"
            );
        } catch (Exception e) {
            e.printStackTrace();
            return new MyResponse(
                    0,
                    "发布失败"
            );
        }
    }
}
