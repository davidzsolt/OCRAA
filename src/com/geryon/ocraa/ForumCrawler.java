package com.geryon.ocraa;

import java.util.ArrayList;
import java.util.LinkedHashMap;

public class ForumCrawler {
String const_forum = "http://ocremix.org";

public LinkedHashMap<String, String> getBreadcrumb (String pile){
	LinkedHashMap<String, String> dict = new LinkedHashMap<String, String>();
	String key = "";
	String value = "";
	
	int in1 = 0, in2 = 0;
	String return2 = "";
	StringBuilder strB = new StringBuilder(pile);
	in1 = strB.indexOf("<div class=\"breadcrumb\"");
	in1 = strB.indexOf("href", in1)+1;
	in1 = strB.indexOf("href", in1);
	in2 = strB.indexOf("</div>", in1);
	//return2 += strB.substring(in1, in2);
	strB = new StringBuilder(strB.substring(in1,in2));
	in1 = 0;
	in2 = 0;
	while (strB.indexOf("href", in2) != -1){

		in1 = strB.indexOf("\"",in2);
	
		in2 = strB.indexOf("\"", in1+1);
	//return2 += "in1: "+String.valueOf(in1);
	//return2 += "in2: "+String.valueOf(in2);
	value = const_forum+"/"+strB.substring(in1+1,in2).replace("&amp;", "&")+"\n";
		//return2 += value+"\n";
	in1 = strB.indexOf(">", in2)+1;
	in2 = strB.indexOf("<", in1);
	//return2 += "in1: "+String.valueOf(in1+1);
	//return2 += "in2: "+String.valueOf(in2);
	key = strB.substring(in1, in2).replace("&amp;", "&")+"\n";
	//return2 += key+"\n";
	//in2 = strB.indexOf("</a>",in1);
	//strB = new StringBuilder(strB.delete(in2, strB.length()));
	dict.put(key, value);
	}
	
	//return2 = return2.replace("&amp;", "&");
	///TESTING
	
	/*for (String keys: dict.keySet()) {
		return2 += keys+dict.get(keys);
	    
	}*/
	
	
	return dict;
	
	
}

public LinkedHashMap<String, String> readForum (String pile){
	LinkedHashMap<String, String> Threads = new LinkedHashMap<String, String>();
	
	StringBuilder strB = new StringBuilder(pile);
	int in1, in2;
	if (strB.indexOf("<!-- sub-forum list  -->") != -1){
		in1 = strB.indexOf("<!-- sub-forum list  -->")+25;
		in2 = strB.indexOf("<!-- / sub-forum list  -->");
		
		
		
		
	}
	
	
	
	
	
	
	return Threads;
	
	
}





public ArrayList<String> readThread (String pile){
	ArrayList<String> Messages = new ArrayList<String>();
	int in1 = 0;
	int in2 = 0;
	String message="";
	StringBuilder strB = new StringBuilder(pile);
	while (strB.indexOf("<a class=\"bigusername\"", in1) != -1){
		in1 = strB.indexOf("<a class=\"bigusername\"");
		in1 = strB.indexOf(">", in1)+1;
		in2 = strB.indexOf("<", in1);
		message += strB.substring(in1,in2)+":\n";
	in1 = strB.indexOf("<!-- message -->");
	in2 = strB.indexOf("<!-- / message -->");
	message+= cleaner(strB.substring(in1, in2));
	Messages.add(message);
	
	}
	
	
	return Messages;
	
	
}


public ArrayList<String> mapToArray (LinkedHashMap<String, String> input){
	final ArrayList<String> output = new ArrayList<String>();
	for (String keys: input.keySet()) {
		output.add(keys);
	    
	}
	
	return output;
} 
public boolean isThread (String link){
	
	if (link.contains("showthread")){
		return true;} else
			return false;
}
public String cleaner (String input){
	input.replace("<li>", "\n");
	input.replace("<br>", "\n");
	input.replace("&quot;", "\"");
	StringBuilder strB = new StringBuilder(input);
	int in1, in2;
	while ((in1 = strB.indexOf("<")) != -1){
		in2 = strB.indexOf(">");
		strB = strB.delete(in1, in2+1);
	}
	
	return strB.toString();
	
}

}