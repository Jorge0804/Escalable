package com.example.escalable.Adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.toolbox.NetworkImageView;
import com.example.escalable.Activities.MainActivity;
import com.example.escalable.Models.courses;
import com.example.escalable.R;
import com.example.escalable.Singletones.VolleyS;

import java.util.List;

public class Courses_adapter extends RecyclerView.Adapter<Courses_adapter.ViewHolder>{

    private List<courses> cl;

    public Courses_adapter(List<courses> cl) {
        this.cl = cl;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cardview_courses, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
        viewHolder.img_course.setImageUrl(cl.get(i).getSrc(), VolleyS.getinstance(VolleyS.getC).getImageLoader());
        viewHolder.txt_name.setText(cl.get(i).getName());
        viewHolder.txt_info.setText(cl.get(i).getInformation());
    }

    @Override
    public int getItemCount() {
        return cl.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
    NetworkImageView img_course;
    TextView txt_name, txt_info;

        public ViewHolder(@NonNull final View itemView) {
            super(itemView);
            img_course = itemView.findViewById(R.id.image_course);
            txt_name = itemView.findViewById(R.id.name_course);
            txt_info = itemView.findViewById(R.id.information_course);
        }
    }
}
