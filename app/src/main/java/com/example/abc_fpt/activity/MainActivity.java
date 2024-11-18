package com.example.abc_fpt.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.abc_fpt.R;

import com.example.abc_fpt.fragment.BillingFragment;
import com.example.abc_fpt.fragment.Homefragment;
import com.example.abc_fpt.fragment.KHFragment;
import com.example.abc_fpt.fragment.Productfragment;
import com.example.abc_fpt.fragment.ThongKeFragment;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {
    Toolbar toolbar;
    NavigationView nav_view;
    DrawerLayout drawer_layout;
    ImageView img_Toolbar;
    TextView tv_Toolbar;
    Homefragment homeFragment;
    KHFragment khfragment;
    BillingFragment billingFragment;

    Productfragment productFragment;
    ThongKeFragment thongKeFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbar);
        nav_view = findViewById(R.id.nav_view);
        drawer_layout = findViewById(R.id.drawer_layout);
        img_Toolbar = findViewById(R.id.img_Toolbar);
        tv_Toolbar = findViewById(R.id.tv_Toolbar);

        homeFragment = new Homefragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_container, homeFragment).commit();

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("");

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer_layout, toolbar, R.string.open_drawer, R.string.close_drawer);
        toggle.setDrawerIndicatorEnabled(true);
        toggle.syncState();
        drawer_layout.addDrawerListener(toggle);

        nav_view.setNavigationItemSelectedListener(item -> {
            if (item.getItemId() == R.id.nav_Home) {
                homeFragment = new Homefragment();
                getSupportFragmentManager().beginTransaction().replace(R.id.frame_container, homeFragment).commit();
                drawer_layout.closeDrawers();
                img_Toolbar.setImageResource(R.drawable.ico_home);
                tv_Toolbar.setText("Trang chủ");
            } else if (item.getItemId() == R.id.nav_qlsp) {
                productFragment = new Productfragment();
                getSupportFragmentManager().beginTransaction().replace(R.id.frame_container, productFragment).commit();
                drawer_layout.closeDrawers();
                img_Toolbar.setImageResource(R.drawable.ico_qlsp);
                tv_Toolbar.setText("Quản lý sản phẩm");
            } else if (item.getItemId() == R.id.nav_qlkh) {
                khfragment = new KHFragment();
                getSupportFragmentManager().beginTransaction().replace(R.id.frame_container, khfragment).commit();
                drawer_layout.closeDrawers();
                img_Toolbar.setImageResource(R.drawable.ico_qlnv);
                tv_Toolbar.setText("Quản lí khách hàng");
            } else if (item.getItemId() == R.id.nav_thongke) {
                thongKeFragment = new ThongKeFragment();
                getSupportFragmentManager().beginTransaction().replace(R.id.frame_container, thongKeFragment).commit();
                drawer_layout.closeDrawers();
                img_Toolbar.setImageResource(R.drawable.ico_qlnv);
                tv_Toolbar.setText("Thống kê doanh thu");
            } else if (item.getItemId() == R.id.nav_qlhd) {
                billingFragment = new BillingFragment();
                getSupportFragmentManager().beginTransaction().replace(R.id.frame_container, billingFragment).commit();
                drawer_layout.closeDrawers();
                img_Toolbar.setImageResource(R.drawable.ico_qlhd);
                tv_Toolbar.setText("Hoá đơn");
            } else if (item.getItemId() == R.id.nav_changePass) {
                Intent intent = new Intent(MainActivity.this, ChagePasword.class);
                startActivity(intent);
            } else if (item.getItemId() == R.id.nav_Logout) {
                finish();
            }
            return true;
        });

    }
}