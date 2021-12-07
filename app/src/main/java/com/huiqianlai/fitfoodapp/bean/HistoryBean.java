package com.huiqianlai.fitfoodapp.bean;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class HistoryBean {

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
        @JsonProperty("current_page")
        private Integer currentPage;
        @JsonProperty("data")
        private List<userInnerData> data;
        @JsonProperty("first_page_url")
        private String first_page_url;
        @JsonProperty("from")
        private Integer from;
        @JsonProperty("last_page")
        private Integer last_page;
        @JsonProperty("last_page_url")
        private String last_page_url;
        @JsonProperty("links")
        private List<linksInnerData> links;
        @JsonProperty("next_page_url")
        private Object next_page_url;
        @JsonProperty("path")
        private String path;
        @JsonProperty("per_page")
        private Integer per_page;
        @JsonProperty("prev_page_url")
        private Object prev_page_url;
        @JsonProperty("to")
        private Integer to;
        @JsonProperty("total")
        private Integer total;

        public Integer getCurrentPage() {
            return currentPage;
        }

        public void setCurrentPage(Integer currentPage) {
            this.currentPage = currentPage;
        }

        public List<userInnerData> getData() {
            return data;
        }

        public void setData(List<userInnerData> data) {
            this.data = data;
        }

        public String getFirst_page_url() {
            return first_page_url;
        }

        public void setFirst_page_url(String first_page_url) {
            this.first_page_url = first_page_url;
        }

        public Integer getFrom() {
            return from;
        }

        public void setFrom(Integer from) {
            this.from = from;
        }

        public Integer getLast_page() {
            return last_page;
        }

        public void setLast_page(Integer last_page) {
            this.last_page = last_page;
        }

        public String getLast_page_url() {
            return last_page_url;
        }

        public void setLast_page_url(String last_page_url) {
            this.last_page_url = last_page_url;
        }

        public List<linksInnerData> getLinks() {
            return links;
        }

        public void setLinks(List<linksInnerData> links) {
            this.links = links;
        }

        public Object getNext_page_url() {
            return next_page_url;
        }

        public void setNext_page_url(Object next_page_url) {
            this.next_page_url = next_page_url;
        }

        public String getPath() {
            return path;
        }

        public void setPath(String path) {
            this.path = path;
        }

        public Integer getPer_page() {
            return per_page;
        }

        public void setPer_page(Integer per_page) {
            this.per_page = per_page;
        }

        public Object getPrev_page_url() {
            return prev_page_url;
        }

        public void setPrev_page_url(Object prev_page_url) {
            this.prev_page_url = prev_page_url;
        }

        public Integer getTo() {
            return to;
        }

        public void setTo(Integer to) {
            this.to = to;
        }

        public Integer getTotal() {
            return total;
        }

        public void setTotal(Integer total) {
            this.total = total;
        }

        public static class userInnerData {
            @JsonProperty("id")
            private Integer id;
            @JsonProperty("user_id")
            private Integer user_id;
            @JsonProperty("path")
            private String path;
            @JsonProperty("created_at")
            private String created_at;
            @JsonProperty("updated_at")
            private String updated_at;
            @JsonProperty("image_id")
            private int image_id;
            @JsonProperty("total_calories")
            private String total_calories;

            public int getImage_id() {
                return image_id;
            }

            public void setImage_id(int image_id) {
                this.image_id = image_id;
            }

            public String getTotal_calories() {
                return total_calories;
            }

            public void setTotal_calories(String total_calories) {
                this.total_calories = total_calories;
            }

            public Integer getId() {
                return id;
            }

            public void setId(Integer id) {
                this.id = id;
            }

            public Integer getUser_id() {
                return user_id;
            }

            public void setUser_id(Integer user_id) {
                this.user_id = user_id;
            }

            public String getPath() {
                return path;
            }

            public void setPath(String path) {
                this.path = path;
            }

            public String getCreated_at() {
                return created_at;
            }

            public void setCreated_at(String created_at) {
                this.created_at = created_at;
            }

            public String getUpdated_at() {
                return updated_at;
            }

            public void setUpdated_at(String updated_at) {
                this.updated_at = updated_at;
            }
        }

        public static class linksInnerData {
            @JsonProperty("url")
            private Object url;
            @JsonProperty("label")
            private String label;
            @JsonProperty("active")
            private Boolean active;

            public Object getUrl() {
                return url;
            }

            public void setUrl(Object url) {
                this.url = url;
            }

            public String getLabel() {
                return label;
            }

            public void setLabel(String label) {
                this.label = label;
            }

            public Boolean getActive() {
                return active;
            }

            public void setActive(Boolean active) {
                this.active = active;
            }
        }
    }
}
