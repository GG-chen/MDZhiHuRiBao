package com.example.shinelon.mymdapp.modle.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by Shinelon on 2017/1/31.
 */

public class NewsListBean implements Parcelable{
    private String date;
    private List<Stories> stories;
    private List<TopStoried> top_stories;



    protected NewsListBean(Parcel in) {
        date = in.readString();
        stories = in.readArrayList(Stories.class.getClassLoader());
        top_stories = in.readArrayList(TopStoried.class.getClassLoader());
    }

    public static final Creator<NewsListBean> CREATOR = new Creator<NewsListBean>() {
        @Override
        public NewsListBean createFromParcel(Parcel in) {
            return new NewsListBean(in);
        }

        @Override
        public NewsListBean[] newArray(int size) {
            return new NewsListBean[size];
        }
    };

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.date);
        dest.writeList(stories);
        dest.writeList(top_stories);
    }

    public static class Stories implements Parcelable{
        private String ga_prefix;
        private int id;
        private List images;
        private String title;
        private int type;

        protected Stories(Parcel in) {
            ga_prefix = in.readString();
            id = in.readInt();
            images = in.readArrayList(List.class.getClassLoader());
            title = in.readString();
            type = in.readInt();
        }

        public static final Creator<Stories> CREATOR = new Creator<Stories>() {
            @Override
            public Stories createFromParcel(Parcel in) {
                return new Stories(in);
            }

            @Override
            public Stories[] newArray(int size) {
                return new Stories[size];
            }
        };

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

        public List getImages() {
            return images;
        }

        public void setImages(List images) {
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
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.ga_prefix);
            dest.writeInt(this.id);
            dest.writeList(this.images);
            dest.writeString(this.title);
            dest.writeInt(this.type);
        }
    }

    public static  class TopStoried implements Parcelable{
        private String ga_prefix;
        private int id;
        private String image;
        private String title;
        private int type;

        protected TopStoried(Parcel in) {
            ga_prefix = in.readString();
            id = in.readInt();
            image = in.readString();
            title = in.readString();
            type = in.readInt();
        }

        public static final Creator<TopStoried> CREATOR = new Creator<TopStoried>() {
            @Override
            public TopStoried createFromParcel(Parcel in) {
                return new TopStoried(in);
            }

            @Override
            public TopStoried[] newArray(int size) {
                return new TopStoried[size];
            }
        };

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

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
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
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.ga_prefix);
            dest.writeInt(this.id);
            dest.writeString(image);
            dest.writeString(this.title);
            dest.writeInt(this.type);
        }
    }
}
