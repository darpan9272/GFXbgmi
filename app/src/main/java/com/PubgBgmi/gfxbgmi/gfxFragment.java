package com.PubgBgmi.gfxbgmi;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.text.NoCopySpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.Fragment;


import com.PubgBgmi.gfxbgmi.databinding.FragmentGfxBinding;
import com.startapp.sdk.adsbase.Ad;
import com.startapp.sdk.adsbase.StartAppAd;
import com.startapp.sdk.adsbase.adlisteners.AdEventListener;
import com.startapp.sdk.adsbase.adlisteners.VideoListener;
import com.unity3d.ads.IUnityAdsInitializationListener;
import com.unity3d.ads.IUnityAdsLoadListener;
import com.unity3d.ads.IUnityAdsShowListener;
import com.unity3d.ads.UnityAds;
import com.unity3d.ads.UnityAdsShowOptions;

import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;

public class gfxFragment extends Fragment  {

    private static ProgressDialog progressDialog;
    private static final Timer _timer = new Timer();
    private static TimerTask timerTask;
    FragmentGfxBinding binding;



    // Reward Unity End



    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

            binding = FragmentGfxBinding.inflate(inflater, container, false);

            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);





       binding.radiogroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
           @Override
           public void onCheckedChanged(RadioGroup group, int checkedId) {


               if(binding.googlePlay.isChecked()){

                   binding.india.setChecked(false);
                   binding.tw.setChecked(false);
                   binding.globalBeta.setChecked(false);
                   binding.china.setChecked(false);
                   binding.vn.setChecked(false);

               }
               if(binding.kr.isChecked()){

                   binding.india.setChecked(false);
                   binding.tw.setChecked(false);
                   binding.globalBeta.setChecked(false);
                   binding.china.setChecked(false);
                   binding.vn.setChecked(false);

               }

               if(binding.lite.isChecked()){

                   binding.india.setChecked(false);
                   binding.tw.setChecked(false);
                   binding.globalBeta.setChecked(false);
                   binding.china.setChecked(false);
                   binding.vn.setChecked(false);

               }


               StartAppAd.showAd(getContext());

               Intent intent = new Intent(getContext(), items.class);
               startActivity(intent);
           }
       });

        binding.radiogroup1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                if (binding.india.isChecked()) {

                    binding.googlePlay.setChecked(false);
                    binding.kr.setChecked(false);
                    binding.lite.setChecked(false);
                    binding.china.setChecked(false);
                    binding.vn.setChecked(false);

                }

                if (binding.tw.isChecked()) {

                    binding.googlePlay.setChecked(false);
                    binding.kr.setChecked(false);
                    binding.lite.setChecked(false);
                    binding.china.setChecked(false);
                    binding.vn.setChecked(false);

                }

                if (binding.globalBeta.isChecked()) {

                    binding.googlePlay.setChecked(false);
                    binding.kr.setChecked(false);
                    binding.lite.setChecked(false);
                    binding.china.setChecked(false);
                    binding.vn.setChecked(false);

                }

                StartAppAd.showAd(getContext());

                Intent intent = new Intent(getContext(), items.class);
                startActivity(intent);
            }
        });
        binding.radiogroup2.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                if(binding.china.isChecked()){

                    binding.googlePlay.setChecked(false);
                    binding.kr.setChecked(false);
                    binding.lite.setChecked(false);
                    binding.india.setChecked(false);
                    binding.tw.setChecked(false);
                    binding.globalBeta.setChecked(false);
                }

                if(binding.vn.isChecked()){

                    binding.googlePlay.setChecked(false);
                    binding.kr.setChecked(false);
                    binding.lite.setChecked(false);
                    binding.india.setChecked(false);
                    binding.tw.setChecked(false);
                    binding.globalBeta.setChecked(false);
                }



                StartAppAd.showAd(getContext());

                Intent intent = new Intent(getContext(), items.class);
                startActivity(intent);


            }
        });

        binding.btnEarn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(getContext(), "Wait For Next Update ", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(getActivity(), earnsection.class);
               startActivity(intent);

            }
        });

        binding.insta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StartAppAd.showAd(getActivity());


            }
        });

        binding.rate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("https://play.google.com/store/apps/details?id=com.PubgBgmi.gfxbgmi");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            }
        });



        binding.accept.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {

                gfxFragment.progressDialog = new ProgressDialog(getContext());
                gfxFragment.progressDialog.setTitle("Activating...");
                gfxFragment.progressDialog.setMessage("Please wait...");
                gfxFragment.progressDialog.setProgress(99);
                gfxFragment.progressDialog.setCancelable(false);
                gfxFragment.progressDialog.setCanceledOnTouchOutside(false);
                gfxFragment.progressDialog.setProgressStyle(0);
                gfxFragment.progressDialog.show();
                StartAppAd video = new StartAppAd(requireContext());
                video.setVideoListener(new VideoListener() {
                    @Override
                    public void onVideoCompleted() {

                        timerTask = new TimerTask() {
                            public void run() {
                                requireActivity().runOnUiThread(new Runnable() {
                                    public void run() {
                                       progressDialog.dismiss();

                                        androidx.appcompat.app.AlertDialog.Builder builder1 = new androidx.appcompat.app.AlertDialog.Builder(requireActivity());
                                        builder1.setMessage("Activated Successful");
                                        builder1.setCancelable(true);

                                        builder1.setPositiveButton(
                                                "DONE",
                                                new DialogInterface.OnClickListener() {
                                                    public void onClick(DialogInterface dialog, int id) {
//                                                        Intent intent = new Intent(earn_section.this, earn_section.class);
//                                                        startActivity(intent);

                                                    }
                                                });
                                        androidx.appcompat.app.AlertDialog alert11 = builder1.create();
                                        alert11.show();
                                    }
                                });

                            }

                        };

                        _timer.schedule(timerTask, 1000);


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



       return binding.getRoot();



    }







}