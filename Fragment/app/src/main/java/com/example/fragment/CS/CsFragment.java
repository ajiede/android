package com.example.fragment.CS;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.fragment.R;

public class CsFragment extends Fragment {

    final String TAG="SSS";

    public static CsFragment instance(String flag){
        CsFragment csFragment=new CsFragment();
        Bundle bundle=new Bundle();
        bundle.putString("flag",flag);
        csFragment.setArguments(bundle);
        return csFragment;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG,"save **********onCreate");
//        mParam1=getArguments().getString(ARG_PARAM1);
//        mParam2=getArguments().getString(ARG_PARAM2);

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Log.d(TAG,"save **********onCreateView");
        return inflater.inflate(R.layout.fragment_cs, container, false);
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        TextView textCs= (TextView) view.findViewById(R.id.text_cs);
        textCs.setText(getArguments().getString("flag"));
    }
    @Override
    public void onAttach(Context context){
        super.onAttach(context);
        Log.d(TAG,"save **********onAttach");
    }
    @Override
    public void onActivityCreated(@NonNull  Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
        Log.d(TAG,"save **********onActivityCreated");
    }
    @Override
    public void onStart(){
        super.onStart();
        Log.d(TAG,"save **********onStart");
    }
    @Override
    public void onResume(){
        super.onResume();
        Log.d(TAG,"save **********Resume");
    }

    @Override
    public void onDestroyView(){
        super.onDestroyView();
        Log.d(TAG,"save **********onDestroyView");
    }
    @Override
    public void onDestroy(){
        super.onDestroy();
        Log.d(TAG,"save **********onDestroy");
    }
    @Override
    public void onDetach(){
        super.onDetach();
        Log.d(TAG,"save **********onDetach");
    }
}