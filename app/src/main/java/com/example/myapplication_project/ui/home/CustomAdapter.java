package com.example.myapplication_project.ui.home;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication_project.R;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    private Context context;
    private Activity activity;
    private ArrayList meal_id, meal, data, calouries_count;

    CustomAdapter(Activity activity, Context context, ArrayList meal_id, ArrayList meal, ArrayList data,
                  ArrayList calouries_count){
        this.activity = activity;
        this.context = context;
        this.meal_id = meal_id;
        this.meal = meal;
        this.data = data;
        this.calouries_count = calouries_count;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_row, parent, false);
        return new MyViewHolder(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {
        holder.meal_txt.setText(String.valueOf(meal.get(position)));
        holder.meal_data_txt.setText(String.valueOf(data.get(position)));
        holder.calouries_count_txt.setText(String.valueOf(calouries_count.get(position)));
        String daata = String.valueOf(data.get(position));
        if (daata.equals("Завтрак")){
            holder.meal_img.setImageBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.breakfast));
        }
        if (daata.equals("Обед")){
            holder.meal_img.setImageBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.lunch));
        }
        if (daata.equals("Ужин")){
            holder.meal_img.setImageBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.dinner));
        }
        if (daata.equals("Поздний ужин")){
            holder.meal_img.setImageBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.late_dinner));
        }
        //Recyclerview onClickListener
        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, UpdateActivity.class);
                intent.putExtra("id", String.valueOf(meal_id.get(position)));
                intent.putExtra("meal", String.valueOf(meal.get(position)));
                intent.putExtra("data", String.valueOf(data.get(position)));
                intent.putExtra("calouries_count", String.valueOf(calouries_count.get(position)));
                activity.startActivityForResult(intent, 1);
            }
        });


    }

    @Override
    public int getItemCount() {
        return meal_id.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView meal_img;

        TextView meal_txt, meal_data_txt, calouries_count_txt;
        LinearLayout mainLayout;

        MyViewHolder(@NonNull View itemView) {
            super(itemView);
            meal_img = itemView.findViewById(R.id.meal_img);
            meal_img.setAdjustViewBounds(true);
            meal_txt = itemView.findViewById(R.id.meal_txt);
            meal_data_txt = itemView.findViewById(R.id.meal_data_txt);
            calouries_count_txt = itemView.findViewById(R.id.calouries_count_txt);
            mainLayout = itemView.findViewById(R.id.mainLayout);
            //Animate Recyclerview
            Animation translate_anim = AnimationUtils.loadAnimation(context, R.anim.translate_anim);
            mainLayout.setAnimation(translate_anim);
        }

    }

}
