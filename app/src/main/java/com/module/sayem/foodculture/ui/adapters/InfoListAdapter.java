package com.module.sayem.foodculture.ui.adapters;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.graphics.Color;
import android.os.Handler;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.module.sayem.foodculture.R;
import com.module.sayem.foodculture.storage.roomDB.AppDatabase;
import com.module.sayem.foodculture.storage.roomDB.User_En;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class InfoListAdapter extends RecyclerView.Adapter<InfoListAdapter.InfoViewHolder> {

    private final List<User_En> info_data;
    private final OnItemClickListener listener;
    private static final int PENDING_REMOVAL_TIMEOUT = 3000; // 3sec
    private HashMap<User_En, Runnable> pendingRunnables = new HashMap<>();
    private Handler handler = new Handler();
    private List<User_En> itemsPendingRemoval = new ArrayList<>();
    private Context context;

    public InfoListAdapter(List<User_En> info, OnItemClickListener listener) {
        this.info_data = info;
        this.listener = listener;
    }

    @Override
    public InfoListAdapter.InfoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_info, parent, false);
        return new InfoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(InfoListAdapter.InfoViewHolder holder, int position) {
        /*holder.tv_firstname.setText(info_data.get(position).getFirstName());
        holder.tv_lastname.setText(info_data.get(position).getLastName());
        holder.tv_email.setText(info_data.get(position).getU_email());
        holder.bind(info_data.get(position), listener, position);*/

        final User_En item = info_data.get(position);
        if (itemsPendingRemoval.contains(item)) {
            // we need to show the "undo" state of the row
            holder.itemView.setBackgroundColor(Color.RED);
            //holder.cv_item.setVisibility(View.GONE);
            //holder.tv_undo.setVisibility(View.VISIBLE);
            //holder.tv_undo.setText(R.string.undo);
            holder.tv_firstname.setVisibility(View.GONE);
            holder.tv_lastname.setVisibility(View.GONE);
            holder.tv_email.setVisibility(View.GONE);
            holder.btn_undo.setVisibility(View.VISIBLE);
            holder.btn_undo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // user wants to undo the removal, let's cancel the pending task
                    Runnable pendingRemovalRunnable = pendingRunnables.get(item);
                    pendingRunnables.remove(item);
                    if (pendingRemovalRunnable != null)
                        handler.removeCallbacks(pendingRemovalRunnable);
                    itemsPendingRemoval.remove(item);
                    // this will rebind the row in "normal" state
                    notifyItemChanged(info_data.indexOf(item));
                }
            });
        } else {
            // we need to show the "normal" state
            holder.itemView.setBackgroundColor(Color.WHITE);
            holder.tv_firstname.setText(info_data.get(position).getFirstName());
            holder.tv_lastname.setText(info_data.get(position).getLastName());
            holder.tv_email.setText(info_data.get(position).getU_email());
            holder.bind(info_data.get(position), listener, position);
            holder.btn_undo.setVisibility(View.GONE);
            holder.btn_undo.setOnClickListener(null);
        }
    }

    @Override
    public int getItemCount() {
        return info_data.size();
    }

    public interface OnItemClickListener {
        void onItemClick(User_En item, int position);
    }

    public boolean isUndoOn() {
        return true;
    }

    public void pendingRemoval(int position) {
        final User_En item = info_data.get(position);
        if (!itemsPendingRemoval.contains(item)) {
            itemsPendingRemoval.add(item);
            // this will redraw row in "undo" state
            notifyItemChanged(position);
            // let's create, store and post a runnable to remove the item
            Runnable pendingRemovalRunnable = () -> remove(info_data.indexOf(item));
            handler.postDelayed(pendingRemovalRunnable, PENDING_REMOVAL_TIMEOUT);
            pendingRunnables.put(item, pendingRemovalRunnable);
        }
    }

    public void remove(int position) {
        User_En item = info_data.get(position);
        if (itemsPendingRemoval.contains(item)) {
            itemsPendingRemoval.remove(item);
        }
        if (info_data.contains(item)) {
            info_data.remove(position);
            AppDatabase db = Room.databaseBuilder(context,
                    AppDatabase.class, "app-database")
                    .allowMainThreadQueries()
                    .build();
            db.userDao().delete(item);
            notifyItemRemoved(position);
        }
    }

    class InfoViewHolder extends RecyclerView.ViewHolder {

        Button btn_undo;
        CardView cv_item;
        private TextView tv_firstname, tv_lastname, tv_email;

        InfoViewHolder(View itemView) {
            super(itemView);
            tv_firstname = itemView.findViewById(R.id.tv_demo);
            tv_lastname = itemView.findViewById(R.id.tv_demo1);
            tv_email = itemView.findViewById(R.id.tv_demo2);
            btn_undo = itemView.findViewById(R.id.undo_button);
            cv_item = itemView.findViewById(R.id.cv_item);
        }

        void bind(final User_En item, final OnItemClickListener listener, int position) {
            //Picasso.with(itemView.getContext()).load(item.imageUrl).into(image);
            itemView.setOnClickListener(v -> listener.onItemClick(item, position));
        }
    }

    public boolean isPendingRemoval(int position) {
        User_En item = info_data.get(position);
        return itemsPendingRemoval.contains(item);
    }
}
