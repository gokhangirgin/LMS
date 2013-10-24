package com.sample;

import java.util.ArrayList;
import java.util.List;

import models.Item;
 
public class LaundryFactory {
	public boolean BinWashed;
	public boolean BinDried;
	public boolean BinIroned;
	public LaundryBin CurrentBin; 
	public Item CurrentLaundry;
	public void ResetV()
	{
		BinWashed = false;
		BinDried = false;
		BinIroned = false;
		CurrentBin = null;
	}
	
    public List<LaundryBin> AllBins;
    
    public List<LaundryBin> WashBins;
    public List<LaundryBin> DryBins;
    public List<LaundryBin> IronBins;
    public List<LaundryBin> PackageBins;    
    
    public LaundryBin ColoredBin;
    public LaundryBin WhiteBin;
    public LaundryBin HospitalBin;
    public LaundryBin DiscardBin; 

    public LaundryBin SilkBin;
    public LaundryBin CottonBin;
    public LaundryBin OpenAirBin;

    public LaundryBin NormalIronBin;
    public LaundryBin DoNotIronBin;
    public LaundryBin IronWithMachineBin;    

    public LaundryBin NorthBin;
    public LaundryBin SouthBin;
    public LaundryBin EastBin;
    public LaundryBin WestBin;
    
    public LaundryFactory()
    { 
    	AllBins = new ArrayList<LaundryBin>();
    	WashBins = new ArrayList<LaundryBin>();
    	DryBins = new ArrayList<LaundryBin>();
    	IronBins = new ArrayList<LaundryBin>();
    	PackageBins = new ArrayList<LaundryBin>();
    	
    	ColoredBin = new LaundryBin("ColoredBin", this);
    	WhiteBin = new LaundryBin("WhiteBin", this);
    	HospitalBin = new LaundryBin("HospitalBin", this);
    	DiscardBin = new LaundryBin("DiscardBin", this); 
    	
    	SilkBin = new LaundryBin("SilkBin", this);
    	CottonBin = new LaundryBin("CottonBin", this);
    	OpenAirBin = new LaundryBin("OpenAirBin", this);
    	
    	NormalIronBin = new LaundryBin("NormalIronBin", this);
    	DoNotIronBin = new LaundryBin("DoNotIronBin", this);
    	IronWithMachineBin = new LaundryBin("IronWithMachineBin", this);

    	NorthBin = new LaundryBin("NorthBin", this);
    	SouthBin = new LaundryBin("SouthBin", this);
    	EastBin = new LaundryBin("EastBin", this);
    	WestBin = new LaundryBin("WestBin", this);

    	
    	AllBins.add(ColoredBin);
    	AllBins.add(WhiteBin);
    	AllBins.add(HospitalBin);
    	AllBins.add(DiscardBin);
    	
    	AllBins.add(SilkBin);
    	AllBins.add(CottonBin);
    	AllBins.add(OpenAirBin);
    	
    	AllBins.add(NormalIronBin); 
    	AllBins.add(DoNotIronBin); 
    	AllBins.add(IronWithMachineBin);
    	
    	AllBins.add(NorthBin); 
    	AllBins.add(SouthBin); 
    	AllBins.add(EastBin);
    	AllBins.add(WestBin);
    	
    	WashBins.add(ColoredBin);
    	WashBins.add(WhiteBin);
    	WashBins.add(HospitalBin);
    	
    	DryBins.add(SilkBin);
    	DryBins.add(CottonBin);
    	DryBins.add(OpenAirBin);
    	
    	IronBins.add(NormalIronBin);
    	IronBins.add(DoNotIronBin);
    	IronBins.add(IronWithMachineBin);   	
    	
    	PackageBins.add(NorthBin);
    	PackageBins.add(SouthBin);
    	PackageBins.add(EastBin);  
    	PackageBins.add(WestBin);    	
    	
    	//AllBins.add(DiscardBin);
    } 
    public LaundryBin AnyFullBin()
    {
    	for(LaundryBin lb : AllBins)
    	{
    		if(lb.getContainer().size() > 15)
    			return lb;
    	}
    	return null;
    }

    public LaundryBin AnyFullWashBin()
    {
    	for(LaundryBin lb : WashBins)
    	{ 
    		if(lb.getContainer().size() >= 3)
    			return lb;
    	}
    	return null;
    }

    public LaundryBin AnyFullDryBin()
    {
    	for(LaundryBin lb : DryBins)
    	{
    		if(lb.getContainer().size() >= 3)
    			return lb;
    	}
    	return null;
    }

    public LaundryBin AnyFullIronBin()
    {
    	for(LaundryBin lb : IronBins)
    	{
    		if(lb.getContainer().size() >= 3)
    			return lb;
    	}
    	return null;
    }

    public LaundryBin AnyFullPackageBin()
    {
    	for(LaundryBin lb : PackageBins)
    	{
    		if(lb.getContainer().size() >= 3)
    			return lb;
    	}
    	return null;
    }
    
    public LaundryBin getBin(String name)
    {
    	for(LaundryBin lb : AllBins)
    	{
    		if(lb.getName() == name)
    			return lb;
    	}
    	return null;
    }
}
