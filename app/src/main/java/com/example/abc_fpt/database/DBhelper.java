package com.example.abc_fpt.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBhelper  extends SQLiteOpenHelper {
    public DBhelper(Context context) {
        super(context, "ABC_FPT", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String sql = "CREATE TABLE USER (id integer primary key autoincrement, username text, password text)";
        db.execSQL(sql);
        sql = "INSERT INTO USER VALUES (null, 'admin', 'admin')";
        db.execSQL(sql);

        sql = "CREATE TABLE PRODUCTS (id integer primary key autoincrement, name text, price integer, image text)";
        db.execSQL(sql);
        sql = "INSERT INTO PRODUCTS VALUES (null, 'Nón màu đỏ', 20000, 'https://bizweb.dktcdn.net/100/346/025/products/mu-luoi-trai-cap-doi-nam-nu-unisex-mau-do-1.jpg?v=1610441922437')";
        db.execSQL(sql);
        sql = "INSERT INTO PRODUCTS VALUES (null, 'Nón Đen ', 25000, 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQ7WZcz3NXkcAVqnn_Z0mxHzHBpXsGLLG5YaQ&s')";
        db.execSQL(sql);
        sql = "INSERT INTO PRODUCTS VALUES (null, 'Nón SIMON C', 27000, 'data:image/jpeg;base64,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')";
        db.execSQL(sql);
        sql = "INSERT INTO PRODUCTS VALUES (null, 'Nón SISCOVERY ', 28000, 'https://nonson.vn/vnt_upload/product/NON_KET/MC024/DN1/thumbs/600_crop_nonson_2_1.png')";
        db.execSQL(sql);
        sql = "INSERT INTO PRODUCTS VALUES (null, 'Nón ORI ', 35000, 'https://gocphukien.com/wp-content/uploads/2022/12/Shop-ban-non-luoi-trai-o-TP-Ho-Chi-Minh.jpg')";
        db.execSQL(sql);
        sql = "INSERT INTO PRODUCTS VALUES (null, 'Nón FOR ', 22000, 'https://zerdio.com.vn/wp-content/uploads/2020/07/non-ket-nam-den-2.jpg')";
        db.execSQL(sql);

        sql = "CREATE TABLE CHITIET (id integer primary key autoincrement,   image text, name text, price integer,mota text)";
        db.execSQL(sql);


        sql = "CREATE TABLE Khachhang (id integer primary key autoincrement, name text, phone text, address text, image text)";
        db.execSQL(sql);
        sql = "INSERT INTO Khachhang VALUES (null, 'Pham xuân A', '0123456789', 'Đà Lạt', 'https://media.doisongphapluat.com/500/2016/12/13/trai%20dep%20nuoc%20anh%20dspl1.jpg')";
        db.execSQL(sql);
        sql = "INSERT INTO Khachhang VALUES (null, 'Pham xuân  B', '0123456789', 'TP.HCM', 'https://static2.yan.vn/YanNews/2167221/201904/sau-3-nam-cau-be-dep-trai-nhat-the-gioi-gio-ra-sao-7bad161f.jpg')";
        db.execSQL(sql);
        sql = "INSERT INTO Khachhang VALUES (null, 'Pham xuân c C', '0123456789', 'Lâm Đồng', 'https://i.pinimg.com/originals/8d/a5/c3/8da5c3a06407303694d6381b23368f02.png')";
        db.execSQL(sql);

        sql = "CREATE TABLE BILL (id integer primary key autoincrement, idtable text, idhr text, time text, total integer)";
        db.execSQL(sql);
        sql = "INSERT INTO BILL VALUES (null, 'NÓN Màu dỏ', 'Pham xuan an', '1', 35000)";
        db.execSQL(sql);
        sql = "INSERT INTO BILL VALUES (null, 'NÓN SIMON C', ' truong vu', '1', 35000)";
        db.execSQL(sql);
        sql = "INSERT INTO BILL VALUES (null, 'NÓN LOGO Chữ C', 'Phan hoang long', '1', 35000)";
        db.execSQL(sql);

        sql = "CREATE TABLE giohang(" +
                "idSanPham integer PRIMARY KEY, " +
                "tenSanPham text," +
                "moTa text," +
                "giaSanPham text," +
                "loaiSanPham text," +
                "image integer," +
                "soLuong integer)";
        db.execSQL(sql);




    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS USER");
        db.execSQL("DROP TABLE IF EXISTS PRODUCTS");
        db.execSQL("DROP TABLE IF EXISTS giohang");
        db.execSQL("DROP TABLE IF EXISTS Khachhang");

    }
}
