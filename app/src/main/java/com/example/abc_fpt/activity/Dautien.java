package com.example.abc_fpt.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.abc_fpt.R;

public class Dautien extends AppCompatActivity {

    ImageView ivLogo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        getWindow().setStatusBarColor(getResources().getColor(R.color.vang));
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dautien);

        ivLogo = findViewById(R.id.ivLogo);

        Glide.with(this).load(R.mipmap.calvini).into(ivLogo);
        Handler handler= new Handler();

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent= new Intent(Dautien.this, Onboading.class);
                startActivity(intent);
                finish();
            }
        },4000);
    }
}