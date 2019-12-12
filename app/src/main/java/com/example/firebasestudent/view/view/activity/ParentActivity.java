package com.example.firebasestudent.view.view.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import com.example.firebasestudent.R;
import com.example.firebasestudent.databinding.ActivityParentBinding;
import com.example.firebasestudent.model.Notice;
import com.example.firebasestudent.view.view.adapter.NotificationAdapter;
import com.example.firebasestudent.view.view.adapter.ViewPagerAdapter;
import com.example.firebasestudent.view.view.fragment.FragmentMessage;
import com.example.firebasestudent.view.view.fragment.FragmentParentNotification;
import com.example.firebasestudent.view.view.fragment.FragmentParentTuitionFee;
import com.example.firebasestudent.view.view.fragment.Fragment_NotificationGeneral;
import com.example.firebasestudent.view.view.fragment.Fragment_NotificationPrivate;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class ParentActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    ActivityParentBinding binding ;
    private NotificationAdapter adapter;
    private ArrayList<Notice> notices;
    private FragmentParentNotification notification;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parent);

        getSupportActionBar().hide();

        binding = DataBindingUtil.setContentView(this,R.layout.activity_parent);

        String studentName = this.getIntent().getStringExtra("StudentName");
        String className = this.getIntent().getStringExtra("ClassName");
        String positionClass = this.getIntent().getStringExtra("PositionClass");
        String position = this.getIntent().getStringExtra("Position");

        binding.bottomNavigation.setOnNavigationItemSelectedListener(this);

        Bundle bundle = new Bundle();
        bundle.putString("ClassName",className);
        bundle.putString("Position",position);
        bundle.putString("PositionClass",positionClass);
        bundle.putString("StudentName",studentName);

         notification = new FragmentParentNotification();
        notification.setArguments(bundle);

        loadFragment(notification);
    }

    private boolean loadFragment(Fragment fragment){
        if (fragment != null){
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.Fragment_container_Parent,fragment)
                    .commit();
            return true;
        }
        return false;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        Fragment fragment = null;
        switch (menuItem.getItemId()){
            case R.id.navigation_notification_parent:
                fragment = notification;
                break;
            case R.id.navigation_HocPhi:
                fragment = new FragmentParentTuitionFee();
                break;
            case R.id.navigation_mess:
                fragment = new FragmentMessage();
        }
        return loadFragment(fragment);
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState, @NonNull PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
    }
}
