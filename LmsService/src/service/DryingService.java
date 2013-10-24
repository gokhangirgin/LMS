package service;

import java.util.Set;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

import org.hibernate.Session;

import meta.IDryingService;
import models.Drying;
import models.HbUtil;
import models.Item;
import models.Phase;

public class DryingService implements IDryingService {

	@Override
	@POST
	@Path("/Add")
	public int Add(Drying item) {
		Session session = HbUtil.getSessionFactory().openSession();
		session.beginTransaction();
		Set<Item> items = item.getBasket().getItems();
		for (Item x : items) {
			x.setLocation(Phase.Drying);
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
	public void Update(Drying item) {
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
		Drying item = (Drying)session.get(Drying.class, id);
		session.beginTransaction();
		session.delete(item);
		session.getTransaction().commit();
		session.close();
	}

	@Override
	@GET
	@Path("/GetOne")
	public Drying GetOne(@QueryParam("id")int id) {
		Session session = HbUtil.getSessionFactory().openSession();
		Drying d = (Drying)session.get(Drying.class, id);
		return d;
	}

}
