package com.huiqianlai.fitfoodapp.bean;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;


public class ImageResultBean {

    @JsonProperty("message")
    private String message;
    @JsonProperty("data")
    private List<DataDTO> data;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<DataDTO> getData() {
        return data;
    }

    public void setData(List<DataDTO> data) {
        this.data = data;
    }

    public static class DataDTO {
        @JsonProperty("id")
        private Integer id;
        @JsonProperty("image_id")
        private Integer imageId;
        @JsonProperty("total_calories")
        private Double totalCalories;
        @JsonProperty("created_at")
        private Object createdAt;
        @JsonProperty("updated_at")
        private Object updatedAt;
        @JsonProperty("result_details")
        private List<ResultDetailsDTO> resultDetails;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public Integer getImageId() {
            return imageId;
        }

        public void setImageId(Integer imageId) {
            this.imageId = imageId;
        }

        public Double getTotalCalories() {
            return totalCalories;
        }

        public void setTotalCalories(Double totalCalories) {
            this.totalCalories = totalCalories;
        }

        public Object getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(Object createdAt) {
            this.createdAt = createdAt;
        }

        public Object getUpdatedAt() {
            return updatedAt;
        }

        public void setUpdatedAt(Object updatedAt) {
            this.updatedAt = updatedAt;
        }

        public List<ResultDetailsDTO> getResultDetails() {
            return resultDetails;
        }

        public void setResultDetails(List<ResultDetailsDTO> resultDetails) {
            this.resultDetails = resultDetails;
        }

        public static class ResultDetailsDTO {
            @JsonProperty("id")
            private Integer id;
            @JsonProperty("result_id")
            private Integer resultId;
            @JsonProperty("ingredient")
            private String ingredient;
            @JsonProperty("volume")
            private Integer volume;
            @JsonProperty("calories")
            private Integer calories;
            @JsonProperty("created_at")
            private Object createdAt;
            @JsonProperty("updated_at")
            private Object updatedAt;

            public Integer getId() {
                return id;
            }

            public void setId(Integer id) {
                this.id = id;
            }

            public Integer getResultId() {
                return resultId;
            }

            public void setResultId(Integer resultId) {
                this.resultId = resultId;
            }

            public String getIngredient() {
                return ingredient;
            }

            public void setIngredient(String ingredient) {
                this.ingredient = ingredient;
            }

            public Integer getVolume() {
                return volume;
            }

            public void setVolume(Integer volume) {
                this.volume = volume;
            }

            public Integer getCalories() {
                return calories;
            }

            public void setCalories(Integer calories) {
                this.calories = calories;
            }

            public Object getCreatedAt() {
                return createdAt;
            }

            public void setCreatedAt(Object createdAt) {
                this.createdAt = createdAt;
            }

            public Object getUpdatedAt() {
                return updatedAt;
            }

            public void setUpdatedAt(Object updatedAt) {
                this.updatedAt = updatedAt;
            }
        }
    }
}
