package com.project;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

public class test extends Activity
{

	Bundle g;
	TextView tv;
	 public void onCreate(Bundle savedInstanceState)
	 
	 {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.vieworder);
	        
//	        Toast.makeText(getBaseContext(), "yoyoyo", Toast.LENGTH_SHORT).show();
	        
	        g = getIntent().getExtras();
	        
	        int i,j;
			for(i=0;i<100;i++)
			{
				  if(g.getIntArray("tag")[i]==0)
					  break;
			}
			
			
			
			HttpClient httpclient = new DefaultHttpClient();
			HttpGet httpget = null;

			
			
			for(j=0;j<i;j++)
			{
				int cat = g.getIntArray("tag")[j];
				String name = g.getStringArray("name")[j];
				int qty = g.getIntArray("qty")[j];

				if(cat==1)
	        	{
					
	        		httpget = new HttpGet("http://10.0.2.2/lab/appetizers.json");
	        	//	Toast.makeText(getBaseContext(), Integer.toString(cat), Toast.LENGTH_SHORT).show();
	        		
//	        		imageUrl="http://10.0.2.2/lab/appetizers/";
	        	}
	        else if(cat==2)
	        	{
	        		httpget = new HttpGet("http://10.0.2.2/lab/beverages.json");
//	        		Toast.makeText(getBaseContext(), Integer.toString(cat), Toast.LENGTH_SHORT).show();
	        		
	  //      		imageUrl="http://10.0.2.2/lab/beverages/";
	        	}
	        else if(cat==3)
	        	{
//	        	Toast.makeText(getBaseContext(), Integer.toString(cat), Toast.LENGTH_SHORT).show();
        		
	        		httpget = new HttpGet("http://10.0.2.2/lab/soups.json");
	    //    		imageUrl="http://10.0.2.2/lab/soups/";
	        	}
	        else if(cat==4)
	        	{
//	        	Toast.makeText(getBaseContext(), Integer.toString(cat), Toast.LENGTH_SHORT).show();
        		
	        		httpget = new HttpGet("http://10.0.2.2/lab/maincourse.json");
	      //  		imageUrl="http://10.0.2.2/lab/maincourse/";
	        	}
	        else if(cat==5)
	        	{	
//	        	Toast.makeText(getBaseContext(), Integer.toString(cat), Toast.LENGTH_SHORT).show();
        		
	        		httpget = new HttpGet("http://10.0.2.2/lab/salad.json");
	        //		imageUrl="http://10.0.2.2/lab/salad/";
	        	}
	        else if(cat==6)
	        	{
//	        	Toast.makeText(getBaseContext(), Integer.toString(cat), Toast.LENGTH_SHORT).show();
        		
	        		httpget = new HttpGet("http://10.0.2.2/lab/desserts.json");
	        	//	imageUrl="http://10.0.2.2/lab/desserts/";
	        	}
				
				
				
				String build = null;
		        
		        try {

		    		HttpResponse response = httpclient.execute(httpget);
		    		HttpEntity entity = response.getEntity();
				
					if (entity != null) {
						InputStream instream = entity.getContent();
						BufferedReader str = new BufferedReader(new InputStreamReader(
								instream));
			
						String ans = new String("");
						build = new String("");
						while ((ans = str.readLine()) != null) {
							build = build + ans;
							Log.d("JSON", ans);
						}

					}
					
					
				} catch (ClientProtocolException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
				
				try
		    	{	
				JSONArray arr = new JSONArray(build);
				JSONObject food = null;
				
				
				String arrlen = Integer.toString(arr.length());

				
				
				
				for(int l=1;l<arr.length();l++)
				{
					food = arr.getJSONObject(l);
					String name1 = food.getString("name");
				//	Toast.makeText(getApplicationContext(), name1+" NO", Toast.LENGTH_LONG).show();
					
					if(name.equals(name1))
					{
						String price = food.getString("price");
						int pri = Integer.parseInt(price);
						int amount = pri * qty;
						Toast.makeText(getApplicationContext(), name+" Yes", Toast.LENGTH_LONG).show();
						
					//	adapter.add(name+Integer.toString(amount));
						break;
					}
				}

			} catch (JSONException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			
			}	

				
			}
	        
	   
	
	 }
	 
	 
	 
}
