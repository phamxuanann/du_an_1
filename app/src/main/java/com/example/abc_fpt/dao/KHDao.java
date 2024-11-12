package com.example.abc_fpt.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.abc_fpt.database.DBhelper;
import com.example.abc_fpt.modal.Khachhang;

import java.util.ArrayList;

public class KHDao {
    static DBhelper helper;
    static SQLiteDatabase db;

    public KHDao(Context context) {helper = new DBhelper(context);}

    public static ArrayList<Khachhang> getAll() {
        ArrayList<Khachhang> list = new ArrayList<>();
        db = helper.getReadableDatabase();
        String sql = "SELECT * FROM Khachhang";
        Cursor cs = db.rawQuery(sql, null);
        cs.moveToFirst();
        while (!cs.isAfterLast()) {
            int id = cs.getInt(0);
            String name = cs.getString(1);
            String phone = cs.getString(2);
            String address = cs.getString(3);
            String image = cs.getString(4);
            Khachhang kh = new Khachhang(id,name,phone,address,image);
            list.add(kh);
            cs.moveToNext();
        }
        db.close();
        return list;
    }

    public static boolean insert(Khachhang item) {
        db = helper.getWritableDatabase();
        String sql = "INSERT INTO Khachhang VALUES(null,?,?,?,?)";
        db.execSQL(sql, new Object[]{item.getName(), item.getPhone(), item.getAddress(), item.getImage()});
        db.close();
        return true;
    }

    public static boolean update(Khachhang item) {
        db = helper.getWritableDatabase();
        String sql = "UPDATE Khachhang SET name=?, phone=?, address=?, image=? WHERE id=?";
        db.execSQL(sql, new Object[]{item.getName(), item.getPhone(), item.getAddress(), item.getImage(), item.getId()});
        db.close();
        return true;
    }

    public static boolean delete(int id) {
        db = helper.getWritableDatabase();
        String sql = "DELETE FROM Khachhang WHERE id=?";
        db.execSQL(sql, new Object[]{id});
        db.close();
        return true;
    }

    public static ArrayList<Khachhang> search(String name) {
        ArrayList<Khachhang> list = new ArrayList<>();
        db = helper.getReadableDatabase();
        String sql = "SELECT * FROM Khachhang WHERE name LIKE '%" + name + "%'";
        Cursor cs = db.rawQuery(sql, null);
        cs.moveToFirst();
        while (!cs.isAfterLast()) {
            int id = cs.getInt(0);
            String name1 = cs.getString(1);
            String phone = cs.getString(2);
            String address = cs.getString(3);
            String image = cs.getString(4);
            Khachhang hr = new Khachhang(id,name1,phone,address,image);
            list.add(hr);
            cs.moveToNext();
        }
        db.close();
        return list;
    }

    public static int countHR() {
        int count = 0;
        db = helper.getReadableDatabase();
        String sql = "SELECT COUNT(*) FROM Khachhang";
        Cursor cs = db.rawQuery(sql, null);
        cs.moveToFirst();
        while (!cs.isAfterLast()) {
            count = cs.getInt(0);
            cs.moveToNext();
        }
        db.close();
        return count;
    }
}

