package com.it520.activity.topic.model.bean;

public class SunjDetailBean {

    /**
     * message : 操作成功
     * data : 数据主体
     * code : 1
     */
    private String message;
    private DataBean data;
    private int code;

    public void setMessage(String message) {
        this.message = message;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public DataBean getData() {
        return data;
    }

    public int getCode() {
        return code;
    }

    @Override
    public String toString() {
        return "SunjDetailBean{" +
                "message='" + message + '\'' +
                ", data=" + data +
                ", code=" + code +
                '}';
    }
}
