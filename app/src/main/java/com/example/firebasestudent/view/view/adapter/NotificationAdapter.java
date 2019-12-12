package com.example.firebasestudent.view.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.firebasestudent.R;
import com.example.firebasestudent.databinding.ItemNotificationBinding;
import com.example.firebasestudent.model.Notice;
import com.google.android.gms.common.util.DataUtils;

import java.util.ArrayList;

public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.ViewHolder> {

    private Context mContext;
    private ArrayList<Notice> notices;

    public NotificationAdapter(Context mContext, ArrayList<Notice> notices) {
        this.mContext = mContext;
        this.notices = notices;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemNotificationBinding binding = DataBindingUtil.inflate(LayoutInflater.from(mContext), R.layout.item_notification,parent,false);
        ViewHolder viewHolder = new ViewHolder(binding);
        return  viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ViewHolder viewHolder = holder;
        viewHolder.bindData(position);
    }

    @Override
    public int getItemCount() {
        return notices.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        ItemNotificationBinding binding;

        public ViewHolder(@NonNull ItemNotificationBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bindData (int position){
            Notice notice = notices.get(position);
            if (notice != null){
                binding.textViewTimeStamp.setText("Thời Gian :"+notice.getTimeStamp());
                binding.textViewContent.setText("Nội Dung :"+notice.getContent());
            }
        }

    }
}
