package com.example.abc_fpt.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.viewpager2.widget.ViewPager2;

import com.example.abc_fpt.R;
import com.example.abc_fpt.adapter.Onboadingadapter;
import com.example.abc_fpt.modal.Onboadingitem;

import java.util.ArrayList;
import java.util.List;

public class Onboading extends AppCompatActivity {
    Onboadingadapter adapter;
    ViewPager2 vp2_Onboading;
    LinearLayout layout_OnboardingIndicator;
    Button btn_GoToSignUp, btn_GoToLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onboading);

        vp2_Onboading = findViewById(R.id.vp2_Onboarding);
        layout_OnboardingIndicator = findViewById(R.id.layout_OnboardingIndicator);
        btn_GoToSignUp = findViewById(R.id.btn_GoToSignUp);
        btn_GoToLogin = findViewById(R.id.btn_GoToLogin);

        setupOnboadingItems();
        vp2_Onboading.setAdapter(adapter);

        setupOnboadingIndicator();
        setCurrentOnboadingIndicator(0);

        vp2_Onboading.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                setCurrentOnboadingIndicator(position);
            }
        });

        btn_GoToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Onboading.this, ActivityLogin.class);
                startActivity(intent);
            }
        });

        btn_GoToSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Onboading.this, SignUp.class);
                startActivity(intent);
            }
        });
    }

    private void setupOnboadingItems() {
        List<Onboadingitem> list = new ArrayList<>();

        Onboadingitem item1 = new Onboadingitem();
        item1.setImage(R.drawable.img);
        item1.setTitle("ABC FPT SHOP");
        item1.setDescription("Xin chào, rất vui được gặp bạn.");

        Onboadingitem item2 = new Onboadingitem();
        item2.setImage(R.drawable.img_2);
        item2.setTitle("ABC FPT SHOP");
        item2.setDescription(" Ở đây có rất nhiều loại máy tính");

        Onboadingitem item3 = new Onboadingitem();
        item3.setImage(R.drawable.img_2);
        item3.setTitle("Cảm ơn");
        item3.setDescription("vì bạn đã chọn ABC FPT SHOP ");

        list.add(item1);
        list.add(item2);
        list.add(item3);

        adapter = new Onboadingadapter(list);
    }

    private void setupOnboadingIndicator() {
        ImageView[] indicator = new ImageView[adapter.getItemCount()];
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT
        );
        layoutParams.setMargins(8, 0, 8, 0);
        for (int i = 0; i < indicator.length; i++) {
            indicator[i] = new ImageView(getApplicationContext());
            indicator[i].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.onboading_indicator_inactive));
            indicator[i].setLayoutParams(layoutParams);
            layout_OnboardingIndicator.addView(indicator[i]);
        }
    }

    private void setCurrentOnboadingIndicator(int index) {
        int childCount = layout_OnboardingIndicator.getChildCount();
        for (int i = 0; i < childCount; i++) {
            ImageView imageView = (ImageView) layout_OnboardingIndicator.getChildAt(i);
            if (i == index) {
                imageView.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.onboading_indicator_active));
            } else {
                imageView.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.onboading_indicator_inactive));
            }
        }
    }
}
