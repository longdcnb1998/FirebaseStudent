package com.example.firebasestudent.view.view.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.MenuItem;

import com.example.firebasestudent.R;
import com.example.firebasestudent.databinding.ActivityTeacherBinding;
import com.example.firebasestudent.model.Student;
import com.example.firebasestudent.view.view.fragment.Fragment_Notification;
import com.example.firebasestudent.view.view.fragment.Fragment_NotificationGeneral;
import com.example.firebasestudent.view.view.fragment.Fragment_NotificationPrivate;
import com.example.firebasestudent.view.view.fragment.Fragment_Teacher;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class TeacherActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    ActivityTeacherBinding binding;
    private Fragment_Teacher teacher;
    private Fragment_Notification notification;
    private Fragment_NotificationPrivate notificationPrivate;
    private Fragment_NotificationGeneral notificationGeneral;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher);


        getSupportActionBar().hide();

        binding = DataBindingUtil.setContentView(this,R.layout.activity_teacher);

        binding.bottomNavigation.setOnNavigationItemSelectedListener(this);

        String jsonClass = this.getIntent().getStringExtra("Class");
        Log.d("Class",jsonClass);
        String position = this.getIntent().getStringExtra("Position");



        Bundle bundle = new Bundle();
        bundle.putString("Class",jsonClass);
        bundle.putString("Position",position);


        teacher = new Fragment_Teacher();
        teacher.setArguments(bundle);

        notification = new Fragment_Notification();
        notification.setArguments(bundle);

        loadFragment(teacher);

    }



    private boolean loadFragment(Fragment fragment){
        if (fragment != null){
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.Fragment_container,fragment)
                    .commit();
            return true;
        }
        return false;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        Fragment fragment = null;
        switch (menuItem.getItemId()){
            case R.id.navigation_notification:
                fragment = notification;
                break;
            case R.id.navigation_user:
                fragment = teacher;
                break;
        }
        return loadFragment(fragment);
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState, @NonNull PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
//        outState.putInt("");
    }
}
