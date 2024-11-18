package com.example.abc_fpt.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;


import com.example.abc_fpt.R;
import com.example.abc_fpt.modal.Product;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class TopHOT extends RecyclerView.Adapter<TopHOT.ViewHolder>{

    private Context context;
    private ArrayList<Product> list;

    public TopHOT(Context context, ArrayList<Product> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_recyler_top, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.txtTensp.setText(list.get(position).getName());
        holder.txtSoLuong.setText(String.valueOf(list.get(position).getSoluongdamua()));



    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView txtTensp, txtSoLuong;
        ImageView img;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTensp = itemView.findViewById(R.id.txtTenSP);
            txtSoLuong = itemView.findViewById(R.id.txtSoLuong);
            img = itemView.findViewById(R.id.img_top);
        }
    }
}


