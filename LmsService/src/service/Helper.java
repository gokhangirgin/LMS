package service;
import java.util.Set;

import org.hibernate.Session;

import models.Basket;
import models.HbUtil;
import models.Item;
import models.Phase;
public class Helper {

	private static Phase GetNextStep(Phase p)
	{
		if(p == Phase.sortingForWashing)
			return Phase.Washing;
		else if(p == Phase.Washing)
			return Phase.sortingForDrying;
		else if(p==Phase.sortingForDrying)
			return Phase.Drying;
		else if(p==Phase.Drying)
			return Phase.sortingForIroning;
		else if(p== Phase.sortingForIroning)
			return Phase.Ironing;
		else if(p== Phase.Ironing)
			return Phase.sortingForPackaging;
		else if(p== Phase.sortingForPackaging)
			return Phase.Packaging;
		else if(p== Phase.Packaging)
			return Phase.OutOfFactory;
		else if(p== Phase.Discard)
			return Phase.Discard;
		else
			return Phase.OutOfFactory;
		
	}
	//kullanýlabilir
	public static void UpdateItemLoc(Basket b)
	{
		
		Set<Item> items = b.getItems();//ilgili basketin itemleri
		Session session = HbUtil.getSessionFactory().openSession(); //hibernate session
		session.beginTransaction(); //yüzlerce item varsa transaction yapalým yamulmasýn performansta
		Phase p; //temp Phase lokasyon için
		for (Item item : items) { //gelen basketin 
			p = item.getLocation();
			item.setLocation(GetNextStep(p));
			session.update(item);
		}
		session.getTransaction().commit();
		session.close();
	}
	/*
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Phase p = Phase.sortingForDrying;
		System.out.println(GetNextStep(p));
	}
	*/
}
