package com.firstapp.emotiondetection;

import androidx.appcompat.app.AppCompatActivity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class CrudActivity extends AppCompatActivity {
    DBMain dBmain;
    SQLiteDatabase sqLiteDatabase;
    EditText lname, fname;
    Button submit, display, edit;
    int id = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crud);

        dBmain = new DBMain(this);
        //create object
        findid();
        insertData();
        cleardata();
        editdata();
    }

    private void editdata() {
        if (getIntent().getBundleExtra("studata") != null) {
            Bundle bundle = getIntent().getBundleExtra("studata");
            id = bundle.getInt("id");
            fname.setText(bundle.getString("fname"));
            lname.setText(bundle.getString("lname"));

            edit.setVisibility(View.VISIBLE);
            submit.setVisibility(View.GONE);
        }
    }

    private void cleardata() {
        fname.setText("");
        lname.setText("");
    }

    private void insertData() {
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ContentValues contentValues = new ContentValues();
                contentValues.put("fname", fname.getText().toString().trim());
                contentValues.put("lname", lname.getText().toString().trim());

                sqLiteDatabase = dBmain.getWritableDatabase();
                Long recid = sqLiteDatabase.insert("subject", null, contentValues);
                if (recid != null) {
                    Toast.makeText(CrudActivity.this, "successfully insert", Toast.LENGTH_SHORT).show();
                    cleardata();
                } else {
                    Toast.makeText(CrudActivity.this, "something wrong try again", Toast.LENGTH_SHORT).show();
                }
            }
        });
        display.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CrudActivity.this, CrudActivity2.class);
                startActivity(intent);
            }
        });
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ContentValues contentValues = new ContentValues();
                contentValues.put("fname", fname.getText().toString().trim());
                contentValues.put("lname", lname.getText().toString().trim());

                sqLiteDatabase = dBmain.getWritableDatabase();
                long recid = sqLiteDatabase.update("subject", contentValues, "id=" + id, null);
                if (recid != -1) {
                    Toast.makeText(CrudActivity.this, "Update successfully", Toast.LENGTH_SHORT).show();
                    submit.setVisibility(View.VISIBLE);
                    edit.setVisibility(View.GONE);
                    cleardata();
                } else {
                    Toast.makeText(CrudActivity.this, "something wrong try again", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void findid() {
        fname = (EditText) findViewById(R.id.fname);
        lname = (EditText) findViewById(R.id.lname);
        submit = (Button) findViewById(R.id.submit_btn);
        display = (Button) findViewById(R.id.display_btn);
        edit = (Button) findViewById(R.id.edit_btn);
    }
}