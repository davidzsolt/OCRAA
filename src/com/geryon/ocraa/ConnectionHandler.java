package com.geryon.ocraa;

//import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class ConnectionHandler {
	private Context conContext;
	public ConnectionHandler(Context c){
		conContext = c;
		
	}
	
	
	public boolean isConnectionAvailable()
	
	{
		ConnectivityManager cm =
                (ConnectivityManager) conContext.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo netInfo = cm.getActiveNetworkInfo();
            if (netInfo != null && netInfo.isConnected()) {
            	return true;
            } else { 
            	return false;            	
            }
	}
	
}
