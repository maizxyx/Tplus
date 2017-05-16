package com.ww6.mai.tplus.model;

/**
 * Created by Administrator on 2017/4/13 0013.
 */

public class TplusEntity {
    public String id;
    public String num;
    public String project;
    public String type;
    public String price;
    public String edit;
    public String sms;
    public String shortcode;

    public TplusEntity() {
    }

    public TplusEntity(String id,String num, String project,  String type, String price,String edit, String sms, String shortcode) {
        this.id = id;
        this.num = num;
        this.project = project;
        this.price = price;
        this.type = type;
        this.edit = edit;
        this.sms = sms;
        this.shortcode = shortcode;
    }
    public String getId() {
        return id;
    }

    public void setId(String num) {
        this.id = id;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getEdit() {
        return edit;
    }

    public void setEdit(String edit) {
        this.edit = edit;
    }

    public String getSms() {
        return sms;
    }

    public void setSms(String sms) {
        this.sms = sms;
    }

    public String getShortcode() {
        return shortcode;
    }

    public void setShortcode(String shortcode) {
        this.shortcode = shortcode;
    }
}
