package com.example.abc_fpt.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.abc_fpt.database.DBhelper;
import com.example.abc_fpt.modal.Bill;


import java.util.ArrayList;

public class BillDAO {
    static DBhelper helper;
    static SQLiteDatabase db;

    public BillDAO(Context context) {
        helper = new DBhelper(context);
    }

    public static ArrayList<Bill> getAll() {
        ArrayList<Bill> list = new ArrayList<>();
        db = helper.getReadableDatabase();
        String sql = "SELECT * FROM BILL";
        Cursor cs = db.rawQuery(sql, null);
        cs.moveToFirst();
        while (!cs.isAfterLast()) {
            int id = cs.getInt(0);
            String idtable = cs.getString(1);
            String idhr = cs.getString(2);
            String time = cs.getString(3);
            int total = cs.getInt(4);
            Bill bill = new Bill(id, idtable, idhr, time, total);
            list.add(bill);
            cs.moveToNext();
        }
        return list;
    }


    public static boolean insert(Bill item) {
        db = helper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("idtable", item.getIdtable());
        values.put("idhr", item.getIdhr());
        values.put("time", item.getTime());
        values.put("total", item.getTotal());
        int row = (int) db.insert("BILL", null, values);
        return (row != -1);
    }

    public static boolean update(Bill item) {
        db = helper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("idtable", item.getIdtable());
        values.put("idhr", item.getIdhr());
        values.put("time", item.getTime());
        values.put("total", item.getTotal());
        int row = db.update("BILL", values, "id=?", new String[]{String.valueOf(item.getId())});
        db.close();
        return (row > 0);
    }

    public static boolean delete(int id) {
        db = helper.getWritableDatabase();
        int row = db.delete("BILL", "id=?", new String[]{String.valueOf(id)});
        db.close();
        return (row > 0);
    }

    public static boolean deleteAll() {
        db = helper.getWritableDatabase();
        int row = db.delete("BILL", null, null);
        db.close();
        return (row > 0);
    }

    public static ArrayList<Bill> search(String name) {
        ArrayList<Bill> list = new ArrayList<>();
        db = helper.getReadableDatabase();
        String sql = "SELECT * FROM BILL WHERE idtable LIKE '%" + name + "%'";
        Cursor cs = db.rawQuery(sql, null);
        cs.moveToFirst();
        while (!cs.isAfterLast()) {
            int id = cs.getInt(0);
            String idtable = cs.getString(1);
            String idhr = cs.getString(2);
            String time = cs.getString(3);
            int total = cs.getInt(4);
            Bill bill = new Bill(id, idtable, idhr, time, total);
            list.add(bill);
            cs.moveToNext();
        }
        return list;
    }

    public static int getSum() {
        int sum = 0;
        db = helper.getReadableDatabase();
        String sql = "SELECT SUM(total) FROM BILL";
        Cursor cs = db.rawQuery(sql, null);
        cs.moveToFirst();
        while (!cs.isAfterLast()) {
            sum = cs.getInt(0);
            cs.moveToNext();
        }
        return sum;
    }

    public static int countBill() {
        int count = 0;
        db = helper.getReadableDatabase();
        String sql = "SELECT COUNT(*) FROM BILL";
        Cursor cs = db.rawQuery(sql, null);
        cs.moveToFirst();
        while (!cs.isAfterLast()) {
            count = cs.getInt(0);
            cs.moveToNext();
        }
        return count;
    }
}
