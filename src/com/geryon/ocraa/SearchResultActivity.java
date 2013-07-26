package com.geryon.ocraa;

import android.app.Activity;
//import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
//import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

//import com.actionbarsherlock.app.SherlockActivity;
//import com.actionbarsherlock.app.SherlockListActivity;



public class SearchResultActivity extends Activity {
	private String text="";
	private int type;
	private MusicInfoDAO dao;
	private SimpleCursorAdapter adapter;
	
	@SuppressWarnings("deprecation")
	@Override
	 public void onCreate(Bundle savedInstanceState) {
	  super.onCreate(savedInstanceState);
	  setContentView(R.layout.search_result);
	  Intent intent = this.getIntent();
		text = intent.getStringExtra("StrString");
		
		type = intent.getIntExtra("Type", 8);
	dao = new MusicInfoDAO(getBaseContext());
	dao.open();
	
	Cursor cursor = dao.search(type, text);
	//Cursor cursor = dao.search(0, "pretzel");
	// The desired columns to be bound
	  String[] columns = new String[] {
	    SQLiteHelper.COLUMN_ARTIST,
	    SQLiteHelper.COLUMN_TITLE,
	    SQLiteHelper.COLUMN_GAME,
	    SQLiteHelper.COLUMN_SONG
	    
	  };
	 
	  // the XML defined views which the data will be bound to
	  int[] to = new int[] { 
	    R.id.artist,
	    R.id.title,
	    R.id.game,
	    R.id.original
	  };
	 
	  // create the adapter using the cursor pointing to the desired data 
	  //as well as the layout information
	  adapter = new SimpleCursorAdapter(
	    this, R.layout.searchcursor, 
	    cursor, 
	    columns, 
	    to);
	 
	  ListView listView = (ListView) findViewById(R.id.listView1);
	  // Assign adapter to ListView
	  listView.setAdapter(adapter);
	 
	 
	  listView.setOnItemClickListener(new OnItemClickListener() {
	   @Override
	   public void onItemClick(AdapterView<?> listView, View view, 
	     int position, long id) {
	   // Get the cursor, positioned to the corresponding row in the result set
	   Cursor cursor = (Cursor) listView.getItemAtPosition(position);
	 
	   // Get the state's capital from this row in the database.
	   Intent intent = new Intent(SearchResultActivity.this, SearchSubActivity.class);
       intent.putExtra("artist", cursor.getString(cursor.getColumnIndex(SQLiteHelper.COLUMN_ARTIST)));
       intent.putExtra("title", cursor.getString(cursor.getColumnIndex(SQLiteHelper.COLUMN_TITLE)));
       intent.putExtra("game", cursor.getString(cursor.getColumnIndex(SQLiteHelper.COLUMN_GAME)));
       intent.putExtra("song", cursor.getString(cursor.getColumnIndex(SQLiteHelper.COLUMN_SONG)));
       intent.putExtra("desc", cursor.getString(cursor.getColumnIndex(SQLiteHelper.COLUMN_DESC)));
       intent.putExtra("link", cursor.getString(cursor.getColumnIndex(SQLiteHelper.COLUMN_LINK1)));
           
   	startActivity(intent);
	   
	   //Toast.makeText(getApplicationContext(),countryCode, Toast.LENGTH_SHORT).show();
	 
	   }
	  });
	
	
	
	
	
	
	
}}
