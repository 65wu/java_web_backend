package com.java_web.backend.controller;

import com.java_web.backend.model.dto.News.DeleteNews;
import com.java_web.backend.model.dto.News.EditNews;
import com.java_web.backend.model.dto.News.PublishNews;
import com.java_web.backend.service.NewsService;
import com.java_web.backend.util.AuthToken;
import com.java_web.backend.util.MyResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/news")
@CrossOrigin
public class NewsController {
    @Autowired
    private NewsService newsService;
    @GetMapping("/all")
    public MyResponse getAll(
            @RequestParam(defaultValue = "1") int pageNo,
            @RequestParam(defaultValue = "10") int pageSize
    ) {
        return newsService.getAll(pageNo, pageSize);
    }
    @AuthToken
    @GetMapping("/detail")
    public MyResponse getDetail(
            @RequestHeader("Token") String token,
            @RequestParam Integer newsId
    ) {
        return newsService.getDetail(token, newsId);
    }
    @AuthToken
    @PutMapping("/edit")
    public MyResponse Edit(
            @RequestHeader("Token") String token,
            @RequestBody @Valid EditNews editNews
    ) {
        return newsService.edit(
            token,
            editNews.getNewsId(),
            editNews.getTitle(),
            editNews.getContent(),
            editNews.getTypeId()
        );
    }
    @AuthToken
    @DeleteMapping("/delete")
    public MyResponse Edit(
            @RequestHeader("Token") String token,
            @RequestBody @Valid DeleteNews deleteNews
    ) {
        return newsService.delete(
                token,
                deleteNews.getNewsId()
        );
    }
    @AuthToken
    @PostMapping("/publish")
    public MyResponse Publish(
            @RequestHeader("Token") String token,
            @RequestBody @Valid PublishNews publishNews
    ) {
        return newsService.publish(
                token,
                publishNews.getContent(),
                publishNews.getTitle(),
                publishNews.getTypeId()
        );
    }
}
