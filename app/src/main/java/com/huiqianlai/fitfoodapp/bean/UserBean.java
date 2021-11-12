package com.huiqianlai.fitfoodapp.bean;

public class UserBean {
    @com.fasterxml.jackson.annotation.JsonProperty("name")
    private String name;
    @com.fasterxml.jackson.annotation.JsonProperty("email")
    private String email;
    @com.fasterxml.jackson.annotation.JsonProperty("updated_at")
    private String updated_at;
    @com.fasterxml.jackson.annotation.JsonProperty("created_at")
    private String created_at;
    @com.fasterxml.jackson.annotation.JsonProperty("id")
    private Integer id;
    @com.fasterxml.jackson.annotation.JsonProperty("email_verified_at")
    private String email_verified_at;
    @com.fasterxml.jackson.annotation.JsonProperty("gender")
    private String gender;
    @com.fasterxml.jackson.annotation.JsonProperty("weight")
    private int weight;
    @com.fasterxml.jackson.annotation.JsonProperty("height")
    private int height;

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


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getEmail_verified_at() {
        return email_verified_at;
    }

    public void setEmail_verified_at(String email_verified_at) {
        this.email_verified_at = email_verified_at;
    }
}
