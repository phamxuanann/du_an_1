package com.example.abc_fpt.fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.abc_fpt.R;
import com.example.abc_fpt.adapter.GioHangAdapter;
import com.example.abc_fpt.adapter.KHadapter;
import com.example.abc_fpt.adapter.ShowDialog;
import com.example.abc_fpt.database.GioHangDao;
import com.example.abc_fpt.modal.GioHang;
import com.example.abc_fpt.modal.Khachhang;
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
}


//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        // Inflate the layout for this fragment
//        View view = inflater.inflate(R.layout.activity_gio_hang, container, false);
//        init();
//        //Set tiêu đề
//
//        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
//        recyclerView.setLayoutManager(layoutManager);
//        GioHangAdapter adapter = new GioHangAdapter(getContext(), list);
//        recyclerView.setAdapter(adapter);
//
//
//        //getAll list
//        list.clear();
//        list.addAll(gioHangDao.getAll());
////        Log.d("testLog", list.get(0).toString());
//        gioHangAdapter.notifyDataSetChanged();
//        if (list.isEmpty()) {
//            pay.setVisibility(View.GONE);
//            emty.setVisibility(View.VISIBLE);
//        }
//
//        //Khi ấn thanh toán
//        pay.setOnClickListener(v -> {
//            AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
//            View view1 = LayoutInflater.from(getContext()).inflate(R.layout.payment, null);
//
//            dialog.setContentView(R.layout.payment);
//            dialog.setCancelable(true);
//            builder.setView(view1);
//            AlertDialog alertDialog = builder.create();
//            Window window = dialog.getWindow();
//            window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
//            if (dialog != null && dialog.getWindow() != null) {
//                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
//            }
//            //Khai báo biến
//            TextInputEditText name = dialog.findViewById(R.id.edt_name);
//            TextInputEditText address = dialog.findViewById(R.id.edt_address);
//            TextInputEditText sdt = dialog.findViewById(R.id.edt_sdt);
//            Button add = dialog.findViewById(R.id.btnThem);
//            Button cancle = dialog.findViewById(R.id.btnHuy);
//
//            //Khi ấn thnah toán
//            add.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    String nameText = name.getText().toString();
//                    String sdtText = sdt.getText().toString();
//                    String addressText = address.getText().toString();
//                    if (nameText.isEmpty() || sdtText.isEmpty() || addressText.isEmpty()) {
//                        showDialog.show("Không được để trống!");
//                    } else if (sdtText.length() != 10) {
//                        showDialog.show("Số điện thoại không chính xác");
//                    } else {
//                        NumberFormat format = NumberFormat.getCurrencyInstance();
//                        showDialog.show("Đã thanh toán " + format.format(sum) + " thành công!");
//                        gioHangDao.delete();
//                        list.clear();
//                        gioHangAdapter.notifyDataSetChanged();
//                        pay.setVisibility(View.GONE);
//                        emty.setVisibility(View.VISIBLE);
//                        dialog.dismiss();
//                    }
//                }
//            });
//
//            //Khi ấn nút hủy
//            cancle.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    dialog.dismiss();
//                }
//            });
//            dialog.show();
////        });
//        return view;
//    }
//
//
//    private void init() {
//        recyclerView = View.findViewById(R.id.rv_list);
//        pay =  View.findViewById(R.id.btn_pay);
//        emty =  View.findViewById(R.id.ln_emty);
//        LinearLayoutManager layoutManager
//                = new LinearLayoutManager(this);
//        recyclerView.setLayoutManager(layoutManager);
//        //Set list vào adapter
//        gioHangAdapter = new GioHangAdapter(this, list);
//        recyclerView.setAdapter(gioHangAdapter);
//        gioHangDao = new GioHangDao(this);
//        showDialog = new ShowDialog(this);
//    }
//}