package com.example.shinelon.mymdapp.modle.bean;


import com.google.gson.annotations.SerializedName;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Shinelon on 2017/1/31.
 */

public class NewsListBean {
    @SerializedName("date")
    private String date;
    @SerializedName("stories")
    private List<Stories> stories;
    @SerializedName("top_stories")
    private List<TopStoried> top_stories;

    public NewsListBean() {
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<Stories> getStories() {
        return stories;
    }

    public void setStories(List<Stories> stories) {
        this.stories = stories;
    }

    public List<TopStoried> getTop_stories() {
        return top_stories;
    }

    public void setTop_stories(List<TopStoried> top_stories) {
        this.top_stories = top_stories;
    }

    public class Stories {
        @SerializedName("ga_prefix")
        private String ga_prefix;
        @SerializedName("id")
        private int id;
        @SerializedName("images")
        private String[] images;
        @SerializedName("title")
        private String title;
        @SerializedName("type")
        private int type;
        private String date ;

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public String getGa_prefix() {
            return ga_prefix;
        }

        public void setGa_prefix(String ga_prefix) {
            this.ga_prefix = ga_prefix;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String[] getImages() {
            return images;
        }

        public void setImages(String[] images) {
            this.images = images;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        @Override
        public String toString() {
            return "Stories{" +
                    "ga_prefix='" + ga_prefix + '\'' +
                    ", id=" + id +
                    ", images=" + Arrays.toString(images) +
                    ", title='" + title + '\'' +
                    ", type=" + type +
                    ", date='" + date + '\'' +
                    '}';
        }
    }

    public class TopStoried{
        @SerializedName("ga_prefix")
        private String ga_prefix;
        @SerializedName("id")
        private int id;
        @SerializedName("image")
        private String images;
        @SerializedName("title")
        private String title;
        @SerializedName("type")
        private int type;
        private String date ;

        public TopStoried(String title, String images, int id) {
            this.title = title;
            this.images = images;
            this.id = id;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }


        public String getGa_prefix() {
            return ga_prefix;
        }

        public void setGa_prefix(String ga_prefix) {
            this.ga_prefix = ga_prefix;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getImages() {
            return images;
        }

        public void setImages(String images) {
            this.images = images;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        @Override
        public String toString() {
            return "TopStoried{" +
                    "ga_prefix='" + ga_prefix + '\'' +
                    ", id=" + id +
                    ", images='" + images + '\'' +
                    ", title='" + title + '\'' +
                    ", type=" + type +
                    ", date='" + date + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "NewsListBean{" +
                "date='" + date + '\'' +
                ", stories=" + stories +
                ", top_stories=" + top_stories +
                '}';
    }
}
