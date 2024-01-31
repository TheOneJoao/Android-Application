package com.outspin.app.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.outspin.app.database.DBHandler;
import com.outspin.app.databinding.ActivityLoginBinding;

public class LoginActivity extends AppCompatActivity {

    private ActivityLoginBinding loginBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loginBinding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(loginBinding.getRoot());

        DBHandler dbHandler = new DBHandler(LoginActivity.this);
        boolean success = dbHandler.checkUser();

        Toast.makeText(LoginActivity.this, "success " + success, Toast.LENGTH_SHORT).show();
    }
}