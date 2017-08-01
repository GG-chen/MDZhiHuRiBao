package com.example.shinelon.mymdapp.modle.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Shinelon on 2017/2/1.
 */

public class NewItemBean implements Parcelable{
    private String date;
    private int id;
    private String title;
    private String images;

    public NewItemBean(int id, String title, String images) {
        this.id = id;
        this.title = title;
        this.images = images;
    }

    protected NewItemBean(Parcel in) {
        date = in.readString();
        id = in.readInt();
        title = in.readString();
        images = in.readString();
    }


    public static final Creator<NewItemBean> CREATOR = new Creator<NewItemBean>() {
        @Override
        public NewItemBean createFromParcel(Parcel in) {
            return new NewItemBean(in);
        }

        @Override
        public NewItemBean[] newArray(int size) {
            return new NewItemBean[size];
        }
    };

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.date);
        dest.writeInt(this.id);
        dest.writeString(this.title);
        dest.writeString(this.images);
    }
}
