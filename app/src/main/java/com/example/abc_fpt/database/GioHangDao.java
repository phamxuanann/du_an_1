package com.example.abc_fpt.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.abc_fpt.fragment.GioHangActivity;
import com.example.abc_fpt.modal.GioHang;


import java.util.ArrayList;

public class GioHangDao {
    DBhelper dtb;
    SQLiteDatabase db;

    public GioHangDao(Context context) {
        dtb = new DBhelper(context);
        db = dtb.getWritableDatabase();
    }

    //Lấy toàn bộ list chấm bài
    public ArrayList<GioHang> getAll() {
        ArrayList<GioHang> list = new ArrayList<>();
        Cursor cs = db.rawQuery("SELECT * FROM giohang", null);
        cs.moveToFirst();
        while (!cs.isAfterLast()) {
            Integer idSanPham = cs.getInt(0);
            String tenSanPham = cs.getString(1);
            String moTa = cs.getString(2);
            String giaSanPham = cs.getString(3);
            String loaiSanPham = cs.getString(4);
            Integer image = cs.getInt(5);
            Integer soLuong = cs.getInt(6);
            GioHangActivity.sum += soLuong * Integer.parseInt(giaSanPham);
            GioHang gioHang = new GioHang(idSanPham, tenSanPham, moTa, giaSanPham, loaiSanPham, image, soLuong);
            list.add(gioHang);
            cs.moveToNext();
        }
        cs.close();
        return list;
    }

    public GioHang getGioHang(int id) {
        Log.d("GioHangDao", "idSanPham: " + id);
        Cursor cs = db.rawQuery("SELECT * FROM giohang WHERE idSanPham = ?", new String[]{String.valueOf(id)});
        if (cs.moveToFirst()) {
            Integer idSanPham = cs.getInt(0);
            String tenSanPham = cs.getString(1);
            String moTa = cs.getString(2);
            String giaSanPham = cs.getString(3);
            String loaiSanPham = cs.getString(4);
            Integer image = cs.getInt(5);
            Integer soLuong = cs.getInt(6);
            cs.close();
            return new GioHang(idSanPham, tenSanPham, moTa, giaSanPham, loaiSanPham, image, soLuong);
        }
        cs.close();
        return null;
    }

    //Thêm
    public boolean them(GioHang gioHang) {
        GioHang existingGioHang = getGioHang(gioHang.getIdSanPham());
        if (existingGioHang != null) {
            themSoluong(gioHang);
            return true;
        } else {
            ContentValues contentValues = new ContentValues();
            contentValues.put("idSanPham", gioHang.getIdSanPham());
            contentValues.put("tenSanPham", gioHang.getTenSanPham());
            contentValues.put("moTa", gioHang.getMoTa());
            contentValues.put("giaSanPham", gioHang.getGiaSanPham());
            contentValues.put("loaiSanPham", gioHang.getLoaiSanPham());
            contentValues.put("image", gioHang.getImage());
            contentValues.put("soLuong", gioHang.getSoLuong());
            long r = db.insert("giohang", null, contentValues);
            return r != -1;
        }
    }


    public void themSoluong(GioHang gioHang) {
        GioHang existingGioHang = getGioHang(gioHang.getIdSanPham());
        if (existingGioHang != null) {
            int newQuantity = existingGioHang.getSoLuong() + gioHang.getSoLuong();
            ContentValues contentValues = new ContentValues();
            contentValues.put("soLuong", newQuantity);
            db.update("giohang", contentValues, "idSanPham=?", new String[]{String.valueOf(gioHang.getIdSanPham())});
        }
    }



    //Xóa
    public boolean xoa(GioHang gioHang) {
        GioHangActivity.sum -= gioHang.getSoLuong() * Integer.parseInt(gioHang.getGiaSanPham());
        int r = db.delete("giohang", "idSanPham=?", new String[]{String.valueOf(gioHang.getIdSanPham())});
        if (r <= 0) {
            return false;
        }
        return true;
    }
    public void delete() {
        db.execSQL("delete from giohang");
        db.close();
    }
}
