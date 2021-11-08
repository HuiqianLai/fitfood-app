package com.huiqianlai.fitfoodapp.bean;

import com.fasterxml.jackson.annotation.JsonProperty;


public class LoginBean {

    @JsonProperty("message")
    private String message;
    @JsonProperty("data")
    private DataDTO data;


    public static class DataDTO {
        @JsonProperty("accessToken")
        private String accessToken;

        public String getAccessToken() {
            return accessToken;
        }

        public void setAccessToken(String accessToken) {
            this.accessToken = accessToken;
        }
    }

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
}
