package com.huiqianlai.fitfoodapp.bean;

import com.fasterxml.jackson.annotation.JsonProperty;


public class ImageResultBean {

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
        @JsonProperty("image_id")
        private String image_id;
        @JsonProperty("total_calories")
        private Double total_calories;
        @JsonProperty("updated_at")
        private String updated_at;
        @JsonProperty("created_at")
        private String created_at;
        @JsonProperty("id")
        private Integer id;

        public String getImage_id() {
            return image_id;
        }

        public void setImage_id(String image_id) {
            this.image_id = image_id;
        }

        public Double getTotal_calories() {
            return total_calories;
        }

        public void setTotal_calories(Double total_calories) {
            this.total_calories = total_calories;
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

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }
    }
}
