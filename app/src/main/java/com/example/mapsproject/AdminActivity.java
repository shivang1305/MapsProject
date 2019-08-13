package com.example.mapsproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AdminActivity extends AppCompatActivity {

    EditText tno,date,userid;
    Button btn_search;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        tno=findViewById(R.id.admin_tno);
        date=findViewById(R.id.admin_date);
        userid=findViewById(R.id.admin_userid);

        btn_search=findViewById(R.id.adminbtnsearch);
        btn_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyData d=new MyData();
                boolean s=d.searchlocations(tno.getText().toString(),userid.getText().toString(),date.getText().toString());
                if(s==true)
                {
                    Toast.makeText(AdminActivity.this, "Locations found", Toast.LENGTH_SHORT).show();
                    Intent in=new Intent(AdminActivity.this,AdminMapActivity.class);
                    in.putExtra("tno",""+tno.getText().toString());
                    in.putExtra("userid",""+userid.getText().toString());
                    in.putExtra("date",""+date.getText().toString());
                    startActivity(in);
                }
                else if(s==false)
                    Toast.makeText(AdminActivity.this, "No Locations found on given Taxi No and Date", Toast.LENGTH_SHORT).show();

            }
        });
    }
}
