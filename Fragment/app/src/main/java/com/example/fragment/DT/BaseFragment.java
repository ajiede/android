package com.example.fragment.DT;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.fragment.R;


public class BaseFragment extends Fragment {
    public static BaseFragment instance(String flag) {
        BaseFragment f = new BaseFragment();
        Bundle bundle = new Bundle();
        bundle.putString("flag", flag);
        f.setArguments(bundle);
        return f;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_base, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        TextView textFlag = view.findViewById(R.id.text_flag);
        textFlag.setText(getArguments().getString("flag"));
    }
}