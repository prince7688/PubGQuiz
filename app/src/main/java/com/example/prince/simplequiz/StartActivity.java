package com.example.sonu.simplequiz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class StartActivity extends AppCompatActivity {

    EditText ed1,ed2,ed3,edid;
    Button btn;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    String teamid,teamname, phonenumber , password, passwrd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        edid=(EditText)findViewById(R.id.edid);
        ed1=(EditText)findViewById(R.id.ed1);
        ed2=(EditText)findViewById(R.id.ed2);
        ed3=(EditText)findViewById(R.id.ed3);
        btn=(Button)findViewById(R.id.btn);


        databaseReference =  FirebaseDatabase.getInstance().getReference();

        ValueEventListener postListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                passwrd = dataSnapshot.child("password").getValue(String.class);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Getting Post failed, log a message
            }
        };
        databaseReference.addValueEventListener(postListener);



        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                teamid=edid.getText().toString();
                teamname=ed1.getText().toString();
                phonenumber=ed2.getText().toString();
                password=ed3.getText().toString();

                if(teamid.equals(""))
                    Toast.makeText(StartActivity.this,"Team id can't be Empty",Toast.LENGTH_LONG).show();

                else if(teamname.equals(""))
                    Toast.makeText(StartActivity.this,"Team name can't be Empty",Toast.LENGTH_LONG).show();

                else if(phonenumber.equals("") || !TextUtils.isDigitsOnly(phonenumber) || phonenumber.length()!=10 )
                    Toast.makeText(StartActivity.this,"Please Enter the valid mobile number",Toast.LENGTH_LONG).show();

                else if(!password.equals(passwrd) || password.equals(""))
                    Toast.makeText(StartActivity.this,"Please Enter the correct password",Toast.LENGTH_LONG).show();

                else {

                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    intent.putExtra("teamid",teamid);
                    intent.putExtra("teamname", teamname);
                    intent.putExtra("phn_no", phonenumber);
                    startActivity(intent);

                    finish();
                }
            }
        });
    }
}
