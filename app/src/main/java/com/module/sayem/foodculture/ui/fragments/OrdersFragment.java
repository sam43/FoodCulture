package com.module.sayem.foodculture.ui.fragments;


import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.module.sayem.foodculture.R;
import com.module.sayem.foodculture.storage.roomDB.User_En;
import com.module.sayem.foodculture.ui.adapters.InfoListAdapter;
import com.module.sayem.foodculture.utils.Utility;

import java.util.ArrayList;
import java.util.List;

import es.dmoral.toasty.Toasty;

public class OrdersFragment extends BaseFragment implements View.OnClickListener {

    private static final String TAG = "FragOffer";
    private OnFragmentInteractionListener mListener;
    FloatingActionButton btn_add_info;
    String f_name = "", l_name = "", u_email = "";
    RecyclerView rv_info_list;
    LinearLayoutManager layoutManager;
    RecyclerView.Adapter adapter;
    List<User_En> users;
    private User_En user_en;
    Utility utility;
    //ArrayList<Info> info_data;

    public OrdersFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        utility = new Utility();
        initRecyclerView();
    }

    private void viewOnclickHandling() {
        btn_add_info.setOnClickListener(this);
    }

    private void initializeViews(View view) {
        btn_add_info = view.findViewById(R.id.btn_add_info);
        users = new ArrayList<>();
        rv_info_list = view.findViewById(R.id.rv_info_list);
        layoutManager = new LinearLayoutManager(getActivity());
    }

    private void initRecyclerView() {
        try {
            if (users != null) {
                users = AppDB().userDao().getAllUsers();
            } else {
                Toasty.error(getActivity(), "data is null...", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        rv_info_list.setHasFixedSize(true);
        rv_info_list.setLayoutManager(layoutManager);
        adapter = new InfoListAdapter(users, (item, position) -> Toasty.error(getActivity(), "Item Clicked..." + position, Toast.LENGTH_SHORT).show());
        rv_info_list.setAdapter(adapter);
        utility.setUpItemTouchHelper(getActivity(), rv_info_list);
        utility.setUpAnimationDecoratorHelper(rv_info_list);
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
                //Toasty.error(getActivity(), "add to info list", Toast.LENGTH_SHORT).show();
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
        final EditText edt_email = dialogView.findViewById(R.id.edit_email);

        dialogBuilder.setTitle("User Servey");
        dialogBuilder.setIcon(R.drawable.ic_boy);
        dialogBuilder.setMessage("Enter info here:");
        dialogBuilder.setPositiveButton("Done", (dialog, whichButton) -> {
            f_name = edt_name.getText().toString();
            l_name = edt_info.getText().toString();
            u_email = edt_email.getText().toString();

            Log.d(TAG, "showInputDialog: database will update" + f_name + "\n" + l_name + "\n" + u_email);
            user_en = new User_En(f_name, l_name, u_email);
            AppDB().userDao().insertAll(user_en);
            //halka chorami korchi
            FragmentTransaction ft = getFragmentManager().beginTransaction();
            ft.detach(this).attach(this).commit();
        });
        dialogBuilder.setNegativeButton("Cancel", (dialog, whichButton) -> {
            //pass
            Toasty.error(getActivity(), "Negative", Toast.LENGTH_SHORT).show();
            AppDB().userDao().deleteAll();
        });
        AlertDialog b = dialogBuilder.create();
        b.show();
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
