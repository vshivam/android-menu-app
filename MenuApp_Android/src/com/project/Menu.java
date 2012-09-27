package com.project;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.UUID;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class Menu extends Activity {
	
	Button menuButton, homeButton, callwaiterButton, orderButton, helpButton, appetizerButton, beveragesButton, soupsButton, maincourseButton, saladButton, dessertButton;
	Intent homeit, menuit, orderit, helpit, appetizerit, beveragesit, soupsit, maincourseit, saladit, dessertit;
	Bundle g;
	

	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu);
        
        g = getIntent().getExtras();
        
        homeButton = (Button)findViewById(R.id.Button01);
        menuButton = (Button)findViewById(R.id.Button02);
        orderButton = (Button)findViewById(R.id.Button03);
        helpButton = (Button)findViewById(R.id.Button04);
        callwaiterButton = (Button)findViewById(R.id.Button05);
        appetizerButton = (Button)findViewById(R.id.Button06);
        beveragesButton = (Button)findViewById(R.id.Button07);
        soupsButton = (Button)findViewById(R.id.Button08);
        maincourseButton = (Button)findViewById(R.id.Button09);
        saladButton = (Button)findViewById(R.id.Button10);
        dessertButton = (Button)findViewById(R.id.Button11);
        homeit = new Intent(getBaseContext(), main.class);
        menuit = new Intent(getBaseContext(), Menu.class);
        helpit = new Intent(getBaseContext(), Help.class);
        orderit = new Intent(getBaseContext(), ViewOrder.class);
        appetizerit = new Intent(getBaseContext(), Appetizers.class);
        beveragesit = new Intent(getBaseContext(), Beverages.class);
        soupsit = new Intent(getBaseContext(), Soups.class);
        maincourseit = new Intent(getBaseContext(), MainCourse.class);
        saladit = new Intent(getBaseContext(), Salad.class);
        dessertit = new Intent(getBaseContext(), Dessert.class);
        

        
        homeButton.setOnClickListener((OnClickListener) new homeListener());
        menuButton.setOnClickListener((OnClickListener) new menuListener());
        orderButton.setOnClickListener((OnClickListener) new orderListener());
        helpButton.setOnClickListener((OnClickListener) new helpListener());
        callwaiterButton.setOnClickListener((OnClickListener) new callwaiterListener());
        appetizerButton.setOnClickListener((OnClickListener) new appetizerListener());
        beveragesButton.setOnClickListener((OnClickListener) new beveragesListener());
        soupsButton.setOnClickListener((OnClickListener) new soupsListener());
        maincourseButton.setOnClickListener((OnClickListener) new maincourseListener());
        saladButton.setOnClickListener((OnClickListener) new saladListener());
        dessertButton.setOnClickListener((OnClickListener) new dessertListener());
        
        
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
    
    private class appetizerListener  implements OnClickListener {

		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			
			Toast.makeText(getApplicationContext(), "Loading Appetizers...", Toast.LENGTH_SHORT).show();
			appetizerit.putExtras(g);
			startActivity(appetizerit);
		}
    	
    }

    private class beveragesListener  implements OnClickListener {

		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			
			Toast.makeText(getApplicationContext(), "Loading Beverages...", Toast.LENGTH_SHORT).show();
			beveragesit.putExtras(g);
			startActivity(beveragesit);
		}
    	
    }
    
    private class soupsListener  implements OnClickListener {

		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			
			Toast.makeText(getApplicationContext(), "Loading Soups...", Toast.LENGTH_SHORT).show();
			soupsit.putExtras(g);
			startActivity(soupsit);
		}
    	
    }
    
    private class maincourseListener  implements OnClickListener {

		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			
			Toast.makeText(getApplicationContext(), "Loading Main Course...", Toast.LENGTH_SHORT).show();
			maincourseit.putExtras(g);
			startActivity(maincourseit);
		}
    	
    }

    private class saladListener  implements OnClickListener {

		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			
			Toast.makeText(getApplicationContext(), "Loading Salads...", Toast.LENGTH_SHORT).show();
			saladit.putExtras(g);
			startActivity(saladit);
		}
    	
    }
    private class dessertListener  implements OnClickListener {

		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			
			Toast.makeText(getApplicationContext(), "Loading Desserts...", Toast.LENGTH_SHORT).show();
			dessertit.putExtras(g);
			startActivity(dessertit);
		}
    	
    }

	@Override
	public void onBackPressed() {
		homeit.putExtras(g);
		startActivity(homeit);
	}
    
}
