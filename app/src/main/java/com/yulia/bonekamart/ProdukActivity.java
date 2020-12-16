package com.yulia.bonekamart;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.Html;
import android.view.View;

import com.yulia.bonekamart.Adapter.ProdukAdapter;
import com.yulia.bonekamart.Adapter.ProdukUserBeliAdapter;
import com.yulia.bonekamart.Helper.DatabaseHelper;

import java.util.ArrayList;
import java.util.List;

public class ProdukActivity extends AppCompatActivity {

    private DatabaseHelper db;
    private List<ProdukUserBeliAdapter> listProduk = new ArrayList<>();
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private ProdukUserBeliAdapter produkAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_produk);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_baseline_keyboard_backspace_24);
        setSupportActionBar(toolbar);

        getSupportActionBar().setTitle(Html.fromHtml("<font color='#ED5D8F'>Produk Dijual</font>"));
        getSupportActionBar().setDisplayShowTitleEnabled(true);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        db = new DatabaseHelper(this);
        recyclerView = (RecyclerView) findViewById(R.id.rv_dataProduk);

        produkAdapter = new ProdukUserBeliAdapter(this, db.getAllProduk());

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(produkAdapter);
        recyclerView.setHasFixedSize(true);
    }
}