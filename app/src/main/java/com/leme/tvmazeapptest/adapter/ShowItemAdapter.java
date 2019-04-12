package com.leme.tvmazeapptest.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.leme.tvmazeapptest.model.Show;

import java.util.List;

public class ShowItemAdapter extends RecyclerView.Adapter<ShowItemAdapter.ShowItemViewHolder {

    @NonNull
    @Override
    public ShowItemViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull ShowItemViewHolder showItemViewHolder, int i) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public void setListData(List<Show> shows) {
        
    }

    public class ShowItemViewHolder {

    }

}
