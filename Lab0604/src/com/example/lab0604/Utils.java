package com.example.lab0604;

import android.util.Log;

public class Utils 
{
	// get the current thread
	public static long getThreadId()
	{
		Thread t = Thread.currentThread();
		return t.getId();
	}
	
	// build up the signature
	public static String getThreadSignature()
	{
		Thread t = Thread.currentThread();
		long l = t.getId();
		String name = t.getName();
		long p = t.getPriority();
		String gname = t.getThreadGroup().getName();
		return (name + ":(id)" + l + ":(priority)" + p
				+ ":(group)" + gname);
	}
	
	// print the signature to the log file
	public static void logThreadSignature(String tag)
	{
		Log.d(tag, getThreadSignature());
	}
	
	// sleep for a number of seconds - not currently used
	public static void sleepForInSecs(int secs)
	{
		try
		{
			Thread.sleep(secs * 1000);
		}
		catch(InterruptedException x)
		{
			throw new RuntimeException("interrupted",x);
		}
	}
}
