package com.example.shinelon.mymdapp.modle.bean;

import android.graphics.Movie;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Shinelon on 2017/3/5.
 */

public class MovieBean {
    @SerializedName("error")
    private String error;
    private List<MovieItem> results;

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public List<MovieItem> getResults() {
        return results;
    }

    public void setResults(List<MovieItem> results) {
        this.results = results;
    }

    public class MovieItem {
        @SerializedName("_id")
        private String id;
        @SerializedName("createdAt")
        private String createdTime;
        @SerializedName("desc")
        private String desc;
        @SerializedName("publishedAt")
        private String publishedTime;
        @SerializedName("source")
        private String source;
        @SerializedName("type")
        private String type;
        @SerializedName("url")
        private String url;
        @SerializedName("used")
        private Boolean used;
        @SerializedName("who")
        private String who;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getCreatedTime() {
            return createdTime;
        }

        public void setCreatedTime(String createdTime) {
            this.createdTime = createdTime;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public String getSource() {
            return source;
        }

        public void setSource(String source) {
            this.source = source;
        }

        public String getPublishedTime() {
            return publishedTime;
        }

        public void setPublishedTime(String publishedTime) {
            this.publishedTime = publishedTime;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public Boolean getUsed() {
            return used;
        }

        public void setUsed(Boolean used) {
            this.used = used;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getWho() {
            return who;
        }

        public void setWho(String who) {
            this.who = who;
        }
    }
}
