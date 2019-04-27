package com.example.amcfire.wallmartlab;

import android.os.Build;
import android.support.annotation.RequiresApi;

import java.util.Objects;

public class WallmartUser {
    private String wuser;
    private String wpass;
    public WallmartUser(String u, String p) {
        wuser = u;
        wpass = p;
    }

    public String getWuser() {
        return wuser;
    }

    public void setWuser(String wuser) {
        this.wuser = wuser;
    }

    public String getWpass() {
        return wpass;
    }

    public void setWpass(String wpass) {
        this.wpass = wpass;
    }

    public boolean verifyUser(WallmartUser w){
        return (w.getWuser().equals(wuser)&&w.getWpass().equals(wpass));
    }

}
