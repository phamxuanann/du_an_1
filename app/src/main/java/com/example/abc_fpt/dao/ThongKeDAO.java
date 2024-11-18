package com.example.abc_fpt.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.abc_fpt.database.DBhelper;


import java.util.ArrayList;

public class ThongKeDAO {
    DBhelper dbHelper;
    public ThongKeDAO(Context context){
        dbHelper = new DBhelper(context);

    }
//    public ArrayList<Sach> getTop10(){
//        ArrayList<Sach> list = new ArrayList<>();
//        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
//        Cursor cursor = sqLiteDatabase.rawQuery("SELECT pm.masach, sc.tensach, count(pm.masach) FROM PHIEUMUON pm, SACH sc WHERE pm.masach = sc.masach GROUP BY pm.masach, sc.tensach ORDER BY COUNT(pm.masach) DESC LIMIT 10", null);
//        if(cursor.getCount() != 0){
//            cursor.moveToFirst();
//            do{
//                list.add(new Sach(cursor.getInt(0), cursor.getString(1), cursor.getInt(2)));
//            }while (cursor.moveToNext());
//        }
//        return list;
//    }

    public int getDoanhThu(String ngaybatdau, String ngayketthuc){
        ngaybatdau = ngaybatdau.replace("/","");
        ngayketthuc = ngayketthuc.replace("/","");
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
        Cursor cursor  = sqLiteDatabase.rawQuery("SELECT SUM(total) FROM BILL  WHERE substr(date,7)||substr(date,4,2)||substr(date,1,2) BETWEEN ? AND ?", new String[]{ngaybatdau, ngayketthuc});
        if(cursor.getCount() !=0){
            cursor.moveToFirst();
            return cursor.getInt(0);
        }
        return 0;
    }

}
