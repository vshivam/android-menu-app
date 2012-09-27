package com.project;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class First extends Activity {
	
	@Override
	public void onBackPressed() {
    return;
    }
	
	Intent home;
	Bundle g = new Bundle();
	ImageView t1;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.first);
        
        t1 = (ImageView)findViewById(R.id.ImageView01);
        
        int [] tag = new int[100];
        String [] name = new String[100];
        int [] qty = new int[100];
        int [] amount = new int[100];
        
        
        g.putIntArray("tag", tag);
        g.putStringArray("name", name);
        g.putIntArray("qty", qty);
        g.putIntArray("amount", amount);
        
        home = new Intent(getBaseContext(), main.class);
        t1.setOnClickListener((OnClickListener) new homeListener());
        
        
	}
	
	private class homeListener  implements OnClickListener {

		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			
			Toast.makeText(getApplicationContext(), "Home Screen Loading...", Toast.LENGTH_SHORT).show();
			home.putExtras(g);
			startActivity(home);
		}
    	
    }


}
