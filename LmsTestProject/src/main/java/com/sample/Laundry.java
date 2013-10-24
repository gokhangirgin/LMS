package com.sample;

import java.util.HashMap;
import java.util.Map;
 
public class Laundry {
	public Laundry()
	{}
	public Laundry(int id)
	{
		setNumberOfWashed(0);
		setId(id);
	}
	public Laundry(int id, int color, int type, int material, int location, float weight)
	{
		setNumberOfWashed(0); 
		setId(id);
		setColor(color);
		setIsBedding(type == 0 ? true : false);
		setMaterial(material);
		setLocation(location);
		setWeight(weight);
	}

	public void Start()
	{
		Map<String, Object> parameterMap = new HashMap<String, Object>();
		parameterMap.put("laundry", this); 
		parameterMap.put("LaundryFactory", MainMenu.laundryFactory);  

		MainMenu.kbGeneral.ksession.startProcess("com.sample.generalFlow", parameterMap);
	} 
		
	private boolean bedding;
	private int washCount;
	private int id;
	private int material;
	private int color;
	private float weight;
	private int location;
	private boolean discarded;
	
	public int WashingBinNumber;
	public int DryingBinNumber;
	public int IroningBinNumber;
	public int PackagingBinNumber;
	  
	public void IncrementNumberOfWashed()
	{
		setNumberOfWashed(getWashCount() + 1);
	}	
	public void Discard()
	{
		discarded = true;
	}
	
	public boolean IsBedding() {
		return bedding;
	}
	public void setIsBedding(boolean isBedding) {
		bedding = isBedding;
	}
	public int getWashCount() {
		return washCount;
	}
	public void setNumberOfWashed(int washCount) 
	{
		this.washCount = washCount;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getMaterial() {
		return material;
	}
	public void setMaterial(int material) {
		this.material = material;
	}
	public int getColor() {
		return color;
	}
	public void setColor(int color) {
		this.color = color;
	}
	public float getWeight() {
		return weight;
	}
	public void setWeight(float weight) {
		this.weight = weight;
	}
	public int getLocation() {
		return location;
	}
	public void setLocation(int location) {
		this.location = location;
	}

	public boolean isDiscarded() {
		return discarded;
	}
	public void setDiscarded(boolean discarded) {
		this.discarded = discarded;
	}
 
}
