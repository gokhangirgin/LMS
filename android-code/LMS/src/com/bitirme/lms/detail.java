package com.bitirme.lms;

import java.util.HashMap;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class detail extends Activity{

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.detail);
		
		Intent i=getIntent();
		
		TextView id=(TextView) findViewById(R.id.idTxt);
		TextView color=(TextView) findViewById(R.id.txtColor);
		TextView location=(TextView) findViewById(R.id.txtLocation);
		TextView material=(TextView) findViewById(R.id.txtMaterial);
		TextView type=(TextView) findViewById(R.id.txtType);
		TextView washed=(TextView) findViewById(R.id.txtWashed);
		TextView weight=(TextView) findViewById(R.id.txtWeight);
		
		
		
//		"id"));
//		icerikscreen.putExtra("location", map.get("location"));
//		icerikscreen.putExtra("type", map.get("type"));
//		icerikscreen.putExtra("color", map.get("color"));
//		icerikscreen.putExtra("weight", map.get("weight"));
//		icerikscreen.putExtra("material", map.get("material"));
//		icerikscreen.putExtra("washedCount", map.get("washedCount"));
		
		
		
		id.setText(i.getStringExtra("id"));
		color.setText(i.getStringExtra("color"));
		location.setText(i.getStringExtra("location"));
		material.setText(i.getStringExtra("material"));
		type.setText(i.getStringExtra("type"));
		washed.setText(i.getStringExtra("washedCount"));
		weight.setText(i.getStringExtra("weight"));
		
		ImageView image=(ImageView) findViewById(R.id.imageView1);
		
		image.setImageResource(Integer.parseInt(i.getStringExtra("image"))) ;
		
		setTitle(i.getStringExtra("id")+". item details");
		
	}
}
