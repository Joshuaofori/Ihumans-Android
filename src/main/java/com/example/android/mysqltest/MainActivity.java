package com.example.android.mysqltest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    EditText username,password;//autheticatting;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        username=(EditText)findViewById(R.id.username);
        password=(EditText)findViewById(R.id.password);
        //autheticatting=(EditText)findViewById(R.id.username);

    }
    public void login(View view){
        String user=username.getText().toString();
        String pas=password.getText().toString();
        //String auth=autheticatting.getText().toString();

        Background  bg= new Background(this);
        bg.execute(user,pas);

       Log.d("mymessage",bg.message);
       if(bg.message.equals("login successfully...!")){
           Intent intent=new Intent(this,Main2Activity.class);
           startActivity(intent);
       }


    }
}
