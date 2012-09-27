package com.project;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class orderadapter extends BaseAdapter
{
	 private static ArrayList<menulist1> menuitems;

	 private LayoutInflater mInflater;
	 
	 public orderadapter(Context context, ArrayList<menulist1> results) 
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
		   convertView = mInflater.inflate(R.layout.menulist1, null);
		   holder = new ViewHolder();
		   
		  
		   holder.txtName = (TextView) convertView.findViewById(R.id.TextView01);
		   holder.txtQty = (TextView) convertView.findViewById(R.id.TextView02);
		   holder.txtAmount = (TextView) convertView.findViewById(R.id.TextView03);

		   convertView.setTag(holder);
		  }
		  else
		  {
		   holder = (ViewHolder) convertView.getTag();
		  }
		  
		  holder.txtName.setText(menuitems.get(position).getName());
		  holder.txtQty.setText(menuitems.get(position).getquantity());
		  holder.txtAmount.setText(menuitems.get(position).getamount());

		  return convertView;
		 }

		 static class ViewHolder 
		 {
		  TextView txtName;
		  TextView txtQty;
		  TextView txtAmount;
		 }
}

