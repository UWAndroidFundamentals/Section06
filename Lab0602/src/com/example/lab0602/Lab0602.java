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
	public class StockQuoteServiceImpl extends IStockQuoteService.Stub
    {
		@Override
        public double getQuote(String ticker) throws RemoteException
        {
            Log.v(TAG, "Service: getQuote() called for " + ticker); 
            return 20.0; 
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
        return new StockQuoteServiceImpl();
    }
}
