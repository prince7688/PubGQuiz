package com.example.sonu.simplequiz;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.LinearLayout;

public class first extends AppCompatActivity {

    LinearLayout linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);

        linearLayout = (LinearLayout)findViewById(R.id.lnlayout);

//        Point size = new Point();
//
//        Bitmap bmp = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(
//                getResources(),R.drawable.main),size.x,size.y,true);

        linearLayout.setBackground(getDrawable(R.drawable.pubg));

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent mainIntent = new Intent(first.this, StartActivity.class);
                startActivity(mainIntent);
                finish();
            }
        }, 2000);
    }
}
