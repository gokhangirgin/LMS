package test;
import java.sql.Date;
import java.util.Calendar;
import java.util.List;
import java.util.Set;

import models.Basket;
import models.Bleach;
import models.Customer;
import models.Detergant;
import models.Drying;
import models.FabricSoftener;
import models.Gender;
import models.Item;
import models.Material;
import models.Order;
import models.Personnel;
import models.Phase;
import models.Status;
import models.Type;
import models.Washing;
import models.Water;

import org.hibernate.*;
public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Session session = HbUtil.getSessionFactory().openSession();
		Transaction txc = session.beginTransaction();
		/**
		 * Customer test
		 * Customer c = new Customer();
		c.setAddress("izmir");
		Calendar cal = Calendar.getInstance();
		cal.set(1988, 11,11);
		c.setDateOfBirth(cal.getTime());
		c.setEmail("gokhan@girgin.com");
		c.setFirstName("Gökhan");
		c.setLastName("Girgin");
		c.setGender(Gender.Male);
		c.setPassword("123456");
		c.setPhone("5557039039");
		System.out.println(c);
		session.save(c);**/
		/**
		 * 
		 * */
		/**Personel Test
		Personnel c = new Personnel();
		c.setAddress("izmir");
		Calendar cal = Calendar.getInstance();
		cal.set(1988, 11,11);
		c.setDateOfBirth(cal.getTime());
		c.setEmail("gokhan@girgin.com");
		c.setFirstName("Gökhan");
		c.setLastName("Girgin");
		c.setGender(Gender.Male);
		c.setTitle("Administrator");
		c.setPhone("5557039039");
		session.save(c);
		**/
		/** Bleach Test
		Bleach b = new Bleach();
		b.setBrand("Bleach Name");
		b.setAbbritionEffect(12.3);
		b.setUnitPrice(7.09);
		session.save(b);
		**/
		/**Detergant test
		Detergant d = new Detergant();
		d.setForColored(false);
		d.setName("Omo");
		d.setOdor("odor?");
		d.setUnitPrice(17.43);
		session.save(d);
		**/
		/**
		FabricSoftener f = new FabricSoftener();
		f.setAbbritionEffect(1.3);
		f.setBrand("Fabric Softener Name");
		f.setUnitPrice(4.63);
		session.save(f);
		**/
		/** suyu db ye ekle
		Water wx = new Water(45.3,7.2,1.2,1,1,1,3.2,1.7,4.8);
		session.save(wx);
		**/
		/** eklenen veriyi çek
		Water wx = (Water)session.load(Water.class,new Integer(1));
		System.out.println(wx.getBleachAmount());
		System.out.println(wx.getDetergant().getName());
		**/
		/**Item test
		Item item = new Item();
		item.setColor(true);
		item.setLocation(Phase.Sorting);
		item.setMaterial(Material.Fiber);
		item.setType(Type.Community);
		item.setWashedCount(2);
		item.setWeight(0.187);
		session.save(item);
		**/
		/**
		Basket b = new Basket();
		Calendar cal = Calendar.getInstance();
		b.setEntryTime(cal.getTime());
		b.setFinishTime(cal.getTime());
		b.setType(Type.Community);
		session.save(b);
		**/
		/**Sepete ekledim
		Basket bx = (Basket)session.get(Basket.class, new Integer(1));
		List<Item> items = session.createQuery("from Item where Material=0").list();
		bx.setItems(items);
		session.update(bx);
		*/
		/** order insert
		Order o = new Order();
		Calendar cal = Calendar.getInstance();
		o.setCustomer(new Customer(1));
		o.setPersonnel(new Personnel(1));
		o.setRealPickUpDate(cal.getTime());
		o.setStatus(Status.Complete);
		o.setRealPickUpDate(cal.getTime());
		o.setTransactionDate(cal.getTime());
		o.setEstimatedPickUpDate(cal.getTime());
		session.save(o);
		**/
		/**Order Ýtems
		Order o = (Order)session.get(Order.class, 1);
		List<Item> items = session.createQuery("from Item").list();
		o.setItems(items);
		session.update(o);
		**/
		/**Washing insert
		Washing c = new Washing();
		c.setBasket(new Basket(1));
		c.setStartTime(Calendar.getInstance().getTime());
		c.setEndTime(Calendar.getInstance().getTime());
		c.setMachineId(1);
		c.setWater(new Water(1));
		session.save(c);
		**/
		/** Basket Update
		Basket b =(Basket)session.get(Basket.class, 1);
		b.setStep(Phase.Drying);
		session.update(b);
		**/
		Drying dr = new Drying();
		dr.setBasket(new Basket(1));
		dr.setStartTime(Calendar.getInstance().getTime());
		dr.setEndTime(Calendar.getInstance().getTime());
		dr.setOpenAir(true);
		dr.setTemperature(32.5);
		session.save(dr);
		session.getTransaction().commit();
		session.close();
	}

}
