package com.example.lab0602;

import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.app.Activity;
import android.app.Service;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;

public class Lab0602 extends Service { 

	// set the log string
    private static final String TAG = "log"; 
    
    // link to the AIDL file
	public class SumServiceImpl extends ISumService.Stub
    {
		@Override
        public double getSum(double first, double second) throws RemoteException
        {
            Log.v(TAG, "Service: getSum() called"); 
        
            // calculate the sum
            double sum = first + second;
            
            // create a new intent
            Intent intent = new Intent();
            
            // set the action to the action type defined in the client manifest
            intent.setAction("com.example.lab0604.testbc");
            
            // pass the sum as a string
            intent.putExtra("message", Double.toString(sum));
            
            // send the broadcast using the intent
            sendBroadcast(intent); 
            
            // return the value to getSum call
            return sum; 
        
        }
    }

	// create the service 
    @Override
    public void onCreate() {
        super.onCreate();
        Log.v(TAG, "Service: onCreate() called");
    }

    // destroy the service
    @Override
    public void onDestroy()
    {
        super.onDestroy();
    	Log.v(TAG, "Service: onDestroy() called");
    }

    // onBind is the way we link
    @Override
    public IBinder onBind(Intent intent)
    {
    	Log.v(TAG, "Service: onBind() called");
        return new SumServiceImpl();
    }
}
