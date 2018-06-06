package com.example.dell.todolist.utilities;

import java.io.Serializable;

public class Notes implements Serializable {

    private String content;
    private String title;
    private long dateTime;


    public Notes(String content, String title, long dateTime) {
        this.content = content;
        this.title = title;
        this.dateTime = dateTime;
    }

    public Notes() {

    }



    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDateTime(long dateTime) {
        this.dateTime = dateTime;
    }

    public long getDateTime() {
        return dateTime;
    }

    /*
    public String getDateTimeFormat(Context context) {

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss", context.getResources().getConfiguration().locale);
        simpleDateFormat.setTimeZone(TimeZone.getDefault());
        return simpleDateFormat.format(new Date(dateTime));
    }*/

}


