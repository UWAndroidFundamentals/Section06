package com.example.lab0603;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.TextView;

public class TestReceiver extends BroadcastReceiver 
{
	// set log tag
	private static final String tag = "log"; 
    @Override
    public void onReceive(Context context, Intent intent) 
    {
    	// print out information on the intent
        Log.d(tag, "test receiver: intent=" + intent);
        
        // pull in the message that was sent with the intent
        String message = intent.getStringExtra("message");
        
        Lab0603.t1.setText(message);
        
        // print out the message that was sent with the intent
        Log.d(tag, message);
    }
}

