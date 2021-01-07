package com.java_web.backend.model.dto.News;

public class NewsBasic {
    private Integer newsId;
    private String content;
    private String type;
    private String publish_time;
    NewsBasic(Integer newsId, String content, String type, String publish_time) {
        this.newsId = newsId;
        this.content = content;
        this.type = type;
        this.publish_time = publish_time;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPublish_time() {
        return publish_time;
    }

    public void setPublish_time(String publish_time) {
        this.publish_time = publish_time;
    }

    public Integer getNewsId() {
        return newsId;
    }

    public void setNewsId(Integer newsId) {
        this.newsId = newsId;
    }
}
