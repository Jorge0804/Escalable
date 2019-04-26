package com.example.escalable.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.escalable.Models.individual_plains;
import com.example.escalable.R;

import java.util.List;

public class Individual_purchases_adapter extends RecyclerView.Adapter<Individual_purchases_adapter.ViewHolder> {
    private List<individual_plains> ip;
    Context context;

    public Individual_purchases_adapter(List<individual_plains> ip) {
        this.ip = ip;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.individualpurchases, viewGroup, false);
        context = view.getContext();
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
        viewHolder.txt_name_course.setText("$"+ip.get(i).getCourse_id().toString());
        viewHolder.txt_price_course.setText("$"+ip.get(i).getPrice());
        viewHolder.txt_date_course.setText("$"+ip.get(i).getCreated_at());
    }

    @Override
    public int getItemCount() {
        return ip.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView txt_name_course,
                txt_price_course,
                txt_date_course;

        public ViewHolder(@NonNull final View itemView) {
            super(itemView);
            context = itemView.getContext();
            txt_name_course = itemView.findViewById(R.id.lblcurse);
            txt_price_course = itemView.findViewById(R.id.lblprice);
            txt_date_course = itemView.findViewById(R.id.lblboughtdate);
        }
    }
}
