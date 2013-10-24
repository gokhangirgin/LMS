package com.sample;

import java.util.HashMap;
import java.util.Map;

public class DecisionItem { 
	public DecisionItem()
	{
		this.id = 1;
	}
	public DecisionItem(int id)
	{
		this.id = id;
	}
	public int id;
	private boolean bedding;
	private boolean toBeDiscarded;
	private boolean dryInDryer;
	public void StartProcess()
	{
		Map<String, Object> parameterMap = new HashMap<String, Object>();
		parameterMap.put("decisionItem", this); 
        MainMenu.kbGeneral.ksession.startProcess("com.sample.generalFlow", parameterMap);
	} 

	public void Wash()
	{ 
		System.out.println("washed");
		try {
			Thread.sleep(waitingTime);
		} catch (InterruptedException e) { 
			e.printStackTrace();
		}
		//fs.GetLaundry(id).IncrementNumberOfWashed();
		//dataService connection needed
	}

	public void DryOut()
	{ 
		System.out.println("in open air");
		try {
			Thread.sleep(waitingTime);
		} catch (InterruptedException e) { 
			e.printStackTrace();
		} 
	}

	public void DryInDryer()
	{ 
		System.out.println("in dryer");
		try {
			Thread.sleep(waitingTime);
		} catch (InterruptedException e) { 
			e.printStackTrace();
		} 
	}

	public boolean isBedding() {
		return bedding;
	}
	public void setBedding(boolean bedding) {
		this.bedding = bedding;
	}
	public boolean isToBeDiscarded() {
		return toBeDiscarded;
	}
	public void setToBeDiscarded(boolean toBeDiscarded) {
		this.toBeDiscarded = toBeDiscarded;
	}

	public boolean isDryInDryer() {
		return dryInDryer;
	}

	public void setDryInDryer(boolean dryInDryer) {
		this.dryInDryer = dryInDryer;
	}
	private static int waitingTime = 5;
}
