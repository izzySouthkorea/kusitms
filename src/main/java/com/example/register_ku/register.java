package com.example.register_ku;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class register extends AppCompatActivity {
    public void onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        final DBHelper dbHelper = new DBHelper(getApplicationContext(), "Register.db", null, 1);

        final EditText name = (EditText) findViewById(R.id.user_name);
        final EditText email = (EditText) findViewById(R.id.email);
        final EditText password = (EditText) findViewById(R.id.password);


        Button insert = (Button) findViewById(R.id.insert);
        Button check = (Button) findViewById(R.id.checkbutton);
        check.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                String em = email.getText().toString();

                dbHelper.CheckEmail(em);
                if (dbHelper.CheckEmail(em) == 1){
                    Toast.makeText(getBaseContext(), "이미 가입 된 이메일 주소 입니다.",
                            Toast.LENGTH_SHORT).show();
                }
                else if (dbHelper.CheckEmail(em) == 0){


                    Toast.makeText(getBaseContext(), "사용할 수 있는 이메일 주소 입니다.",
                            Toast.LENGTH_SHORT).show();

                }
            }
        });
        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String na = name.getText().toString();
                String em = email.getText().toString();
                String pw = password.getText().toString();

                dbHelper.CheckEmail(em);
                if (dbHelper.CheckEmail(em) == 1){
                    Toast.makeText(getBaseContext(), "이미 가입 된 이메일 주소로는 가입할 수 없습니다.",
                            Toast.LENGTH_SHORT).show();
                }
                else if (dbHelper.CheckEmail(em) == 0){
                    dbHelper.insert(na, em, pw);

                    Toast.makeText(getBaseContext(), "회원가입 완료",
                            Toast.LENGTH_SHORT).show();

                    startActivity(new Intent(register.this, MainActivity.class));
                }

            }
        });
    }
}
