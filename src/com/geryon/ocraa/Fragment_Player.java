/*   
 * Copyright (C) 2011 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.geryon.ocraa;

import java.util.ArrayList;
import java.util.HashMap;

import com.actionbarsherlock.app.SherlockFragment;

import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;

import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.SeekBar.OnSeekBarChangeListener;

/** 
 * Main activity: shows media player buttons. This activity shows the media player buttons and
 * lets the user click them. No media handling is done here -- everything is done by passing
 * Intents to our {@link MusicService}.
 * */
public class Fragment_Player extends SherlockFragment implements OnClickListener,OnSeekBarChangeListener {
    /**
     * The URL we suggest as default when adding by URL. This is just so that the user doesn't
     * have to find an URL to test this sample.
     */
 private boolean isPlaying =false;
public static ImageView btnPlay, btnForward, btnBackward, btnNext, btnPrevious, btnPlayl, btnAddPl, btnRemovePl;
public static ImageButton btnShuffle, btnRepeat;
public static SeekBar songProgressBar;
private static int totalDuration;
private TextView titleView, ShuffleView, RepeatView;
public static ArrayList<HashMap<String, String>> songsList = new ArrayList<HashMap<String, String>>();
public boolean repeat, shuffle;
public static TextView songTitle, songCurrentDurationLabel,
	songTotalDurationLabel;
	private Utilities utils;
public SharedPreferences prefs;
	/**
     * Called when the activity is first created. Here, we simply set the event listeners and
     * start the background service ({@link MusicService}) that will handle the actual media
     * playback.
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
    	final View rootView = inflater.inflate(R.layout.player, container, false);
        btnPlay = (ImageView) rootView.findViewById(R.id.btnPlay);
        btnForward = (ImageView) rootView.findViewById(R.id.btnNext);
        btnBackward = (ImageView) rootView.findViewById(R.id.btnPrevious);
        btnPlayl = (ImageView) rootView.findViewById(R.id.btnPlaylist);
        btnShuffle = (ImageButton) rootView.findViewById(R.id.btnShuffle);
        btnRepeat = (ImageButton) rootView.findViewById(R.id.btnRepeat);
        songProgressBar = (SeekBar)rootView.findViewById(R.id.songProgressBar);
        songProgressBar.setOnSeekBarChangeListener(this);
        songProgressBar.setProgress(0);
		songProgressBar.setMax(100);
		titleView = (TextView) rootView.findViewById(R.id.songTitle);
		songTotalDurationLabel = (TextView) rootView.findViewById(R.id.songTotalDur);
		songCurrentDurationLabel = (TextView) rootView.findViewById(R.id.songCurrentDur);
		RepeatView = (TextView)rootView.findViewById(R.id.RepeatView);
	    ShuffleView = (TextView)rootView.findViewById(R.id.ShuffleView);
		btnPlay.setOnClickListener(this);
        btnForward.setOnClickListener(this);
        btnBackward.setOnClickListener(this);
        btnPlayl.setOnClickListener(this);
    	btnShuffle.setOnClickListener(this);
    	btnRepeat.setOnClickListener(this);
        utils = new Utilities();
       
        return rootView;
    }
    @Override
	public void onResume() {
        // TODO Auto-generated method stub
        super.onResume();
         
        IntentFilter iff= new IntentFilter(MusicService.ACTION_UPDATE);
        LocalBroadcastManager.getInstance(getActivity()).registerReceiver(currentTimeReceiver, iff);
        IntentFilter iff2= new IntentFilter(MusicService.ACTION_TOTAL);
        LocalBroadcastManager.getInstance(getActivity()).registerReceiver(totalReceiver, iff2);
    prefs = getActivity().getSharedPreferences("Settings", Context.MODE_PRIVATE);
    if (prefs.getBoolean("repeat",false)==true){ 
    RepeatView.setText("REPEAT: ON");
    }else {
    	RepeatView.setText("REPEAT: OFF");
    }
    if (prefs.getBoolean("shuffle",false)==true){ 
        ShuffleView.setText("SHUFFLE: ON");
        }else {
        	ShuffleView.setText("SHUFFLE: OFF");
        }
    
    
    
    
    }
     
    private BroadcastReceiver currentTimeReceiver= new BroadcastReceiver() {
         
        @Override
        public void onReceive(Context context, Intent intent) {
            int progress = intent.getIntExtra("progress", 0);
            songCurrentDurationLabel.setText((utils.milliSecondsToTimer(progress))); 
            
            progress = (int) (utils.getProgressPercentage(progress, totalDuration));
            songProgressBar.setProgress(progress);
      
      
        }
    };
    
    private BroadcastReceiver totalReceiver= new BroadcastReceiver() {
        
        @Override
        public void onReceive(Context context, Intent intent) {
            int totalDur = intent.getIntExtra("total", 0);
        	totalDuration = totalDur;
           songTotalDurationLabel.setText(utils.milliSecondsToTimer(totalDur));
        	titleView.setText(intent.getStringExtra("name"));
      
        
        }
    };
    
    
    
    
    @Override
	public void onPause() {
        super.onPause();
         
        LocalBroadcastManager.getInstance(getActivity()).unregisterReceiver(currentTimeReceiver);
    }
    public void onClick(View target) {
        if (target == btnPlay)
        {if (isPlaying == false){
        	getActivity().startService(new Intent(MusicService.ACTION_PLAY));
        	
        	isPlaying = true;
        	btnPlay.setImageResource(
					R.drawable.btn_pause);
        }else{
            getActivity().startService(new Intent(MusicService.ACTION_PAUSE));	
            isPlaying = false;
            btnPlay.setImageResource(
					R.drawable.btn_play);
        }
        
        }
        else if (target == btnPlayl){
        	Intent intent = new Intent(getActivity().getBaseContext(), PlayListActivity.class);
        		        startActivityForResult(intent, 100);
        		   	
        		   	
        		   	
        		   }
        else if (target == btnForward){
        	getActivity().startService(new Intent(MusicService.ACTION_SKIP));
        
        }
        else if (target == btnBackward)
        	getActivity().startService(new Intent(MusicService.ACTION_REWIND));
        else if (target == btnShuffle){
        	      	
        	shuffle = prefs.getBoolean("shuffle", false);
        	if (shuffle == false){
        		shuffle = true;
        		ShuffleView.setText("Shuffle: ON");
        		Log.d("Shuffle:", "ON");
        	}else{
        		shuffle = false;
        		ShuffleView.setText("Shuffle: OFF");
        		Log.d("Shuffle:", "OFF");
        	}
        	SharedPreferences.Editor editor = prefs.edit();
    	    editor.putBoolean("shuffle", shuffle);
    	    editor.commit();
    	    Intent repeatIntent = new Intent(MusicService.ACTION_SHUFFLE);
    		repeatIntent.putExtra("shuffle", shuffle);
    		getActivity().startService(repeatIntent);
        
        
        }
        else if (target == btnRepeat){
        	        	
        	repeat = prefs.getBoolean("repeat", false);
        	         	
        	shuffle = prefs.getBoolean("repeat", false);
        	if (repeat == false){
        		repeat = true;
        		RepeatView.setText("Repeat: ON");
        		Log.d("Repeat:", "ON");
        	}else{
        		repeat = false;
        		RepeatView.setText("Repeat: OFF");
        		Log.d("Repeat:", "OFF");  		
        		       		
        	}
        	SharedPreferences.Editor editor = prefs.edit();
    	    editor.putBoolean("repeat", repeat);
    	    editor.commit();
    	    Intent repeatIntent = new Intent(MusicService.ACTION_REPEAT);
    		repeatIntent.putExtra("repeat", repeat);
    		getActivity().startService(repeatIntent);
        
        }
        }
        

   

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        switch (keyCode) {
            case KeyEvent.KEYCODE_MEDIA_PLAY_PAUSE:
            case KeyEvent.KEYCODE_HEADSETHOOK:
            	getActivity().startService(new Intent(MusicService.ACTION_TOGGLE_PLAYBACK));
                return true;
        }
        return onKeyDown(keyCode, event);
    }

	@Override
	public void onProgressChanged(SeekBar arg0, int arg1, boolean arg2) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStartTrackingTouch(SeekBar arg0) {
		 if (currentTimeReceiver != null)
			 {
			 LocalBroadcastManager.getInstance(getActivity()).unregisterReceiver(currentTimeReceiver);
			 }
				
	}
	
	@Override
	public void onStopTrackingTouch(SeekBar seekto) {
		
		Utilities utils = new Utilities();
		int currentPosition = utils.progressToTimer(seekto.getProgress(),
				totalDuration);

				
		Intent seekIntent = new Intent(MusicService.ACTION_SEEKTO);
		seekIntent.putExtra("seekto", currentPosition);
		getActivity().startService(seekIntent);
		
		IntentFilter iff= new IntentFilter(MusicService.ACTION_UPDATE);
        LocalBroadcastManager.getInstance(getActivity()).registerReceiver(currentTimeReceiver, iff);
	}

@Override
public void onDestroy(){
	super.onDestroy();
	LocalBroadcastManager.getInstance(getActivity()).unregisterReceiver(currentTimeReceiver);
	
}
}