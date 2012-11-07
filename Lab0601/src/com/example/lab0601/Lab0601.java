package com.example.lab0601;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;

public class Lab0601 extends Activity {

    private static final String TAG = "log";
    private int counter = 1;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
    	Log.v("TAG","LAB0601: onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.screen001);
    }

    public void doClick(View view) {
        switch(view.getId()) {
        case R.id.startBtn:
            Log.v(TAG, "LAB0601: Starting service... counter = " + counter);
            Intent intent = new Intent(Lab0601.this,
                    BackgroundService.class);
            intent.putExtra("counter", counter++);
            startService(intent);
            break;
        case R.id.stopBtn:
            stopService();
        }
    }

    private void stopService() {
    	Log.v(TAG, "LAB0601: Stopping service...");
        if(stopService(new Intent(Lab0601.this,
                    BackgroundService.class)))
        	Log.v(TAG, "LAB0601: stopService was successful");
        else
        	Log.v(TAG, "LAB0601: stopService was unsuccessful");
    }

    @Override
    public void onDestroy()
    {
    	Log.v(TAG,"LAB0601: destroy activity and service");
    	stopService();
        super.onDestroy();
    }
}
