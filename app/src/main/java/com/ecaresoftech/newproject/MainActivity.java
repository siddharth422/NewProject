package com.ecaresoftech.newproject;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.ecaresoftech.newproject.actiitys.Login;
import com.ecaresoftech.newproject.fragments.DashBoard;
import com.ecaresoftech.newproject.interfaces.Response_Api;
import com.ecaresoftech.newproject.services.Api_Client;

import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    SharedPreferences sharedPreferences, app_preferences;
    String Acitivity, username, fullname;
    public  static  String role;
    TextView name, Email;
    String decoded_user_id;
    AlertDialog.Builder alertDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        View viewHeaderView= navigationView.getHeaderView(0);
        ImageView imageView=viewHeaderView.findViewById(R.id.profilepic);
        TextView usernameheader=viewHeaderView.findViewById(R.id.name);
        TextView useremailheader=viewHeaderView.findViewById(R.id.email);
        navigationView.setNavigationItemSelectedListener(this);
        sharedPreferences=getSharedPreferences("Login", MODE_PRIVATE);
        username=sharedPreferences.getString("username","");
        decoded_user_id=sharedPreferences.getString("user_id","");
        usernameheader.setText(username);
        useremailheader.setText(decoded_user_id);

        login();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.logout) {
            alertDialog = new AlertDialog.Builder(MainActivity.this);

            // Setting Dialog Title
            alertDialog.setTitle("Logout");
            alertDialog.setIcon(R.drawable.logout);

            alertDialog.setMessage("Are you sure you want to logout?");


            // Setting Positive "Yes" Button
            alertDialog.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {

                    logout();


                }
            });
            // Setting Negative "NO" Button
            alertDialog.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    // Write your code here to invoke NO event
                    dialog.cancel();
                }
            });
            alertDialog.show();

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void logout() {
        SharedPreferences sharedPreferences=getSharedPreferences("Login",MODE_PRIVATE);
        String token =sharedPreferences.getString("logouttoken","");
        // String Copkie=sharedPreferences.getString("sesstion_name","")+"="+sharedPreferences.getString("session_id","");
        Map<String,String> map=new HashMap<>();
        map.put("Content-Type",getString(R.string.Content_Type));
        map.put("logout_token",token);

        Response_Api response_api = Api_Client.getClient(getApplicationContext()).create(Response_Api.class);
        Call<ResponseBody> call=response_api.Logout("json",map);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful())
                {

                    SharedPreferences sharedPreferences1=getSharedPreferences("Login",MODE_PRIVATE);
                    SharedPreferences.Editor editor=sharedPreferences1.edit();
                    editor.clear();
                    editor.apply();
                    startActivity(new Intent(getApplicationContext(),Login.class));
                    finish();

                }else {
                    Toast.makeText(getApplicationContext(),response.errorBody().source().buffer().clone().readString(Charset.forName("UTF-8")).toString(), Toast.LENGTH_SHORT).show();
                }

            }
            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.d("TAG", "Response = " + t.toString());
                Toast.makeText(getApplicationContext(), t.toString(), Toast.LENGTH_SHORT).show();

            }
        });


    }


    private void login() {
        if (username.equals("")) {
            Intent intent = new Intent(getApplicationContext(), Login.class);
            startActivity(intent);
            finish();
        } else {
            /*startActivity(new Intent(getApplicationContext(), TabLayout_Leads.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
            finish();*/
            if(isConnectingToInternet())
            {
                setTitle("DashBoard");
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                DashBoard fragment = new DashBoard();
                fragmentTransaction.replace(R.id.frame, fragment);
                fragmentTransaction.commit();

            }else {
                Toast.makeText(this, "Please Connect Internet", Toast.LENGTH_SHORT).show();

            }

        }

    }

    private boolean isConnectingToInternet()
    {
        ConnectivityManager cm = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo ni = cm != null ? cm.getActiveNetworkInfo() : null;
        return ni != null;

    }

}
