package com.example.shinelon.mymdapp.modle.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by Shinelon on 2017/1/31.
 */

public class WelcomeBean {
    public List<Creatives> creatives;

     public class Creatives {
        private String url;

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }

    public List<Creatives> getCreatives() {
        return creatives;
    }

    public void setCreatives(List<Creatives> creatives) {
        this.creatives = creatives;
    }
}
