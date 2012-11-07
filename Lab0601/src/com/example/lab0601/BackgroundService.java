package com.example.lab0601;

// BackgroundService.java
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.IBinder;
import android.util.Log;

public class BackgroundService extends Service
{
	// set log value 
    private static final String TAG = "log";
	
    // create notification manager object
    private NotificationManager notificationMgr;
    
    // create thread group to manage multiple threads
    private ThreadGroup myThreads = new ThreadGroup("ServiceWorker");
    
    @Override
    public void onCreate() {
        super.onCreate();

        // create log entry
        Log.v(TAG, "BackgroundService: in onCreate()");
        
        // create notification manager
        notificationMgr =(NotificationManager)getSystemService(
               NOTIFICATION_SERVICE);
        
        // create notification using emo_im_winking.png in drawable
        displayNotificationMessage("Background Service is running");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        super.onStartCommand(intent, flags, startId);
        
        // passing "Extras" value - a type of Bundle
        int counter = intent.getExtras().getInt("counter");
        Log.v(TAG, "BackgroundService: in onStartCommand(), counter = " + counter +
        		", startId = " + startId);

        // start a new thread in the thread group
        // note that service does not come with default thread or thread group
        // you must manage your own threads
        new Thread(myThreads, new ServiceWorker(counter), "BackgroundService")
        	.start();
        
        // note non-sticky (don't stick around) option after work is done
        return START_NOT_STICKY;
    }

    class ServiceWorker implements Runnable
    {
    	private int counter = -1;
		public ServiceWorker(int counter) {
			this.counter = counter;
		}

		public void run() {
	        // we simulate each thread work with 10 second delay
            try {
				Log.v(TAG, "BackgroundService: ServiceWorker: " + Thread.currentThread().getId() + " sleeping for 10 seconds. counter = " + counter);
				Thread.sleep(10000);
				Log.v(TAG, "BackgroundService: ServiceWorker: " + Thread.currentThread().getId() + " ... waking up");
			} catch (InterruptedException e) {
				// log interrupt message
				Log.v(TAG, "BackgroundService: ServiceWorker: " + Thread.currentThread().getId() + " ... sleep interrupted");
			}
        }
    }

    @Override
    public void onDestroy()
    {
        Log.v(TAG, "in onDestroy(). Interrupting threads and cancelling notifications");
        
        // interrupt ALL threads by invoking method on threadgroup object 
        myThreads.interrupt();
        
        // cancel the notification manager and clear notifications
        notificationMgr.cancelAll();
        
        // call to parent destroy of service for clean up
        super.onDestroy();
    }

    @Override
    public IBinder onBind(Intent intent) {
    	
    	// since this is NOT a remote service, we return NULL
    	// remote services can bind/unbind to leverage functionality of service
    	Log.v(TAG, "in onBind()");
        return null;
    }

    private void displayNotificationMessage(String message)
    {
     	// using depreciated method because it is simpler to understand
    	// and fully backward compatable to API 8
    	
        // create the notification item
    	Notification notification = new Notification(R.drawable.emo_im_winking, 
                message, System.currentTimeMillis());
        
    	// set basic flags
        notification.flags = Notification.FLAG_NO_CLEAR;

        // pending intent is used for the detail of the notification
        PendingIntent contentIntent = 
                PendingIntent.getActivity(this, 0, new Intent(this, Lab0601.class), 0);

        // link up the notification
        notification.setLatestEventInfo(this, TAG, message, contentIntent);

        // activate the notification
        notificationMgr.notify(0, notification);
    
    }
}
