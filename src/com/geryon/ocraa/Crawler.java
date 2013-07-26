package com.geryon.ocraa;


//All HTML-code searching ("crawling")

public class Crawler {
	public StringBuilder[] buildData (int repeater, StringBuilder pile){
		
		StringBuilder[] alloc = new StringBuilder[repeater];
		for (int i = 0; i<repeater;i++){
			alloc[i]= new StringBuilder();
		} 
		StringBuilder TempString = new StringBuilder(pile);
		int in1, in2 = 0; //in1 = opening index, in2 = end index for search 
		in1 = pile.indexOf("tbody");
		pile = pile.delete(0, in1); 
		in2 = pile.indexOf("/tbody");
		pile= pile.delete(in2, (TempString.length()-1));
		in1 = 1;
		for (int i=0;i<repeater;i++ ){
		alloc[i].append("<remix>\n");
		
		in1 = pile.indexOf("<a href=\"/game/",in1);
				
		in1 = pile.indexOf(">", in1)+1;
		in2 = pile.indexOf("<", in1)-1;
		alloc[i].append("<game>");
		alloc[i].append(pile.substring(in1, in2));
		alloc[i].append("</game>"+"\n");
		in1 = in2;
		in1 = pile.indexOf("<a href=\"/remix/",in1);
		alloc[i].append("<id>");
		alloc[i].append(pile.substring(in1+19, in1+24));
		alloc[i].append("</id>"+"\n");
		in1 = pile.indexOf("'", in1)+1;
		in2 = pile.indexOf("'", in1);
		alloc[i].append("<title>");
		alloc[i].append(pile.substring(in1, in2));
		alloc[i].append("</title>"+"\n");
		in1 = in2;
		
		
		
		in1 = pile.indexOf("<td>", in1)+4;
		if (pile.charAt(in1) == '\'') {
		do {
		in1 = pile.indexOf("<a href=\"/song/",in1);
		in1 = pile.indexOf(">", in1)+1;
		in2 = pile.indexOf("<", in1);
		
		
		alloc[i].append("<song>");
		alloc[i].append(pile.substring(in1, in2));
		alloc[i].append("</song>"+"\n");	
		in1 = in2;
		} while (pile.charAt(in2+5)== ',');
		}		
		do {
		in1 = pile.indexOf("<a href=\"/artist/",in1);
		in1 = pile.indexOf(">", in1)+1;
		in2 = pile.indexOf("<", in1);
		alloc[i].append("<artist>");
		alloc[i].append(pile.substring(in1, in2));
		alloc[i].append("</artist>"+"\n");
		in1 = in2;
		} while (pile.charAt(in2+4)== ',');
		//alloc[i] += "</remix>\n";
		}
	
		
		return alloc; 
	} //End of builddata
	
	
	public StringBuilder buildsubp (StringBuilder pile){
		int in1 = 0, in2 = 0;
		
		//StringBuilder tempString = new StringBuilder(pile);
		StringBuilder suppSB = new StringBuilder();
		/*in1 = pile.indexOf("a href=\"/game");
		in1 = pile.indexOf(">",in1);
		in2 = pile.indexOf("<",in1);*/
		
		
		//description
		//in1 = tempString.indexOf("<strong>Posted:</strong>");
		in1 = pile.indexOf("<strong>Posted:</strong>");
		//in1 = tempString.indexOf("<p>");
		in1 = pile.indexOf("<p>");
		//in2 = tempString.indexOf("clear:left"); 
		in2 = pile.indexOf("clear:left");
		suppSB = suppSB.append("<desc>");
		
		//suppSB = suppSB.append(tempString.substring(in1+34, in2-39));
		//StringBuilder tempSB = new StringBuilder(tempString.substring(in1+34, in2-39));
		StringBuilder tempSB = new StringBuilder(pile.substring(in1, in2-39));
		while ((in1 = tempSB.indexOf("<p>")) != -1){
			tempSB = tempSB.delete(in1, in1+3);
			tempSB = tempSB.insert(in1, "\n");
		} 
		while ((in1 = tempSB.indexOf("<blockquote>")) != -1){
			tempSB = tempSB.delete(in1, in1+12);
			//tempSB = tempSB.insert(in1, "\n");
		} 
		
		
		while ((in1 = tempSB.indexOf("<")) != -1){
			in2 = tempSB.indexOf(">");
			tempSB = tempSB.delete(in1, in2+1);
		}
		while ((in1 = tempSB.indexOf("\t")) != -1){
			tempSB = tempSB.deleteCharAt(in1);
		
		}
		/*while ((in1 = tempSB.indexOf("&amp;")) != -1){
			tempSB = tempSB.delete(in1, in1+5);
			tempSB = tempSB.insert(in1, '&');
		} */
		
		suppSB.append(tempSB);
		//suppSB.append("in1:"+in1+"\nin2:"+in2);
		
		suppSB = suppSB.append("</desc>\n");
		
		//md5 hash
		//in1 = tempString.indexOf("MD5 Checksum:")+22;
		in1 = pile.indexOf("MD5 Checksum:")+23;
		in2 = in1+32;
		suppSB = suppSB.append("<md5>");
		//suppSB = suppSB.append(tempString.substring(in1, in2));
		suppSB = suppSB.append(pile.substring(in1, in2));
		suppSB = suppSB.append("</md5>\n");
		
		//link one of 4
		
		//in1 = tempString.indexOf("Trouble downloading?");
		in1 = pile.indexOf("Trouble downloading?");
		//in1 = tempString.indexOf("href", in1);
		for (int k=0;k<4;k++)
		{		
			suppSB = suppSB.append("<link>");
		
		in1 = pile.indexOf("href", in1);
		//in2 = tempString.indexOf(".mp3", in1);
		in2 = pile.indexOf(".mp3", in1);
		//suppSB = suppSB.append(tempString.substring(in1+6, in2+4));
		suppSB = suppSB.append(pile.substring(in1+6, in2+4));
		suppSB = suppSB.append("</link>\n");
		in1=in2;
		}
		suppSB = suppSB.append("</remix>\n");
		//	return pile;
		return suppSB;
	}

	
	
public String buildDesc (String pile){
	String desc = "";
	int in1 = 0, in2 = 0;
	StringBuilder TempString = new StringBuilder(pile);
	in1 = TempString.indexOf("<span style=\"font-size:16px;\">");
	in2 = TempString.indexOf("<div style=\"margin-right:350px;clear:left;\">"); 
	desc= TempString.substring(in1, in2);
	return desc;
}

public int buildmd5(String pile){
	int md5 = 0;
	int in1 = 0, in2=0;
	StringBuilder tempString = new StringBuilder(pile);
	in1 = tempString.indexOf("MD5 Checksum: ")+22;
	in2 = in1+32;
	md5 = Integer.parseInt(tempString.substring(in1, in2));
	return md5;
}
	public String[] getdllinks (String pile){
		String dllinks[] = new String[4];
		StringBuilder dllinktemp = new StringBuilder(pile);
		int in1 = 0;
		int in2 = 0;
		in1 = dllinktemp.indexOf("Trouble downloading?");
		for (int i=0;i<4;i++)
		{
		in1 = dllinktemp.indexOf("href", in1);
		in2 = dllinktemp.indexOf(".mp3", in1);
		
		
			dllinks[i] = dllinktemp.substring(in1+6, in2+4);
		in1=in2;
		
		}
		return dllinks;
	}



	public String[] getlinks (StringBuilder pile, int repeater){
		String[] links = new String[repeater] ;
		int in1 = 0, count=0;
		//StringBuilder tempString = new StringBuilder(pile);
		for(count=0;count<repeater;count++){
		//in1 = tempString.indexOf("/remix/O",in1);
			in1 = pile.indexOf("/remix/O",in1);
			in1++;
		//links[count] = buildDummyLink(pile.substring(in1+9, in1+14));
		links[count] = buildLink(pile.substring(in1+9, in1+14));
		}
		
		return links;
	}
	

	public String buildLink (String pile){
		String link = "";
		link = "http://ocremix.org/remix/OCR"+pile+"/";
		return link; 
	}


public String buildDummyLink (String pile){
	String link = "";
	link = "http://127.0.0.1/remix/OCR"+pile+"/";
	return link; 
	}


}