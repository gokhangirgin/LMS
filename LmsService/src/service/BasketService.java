package service;

import java.util.Set;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

import org.hibernate.Session;

import meta.IBasketService;
import models.Basket;
import models.HbUtil;
import models.Item;

public class BasketService implements IBasketService {

	@Override
	@POST
	@Path("/Add")
	public int Add(Basket item) {
		// TODO Auto-generated method stub
		Session session = HbUtil.getSessionFactory().openSession();
		session.beginTransaction();
		session.save(item);
		session.flush();
		session.getTransaction().commit();
		session.close();
		return item.getId();
	}

	@Override
	@PUT
	@Path("/Update")
	public void Update(Basket item) {
		// TODO Auto-generated method stub
		Session session = HbUtil.getSessionFactory().openSession();
		session.beginTransaction();
		session.update(item);//basketin lokasyonunu güncelliyoruz
		Set<Item> items = item.getItems();
		for (Item it : items) {
			it.setLocation(item.getStep());
			session.update(item);//basketin içerisindeki itemler
		}
		session.getTransaction().commit();
		session.close();
	}


	@Override
	@DELETE
	@Path("/Delete")
	public void Delete(@QueryParam("id") int id) {
		Session session = HbUtil.getSessionFactory().openSession();
		Basket b = (Basket)session.get(Basket.class, id);
		session.delete(b);
		session.close();
	}

	@Override
	@GET
	@Path("/GetOne")
	public Basket GetOne(@QueryParam("id") int id) {
		Session session = HbUtil.getSessionFactory().openSession();
		Basket b = (Basket)session.get(Basket.class, id);
		return b;
	}

}
