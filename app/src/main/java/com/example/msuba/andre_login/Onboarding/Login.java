package com.example.msuba.andre_login.Onboarding;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.msuba.andre_login.Adapters.UserCreds;
import com.example.msuba.andre_login.Landing.Landing;
import com.example.msuba.andre_login.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Login extends AppCompatActivity
{
    EditText email,pass;
    String UEmail,Upass;
    Button logbut;
    DatabaseReference fb_db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        email = (EditText)findViewById(R.id.Umail);
        pass = (EditText)findViewById(R.id.Pass);
        logbut = (Button)findViewById(R.id.logbut);
        fb_db = FirebaseDatabase.getInstance().getReference().child("UserCreds");
        logbut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UEmail = email.getText().toString();
                Upass = pass.getText().toString();
                new MyTask().execute();
            }
        });
    }
    class MyTask extends AsyncTask<String,Integer,String>{

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            System.out.println("MYTASK INVOKED");


        }
        @Override
        protected String doInBackground(String... strings) {
            System.out.println("in DIB "+fb_db.toString());


            fb_db.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    System.out.println("b4 FOR  "+dataSnapshot.getKey());
                    System.out.println("b4 FOR  "+dataSnapshot.getChildrenCount());
                    System.out.println("b4 FOR  "+dataSnapshot.getChildren());

                    for (DataSnapshot postsnapshot : dataSnapshot.getChildren())
                    {
                        System.out.println("outside Umail is "+postsnapshot.getKey());

                        if (postsnapshot.getKey().equals(UEmail.replace(".","_")))
                        {
                            System.out.println("inside  Umail is "+postsnapshot.getKey());
                            UserCreds userCreds = postsnapshot.getValue(UserCreds.class);
                            if(userCreds.getEmail().equals(UEmail.replace(".","_"))&&userCreds.getPass().equals(Upass))
                            {
                                Toast.makeText(getApplicationContext(),"Welcome "+userCreds.getUname(),Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(getApplicationContext(), Landing.class));
                                break;
                            }
                            else
                            {
                                Toast.makeText(getApplicationContext(),"Invalid Credentials ",Toast.LENGTH_SHORT).show();
                                break;
                            }
                        }
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
            return null;
        }



        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
        }
    }
}
