package com.example.escalable.Adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.toolbox.NetworkImageView;
import com.example.escalable.Activities.MainActivity;
import com.example.escalable.Class.Data;
import com.example.escalable.Models.courses;
import com.example.escalable.R;
import com.example.escalable.Singletones.VolleyS;
import com.squareup.picasso.Picasso;

import java.util.List;

public class Courses_adapter extends RecyclerView.Adapter<Courses_adapter.ViewHolder>{

    private List<courses> cl;
    Context context;

    public Courses_adapter(List<courses> cl) {
        this.cl = cl;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cardview_courses, viewGroup, false);
        context = view.getContext();
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
        Picasso.with(context).load(Data.Image_url + "courses_img/" + cl.get(i).getSrc()).fit().into(viewHolder.image);
        Log.d("img", cl.get(i).getSrc());
        viewHolder.txt_price.setText("$"+cl.get(i).getPrice().toString());

        if(cl.get(i).getName().length() > 36)
        {
            viewHolder.txt_name.setText(cl.get(i).getName().substring(0, 34) + "...");
        }
        else
        {
            viewHolder.txt_name.setText(cl.get(i).getName());
        }
    }

    @Override
    public int getItemCount() {
        return cl.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
    TextView txt_name, txt_price;
    ImageView image;

        public ViewHolder(@NonNull final View itemView) {
            super(itemView);
            context = itemView.getContext();
            image = itemView.findViewById(R.id.image_course);
            txt_name = itemView.findViewById(R.id.name_course);
            txt_price = itemView.findViewById(R.id.price_course);
        }
    }
}
