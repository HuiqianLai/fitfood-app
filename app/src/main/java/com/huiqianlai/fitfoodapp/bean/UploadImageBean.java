package com.huiqianlai.fitfoodapp.bean;

import com.fasterxml.jackson.annotation.JsonProperty;


public class UploadImageBean {

    @JsonProperty("message")
    private String message;
    @JsonProperty("data")
    private DataDTO data;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public DataDTO getData() {
        return data;
    }

    public void setData(DataDTO data) {
        this.data = data;
    }

    public static class DataDTO {
        @JsonProperty("user_id")
        private Integer userId;
        @JsonProperty("path")
        private String path;
        @JsonProperty("updated_at")
        private String updatedAt;
        @JsonProperty("created_at")
        private String createdAt;
        @JsonProperty("id")
        private Integer id;

        public Integer getUserId() {
            return userId;
        }

        public void setUserId(Integer userId) {
            this.userId = userId;
        }

        public String getPath() {
            return path;
        }

        public void setPath(String path) {
            this.path = path;
        }

        public String getUpdatedAt() {
            return updatedAt;
        }

        public void setUpdatedAt(String updatedAt) {
            this.updatedAt = updatedAt;
        }

        public String getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
        }

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }
    }
}
