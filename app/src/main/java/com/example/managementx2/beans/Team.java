package com.example.managementx2.beans;

import cn.bmob.v3.BmobObject;
import cn.bmob.v3.datatype.BmobRelation;

public class Team extends BmobObject {

    private String teamName;
    private BmobRelation UserOFTeam;

    public BmobRelation getUserOFTeam() {
        return UserOFTeam;
    }

    public void setUserOFTeam(BmobRelation userOFTeam) {
        UserOFTeam = userOFTeam;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }
}
