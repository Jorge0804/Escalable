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
import com.example.escalable.Models.blogs;
import com.example.escalable.R;
import com.example.escalable.Singletones.VolleyS;
import com.squareup.picasso.Picasso;

import java.util.List;

public class Blogs_adapter extends RecyclerView.Adapter<Blogs_adapter.ViewHolder>{

    private List<blogs> bl;
    Context context;

    public Blogs_adapter(List<blogs> bl) {
        this.bl = bl;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cardview_blogs, viewGroup, false);
        context = view.getContext();
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
        Picasso.with(context).load(Data.Image_url + "posts_img/" + bl.get(i).getFile()).fit().into(viewHolder.image);
        Log.d("img", bl.get(i).getFile());
        viewHolder.txt_title.setText(bl.get(i).getName().toString());
        viewHolder.txt_extract.setText(bl.get(i).getExcerpt().toString());
        viewHolder.txt_footer.setText(bl.get(i).getId() + "|" + bl.get(i).getCreated_at());

        viewHolder.itemView.setOnClickListener(blogs.showinfo(bl.get(i), context));
    }

    @Override
    public int getItemCount() {
        return bl.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView txt_title, txt_extract, txt_footer;
        ImageView image;

        public ViewHolder(@NonNull final View itemView) {
            super(itemView);
            context = itemView.getContext();
            image = itemView.findViewById(R.id.image_course);
            txt_title = itemView.findViewById(R.id.titlecardviewblog);
            txt_extract = itemView.findViewById(R.id.extractcardviewblog);
            txt_footer = itemView.findViewById(R.id.footerblogcardview);
        }
    }
}