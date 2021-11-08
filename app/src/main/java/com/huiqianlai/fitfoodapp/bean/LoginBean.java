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
    }
}
