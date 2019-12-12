package com.example.firebasestudent.view.view.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.firebasestudent.R;
import com.example.firebasestudent.databinding.ItemStudentBinding;
import com.example.firebasestudent.model.Student;

import java.util.ArrayList;

public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.ViewHolder> {

    public Context mContext;
    public ArrayList<Student> students;

    public StudentAdapter(Context mContext, ArrayList<Student> students) {
        this.mContext = mContext;
        this.students = students;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemStudentBinding binding = DataBindingUtil.inflate(LayoutInflater.from(mContext), R.layout.item_student,parent,false);
        ViewHolder viewHolder = new ViewHolder(binding);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ViewHolder viewHolder = holder;
        viewHolder.bindData(position);
    }

    @Override
    public int getItemCount() {
//        Log.d("ListSize",students.get(0).getName());
        return students.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public ItemStudentBinding binding;

        public ViewHolder(@NonNull ItemStudentBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bindData(int position){
            Student student = students.get(position);
//            Log.d("GetName",student.getName() + student.getUsername());
            if (student != null){
                binding.textViewStudentName.setText(student.getName());
            }
        }
    }
}
