package com.example.managementx2.beans;

import cn.bmob.v3.BmobObject;
import cn.bmob.v3.datatype.BmobPointer;

public class Task extends BmobObject {

    private String taskName;
    private String detail;
    private Team TaskToTeam;

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public Team getTaskToTeam() {
        return TaskToTeam;
    }

    public void setTaskToTeam(Team taskToTeam) {
        TaskToTeam = taskToTeam;
    }
}
