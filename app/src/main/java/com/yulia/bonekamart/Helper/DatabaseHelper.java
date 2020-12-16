package com.yulia.bonekamart.Helper;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.yulia.bonekamart.Model.DataOrder;
import com.yulia.bonekamart.Model.DataProduk;
import com.yulia.bonekamart.Model.DataUser;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 5;

    static final String DATABASE_NAME = "yulia";

    // ----------------------------- Table User---------------------------------------
    public static final String TABLE_USERS = "users";
    public static final String COLUMN_ID_USERS = "id";
    public static final String COLUMN_USERNAME = "username";
    public static final String COLUMN_EMAIL = "email";
    public static final String COLUMN_PASSWORD = "password";
    // ----------------------------- Query Table User---------------------------------------
    final String SQL_CREATE_USERS_TABLE = "CREATE TABLE " + TABLE_USERS + " (" +
            COLUMN_ID_USERS + " INTEGER PRIMARY KEY autoincrement, " +
            COLUMN_USERNAME + " TEXT NOT NULL, " +
            COLUMN_EMAIL + " TEXT NOT NULL, " +
            COLUMN_PASSWORD + " TEXT NOT NULL " +
            " )";

    // ----------------------------- Table Produk---------------------------------------
    public static final String TABLE_PRODUK = "produk";
    public static final String COLUMN_ID_PRODUK = "id_produk";
    public static final String COLUMN_NAMA_PRODUK = "nama_produk";
    public static final String COLUMN_HARGA_PRODUK = "harga_produk";
    public static final String COLUMN_DESKRIPSI = "deskripsi";
    // ----------------------------- Query Table Produk---------------------------------------
    final String SQL_CREATE_PRODUK_TABLE = "CREATE TABLE " + TABLE_PRODUK + " (" +
            COLUMN_ID_PRODUK + " INTEGER PRIMARY KEY autoincrement, " +
            COLUMN_NAMA_PRODUK + " TEXT NOT NULL, " +
            COLUMN_HARGA_PRODUK + " TEXT NOT NULL, " +
            COLUMN_DESKRIPSI + " TEXT NOT NULL " +
            " )";


    // ----------------------------- Table Order---------------------------------------
    public static final String TABLE_ORDER = "orders";
    public static final String COLUMN_ID_ORDER = "id_order";
    public static final String COLUMN_NAMA_ORDER = "nama_order";
    public static final String COLUMN_HARGA_ORDER = "harga_order";
    public static final String COLUMN_DESKRIPSI_ORDER = "deskripsi_order";
    public static final String COLUMN_STATUS_ORDER = "status_order";
    // ----------------------------- Query Table Order---------------------------------------
    final String SQL_CREATE_ORDER_TABLE = "CREATE TABLE " + TABLE_ORDER + " (" +
            COLUMN_ID_ORDER + " INTEGER PRIMARY KEY autoincrement, " +
            COLUMN_NAMA_ORDER + " TEXT NOT NULL, " +
            COLUMN_HARGA_ORDER + " TEXT NOT NULL, " +
            COLUMN_DESKRIPSI_ORDER + " TEXT NOT NULL, " +
            COLUMN_STATUS_ORDER + " TEXT NOT NULL " +
            " )";


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_USERS_TABLE);
        db.execSQL(SQL_CREATE_PRODUK_TABLE);
        db.execSQL(SQL_CREATE_ORDER_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PRODUK);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ORDER);
        onCreate(db);
    }

    // ----------------------------- Method-method untuk table users---------------------------------------
    // method untuk insert data username yang register
    public void insertUserBaru(String username, String email, String password) {
        SQLiteDatabase database = this.getWritableDatabase();
        String queryValues = "INSERT INTO " + TABLE_USERS + " (username, email, password) " +
                " VALUES ('" + username + "', '" + email + "', '" + password + "')";

        Log.d("sqlite insert ", "" + queryValues);
        database.execSQL(queryValues);
        database.close();
    }

    public DataUser Authenticate(DataUser user) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_USERS,// Selecting Table
                new String[]{COLUMN_ID_USERS, COLUMN_USERNAME, COLUMN_USERNAME, COLUMN_PASSWORD},
                COLUMN_USERNAME + "=?",
                new String[]{user.username}, // where clause
                null, null, null);

        if (cursor != null && cursor.moveToFirst() && cursor.getCount() > 0) {
            DataUser user1 = new DataUser(cursor.getString(0), cursor.getString(1), cursor.getString(2), cursor.getString(3));
            if (user.password.equalsIgnoreCase(user1.password)) {
                return user1;
            }
        }
        //jika user password tidak sama dan tidak ada data dengan username yang dimaksud return dibawah
        return null;
    }

    public boolean isUsernameExist(String user) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_USERS,// Selecting Table
                new String[]{COLUMN_ID_USERS, COLUMN_USERNAME, COLUMN_USERNAME, COLUMN_PASSWORD},
                COLUMN_USERNAME + "=?",
                new String[]{user}, // where clause
                null, null, null);

        if (cursor != null && cursor.moveToFirst() && cursor.getCount() > 0) {
            return true;
        }
        return false;
    }


    // ----------------------------- Method-method untuk table produk---------------------------------------
    public void insertDataProduk(String nama_produk, String harga_produk, String deskripsi) {
        SQLiteDatabase database = this.getWritableDatabase();
        String queryValues = "INSERT INTO " + TABLE_PRODUK + " (nama_produk, harga_produk, deskripsi) " +
                " VALUES ('" + nama_produk + "', '" + harga_produk + "', '" + deskripsi + "')";

        Log.d("sqlite insert produk ", "" + queryValues);
        database.execSQL(queryValues);
        database.close();
    }

    public void updateDataProduk(int id, String nama_produk, String harga_produk, String deskripsi__produk) {
        SQLiteDatabase database = this.getWritableDatabase();
        String updateData = "UPDATE " + TABLE_PRODUK + " SET " +
                COLUMN_NAMA_PRODUK + "= '" + nama_produk + "', " +
                COLUMN_HARGA_PRODUK + "= '" + harga_produk + "', " +
                COLUMN_DESKRIPSI + "= '" + deskripsi__produk + "'" +
                " WHERE " + COLUMN_ID_PRODUK + " = " + id;
        Log.d("sqlite update ", "" + updateData);
        database.execSQL(updateData);
        database.close();
    }

    public void deleteDataProduk(int id) {
        SQLiteDatabase database = this.getWritableDatabase();
        String deleteQuery = "DELETE FROM " + TABLE_PRODUK + " WHERE " + COLUMN_ID_PRODUK + "=" + "'" + id + "'";
        Log.d("sqlite delete ", "" + deleteQuery);
        database.execSQL(deleteQuery);
        database.close();
    }

    public DataProduk getDataProduk(int id) {
        DataProduk model = null;
        String selectData = "SELECT * FROM " + TABLE_PRODUK + " WHERE " + COLUMN_ID_PRODUK + "=" + "'" + id + "'";
        Cursor data = this.getWritableDatabase().rawQuery(selectData, null);
        if (data.moveToFirst()) {
            model = new DataProduk(Integer.parseInt(data.getString(data.getColumnIndex(COLUMN_ID_PRODUK))),
                    data.getString(data.getColumnIndex(COLUMN_NAMA_PRODUK)),
                    data.getString(data.getColumnIndex(COLUMN_HARGA_PRODUK)),
                    data.getString(data.getColumnIndex(COLUMN_DESKRIPSI)));
        }
        return model;
    }

    public List<DataProduk> getAllProduk() {
        SQLiteDatabase database = this.getWritableDatabase();
        List<DataProduk> model = new ArrayList<>();
        String selectData = "SELECT * FROM " + TABLE_PRODUK;
        Cursor data = this.getWritableDatabase().rawQuery(selectData, null);
        if (data.moveToFirst()) {
            do {
                model.add(new DataProduk(Integer.parseInt(data.getString(data.getColumnIndex(COLUMN_ID_PRODUK))),
                        data.getString(data.getColumnIndex(COLUMN_NAMA_PRODUK)),
                        data.getString(data.getColumnIndex(COLUMN_HARGA_PRODUK)),
                        data.getString(data.getColumnIndex(COLUMN_DESKRIPSI))));
            } while (data.moveToNext());
        }
        database.close();
        return model;
    }


    // ----------------------------- Method-method untuk table order---------------------------------------
    public void insertDataOrder(String nama_order, String harga_order, String deskripsi_order, String status_order) {
        SQLiteDatabase database = this.getWritableDatabase();
        String queryValues = "INSERT INTO " + TABLE_ORDER + " (nama_order, harga_order, deskripsi_order, status_order) " +
                " VALUES ('" + nama_order + "', '" + harga_order + "', '" + deskripsi_order + "', '" + status_order + "')";

        Log.d("sqlite insert order ", "" + queryValues);
        database.execSQL(queryValues);
        database.close();
    }

    public List<DataOrder> getAllOrder() {
        SQLiteDatabase database = this.getWritableDatabase();
        List<DataOrder> model = new ArrayList<>();
        String selectData = "SELECT * FROM " + TABLE_ORDER;
        Cursor data = this.getWritableDatabase().rawQuery(selectData, null);
        if (data.moveToFirst()) {
            do {
                model.add(new DataOrder(Integer.parseInt(data.getString(data.getColumnIndex(COLUMN_ID_ORDER))),
                        data.getString(data.getColumnIndex(COLUMN_NAMA_ORDER)),
                        data.getString(data.getColumnIndex(COLUMN_HARGA_ORDER)),
                        data.getString(data.getColumnIndex(COLUMN_DESKRIPSI_ORDER)),
                        data.getString(data.getColumnIndex(COLUMN_STATUS_ORDER))));
            } while (data.moveToNext());
        }
        database.close();
        return model;
    }
}
