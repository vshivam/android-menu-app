package com.project;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class menuadapter extends BaseAdapter
{
	 private static ArrayList<menulist> menuitems;

	 private LayoutInflater mInflater;
	 
	 public menuadapter(Context context, ArrayList<menulist> results) 
	 {
		 menuitems = results;
		  mInflater = LayoutInflater.from(context);
	 }
	 
	 public int getCount()
	 {
		  return menuitems.size();
	 }
	 
	 public Object getItem(int position)
	 {
		  return menuitems.get(position);
	 }

	public long getItemId(int position) 
	{
		  return position;
	}
	
	public View getView(int position, View convertView, ViewGroup parent) 
	{
		  ViewHolder holder;
		  if (convertView == null) 
		  {
		   convertView = mInflater.inflate(R.layout.lview, null);
		   holder = new ViewHolder();
		   
		  
		   holder.txtName = (TextView) convertView.findViewById(R.id.name);
		   holder.txtDescp = (TextView) convertView.findViewById(R.id.description);
		   holder.txtPrice = (TextView) convertView.findViewById(R.id.price);

		   convertView.setTag(holder);
		  }
		  else
		  {
		   holder = (ViewHolder) convertView.getTag();
		  }
		  
		  holder.txtName.setText(menuitems.get(position).getName());
		  holder.txtDescp.setText(menuitems.get(position).getdescription());
		  holder.txtPrice.setText(menuitems.get(position).getprice());

		  return convertView;
		 }

		 static class ViewHolder 
		 {
		  TextView txtName;
		  TextView txtDescp;
		  TextView txtPrice;
		 }
}

