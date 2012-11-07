package com.example.lab0608;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class TestReceiver2 extends BroadcastReceiver 
{
	// set log tag
	private static final String tag = "log"; 
    @Override
    public void onReceive(Context context, Intent intent) 
    {
        String message = "calling TestReceiver 2";
        // print out the message that was sent with the intent
        Log.d(tag, message);
    	
    	// print out information on the intent
        Log.v(tag, "test receiver: intent=" + intent);
  
        // set the date for the lower priority receiver
        setResultData("calling TestReceiver 1");
        
    }
}

