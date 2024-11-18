package com.example.abc_fpt.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.abc_fpt.database.DBhelper;
import com.example.abc_fpt.modal.Product;


import java.util.ArrayList;

public class ThongKeDAO {
    DBhelper dbHelper;
    public ThongKeDAO(Context context){
        dbHelper = new DBhelper(context);

    }
    public ArrayList<Product> getTopHot(){
        ArrayList<Product> list = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT idtable AS product_name, COUNT(idtable) AS total_sales\n" +
                "FROM BILL\n" +
                "GROUP BY idtable\n" +
                "ORDER BY total_sales DESC\n" +
                "LIMIT 10",null);

        if(cursor.getCount() != 0){
            cursor.moveToFirst();
            do{
                list.add(new Product(cursor.getString(0), cursor.getInt(1)));
            }while (cursor.moveToNext());
        }
        return list;
    }

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
