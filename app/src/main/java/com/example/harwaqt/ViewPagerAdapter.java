package com.example.harwaqt;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class ViewPagerAdapter extends FragmentStateAdapter {

    public ViewPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return new AvailableFragment();
            case 1:
                return new UsedFragment();
            case 2:
                return new ExpiredFragment();
            default:
                return new AvailableFragment();
        }
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}
