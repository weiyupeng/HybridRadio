package com.example.weiyupeng.hybridradio;

import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("MainActivity", "onCreate called");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        matchScreenSize();
        View view = findViewById(R.id.main);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("Hello","hello");
                Intent intent = new Intent(view.getContext(),HomePageActivity.class);
                startActivity(intent);
            }
        });
    }


    // set the size before render screen
    void matchScreenSize(){
        if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
            int width = getResources().getDisplayMetrics().widthPixels;
            CardView cardView = (CardView) findViewById(R.id.HomepageCardView);
            ConstraintLayout.LayoutParams params = (ConstraintLayout.LayoutParams) cardView.getLayoutParams();
            params.width = width/2;
            cardView.setLayoutParams(params);

            ImageView logoView = (ImageView) findViewById(R.id.imageView_radio_dns_log);
            params = (ConstraintLayout.LayoutParams) logoView.getLayoutParams();
            params.width = width/6;
            logoView.setLayoutParams(params);

            //for text, can directly set the sizef for text
            TextView poweredByTextView = (TextView) findViewById(R.id.textView_PoweredBy);
            poweredByTextView.setTextSize(width/120);

            TextView DesignedByTextView = (TextView) findViewById(R.id.textView_DesignedBy);
            DesignedByTextView.setTextSize(width/120);

        }
        else{
            int height = getResources().getDisplayMetrics().heightPixels;

            //get the view paras, then set it to the view
            CardView cardView = (CardView) findViewById(R.id.HomepageCardView);
            ConstraintLayout.LayoutParams params = (ConstraintLayout.LayoutParams) cardView.getLayoutParams();
            params.height = height/2;
            cardView.setLayoutParams(params);

            ImageView logoView = (ImageView) findViewById(R.id.imageView_radio_dns_log);
            params = (ConstraintLayout.LayoutParams) logoView.getLayoutParams();
            params.height = height/6;
            logoView.setLayoutParams(params);

            //for text, can directly set the sizef for text
            TextView poweredByTextView = (TextView) findViewById(R.id.textView_PoweredBy);
            poweredByTextView.setTextSize(height/120);

            TextView DesignedByTextView = (TextView) findViewById(R.id.textView_DesignedBy);
            DesignedByTextView.setTextSize(height/120);
        }


    }
}
