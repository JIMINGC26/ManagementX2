package com.example.managementx2.beans;

import cn.bmob.v3.BmobObject;
import cn.bmob.v3.datatype.BmobPointer;

public class Comment extends BmobObject {

    private String content;
    private BmobPointer author;
    private BmobPointer CommentToTask;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public BmobPointer getAuthor() {
        return author;
    }

    public void setAuthor(BmobPointer author) {
        this.author = author;
    }

    public BmobPointer getCommentToTask() {
        return CommentToTask;
    }

    public void setCommentToTask(BmobPointer commentToTask) {
        CommentToTask = commentToTask;
    }
}
