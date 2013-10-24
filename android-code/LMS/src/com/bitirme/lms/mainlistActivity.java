package com.bitirme.lms;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.entity.BufferedHttpEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;





import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Point;
import android.graphics.drawable.BitmapDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Html;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

public class mainlistActivity<URL> extends Activity {
	// All static variables
	
	/////////////
//	static final String URL = "http://fatih.demiraytelekom.com/KSK/getNews(basketball).xml";
	/////////////
	
	
	static final String KEY_ID = "id"; // parent node
	static final String KEY_PROCESS = "process";
	static final String IMAGE = "image";
	Point p;
	
	ListView list;
    LazyAdapter adapter;
    ProgressDialog progressDialog;
    ArrayList<HashMap<String, String>> items;
    
    ArrayList<String> linkler;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
//		TextView a=(TextView) findViewById(R.id.left_text);
//		a.setText("Haberler");
		
		progressDialog = new ProgressDialog(this);
//		  
//		    TextView tv1 = (TextView) progressDialog.findViewById(android.R.id.message);  
//		    tv1.setTextColor(Color.WHITE);
	    progressDialog.setMessage(Html.fromHtml("<font color='#FFFFFF'>Veriler Yükleniyor..</font>"));
		    progressDialog.setCancelable(true);
		     
	        progressDialog.setButton(DialogInterface.BUTTON_NEGATIVE, "Iptal", new DialogInterface.OnClickListener() {
	            public void onClick(DialogInterface dialog, int which) {
	                progressDialog.dismiss();
	                try {
	                	finish();
					return;
					} catch (Throwable e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	            }
	        });
			
	        Intent gelen=getIntent();
	        
	        TextView tex=(TextView) findViewById(R.id.textViewUser);
	        tex.setText("  "+gelen.getStringExtra("user"));
	        
		
			 DefaultHttpClient httpclient = new DefaultHttpClient();

				HttpGet httppost = new HttpGet("http://192.168.133.115:8080/LmsService/OrderView/Perc?id="+gelen.getStringExtra("id"));
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

				        items = new ArrayList<HashMap<String, String>>();
//				        atext.setText(total);
				        try {
							JSONArray js=new JSONArray(total.toString());
							for (int i = 0; i < js.length(); i++) {
								JSONObject obj = js.getJSONObject(i);
								HashMap<String, String> map = new HashMap<String, String>();
								
//								obj.getInt("orderId");
//								obj.getInt("percentage");
								
								map.put(KEY_ID, Integer.toString(obj.getInt("orderId")) );
								
								
								map.put(KEY_PROCESS,Integer.toString(obj.getInt("percentage")));
								
								
								
								
								
								// adding HashList to ArrayList
								items.add(map);
								
								  
							}
						} catch (JSONException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
		
	        //buraya kod
	        
	       
	        
	        
		list=(ListView)findViewById(R.id.listView1);
        
		

        // Click event for single list row
        list.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				
				
				Intent icerikscreen=new Intent(mainlistActivity.this,mainlistActivity2.class);
					
				
				HashMap<String, String> map = new HashMap<String, String>();
				map=items.get(position);
				icerikscreen.putExtra("id",map.get("id"));
//				icerikscreen.putExtra("image",map.get("image"));
				
				startActivity(icerikscreen);

				     
				
				

			}
		});		
        
        
        adapter=new LazyAdapter(mainlistActivity.this,items);
        list.setAdapter(adapter);
	}	
	

	@Override
	public void onWindowFocusChanged(boolean hasFocus) {
		// TODO Auto-generated method stub
		
		   int[] location = new int[2];
		   ListView ls = (ListView) findViewById(R.id.listView1);
		 
		   // Get the x, y location and store it in the location[] array
		   // location[0] = x, location[1] = y.
		   ls.getLocationOnScreen(location);
		 
		   //Initialize the Point with x, and y positions
		   p = new Point();
		   p.x = location[0];
		   p.y = location[1];
		
		super.onWindowFocusChanged(hasFocus);
	}
	

	
	
	public String TurkceKarakterDuzenle(String name)
	{
		
		name = name.replaceAll("B#304;", "Ý");
		name = name.replaceAll("B#305;", "ý");
		name = name.replaceAll("BOuml;", "Ö");
		name = name.replaceAll("Bouml;", "ö");
		name = name.replaceAll("BUuml;", "Ü");
		name = name.replaceAll("Buuml;", "ü");
		name = name.replaceAll("BCcedil;", "Ç");
		name = name.replaceAll("Bccedil;", "ç");
		name = name.replaceAll("B#286;", "Ð");
		name = name.replaceAll("B#287;", "ð");
		name = name.replaceAll("B#350;", "Þ");
		name = name.replaceAll("B#351;", "þ");
		
		return name;
	}
	
	







	
	
}