package com.example.msuba.andre_login.Onboarding;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.msuba.andre_login.Adapters.UserCreds;
import com.example.msuba.andre_login.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignUp extends AppCompatActivity
{
    DatabaseReference fb_db;
    EditText username,email,pass;
    String Uname,Umail,Upass;
    Button signupbut,login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        fb_db = FirebaseDatabase.getInstance().getReference();
        username = (EditText)findViewById(R.id.username);
        email=(EditText)findViewById(R.id.email);
        pass = (EditText)findViewById(R.id.pass);
        signupbut = (Button)findViewById(R.id.signupbut);
        login = (Button)findViewById(R.id.Login);
        signupbut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uname = username.getText().toString();
                Umail = email.getText().toString().replace(".","_");
                Upass = pass.getText().toString();
                UserCreds userCreds = new UserCreds();
                userCreds.setEmail(Umail);
                userCreds.setUname(Uname);
                userCreds.setPass(Upass);
                fb_db.child("UserCreds").child(Umail).setValue(userCreds);
                Toast.makeText(getApplicationContext(),"Successfully Sign up",Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getApplicationContext(),Login.class));

            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),Login.class));
            }
        });
    }
}
