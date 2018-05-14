package com.example.ishanpant.colorsqliteapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Main2Activity extends AppCompatActivity {
    private EditText editText;
    private Button button1;
    private SqliteDataHelper sqliteDataHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        editText = (EditText) findViewById(R.id.editText);
        button1 = (Button) findViewById(R.id.button1);
        sqliteDataHelper = new SqliteDataHelper(Main2Activity.this);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickButton();
            }
        });
    }

    private void clickButton() {
        String name = editText.getText().toString().trim();
        sqliteDataHelper.addName(name);
        Toast.makeText(Main2Activity.this , "Data Added Successfully..." , Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(Main2Activity.this , MainActivity.class);
        startActivity(intent);
    }
}
