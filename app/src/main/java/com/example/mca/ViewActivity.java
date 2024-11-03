package com.example.mca;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class ViewActivity extends AppCompatActivity {

    TextView textViewData;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);

        textViewData = findViewById(R.id.textViewData);
        DB = new DBHelper(this);

        displayData();
    }

    private void displayData() {
        Cursor cursor = DB.viewAllData();
        if (cursor.getCount() == 0) {
            textViewData.setText("No records found.");
            return;
        }

        StringBuilder builder = new StringBuilder();
        while (cursor.moveToNext()) {
            builder.append("ID: ").append(cursor.getInt(0)).append("\n");
            builder.append("Name: ").append(cursor.getString(1)).append("\n");
            builder.append("Password: ").append(cursor.getString(2)).append("\n");
            builder.append("Email: ").append(cursor.getString(3)).append("\n");
            builder.append("Phone: ").append(cursor.getString(4)).append("\n\n");
        }

        textViewData.setText(builder.toString());
        cursor.close();
    }
}