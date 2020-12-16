package com.yulia.bonekamart;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.yulia.bonekamart.Adapter.ProdukAdapter;
import com.yulia.bonekamart.Helper.DatabaseHelper;


import java.util.ArrayList;
import java.util.List;

public class ProdukAdminActivity extends AppCompatActivity {

    private DatabaseHelper db;
    private List<ProdukAdapter> listProduk = new ArrayList<>();
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private ProdukAdapter produkAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_produk_admin);

        // ---------- awal action bar --------------
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_baseline_keyboard_backspace_24);
        setSupportActionBar(toolbar);

        getSupportActionBar().setTitle(Html.fromHtml("<font color='#ED5D8F'>Produk</font>"));
        getSupportActionBar().setDisplayShowTitleEnabled(true);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        // --------------- akhir action bar ------------------

        //  ------------------------------------------------------
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ProdukAdminActivity.this, AddEditProdukAdminActivity.class);
                startActivity(intent);
                finish();
            }
        });
        //  ------------------------------------------------------

        //  ------------------------------------------------------

        db = new DatabaseHelper(this);
        recyclerView = (RecyclerView) findViewById(R.id.rv_dataProdukAdmin);

        produkAdapter = new ProdukAdapter(this, db.getAllProduk());

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(produkAdapter);
        recyclerView.setHasFixedSize(true);
        //  ------------------------------------------------------
    }
}