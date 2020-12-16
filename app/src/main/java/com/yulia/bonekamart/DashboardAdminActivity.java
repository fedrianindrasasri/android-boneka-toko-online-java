package com.yulia.bonekamart;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class DashboardAdminActivity extends AppCompatActivity {

    Button logout;
    CardView profil, pemesanan, produk, lokasi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard_admin);

        profil = (CardView) findViewById(R.id.card_profil);
        profil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DashboardAdminActivity.this, ProfileActivity.class);
                profil = (CardView) findViewById(R.id.card_profil);
                startActivity(intent);
            }
        });

        pemesanan = (CardView) findViewById(R.id.card_pesanan);
        pemesanan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DashboardAdminActivity.this, PemesananActivity.class);
                pemesanan = (CardView) findViewById(R.id.card_pesanan);
                startActivity(intent);
            }
        });

        produk = (CardView) findViewById(R.id.card_produk);
        produk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DashboardAdminActivity.this, ProdukAdminActivity.class);
                produk = (CardView) findViewById(R.id.card_produk);
                startActivity(intent);
            }
        });

        logout = (Button) findViewById(R.id.btn_logout);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DashboardAdminActivity.this, MainActivity.class);
                lokasi = (CardView) findViewById(R.id.card_lokasi);
                startActivity(intent);
                finish();
            }
        });

    }
}