package te;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET; 
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
/**/
import org.drools.KnowledgeBase;
import org.drools.KnowledgeBaseFactory;
import org.drools.builder.KnowledgeBuilder;
import org.drools.builder.KnowledgeBuilderError;
import org.drools.builder.KnowledgeBuilderErrors;
import org.drools.builder.KnowledgeBuilderFactory;
import org.drools.builder.ResourceType;
import org.drools.io.ResourceFactory;
import org.drools.runtime.StatefulKnowledgeSession;

@Path("/LMS")
public class Service {
	private List<Shirt> _shirts = new ArrayList<Shirt>();  
	private KnowledgeBase kbase;
	private StatefulKnowledgeSession externalSession;
	public Service()
	{         
		_shirts.add(new Shirt());
		_shirts.add(new Shirt(2,40,"Yellow","Dirty"));
		_shirts.add(new Shirt(1,40,"Black","Dirty"));
		_shirts.add(new Shirt(7,40,"Green","Dirty"));
		_shirts.add(new Shirt(8,55,"Orange","Dirty"));
		
		try { 
            kbase = readKnowledgeBase("External");
            externalSession = kbase.newStatefulKnowledgeSession();  
        } catch (Throwable t) {
            t.printStackTrace();
        }
	}

	@GET
	@Path("/getSortingDecisionForWash") 
	public int getSortingForWashDecision(@QueryParam("id") int id)
	{
		File f = new File("C:\\Users\\penishead\\Desktop\\log.txt"); 
        try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(f, true));
			writer.write("washta " + id);
			writer.newLine();
			BinDecision bd = new BinDecision();
			writer.write("bd olustu");
			try {  writer.write("tryda");
				Laundry l = GetLaundryy(id);
				writer.write("l geldi"); 
				if(l!=null)
				{
					writer.write("l null deel");
					try { 
    				
						KnowledgeBase kbase = readKnowledgeBase("SortingForWash");
						writer.write("kbase alindi");						
						StatefulKnowledgeSession externalSession = kbase.newStatefulKnowledgeSession(); 
						writer.write("sessionn acildi");
						externalSession.insert(l);
						externalSession.insert(bd);
						writer.write("l + bd inserted");
						externalSession.fireAllRules();    
						writer.write("fireallrules oldu");
					} catch (Throwable t) {
						t.printStackTrace();
						writer.write(t.getMessage());
						writer.newLine(); 
					}
				}
			} catch (Throwable t) {
				t.printStackTrace();
				writer.write(t.getMessage());
				writer.newLine(); 
			}
			writer.close();
			return bd.getBinNumber();	
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
        return -98;
	}

	@GET
	@Path("/getSortingDecisionForDry") 
	public int getSortingForDryDecision(@QueryParam("id") int id)
	{
		BinDecision bd = new BinDecision();
		try {  
    		Laundry l = GetLaundryy(id);
    		if(l!=null)
    		{
    			try { 
    				File f = new File("C:\\Users\\penishead\\Desktop\\log.txt"); 
    		        try {
    					BufferedWriter writer = new BufferedWriter(new FileWriter(f, true));
    					writer.write(l.getMaterial());
    					writer.newLine();
    					writer.close();
    				} catch (IOException e) {
    					// TODO Auto-generated catch block
    					e.printStackTrace();
    				}
    	            KnowledgeBase kbase = readKnowledgeBase("SortingForDry");
    	            StatefulKnowledgeSession externalSession = kbase.newStatefulKnowledgeSession(); 
        			externalSession.insert(l);
        			externalSession.insert(bd);
        			externalSession.fireAllRules();   
    	        } catch (Throwable t) {
    	            t.printStackTrace();
    	        }
    		}
        } catch (Throwable t) {
            t.printStackTrace();
        }
		return bd.getBinNumber();	
	}

	@GET
	@Path("/getSortingDecisionForIron") 
	public int getSortingForIronDecision(@QueryParam("id") int id)
	{
		BinDecision bd = new BinDecision();
		try {  
    		Laundry l = GetLaundryy(id);
    		if(l!=null)
    		{
    			try { 
    	            KnowledgeBase kbase = readKnowledgeBase("SortingForIron");
    	            StatefulKnowledgeSession externalSession = kbase.newStatefulKnowledgeSession(); 
        			externalSession.insert(l);
        			externalSession.insert(bd);
        			externalSession.fireAllRules();   
    	        } catch (Throwable t) {
    	            t.printStackTrace();
    	        }
    		}
        } catch (Throwable t) {
            t.printStackTrace();
        }
		return bd.getBinNumber();	
	}
	
	@GET
	@Path("/putLaundry") 
	@Produces({MediaType.APPLICATION_JSON})
	public Laundry putLaundry(@QueryParam("id") int id, @QueryParam("material") int material, @QueryParam("color") int color, @QueryParam("weight") float weight, @QueryParam("bedding") int bedding)
	{ 
		File f = new File("C:\\Users\\penishead\\Desktop\\database.txt"); 
        try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(f, true));
			writer.write("id=" + id + ";color=" + color + ";bedding=" + bedding + ";material=" + material + ";weight=" + weight + ";");
			writer.newLine();
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        
		Laundry l = new Laundry(id, color, bedding, material, 0 , (float) weight);
		return l;
	}

	@GET
	@Path("/putLaundry") 
	@Produces({MediaType.APPLICATION_JSON})
	public void IncreaseWashCount(@QueryParam("id") int id)
	{  
		
	}
	
	@GET
	@Path("/getLaundries") 
	@Produces({MediaType.APPLICATION_JSON})
	public List<Laundry> getLaundries()
	{ 
		List<Laundry> laundries = new ArrayList<Laundry>();
		File f = new File("C:\\Users\\penishead\\Desktop\\database.txt");
		try
		{
			BufferedReader reader = new BufferedReader(new FileReader(f)); 

	        //... Loop as long as there are input lines.
	        String line = null;
	        int si = 0;
	        while ((line=reader.readLine()) != null) {
	            String v1 = line.substring(line.indexOf("=", si) + 1, line.indexOf(";", si));
	            si = line.indexOf(";", si) + 1;
	            String v2 = line.substring(line.indexOf("=", si) + 1, line.indexOf(";", si));
	            si = line.indexOf(";", si) + 1;
	            String v3 = line.substring(line.indexOf("=", si) + 1, line.indexOf(";", si));
	            si = line.indexOf(";", si) + 1;
	            String v4 = line.substring(line.indexOf("=", si) + 1, line.indexOf(";", si));
	            si = line.indexOf(";", si) + 1;
	            String v5 = line.substring(line.indexOf("=", si) + 1, line.indexOf(";", si));
	            si = line.indexOf(";", si) + 1;
	            
	            Laundry l = new Laundry(Integer.parseInt(v1), Integer.parseInt(v2), Integer.parseInt(v3), Integer.parseInt(v4),  0, Float.parseFloat(v5));
	            laundries.add(l);
	            si = 0;
	        }
	        reader.close();
		}
		catch(Exception e){}
		return laundries;
	}

	@GET
	@Path("/getLaundryCount")  
	public int getLaundryCount()
	{  
		File f = new File("C:\\Users\\penishead\\Desktop\\database.txt");
        int si = 0;
		try
		{
			BufferedReader reader = new BufferedReader(new FileReader(f)); 

	        //... Loop as long as there are input lines. 
	        while (reader.readLine() != null) {
	            si++;
	        }
	        reader.close();
		}
		catch(Exception e){}
		return si;
	}
	
	@GET
	@Path("/getLaundry") 
	@Produces({MediaType.APPLICATION_JSON})
	public Laundry getLaundry(@QueryParam("id") int id)
	{  
		File f = new File("C:\\Users\\penishead\\Desktop\\database.txt");
		Laundry l = null; 
        
		try
		{ 
				BufferedReader reader = new BufferedReader(new FileReader(f));  
				//	... Loop as long as there are input lines.
	        	String line = null;
	        	int si = 0;
	        	line = reader.readLine();
	        	while (line != null) { 
	            	String v1 = line.substring(line.indexOf("=", si) + 1, line.indexOf(";", si));
	            	si = line.indexOf(";", si) + 1;
	            	if(Integer.parseInt(v1) == id)
	            	{ 
	            		String v2 = line.substring(line.indexOf("=", si) + 1, line.indexOf(";", si));
	            		si = line.indexOf(";", si) + 1;
	            		String v3 = line.substring(line.indexOf("=", si) + 1, line.indexOf(";", si));
	            		si = line.indexOf(";", si) + 1;
	            		String v4 = line.substring(line.indexOf("=", si) + 1, line.indexOf(";", si));
	            		si = line.indexOf(";", si) + 1;
	            		String v5 = line.substring(line.indexOf("=", si) + 1, line.indexOf(";", si));
	            		si = line.indexOf(";", si) + 1;

	    	        	reader.close();
	    	        	l = new Laundry(Integer.parseInt(v1), Integer.parseInt(v2), Integer.parseInt(v3), Integer.parseInt(v4),  0, Float.parseFloat(v5));
	    	        
	            	}
 
					try
					{line = reader.readLine();}catch(Exception e){line = null;} 
	            	si = 0;
	        	}
 
	        	reader.close();  
	        return l;
		}
		catch(Exception e){}
		return null;
	}
    
    private Laundry GetLaundryy(int id)
    {
		File f = new File("C:\\Users\\penishead\\Desktop\\database.txt");
		Laundry l = null; 
        
		try
		{ 
				BufferedReader reader = new BufferedReader(new FileReader(f));  
				//	... Loop as long as there are input lines.
	        	String line = null;
	        	int si = 0;
	        	line = reader.readLine();
	        	while (line != null) { 
	            	String v1 = line.substring(line.indexOf("=", si) + 1, line.indexOf(";", si));
	            	si = line.indexOf(";", si) + 1;
	            	if(Integer.parseInt(v1) == id)
	            	{ 
	            		String v2 = line.substring(line.indexOf("=", si) + 1, line.indexOf(";", si));
	            		si = line.indexOf(";", si) + 1;
	            		String v3 = line.substring(line.indexOf("=", si) + 1, line.indexOf(";", si));
	            		si = line.indexOf(";", si) + 1;
	            		String v4 = line.substring(line.indexOf("=", si) + 1, line.indexOf(";", si));
	            		si = line.indexOf(";", si) + 1;
	            		String v5 = line.substring(line.indexOf("=", si) + 1, line.indexOf(";", si));
	            		si = line.indexOf(";", si) + 1;

	    	        	reader.close();
	    	        	l = new Laundry(Integer.parseInt(v1), Integer.parseInt(v2), Integer.parseInt(v3), Integer.parseInt(v4),  0, Float.parseFloat(v5));
	    	        
	            	}
 
					try
					{line = reader.readLine();}catch(Exception e){line = null;} 
	            	si = 0;
	        	}
 
	        	reader.close();  
	        return l;
		}
		catch(Exception e){}
		return null;
    }
    
    private static KnowledgeBase readKnowledgeBase(String file) throws Exception {
        KnowledgeBuilder kbuilder = KnowledgeBuilderFactory.newKnowledgeBuilder();
        kbuilder.add(ResourceFactory.newClassPathResource(file + "Rules.drl"), ResourceType.DRL);
        KnowledgeBuilderErrors errors = kbuilder.getErrors();
        if (errors.size() > 0) {
            for (KnowledgeBuilderError error: errors) {
                System.err.println(error);
            }
            throw new IllegalArgumentException("Could not parse knowledge.");
        }
        KnowledgeBase kbase = KnowledgeBaseFactory.newKnowledgeBase();
        kbase.addKnowledgePackages(kbuilder.getKnowledgePackages());
        return kbase;
    }
 
	@GET
	@Path("/getShirts")
	@Produces({MediaType.APPLICATION_JSON})
	public List<Shirt> getShirts()
	{
		return _shirts;
	}
	
	@GET
	@Path("/getOne")
	@Produces({MediaType.APPLICATION_JSON})
	public Shirt getOne(@QueryParam("id") int id)
	{
		return _shirts.get(id);
	}

	@GET
	@Path("/getDecisions")
	@Produces({MediaType.APPLICATION_JSON})
	public DecisionItem getDecisions(@QueryParam("id") int id)
	{
		DecisionItem i = new DecisionItem(id);
		try {  
    		Laundry l = GetLaundryy(id);
    		if(l!=null)
    		{
    			externalSession.insert(l);
    			externalSession.insert(i);
    			externalSession.fireAllRules();  
    		}
        } catch (Throwable t) {
            t.printStackTrace();
        }
		return i;
	}

}