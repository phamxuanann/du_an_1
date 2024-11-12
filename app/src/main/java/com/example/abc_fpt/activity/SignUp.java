package com.example.abc_fpt.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.abc_fpt.R;
import com.example.abc_fpt.dao.UserDAO;
import com.google.android.material.snackbar.Snackbar;

public class SignUp extends AppCompatActivity {
    ImageView img_BackFromSignUp;
    EditText edt_SignUpUsername, edt_SignUpPassword, edt_SignUpConfirmPassword;
    Button btn_SignUp;
    LinearLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        layout = findViewById(R.id.layoutSignUp);
        img_BackFromSignUp = findViewById(R.id.img_BackFromSignUp);
        edt_SignUpUsername = findViewById(R.id.edt_SignUpUsername);
        edt_SignUpPassword = findViewById(R.id.edt_SignUpPassword);
        edt_SignUpConfirmPassword = findViewById(R.id.edt_SignUpConfirmPassword);
        btn_SignUp = findViewById(R.id.btn_SignUp);
        UserDAO dao = new UserDAO(this);

        img_BackFromSignUp.setOnClickListener(v -> finish());

        btn_SignUp.setOnClickListener(v -> {
            String username = edt_SignUpUsername.getText().toString();
            String password = edt_SignUpPassword.getText().toString();
            String repassword = edt_SignUpConfirmPassword.getText().toString();
            if (username.isEmpty() || password.isEmpty() || repassword.isEmpty()) {
                errorSnkbar(layout, "Vui lòng điền đầy đủ thông tin");
            } else if (password.length() < 8) {
                errorSnkbar(layout, "Mật khẩu phải có ít nhất 8 kí tự");
            } else if (!password.matches(".*[a-z].*")) {
                errorSnkbar(layout, "Mật khẩu phải chứa ít nhất 1 chứ cái viết thường");
            } else if (!password.matches(".*[0-9].*")) {
                errorSnkbar(layout, "Mật khẩu phải chứa ít nhất 1 số");
            } else if (!password.matches(".*[!@#$%^&*()_+=|<>?{}\\[\\]~-].*")) {
                errorSnkbar(layout, "Mật khẩu phải chứa ít nhất 1 kí tự đặc biệt");
            } else if (!password.equals(repassword)) {
                errorSnkbar(layout, "Mật khẩu phải trùng khớp với nhau");
            } else {
                if (com.example.abc_fpt.dao.UserDAO.checkUsername(username)) {
                    errorSnkbar(layout, "Tài khoản đã tồn tại");
                } else {
                    com.example.abc_fpt.dao.UserDAO.insert(username, password);
                    successSnkbar(layout);
                    new Handler().postDelayed(() -> {
                        startActivity(new Intent(SignUp.this, ActivityLogin.class));
                        finish();
                    }, 1000);
                }
            }
        });

    }
    private void errorSnkbar(LinearLayout layout, String errorText) {
        final Snackbar snackbar = Snackbar.make(layout, "", Snackbar.LENGTH_SHORT);
        @SuppressLint("InflateParams") View view = getLayoutInflater().inflate(R.layout.eeror, null);
        snackbar.getView().setBackgroundColor(Color.TRANSPARENT);
        @SuppressLint("RestrictedApi") Snackbar.SnackbarLayout snackbarLayout = (Snackbar.SnackbarLayout) snackbar.getView();
        snackbarLayout.setPadding(0, 0, 0, 0);
        TextView tv_Error = view.findViewById(R.id.tv_Error);
        tv_Error.setText("Đăng kí thất bại");
        snackbarLayout.addView(view, 0);
        snackbar.show();
    }

    @SuppressLint("SetTextI18n")
    private void successSnkbar(LinearLayout layout) {
        final Snackbar snackbar = Snackbar.make(layout, "", Snackbar.LENGTH_SHORT);
        @SuppressLint("InflateParams") View view = getLayoutInflater().inflate(R.layout.success, null);
        snackbar.getView().setBackgroundColor(Color.TRANSPARENT);
        @SuppressLint("RestrictedApi") Snackbar.SnackbarLayout snackbarLayout = (Snackbar.SnackbarLayout) snackbar.getView();
        snackbarLayout.setPadding(0, 0, 0, 0);
        TextView tv_Success = view.findViewById(R.id.tv_Success);
        tv_Success.setText("Đăng kí thành công");
        snackbarLayout.addView(view, 0);
        snackbar.show();
    }

}



