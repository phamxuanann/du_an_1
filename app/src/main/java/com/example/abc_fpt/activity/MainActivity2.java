package com.example.abc_fpt.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.abc_fpt.R;
import com.example.abc_fpt.fragment.GioHangActivity;
import com.example.abc_fpt.fragment.HomeStafffragment;
import com.example.abc_fpt.fragment.Productfframent2;
import com.google.android.material.navigation.NavigationView;

public class MainActivity2 extends AppCompatActivity {
    Toolbar toolbarStaff;
    ImageView img_ToolbarStaff;
    TextView tv_ToolbarStaff;
    FrameLayout frame_containerStaff;
    NavigationView nav_viewStaff;

    Productfframent2 productFragment2;
    GioHangActivity gioHangActivity;

    HomeStafffragment homeStaffFragment;
    DrawerLayout drawer_layoutStaff;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_2);

        toolbarStaff = findViewById(R.id.toolbarStaff);
        img_ToolbarStaff = findViewById(R.id.img_ToolbarStaff);
        tv_ToolbarStaff = findViewById(R.id.tv_ToolbarStaff);
        frame_containerStaff = findViewById(R.id.frame_containerStaff);
        nav_viewStaff = findViewById(R.id.nav_viewStaff);
        drawer_layoutStaff = findViewById(R.id.drawer_layoutStaff);

        gioHangActivity = new GioHangActivity();
        productFragment2 = new Productfframent2();
        homeStaffFragment = new HomeStafffragment();

        getSupportFragmentManager().beginTransaction().replace(R.id.frame_containerStaff, productFragment2).commit();
        setSupportActionBar(toolbarStaff);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("");

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer_layoutStaff, toolbarStaff, R.string.open_drawer, R.string.close_drawer);
        toggle.setDrawerIndicatorEnabled(true);
        toggle.syncState();
        drawer_layoutStaff.addDrawerListener(toggle);

        nav_viewStaff.setNavigationItemSelectedListener(item -> {
            int id = item.getItemId();

            if (id == R.id.nav_HomeStaff) {
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.frame_containerStaff, homeStaffFragment)
                        .commit();
                img_ToolbarStaff.setImageResource(R.drawable.ico_home);
                tv_ToolbarStaff.setText("Trang chủ");
            } else if (id == R.id.nav_qlspStaff) {
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.frame_containerStaff, productFragment2)
                        .commit();
                img_ToolbarStaff.setImageResource(R.drawable.ico_qlsp);
                tv_ToolbarStaff.setText("Sản phẩm");
            } else if (id == R.id.nav_qlbanStaff) {
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.frame_containerStaff, gioHangActivity)
                        .commit();
                img_ToolbarStaff.setImageResource(R.drawable.ico_qlsp);
                tv_ToolbarStaff.setText("Giỏ Hàng");
            } else if (id == R.id.nav_changePassStaff) {
                Intent intent = new Intent(MainActivity2.this, ChagePasword.class);
                startActivity(intent);
            } else if (id == R.id.nav_LogoutStaff) {
                finish();
            }

            drawer_layoutStaff.closeDrawers();
            return true;
        });



    }
}

