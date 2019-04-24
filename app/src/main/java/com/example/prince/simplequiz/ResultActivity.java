package com.example.sonu.simplequiz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ResultActivity extends AppCompatActivity {

    TextView team_name,time_taken,total_q,correct_q,wrong_q;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    int palyer_second;
    int total_time_set = 600;
    int total_question_set = 25;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        team_name=(TextView)findViewById(R.id.team_name);
        time_taken=(TextView)findViewById(R.id.time);
        total_q=(TextView)findViewById(R.id.total_q);
        correct_q=(TextView)findViewById(R.id.correct_q);
        wrong_q=(TextView)findViewById(R.id.wrong_q);

        databaseReference =  FirebaseDatabase.getInstance().getReference();

        Intent i=getIntent();
        String questions=i.getStringExtra("total");
        String correct=i.getStringExtra("correct");
        String wrong=i.getStringExtra("incorrect");
        String teamid=i.getStringExtra("teamid");
        String teamname = i.getStringExtra("teamname");
        String phonenumber = i.getStringExtra("phn_no");
        String currenttime = i.getStringExtra("currenttime");

//        String r_minutes = String.valueOf(currenttime.charAt(0) + currenttime.charAt(1));
//        String r_seconds = String.valueOf(currenttime.charAt(3) + currenttime.charAt(4));

        String[] part = currenttime.split(":");

        int seconds =  Integer.parseInt(part[0]) * 60;
        int r_r_seconds = Integer.parseInt(part[1]);

       int totalseconds = seconds + r_r_seconds;


        palyer_second = total_time_set - totalseconds;

        //Toast.makeText(ResultActivity.this, String.valueOf(palyer_second), Toast.LENGTH_LONG).show();



        TeamInfo teamInfo =  new TeamInfo(teamname,phonenumber,correct,
                String.valueOf(total_question_set - Integer.parseInt(correct)),String.valueOf(palyer_second));

        databaseReference.child(teamid).setValue(teamInfo);


        total_q.setText(String.valueOf(total_question_set));
        correct_q.setText(correct);
        wrong_q.setText(String.valueOf(total_question_set - Integer.parseInt(correct)));
        time_taken.setText(String.valueOf(palyer_second));
        team_name.setText(teamname);



    }
}
