package com.example.abc_fpt.activity;

import android.annotation.SuppressLint;
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

public class ChagePasword extends AppCompatActivity {
    ImageView img_BackFromChangePassword;
    EditText edt_ChangeUsername, edt_ChangePassword, edt_ChangeNewPassword;
    Button btn_ChangePassword;
    UserDAO dao;
    LinearLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);

        img_BackFromChangePassword = findViewById(R.id.img_BackFromChangePassword);
        edt_ChangeUsername = findViewById(R.id.edt_ChangeUsername);
        edt_ChangePassword = findViewById(R.id.edt_ChangePassword);
        edt_ChangeNewPassword = findViewById(R.id.edt_ChangeNewPassword);
        btn_ChangePassword = findViewById(R.id.btn_ChangePassword);
        layout = findViewById(R.id.layoutChangePassword);

        dao = new UserDAO(this);

        img_BackFromChangePassword.setOnClickListener(v -> finish());

        btn_ChangePassword.setOnClickListener(v -> {
            String username = edt_ChangeUsername.getText().toString();
            String password = edt_ChangePassword.getText().toString();
            String newPassword = edt_ChangeNewPassword.getText().toString();

            if (username.isEmpty() || password.isEmpty() || newPassword.isEmpty()) {
                errorSnkbar(layout, "Vui lòng điền vào tất cả các trường");
            } else if (newPassword.length() < 8) {
                errorSnkbar(layout, "Mật khẩu cần dài ít nhất 8 ký tự");
            } else if (!newPassword.matches(".*[a-z].*")) {
                errorSnkbar(layout, "Mật khẩu phải chứa ít nhất 1 chữ cái viết thường");
            } else if (!newPassword.matches(".*[0-9].*")) {
                errorSnkbar(layout, "Mật khẩu phải chứa ít nhất 1 số");
            } else if (!newPassword.matches(".*[!@#$%^&*()_+=|<>?{}\\[\\]~-].*")) {
                errorSnkbar(layout, "Mật khẩu phải chứa ít nhất 1 ký tự đặc biệt");
            } else {
                if (UserDAO.checkLogin(username, password)) {
                    UserDAO.update(username, newPassword);
                    successSnkbar(layout, "Đổi mật khẩu thành công");
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            finish();
                        }
                    }, 3000);
                } else {
                    errorSnkbar(layout, "Tên đăng nhập hoặc tài khoản của bạn không chính xác");
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
        tv_Error.setText(errorText);
        snackbarLayout.addView(view, 0);
        snackbar.show();
    }

    private void successSnkbar(LinearLayout layout, String successText) {
        final Snackbar snackbar = Snackbar.make(layout, "", Snackbar.LENGTH_SHORT);
        @SuppressLint("InflateParams") View view = getLayoutInflater().inflate(R.layout. success, null);
        snackbar.getView().setBackgroundColor(Color.TRANSPARENT);
        @SuppressLint("RestrictedApi") Snackbar.SnackbarLayout snackbarLayout = (Snackbar.SnackbarLayout) snackbar.getView();
        snackbarLayout.setPadding(0, 0, 0, 0);
        TextView tv_Success = view.findViewById(R.id.tv_Success);
        tv_Success.setText(successText);
        snackbarLayout.addView(view, 0);
        snackbar.show();
    }
}
