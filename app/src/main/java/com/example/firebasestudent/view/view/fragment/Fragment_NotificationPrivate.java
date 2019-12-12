package com.example.firebasestudent.view.view.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.firebasestudent.R;
import com.example.firebasestudent.databinding.FragmentNotificationPrivateBinding;
import com.example.firebasestudent.model.Notice;
import com.example.firebasestudent.view.view.adapter.NotificationAdapter;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class Fragment_NotificationPrivate extends Fragment {


    private FragmentNotificationPrivateBinding binding;
    private NotificationAdapter adapter;
    private ArrayList<Notice> notices;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_notification_private,container,false);

        String jsonNoticePri = getArguments().getString("Private");
        Log.d("Private",jsonNoticePri);
//
        notices = new ArrayList<>();
        Gson gson = new Gson();
        Type type = new TypeToken<List<Notice>>(){}.getType();
        notices = gson.fromJson(jsonNoticePri,type);
        adapter = new NotificationAdapter(getContext(),notices);
        binding.recyclerViewNotiPrivate.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL,false));

        binding.recyclerViewNotiPrivate.setAdapter(adapter);

        View view = binding.getRoot();

        return view;
    }
}
