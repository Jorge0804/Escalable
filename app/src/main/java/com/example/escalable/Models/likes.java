package com.example.escalable.Models;

public class likes {
    Integer id, post_id;
    String api_token;

    public likes(Integer id, Integer post_id, String api_token) {
        this.id = id;
        this.post_id = post_id;
        this.api_token = api_token;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPost_id() {
        return post_id;
    }

    public void setPost_id(Integer post_id) {
        this.post_id = post_id;
    }

    public String getApi_token() {
        return api_token;
    }

    public void setApi_token(String api_token) {
        this.api_token = api_token;
    }
}
