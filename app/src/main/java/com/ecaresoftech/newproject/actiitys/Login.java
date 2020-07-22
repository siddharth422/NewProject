package com.ecaresoftech.newproject.actiitys;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.Toast;

import com.ecaresoftech.newproject.Api;
import com.ecaresoftech.newproject.MainActivity;
import com.ecaresoftech.newproject.R;
import com.ecaresoftech.newproject.interfaces.Response_Api;
import com.ecaresoftech.newproject.poja.LoginResponse;
import com.ecaresoftech.newproject.services.Api_Client;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.ResponseBody;
import okio.Buffer;
import okio.BufferedSource;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Login extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.logo)
    ImageView logo;
    @BindView(R.id.username)
    EditText username;
    @BindView(R.id.password)
    EditText password;
    @BindView(R.id.button)
    Button button;
    @BindView(R.id.progress)
    ProgressBar progress;
    @BindView(R.id.scroll)
    ScrollView scroll;
    LoginResponse loginResponse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.button)
    public void onViewClicked() {
        checkValidation();
    }

    private void checkValidation() {
        String userName,passWord;
        userName=username.getText().toString().trim();
        passWord=password.getText().toString().trim();

        if (userName.isEmpty())
        {
            username.setError("Enter Your User Name");
            username.requestFocus();
        }else if (passWord.isEmpty())
        {
            password.setError("Enter Your Password");
            password.requestFocus();
        }else {
            LoginFun(userName,passWord);
        }

    }

    private void LoginFun(String userName, String passWord) {
        final ProgressDialog progress;
        progress = new ProgressDialog(this);
        progress.setTitle("Please Wait!!");
        progress.setMessage("Wait!!");
        progress.setCancelable(true);
        progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progress.show();
        HashMap<String,String> map=new HashMap<>();
        map.put("Content-Type",getString(R.string.Content_Type));
        JsonObject jsonObject=new JsonObject();
        jsonObject.addProperty("name",userName);
        jsonObject.addProperty("pass",passWord);
        SharedPreferences sp=getSharedPreferences("Auth", MODE_PRIVATE);
        SharedPreferences.Editor ed =sp.edit();
        ed.putString("username",userName);
        ed.putString("password",passWord);
        ed.apply();

        Response_Api response_api= Api_Client.getClient(getApplicationContext()).create(Response_Api.class);
        Call<LoginResponse> call=response_api.Login("json",map,jsonObject);
        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if (response.isSuccessful())
                {
                    loginResponse=response.body();
                    SharedPreferences sharedPreferences=getSharedPreferences("Login",MODE_PRIVATE);
                    SharedPreferences.Editor editor=sharedPreferences.edit();
                    editor.putString("username",loginResponse.getCurrentUser().getName());
                    editor.putString("uid",loginResponse.getCurrentUser().getUid());
                    editor.putString("crftoken",loginResponse.getCsrfToken());
                    editor.putString("logouttoken",loginResponse.getLogoutToken());
                    editor.apply();
                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                    finish();

                }else {
                    progress.dismiss();
                    Toast.makeText(getApplicationContext(),response.message(), Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                progress.dismiss();
            }
        });



    }
}