package com.kty.dailynotepractices;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Data {
    private String content ;

    private String date;



    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Data(String content) {
        long now = System.currentTimeMillis();
        Date mDate = new Date(now);
        this.content = content;
        SimpleDateFormat simpleDate = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String nowTime = simpleDate.format(mDate);
        this.date= nowTime;
    }
}

