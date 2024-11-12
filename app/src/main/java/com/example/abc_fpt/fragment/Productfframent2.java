package com.example.abc_fpt.fragment;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.abc_fpt.R;
import com.example.abc_fpt.adapter.Productapdapter;
import com.example.abc_fpt.dao.ProductaDao;
import com.example.abc_fpt.modal.Product;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class Productfframent2 extends Fragment {
    SearchView search_Product;
    RecyclerView rcv_Product;
    FloatingActionButton fab_AddNewProduct;
    ProductaDao dao;
    ArrayList<Product> list;
    LinearLayout layout;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dao = new ProductaDao(getContext());
        list = new ArrayList<>();
        list = dao.getAll();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.product, container, false);
        layout = view.findViewById(R.id.produc2);
        search_Product = view.findViewById(R.id.search_Product);
        rcv_Product = view.findViewById(R.id.rcv_Product);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        rcv_Product.setLayoutManager(layoutManager);
        Productapdapter adapter = new Productapdapter(getContext(), list);
        rcv_Product.setAdapter(adapter);

        search_Product.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                list.clear();
                list.addAll(ProductaDao.search(query));
                Productapdapter adapter = new Productapdapter(getContext(), list);
                rcv_Product.setAdapter(adapter);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                list.clear();
                list.addAll(ProductaDao.search(newText));
                Productapdapter adapter = new Productapdapter(getContext(), list);
                rcv_Product.setAdapter(adapter);
                return false;
            }
        });
        return view;
    }

    private void errorSnkbar(LinearLayout layout, String errorText) {
        final Snackbar snackbar = Snackbar.make(layout, "", Snackbar.LENGTH_SHORT);
        View view = getLayoutInflater().inflate(R.layout.eeror, null);
        snackbar.getView().setBackgroundColor(Color.TRANSPARENT);
        @SuppressLint("RestrictedApi") Snackbar.SnackbarLayout snackbarLayout = (Snackbar.SnackbarLayout) snackbar.getView();
        snackbarLayout.setPadding(0, 0, 0, 0);
        TextView tv_Error = view.findViewById(R.id.tv_Error);
        tv_Error.setText(errorText);
        snackbarLayout.addView(view, 0);
        snackbar.show();
    }

    private void successSnkbar(LinearLayout layout, String successText) {
        final Snackbar snackbar = Snackbar.make(layout, "", Snackbar.LENGTH_SHORT);
        View view = getLayoutInflater().inflate(R.layout.success, null);
        snackbar.getView().setBackgroundColor(Color.TRANSPARENT);
        @SuppressLint("RestrictedApi") Snackbar.SnackbarLayout snackbarLayout = (Snackbar.SnackbarLayout) snackbar.getView();
        snackbarLayout.setPadding(0, 0, 0, 0);
        TextView tv_Success = view.findViewById(R.id.tv_Success);
        tv_Success.setText(successText);
        snackbarLayout.addView(view, 0);
        snackbar.show();
    }
}