package com.example.mca;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    EditText name, id, password, email, phone;
    Button insert, update, delete, view;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name = findViewById(R.id.name);
        id = findViewById(R.id.id);
        password = findViewById(R.id.password);
        email = findViewById(R.id.email);
        phone = findViewById(R.id.phone);

        insert = findViewById(R.id.button1);
        update = findViewById(R.id.button4);
        delete = findViewById(R.id.button2);
        view = findViewById(R.id.button3);

        DB = new DBHelper(this);

        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (id.getText().toString().isEmpty() || name.getText().toString().isEmpty() ||
                        password.getText().toString().isEmpty() || email.getText().toString().isEmpty() ||
                        phone.getText().toString().isEmpty()) {

                    Toast.makeText(MainActivity.this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
                    return;
                }

                int idInt = Integer.parseInt(id.getText().toString());
                String nameStr = name.getText().toString();
                String passStr = password.getText().toString();
                String emailStr = email.getText().toString();
                String phoneStr = phone.getText().toString();

                boolean checkInsertData = DB.insertuserdata(idInt, nameStr, passStr, emailStr, phoneStr);
                if (checkInsertData) {
                    Toast.makeText(MainActivity.this, "New Entry Inserted", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "New Entry Not Inserted", Toast.LENGTH_SHORT).show();
                }
            }
        });


        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int idInt = Integer.parseInt(id.getText().toString());
                String nameStr = name.getText().toString();

                boolean checkUpdateData = DB.updateuserdata(nameStr, idInt);
                if (checkUpdateData) {
                    Toast.makeText(MainActivity.this, "Entry Updated", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "Entry Not Updated", Toast.LENGTH_SHORT).show();
                }
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nameStr = name.getText().toString();

                boolean checkDeleteData = DB.deleteData(nameStr);
                if (checkDeleteData) {
                    Toast.makeText(MainActivity.this, "Entry Deleted", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "Entry Not Deleted", Toast.LENGTH_SHORT).show();
                }
            }
        });

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ViewActivity.class);
                startActivity(intent);
            }
        });
    }
}