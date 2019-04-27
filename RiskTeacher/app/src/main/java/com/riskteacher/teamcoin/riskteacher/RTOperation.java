package com.riskteacher.teamcoin.riskteacher;

import java.math.BigDecimal;
import com.google.gson.annotations.SerializedName;

public class RTOperation {
    @SerializedName("id")
    private String id;
    @SerializedName("username")
    private String username;
    @SerializedName("opType")
    private String opType;
    @SerializedName("balance")
    private BigDecimal balance;
    @SerializedName("opProfit")
    private BigDecimal opProfit;
    @SerializedName("opResult")
    private String opResult;
    @SerializedName("opDate")
    private String opDate;

    public RTOperation(String username, String opType, BigDecimal balance, BigDecimal opProfit, String opResult, String opDate) {
        this.username = username;
        this.opType = opType;
        this.balance = balance;
        this.opProfit = opProfit;
        this.opResult = opResult;
        this.opDate = opDate;
    }

    public RTOperation(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getOpType() {
        return opType;
    }

    public void setOpType(String opType) {
        this.opType = opType;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public BigDecimal getOpProfit() {
        return opProfit;
    }

    public void setOpProfit(BigDecimal opProfit) {
        this.opProfit = opProfit;
    }

    public String getOpResult() {
        return opResult;
    }

    public void setOpResult(String opResult) {
        this.opResult = opResult;
    }

    public String getOpDate() {
        return opDate;
    }

    public void setOpDate(String opDate) {
        this.opDate = opDate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "RTOperation{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", opType='" + opType + '\'' +
                ", balance='" + balance.toString() + '\'' +
                ", opProfit='" + opProfit.toString() + '\'' +
                ", opResult='" + opResult + '\'' +
                ", opDate='" + opDate + '\'' +
                '}';
    }
}
