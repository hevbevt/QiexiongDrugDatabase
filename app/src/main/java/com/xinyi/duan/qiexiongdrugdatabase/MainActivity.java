package com.xinyi.duan.qiexiongdrugdatabase;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private DrugsDatabaseHelper dbHelper;


    private EditText drugNameEditText;
    private EditText drugStandardEditText;
    private EditText drugEditId;
    private EditText queryText;

    private TextView drugText;

    StringBuilder queryAnswer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dbHelper = new DrugsDatabaseHelper(this, "DrugStore.db", null, 2);

        Button addDrug = (Button) findViewById(R.id.add_drug_button);
        Button checkAll = (Button) findViewById(R.id.check_all);
        drugNameEditText = (EditText) findViewById(R.id.drug_name);
        drugStandardEditText = (EditText) findViewById(R.id.drug_standard);
        drugEditId = (EditText) findViewById(R.id.drug_id);
        drugText = (TextView) findViewById(R.id.drug_text);

        queryText = (EditText) findViewById(R.id.query_text);

        queryAnswer = new StringBuilder();

        drugText.setMovementMethod(ScrollingMovementMethod.getInstance());
        //将数据库中的药品存储到DrugList中。
        setDrugList();
        addDrug.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                ContentValues values = new ContentValues();
                String name = drugNameEditText.getText().toString();
                String standard = drugStandardEditText.getText().toString();
                String drugId = drugEditId.getText().toString();
                if (name.isEmpty() || standard.isEmpty() || drugId.isEmpty()) {
                    Toast.makeText(MainActivity.this,
                            "请重新输入要存入的信息。", Toast.LENGTH_SHORT).show();
                    clearEditText();
                    return;
                }

                values.put("name",name);
                values.put("standard", standard);
                values.put("drug_id",drugId);
                GetDrugs.getDrugs(name, standard, drugId);
                db.insert("Drug", null, values);
                clearEditText();
            }
        });

        Button queryButton = (Button) findViewById(R.id.query_button);
        queryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                Cursor cursor = db.query("Drug", null, null, null, null, null, null);
                queryAnswer = new StringBuilder();
                if (cursor.moveToFirst()) {
                    do {
                        String name = cursor.getString(cursor.getColumnIndex("name"));
                        String standard = cursor.getString(cursor.getColumnIndex("standard"));
                        String drugId = cursor.getString(cursor.getColumnIndex("drug_id"));
                        if (name.contains(queryText.getText().toString()) ||
                                drugId.contains(queryText.getText().toString()))
                            queryAnswer.append("药品名称:" + name + "\n药品规格:\n" + standard
                                    + "\n药品编号:" + drugId + "\n");
                    } while (cursor.moveToNext());
                }
               // if (!queryAnswer.toString().isEmpty())
                drugText.setText(queryAnswer);
                cursor.close();
            }
        });

        checkAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AllActivity.class);
                startActivity(intent);
            }
        });
    }

    private void clearEditText() {
        drugNameEditText.setText(null);
        drugStandardEditText.setText(null);
        drugEditId.setText(null);
    }

    private void setDrugList() {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor cursor = db.query("Drug", null, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                String name = cursor.getString(cursor.getColumnIndex("name"));
                String standard = cursor.getString(cursor.getColumnIndex("standard"));
                String drugId = cursor.getString(cursor.getColumnIndex("drug_id"));
                GetDrugs.getDrugs(name, standard, drugId);
            } while (cursor.moveToNext());
        }
        cursor.close();
    }

}
