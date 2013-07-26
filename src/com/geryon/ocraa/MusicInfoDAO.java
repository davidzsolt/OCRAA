package com.geryon.ocraa;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class MusicInfoDAO {

private SQLiteDatabase database;
private SQLiteHelper helper;
//private String[] allColumns = {SQLiteHelper.COLUMN_ID, SQLiteHelper.COLUMN_ARTIST, SQLiteHelper.COLUMN_TITLE, SQLiteHelper.COLUMN_GAME, SQLiteHelper.COLUMN_SONG, SQLiteHelper.COLUMN_DESC};
//private MusicInfo tempInfo = new MusicInfo();
//private static final String TAG = "DAO";
private Utilities utils;

public MusicInfoDAO(Context context){
	helper = new SQLiteHelper(context);
}

public void open() throws SQLException {
    database = helper.getWritableDatabase();
  }

public Cursor search(int row, String exp){
	Cursor mCursor = null;
	String type="";
	switch (row) {
	case 0: {
		type = SQLiteHelper.COLUMN_TITLE;
		break;
	}
	case 1: {
		type = SQLiteHelper.COLUMN_ARTIST;
		break;
	}
	case 2: {
		type = SQLiteHelper.COLUMN_GAME;
		break;
	}
	case 3: {
		type = SQLiteHelper.COLUMN_SONG;
		break;
	}
	case 4: {
		type = SQLiteHelper.COLUMN_DESC;
		break;
	}
		
}
		
	if ((exp == null)  ||  (exp.length () == 0))  {
		   mCursor = database.query(SQLiteHelper.TABLE_MUSICINFO, new String[] {SQLiteHelper.COLUMN_ID, SQLiteHelper.COLUMN_ARTIST, SQLiteHelper.COLUMN_TITLE, SQLiteHelper.COLUMN_SONG, SQLiteHelper.COLUMN_GAME, SQLiteHelper.COLUMN_DESC,SQLiteHelper.COLUMN_LINK1, SQLiteHelper.COLUMN_LINK2,SQLiteHelper.COLUMN_LINK3, SQLiteHelper.COLUMN_LINK4 }, 
		     null, null, null, null, null);
		 
		  }
		  else {
		   mCursor = database.query(SQLiteHelper.TABLE_MUSICINFO, new String[] {SQLiteHelper.COLUMN_ID, SQLiteHelper.COLUMN_ARTIST, SQLiteHelper.COLUMN_TITLE, SQLiteHelper.COLUMN_SONG,  SQLiteHelper.COLUMN_GAME, SQLiteHelper.COLUMN_DESC,SQLiteHelper.COLUMN_LINK1, SQLiteHelper.COLUMN_LINK2,SQLiteHelper.COLUMN_LINK3, SQLiteHelper.COLUMN_LINK4 }, 
		     type + " like '%" + exp + "%'", null, null, null, null, null);
		  }
		  if (mCursor != null) {
		   mCursor.moveToFirst();
		  }
		  return mCursor;
		 
		 }
	
	
public void close() {
    helper.close();
  }

public void writeDB (MusicInfo input){
	utils = new Utilities();
	
		ContentValues values = new ContentValues();
		values.put(SQLiteHelper.COLUMN_ID, input.getId());
		values.put(SQLiteHelper.COLUMN_ARTIST, utils.StringMerge(input.getArtist()) );// !!!
		values.put(SQLiteHelper.COLUMN_TITLE, input.getTitle() );
		values.put(SQLiteHelper.COLUMN_GAME , input.getGame());
		values.put(SQLiteHelper.COLUMN_SONG , utils.StringMerge(input.getSong()) ); //!!!
		values.put(SQLiteHelper.COLUMN_DESC , input.getDesc());
		values.put(SQLiteHelper.COLUMN_MD5 , input.getMD5() );
		values.put(SQLiteHelper.COLUMN_LINK1 , input.getDlLinks().get(0) );
		values.put(SQLiteHelper.COLUMN_LINK2 , input.getDlLinks().get(1));
		values.put(SQLiteHelper.COLUMN_LINK3 , input.getDlLinks().get(2));
		values.put(SQLiteHelper.COLUMN_LINK4 , input.getDlLinks().get(3));
		long insertId = database.insert(SQLiteHelper.TABLE_MUSICINFO, null,
		        values);
	}
	
	
}

	

