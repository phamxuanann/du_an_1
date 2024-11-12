package com.example.abc_fpt.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.abc_fpt.R;
import com.example.abc_fpt.dao.UserDAO;
import com.google.android.material.snackbar.Snackbar;

public class ActivityLogin extends AppCompatActivity {
    ImageView img_BackFromLogin;
    EditText edt_Username, edt_Password;
    Button btn_Login;
    UserDAO dao;
    LinearLayout layout;
    CheckBox cb_LoginWithAdmin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        layout = findViewById(R.id.layoutLogin);
        img_BackFromLogin = findViewById(R.id.img_BackFromLogin);
        edt_Username = findViewById(R.id.edt_Username);
        edt_Password = findViewById(R.id.edt_Password);
        btn_Login = findViewById(R.id.btn_Login);
        cb_LoginWithAdmin = findViewById(R.id.cb_LoginWithAdmin);

        dao = new UserDAO(this);

        img_BackFromLogin.setOnClickListener(v -> finish());

        btn_Login.setOnClickListener(v -> {
            String username = edt_Username.getText().toString().trim();
            String password = edt_Password.getText().toString().trim();

            if (username.isEmpty() || password.isEmpty()) {
                errorSnkbar(layout, "Vui lòng nhập tên tài khoản và mật khẩu!");
            } else {
                if (cb_LoginWithAdmin.isChecked()) {
                    if (UserDAO.checkLogin(username, password)) {
                        successSnkbar(layout, "Đăng nhập thành công!");
                        new Handler().postDelayed(() -> {
                            startActivity(new Intent(ActivityLogin.this, MainActivity.class));
                            finish();
                        }, 1000);
                    } else {
                        errorSnkbar(layout, "Tên người dùng hoặc mật khẩu sai!");
                    }
                } else {
                    if (UserDAO.checkLogin(username, password)) {
                        successSnkbar(layout, "Đăng nhập thành công!");
                        new Handler().postDelayed(() -> {
                            startActivity(new Intent(ActivityLogin.this, MainActivity2.class));
                            finish();
                        }, 1000);
                    } else {
                        errorSnkbar(layout, "Tên người dùng hoặc mật khẩu sai!");
                    }
                }
            }
        });
    }

    private void errorSnkbar(LinearLayout layout, String errorText) {
        final Snackbar snackbar = Snackbar.make(layout, "", Snackbar.LENGTH_SHORT);
        View view = getLayoutInflater().inflate(R.layout.eeror, null);
        snackbar.getView().setBackgroundColor(Color.TRANSPARENT);
        @SuppressLint("RestrictedApi") Snackbar.SnackbarLayout snackbarLayout = (Snackbar.SnackbarLayout) snackbar.getView();
        snackbarLayout.setPadding(0, 0, 0, 0);
        TextView tv_Error = view.findViewById(R.id.tv_Error);
        tv_Error.setText(errorText);
        snackbarLayout.addView(view, 0);
        snackbar.show();
    }

    private void successSnkbar(LinearLayout layout, String successText) {
        final Snackbar snackbar = Snackbar.make(layout, "", Snackbar.LENGTH_SHORT);
        View view = getLayoutInflater().inflate(R.layout.success, null);
        snackbar.getView().setBackgroundColor(Color.TRANSPARENT);
        @SuppressLint("RestrictedApi") Snackbar.SnackbarLayout snackbarLayout = (Snackbar.SnackbarLayout) snackbar.getView();
        snackbarLayout.setPadding(0, 0, 0, 0);
        TextView tv_Success = view.findViewById(R.id.tv_Success);
        tv_Success.setText(successText);
        snackbarLayout.addView(view, 0);
        snackbar.show();
    }
}

