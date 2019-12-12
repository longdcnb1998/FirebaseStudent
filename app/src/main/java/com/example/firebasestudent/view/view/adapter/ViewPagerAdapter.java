package com.example.firebasestudent.view.view.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.firebasestudent.view.view.fragment.Fragment_NotificationGeneral;
import com.example.firebasestudent.view.view.fragment.Fragment_NotificationPrivate;

public class ViewPagerAdapter extends FragmentPagerAdapter {

    Fragment_NotificationGeneral general;
    Fragment_NotificationPrivate aPrivate;
    private String list[] = {"Chung","Cá Nhân"};

    public ViewPagerAdapter(@NonNull FragmentManager fm, int behavior,Fragment_NotificationPrivate privateNo,Fragment_NotificationGeneral generalNo) {
        super(fm, behavior);
         general = generalNo;
         aPrivate = privateNo;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        if (position == 0){
            return general;
        }
        else return aPrivate;
    }

    @Override
    public int getCount() {
        return list.length;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        String title = list[position];
        return title;
    }
}
