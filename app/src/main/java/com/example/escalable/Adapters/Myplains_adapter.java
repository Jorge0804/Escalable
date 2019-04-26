package com.example.escalable.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.escalable.Class.Data;
import com.example.escalable.Models.payments;
import com.example.escalable.R;

import java.util.List;

public class Myplains_adapter extends RecyclerView.Adapter<Myplains_adapter.ViewHolder>{

    private List<payments> pl;
    Context context;

    public Myplains_adapter(List<payments> pl) {
        this.pl = pl;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.myplainsadapter, viewGroup, false);
        context = view.getContext();
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
        viewHolder.txt_name.setText("$"+pl.get(i).getPlan());
        viewHolder.txt_price.setText("$"+pl.get(i).getPrice().toString());
        viewHolder.txt_date.setText("$"+pl.get(i).getFinished_at());
    }

    @Override
    public int getItemCount() {
        return pl.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView txt_name, txt_price, txt_date;

        public ViewHolder(@NonNull final View itemView) {
            super(itemView);
            context = itemView.getContext();
            txt_name = itemView.findViewById(R.id.lblplain);
            txt_price = itemView.findViewById(R.id.lblstatus);
            txt_date = itemView.findViewById(R.id.lbldate);
        }
    }
}
