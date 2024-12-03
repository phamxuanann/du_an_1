package com.example.abc_fpt.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import androidx.appcompat.app.AppCompatActivity;

import com.example.abc_fpt.R;
import com.example.abc_fpt.database.GioHangDao;
import com.example.abc_fpt.modal.Chitietsanpham;
import com.example.abc_fpt.modal.GioHang;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;

public class ChitietProduct extends AppCompatActivity {
    TextView tensp, giasp, mota, tvSoLuong;
    Button btnthem;
    ImageView imghinhanh;
    Spinner spinner;
    Toolbar toolbar;
    GioHangDao gioHangDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chi_tiet_san_pham);
        initView();

        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        int price = intent.getIntExtra("price", 0);
        String image = intent.getStringExtra("image");

        DecimalFormat format = new DecimalFormat("###,###,###");

        tensp.setText(name);
        giasp.setText(format.format(price) + "đ");
        mota.setText("Mô tả chi tiết sản phẩm");
        if (image.startsWith("http://") || image.startsWith("https://")) {
            Picasso.get().load(image).into(imghinhanh);
        } else {
            int imgId = getResources().getIdentifier(image, "drawable", getPackageName());
            imghinhanh.setImageResource(imgId);
        }

        GioHangDao gioHangDao = new GioHangDao(this);

        btnthem.setOnClickListener(v -> {
            int quantity = 1;

            GioHang gioHang = gioHangDao.getGioHang(name.hashCode());
            if (gioHang != null) {
                // Nếu sản phẩm đã tồn tại +1
                gioHang.setSoLuong(quantity);
                gioHangDao.themSoluong(gioHang);
                Toast.makeText(this, "Sản phẩm đã được thêm vào giỏ hàng!", Toast.LENGTH_SHORT).show();
            } else {
                // Nếu sản phẩm chưa tồn tại, thêm mới
                GioHang newGioHang = new GioHang(
                        name.hashCode(),
                        name,
                        "Mô tả sản phẩm",
                        String.valueOf(price),
                        "Loại sản phẩm",
                        R.drawable.lazyimage,
                        quantity
                );
                if (gioHangDao.them(newGioHang)) {
                    Toast.makeText(this, "Sản phẩm đã được thêm vào giỏ hàng!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "Thêm sản phẩm thất bại!", Toast.LENGTH_SHORT).show();
                }
            }

        });


    }

    private void initView() {
        tensp = findViewById(R.id.tv_NameProduct);
        giasp = findViewById(R.id.tvgiatien);
        mota = findViewById(R.id.tvmota);
        btnthem = findViewById(R.id.btnthem);
        spinner = findViewById(R.id.spinner);
        imghinhanh = findViewById(R.id.img_Product);
        toolbar = findViewById(R.id.toolbar);
        tvSoLuong = findViewById(R.id.tvSoLuong);
    }
}