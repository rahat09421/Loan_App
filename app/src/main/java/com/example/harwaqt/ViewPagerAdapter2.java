package com.example.harwaqt;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class ViewPagerAdapter2 extends FragmentStateAdapter {

    public ViewPagerAdapter2(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return new CurrentOrderFragment();
            case 1:
                return new OrderHistoryFragment();
            default:
                return new CurrentOrderFragment();
        }
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}

