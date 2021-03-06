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
import com.bumptech.glide.request.RequestOptions;
import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class User_Profile_Activity extends AppCompatActivity {

    private User_Profile user;
    private Gson gson;
    private TextView user_name;
    private TextView user_username;
    private TextView following;
    private TextView followers;
    private TextView about_me;
    private ImageView user_image;
    private ImageView background_image;
    private RequestQueue queue;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user__profile);

        gson = new Gson();
        queue = Volley.newRequestQueue(this);

        user_name = findViewById(R.id.user_name);
        user_username = findViewById(R.id.user_username);
        following = findViewById(R.id.following);
        followers = findViewById(R.id.followers);
        about_me = findViewById(R.id.about_me);
        user_image = findViewById(R.id.user_image);
        background_image = findViewById(R.id.background_image);


        try {
            InputStream stream = getAssets().open("user_info.json");
            InputStreamReader reader = new InputStreamReader(stream);
            user = gson.fromJson(reader, User_Profile.class);
        } catch (IOException exception) {
            Toast.makeText(this,"NOT WORKING",Toast.LENGTH_SHORT).show();
        }

        Glide.with(this)
                .load("file:///android_asset/background_image.jpg")
                .into(background_image);
        Glide.with(this)
                .load("file:///android_asset/user_image.jpg")
                .apply(RequestOptions.circleCropTransform())
                .into(user_image);

        updateUser();
    }

    private void updateUser() {
        user_name.setText(user.getUserName());
        user_username.setText(user.getUserUsername());
        following.setText(user.getFollower());
        followers.setText(user.getFollowingNumber());
        about_me.setText(user.getAboutMe());
    }
}

