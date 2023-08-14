package com.PubgBgmi.gfxbgmi;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;


public class C3616a extends FragmentStateAdapter {


    public C3616a(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }


    @NonNull
    @Override
    public Fragment createFragment(int position) {
        if (position == 0) {
            return new gfxFragment();
        }
        return new faqFragment();
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
