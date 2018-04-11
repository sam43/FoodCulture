package com.module.sayem.foodculture.ui.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.module.sayem.foodculture.R;
import com.module.sayem.foodculture.storage.roomDB.User_En;

import java.util.List;

public class InfoListAdapter extends RecyclerView.Adapter<InfoListAdapter.InfoViewHolder> {

    private List<User_En> info_data;

    public InfoListAdapter(List<User_En> info) {
        this.info_data = info;
    }

    @Override
    public InfoListAdapter.InfoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_info, parent, false);
        return new InfoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(InfoListAdapter.InfoViewHolder holder, int position) {
        holder.tv_firstname.setText(info_data.get(position).getFirstName());
        holder.tv_lastname.setText(info_data.get(position).getLastName());
        holder.tv_email.setText(info_data.get(position).getU_email());
    }

    @Override
    public int getItemCount() {
        return info_data.size();
    }

    public class InfoViewHolder extends RecyclerView.ViewHolder {

        private TextView tv_firstname, tv_lastname, tv_email;
        public InfoViewHolder(View itemView) {
            super(itemView);
            tv_firstname = itemView.findViewById(R.id.tv_demo);
            tv_lastname = itemView.findViewById(R.id.tv_demo1);
            tv_email = itemView.findViewById(R.id.tv_demo2);
        }
    }
}
