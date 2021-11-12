package com.huiqianlai.fitfoodapp.bean;

public class RegisterBean {


    @com.fasterxml.jackson.annotation.JsonProperty("message")
    private String message;
    @com.fasterxml.jackson.annotation.JsonProperty("data")
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
        @com.fasterxml.jackson.annotation.JsonProperty("name")
        private String name;
        @com.fasterxml.jackson.annotation.JsonProperty("email")
        private String email;
        @com.fasterxml.jackson.annotation.JsonProperty("updated_at")
        private String updatedAt;
        @com.fasterxml.jackson.annotation.JsonProperty("created_at")
        private String createdAt;
        @com.fasterxml.jackson.annotation.JsonProperty("id")
        private Integer id;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
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
