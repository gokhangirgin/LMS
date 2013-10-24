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

import org.drools.process.instance.WorkItemHandler;
import org.drools.runtime.process.WorkItem;
import org.drools.runtime.process.WorkItemManager;

import com.fasterxml.jackson.databind.ObjectMapper;

@SuppressWarnings("restriction")
public class WebServiceHandler implements WorkItemHandler{

	@Override
	public void executeWorkItem(WorkItem item, WorkItemManager manager) {
		// TODO Auto-generated method stub

		String _url = item.getParameter("Url").toString();
		DecisionItem di = (DecisionItem)item.getParameter("decisionItem"); 
		try {
			URL url = new URL(_url + "?id=" + di.id);
			HttpURLConnection connection = (HttpURLConnection)url.openConnection();
			StringBuffer text = new StringBuffer();
			connection.connect();
			InputStreamReader in = new InputStreamReader((InputStream) connection.getContent());
		    BufferedReader buff = new BufferedReader(in);
		    String line;
		    do {
		      line = buff.readLine();
		      text.append(line + "\n");
		    } while (line != null);
			ObjectMapper mapper = new ObjectMapper();
			di = mapper.readValue(text.toString(),DecisionItem.class);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("decisionItem", di);
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

}
