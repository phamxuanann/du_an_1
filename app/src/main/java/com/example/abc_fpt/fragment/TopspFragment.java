package com.example.abc_fpt.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.abc_fpt.R;
import com.example.abc_fpt.adapter.TopHOT;
import com.example.abc_fpt.dao.ThongKeDAO;
import com.example.abc_fpt.modal.Product;

import java.util.ArrayList;

public class TopspFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sanphamhot, container, false);

        RecyclerView recyclerViewTop10 = view.findViewById(R.id.recyclertophot);

        ThongKeDAO thongKeDAO = new ThongKeDAO(getContext());
        ArrayList<Product> list = thongKeDAO.getTopHot();

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerViewTop10.setLayoutManager(linearLayoutManager);
        TopHOT adapter = new TopHOT(getContext(), list);
        recyclerViewTop10.setAdapter(adapter);

        return view;
    }
}
