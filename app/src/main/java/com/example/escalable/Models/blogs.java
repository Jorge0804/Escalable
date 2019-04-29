package com.example.escalable.Models;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.escalable.Activities.InfoBlog;
import com.example.escalable.Activities.InfoCourse;
import com.example.escalable.Adapters.Blogs_adapter;
import com.example.escalable.Adapters.Courses_adapter;
import com.example.escalable.Class.Data;
import com.example.escalable.R;
import com.example.escalable.Singletones.VolleyS;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.List;

public class blogs {
    Integer id;
    String name, excerpt, file, description, created_at;
    List<likes> likes;

    public Integer liked = 0;

    public blogs(Integer id, String name, String excerpt, String file, String description, String created_at,
                 List<likes> likes) {
        this.id = id;
        this.name = name;
        this.excerpt = excerpt;
        this.file = file;
        this.description = description;
        this.created_at = created_at;
        this.likes = likes;
    }

    public List<likes> getLikes() {
        return likes;
    }

    public void setLikes(List<likes> llikes) {
        this.likes = llikes;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getExcerpt() {
        return excerpt;
    }

    public void setExcerpt(String excerpt) {
        this.excerpt = excerpt;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public static void ShowBlogs(View v, final Context context)
    {
        final RecyclerView recyclerView;
        recyclerView = v.findViewById(R.id.blogs_container);

        JsonArrayRequest jar = new JsonArrayRequest(
                Request.Method.GET,
                Data.url+"showposts",
                null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Gson gson = new Gson();
                        Type type = new TypeToken<List<blogs>>(){}.getType();
                        List<blogs> lb = gson.fromJson(response.toString(), type);
                        Blogs_adapter blogs_adapter = new Blogs_adapter(lb);
                        recyclerView.setAdapter(blogs_adapter);
                        LinearLayoutManager lm = new LinearLayoutManager(context);
                        lm.setOrientation(LinearLayoutManager.VERTICAL);
                        recyclerView.setLayoutManager(new LinearLayoutManager(context));
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, "Verifica tu conexión a internet", Toast.LENGTH_SHORT).show();
            }
        });
        VolleyS.getinstance(context).getRq().add(jar);
    }

    public static void ShowBestBlogs(View v, final Context context)
    {
        final RecyclerView recyclerView;
        recyclerView = v.findViewById(R.id.bestblogs);

        JsonArrayRequest jar = new JsonArrayRequest(
                Request.Method.GET,
                Data.url+"bestposts",
                null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Gson gson = new Gson();
                        Type type = new TypeToken<List<blogs>>(){}.getType();
                        List<blogs> lb = gson.fromJson(response.toString(), type);
                        Blogs_adapter blogs_adapter = new Blogs_adapter(lb);
                        recyclerView.setAdapter(blogs_adapter);
                        LinearLayoutManager lm = new LinearLayoutManager(context);
                        lm.setOrientation(LinearLayoutManager.VERTICAL);
                        recyclerView.setLayoutManager(new LinearLayoutManager(context));
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, "Verifica tu conexión a internet", Toast.LENGTH_SHORT).show();
            }
        });
        VolleyS.getinstance(context).getRq().add(jar);
    }
    
    public static View.OnClickListener showinfo(final blogs c, final Context context, final ImageView img_like)
    {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v.getId() != R.id.btn_like)
                {
                    Intent in = new Intent(v.getContext(), InfoBlog.class);
                    in.putExtra("id", c.getId().toString());
                    in.putExtra("name", c.getName());
                    in.putExtra("excerpt", c.getExcerpt());
                    in.putExtra("file", c.getFile());
                    in.putExtra("description", c.getDescription());
                    in.putExtra("created", c.getCreated_at());
                    context.startActivity(in);
                }
                else
                {
                    img_like.setImageResource(R.mipmap.like);
                }
            }
        };
    }

    public static View.OnClickListener like(final ImageView img_like, final blogs blog, final TextView numlikes,
                                            final Context context,final Integer i)
    {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ((blog.liked+"").equals("1"))
                {
                    JSONArray user = new JSONArray();
                    try {
                        user.put(0, Data.getapi_token());
                        user.put(1, blog.getId());
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    JsonArrayRequest jar = new JsonArrayRequest(
                            Request.Method.POST,
                            Data.url + "removelike",
                            user,
                            new Response.Listener<JSONArray>() {
                                @Override
                                public void onResponse(JSONArray response) {
                                    img_like.setImageResource(R.mipmap.dislike);
                                    blog.liked = 0;
                                    numlikes.setText(String.valueOf(blog.getLikes().size()) + " - likes");
                                    Toast.makeText(context, "Ya no te gustó este blog", Toast.LENGTH_SHORT).show();
                                }
                            }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(context, "Algo salió mal", Toast.LENGTH_SHORT).show();
                        }
                    }
                    );
                    VolleyS.getinstance(context).getRq().add(jar);
                }
                else
                {
                    JSONObject user = new JSONObject();
                    try {
                        user.put("user_api", Data.getapi_token());
                        user.put("post_id", blog.getId());
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    JsonObjectRequest jar = new JsonObjectRequest(
                            Request.Method.POST,
                            Data.url + "givelike",
                            user,
                            new Response.Listener<JSONObject>() {
                                @Override
                                public void onResponse(JSONObject response) {
                                    img_like.setImageResource(R.mipmap.like);
                                    blog.liked = 1;
                                    numlikes.setText(String.valueOf(blog.getLikes().size() + 1) + " - likes");
                                    Toast.makeText(context, "Te gustó este blog", Toast.LENGTH_SHORT).show();
                                }
                            }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(context, "Algo salió mal", Toast.LENGTH_SHORT).show();
                        }
                    });
                    VolleyS.getinstance(context).getRq().add(jar);
                }
            }
        };
    }
}