package com.module.sayem.foodculture.ui.fragments;

import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.module.sayem.foodculture.R;
import com.module.sayem.foodculture.models.Info;
import com.module.sayem.foodculture.ui.adapters.InfoListAdapter;

import java.util.ArrayList;

import es.dmoral.toasty.Toasty;

public class OrdersFragment extends BaseFragment implements View.OnClickListener {

    private OnFragmentInteractionListener mListener;
    FloatingActionButton btn_add_info;
    String name, info;
    RecyclerView rv_info_list;
    LinearLayoutManager layoutManager;
    RecyclerView.Adapter adapter;
    ArrayList<String> info_data;
    //ArrayList<Info> info_data;

    public OrdersFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_orders, container, false);
        initializeViews(view);
        viewOnclickHandling();
        return view;
    }

    private void viewOnclickHandling() {
        btn_add_info.setOnClickListener(this);
    }

    private void initializeViews(View view) {
        btn_add_info = view.findViewById(R.id.btn_add_info);
        info_data = new ArrayList<>();
        rv_info_list = view.findViewById(R.id.rv_info_list);
        layoutManager = new LinearLayoutManager(getActivity());
        initRecyclerView();
    }

    private void initRecyclerView() {

        for (int i = 0; i < 20; i++) {
            info_data.add("Sample User #" + i + "OK!");
        }

        rv_info_list.setHasFixedSize(true);
        rv_info_list.setLayoutManager(layoutManager);
        adapter = new InfoListAdapter(info_data);
        rv_info_list.setAdapter(adapter);
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_add_info:
                Toasty.error(getActivity(), "add to info list", Toast.LENGTH_SHORT).show();
                showInputDialog();
                break;
            /*case R.id.responseButton2:
                break;*/
            default:
                Toasty.error(getActivity(), "default", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    public void showInputDialog() {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = this.getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.custom_input_dialog, null);
        dialogBuilder.setCancelable(false);
        dialogBuilder.setView(dialogView);

        final EditText edt_name = dialogView.findViewById(R.id.edit_name);
        final EditText edt_info = dialogView.findViewById(R.id.edit_info);
        //final EditText edt_name = dialogView.findViewById(R.id.edit_name);

        name = edt_name.getText().toString();
        info = edt_info.getText().toString();


        dialogBuilder.setTitle("User Servey");
        dialogBuilder.setIcon(R.drawable.ic_boy);
        dialogBuilder.setMessage("Enter desired info here:");
        dialogBuilder.setPositiveButton("Done", (dialog, whichButton) -> {
            //do something with edt.getText().toString();
            Toasty.error(getActivity(), "Positive", Toast.LENGTH_SHORT).show();
        });
        dialogBuilder.setNegativeButton("Cancel", (dialog, whichButton) -> {
            //pass
            Toasty.error(getActivity(), "Negative", Toast.LENGTH_SHORT).show();
        });
        AlertDialog b = dialogBuilder.create();
        b.show();
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
