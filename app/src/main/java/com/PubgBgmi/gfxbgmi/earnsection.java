package com.PubgBgmi.gfxbgmi;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.cardview.widget.CardView;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.google.android.material.dialog.MaterialAlertDialogBuilder;
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

public class earnsection extends AppCompatActivity {

    TextView textView, textView2;
    Button btn;

    CardView cardview;
    String UID;
    ProgressDialog progressDialog;

    TimerTask timerTask;
    Timer _timer = new Timer();

    //   InterstitialAd minterstitialAd;
    int mCounter;






    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_earnsection);

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        textView = findViewById(R.id.text);
        textView2 = findViewById(R.id.textview2);
        btn = findViewById(R.id.button);
        cardview = findViewById(R.id.cardview);



        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                earnsection.this.progressDialog = new ProgressDialog(earnsection.this);
                earnsection.this.progressDialog.setTitle("Ads Loading...");
                earnsection.this.progressDialog.setMessage("Please wait For 5 Seconds...");
                earnsection.this.progressDialog.setProgress(99);
                earnsection.this.progressDialog.setCancelable(false);
                earnsection.this.progressDialog.setCanceledOnTouchOutside(false);
                earnsection.this.progressDialog.setProgressStyle(0);
                earnsection.this.progressDialog.show();

                StartAppAd video = new StartAppAd(earnsection.this);
                video.setVideoListener(new VideoListener() {
                    @Override
                    public void onVideoCompleted() {

                        SharedPreferences preferences = getSharedPreferences("coin" , MODE_PRIVATE);
                        int i = preferences.getInt("point", 50);
                        mCounter  = i+5;
                        textView.setText(Integer.toString(mCounter));
                        SharedPreferences.Editor editor = preferences.edit();
                        editor.putInt("point" , mCounter);
                        editor.apply();

                        earnsection.this.timerTask = new TimerTask() {
                            public void run() {
                                earnsection.this.runOnUiThread(new Runnable() {
                                    public void run() {
                                        earnsection.this.progressDialog.dismiss();

                                        androidx.appcompat.app.AlertDialog.Builder builder1 = new androidx.appcompat.app.AlertDialog.Builder(earnsection.this);
                                        builder1.setMessage("5 Coins Added Successful");
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

                        earnsection.this._timer.schedule(earnsection.this.timerTask, 1000);




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

                btn.setEnabled(false);
                btn.postDelayed(new Runnable() {
                    public void run() {
                        btn.setEnabled(true);
                        Log.d(TAG,"Earn Button Enable");
                    }
                },5000);

            }
        });


        cardview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mCounter >= 1000) {
                    Toast.makeText(earnsection.this, "You have win", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(earnsection.this, "You have not complete coin", Toast.LENGTH_SHORT).show();
                }
            }
        });


        SharedPreferences getshared = getSharedPreferences("coin", MODE_PRIVATE);
        Integer value = getshared.getInt("point", 50);
        textView.setText(Integer.toString(value));



        final SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        boolean agreed = sharedPreferences.getBoolean("agreed", false);
        if (!agreed) {
            new MaterialAlertDialogBuilder(earnsection.this, R.color.golden)

                    .setTitle("Terms & Conditions")
                    .setPositiveButton("agreed", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            editor.putBoolean("agreed", true);
                            editor.commit();
                        }
                    })
                    .setMessage("By downloading or using the app, these terms will automatically apply to you – you should make sure therefore that you read them carefully before using the app. You’re not allowed to copy or modify the app, any part of the app, or our trademarks in any way. You’re not allowed to attempt to extract the source code of the app, and you also shouldn’t try to translate the app into other languages or make derivative versions. The app itself, and all the trademarks, copyright, database rights, and other intellectual property rights related to it, still belong to Kshtbhanjan Infotech. \n" +
                            "\n" +
                            "Kashtbhanjan Infotech is committed to ensuring that the app is as useful and efficient as possible. For that reason, we reserve the right to make changes to the app or to charge for its services, at any time and for any reason. We will never charge you for the app or its services without making it very clear to you exactly what you’re paying for. \n" +
                            "\n" +
                            "The Earn Redeem Code - Watch Ads app stores and processes personal data that you have provided to us, to provide my Service. It’s your responsibility to keep your phone and access to the app secure. We therefore recommend that you do not jailbreak or root your phone, which is the process of removing software restrictions and limitations imposed by the official operating system of your device. It could make your phone vulnerable to malware/viruses/malicious programs, compromise your phone’s security features and it could mean that the Earn Redeem Code - Watch Ads app won’t work properly or at all. \n" +
                            "\n" +
                            "We may make any changes within this app without your permit such as withdrawal prescribed amount, withdrawal prescribed coin, Task complete coin, and etc.\n" +
                            "\n" +
                            "We do not Stored any data and coin online our app users, all data is stored on the user's device, So we warn app users that you should not uninstall the app or clear data, if you do this then you will lose all your earned coins and you will never get them back, we can't help you with that, And for this you will be responsible because we warned you.If you provide us with any proof after uninstall or clear data, we are not obliged to give withdrawal.\n" +
                            "\n" +
                            "Dollar from coin, Example 1000 coin =10 Dollar but 999.99 coin ≠ 10 Dollar because 999.99 = 0 Dollar This means your earned coins are worth 0 Dollar as long as you are earning equal to our prescribed coins. This is how our app and we calculate Dollar from coin.You get 0 Dollar from us till you earn equal to prescribed coins.\n" +
                            "\n" +
                            "1000 coins required for withdrawal You cannot claim 10 or 9.90 Dollar from us by earning 999.99 coins. Because we said earlier that if your earned coins are not equal to our fixed coins then your earned coins will be worth 0 Dollar.\n" +
                            "\n" +
                            "No one gets paid if our ad network is suspended because we earn from that and then give you a portion of that revenue.\n" +
                            "\n" +
                            "No one will get paid if i die because i manage the app alone.\n" +
                            "\n" +
                            "You will get more coins for completing a task up to a certain limit, less coins when you equal or exceed our set certain limit.\n" +
                            "\n" +
                            "You use any hack or trick then your withdrawal cancelled.\n" +
                            "\n" +
                            "If a new version of our app has been released and you have not updated and requested a withdrawal from an older version, we may cancel your withdrawal. Please update our app in time.\n" +
                            "\n" +
                            "The app does use third-party services that declare their Terms and Conditions. \n" +
                            "\n" +
                            "Link to Terms and Conditions of third-party service providers used by the app \n" +
                            "\n" +
                            "Google Play Services\n" +
                            "\n" +
                            "Unity\n" +
                            "\n" +
                            "You should be aware that there are certain things that Kashtbhanjan Infotech will not take responsibility for. Certain functions of the app will require the app to have an active internet connection. The connection can be Wi-Fi or provided by your mobile network provider, but Kashtbhanjan Infotech cannot take responsibility for the app not working at full functionality if you don’t have access to Wi-Fi, and you don’t have any of your data allowance left. \n" +
                            "\n" +
                            "If you’re using the app outside of an area with Wi-Fi, you should remember that the terms of the agreement with your mobile network provider will still apply. As a result, you may be charged by your mobile provider for the cost of data for the duration of the connection while accessing the app, or other third-party charges. In using the app, you’re accepting responsibility for any such charges, including roaming data charges if you use the app outside of your home territory (i.e. region or country) without turning off data roaming. If you are not the bill payer for the device on which you’re using the app, please be aware that we assume that you have received permission from the bill payer for using the app. \n" +
                            "\n" +
                            "Along the same lines, Kashtbhnjan Infotech cannot always take responsibility for the way you use the app i.e. You need to make sure that your device stays charged – if it runs out of battery and you can’t turn it on to avail the Service, Kashtbhanjan Infotech cannot accept responsibility. \n" +
                            "\n" +
                            "With respect to Kashtbhanjan Infotech’s responsibility for your use of the app, when you’re using the app, it’s important to bear in mind that although we endeavor to ensure that it is updated and correct at all times, we do rely on third parties to provide information to us so that we can make it available to you. Kashtbhanjan Infotech accepts no liability for any loss, direct or indirect, you experience as a result of relying wholly on this functionality of the app. \n" +
                            "\n" +
                            "At some point, we may wish to update the app. The app is currently available on Android – the requirements for the system(and for any additional systems we decide to extend the availability of the app to) may change, and you’ll need to download the updates if you want to keep using the app. Kashtbhanjan Infotech does not promise that it will always update the app so that it is relevant to you and/or works with the Android version that you have installed on your device. However, you promise to always accept updates to the application when offered to you, We may also wish to stop providing the app, and may terminate use of it at any time without giving notice of termination to you. Unless we tell you otherwise, upon any termination, (a) the rights and licenses granted to you in these terms will end; (b) you must stop using the app, and (if needed) delete it from your device. \n" +
                            "\n" +
                            "Changes to This Terms and Conditions\n" +
                            "\n" +
                            "I may update our Terms and Conditions from time to time. Thus, you are advised to review this page periodically for any changes. I will notify you of any changes by posting the new Terms and Conditions on this page. \n" +
                            "\n" +
                            "These terms and conditions are effective as of 2023-07-02 \n" +
                            "\n" +
                            "Contact Us\n" +
                            "\n" +
                            "If you have any questions or suggestions about my Terms and Conditions, do not hesitate to contact me at kashtbhanjaninfotech@gmail.com. \n" +
                            "\n" +
                            "Click I Agree if you agree to our term's & condition.")
                    .show();
        }

        textView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new MaterialAlertDialogBuilder(earnsection.this, R.color.golden)
                        .setMessage("1. Because You have Watched Maximum number of Videos.\n" +
                                "\n" +
                                "2. Because in your country maximum number of video reach.\n" +
                                "\n" +
                                "3. In you country video service not available.\n" +
                                "\n" +
                                "How To Fix This Problem\n" +
                                "\n" +
                                "1. Download Halo VPN App.\n" +
                                "\n" +
                                "2. Connect to USA Server.\n" +
                                "\n" +
                                "3. Then Restart This App.\n" +
                                "\n" +
                                "4. Wait for a minute, video will load\n" +
                                "\n" +
                                "successfully.\n" +
                                "\n" +
                                "Note\n" +
                                "\n" +
                                "If video loading without VPN, Then use this App without VPN, If video is not loading then use VPN App.")
                        .show();
            }
        });

        final SharedPreferences sharedPreferences1= PreferenceManager.getDefaultSharedPreferences(this);
        boolean agreed1 = sharedPreferences1.getBoolean("agreed", false);
        if (!agreed1) {

            AlertDialog.Builder alertDialog = new AlertDialog.Builder(earnsection.this);
            alertDialog.setTitle("UID");
            alertDialog.setMessage("Enter UID");

            final EditText input = new EditText(earnsection.this);
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.MATCH_PARENT);
            input.setLayoutParams(lp);
            alertDialog.setView(input);


            alertDialog.setPositiveButton("YES",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            if (UID == null) {

                                Toast.makeText(getApplicationContext(),
                                        "UID Fatched", Toast.LENGTH_SHORT).show();


                            } else {
                                Toast.makeText(getApplicationContext(),
                                        "Please Enter Your UID!", Toast.LENGTH_SHORT).show();
                            }
                        }

                    });

            alertDialog.setNegativeButton("NO",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });

            alertDialog.show();
        }

    }





}


