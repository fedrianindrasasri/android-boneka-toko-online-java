package com.yulia.bonekamart.Model;

public class DataOrder {
    public int id;
    public String nama_order, harga_order, deskripsi_order, status;

    public DataOrder(int id, String nama_order, String harga_order, String deskripsi_order, String status) {
        this.id =  id;
        this.nama_order = nama_order;
        this.harga_order = harga_order;
        this.deskripsi_order = deskripsi_order;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNama_order() {
        return nama_order;
    }

    public void setNama_order(String nama_order) {
        this.nama_order = nama_order;
    }

    public String getHarga_order() {
        return harga_order;
    }

    public void setHarga_order(String harga_order) {
        this.harga_order = harga_order;
    }

    public String getDeskripsi_order() {
        return deskripsi_order;
    }

    public void setDeskripsi_order(String deskripsi_order) {
        this.deskripsi_order = deskripsi_order;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
