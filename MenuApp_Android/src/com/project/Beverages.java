package com.project;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
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
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

public class Beverages extends Activity {
	
    /** Called when the activity is first created. */
	
	Button menuButton, homeButton, callwaiterButton, orderButton, helpButton;
	Intent homeit, menuit, orderit, helpit, detailsit;
	ListView lv1;
	ArrayAdapter<String> adapter;
	Bundle g;
	  String build = null;

 	 	ArrayList<menulist> items = new ArrayList<menulist>();
	  	AlertDialog.Builder alert;
	boolean temp=true;
	int z;
	int argv;
	


	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menulist);
        
        g = getIntent().getExtras();
        
        homeButton = (Button)findViewById(R.id.Button01);
        menuButton = (Button)findViewById(R.id.Button02);
        orderButton = (Button)findViewById(R.id.Button03);
        helpButton = (Button)findViewById(R.id.Button04);
        callwaiterButton = (Button)findViewById(R.id.Button05);
        homeit = new Intent(getBaseContext(), main.class);
        menuit = new Intent(getBaseContext(), Menu.class);
        helpit = new Intent(getBaseContext(), Help.class);
        orderit = new Intent(getBaseContext(), ViewOrder.class);
        detailsit  = new Intent(getBaseContext(), FoodDetails.class);
        
        final ListView lv1 = (ListView) findViewById(R.id.menu);

		
		HttpClient httpclient = new DefaultHttpClient();
        //HttpGet httpget = new HttpGet("http://192.168.11.3/lab/beverages.json");
		HttpGet httpget = new HttpGet("http://192.168.11.3/lab/beverages.json");
        
      
        
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
			String arrlen = Integer.toString(arr.length());
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
				String name = food.getString("name");
				String description = food.getString("description");
				String rating = food.getString("rating");
				String price = food.getString("price");
				String cooktime = food.getString("cooktime");
				JSONArray ingredients = food.getJSONArray("ingredients");
				String [] ing = new String[ingredients.length()];
				for(int k=0;k<ingredients.length();k++)
				{
					JSONObject ingd = ingredients.getJSONObject(k);
					ing[k] = ingd.getString("ingredient");
				}
				
				int flag=0;
				
				for(int l=0;l<ing.length;l++)
				{
					for(int m=0;m<ingr.length;m++)
					{
						if(ing[l].matches(ingr[m])) flag=1;
					}
				}
				
				 
				if(flag==0)
				{

					menulist sr1 = new menulist();
					sr1.setName(name);
				     sr1.setdescription(description);
				     sr1.setprice("Rs. "+price);
				     items.add(sr1);
				    
					}
		
			}
		} catch (JSONException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
		
		}	
    	
	       
        
		lv1.setAdapter(new menuadapter(this, items));
	           
        lv1.setOnItemClickListener((OnItemClickListener) new Listener1());
        
        homeButton.setOnClickListener((OnClickListener) new homeListener());
        menuButton.setOnClickListener((OnClickListener) new menuListener());
        orderButton.setOnClickListener((OnClickListener) new orderListener());
        helpButton.setOnClickListener((OnClickListener) new helpListener());
        callwaiterButton.setOnClickListener((OnClickListener) new callwaiterListener());
        
    }
    
    
    public class Listener1 implements OnItemClickListener {
   	 public void onItemLongClick1(AdapterView<?> parent, View view,
                int position, long id) {
     		
   	 }

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
			long arg3) {
		// TODO Auto-generated method stub
		menulist sr1 = items.get(arg2);
		
		String nn=sr1.getName();
		int cat = 2;
		detailsit.putExtras(g);
		detailsit.putExtra("category", cat);
		detailsit.putExtra("food", nn);
		
		startActivity(detailsit);
	}
   	
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
    	
    

	@Override
	public void onBackPressed() {
		menuit.putExtras(g);
		startActivity(menuit);
	   return;
	}
}
