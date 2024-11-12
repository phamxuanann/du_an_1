package com.example.abc_fpt.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.abc_fpt.R;
import com.example.abc_fpt.modal.Onboadingitem;

import java.util.List;

public class Onboadingadapter extends RecyclerView.Adapter<Onboadingadapter.OnboadingViewHolder> {
    List<Onboadingitem> list;

    public Onboadingadapter(List<Onboadingitem> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public OnboadingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new OnboadingViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_anhchay, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull OnboadingViewHolder holder, int position) {
        holder.bindData(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class OnboadingViewHolder extends RecyclerView.ViewHolder {
        private ImageView img_onboarding;
        private TextView tv_onboarding;
        private TextView tv_onboarding_description;

        OnboadingViewHolder(@NonNull View view) {
            super(view);
            img_onboarding = view.findViewById(R.id.img_onboarding);
            tv_onboarding = view.findViewById(R.id.tv_onboarding);
            tv_onboarding_description = view.findViewById(R.id.tv_onboardingDescription);
        }

        void bindData(Onboadingitem item) {
            img_onboarding.setImageResource(item.getImage());
            tv_onboarding.setText(item.getTitle());
            tv_onboarding_description.setText(item.getDescription());
        }
    }
}
