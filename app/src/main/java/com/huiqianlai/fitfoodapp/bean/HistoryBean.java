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
        private String firstPageUrl;
        @JsonProperty("from")
        private Integer from;
        @JsonProperty("last_page")
        private Integer lastPage;
        @JsonProperty("last_page_url")
        private String lastPageUrl;
        @JsonProperty("links")
        private List<linksInnerData> links;
        @JsonProperty("next_page_url")
        private Object nextPageUrl;
        @JsonProperty("path")
        private String path;
        @JsonProperty("per_page")
        private Integer perPage;
        @JsonProperty("prev_page_url")
        private Object prevPageUrl;
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

        public String getFirstPageUrl() {
            return firstPageUrl;
        }

        public void setFirstPageUrl(String firstPageUrl) {
            this.firstPageUrl = firstPageUrl;
        }

        public Integer getFrom() {
            return from;
        }

        public void setFrom(Integer from) {
            this.from = from;
        }

        public Integer getLastPage() {
            return lastPage;
        }

        public void setLastPage(Integer lastPage) {
            this.lastPage = lastPage;
        }

        public String getLastPageUrl() {
            return lastPageUrl;
        }

        public void setLastPageUrl(String lastPageUrl) {
            this.lastPageUrl = lastPageUrl;
        }

        public List<linksInnerData> getLinks() {
            return links;
        }

        public void setLinks(List<linksInnerData> links) {
            this.links = links;
        }

        public Object getNextPageUrl() {
            return nextPageUrl;
        }

        public void setNextPageUrl(Object nextPageUrl) {
            this.nextPageUrl = nextPageUrl;
        }

        public String getPath() {
            return path;
        }

        public void setPath(String path) {
            this.path = path;
        }

        public Integer getPerPage() {
            return perPage;
        }

        public void setPerPage(Integer perPage) {
            this.perPage = perPage;
        }

        public Object getPrevPageUrl() {
            return prevPageUrl;
        }

        public void setPrevPageUrl(Object prevPageUrl) {
            this.prevPageUrl = prevPageUrl;
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
            private Integer userId;
            @JsonProperty("path")
            private String path;
            @JsonProperty("created_at")
            private String createdAt;
            @JsonProperty("updated_at")
            private String updatedAt;

            public Integer getId() {
                return id;
            }

            public void setId(Integer id) {
                this.id = id;
            }

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

            public String getCreatedAt() {
                return createdAt;
            }

            public void setCreatedAt(String createdAt) {
                this.createdAt = createdAt;
            }

            public String getUpdatedAt() {
                return updatedAt;
            }

            public void setUpdatedAt(String updatedAt) {
                this.updatedAt = updatedAt;
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
