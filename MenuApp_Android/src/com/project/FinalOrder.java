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
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class FinalOrder extends Activity {
	
	Button menuButton, homeButton, callwaiterButton, orderButton, helpButton, endButton;
	Intent homeit, menuit, orderit, helpit, endit;
	TextView taxText, totalText, disText;
	Bundle g;
	ListView lv1;
	ArrayList<menulist1> items = new ArrayList<menulist1>();
	
	@Override
	public void onBackPressed() {
    return;
    }
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.finalorder);
        
        
        g = getIntent().getExtras();
        
        homeButton = (Button)findViewById(R.id.Button01);
        menuButton = (Button)findViewById(R.id.Button02);
        orderButton = (Button)findViewById(R.id.Button03);
        helpButton = (Button)findViewById(R.id.Button04);
        callwaiterButton = (Button)findViewById(R.id.Button05);
        endButton = (Button)findViewById(R.id.Button06);
        lv1 = (ListView)findViewById(R.id.ListView01); 
        taxText = (TextView)findViewById(R.id.TextView02);
        totalText = (TextView)findViewById(R.id.TextView03);
        disText = (TextView)findViewById(R.id.TextView04);
        
        homeit = new Intent(getBaseContext(), main.class);
        menuit = new Intent(getBaseContext(), Menu.class);
        helpit = new Intent(getBaseContext(), Help.class);
        orderit = new Intent(getBaseContext(), ViewOrder.class);
        endit = new Intent(getBaseContext(), First.class);
        
//        homeButton.setOnClickListener((OnClickListener) new homeListener());
//        menuButton.setOnClickListener((OnClickListener) new menuListener());
//        orderButton.setOnClickListener((OnClickListener) new orderListener());
//        helpButton.setOnClickListener((OnClickListener) new helpListener());
        callwaiterButton.setOnClickListener((OnClickListener) new callwaiterListener());
        endButton.setOnClickListener((OnClickListener) new endListener());
        
        
        int i,j;
        double tax=0, total=0;
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
        		httpget = new HttpGet("http://192.168.11.3/lab/appetizers.json");
        	}
        else if(cat==2)
        	{
        		httpget = new HttpGet("http://192.168.11.3/lab/beverages.json");
        	}
        else if(cat==3)
        	{
        		httpget = new HttpGet("http://192.168.11.3/lab/soups.json");
        	}
        else if(cat==4)
        	{
        		httpget = new HttpGet("http://192.168.11.3/lab/maincourse.json");
        	}
        else if(cat==5)
        	{	
        		httpget = new HttpGet("http://192.168.11.3/lab/salad.json");
        	}
        else if(cat==6)
        	{
        		httpget = new HttpGet("http://192.168.11.3/lab/desserts.json");
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
			String arrlen = Integer.toString(arr.length());

			for(int l=1;l<arr.length();l++)
			{
				JSONObject food = null;
				food = arr.getJSONObject(l);
				String name1 = food.getString("name");
				
				if(name.equals(name1))
				{
					String price = food.getString("price");
					int pri = Integer.parseInt(price);
					int amount = pri * qty;
					total = total + amount;
					menulist1 sr1 = new menulist1();
					sr1.setName(name);
				     sr1.setquantity(Integer.toString(qty));
				     sr1.setamount("Rs. " + Integer.toString(amount));
				     items.add(sr1);
					break;
				}
			}

		} catch (JSONException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
		
		}
		
		}
		
		HttpClient httpclient1 = new DefaultHttpClient();
		HttpGet httpget1 = null;
		
		httpget1 = new HttpGet("http://192.168.11.3/lab/rates.json");
		
		String build1 = null;
		double taxrate=0, discount=0;
		String dis=null, tax1=null;
		
        try {

    		HttpResponse response = httpclient1.execute(httpget1);
    		HttpEntity entity = response.getEntity();
		
			if (entity != null) {
				InputStream instream = entity.getContent();
				BufferedReader str = new BufferedReader(new InputStreamReader(
						instream));
	
				String ans = new String("");
				build1 = new String("");
				while ((ans = str.readLine()) != null) {
					build1 = build1 + ans;
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
		JSONArray arr = new JSONArray(build1);
		String arrlen = Integer.toString(arr.length());
		
		JSONObject rates = null;
		rates = arr.getJSONObject(0);
		dis = rates.getString("discount");
		tax1 = rates.getString("tax");
		discount = Double.parseDouble(dis);
		taxrate = Double.parseDouble(tax1);
		
		

	} catch (JSONException e1) {
	// TODO Auto-generated catch block
	e1.printStackTrace();
	
	}

		
		
		lv1.setAdapter(new orderadapter(this, items));
		
		double dis1 = (discount/100)*total;
		total = total - dis1;
        tax = (taxrate/100) * total;
        total = total + tax;
        disText.setText("Discount (@"+dis+"%) = Rs. "+Double.toString(dis1));
        taxText.setText("Tax (@12.5%) = Rs. " + Double.toString(tax));
        totalText.setText("Total = Rs. " + Double.toString(total));
        
        
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
    	

	private class endListener  implements OnClickListener {

		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			
			Toast.makeText(getApplicationContext(), "Ending Session...", Toast.LENGTH_SHORT).show();
			startActivity(endit);
		}
    	
    }
    
}
