package com.example.abc_fpt.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.bumptech.glide.Glide;
import com.example.abc_fpt.R;

public class Homefragment extends Fragment {
    Button btnQlLoaiSP, btnQlHD, btnKH, btnThongKe;

    ImageView ivLogo;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        //anh xa
        btnQlLoaiSP = view.findViewById(R.id.btnQlLoaiSach);
        btnQlHD = view.findViewById(R.id.btnQlPhieuMuon);
        btnKH = view.findViewById(R.id.btnThanhVien);
        btnThongKe = view.findViewById(R.id.btnThongKe);
        ivLogo = view.findViewById(R.id.ivLogo);

        // load ảnh động lên
        Glide.with(this).load(R.mipmap.fashion).into(ivLogo);


        btnQlLoaiSP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = null;
                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.frame_container, new Productfragment()).commit();
            }
        });

        btnQlHD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = null;
                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.frame_container, new KHFragment()).commit();
            }
        });

        btnKH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = null;
                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.frame_container, new BillingFragment()).commit();
            }
        });

        btnThongKe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = null;
                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.frame_container, new Productfframent2()).commit();
            }
        });

        return view;
    }
}
