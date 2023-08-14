package com.PubgBgmi.gfxbgmi;

import android.annotation.TargetApi;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.startapp.sdk.adsbase.StartAppAd;
import com.startapp.sdk.adsbase.StartAppSDK;

import java.util.ArrayList;

public class MainActivity extends privacypolicy {

    private ViewPager2 viewPager2;

    TabLayout tabLayout;

    private int num = 3;

    private C3616a adapter;


    @TargetApi(23)
    public void m23047M() {
        if (!getApplicationContext().getPackageName().equals("com.PubgBgmi.gfxbgmi")) {
            finish();
        }
        int checkSelfPermission = checkSelfPermission("android.permission.WRITE_EXTERNAL_STORAGE");
        ArrayList arrayList = new ArrayList();
        if (checkSelfPermission != 0) {
            arrayList.add("android.permission.WRITE_EXTERNAL_STORAGE");
        }
        if (!arrayList.isEmpty()) {
            requestPermissions((String[]) arrayList.toArray(new String[arrayList.size()]), this.num);
        }
    }

    @TargetApi(23)
    private boolean m23048N() {
        return checkSelfPermission("android.permission.WRITE_EXTERNAL_STORAGE") == PackageManager.PERMISSION_GRANTED;
    }

    @TargetApi(23)
    private void m23049O() {
        boolean z;
        String string = getString(R.string.perm_unknown);
        if (checkSelfPermission("android.permission.WRITE_EXTERNAL_STORAGE") != PackageManager.PERMISSION_GRANTED) {
            Log.e("GFX Tool", "Storage permission not granted!");
            z = true;
        } else {
            z = false;
        }
        if (z) {
            string = getString(R.string.perm_storage);
        }
      //  new AlertDialog.Builder(this).setMessage(getResources().getString(R.string.denied_title)).setTitle(string).setPositiveButton(getResources().getString(R.string.ask_again), new C3615b()).setPositiveButton(getResources().getString(R.string.dismiss), new C3614a()).show();
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.activity_main);
        tabLayout = findViewById(R.id.tabs);
       viewPager2 = findViewById(R.id.container);

        StartAppSDK.setTestAdsEnabled(true);
        StartAppAd.showAd(this);



        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);



        if (Build.VERSION.SDK_INT >= 23 && !m23048N()) {
            m23047M();
        }
        FragmentManager fragmentManager = getSupportFragmentManager();
        adapter = new C3616a(fragmentManager, getLifecycle());
        viewPager2.setAdapter(adapter);



        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {

            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager2.setCurrentItem(tab.getPosition());

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });


        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                tabLayout.selectTab(tabLayout.getTabAt(position));
            }
        });


    }

//    @TargetApi(33)
//    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
//        super.onRequestPermissionsResult(i, strArr, iArr);
//        if (m23048N()) {
//            Log.i("GFX Tool", "All required permissions were granted");
//        } else if (shouldShowRequestPermissionRationale("android.permission.WRITE_EXTERNAL_STORAGE")) {
//            m23049O();
//        } else {
//            Toast.makeText(this, getString(R.string.perm_toast), Toast.LENGTH_LONG).show();
//        }
//    }


//    class C3615b implements DialogInterface.OnClickListener {
//        C3615b() {
//        }
//
//        public void onClick(DialogInterface dialogInterface, int i) {MainActivity.this.m23047M();
//        }
//    }



}



