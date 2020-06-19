package com.ecaresoftech.newproject.actiitys;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;

import com.ecaresoftech.newproject.MainActivity;
import com.ecaresoftech.newproject.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Splash extends AppCompatActivity {

    @BindView(R.id.image)
    ImageView image;
    String baseurl,theme,logo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);
        check();

    }

    private void check() {

            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    if (isConnectingToInternet()) {
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        intent.putExtra("open", "Home");
                        startActivity(intent);
                        finish();

                    } else {
                        Toast.makeText(Splash.this, "Please Connect Internet", Toast.LENGTH_SHORT).show();

                    }

                }
            }, 2000);



    }

    private boolean isConnectingToInternet() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo ni = cm != null ? cm.getActiveNetworkInfo() : null;
        return ni != null;
    }
}
