package com.gmo.gmo.common;

public class MyResponse {
    private int status;
    private String message;
    private long timeStamp;
    private Object data;

    public MyResponse(int status, String message, long timeStamp, Object data) {
        this.status = status;
        this.message = message;
        this.timeStamp = timeStamp;
        this.data = data;
    }
    public MyResponse() {
        this.status = 200;
        this.message = "Success";
        this.timeStamp = System.currentTimeMillis();
        this.data = null;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
