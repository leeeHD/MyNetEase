package com.it520.activity.yxxbase;

import android.support.v7.app.AppCompatActivity;

import com.it520.activity.R;

public class BaseActivity extends AppCompatActivity {

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
        overridePendingTransition(R.anim.activity_exit_in, R.anim.activity_exit_out);
    }
}
