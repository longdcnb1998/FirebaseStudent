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
import com.example.firebasestudent.databinding.FragmentNotificationGeneralBinding;
import com.example.firebasestudent.model.Notice;
import com.example.firebasestudent.view.view.adapter.NotificationAdapter;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class Fragment_NotificationGeneral extends Fragment {

    FragmentNotificationGeneralBinding binding;
    private NotificationAdapter adapter;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_notification_general,container,false);

        String jsonNoticeGen = getArguments().getString("General");
        Log.d("General",jsonNoticeGen);
//
        Gson gson = new Gson();
        Type type = new TypeToken<List<Notice>>(){}.getType();
        ArrayList<Notice> notices;
        notices = gson.fromJson(jsonNoticeGen,type);

        adapter = new NotificationAdapter(getContext(),notices);
        binding.recyclerViewNotiGen.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL,false));
        binding.recyclerViewNotiGen.setAdapter(adapter);

        View view = binding.getRoot();

        return view;
    }
}
