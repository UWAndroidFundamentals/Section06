package com.example.lab0608;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

public class Lab0608 extends Activity {

	// set log value
	public static final String tag="log";
    
	// display screen
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.screen001);
    }
	
	// set up basic menu from main_menu.xml file
    @Override
    public boolean onCreateOptionsMenu(Menu menu){ 
    	super.onCreateOptionsMenu(menu);
 	   	MenuInflater inflater = getMenuInflater(); 
 	   	inflater.inflate(R.menu.main_menu, menu);
    	return true;
    }
    
    // take action based on menu selection
    @Override
    public boolean onOptionsItemSelected(MenuItem item){ 
    	appendMenuItemText(item);
    	
    	// call method to clear the text field
    	if (item.getItemId() == R.id.menu_clear){
    		this.emptyText();
    		return true;
    	}
    	
    	// send a simple broadcast menu
    	if (item.getItemId() == R.id.menu_send_broadcast){
    		this.testSendBroadcast();
    		return true;
    	}
    	return true;
    }
    
    // link TextView to xml definition
    private TextView getTextView(){
        return (TextView)this.findViewById(R.id.text1);
    }
    
    // add a new item to the text view
    private void appendMenuItemText(MenuItem menuItem){  
       String title = menuItem.getTitle().toString();
       TextView tv = getTextView(); 
       tv.setText(tv.getText() + "\n" + title);
    }
    
    // clear the text field
    private void emptyText(){
          TextView tv = getTextView();
          tv.setText("");
    }
    private void testSendBroadcast()
    {
    	
    	//Create an intent with an action
    	Intent broadcastIntent = new Intent("com.example.lab0608.testbc");
    	
    	//send out the ORDERED broadcast
    	this.sendOrderedBroadcast(broadcastIntent, null);
    	
    	//Log a message after sending the broadcast
    	//This message should appear first in the log file
    	//before the log messages from the broadcast
    	//because they all run on the same thread
    	Log.d(tag,"after send broadcast from main menu");
    }

}
