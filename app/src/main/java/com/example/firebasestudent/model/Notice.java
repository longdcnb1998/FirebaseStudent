package com.example.firebasestudent.model;

public class Notice {

    private int seen;
    private String content;
    private String timeStamp;


    public Notice() {
    }

    public Notice(int seen, String content, String timeStamp) {
        this.seen = seen;
        this.content = content;
        this.timeStamp = timeStamp;
    }

    public int getSeen() {
        return seen;
    }

    public void setSeen(int seen) {
        this.seen = seen;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }
}
