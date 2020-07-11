package com.ecaresoftech.newproject;

import android.content.Intent;
import android.os.Build;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.WindowManager;

import com.ecaresoftech.newproject.fragments.LiftRegister;
import com.ecaresoftech.newproject.fragments.ListLifts;

public class DetailActivity extends AppCompatActivity {
    FragmentTransaction fragmentTransaction;
    FragmentManager fragmentManager;
    Intent intent;
    String activity,uid;
    public static Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            // View decor = getWindow().getDecorView();
            //  decor.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            getWindow().setStatusBarColor(getResources().getColor(R.color.colorPrimaryDark));
        }
        setContentView(R.layout.activity_detail);
        toolbar=findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        setSupportActionBar(toolbar);
        intent=getIntent();
        activity=intent.getStringExtra("open");
        switch (activity)
        {
            case "LiftRegister":
                setTitle("Lift Register");
                fragmentManager = getSupportFragmentManager();
                fragmentTransaction = fragmentManager.beginTransaction();
                LiftRegister fragment = new LiftRegister();
                fragmentTransaction.replace(R.id.framelayout, fragment);
                fragmentTransaction.commit();
                break;

            case"listlift":
                setTitle("Lift List");
                fragmentManager = getSupportFragmentManager();
                fragmentTransaction = fragmentManager.beginTransaction();
                ListLifts listLifts = new ListLifts();
                fragmentTransaction.replace(R.id.framelayout, listLifts);
                fragmentTransaction.commit();
                break;



        }
    }
}