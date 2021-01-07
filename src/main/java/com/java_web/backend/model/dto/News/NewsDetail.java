package com.java_web.backend.model.dto.News;

public class NewsDetail {
    private String title;
    private String content;
    private String publish_time;
    private String type;
    private Integer user_id;
    private String nickname;

    public NewsDetail(
            String title,
            String content,
            String publish_time,
            String type,
            Integer user_id,
            String nickname
    ) {
        this.title = title;
        this.content = content;
        this.publish_time = publish_time;
        this.type = type;
        this.user_id = user_id;
        this.nickname = nickname;
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

    public String getPublish_time() {
        return publish_time;
    }

    public void setPublish_time(String publish_time) {
        this.publish_time = publish_time;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
}
