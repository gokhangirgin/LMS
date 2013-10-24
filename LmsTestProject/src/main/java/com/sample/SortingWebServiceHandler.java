package com.sample;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import models.Item;
import models.Phase;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.drools.process.instance.WorkItemHandler;
import org.drools.runtime.process.WorkItem;
import org.drools.runtime.process.WorkItemManager;

import com.fasterxml.jackson.databind.ObjectMapper;
 
@SuppressWarnings("restriction")
public class SortingWebServiceHandler implements WorkItemHandler{
	private String URL;

	@Override
	public void executeWorkItem(WorkItem item, WorkItemManager manager) {
		// TODO Auto-generated method stub
 
		String _url = item.getParameter("Url").toString();
		URL = _url;
		Item _item = null;  
		_item = (Item)item.getParameter("Item");  
		if(_item == null) 
			_item = MainMenu.laundryFactory.CurrentLaundry;  
		try {  
			URL url = new URL(_url + "?id=" + _item.getId());
			System.out.println(url);
			HttpURLConnection connection = (HttpURLConnection)url.openConnection(); 
			connection.connect();  
			InputStreamReader in = new InputStreamReader((InputStream) connection.getContent()); 
		    BufferedReader buff = new BufferedReader(in);
		    String line; 
		    line = buff.readLine();  
			int binNumber = Integer.parseInt(line); 
			Map<String, Object> map = new HashMap<String, Object>();
			System.out.println(binNumber);
			map.put("binNumber", binNumber); 
			map.put("Item", _item); 
			
			System.out.println("it was already okay,");
			Phase p = GetPhase();
			System.out.println(p);
			_item.setLocation(p);
			HttpClient client = new DefaultHttpClient();
			HttpPut request = new HttpPut("http://localhost:8080/LmsService/Item/Update");
			 
			ObjectMapper mapper = new ObjectMapper();
			String posted = mapper.writeValueAsString(_item);
			StringEntity entity = new StringEntity(posted);
			entity.setContentType("application/json");
			request.setEntity(entity);
			client.execute(request); 
			
			System.out.println("rest is okay too :)))");
			
			manager.completeWorkItem(item.getId(), map); 
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void abortWorkItem(WorkItem arg0, WorkItemManager arg1) {
		// TODO Auto-generated method stub
		
	}
	
	private Phase GetPhase()
	{
		if(URL.endsWith("getSortingDecisionForWash"))
			return Phase.sortingForWashing;
		else if(URL.endsWith("getSortingDecisionForDry"))
			return Phase.sortingForDrying;
		else if(URL.endsWith("getSortingDecisionForIron"))
			return Phase.sortingForIroning;
		else if(URL.endsWith("getSortingDecisionForPackage"))
			return Phase.sortingForPackaging;
		 return null;
	}

}
