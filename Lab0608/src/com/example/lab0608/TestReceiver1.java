package com.example.lab0608;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class TestReceiver1 extends BroadcastReceiver 
{
	// set log tag
	private static final String tag = "log"; 
    @Override
    public void onReceive(Context context, Intent intent) 
    {
        
        // pull in the message 
        String message = getResultData();
    
        // print out the message that was sent with the intent
        Log.d(tag, message);
     	
    	// print out information on the intent
        Log.d(tag, "test receiver: intent=" + intent);
   
    }
}

