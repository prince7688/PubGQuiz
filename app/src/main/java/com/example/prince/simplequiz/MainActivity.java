package com.example.sonu.simplequiz;

import android.content.Intent;
import android.graphics.Color;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    public TextView mTimerView;
    public TextView mQuestion;

    private Button mButtonChoice1, mButtonChoice2, mButtonChoice3, mButtonChoice4;
    private int totalquestion = 0;
    private DatabaseReference databaseReference;
    private  FirebaseDatabase firebaseDatabase;
    private int correct = 0;
    private int wrong = 0;
    String teamname,phonenumber,teamid;
    private LinearLayout linearLayout;
    private ProgressBar progressBar;
    private int count=0;
    int total_time_set = 600;
    int total_question_set = 25;

    //private Firebase mQuestionRef, mChoice1Ref, mChoice2Ref, mChoice3Ref, mChoice4Ref, mAnswerRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTimerView = (TextView) findViewById(R.id.timerTxt);
        mQuestion = (TextView) findViewById(R.id.questionsTxt);

        mButtonChoice1 = (Button) findViewById(R.id.button1);
        mButtonChoice2 = (Button) findViewById(R.id.button2);
        mButtonChoice3 = (Button) findViewById(R.id.button3);
        mButtonChoice4 = (Button) findViewById(R.id.button4);

        progressBar =(ProgressBar)findViewById(R.id.progressBar);
        linearLayout = (LinearLayout)findViewById(R.id.activity_quiz);

         Intent teamintent = getIntent();
         teamname = teamintent.getStringExtra("teamname");
         phonenumber = teamintent.getStringExtra("phn_no");
         teamid=teamintent.getStringExtra("teamid");



        updateQuestion();

    }


    private void updateQuestion()
    {
        totalquestion++;
        if (totalquestion>total_question_set)
        {


            Intent intent = new Intent(MainActivity.this, ResultActivity.class);
            intent.putExtra("total", String.valueOf(totalquestion-1));
            intent.putExtra("correct", String.valueOf(correct));
            intent.putExtra("incorrect", String.valueOf(wrong));
            intent.putExtra("teamname",teamname);
            intent.putExtra("teamid",teamid);
            intent.putExtra("phn_no",phonenumber);
            intent.putExtra("currenttime",mTimerView.getText());
            startActivity(intent);
            finish();

        }
        else {
            databaseReference = FirebaseDatabase.getInstance().getReference().child("Questions").child(String.valueOf(totalquestion));

            ValueEventListener valueEventListener = new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull com.google.firebase.database.DataSnapshot dataSnapshot) {
                    final Question question = dataSnapshot.getValue(Question.class);
                    mQuestion.setText(question.getQuestion());
                    mButtonChoice1.setText(question.getOption1());
                    mButtonChoice2.setText(question.getOption2());
                    mButtonChoice3.setText(question.getOption3());
                    mButtonChoice4.setText(question.getOption4());
                    if(count==0) {
                        reverseTimer(total_time_set, mTimerView);
                        count = 1;
                    }
                    progressBar.setVisibility(View.GONE);
                    linearLayout.setVisibility(View.VISIBLE);
                    mButtonChoice1.setEnabled(true);
                    mButtonChoice2.setEnabled(true);
                    mButtonChoice3.setEnabled(true);
                    mButtonChoice4.setEnabled(true);



                    mButtonChoice1.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                            mButtonChoice1.setEnabled(false);
                            mButtonChoice2.setEnabled(false);
                            mButtonChoice3.setEnabled(false);
                            mButtonChoice4.setEnabled(false);

                            if (mButtonChoice1.getText().toString().equals(question.getAnswer())) {
                                mButtonChoice1.setBackgroundColor(Color.GREEN);
                                Handler handler = new Handler();
                                handler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        correct++;
                                        mButtonChoice1.setBackgroundColor(Color.parseColor("#03A9F4"));

                                        updateQuestion();

                                    }
                                }, 1500);
                            } else {
                                wrong++;
                                mButtonChoice1.setBackgroundColor(Color.RED);
//                                if (mButtonChoice2.getText().toString().equals(question.getAnswer()))
//                                    mButtonChoice2.setBackgroundColor(Color.GREEN);
//
//
//                                else if (mButtonChoice3.getText().toString().equals(question.getAnswer()))
//                                    mButtonChoice3.setBackgroundColor(Color.GREEN);
//
//
//                                else if (mButtonChoice4.getText().toString().equals(question.getAnswer()))
//                                    mButtonChoice4.setBackgroundColor(Color.GREEN);


                                Handler handler = new Handler();
                                handler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        mButtonChoice1.setBackgroundColor(getResources().getColor(R.color.background));
                                        mButtonChoice2.setBackgroundColor(getResources().getColor(R.color.background));
                                        mButtonChoice3.setBackgroundColor(getResources().getColor(R.color.background));
                                        mButtonChoice4.setBackgroundColor(getResources().getColor(R.color.background));
                                        updateQuestion();
                                    }
                                }, 1500);
                            }
                        }
                    });

                    //option2 corect


                    mButtonChoice2.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                            mButtonChoice1.setEnabled(false);
                            mButtonChoice2.setEnabled(false);
                            mButtonChoice3.setEnabled(false);
                            mButtonChoice4.setEnabled(false);

                            if (mButtonChoice2.getText().toString().equals(question.getAnswer())) {
                                mButtonChoice2.setBackgroundColor(Color.GREEN);
                                Handler handler = new Handler();
                                handler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        correct++;
                                        mButtonChoice2.setBackgroundColor(Color.parseColor("#03A9F4"));
                                        updateQuestion();

                                    }
                                }, 1500);
                            } else {
                                wrong++;
                                mButtonChoice2.setBackgroundColor(Color.RED);

//                                if (mButtonChoice1.getText().toString().equals(question.getAnswer()))
//                                    mButtonChoice1.setBackgroundColor(Color.GREEN);
//
//
//                                else if (mButtonChoice3.getText().toString().equals(question.getAnswer()))
//                                    mButtonChoice3.setBackgroundColor(Color.GREEN);
//
//
//                                else if (mButtonChoice4.getText().toString().equals(question.getAnswer()))
//                                    mButtonChoice4.setBackgroundColor(Color.GREEN);


                                Handler handler = new Handler();
                                handler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        mButtonChoice1.setBackgroundColor(getResources().getColor(R.color.background));
                                        mButtonChoice2.setBackgroundColor(getResources().getColor(R.color.background));
                                        mButtonChoice3.setBackgroundColor(getResources().getColor(R.color.background));
                                        mButtonChoice4.setBackgroundColor(getResources().getColor(R.color.background));
                                        updateQuestion();
                                    }
                                }, 1500);
                            }
                        }
                    });


                    //option3 correct


                    mButtonChoice3.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                            mButtonChoice1.setEnabled(false);
                            mButtonChoice2.setEnabled(false);
                            mButtonChoice3.setEnabled(false);
                            mButtonChoice4.setEnabled(false);

                            if (mButtonChoice3.getText().toString().equals(question.getAnswer())) {
                                mButtonChoice3.setBackgroundColor(Color.GREEN);
                                Handler handler = new Handler();
                                handler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        correct++;

                                        mButtonChoice3.setBackgroundColor(Color.parseColor("#03A9F4"));
                                        updateQuestion();

                                    }
                                }, 1500);
                            } else {
                                wrong++;
                                mButtonChoice3.setBackgroundColor(Color.RED);

//                                if (mButtonChoice2.getText().toString().equals(question.getAnswer()))
//                                    mButtonChoice2.setBackgroundColor(Color.GREEN);
//
//
//                                else if (mButtonChoice1.getText().toString().equals(question.getAnswer()))
//                                    mButtonChoice1.setBackgroundColor(Color.GREEN);
//
//
//                                else if (mButtonChoice4.getText().toString().equals(question.getAnswer()))
//                                    mButtonChoice4.setBackgroundColor(Color.GREEN);


                                Handler handler = new Handler();
                                handler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        mButtonChoice1.setBackgroundColor(getResources().getColor(R.color.background));
                                        mButtonChoice2.setBackgroundColor(getResources().getColor(R.color.background));
                                        mButtonChoice3.setBackgroundColor(getResources().getColor(R.color.background));
                                        mButtonChoice4.setBackgroundColor(getResources().getColor(R.color.background));
                                        updateQuestion();
                                    }
                                }, 1500);
                            }
                        }
                    });


                    //option4 correct


                    mButtonChoice4.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                            mButtonChoice1.setEnabled(false);
                            mButtonChoice2.setEnabled(false);
                            mButtonChoice3.setEnabled(false);
                            mButtonChoice4.setEnabled(false);

                            if (mButtonChoice4.getText().toString().equals(question.getAnswer())) {
                                mButtonChoice4.setBackgroundColor(Color.GREEN);
                                Handler handler = new Handler();
                                handler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        correct++;
                                        mButtonChoice4.setBackgroundColor(Color.parseColor("#03A9F4"));
                                        updateQuestion();

                                    }
                                }, 1500);
                            } else {
                                wrong++;
                                mButtonChoice4.setBackgroundColor(Color.RED);

//                                if (mButtonChoice2.getText().toString().equals(question.getAnswer()))
//                                    mButtonChoice2.setBackgroundColor(Color.GREEN);
//
//
//                                else if (mButtonChoice3.getText().toString().equals(question.getAnswer()))
//                                    mButtonChoice3.setBackgroundColor(Color.GREEN);
//
//
//                                else if (mButtonChoice1.getText().toString().equals(question.getAnswer()))
//                                    mButtonChoice1.setBackgroundColor(Color.GREEN);


                                Handler handler = new Handler();
                                handler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        mButtonChoice1.setBackgroundColor(getResources().getColor(R.color.background));
                                        mButtonChoice2.setBackgroundColor(getResources().getColor(R.color.background));
                                        mButtonChoice3.setBackgroundColor(getResources().getColor(R.color.background));
                                        mButtonChoice4.setBackgroundColor(getResources().getColor(R.color.background));
                                        updateQuestion();
                                    }
                                }, 1500);
                            }
                        }
                    });
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            };

          databaseReference.addListenerForSingleValueEvent(valueEventListener);


        }
    }



    public void reverseTimer(int seconds, final TextView tv)
    {
        new CountDownTimer(seconds* 1000+1000,1000) {

            public void onTick(long multliFnished) {

                int seconds = (int) (multliFnished / 1000);
                int minutes = seconds / 60;
                seconds = seconds % 60;
                tv.setText(String.format("%02d", minutes) + ":" + String.format("%02d", seconds));

            }

            public void onFinish() {
                if(totalquestion<=total_question_set) {
                    tv.setText("Completed");
                    Intent intent = new Intent(MainActivity.this, ResultActivity.class);
                    intent.putExtra("total", String.valueOf(totalquestion - 1));
                    intent.putExtra("correct", String.valueOf(correct));
                    intent.putExtra("incorrect", String.valueOf(wrong));
                    intent.putExtra("teamname", teamname);
                    intent.putExtra("teamid",teamid);
                    intent.putExtra("phn_no", phonenumber);
                    intent.putExtra("currenttime","00:00");
                    startActivity(intent);
                    finish();
                }
            }

        }.start();

    }



}


























