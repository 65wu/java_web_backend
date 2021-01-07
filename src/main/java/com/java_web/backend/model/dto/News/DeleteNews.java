package com.java_web.backend.model.dto.News;

import javax.validation.constraints.NotNull;

public class DeleteNews {
    @NotNull
    private Integer newsId;

    public Integer getNewsId() {
        return newsId;
    }

    public void setNewsId(Integer newsId) {
        this.newsId = newsId;
    }
}
