package com.pictureyourtravel.pyt.ui.standard_signup_fourth_screen;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pictureyourtravel.pyt.R;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class InterestAdapter extends RecyclerView.Adapter   <InterestAdapter.MyViewHolder>{

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.interest_adapter, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
