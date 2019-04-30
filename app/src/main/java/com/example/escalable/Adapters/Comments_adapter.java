package com.example.escalable.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.escalable.Models.comments;
import com.example.escalable.R;

import java.util.List;

public class Comments_adapter extends RecyclerView.Adapter<Comments_adapter.ViewHolder>{

    private List<comments> lc;
    Context context;

    public Comments_adapter(List<comments> lc) {
        this.lc = lc;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cardview_comments, viewGroup, false);
        context = view.getContext();
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.name_user.setText(lc.get(i).getUser_name());
        viewHolder.comment.setText(lc.get(i).getContent());
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView name_user, comment;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name_user = itemView.findViewById(R.id.txt_user);
            comment = itemView.findViewById(R.id.comment);
        }
    }
}
