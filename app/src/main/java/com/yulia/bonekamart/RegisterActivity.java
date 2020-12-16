package com.yulia.bonekamart;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.yulia.bonekamart.Helper.DatabaseHelper;


public class RegisterActivity extends AppCompatActivity {

    EditText username, password, email;
    Button kembali, register;
    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        kembali = (Button) findViewById(R.id.btn_kembali);
        kembali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                kembali = (Button) findViewById(R.id.btn_kembali);
                startActivity(intent);
                finish();
            }
        });


        db = new DatabaseHelper(this);
        initViews();

        register = (Button) findViewById(R.id.btn_register);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validate()) {
                    String UserName = username.getText().toString();
                    String Email = email.getText().toString();
                    String Password = password.getText().toString();
                    if (!db.isUsernameExist(UserName)) {
                        db.insertUserBaru(UserName, Email, Password);
                        Toast.makeText(getApplicationContext(), "User Berhasil Dibuat!!, Silahkan Login", Toast.LENGTH_SHORT).show();
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                finish();
                            }
                        }, 1000);
                    } else {
                        Toast.makeText(getApplicationContext(), "User Sudah ada, Coba Lagi", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }


    private void initViews() {
        username = (EditText) findViewById(R.id.txt_username);
        password = (EditText) findViewById(R.id.txt_password);
        email = (EditText) findViewById(R.id.txt_email);
        register = (Button) findViewById(R.id.btn_register);
    }

    public boolean validate() {
        boolean valid = false;

        //Get values from EditText fields
        String UserName = username.getText().toString();
        String Email = email.getText().toString();
        String Password = password.getText().toString();

        //Handling validation for UserName field
        if (UserName.isEmpty() || Email.isEmpty() || Password.isEmpty()) {
            valid = false;
            Toast.makeText(getApplicationContext(), "Data Masih Ada yang kosong", Toast.LENGTH_SHORT).show();
        }


        if (UserName.length() > 5 && Password.length() > 5) {
            valid = true;
        } else {
            valid = false;
            Toast.makeText(getApplicationContext(), "Username atau Password Kurang Dari 5", Toast.LENGTH_SHORT).show();
        }
        return valid;
    }
}