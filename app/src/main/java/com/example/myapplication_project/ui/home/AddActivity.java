package com.example.myapplication_project.ui.home;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication_project.R;

public class AddActivity extends AppCompatActivity {

    EditText meal_input, calouries_count_input;
    Spinner data_input;
    Button add_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        meal_input = findViewById(R.id.meal_input);
        data_input = findViewById(R.id.data_input);
        calouries_count_input = findViewById(R.id.calouries_count_input);
        add_button = findViewById(R.id.add_button);
        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyDatabaseHelper myDB = new MyDatabaseHelper(AddActivity.this);
                myDB.addMeal(meal_input.getText().toString().trim(),
                        data_input.getSelectedItem().toString().trim(),
                        Integer.valueOf(calouries_count_input.getText().toString().trim()));
            }
        });
    }
}
