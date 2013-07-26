package com.geryon.ocraa;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v4.app.TaskStackBuilder;
import android.widget.TextView;
//import android.view.View;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.MenuItem;

public class AboutActivity extends SherlockActivity{
	TextView tv;
	String about = "";
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_about);
		 final ActionBar actionBar = getSupportActionBar();

	        // Specify that the Home button should show an "Up" caret, indicating that touching the
	        // button will take the user one step up in the application's hierarchy.
	        actionBar.setDisplayHomeAsUpEnabled(true);
	        tv = (TextView) findViewById(R.id.aboutTextView); 
	        about = "Hi! \n \n" +
	        		"First of all, let me thank you for downloading this app. I hope you will enjoy using it.\n\n" +
	        		"So what's this?\n\n" +
	        		"This is an unofficial, beta-beta-beta version app for the OverClocked ReMix community made by me." +
	        		"In case you're wondering what you can do with this: " +
	        		"Primarily you can search and read about ReMixes (even without active internet connection, so you can read djpretzel-philosophy and judges decisions while travelling, working etc.),\n" +
	        		"you can preview (I mean prelisten) and download ReMixes (OK, I'm no mage, you will need Wi-Fi connection for these functions)\n" +
	        		"And in the meantime you can listen to your music (even freshly downloaded ones) in a primitive media player.\n" +
	        		"Also, you can browse the forum (this solution is temporary as I need further testing about my method (security issues mainly). \n\n"+
	        		"That's it???\n\n"+
	        		"Well, that's it for now, but in case this app gets approved, expect the followings:\n\n" +
	        		"-a nice and shiny update button (this is priority one for me at the moment, but it still needs some testing)\n\n"+
	                "-radio-support (so you can listen to VGM-themed channels)\n\n"+
	                "-native (!) forum support with customizable notifications in case of new posts)\n\n"+
	                "-album support (as you can see, only the ReMixes on ocremix.org are supported. That\'s because I couldn\'t find a pattern to read all albums all at once. I guess this will have to be done manually.)\n\n"+
	        		"-tablet support (although this app works on tablets, I guess it looks ugly as hell. There will be some treatment for that by the end of July)\n\n"+
	                "-the whole interviews section (I can implement Mirby\'s interviews too, if needed. So you are in Texas starving and can't remember what kind of food was mentioned by Alexander Brandon in his interview? No worries, this app will know!)\n\n"+
	        		"-skins! (yeah, I guess there will be a bunch of them. How was that always-mentioned orange style?)\n\n"+
	                "-a built-in mediaplayer (actually, that is almost finished, but was cut out in the last minute, because I was not satisfied with it. There are a lot of better mediaplayers out there. I will add it in a few weeks actually for only one purpose: ID3 Comment tags. I don\'t know about you, but I found much pleasure in reading about the currently played ReMix.)\n\n"+
	        		"-widget to control all those sounds (  \\o ALL THOSE SOUNDS!)\n\n"+
	                "-podcast autodownloader (so you can enjoy Touhou Tuesdays without anytime)\n\n" +
	                "-RSS feed (so you can be the first (among the other app-users) to get informed about a new ReMix)\n\n"+
	                "-and whatever you need (if it can be implemented, of course. Unfortunately, this app won\'t make you a coffee, sorry.)\n\n\n"+
	                "About bugs: I won't try to deceive you, it's possible that there are bugs (not outside, in the app), because I couldn\'t test this on all available device. Please give me a feedback, if you find one, so I can make it disappear with my \"magic\".\n\n"+
	                "You can bombard me with suggestions, ideas, bugreports on: ocremixandroid@gmail.com.\n"+
	                "Thanks for reading this all the way here.\n\n Best wishes:\n"+
	                "Geryon (Zsolt Dávid)";
	        
	        		tv.setText(about);
	}
	 @Override
	    public boolean onOptionsItemSelected(MenuItem item) {
	        switch (item.getItemId()) {
	            case android.R.id.home:
	                // This is called when the Home (Up) button is pressed in the action bar.
	                // Create a simple intent that starts the hierarchical parent activity and
	                // use NavUtils in the Support Package to ensure proper handling of Up.
	                Intent upIntent = new Intent(this, OCRAA.class);
	                if (NavUtils.shouldUpRecreateTask(this, upIntent)) {
	                    // This activity is not part of the application's task, so create a new task
	                    // with a synthesized back stack.
	                    TaskStackBuilder.from(this)
	                            // If there are ancestor activities, they should be added here.
	                            .addNextIntent(upIntent)
	                            .startActivities();
	                    finish();
	                } else {
	                    // This activity is part of the application's task, so simply
	                    // navigate up to the hierarchical parent activity.
	                    NavUtils.navigateUpTo(this, upIntent);
	                }
	                return true;
	        }
	        return super.onOptionsItemSelected(item);
	    }
	
	
/*	
private void toggleDonators(View view){
	if (view.findViewById(R.id.donatorList).getVisibility() == View.GONE){
	view.findViewById(R.id.donatorList).setVisibility(View.VISIBLE);
	view.findViewById(R.id.aboutTextView).setVisibility(View.GONE);
	} else 
		view.findViewById(R.id.donatorList).setVisibility(View.GONE);
	view.findViewById(R.id.aboutTextView).setVisibility(View.VISIBLE);
	
	
}*/


}
