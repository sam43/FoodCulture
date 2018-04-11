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

    private final List<User_En> info_data;
    private final OnItemClickListener listener;

    public InfoListAdapter(List<User_En> info, OnItemClickListener listener) {
        this.info_data = info;
        this.listener = listener;
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
        holder.bind(info_data.get(position), listener, position);
    }

    @Override
    public int getItemCount() {
        return info_data.size();
    }

    public interface OnItemClickListener {
        void onItemClick(User_En item, int position);
    }

    class InfoViewHolder extends RecyclerView.ViewHolder {

        private TextView tv_firstname, tv_lastname, tv_email;

        InfoViewHolder(View itemView) {
            super(itemView);
            tv_firstname = itemView.findViewById(R.id.tv_demo);
            tv_lastname = itemView.findViewById(R.id.tv_demo1);
            tv_email = itemView.findViewById(R.id.tv_demo2);
        }

        void bind(final User_En item, final OnItemClickListener listener, int position) {
            //Picasso.with(itemView.getContext()).load(item.imageUrl).into(image);
            itemView.setOnClickListener(v -> listener.onItemClick(item, position));
        }
    }
}
