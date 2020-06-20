package com.ecaresoftech.newproject.actiitys;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.ScrollView;

import com.ecaresoftech.newproject.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

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



    }
}