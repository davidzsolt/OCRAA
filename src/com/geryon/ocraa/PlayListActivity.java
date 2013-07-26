package com.geryon.ocraa;

import java.util.ArrayList;
import java.util.HashMap;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import com.actionbarsherlock.app.SherlockListActivity;

public class PlayListActivity extends SherlockListActivity {
	// Songs list
	public ArrayList<HashMap<String, String>> songsList = new ArrayList<HashMap<String, String>>();

	SimpleCursorAdapter adapter;

       final Uri mediaSrc = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
       
     /** Called when the activity is first created. */
     @SuppressWarnings("deprecation")
	@Override
     public void onCreate(Bundle savedInstanceState) {
         super.onCreate(savedInstanceState);
         String[] from = {MediaStore.MediaColumns.TITLE};
         int[] to = {
            android.R.id.text1};

          Cursor cursor = managedQuery(
            mediaSrc, 
            null, 
            null, 
            null, 
            MediaStore.Audio.Media.TITLE);

       adapter = new SimpleCursorAdapter(this,
            android.R.layout.activity_list_item, cursor, from, to);
       setListAdapter(adapter);
    }

    @Override
   protected void onListItemClick(ListView l, View v, int position, long id) {
    Cursor cursor = adapter.getCursor();
    cursor.moveToPosition(position);

    String _id = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media._ID));

    Uri playableUri
        = Uri.withAppendedPath(mediaSrc, _id);

   // Toast.makeText(this, "Play: " + playableUri.toString(), Toast.LENGTH_LONG).show();
    //this.startService(new Intent(MusicService.ACTION_URL));
    Intent intent = new Intent(MusicService.ACTION_URL);
   intent.putExtra("Uri", position-1);
     this.startService(intent);
  // Log.e("tssssssssss",String.valueOf(position));
    /*Intent intent = new Intent();
    intent.setClass(AndroidListMediaActivity.this, 
            PlayerActivity.class);
    intent.setData(playableUri);
    startActivity(intent);
	*/	
	this.finish();	
		}
}