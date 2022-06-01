package com.example.managementx2;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.managementx2.beans.User;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

public class RegisterActivity extends AppCompatActivity {

    private EditText usernameEt;
    private EditText passwdEt;
    private EditText rePasswdEt;
    private Button registerBtn;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Bmob.initialize(this, "cc617f5b15d0a881f15b80c4f9dd3cd1");

        ActionBar bar = getSupportActionBar();
        if (bar != null){
            bar.hide();
        }

        usernameEt = (EditText) findViewById(R.id.edittext_account);
        passwdEt = (EditText) findViewById(R.id.edittext_password);
        rePasswdEt = (EditText) findViewById(R.id.edittext_password_conform);
        registerBtn = (Button) findViewById(R.id.register_btn);

        User user = new User();


        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String username = usernameEt.getText().toString();
                String passwd = passwdEt.getText().toString();
                String rePasswd = rePasswdEt.getText().toString();

                if (username.isEmpty()){
                    Toast.makeText(RegisterActivity.this, "请输入用户名", Toast.LENGTH_SHORT).show();
                }else if (passwd.isEmpty()){
                    Toast.makeText(RegisterActivity.this, "请输入密码", Toast.LENGTH_SHORT).show();
                }else if (rePasswd.isEmpty()){
                    Toast.makeText(RegisterActivity.this, "请再次输入密码！", Toast.LENGTH_SHORT).show();
                }else if (!(passwd.equals(rePasswd))) {
                    Toast.makeText(RegisterActivity.this, "两次密码不一致！", Toast.LENGTH_SHORT).show();
                }else {
                    user.setUsername(username);
                    user.setPassword(passwd);
                    user.signUp(new SaveListener<Object>() {
                        @Override
                        public void done(Object o, BmobException e) {
                            if (e == null){
                                Toast.makeText(RegisterActivity.this, "注册成功！", Toast.LENGTH_SHORT).show();
                                finish();
                            }else {
                                Log.d("register", e.getMessage());
                            }
                        }
                    });
                }




            }
        });


    }
}
