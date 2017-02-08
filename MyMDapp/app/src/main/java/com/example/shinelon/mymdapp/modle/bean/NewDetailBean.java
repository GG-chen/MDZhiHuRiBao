package com.example.shinelon.mymdapp.modle.bean;

import java.util.List;

import static android.R.id.list;

/**
 * Created by Shinelon on 2017/2/3.
 */

public class NewDetailBean {
    public String body;
    public List css;
    public String ga_prefix;
    public int id;
    public String image;
    public String image_source;
    public List images;
    public List js;
    public String share_url;
    public String title;
    public int type;

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public List getCss() {
        return css;
    }

    public void setCss(List css) {
        this.css = css;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getImage_source() {
        return image_source;
    }

    public void setImage_source(String image_source) {
        this.image_source = image_source;
    }

    public List getImages() {
        return images;
    }

    public void setImages(List images) {
        this.images = images;
    }

    public List getJs() {
        return js;
    }

    public void setJs(List js) {
        this.js = js;
    }

    public String getShare_url() {
        return share_url;
    }

    public void setShare_url(String share_url) {
        this.share_url = share_url;
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
}
