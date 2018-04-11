package com.module.sayem.foodculture.ui.fragments;

import android.app.ProgressDialog;
import android.arch.persistence.room.Room;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.View;

import com.module.sayem.foodculture.storage.roomDB.AppDatabase;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link BaseFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 */
public abstract class BaseFragment extends Fragment {

    private static final String TAG = "FragBase";
    private OnFragmentInteractionListener mListener;

    public BaseFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate: ");
    }

    protected ProgressDialog mProgressDialog;
    protected int mRequestingFor;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.d(TAG, "onViewCreated: ");


    }

    protected void dismissProgress() {
        if(mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.hide();
        }
    }

    protected void AppDB() {
        AppDatabase db = Room.databaseBuilder(getActivity(),
                AppDatabase.class, "app-database")
                .allowMainThreadQueries()
                .build();
    }

    public void showProgress(String text) {
        if(mProgressDialog == null) {
            mProgressDialog = new ProgressDialog(getActivity());
        }
        mProgressDialog.setCancelable(true);
//                mProgressDialog.setTitle(getString(R.string.signing_in));
        mProgressDialog.setMessage(text);
        mProgressDialog.show();
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.d(TAG, "onActivityCreated: ");
    }

    //public abstract View ChildFragmentView(LayoutInflater inflater,ViewGroup parent, Bundle savedInstanceState);


    @Override
    public void onDetach() {
        super.onDetach();
        Log.d(TAG, "onDetach: ");
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
