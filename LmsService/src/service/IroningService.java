package service;

import java.util.Set;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

import org.hibernate.Session;

import meta.IIroningService;
import models.HbUtil;
import models.Ironing;
import models.Item;
import models.Phase;

public class IroningService implements IIroningService {

	@Override
	@POST
	@Path("/Add")
	public int Add(Ironing item) {
		Session session = HbUtil.getSessionFactory().openSession();
		session.beginTransaction();
		Set<Item> items = item.getBasket().getItems();
		for (Item x : items) {
			x.setLocation(Phase.Ironing);
		}
		session.save(item);
		session.flush();
		session.getTransaction().commit();
		session.close();
		return item.getId();
	}

	@Override
	@PUT
	@Path("/Update")
	public void Update(Ironing item) {
		Session session = HbUtil.getSessionFactory().openSession();
		session.beginTransaction();
		session.update(item);
		session.getTransaction().commit();
		session.close();
	}

	@Override
	@DELETE
	@Path("/Delete")
	public void Delete(@QueryParam("id") int id) {
		Session session = HbUtil.getSessionFactory().openSession();
		Ironing b = (Ironing)session.get(Ironing.class, id);
		session.beginTransaction();
		session.delete(b);
		session.getTransaction().commit();
		session.close();
	}

	@Override
	@GET
	@Path("/GetOne")
	public Ironing GetOne(@QueryParam("id") int id) {
		Session session = HbUtil.getSessionFactory().openSession();
		Ironing b = (Ironing)session.get(Ironing.class, id);
		return b;
	}

}
