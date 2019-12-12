package com.example.firebasestudent.view.view.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.example.firebasestudent.R;
import com.example.firebasestudent.databinding.FragmentParentTuitionFeeBinding;

public class FragmentParentTuitionFee extends Fragment {

    FragmentParentTuitionFeeBinding binding;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_parent_tuition_fee,container,false);


        View view = binding.getRoot();
        return view;
    }
}
