package com.example.shinelon.mymdapp.modle.bean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Shinelon on 2017/3/4.
 */

public class WelfareBean {
    @SerializedName("erroe")
    private boolean error;
    private List<WelfareItem> results;

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public List<WelfareItem> getResults() {
        return results;
    }

    public void setResults(List<WelfareItem> results) {
        this.results = results;
    }

    public class WelfareItem {
        @SerializedName("_id")
        private String id;
        @SerializedName("createdAt")
        private String createTime;
        @SerializedName("desc")
        private String desc;
        @SerializedName("publishedAt")
        private String publishTime;
        @SerializedName("source")
        private String source;
        @SerializedName("type")
        private String type;
        @SerializedName("url")
        private String imageUrl;
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

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public String getPublishTime() {
            return publishTime;
        }

        public void setPublishTime(String publishTime) {
            this.publishTime = publishTime;
        }

        public String getSource() {
            return source;
        }

        public void setSource(String source) {
            this.source = source;
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

        public String getImageUrl() {
            return imageUrl;
        }

        public void setImageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
        }

        public String getWho() {
            return who;
        }

        public void setWho(String who) {
            this.who = who;
        }
    }
}
