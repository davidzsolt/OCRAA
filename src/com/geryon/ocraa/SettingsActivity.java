package com.geryon.ocraa;



import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.NavUtils;
import android.support.v4.app.TaskStackBuilder;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.MenuItem;

public class SettingsActivity extends SherlockFragmentActivity {
Fragment valami = new Fragment();


	
	/**
     * The {@link android.support.v4.view.PagerAdapter} that will provide fragments representing
     * each object in a collection. We use a {@link android.support.v4.app.FragmentStatePagerAdapter}
     * derivative, which will destroy and re-create fragments as needed, saving and restoring their
     * state in the process. This is important to conserve memory and is a best practice when
     * allowing navigation between objects in a potentially large collection.
     */
   // DemoCollectionPagerAdapter mDemoCollectionPagerAdapter;

    /**
     * The {@link android.support.v4.view.ViewPager} that will display the object collection.
     */
    //ViewPager mViewPager;
	TextView dir_textview;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        SharedPreferences prefs = this.getSharedPreferences("Settings", Context.MODE_PRIVATE);
        
        dir_textview = (TextView) findViewById(R.id.textView_dlDir);
		dir_textview.setText(prefs.getString("dlDir", ""));
           
        final ActionBar actionBar = getSupportActionBar();

        actionBar.setDisplayHomeAsUpEnabled(true);

      
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    	if(requestCode == DirectoryPicker.PICK_DIRECTORY && resultCode == RESULT_OK) {
    		Bundle extras = data.getExtras();
    		String path = (String) extras.get(DirectoryPicker.CHOSEN_DIRECTORY);
    		dir_textview = (TextView) findViewById(R.id.textView_dlDir);
    		dir_textview.setText(path);
    		SharedPreferences prefs = this.getSharedPreferences("Settings", Context.MODE_PRIVATE);
    		SharedPreferences.Editor editor = prefs.edit();
    	    editor.putString("dlDir", path);
    	    editor.commit();
    		
    		
    	}
    }
    
    
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
               
                Intent upIntent = new Intent(this, OCRAA.class);
                if (NavUtils.shouldUpRecreateTask(this, upIntent)) {
                    
                    TaskStackBuilder.from(this)
                            
                            .addNextIntent(upIntent)
                            .startActivities();
                    finish();
                } else {
                    
                    NavUtils.navigateUpTo(this, upIntent);
                }
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    
    
    public void selectDir (View view){
    	Intent intent = new Intent(this, DirectoryPicker.class);
    	startActivityForResult(intent, DirectoryPicker.PICK_DIRECTORY);
    	
    	
    }
}
