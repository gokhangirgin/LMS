package com.bitirme.lms;



import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.entity.BufferedHttpEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity {

	
	EditText usarname;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		
		
		usarname=(EditText) findViewById(R.id.editText1);
		
		Button btn =(Button) findViewById(R.id.btnDetail);
		btn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				String URL="";
				
				
				
				
				
				Intent i=new Intent(MainActivity.this, mainlistActivity.class);
				if(!usarname.getText().toString().equals(""))
				{
					
					
					DefaultHttpClient httpclient = new DefaultHttpClient();

					HttpGet httppost = new HttpGet("http://192.168.133.115:8080/LmsService/Customer/GetOne?id="+usarname.getText().toString());
					HttpResponse response = null;
					try {
						response = httpclient.execute(httppost);
					} catch (ClientProtocolException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					        HttpEntity ht = response.getEntity();

					        BufferedHttpEntity buf = null;
							try {
								buf = new BufferedHttpEntity(ht);
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}

					        InputStream is = null;
							try {
								is = buf.getContent();
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}


					        BufferedReader r = new BufferedReader(new InputStreamReader(is));

					        StringBuilder total = new StringBuilder();
					        String line;
					        try {
								while ((line = r.readLine()) != null) {
									

									
								    total.append(line + "\n");
								}
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
					
					
					        JSONObject js;
							try {
								js = new JSONObject(total.toString());
								String a=(String) js.get("firstName");
								String b=(String) js.get("lastName");
								
						        i.putExtra("user",a+" "+b );
							} catch (JSONException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
					        
					
					
					
				i.putExtra("id", usarname.getText().toString());
				startActivity(i);
				}
				
			}
		});
		
		
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
