package org.techtown.termproject;

import java.util.Date;

public class todo {
    int     _id;
    String  title;
    String  content;
    String  date;
    int     important;

    public todo()
    {
    }

    public todo(int _id, String title, String content, String date, int important) {
        this._id = _id;
        this.title = title;
        this.content = content;
        this.date = date;
        this.important = important;
    }

    public int get_Id() {
        return _id;
    }

    public void set_Id(int _id) {
        this._id = _id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

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

    public int getImportant() {
        return important;
    }

    public void setImportant(int important) {
        this.important = important;
    }
}
