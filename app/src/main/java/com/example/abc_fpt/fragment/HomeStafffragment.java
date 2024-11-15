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


public class HomeStafffragment extends Fragment {



    Button btnSP, btnHD;

    ImageView ivLogo;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home_staff, container, false);

        //anh xa
        btnSP = view.findViewById(R.id.btnQlLoaiSach);
        btnHD = view.findViewById(R.id.btnThanhVien);
        ivLogo = view.findViewById(R.id.ivLogo);

        // load ảnh động lên
        Glide.with(this).load(R.mipmap.fashion).into(ivLogo);


        btnSP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = null;
                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.frame_container, new Productfragment()).commit();
            }
        });



        btnHD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = null;
                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.frame_container, new BillingFragment()).commit();
            }
        });



        return view;
    }
}
