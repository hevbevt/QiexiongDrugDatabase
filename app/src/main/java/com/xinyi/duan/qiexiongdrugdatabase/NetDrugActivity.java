package com.xinyi.duan.qiexiongdrugdatabase;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

/**
 * Created by Duan on 2015/12/9.
 */
public class NetDrugActivity extends AppCompatActivity {

    private ListView drugList;
    private DrugAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.check_all);
        drugList = (ListView) findViewById(R.id.list_view);
        adapter = new DrugAdapter(GetDrugs.lists, this);
        drugList.setAdapter(adapter);
    }
}
