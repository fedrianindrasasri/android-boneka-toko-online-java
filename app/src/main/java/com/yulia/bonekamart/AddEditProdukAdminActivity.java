package com.yulia.bonekamart;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.yulia.bonekamart.Helper.DatabaseHelper;

public class AddEditProdukAdminActivity extends AppCompatActivity {

    String id, name, address;

    public EditText nama, harga, deskripsi;
    public Button tambah, kembali;
    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit_produk_admin);

        Bundle bundle = getIntent().getExtras();
        initViews();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_baseline_keyboard_backspace_24);
        setSupportActionBar(toolbar);
        TextView text = (TextView) findViewById(R.id.text_splash_2);
        if (bundle!= null){
            getSupportActionBar().setTitle(Html.fromHtml("<font color='#ED5D8F'>Edit Produk</font>"));
            text.setText("Edit Produk");
            nama.setText(bundle.getString("NAMA_PRODUK"));
            harga.setText(bundle.getString("HARGA_PRODUK"));
            deskripsi.setText(bundle.getString("DESKRIPSI_PRODUK"));
            Log.d("teks bundle", ""+bundle.getString("NAMA_PRODUK"));
        }else{
            getSupportActionBar().setTitle(Html.fromHtml("<font color='#ED5D8F'>Tambah Produk</font>"));
            text.setText("Tambah Produk");
        }

        getSupportActionBar().setDisplayShowTitleEnabled(true);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });



        kembali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AddEditProdukAdminActivity.this, ProdukAdminActivity.class);
                kembali = (Button) findViewById(R.id.btn_kembali);
                startActivity(intent);
                finish();
            }
        });



        db = new DatabaseHelper(this);
        tambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validate()) {
                    if(bundle != null){
                        int id = bundle.getInt("ID");
                        edit(id);
                    }else{
                        save();
                    }

                }
            }
        });

    }

    private void initViews() {
        nama = (EditText) findViewById(R.id.txt_namaProduk);
        harga = (EditText) findViewById(R.id.txt_hargaProduk);
        deskripsi = (EditText) findViewById(R.id.txt_deksripsiProduk);
        tambah = (Button) findViewById(R.id.btn_tambah);
        kembali = (Button) findViewById(R.id.btn_kembali);


    }

    public boolean validate() {
        boolean valid = false;

        //Get values from EditText fields
        String namaProduk = nama.getText().toString();
        String HargaProduk = harga.getText().toString();
        String DeskripsiProduk = deskripsi.getText().toString();

        //Handling validation for UserName field
        if (namaProduk.isEmpty() || HargaProduk.isEmpty() || DeskripsiProduk.isEmpty()) {
            valid = false;
            Toast.makeText(getApplicationContext(), "Data Masih Ada yang kosong", Toast.LENGTH_SHORT).show();
        } else {
            valid = true;
        }

        return valid;
    }

    private void blank() {
        nama.requestFocus();
        nama.setText(null);
        harga.setText(null);
        deskripsi.setText(null);
    }

    public void save(){
        db.insertDataProduk(nama.getText().toString(), harga.getText().toString(), deskripsi.getText().toString());
        Toast.makeText(getApplicationContext(), "Data Berhasil Masuk", Toast.LENGTH_SHORT).show();
        blank();
    }

    public void edit(int id){
        db.updateDataProduk(id, nama.getText().toString(), harga.getText().toString(), deskripsi.getText().toString());
        Toast.makeText(getApplicationContext(), "Data Berhasil Update", Toast.LENGTH_SHORT).show();
        blank();
        Intent intent = new Intent(this, ProdukAdminActivity.class);
        startActivity(intent);
        finish();
    }
}