package com.java_web.backend.controller;

import com.java_web.backend.model.dto.News.DeleteNews;
import com.java_web.backend.model.dto.News.EditNews;
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
    public MyResponse GetAll(
            @RequestParam(defaultValue = "1") int pageNo,
            @RequestParam(defaultValue = "10") int pageSize
    ) {
        return newsService.GetAll(pageNo, pageSize);
    }
    @GetMapping("detail")
    public MyResponse GetDetail(
            @RequestParam Integer news_id
    ) {
        return newsService.GetDetail(news_id);
    }
    @AuthToken
    @PutMapping("/edit")
    public MyResponse Edit(
            @RequestHeader("Token") String token,
            @RequestBody @Valid EditNews editNews
    ) {
        return newsService.Edit(
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
        return newsService.Delete(
                token,
                deleteNews.getNewsId()
        );
    }
}
