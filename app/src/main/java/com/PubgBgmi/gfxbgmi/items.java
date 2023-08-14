package com.PubgBgmi.gfxbgmi;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.PubgBgmi.gfxbgmi.databinding.ActivityItemsBinding;
import com.startapp.sdk.adsbase.Ad;
import com.startapp.sdk.adsbase.StartAppAd;
import com.startapp.sdk.adsbase.adlisteners.AdEventListener;
import com.startapp.sdk.adsbase.adlisteners.VideoListener;
import com.unity3d.ads.IUnityAdsInitializationListener;
import com.unity3d.ads.IUnityAdsLoadListener;
import com.unity3d.ads.IUnityAdsShowListener;
import com.unity3d.ads.UnityAds;
import com.unity3d.ads.UnityAdsShowOptions;

import java.util.Timer;
import java.util.TimerTask;

public class items extends AppCompatActivity{
    
    ActivityItemsBinding binding;
     ProgressDialog progressDialog;
     TimerTask timerTask;

     Timer _timer = new Timer();




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityItemsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);


        binding.accept.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {

                Toast.makeText(items.this, "Activating", Toast.LENGTH_SHORT).show();



//                items.this.progressDialog = new ProgressDialog(items.this);
//                items.this.progressDialog.setTitle("Ads Loading...");
//                items.this.progressDialog.setMessage("Please wait For 5 Seconds...");
//                items.this.progressDialog.setProgress(99);
//                items.this.progressDialog.setCancelable(false);
//                items.this.progressDialog.setCanceledOnTouchOutside(false);
//                items.this.progressDialog.setProgressStyle(0);
//                items.this.progressDialog.show();


                StartAppAd video = new StartAppAd(items.this);
                video.setVideoListener(new VideoListener() {
                    @Override
                    public void onVideoCompleted() {

                        Intent intent = new Intent(items.this, items.class);
                      startActivity(intent);

                        Toast.makeText(items.this, "Activated", Toast.LENGTH_SHORT).show();


//                        items.this.timerTask = new TimerTask() {
//                           public void run() {
////                                items.this.runOnUiThread(new Runnable() {
////                                    public void run() {
////
////                                        androidx.appcompat.app.AlertDialog.Builder builder1 = new androidx.appcompat.app.AlertDialog.Builder(items.this);
////                                        builder1.setMessage("Activated Successful");
////                                        builder1.setCancelable(true);
////
////                                        builder1.setPositiveButton(
////                                                "DONE",
////                                                new DialogInterface.OnClickListener() {
////                                                    public void onClick(DialogInterface dialog, int id) {
//////                                                        Intent intent = new Intent(earn_section.this, earn_section.class);
//////                                                        startActivity(intent);
////
////                                                    }
////                                                });
////                                        androidx.appcompat.app.AlertDialog alert11 = builder1.create();
////                                            alert11.show();
////
////                                    }
////                                });
//                               items.this.progressDialog.dismiss();
////
//                    }
//
//                        };
//
//                        items.this._timer.schedule(items.this.timerTask, 1000);




                    }
                });

                video.loadAd(StartAppAd.AdMode.REWARDED_VIDEO, new AdEventListener() {
                    @Override
                    public void onReceiveAd(@NonNull Ad ad) {
                        video.showAd();
                    }

                    @Override
                    public void onFailedToReceiveAd(@Nullable Ad ad) {

                    }
                });







    }
        });

        binding.insta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StartAppAd.showAd(items.this);


            }
        });

        binding.rate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               launchMarket();
                Uri uri = Uri.parse("https://play.google.com/store/apps/details?id=com.PubgBgmi.gfxbgmi");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            }
        });



    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        try {
            Toast.makeText(getApplicationContext(), "back pressed", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(items.this,MainActivity.class);
            startActivity(intent);
        }catch (Exception e){
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }

    }

    private void launchMarket() {
        Uri uri = Uri.parse("market://details?id=" + getPackageName());
        Intent myAppLinkToMarket = new Intent(Intent.ACTION_VIEW, uri);
        try {
            startActivity(myAppLinkToMarket);
        } catch (ActivityNotFoundException e) {
            Toast.makeText(this, " unable to find market app", Toast.LENGTH_LONG).show();
        }
    }

   }