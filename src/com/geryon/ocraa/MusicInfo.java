package com.geryon.ocraa;

import java.util.ArrayList;

//import android.util.Log;

//The dataStructure for a music
public class MusicInfo {
	public int id = 0;
	public  String title = "";
	public  ArrayList<String> artist;
	public  ArrayList<String> song;
	//TODO Original atrist
	//public  String origArtist = "";
	public  ArrayList<String> dlLinks;
	public  String desc = "";
	public  String md5 = "";
	public String game = "";
	//private static String TAG = "MusicInfo";
	//default constructors
	MusicInfo(){
		artist = new ArrayList<String>();
		song = new ArrayList<String>();
		dlLinks = new ArrayList<String>();
	}
	//TODO test if private method can be used here
	public void setId (int inId){
		this.id = inId;
	//Log.d(TAG, "Set(ID):"+String.valueOf(inId));
	}

	public void setGame (String inGame){
		//Log.d(TAG, "Set(GAME):"+inGame);
		this.game = inGame;
		
	}
	public void setTitle (String inTitle){
		//Log.d(TAG, "Set(TITLE):"+inTitle);
		this.title = inTitle;
		
	}

	public void setArtist (String inArtist){
		//Log.d(TAG, "Set(ARTIST):"+inArtist);
		this.artist.add(inArtist);
		
	}

	public void setSong (String inSong){
		//Log.d(TAG, "Set(SONG):"+inSong);
		this.song.add(inSong);
		
	}
//TODO Original artist
	/*
	public void setOrig (String inOrig){
		Log.d(TAG, "Set(ORIG):"+inOrig);
		this.origArtist = inOrig;
		
	}*/

	public void setDlLinks (String inLink){
	
		//Log.d(TAG, "Set(LINK):"+inLink);
		this.dlLinks.add(inLink);
		
	}

	public void setDesc (String inDesc){
		//Log.d(TAG, "SetDESC:"+inDesc);
		this.desc = inDesc;
		
	}

	public void setMD5 (String inMD5){
		//Log.d(TAG, "Set(MD5):"+inMD5);
		this.md5 = inMD5;
		
	}
	
	public int getId () {
		//Log.d(TAG, "Got(ID):"+this.id);
		return this.id;
		
	}
	
	public String getTitle () {
		//Log.d(TAG, "Got(TITLE):"+this.title);
		return this.title;
		
	}
	
	public ArrayList<String> getArtist () {
		/*for (int r = 0; r<this.artist.size();r++)
		{
		Log.d(TAG, "Got(ARTIST):"+this.artist.get(r));
		}*/
		return this.artist;
		
	}
	

	public ArrayList<String> getSong () {
	/*for (int z=0; z<this.song.size();z++){
		Log.d(TAG, "Got(SONG):"+this.song.get(z));
	}*/
		return this.song;
		
	}
//TODO Original artist
	/*
	public String getOrig () {
		Log.d(TAG, "Got(ORIG.ARTIST):"+this.origArtist);
		return this.origArtist;
		
	}
*/
	public ArrayList<String> getDlLinks () {
		/*for (int t=0; t<this.dlLinks.size();t++){
			Log.d(TAG, "Got(LINK):"+this.dlLinks.get(t));
		}*/
		return this.dlLinks;
		
	}

	public String getDesc () {
		//Log.d(TAG, "Got(DESC):"+this.desc);	
		return this.desc;
		
	}
	public String getGame (){
		//Log.d(TAG, "Got(GAME):"+this.game);
		return this.game;
		
	}
	public String getMD5 () {
		//Log.d(TAG, "Got(MD5):"+this.md5);
		return this.md5;
		
	}

	public void reset(){
		//Log.d(TAG, "Resetted.");
		this.artist.clear();
		this.desc = "";
		this.dlLinks.clear();
	
		this.song.clear();
		this.id = 0;
		//TODO Original artist
		//this.origArtist = "";
		this.title = "";
	}
}
