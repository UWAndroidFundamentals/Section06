package com.example.lab0603;

import com.example.lab0602.IStockQuoteService;

import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class Lab0603 extends Activity {

	protected static final String TAG = "log";
	private IStockQuoteService stockService = null;

	private Button bindBtn;
	private Button callBtn;
	private Button unbindBtn;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.screen001); 

		bindBtn = (Button) findViewById(R.id.bindBtn);
		bindBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View view) {
				bindService(new Intent(IStockQuoteService.class.getName()),
						serConn, Context.BIND_AUTO_CREATE);
				bindBtn.setEnabled(false);
				callBtn.setEnabled(true);
				unbindBtn.setEnabled(true);
	
			}
		});

		callBtn = (Button) findViewById(R.id.callBtn);
		callBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View view) {
				callService();
			}
		});
		callBtn.setEnabled(false);

		unbindBtn = (Button) findViewById(R.id.unbindBtn);
		unbindBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View view) {
				unbindService(serConn);
				bindBtn.setEnabled(true);
				callBtn.setEnabled(false);
				unbindBtn.setEnabled(false);
			}
		});
		unbindBtn.setEnabled(false);
	}

	private void callService() {
		try {
			double val = stockService.getQuote("SYH");
			Toast.makeText(Lab0603.this, "Value from service is " + val,
					Toast.LENGTH_SHORT).show();
		} catch (RemoteException ee) {
			Log.v("log", "StockQuoteClient: " + ee.getMessage(), ee);
		}
	}

	private ServiceConnection serConn = new ServiceConnection() {

		@Override
		public void onServiceConnected(ComponentName name, IBinder service) {
			Log.v(TAG, "StockQuoteClient: onServiceConnected() called");
			stockService = IStockQuoteService.Stub.asInterface(service);
			callService();
		}

		@Override
		public void onServiceDisconnected(ComponentName name) {
			Log.v(TAG, "StockQuoteClient: onServiceDisconnected() called");
			stockService = null;
		}
	};
}
