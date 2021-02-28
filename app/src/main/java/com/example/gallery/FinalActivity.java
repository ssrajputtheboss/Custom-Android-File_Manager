package com.example.gallery;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

public class FinalActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full);

        Intent intent= getIntent();
            String title = intent.getStringExtra("title");
            String path = intent.getStringExtra("image_url");
            Toast.makeText(getApplicationContext(),title,Toast.LENGTH_SHORT).show();
            ImageView image = (ImageView) findViewById(R.id.full_image);
            Glide.with(this).asBitmap().load(path).into(image);


    }
}
