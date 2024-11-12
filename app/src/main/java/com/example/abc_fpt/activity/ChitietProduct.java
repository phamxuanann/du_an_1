package com.example.abc_fpt.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toolbar;

import androidx.appcompat.app.AppCompatActivity;

import com.example.abc_fpt.R;
import com.example.abc_fpt.modal.Chitietsanpham;

import java.text.DecimalFormat;

public class ChitietProduct extends AppCompatActivity {
    TextView tensp,giasp,mota;
    Button btnthem;
    ImageView imghinhanh;
    Spinner spinner;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chi_tiet_san_pham);
        initView();
//        ActionTooBar();



    }

    private void initView() {
            tensp = findViewById(R.id.tv_NameProduct);
            giasp = findViewById(R.id.tvgiatien);
            mota = findViewById(R.id.tvmota);
            btnthem = findViewById(R.id.btnthem);
            spinner = findViewById(R.id.spinner);
            imghinhanh = findViewById(R.id.img_Product);
            toolbar = findViewById(R.id.toolbar);




    }
   

}
