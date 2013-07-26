package com.geryon.ocraa;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.EditText;
import android.widget.Spinner;
import com.actionbarsherlock.app.SherlockFragment;





public class Fragment_HomeTab extends SherlockFragment /*implements OnItemSelectedListener*/{
	private static EditText mEdit;

	private static Spinner searchType;
 
	private int spinnerPos;
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {

    	final View rootView = inflater.inflate(R.layout.activity_home, container, false);
    	searchType = (Spinner) rootView.findViewById(R.id.spinner_searchCrit);
    	searchType.setOnItemSelectedListener(new OnItemSelectedListener() {


    		public void onItemSelected(AdapterView<?> parent, View view, int pos,
    				long id) {
    			spinnerPos = pos;
    		}

			
			public void onNothingSelected(AdapterView<?> arg0) {
				
			}
		});
       rootView.findViewById(R.id.button_search)
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                       
                    	mEdit = (EditText) rootView.findViewById(R.id.editText_search);
                    	
                    	Intent intent = new Intent(getActivity(), SearchResultActivity.class);
                        intent.putExtra("StrString", mEdit.getText().toString());
                        intent.putExtra("Type", spinnerPos);
                    	startActivity(intent);
                    	                   	
                    	
                    }
                });

       rootView.findViewById(R.id.button_about)
       .setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Intent intent = new Intent(getActivity().getBaseContext(), AboutActivity.class);
               startActivity(intent);
           }
       });
           
       rootView.findViewById(R.id.button_settings)
       .setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Intent intent = new Intent(getActivity(), SettingsActivity.class);
               startActivity(intent);
           }
       });
      return rootView;
    
	}
	
}