package com.example.amcfire.favoritewebsites;

public class WebUser {
    private String wuser;
    private String wpass;
    public WebUser(String u, String p) {
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

    public boolean verifyUser(WebUser w){
        return (w.getWuser().equals(wuser)&&w.getWpass().equals(wpass));
    }

}
