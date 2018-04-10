package com.module.sayem.foodculture.ui.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.module.sayem.foodculture.R;

import java.util.ArrayList;

public class InfoListAdapter extends RecyclerView.Adapter<InfoListAdapter.InfoViewHolder> {

    private ArrayList<String> info_data;

    public InfoListAdapter(ArrayList<String> info) {
        this.info_data = info;
    }

    @Override
    public InfoListAdapter.InfoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_info, parent, false);
        return new InfoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(InfoListAdapter.InfoViewHolder holder, int position) {
        holder.tv_demo.setText(info_data.get(position));
    }

    @Override
    public int getItemCount() {
        return info_data.size();
    }

    public class InfoViewHolder extends RecyclerView.ViewHolder {

        private TextView tv_demo;
        public InfoViewHolder(View itemView) {
            super(itemView);
            tv_demo = itemView.findViewById(R.id.tv_demo);
        }
    }
}
