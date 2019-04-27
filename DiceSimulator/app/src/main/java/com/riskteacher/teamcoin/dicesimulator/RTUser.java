package com.riskteacher.teamcoin.dicesimulator;

import java.math.BigDecimal;

public class RTUser {
    private String rtuser;
    private String rtpass;
    private String rtemail;
    private BigDecimal rtbalance;
    public RTUser(String u, String p) {
        rtuser = u;
        rtpass = p;
        rtbalance = new BigDecimal(0);
    }

    public String getRtuser() {
        return rtuser;
    }

    public void setRtuser(String rtuser) {
        this.rtuser = rtuser;
    }

    public String getRtpass() {
        return rtpass;
    }

    public void setRtpass(String rtpass) {
        this.rtpass = rtpass;
    }

    public String getRtemail() {
        return rtemail;
    }

    public void setRtemail(String rtemail) {
        this.rtemail = rtemail;
    }

    public BigDecimal getRtbalance() {
        return rtbalance;
    }

    public void setRtbalance(BigDecimal rtbalance) {
        this.rtbalance = rtbalance;
    }

    public boolean verifyUser(RTUser w){
        return (w.getRtuser().equals(rtuser)&&w.getRtpass().equals(rtpass));
    }

}
