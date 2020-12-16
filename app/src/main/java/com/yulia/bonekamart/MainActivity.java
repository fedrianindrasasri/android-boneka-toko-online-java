package com.yulia.bonekamart;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.yulia.bonekamart.Helper.DatabaseHelper;
import com.yulia.bonekamart.Model.DataUser;

public class MainActivity extends AppCompatActivity {

    Button login;
    TextView register;
    DatabaseHelper db;
    EditText username, password;
    private Session session;//global variable

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        session = new Session(MainActivity.this);

        register = (TextView) findViewById(R.id.text_register);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
                startActivity(intent);
//                finish();
            }
        });

        db = new DatabaseHelper(this);
        initViews();

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validate()) {
                    String UserName = username.getText().toString();
                    String Password = password.getText().toString();
                    DataUser currentUser = db.Authenticate(new DataUser(null, UserName, null, Password));
                    String Email;
                    if (currentUser != null) {
                        Email = currentUser.getEmail();
                        session.setemail(Email);
                        Log.d("email", "" + Email);
                    }
                    session.setusename(UserName);

                    if (currentUser != null) {
                        Toast.makeText(getApplicationContext(), "Berhasil Login", Toast.LENGTH_SHORT).show();


                        if (UserName.equals("admins")) {
                            Intent intent = new Intent(MainActivity.this, DashboardAdminActivity.class);
                            login = (Button) findViewById(R.id.buttonLogin);
                            startActivity(intent);
                            finish();
                        } else {
                            Intent intent = new Intent(MainActivity.this, DashboardActivity.class);
                            login = (Button) findViewById(R.id.buttonLogin);
                            startActivity(intent);
                            finish();
                        }

                    } else {
                        Toast.makeText(getApplicationContext(), "Gagal Login, Coba Lagi", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }


    //this method is used to connect XML views to its Objects
    private void initViews() {
        username = (EditText) findViewById(R.id.txt_username);
        password = (EditText) findViewById(R.id.txt_password);
        login = (Button) findViewById(R.id.buttonLogin);

    }

    public boolean validate() {
        boolean valid = false;

        String UserName = username.getText().toString();
        String Password = password.getText().toString();

        if (UserName.isEmpty() || Password.isEmpty()) {
            valid = false;
            Toast.makeText(getApplicationContext(), "Data Masih Ada yang kosong", Toast.LENGTH_SHORT).show();
        } else {
            valid = true;
        }
        return valid;
    }
}