package com.example.abc_fpt.fragment;

import android.app.Activity;
import android.app.AlertDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.abc_fpt.R;
import com.example.abc_fpt.adapter.GioHangAdapter;
import com.example.abc_fpt.adapter.ShowDialog;
import com.example.abc_fpt.database.GioHangDao;
import com.example.abc_fpt.modal.GioHang;
import com.google.android.material.textfield.TextInputEditText;

import java.text.NumberFormat;
import java.util.ArrayList;

public class GioHangActivity extends Fragment {
    private ArrayList<GioHang> list = new ArrayList<>();
    GioHangAdapter gioHangAdapter;
    private GioHangDao gioHangDao;
    private RecyclerView recyclerView;
    public static int sum = 0;
    public static Button pay;
    private ShowDialog showDialog;
    public static LinearLayout emty;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.activity_gio_hang, container, false);
        init(view);

        //Set tiêu đề

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        GioHangAdapter adapter = new GioHangAdapter(getContext(), list);
        recyclerView.setAdapter(adapter);

        // Khởi tạo DAO và lấy danh sách giỏ hàng
        gioHangDao = new GioHangDao(getContext());
        list = gioHangDao.getAll();

        // Truyền danh sách vào Adapter
        gioHangAdapter = new GioHangAdapter(getContext(), list);
        recyclerView.setAdapter(gioHangAdapter);



        //getAll list
        list.clear();
        list.addAll(gioHangDao.getAll());
//        Log.d("testLog", list.get(0).toString());
        gioHangAdapter.notifyDataSetChanged();
        if (list.isEmpty()) {
            pay.setVisibility(View.GONE);
            emty.setVisibility(View.VISIBLE);
        }


        pay.setOnClickListener(v -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
            View view1 = LayoutInflater.from(getContext()).inflate(R.layout.payment, null);

            builder.setView(view1);
            AlertDialog alertDialog = builder.create();

            // Set background và kích thước cho dialog
            if (alertDialog.getWindow() != null) {
                alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            }

            // Khai báo biến
            TextInputEditText name = view1.findViewById(R.id.edt_name);
            TextInputEditText address = view1.findViewById(R.id.edt_address);
            TextInputEditText sdt = view1.findViewById(R.id.edt_sdt);
            Button add = view1.findViewById(R.id.btnThem);
            Button cancel = view1.findViewById(R.id.btnHuy);

            // Xử lý khi ấn "Thêm"
            add.setOnClickListener(v1 -> {
                String nameText = name.getText().toString();
                String sdtText = sdt.getText().toString();
                String addressText = address.getText().toString();

                if (nameText.isEmpty() || sdtText.isEmpty() || addressText.isEmpty()) {
                    showDialog.show("Không được để trống!");
                } else if (sdtText.length() != 10) {
                    showDialog.show("Số điện thoại không chính xác");
                } else {
                    NumberFormat format = NumberFormat.getCurrencyInstance();
                    showDialog.show("Đã thanh toán " + format.format(sum) + " thành công!");
                    gioHangDao.delete();
                    list.clear();
                    gioHangAdapter.notifyDataSetChanged();
                    pay.setVisibility(View.GONE);
                    emty.setVisibility(View.VISIBLE);
                    alertDialog.dismiss();
                }
            });

            // Xử lý khi ấn "Hủy"
            cancel.setOnClickListener(v12 -> alertDialog.dismiss());

            alertDialog.show();
        });


        return view;
    }
    private void init(View view) {
        recyclerView = view.findViewById(R.id.rv_list);  // Sử dụng đối tượng view
        pay = view.findViewById(R.id.btn_pay);
        emty = view.findViewById(R.id.ln_emty);

        // Set layout manager và adapter
        LinearLayoutManager lyManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(lyManager);
        gioHangAdapter = new GioHangAdapter(getContext(), list);
        recyclerView.setAdapter(gioHangAdapter);

        // Khởi tạo DAO và ShowDialog
        gioHangDao = new GioHangDao(getContext());
        showDialog = new ShowDialog((Activity) getContext());

    }
}