package com.example.managementx2.beans;

import cn.bmob.v3.BmobObject;
import cn.bmob.v3.datatype.BmobPointer;

public class Task extends BmobObject {

    private String TaskName;
    private String detail;
    private BmobPointer TaskToTeam;

    public String getTaskName() {
        return TaskName;
    }

    public void setTaskName(String taskName) {
        TaskName = taskName;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public BmobPointer getTaskToTeam() {
        return TaskToTeam;
    }

    public void setTaskToTeam(BmobPointer taskToTeam) {
        TaskToTeam = taskToTeam;
    }
}
