package com.geryon.ocraa;

import java.io.File;
import java.io.IOException;

import android.app.DownloadManager;
import android.app.DownloadManager.Request;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnPreparedListener;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.actionbarsherlock.app.SherlockActivity;

public class SearchSubActivity extends SherlockActivity {
	private MediaPlayer player;
	private boolean isListening = false;
	private long enqueue;
	private DownloadManager dm;
	private Button dlButton;
	TextView tv_artist;
	TextView tv_title;
	TextView tv_game;
	TextView tv_original;
	TextView tv_desc;
	String link="";
	//String artist, title, game, original, desc;
	
	public void onCreate(Bundle savedInstanceState) {
	  super.onCreate(savedInstanceState);
	  setContentView(R.layout.download_page);
	  
	Intent intent = this.getIntent();
	String artist = intent.getStringExtra("artist");
	String title = intent.getStringExtra("title");
	String game = intent.getStringExtra("game");
	String original = intent.getStringExtra("original");
	String desc = intent.getStringExtra("desc");
	link = intent.getStringExtra("link");
	tv_artist = (TextView) findViewById(R.id.artist);
	tv_title = (TextView) findViewById(R.id.title);
	tv_game = (TextView) findViewById(R.id.game);
	tv_original = (TextView) findViewById(R.id.original);
	tv_desc = (TextView) findViewById(R.id.desc);
	
	tv_artist.setText("Artist: "+artist);
	tv_title.setText("Title: "+title);
	tv_game.setText("Game: "+game);
	if (original == null){original = "";}
	tv_original.setText("Original song: "+original);
	tv_desc.setText(desc);
}
public void download (View view){
	 dm = (DownloadManager) getSystemService(DOWNLOAD_SERVICE);
     Request request = new Request(
             Uri.parse(link));
   
     StringBuilder strB = new StringBuilder(link.toString());
     String name = strB.substring(strB.lastIndexOf("/"),strB.length()-1); 
     
      SharedPreferences prefs = this.getSharedPreferences("Settings", Context.MODE_PRIVATE);
     name = prefs.getString("dlDir", "")+name;
     request.setDestinationUri(Uri.fromFile(new File(name)));
     enqueue = dm.enqueue(request);
     dlButton = (Button)findViewById(R.id.Download);
     dlButton.setText("Queued");
     dlButton.setClickable(false);
//Toast.makeText(this, name, Toast.LENGTH_SHORT).show();
}

public void prelisten (View view){
if (isListening == false ){
	
	this.startService(new Intent(MusicService.ACTION_PAUSE));
	
	player = new MediaPlayer();
     try {
         player.setDataSource(this,Uri.parse(link));
     } catch (IllegalArgumentException e) {
         e.printStackTrace();
     } catch (IllegalStateException e) {
         e.printStackTrace();
     } catch (IOException e) {
         e.printStackTrace();
     }
     player.prepareAsync();

     player.setOnPreparedListener(new OnPreparedListener() {

         public void onPrepared(MediaPlayer mp) {
        	
        	 player.start();
            // buttonRecord.setEnabled(true);
         }
     });
	
	isListening = true;
} else {
	if (player.isPlaying()) {
        player.stop();
        player.release();}
	isListening = false;
	
	
}

}

}