package com.example.myapplication_project.ui.home;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication_project.R;

public class UpdateActivity extends AppCompatActivity {

    EditText meal_input, calouries_count_input;
    Spinner data_input;
    Button update_button, delete_button;

    String id, meal, data, calouries_count;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        meal_input = findViewById(R.id.meal_input2);
        data_input = findViewById(R.id.data_input2);
        calouries_count_input = findViewById(R.id.calouries_count_input2);
        update_button = findViewById(R.id.update_button);
        delete_button = findViewById(R.id.delete_button);

        //First we call this
        getAndSetIntentData();

        //Set actionbar title after getAndSetIntentData method
        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setTitle(meal);
        }

        update_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //And only then we call this
                MyDatabaseHelper myDB = new MyDatabaseHelper(UpdateActivity.this);
                meal = meal_input.getText().toString().trim();
                data = data_input.getSelectedItem().toString().trim();
                calouries_count = calouries_count_input.getText().toString().trim();
                myDB.updateData(id, meal, data, calouries_count);
            }
        });
        delete_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                confirmDialog();
            }
        });

    }

    void getAndSetIntentData(){
        if(getIntent().hasExtra("id") && getIntent().hasExtra("meal") &&
                getIntent().hasExtra("data") && getIntent().hasExtra("calouries_count")){
            //Getting Data from Intent
            id = getIntent().getStringExtra("id");
            meal = getIntent().getStringExtra("meal");
            data = getIntent().getStringExtra("data");
            calouries_count = getIntent().getStringExtra("calouries_count");

            //Setting Intent Data
            meal_input.setText(meal);
            calouries_count_input.setText(calouries_count);
            Log.d("stev", meal+" "+data+" "+calouries_count);
        }else{
            Toast.makeText(this, "Нет записей.", Toast.LENGTH_SHORT).show();
        }
    }

    void confirmDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Удалить " + meal + " ?");
        builder.setMessage("Вы уверены что хотите удалить " + meal + " ?");
        builder.setPositiveButton("Да", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                MyDatabaseHelper myDB = new MyDatabaseHelper(UpdateActivity.this);
                myDB.deleteOneRow(id);
                finish();
            }
        });
        builder.setNegativeButton("Нет", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.create().show();
    }
}
