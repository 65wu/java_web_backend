package com.java_web.backend.model.dto.News;

import javax.validation.constraints.NotNull;

public class PublishNews {
    @NotNull
    private String content;
    @NotNull
    private String title;
    @NotNull
    private Integer typeId;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }
}
