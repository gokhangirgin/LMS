package com.sample;

import java.io.IOException;  
import java.util.ArrayList; 
import java.util.Date;
import java.util.HashSet;
import java.util.List; 

import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
  
import com.fasterxml.jackson.databind.ObjectMapper;


import models.Basket;
import models.BasketType;
import models.Drying;
import models.Ironing;
import models.Item;
import models.Packaging;
import models.Washing;

public class LaundryBin { 
	private String name; 
	private LaundryFactory lf;
	private DefaultListModel model;
	private JProgressBar pbar;
	private List<Item> Container;	
	
	public LaundryBin(String n, LaundryFactory lf)
	{
		setModel(new DefaultListModel());
		setPbar(new JProgressBar());
		getPbar().setMaximum(3);
		Container = new ArrayList<Item>();
		setName(n);
		setLf(lf);
	}
	 
	public Item GetNextLaundry()
	{
		try
		{
			System.out.println("element at " + getContainer().size() + " will be returned;");
			Item l = getContainer().get(getContainer().size() - 1); 
			return l;
		}
		catch(Exception e)
		{
			System.out.println("couldn't : " + e.getMessage());
			return null;
		}		 
	}
	
	public void putLaundry(Item l)
	{ 
		Container.add(l);  
		model.addElement(l.getId());   
		getPbar().setValue(model.size());
	}

	public void removeLaundry(Item l)
	{ 
		Container.remove(l);  
		model.removeElement(l.getId());   
		getPbar().setValue(model.size());
	}
	
	public List<Item> getContainer() {
		return Container;
	}

	public void Wash(WashingAttributes wa) throws InterruptedException, ClientProtocolException, IOException
	{
		// data web service operations   
		System.out.println("washing...");
		JOptionPane.showMessageDialog(null, "Your bin will be washed now."); 
		Date d = new Date();
		Basket basket = new Basket();
		basket.setItems(new HashSet<Item>(getContainer()));
		basket.setType(getBType()); 
		basket.setEntryTime(d);
		Washing washing = new Washing();
		washing.setBasket(basket);
		washing.setStartTime(d);
		 
		HttpClient client = new DefaultHttpClient();
		HttpPost request = new HttpPost("http://localhost:8080/LmsService/Washing/Add");
		 
		ObjectMapper mapper = new ObjectMapper();
		String posted = mapper.writeValueAsString(washing);
		StringEntity entity = new StringEntity(posted);
		entity.setContentType("application/json");
		request.setEntity(entity);
		
		washing.setId(mapper.readValue(client.execute(request).getEntity().getContent(), Integer.class));  
		//insert washing here, randomize the other values from wa if necessary
		Thread.sleep(5000); 
		
		washing.setEndTime(new Date());
		HttpPut _request = new HttpPut("http://localhost:8080/LmsService/Washing/Update");

		mapper = new ObjectMapper();
		posted = mapper.writeValueAsString(washing);
		entity = new StringEntity(posted);
		entity.setContentType("application/json");
		_request.setEntity(entity);
		client.execute(_request);
		
		//update washing here
		getPbar().setValue(model.size());
	}

	public void Dry(DryingAttributes da) throws InterruptedException, ClientProtocolException, IOException
	{
		// data web service operations  
		System.out.println("drying...");
		JOptionPane.showMessageDialog(null, "Your bin will be dried now."); 
		Date d = new Date();
		Basket basket = new Basket();
		basket.setItems(new HashSet<Item>(getContainer()));
		basket.setType(getBType()); 
		basket.setEntryTime(d);
		
		Drying drying = new Drying();
		drying.setBasket(basket);
		drying.setStartTime(d);
		 
		HttpClient client = new DefaultHttpClient();
		HttpPost request = new HttpPost("http://localhost:8080/LmsService/Drying/Add");
		 
		ObjectMapper mapper = new ObjectMapper();
		String posted = mapper.writeValueAsString(drying);
		StringEntity entity = new StringEntity(posted);
		entity.setContentType("application/json");
		request.setEntity(entity);
		
		drying.setId(mapper.readValue(client.execute(request).getEntity().getContent(), Integer.class));  
		//insert washing here, randomize the other values from wa if necessary
		Thread.sleep(5000); 
		
		drying.setEndTime(new Date());
		HttpPut _request = new HttpPut("http://localhost:8080/LmsService/Drying/Update");

		mapper = new ObjectMapper();
		posted = mapper.writeValueAsString(drying);
		entity = new StringEntity(posted);
		entity.setContentType("application/json");
		_request.setEntity(entity);
		client.execute(_request);
		
		//update washing here
		getPbar().setValue(model.size());
	}

	public void Iron(IroningAttributes ia) throws InterruptedException, ClientProtocolException, IOException
	{
		// data web service operations   
		System.out.println("ironing...");
		JOptionPane.showMessageDialog(null, "Your bin will be ironed now."); 
		Date d = new Date();Basket basket = new Basket();
		basket.setItems(new HashSet<Item>(getContainer()));
		basket.setType(getBType()); 
		basket.setEntryTime(d);
		
		Ironing ironing = new Ironing();
		ironing.setBasket(basket);
		ironing.setStartTime(d);
		 
		HttpClient client = new DefaultHttpClient();
		HttpPost request = new HttpPost("http://localhost:8080/LmsService/Ironing/Add");
		 
		ObjectMapper mapper = new ObjectMapper();
		String posted = mapper.writeValueAsString(ironing);
		StringEntity entity = new StringEntity(posted);
		entity.setContentType("application/json");
		request.setEntity(entity);
		ironing.setId(mapper.readValue(client.execute(request).getEntity().getContent(), Integer.class));  
		//insert washing here, randomize the other values from wa if necessary
		Thread.sleep(5000); 
		
		ironing.setEndTime(new Date());
		HttpPut _request = new HttpPut("http://localhost:8080/LmsService/Ironing/Update");

		mapper = new ObjectMapper();
		posted = mapper.writeValueAsString(ironing);
		entity = new StringEntity(posted);
		entity.setContentType("application/json");
		_request.setEntity(entity);
		client.execute(_request);
		//update washing here
		getPbar().setValue(model.size());
	}
	
	public void Package(/*PackagingAttributes pa*/) throws InterruptedException, ClientProtocolException, IOException
	{
		// data web service operations   
		System.out.println("packaging...");
		JOptionPane.showMessageDialog(null, "Your bin will be packaged now."); 
		Date d = new Date();Basket basket = new Basket();
		basket.setItems(new HashSet<Item>(getContainer()));
		basket.setType(getBType()); 
		basket.setEntryTime(d);
		
		Packaging packaging = new Packaging();
		packaging.setBasket(basket);
		packaging.setStartTime(d);
		 
		HttpClient client = new DefaultHttpClient();
		HttpPost request = new HttpPost("http://localhost:8080/LmsService/Packaging/Add");
		 
		ObjectMapper mapper = new ObjectMapper();
		String posted = mapper.writeValueAsString(packaging);
		StringEntity entity = new StringEntity(posted);
		entity.setContentType("application/json");
		request.setEntity(entity);
		packaging.setId(mapper.readValue(client.execute(request).getEntity().getContent(), Integer.class));  
		//insert washing here, randomize the other values from wa if necessary
		Thread.sleep(5000); 
		
		packaging.setEndTime(new Date());
		HttpPut _request = new HttpPut("http://localhost:8080/LmsService/Packaging/Update");

		mapper = new ObjectMapper();
		posted = mapper.writeValueAsString(packaging);
		entity = new StringEntity(posted);
		entity.setContentType("application/json");
		_request.setEntity(entity);
		client.execute(_request);
		//update washing here
		getPbar().setValue(model.size());
	}
	 
	public void setContainer(List<Item> container) {
		Container = container;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LaundryFactory getLf() {
		return lf;
	}

	public void setLf(LaundryFactory lf) {
		this.lf = lf;
	}

	public DefaultListModel getModel() {
		return model;
	}

	public void setModel(DefaultListModel model) {
		this.model = model;
	}

	public JProgressBar getPbar() {
		return pbar;
	}

	public void setPbar(JProgressBar pbar) {
		this.pbar = pbar;
	}
 
	private BasketType getBType()
	{ 
		if(getName() == BasketType.ColoredBin.toString())
			return BasketType.ColoredBin;
		if(getName() == BasketType.WhiteBin.toString())
			return BasketType.WhiteBin;
		if(getName() == BasketType.HospitalBin.toString())
			return BasketType.HospitalBin;
		if(getName() == BasketType.DiscardBin.toString())
			return BasketType.DiscardBin;
		if(getName() == BasketType.CottonBin.toString())
			return BasketType.CottonBin;
		if(getName() == BasketType.SilkBin.toString())
			return BasketType.SilkBin;
		if(getName() == BasketType.OpenAirBin.toString())
			return BasketType.OpenAirBin;
		if(getName() == BasketType.IronWithMachineBin.toString())
			return BasketType.IronWithMachineBin;
		if(getName() == BasketType.DoNotIronBin.toString())
			return BasketType.DoNotIronBin;
		if(getName() == BasketType.NormalIronBin.toString())
			return BasketType.NormalIronBin;
		if(getName() == BasketType.NorthBin.toString())
			return BasketType.NorthBin;
		if(getName() == BasketType.WestBin.toString())
			return BasketType.WestBin;
		if(getName() == BasketType.SouthBin.toString())
			return BasketType.SouthBin;
		if(getName() == BasketType.EastBin.toString())
			return BasketType.EastBin; 
		return null;
	} 
}
