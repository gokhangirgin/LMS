package service;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

import org.hibernate.Session;

import meta.IBleachService;
import models.Bleach;
import models.HbUtil;

public class BleachService implements IBleachService {

	@Override
	@POST
	@Path("/Add")
	public int Add(Bleach item) {
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
	public void Update(Bleach item) {
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
		Bleach b = (Bleach)session.get(Bleach.class, id);
		session.beginTransaction();
		session.delete(b);
		session.getTransaction().commit();
		session.close();
	}

	@Override
	@GET
	@Path("/GetOne")
	public Bleach GetOne(@QueryParam("id") int id) {
		Session session = HbUtil.getSessionFactory().openSession();
		Bleach b = (Bleach)session.get(Bleach.class, id);
		return b;
	}

}
