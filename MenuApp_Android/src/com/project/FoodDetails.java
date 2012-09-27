package com.project;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.UUID;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class FoodDetails extends Activity {
	
	Button menuButton, homeButton, callwaiterButton, orderButton, helpButton;
	Intent homeit, menuit, orderit, helpit, testing, appetizerit, beveragesit, soupsit, maincourseit, saladit, dessertit;
	ImageView im1;
	private String[] filepath;
	public Bitmap downloadedBitmap;
	Bundle g;
	TextView nametext, ratingtext, detailstext, ingredientstext, cooktimetext, pricetext;
	EditText qty;
	Button add;
	int cat;
	String name;
	int price1;
	
	
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fooddetails); 
    
        nametext = (TextView)findViewById(R.id.TextView01);
        ratingtext = (TextView)findViewById(R.id.TextView02);
        detailstext = (TextView)findViewById(R.id.TextView03);
        ingredientstext = (TextView)findViewById(R.id.TextView04);
        cooktimetext = (TextView)findViewById(R.id.TextView05);
        pricetext = (TextView)findViewById(R.id.TextView06);
        qty = (EditText)findViewById(R.id.EditText01);
        add = (Button)findViewById(R.id.Button06);
        
        appetizerit = new Intent(getBaseContext(), Appetizers.class);
        beveragesit = new Intent(getBaseContext(), Beverages.class);
        soupsit = new Intent(getBaseContext(), Soups.class);
        maincourseit = new Intent(getBaseContext(), MainCourse.class);
        saladit = new Intent(getBaseContext(), Salad.class);
        dessertit = new Intent(getBaseContext(), Dessert.class);
        
        
        g = getIntent().getExtras();
        
        cat = getIntent().getIntExtra("category", 0); 
        name = getIntent().getStringExtra("food");
        
        String categ = Integer.toString(cat);
        String imageUrl = null;
        
        HttpClient httpclient = new DefaultHttpClient();
        HttpGet httpget = null;
        
        
        if(cat==1)
        	{
        		httpget = new HttpGet("http://192.168.11.3/lab/appetizers.json");
        		imageUrl="http://192.168.11.3/lab/appetizers/";
        	}
        else if(cat==2)
        	{
        		httpget = new HttpGet("http://192.168.11.3/lab/beverages.json");
        		imageUrl="http://192.168.11.3/lab/beverages/";
        	}
        else if(cat==3)
        	{
        		httpget = new HttpGet("http://192.168.11.3/lab/soups.json");
        		imageUrl="http://192.168.11.3/lab/soups/";
        	}
        else if(cat==4)
        	{
        		httpget = new HttpGet("http://192.168.11.3/lab/maincourse.json");
        		imageUrl="http://192.168.11.3/lab/maincourse/";
        	}
        else if(cat==5)
        	{	
        		httpget = new HttpGet("http://192.168.11.3/lab/salad.json");
        		imageUrl="http://192.168.11.3/lab/salads/";
        	}
        else if(cat==6)
        	{
        		httpget = new HttpGet("http://192.168.11.3/lab/desserts.json");
        		imageUrl="http://192.168.11.3/lab/desserts/";
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
		//String arrlen = Integer.toString(arr.length());
		JSONObject na = arr.getJSONObject(0);
		JSONArray ingna = na.getJSONArray("unavailable");
		
		String [] ingr = new String[ingna.length()];
		for(int k=0;k<ingna.length();k++)
		{
			JSONObject abc = ingna.getJSONObject(k);
			ingr[k] = abc.getString("ingredient");
		}
		for(int i=1;i<arr.length();i++)
		{
			JSONObject food = null;
			food = arr.getJSONObject(i);
			String name1 = food.getString("name");
			
			if(name.equals(name1))
			{
				imageUrl = imageUrl + i;
				nametext.setText("Name : "+name);
				String description = food.getString("description");
				detailstext.setText("Description : "+description);
				String rating = food.getString("rating");
				String price = food.getString("price");
				price1 = Integer.parseInt(price);
				pricetext.setText("Price : Rs. "+price);
				ratingtext.setText("Rating : "+rating+" stars");
				String cooktime = food.getString("cooktime");
				cooktimetext.setText("Cooktime : "+cooktime);
				JSONArray ingredients = food.getJSONArray("ingredients");
				String [] ing = new String[ingredients.length()];
				for(int k=0;k<ingredients.length();k++)
				{
					JSONObject ingd = ingredients.getJSONObject(k);
					ing[k] = ingd.getString("ingredient");
				}	
				
				String ingre = "Ingredients:";
				
				for(int k=0;k<ing.length;k++)
				{
					if(k<(ing.length-1))
						ingre = ingre+" "+ing[k]+",";
					else
						ingre = ingre+" "+ing[k];
				}
				
				ingredientstext.setText(ingre);
				break;
			}
		}

	} catch (JSONException e1) {
	// TODO Auto-generated catch block
	e1.printStackTrace();
	
	}	
        
        Bitmap bitmap = null;
        
        ImageView i = (ImageView)findViewById(R.id.ImageView02);        
        try 
		{
        	
        	  URL aURL = new URL(imageUrl+".png");
              URLConnection conn = aURL.openConnection();
              conn.connect();
              InputStream is = conn.getInputStream();
              /* Buffered is always good for a performance plus. */
              BufferedInputStream bis = new BufferedInputStream(is);
              /* Decode url-data to a bitmap. */
              Bitmap bm = BitmapFactory.decodeStream(bis);
              bis.close();
              is.close();
              
              i.setImageBitmap(bm);	
			
		} 
		catch (IOException e) 
		{
			im1.setImageResource(R.drawable.imagenotfound);
			
		}
		
        
        
        homeButton = (Button)findViewById(R.id.Button01);
        menuButton = (Button)findViewById(R.id.Button02);
        orderButton = (Button)findViewById(R.id.Button03);
        helpButton = (Button)findViewById(R.id.Button04);
        im1 = (ImageView)findViewById(R.id.ImageView02);
        callwaiterButton = (Button)findViewById(R.id.Button05);
        homeit = new Intent(getBaseContext(), main.class);
        menuit = new Intent(getBaseContext(), Menu.class);
        helpit = new Intent(getBaseContext(), Help.class);
        orderit = new Intent(getBaseContext(), ViewOrder.class);
        //testing = new Intent(getBaseContext(), test.class);
        
        homeButton.setOnClickListener((OnClickListener) new homeListener());
        menuButton.setOnClickListener((OnClickListener) new menuListener());
        orderButton.setOnClickListener((OnClickListener) new orderListener());
        helpButton.setOnClickListener((OnClickListener) new helpListener());
        callwaiterButton.setOnClickListener((OnClickListener) new callwaiterListener());
        add.setOnClickListener((OnClickListener) new addListener());
        
        qty.setOnClickListener((OnClickListener) new qtyListener());
        
        
    }
   
    
    @Override
	public void onBackPressed() {
		
    	if(cat==1){
    		appetizerit.putExtras(g);
    		startActivity(appetizerit);
    	}
    	else if(cat==2){
    		beveragesit.putExtras(g);
    		startActivity(beveragesit);
    	}
    	else if(cat==3){
    		soupsit.putExtras(g);
    		startActivity(soupsit);
    	}
    	else if(cat==4){
    		maincourseit.putExtras(g);
    		startActivity(maincourseit);
    	}
    	else if(cat==5){
    		saladit.putExtras(g);
    		startActivity(saladit);
    	}
    	else if(cat==6){
    		dessertit.putExtras(g);
    		startActivity(dessertit);
    	}
		
	   return;
	}
	
    
	private class homeListener  implements OnClickListener {

		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			
			Toast.makeText(getApplicationContext(), "Home Screen Loading...", Toast.LENGTH_SHORT).show();
			homeit.putExtras(g);
			startActivity(homeit);
		}
    	
    }
	
	private class qtyListener  implements OnClickListener {

		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			
			
			qty.setText("");
		}
    	
    }
    
    private class menuListener  implements OnClickListener {

		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			
			Toast.makeText(getApplicationContext(), "Loading Menu...", Toast.LENGTH_SHORT).show();
			menuit.putExtras(g);
			startActivity(menuit);
		}
    	
    }

        private class orderListener  implements OnClickListener {

		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			
			
			Toast.makeText(getApplicationContext(), "Loading your current Order...", Toast.LENGTH_SHORT).show();
			
		orderit.putExtras(g);
		startActivity(orderit);
		
		}
    	
    }

    
    private class helpListener  implements OnClickListener {

		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			
			Toast.makeText(getApplicationContext(), "Loading Help Screen...", Toast.LENGTH_SHORT).show();
			helpit.putExtras(g);
			startActivity(helpit);
		}
    	
    }
    
    private class callwaiterListener  implements OnClickListener {

		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			
			final TelephonyManager tm = (TelephonyManager) getBaseContext().getSystemService(Context.TELEPHONY_SERVICE);
			ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
			final String tmDevice, tmSerial, tmPhone, androidId;
			tmDevice = "" + tm.getDeviceId();
			tmSerial = "" + tm.getSimSerialNumber();
			androidId = "" + android.provider.Settings.Secure.getString(getContentResolver(), android.provider.Settings.Secure.ANDROID_ID);

			UUID deviceUuid = new UUID(androidId.hashCode(), ((long)tmDevice.hashCode() << 32) | tmSerial.hashCode());
			String deviceId = deviceUuid.toString();




			nameValuePairs.add(new BasicNameValuePair("deviceId", deviceId));
			System.out.println(deviceId);


			try
			{

			HttpClient httpclient = new DefaultHttpClient();
			HttpPost httppost = new HttpPost("http://192.168.11.3/lab/alert.php");
			httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
			HttpResponse response = httpclient.execute(httppost);
			HttpEntity entity = response.getEntity();


			Toast.makeText(getApplicationContext(), "Calling Waiter...", Toast.LENGTH_LONG).show();
			}
			catch(Exception e)
			{
			Toast.makeText(getBaseContext(),"Error in http connection "+e.toString(), Toast.LENGTH_LONG).show();
			}
			finally{
			}

			}

		
		}
    	
    
    private class addListener  implements OnClickListener {

		@Override
		public void onClick(View arg0) {
			
			// TODO Auto-generated method stub
			String s = qty.getEditableText().toString();
			if((s.matches("[0]*[0-9]{3}") || s.matches("[0]*[0-9]{2}") || s.matches("[0]*[0-9]{1}")) && !(s.matches("[0]*")))
			{
				int av = Integer.parseInt(s);
				int flag2=0;
				if(av<100)
				{
					int z, flag=0;
					
					for(z=0;z<100;z++)
					{
						if(g.getIntArray("tag")[z]==0)
							break;
					}
					
					String quan=null;
					
					for(int k=0;k<z;k++)
					{
						if(name.equals(g.getStringArray("name")[k]))
						{
							Editable quant = qty.getText();
							quan = quant.toString();
							int qu = g.getIntArray("qty")[k] + Integer.parseInt(quan);
							int qu1 = Integer.parseInt(quan);
							if(qu<100)
							{
								quan = Integer.toString(qu1);
								g.getIntArray("tag")[k]=cat;
								g.getStringArray("name")[k]=name;
								g.getIntArray("qty")[k]=qu;
								g.getIntArray("amount")[k]=qu*price1;
								flag=1;
							}	
							else
							{
								Toast.makeText(getApplicationContext(),"Maximum quatity allowed is 99!",Toast.LENGTH_SHORT).show();
								flag=1;
								flag2=1;
							}
						}
					}
				
					if(flag==0)
					{
						Editable quant = qty.getText();
						quan = quant.toString();
						int qu = Integer.parseInt(quan);
						quan = Integer.toString(qu);
						g.getIntArray("tag")[z]=cat;
						g.getStringArray("name")[z]=name;
						g.getIntArray("qty")[z]=Integer.parseInt(quan);
						g.getIntArray("amount")[z]=qu*price1;
						
					}
					if(flag2==0)
					{
						if(Integer.parseInt(quan)==1)
							Toast.makeText(getApplicationContext(),"Added "+quan+" "+name+" to your order!",Toast.LENGTH_SHORT).show();
						else	
							Toast.makeText(getApplicationContext(),"Added "+quan+" "+name+"s to your order!",Toast.LENGTH_SHORT).show();
					}
					
					menuit.putExtras(g);
					startActivity(menuit);
				}
				else
					Toast.makeText(getApplicationContext(),"Please enter quantity less than 100",Toast.LENGTH_SHORT).show();
			}
			
			else
			{
				Toast.makeText(getApplicationContext(),"Please enter a valid quantity",Toast.LENGTH_SHORT).show();
				
			}
			
		}
    	
    }    

}
