package com.userprofile.davidls4.userprofile;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;

import com.google.gson.Gson;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class User_Profile_Activity{

    private User_Profile user;
    private Gson gson;
    private TextView user_name;
    private TextView user_username;
    private TextView following;
    private TextView followers;
    private TextView about_me;
    private ImageView user_image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);

        gson = new Gson();
        queue = Volley.newRequestQueue(this);

        user_name = findViewById(R.id.user_name);
        user_username = findViewById(R.id.user_username);
        following = findViewById(R.id.following);
        followers = findViewById(R.id.followers);
        about_me = findViewById(R.id.about_me);

        StringRequest req = new StringRequest(
                Request.Method.GET,
                "https://www.omdbapi.com/?apikey=80879df5&i=tt0317705", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                user = gson.fromJson(response, User_Profile.class);
                updateUser();
                Glide.with(User_Profile_Activity.this)
                        .load(user.getUserImage())
                        .into(user_image);
            }
        });

        queue.add(req);
    }

    private void updateUser() {
        user_name.setText(user.getUserName());
        user_username.setText(user.getUserUsername());
        following.setText(user.getFollower());
        followers.setText(user.getFollowingNumber());
        about_me.setText(user.getAboutMe());

    }
}
