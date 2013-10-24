package com.sample;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class fileTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
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
	    		System.out.println(v1);
	            si = line.indexOf(";", si) + 1; 
	            String v2 = line.substring(line.indexOf("=", si) + 1, line.indexOf(";", si));
	    		System.out.println(v2);
	            si = line.indexOf(";", si) + 1;
	            String v3 = line.substring(line.indexOf("=", si) + 1, line.indexOf(";", si));
	    		System.out.println(v3);
	            si = line.indexOf(";", si) + 1;
	            String v4 = line.substring(line.indexOf("=", si) + 1, line.indexOf(";", si));
	    		System.out.println(v4);
	            si = line.indexOf(";", si) + 1;
	            String v5 = line.substring(line.indexOf("=", si) + 1, line.indexOf(";", si));
	    		System.out.println(v5);
	            si = line.indexOf(";", si) + 1;
	            
	            Laundry l = new Laundry(Integer.parseInt(v1), Integer.parseInt(v2), Integer.parseInt(v3), Integer.parseInt(v4),  0, Float.parseFloat(v5));
	            laundries.add(l);
	            si = 0;
	        }
		}
		catch(Exception e){
			e.printStackTrace();
			}
		System.out.println(3);
	}

}
