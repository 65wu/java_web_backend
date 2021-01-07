package com.java_web.backend.model.dto.News;

import javax.validation.constraints.NotNull;
import java.lang.annotation.Native;

public class EditNews {
    @NotNull
    private Integer newsId;
    @NotNull
    private String title;
    @NotNull
    private String content;
    @NotNull
    private Integer typeId;

    public Integer getNewsId() {
        return newsId;
    }

    public void setNewsId(Integer newsId) {
        this.newsId = newsId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }
}
