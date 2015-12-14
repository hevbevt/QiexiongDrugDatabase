package com.xinyi.duan.qiexiongdrugdatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Duan on 2015/12/8.
 */
public class GetDrugs {

    public static List<DrugInfo> lists = new ArrayList<>();

    public static void getDrugs(String drugName, String drugStandard, String drugId) {
        DrugInfo drugInfo = new DrugInfo(drugName, drugStandard, drugId);
        lists.add(drugInfo);
    }
}
