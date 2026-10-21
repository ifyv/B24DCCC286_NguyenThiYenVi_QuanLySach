package vn.edu.example.quanlysach;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends Activity {
    private EditText edtUsername, edtPassword;
    private TextView txtError;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edtUsername = findViewById(R.id.edtUsername);
        edtPassword = findViewById(R.id.edtPassword);
        txtError = findViewById(R.id.txtError);
        Button btnLogin = findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener(v -> login());
    }

    private void login() {
        String username = edtUsername.getText().toString().trim();
        String password = edtPassword.getText().toString();

        if (TextUtils.isEmpty(username) || TextUtils.isEmpty(password)) {
            txtError.setText("Vui lòng nhập đầy đủ tên đăng nhập và mật khẩu.");
            txtError.setVisibility(View.VISIBLE);
            return;
        }

        if (username.equals("admin") && password.equals("123456")) {
            BookData.initSampleData();
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
            finish(); // Không quay lại màn hình đăng nhập bằng nút Back.
        } else {
            txtError.setText("Sai tên đăng nhập hoặc mật khẩu");
            txtError.setVisibility(View.VISIBLE);
        }
    }
}
