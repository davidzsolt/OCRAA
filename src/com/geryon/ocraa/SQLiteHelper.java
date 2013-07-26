package com.geryon.ocraa;



import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
//import android.util.Log;

public class SQLiteHelper extends SQLiteOpenHelper{

	 public static final String TABLE_MUSICINFO = "MusicInfo";
	  public static final String COLUMN_ID = "_id";
	  public static final String COLUMN_ARTIST = "artist";
	  public static final String COLUMN_TITLE ="title";
	  public static final String COLUMN_GAME ="game";
	  public static final String COLUMN_SONG = "song";
	  public static final String COLUMN_DESC ="description";
	  public static final String COLUMN_MD5 = "md5hash";
	  public static final String COLUMN_LINK1 = "link1";
	  public static final String COLUMN_LINK2 = "link2";
	  public static final String COLUMN_LINK3 = "link3";
	  public static final String COLUMN_LINK4 = "link4";
	    
	  private static final String DATABASE_NAME = "musicinfo.db";
	  private static final int DATABASE_VERSION = 1;

	  // Database creation sql statement
	  private static final String DATABASE_CREATE = "create table "
	      + TABLE_MUSICINFO + "(" 
	      + COLUMN_ID + " integer primary key, " 
	      + COLUMN_ARTIST + " text, "
	      + COLUMN_TITLE + " text, " 
	      + COLUMN_GAME + " text, "
	      + COLUMN_SONG + " text, "		
	      + COLUMN_DESC + " text, "
	      + COLUMN_MD5 + " text, "
	      + COLUMN_LINK1 + " text, "
	      + COLUMN_LINK2 + " text, "
	      + COLUMN_LINK3 + " text, "
	      + COLUMN_LINK4 + " text);";

	  public SQLiteHelper(Context context) {
	    super(context, DATABASE_NAME, null, DATABASE_VERSION);
	  }

	  
	  @Override
	  public void onCreate(SQLiteDatabase database) {
	    //database.execSQL(DATABASE_CREATE);
	  }

	  
	  @Override
	  public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
	   /* Log.w(DBConnector.class.getName(),
	        "Upgrading database from version " + oldVersion + " to "
	            + newVersion + ", which will destroy all old data");*/
	    db.execSQL("DROP TABLE IF EXISTS " + TABLE_MUSICINFO);
	    onCreate(db);
	  }
	
	
}
