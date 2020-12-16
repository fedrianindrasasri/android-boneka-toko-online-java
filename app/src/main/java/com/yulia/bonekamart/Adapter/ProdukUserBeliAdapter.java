package com.yulia.bonekamart.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.yulia.bonekamart.Helper.DatabaseHelper;
import com.yulia.bonekamart.Model.DataProduk;
import com.yulia.bonekamart.R;

import java.util.List;

public class ProdukUserBeliAdapter extends RecyclerView.Adapter<ProdukUserBeliAdapter.ListHolder> {
    private Context context;
    private List<DataProduk> list;
    DatabaseHelper db;

    public ProdukUserBeliAdapter(Context context, List<DataProduk> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public ListHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_produk, parent, false);
        return new ListHolder(v);
    }

    @Override
    public void onBindViewHolder(ListHolder holder, int position) {
        final DataProduk model = list.get(position);
        holder.namaProduk.setText(model.getNama_produk());
        holder.hargaProduk.setText(model.getHarga_produk());
        holder.deskripsiProduk.setText(model.getDeskripsi());

        db = new DatabaseHelper(context);

        holder.beli.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setMessage("Yakin Membeli Boneka?");
                builder.setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Log.d("sqlite beli", "" + model.getId());
                        db.insertDataOrder(model.getNama_produk(), model.getHarga_produk(), model.getDeskripsi(), "Order");
                        dialogInterface.dismiss();
                        Toast.makeText(context, "Bonea Berhasil Dibeli", Toast.LENGTH_SHORT).show();
                        ((Activity) context).finish();
                        context.startActivity(((Activity) context).getIntent());
                    }
                });
                builder.setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
                builder.show();
            }
        });
    }

    public class ListHolder extends RecyclerView.ViewHolder {
        public TextView namaProduk, hargaProduk, deskripsiProduk;
        public Button beli;

        public ListHolder(View itemView) {
            super(itemView);
            namaProduk = (TextView) itemView.findViewById(R.id.txt_namaProduk);
            hargaProduk = (TextView) itemView.findViewById(R.id.txt_hargaProduk);
            deskripsiProduk = (TextView) itemView.findViewById(R.id.txt_deksripsiProduk);
            beli = (Button) itemView.findViewById(R.id.btn_beli);
        }
    }
}
