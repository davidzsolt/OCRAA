package com.geryon.ocraa;

import java.util.LinkedHashMap;
import java.util.List;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.actionbarsherlock.app.SherlockFragment;

public class Fragment_Forum extends SherlockFragment{
	String html = "http://ocremix.org/forums/showthread.php?t=40864";
	LinkedHashMap <String,String> breadcrumb = new LinkedHashMap<String,String>();
	 List<String> spinnerLinks;
	private WebView web;
	String pile = "";
	/*private TextView tv;
	private Context context;
   
	private Spinner bc_spinner;
	private ListView forumviever ;  
	private ArrayAdapter<String> listAdapter ; */ 
/*	
@Override
public void onStart(){
	super.onStart();
	DownloadAsync dl = new DownloadAsync(this.getActivity());

	dl.execute(html);
	
}
*/



@Override
	    public View onCreateView(LayoutInflater inflater, ViewGroup container,
	            Bundle savedInstanceState) {
	    	final View rootView = inflater.inflate(R.layout.forum, container, false);
	
	    	//tv = (TextView)rootView.findViewById(R.id.testView);
	    	//spinnerLinks = new ArrayList<String>();
	    	//bc_spinner = (Spinner) rootView.findViewById(R.id.spinner_breadcrumb);
	    	web = (WebView)rootView.findViewById(R.id.view_web);
	    	web.getSettings().setJavaScriptEnabled(true);
	    	web.getSettings().setBuiltInZoomControls(true);
	    	web.loadUrl("http://ocremix.org/forums/");
	    	//web.setInitialScale(50);
	web.setWebViewClient(new WebViewHelper());
	    	
	    	
	    	
return rootView;	
}
/*	 public class DownloadAsync extends AsyncTask<String, Void, String> {
		   String source = "";
		   private ProgressDialog dialog;
	        private Activity activity;
		  		   
		   public DownloadAsync(Activity activity) {
	            this.activity = activity;
	            context = activity;
	            dialog = new ProgressDialog(context);
	        }
		   
		   
		   @Override
		    protected String doInBackground(String... urls) {
		    

		        //URL url1 = new URL(url);
		       
		    	for (String url : urls) {
		    		try {
		    	
		       URL address = new URL(url);
		    		HttpURLConnection urlConn = (HttpURLConnection) address
		                .openConnection();



		        InputStreamReader in = new InputStreamReader(
		                urlConn.getInputStream());
		        BufferedReader buffer = new BufferedReader(in, 100000);

		        StringBuilder builder = new StringBuilder();
		        String aux = "";



		        while ((aux = buffer.readLine()) != null)
		            builder.append(aux);

		        source = builder.toString();

		        in.close();
		        urlConn.disconnect();

		    } catch (SocketTimeoutException e) {
		        return "time out";
		    } catch (IOException e) {
		        e.printStackTrace();
		    }
		    // return XML
		    }
		    return source;
		}
		/* @Override
		 protected void onPreExecute(){
			 dialog.setMessage("Retrieving forum");
	         dialog.show();		 
	         super.onPreExecute();
		 }*/
		 
		 
		 /*
		 @Override
		    protected void onPostExecute(String result) {
			 ForumCrawler fc = new ForumCrawler();
				 breadcrumb = fc.getBreadcrumb(source);
				spinnerLinks = fc.mapToArray(breadcrumb);
				ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getActivity(),
						android.R.layout.simple_spinner_item, spinnerLinks);
					dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
					bc_spinner.setAdapter(dataAdapter);
				if (fc.isThread(html)){
					
								
					
					
					
				}else {
					
					
					
					
				}
					
					
					//tv.setText(breadcrumb);
			  pile = source;
		   // dialog.dismiss();
		
		 
		 
		 
		 
		 }
	 }
	 
*/

}


	