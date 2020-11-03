package com.example.register_ku;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        final DBHelper dbHelper = new DBHelper(getApplicationContext(), "Register.db", null, 1);

        // 테이블에 있는 모든 데이터 출력
        final TextView result = (TextView) findViewById(R.id.result);

        final EditText email = (EditText) findViewById(R.id.email);
        final EditText password = (EditText) findViewById(R.id.password);



        // 회원가입 화면으로 이동
        Button register = (Button) findViewById(R.id.register);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,
                        register.class));
            }
        });


        // 로그인
        Button select = (Button) findViewById(R.id.select);
        select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String em = email.getText().toString();
                String pw = password.getText().toString();

                dbHelper.getResult (em,pw);
                if (dbHelper.getResult(em,pw) == "null"){
                    Toast.makeText(getBaseContext(), "없는 회원이거나 잘못 입력 하셨습니다. ",
                            Toast.LENGTH_SHORT).show();
                }
                else{
                    //result.setText(dbHelper.getResult(em, pw));
                    Toast.makeText(getBaseContext(), "로그인 성공 ",
                            Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(MainActivity.this,
                            real_main.class));
                }
            }
        });
    }
}
