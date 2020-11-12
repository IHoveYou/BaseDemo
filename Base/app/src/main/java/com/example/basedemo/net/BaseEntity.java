package com.example.basedemo.net;


import com.google.gson.annotations.SerializedName;

public class BaseEntity<T> {
    @SerializedName("data")
    public T data;
    @SerializedName("code")
    private String respCode;
    @SerializedName("message")
    private String respContent;

    public String getRespCode() {
        return respCode;
    }

    public void setRespCode(String respCode) {
        this.respCode = respCode;
    }

    public String getRespContent() {
        return respContent;
    }

    public void setRespContent(String respContent) {
        this.respContent = respContent;
    }

}
