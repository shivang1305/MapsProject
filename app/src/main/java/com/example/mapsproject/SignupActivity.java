package com.example.mapsproject;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mapsproject.ui.login.LoginActivity;

public class SignupActivity extends AppCompatActivity {

    Button btn_register;
    EditText userid,name,pass,confirmpass,tno,pno;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        userid=findViewById(R.id.s_usernm);
        name=findViewById(R.id.s_name);
        pass=findViewById(R.id.s_pass);
        confirmpass=findViewById(R.id.s_cpass);
        tno=findViewById(R.id.s_taxino);
        pno=findViewById(R.id.s_pno);
        btn_register=findViewById(R.id.register_btn);
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if((userid.getText().toString().compareTo(""))==0 ||(pass.getText().toString().compareTo(""))==0||(confirmpass.getText().toString().compareTo(""))==0||(tno.getText().toString().compareTo(""))==0||(pno.getText().toString().compareTo(""))==0)
                    Toast.makeText(SignupActivity.this, "Please fill all the required fields", Toast.LENGTH_SHORT).show();
                else
                {
                    MyData md = new MyData();
                    String s=md.Signup(userid.getText().toString(),name.getText().toString(),pass.getText().toString(),tno.getText().toString(),pno.getText().toString());
                    Toast.makeText(SignupActivity.this, "Data is saved successfully", Toast.LENGTH_SHORT).show();
                    Intent in=new Intent(SignupActivity.this, LoginActivity.class);
                    startActivity(in);
                }
            }
        });
    }
}
