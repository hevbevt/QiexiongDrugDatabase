package com.xinyi.duan.qiexiongdrugdatabase;

/**
 * Created by Duan on 2015/12/8.
 */
public class DrugInfo {
    private String drugName;
    private String drugStandard;
    private String drugId;

    public DrugInfo(String drugName, String drugStandard, String drugId) {
        this.drugName = drugName;
        this.drugStandard = drugStandard;
        this.drugId = drugId;
    }

    public String getDrugName() {
        return drugName;
    }

    public void setDrugName(String drugName) {
        this.drugName = drugName;
    }

    public String getDrugStandard() {
        return drugStandard;
    }

    public void setDrugStandard(String drugStandard) {
        this.drugStandard = drugStandard;
    }

    public String getDrugId() {
        return drugId;
    }

    public void setDrugId(String drugId) {
        this.drugId = drugId;
    }
}
