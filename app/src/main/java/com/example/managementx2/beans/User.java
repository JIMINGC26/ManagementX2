package com.example.managementx2.beans;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.datatype.BmobRelation;

public class User extends BmobUser {

    private String account;


    private BmobRelation UserToTeam;

    public BmobRelation getUserToTeam() {
        return UserToTeam;
    }

    public void setUserToTeam(BmobRelation userToTeam) {
        UserToTeam = userToTeam;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }


}
