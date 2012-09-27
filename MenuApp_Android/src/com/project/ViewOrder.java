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
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class ViewOrder extends Activity {
	
	Button menuButton, homeButton, callwaiterButton, orderButton, helpButton, confirmButton;
	Intent homeit, menuit, orderit, helpit, confirmit;
	Bundle g;
	ListView lv1;
	ArrayAdapter<String> adapter;
	TextView totalText;
	ArrayList<menulist1> items = new ArrayList<menulist1>();
	AlertDialog.Builder alert;
	String Build = null;
	int k, z, pri;

	
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vieworder);
        int cost;
       
        
        totalText = (TextView)findViewById(R.id.TextView02);
          lv1 = (ListView)findViewById(R.id.ListView01); 
        
        
        g = getIntent().getExtras();
        int i,j, total=0;
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
					pri = Integer.parseInt(price);
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
        
		totalText.setText("Total : Rs. "+Integer.toString(total));
        
        
        homeButton = (Button)findViewById(R.id.Button01);
        menuButton = (Button)findViewById(R.id.Button02);
        orderButton = (Button)findViewById(R.id.Button03);
        helpButton = (Button)findViewById(R.id.Button04);
        callwaiterButton = (Button)findViewById(R.id.Button05);
        confirmButton = (Button)findViewById(R.id.Button06);
  
        homeit = new Intent(getBaseContext(), main.class);
        menuit = new Intent(getBaseContext(), Menu.class);
        helpit = new Intent(getBaseContext(), Help.class);
        orderit = new Intent(getBaseContext(), ViewOrder.class);
        confirmit = new Intent(getBaseContext(), FinalOrder.class);
 
        
        homeButton.setOnClickListener((OnClickListener) new homeListener());
        menuButton.setOnClickListener((OnClickListener) new menuListener());
        orderButton.setOnClickListener((OnClickListener) new orderListener());
        helpButton.setOnClickListener((OnClickListener) new helpListener());
        callwaiterButton.setOnClickListener((OnClickListener) new callwaiterListener());
        confirmButton.setOnClickListener((OnClickListener) new confirmListener());
        
        lv1.setAdapter(new orderadapter(this, items));
    	
        lv1.setOnItemClickListener((OnItemClickListener) new lissListener());
        
        
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
    
    public class lissListener implements OnItemClickListener {
      	 public void onItemLongClick1(AdapterView<?> parent, View view,
                   int position, long id) {
        		
      	 }

   	@Override
   	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
   			long arg3) {
   		// TODO Auto-generated method stub
   		menulist1 sr1 = items.get(arg2);
   		
   		String nn=sr1.getName();
   		
   		for(z=0;z<100;z++)
   		{
   			if(g.getIntArray("tag")[z]==0)
   				break;
   		}
   		
   		
   		
   		for(k=0;k<z;k++)
   		{
   			if(nn.equals(g.getStringArray("name")[k]))
   				break;
   		}
   		
   		int q = g.getIntArray("qty")[k];
   		alert = new AlertDialog.Builder(ViewOrder.this);

		
	alert.setTitle("Edit Order - "+nn);
	alert.setMessage("Enter new quantity, or press remove to remove "+nn+" from your order\nCurrent quantity is : "+q);

	 //Set an EditText view to get user input 
	final EditText input = new EditText(getBaseContext());
	input.setInputType(InputType.TYPE_CLASS_NUMBER);
//	DialogInterface.OnClickListener
	alert.setView(input);
	


	alert.setPositiveButton("Change", new DialogInterface.OnClickListener() {
	public void onClick(DialogInterface dialog, int whichButton) {
	  String value = input.getText().toString();
	  if((value.matches("[0]*[0-9]{3}") || value.matches("[0]*[0-9]{2}") || value.matches("[0]*[0-9]{1}")) && !(value.matches("[0]*")))
	  {
		  int abc = Integer.parseInt(value);
		  if(abc<100)
		  {
			  g.getIntArray("qty")[k]=Integer.parseInt(value);
			  g.getIntArray("amount")[k]=Integer.parseInt(value)*pri;
			  orderit.putExtras(g);
			  startActivity(orderit);
		  }
		  else
			  Toast.makeText(getApplicationContext(), "Enter a quantity less than 100", Toast.LENGTH_SHORT).show();
	  }
	  else
		  Toast.makeText(getApplicationContext(), "Enter a valid quantity!", Toast.LENGTH_SHORT).show();
		  
	  }
	  
	});
	
	alert.setNeutralButton("Remove", new DialogInterface.OnClickListener() {
		public void onClick(DialogInterface dialog, int whichButton) {
		  for(int m=k;k<z-1;k++)
		  {
			  g.getIntArray("tag")[m]=g.getIntArray("tag")[m+1];
			  g.getIntArray("qty")[m]=g.getIntArray("qty")[m+1];
			  g.getStringArray("name")[m]=g.getStringArray("name")[m+1];
			  g.getIntArray("amount")[m]=g.getIntArray("amount")[m+1];
		  }

			  g.getIntArray("tag")[z-1]=0;
			  orderit.putExtras(g);
			  startActivity(orderit);
			  
		  }
		  
		});
	alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
	  public void onClick(DialogInterface dialog, int whichButton) {
	    // Canceled.
	  }
	});

	alert.show();
   		
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
    	
    private class confirmListener  implements OnClickListener {

		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			
			int p;
			for(p=0;p<100;p++)
  		   {
  			   if(g.getIntArray("tag")[p]==0)
  				   break;
  		   }
			
			if(p>=1)
			{
			  ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
     		   int i;
     		   for(i=0;i<100;i++)
     		   {
     			   if(g.getIntArray("tag")[i]==0)
     				   break;
     		   }
     		   
     		   int k = 0;
     		   
     		final TelephonyManager tm = (TelephonyManager) getBaseContext().getSystemService(Context.TELEPHONY_SERVICE);
     		
   	        final String tmDevice, tmSerial, tmPhone, androidId;
   	        tmDevice = "" + tm.getDeviceId();
   	        tmSerial = "" + tm.getSimSerialNumber();
   	        androidId = "" + android.provider.Settings.Secure.getString(getContentResolver(), android.provider.Settings.Secure.ANDROID_ID);

   	        UUID deviceUuid = new UUID(androidId.hashCode(), ((long)tmDevice.hashCode() << 32) | tmSerial.hashCode());
   	        String deviceId = deviceUuid.toString();
     		 
     		   String name="";
     		   String amount="";
     		   String qty="";
     		   
     		   while(k<i)
     		   {      			   
     			 
     			   name = g.getStringArray("name")[k];
     			   amount = Integer.toString(g.getIntArray("amount")[k]);
     			   qty = Integer.toString(g.getIntArray("qty")[k]);
     			   ++k; 
     		      
     			nameValuePairs.add(new BasicNameValuePair("name", name));
			    nameValuePairs.add(new BasicNameValuePair("price", amount));
			    nameValuePairs.add(new BasicNameValuePair("qty", qty));
			    nameValuePairs.add(new BasicNameValuePair("deviceId", deviceId));	   
			    System.out.println(deviceId);
     		   try
     		   {

     			   HttpClient httpclient = new DefaultHttpClient();
     			   HttpPost httppost = new HttpPost("http://192.168.11.3/lab/test.php");
     			   httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
     			   HttpResponse response = httpclient.execute(httppost);
                  HttpEntity entity = response.getEntity();

                  InputStream is = entity.getContent();
                  byte[] data = new byte[1];
                         
                  int len = 0;
                  int b = 0;
               
                  String temp = "";
     		   }
     		   catch(Exception e)
     		   {
     			   Toast.makeText(getBaseContext(),"Error in http connection "+e.toString(), Toast.LENGTH_LONG).show();
     		   }
     		   finally{
     		   }
      
     		   }
     
	      	
       Toast.makeText(getApplicationContext(), "Confirming your order...", Toast.LENGTH_SHORT).show();
			confirmit.putExtras(g);
			startActivity(confirmit);
		}
			else
				Toast.makeText(getApplicationContext(), "Please add something to your order to confirm it!", Toast.LENGTH_LONG).show();
		}
    }

    @Override
	public void onBackPressed() {
		menuit.putExtras(g);
		startActivity(menuit);
	   return;
	}
    
}
