package com.example.firebasestudent.view.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.firebasestudent.R;
import com.example.firebasestudent.databinding.ItemClassBinding;
import com.example.firebasestudent.model.Class;

import java.util.ArrayList;

public class ClassAdapter extends RecyclerView.Adapter<ClassAdapter.ViewHolder>{


    private Context mContext;
    private ArrayList<Class> classes;

    public ClassAdapter(Context mContext, ArrayList<Class> classes) {
        this.mContext = mContext;
        this.classes = classes;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemClassBinding itemClassBinding = DataBindingUtil.inflate(LayoutInflater.from(mContext), R.layout.item_class,parent,false);
        ViewHolder viewHolder = new ViewHolder(itemClassBinding);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ViewHolder viewHolder = (ViewHolder) holder;
        viewHolder.bindData(position);
    }

    @Override
    public int getItemCount() {
        return classes.size();
    }

    public void removeItem(int position) {
        classes.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, classes.size());
    }

    public void restoreItem(Class model, int position) {
        classes.add(position, model);
        // notify item added by position
        notifyItemInserted(position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ItemClassBinding itemClassBinding;

        public ViewHolder(@NonNull ItemClassBinding itemClassBinding) {
            super(itemClassBinding.getRoot());
            this.itemClassBinding = itemClassBinding;
        }

        public void  bindData(int position) {
            Class aClass = classes.get(position);
            if (aClass != null){
                int count= 0;
//                int count =aClass.students.size();
//                int count =aClass.getCount();
               if (aClass.students != null){
                   count =aClass.getStudents().size();
               }
                itemClassBinding.textViewClassName.setText(aClass.getClassName());
                itemClassBinding.textViewTeacherName.setText("Giáo Viên :"+aClass.getTeacher());
                itemClassBinding.textViewCount.setText("Sĩ Số : "+String.valueOf(count));
            }
        }
    }
}
