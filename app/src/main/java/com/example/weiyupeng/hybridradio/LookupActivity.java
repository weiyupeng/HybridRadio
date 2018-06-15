package com.example.weiyupeng.hybridradio;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

/**
 * A login screen that offers login via email/password.
 */
public class LookupActivity extends AppCompatActivity  {

    public static final String TAG = "LookupActivity";
    BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String message = intent.getStringExtra(LookupIntentService.LOOKUP_UPDATE);
            Log.e(TAG, "Received from Lookup Service: " + message );
        }
    };


    // UI references.
    private EditText mCountryCodeView;
    private EditText mPICodeView;
    private EditText mFrequencyView;
    private View mProgressView;
    private View mLoginFormView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lookup);

        mCountryCodeView = (EditText) findViewById(R.id.CountryCode);
        mPICodeView = (EditText) findViewById(R.id.PICode);
        mFrequencyView = (EditText) findViewById(R.id.frequency);

        mCountryCodeView.setText("ce1");
        mPICodeView.setText("c479");
        mFrequencyView.setText("95800");



        Button mLookupButton = (Button) findViewById(R.id.lookup);
        mLookupButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                //checking()
                //start a service
                LookupIntentService.startActionStart(getApplicationContext(), mCountryCodeView.getText().toString(),mPICodeView.getText().toString(),mFrequencyView.getText().toString());
            }
        });

        mLoginFormView = findViewById(R.id.login_form);
        mProgressView = findViewById(R.id.login_progress);
    }

    @Override
    protected void onStart() {
        super.onStart();
        // register Receiver

        LocalBroadcastManager.getInstance(getApplicationContext())
                .registerReceiver(receiver,new IntentFilter(LookupIntentService.LOOKUP_SERVICE_MESSAGE));
    }



    @Override
    protected void onStop() {
        super.onStop();
        LocalBroadcastManager.getInstance(getApplicationContext()).unregisterReceiver(receiver);
    }
/**
     * Shows the progress UI and hides the login form.
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    private void showProgress(final boolean show) {
        // On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
        // for very easy animations. If available, use these APIs to fade-in
        // the progress spinner.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
            mLoginFormView.animate().setDuration(shortAnimTime).alpha(
                    show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
                }
            });

            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mProgressView.animate().setDuration(shortAnimTime).alpha(
                    show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
                }
            });
        } else {
            // The ViewPropertyAnimator APIs are not available, so simply show
            // and hide the relevant UI components.
            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
        }
    }




}

