package com.riskteacher.teamcoin.riskteacher;

import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;

public class RTOperations {

    @SerializedName("RTOperations")
    private ArrayList<RTOperation> rtoperations;

    public ArrayList<RTOperation> getEmployees() {
        return rtoperations;
    }

    public void setOperations(ArrayList<RTOperation> ops) {
        this.rtoperations = ops;
    }
}
