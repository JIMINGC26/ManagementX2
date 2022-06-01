package com.example.managementx2.beans;

import cn.bmob.v3.BmobObject;
import cn.bmob.v3.datatype.BmobPointer;

public class Comment extends BmobObject {

    private String content;
    private User author;
    private Task CommentToTask;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public Task getCommentToTask() {
        return CommentToTask;
    }

    public void setCommentToTask(Task commentToTask) {
        CommentToTask = commentToTask;
    }
}
