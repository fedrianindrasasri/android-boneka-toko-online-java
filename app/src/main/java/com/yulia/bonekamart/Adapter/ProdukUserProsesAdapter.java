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
import com.yulia.bonekamart.Model.DataOrder;
import com.yulia.bonekamart.Model.DataProduk;
import com.yulia.bonekamart.R;

import java.util.List;

public class ProdukUserProsesAdapter extends RecyclerView.Adapter<ProdukUserProsesAdapter.ListHolder> {
    private Context context;
    private List<DataOrder> list;
    DatabaseHelper db;

    public ProdukUserProsesAdapter(Context context, List<DataOrder> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public ListHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_order_user, parent, false);
        return new ListHolder(v);
    }

    @Override
    public void onBindViewHolder(ListHolder holder, int position) {
        final DataOrder model = list.get(position);
        holder.namaProduk.setText(model.getNama_order());
        holder.hargaProduk.setText(model.getHarga_order());
        holder.deskripsiProduk.setText(model.getDeskripsi_order());
        holder.statusOrder.setText(model.getStatus());

        db = new DatabaseHelper(context);
    }

    public class ListHolder extends RecyclerView.ViewHolder {
        public TextView namaProduk, hargaProduk, deskripsiProduk, statusOrder;
        public Button selesai, batal;

        public ListHolder(View itemView) {
            super(itemView);
            namaProduk = (TextView) itemView.findViewById(R.id.txt_namaProduk);
            hargaProduk = (TextView) itemView.findViewById(R.id.txt_hargaProduk);
            deskripsiProduk = (TextView) itemView.findViewById(R.id.txt_deksripsiProduk);
            statusOrder = (TextView) itemView.findViewById(R.id.txt_status);
//            selesai = (Button) itemView.findViewById(R.id.btn_selesai);
//            batal = (Button) itemView.findViewById(R.id.btn_batal);
        }
    }
}
