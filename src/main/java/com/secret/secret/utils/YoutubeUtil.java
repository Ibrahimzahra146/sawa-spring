package com.secret.secret.utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.json.JSONObject;
/*
 * {
    "author_name": "AJ+ كبريت",
    "thumbnail_url": "https://i.ytimg.com/vi/9FLoZegGlzA/hqdefault.jpg",
    "provider_url": "https://www.youtube.com/",
    "type": "video",
    "version": "1.0",
    "width": 480,
    "title": "السليط الإخباري -  أصابع أجنبية | الحلقة (18) الموسم الرابع",
    "thumbnail_width": 480,
    "thumbnail_height": 360,
    "html": "<iframe width=\"480\" height=\"270\" src=\"https://www.youtube.com/embed/9FLoZegGlzA?feature=oembed\" frameborder=\"0\" gesture=\"media\" allowfullscreen></iframe>",
    "height": 270,
    "provider_name": "YouTube",
    "author_url": "https://www.youtube.com/channel/UC-4KnPMmZzwAzW7SbVATUZQ"
}
 * */
import org.springframework.beans.factory.annotation.Autowired;

import com.secret.secret.model.YoutubeLink;
import com.secret.secret.repository.ImageRepository;
import com.secret.secret.repository.YoutubeLinkRepository;

public class YoutubeUtil {
   
	
	@Autowired
	YoutubeLinkRepository youtubeLinkRep;
	public YoutubeLink getVideoInfo(String youtubeUrl) throws Exception{
	    YoutubeLink youtubeLink=new YoutubeLink();
	  
		String url ="http://www.youtube.com/oembed?url="+ youtubeUrl+"&format=json";

		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();

		// optional default is GET
		con.setRequestMethod("GET");

		//add request header
		//con.setRequestProperty("User-Agent", USER_AGENT);

		int responseCode = con.getResponseCode();
		System.out.println("\nSending 'GET' request to URL : " + url);
		System.out.println("Response Code : " + responseCode);

		BufferedReader in = new BufferedReader(
		        new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();

		//print result
		System.out.println(response.toString());
	    JSONObject jsonObject = new JSONObject(response.toString());
	    String author_name=jsonObject.getString("author_name");
	    String image=jsonObject.getString("thumbnail_url");
	    String title=jsonObject.getString("title");
	    
	    youtubeLink.setAuthor_name(author_name);
	    youtubeLink.setImage(image);
	    youtubeLink.setTitle(title);
	    youtubeLink.setLink(youtubeUrl);
	 
    
		return youtubeLink;
	}

}
