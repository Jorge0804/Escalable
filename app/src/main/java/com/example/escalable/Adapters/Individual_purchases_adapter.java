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
    private List<individual_plains> cl;
    Context context;

    public Individual_purchases_adapter(List<individual_plains> cl) {
        this.cl = cl;
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
        viewHolder.txt_name.setText("$"+cl.get(i).getCourse_id().toString());
        viewHolder.txt_price.setText("$"+cl.get(i).getPrice().toString());
        viewHolder.txt_date.setText("$"+cl.get(i).getCreated_at());
    }

    @Override
    public int getItemCount() {
        return cl.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView txt_name, txt_price, txt_date;

        public ViewHolder(@NonNull final View itemView) {
            super(itemView);
            context = itemView.getContext();
            txt_name = itemView.findViewById(R.id.lblcurse);
            txt_price = itemView.findViewById(R.id.lblprice);
            txt_date = itemView.findViewById(R.id.lblboughtdate);
        }
    }
}
