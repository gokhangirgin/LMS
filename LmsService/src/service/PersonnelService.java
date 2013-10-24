package service;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

import org.hibernate.Session;

import meta.IPersonnelService;
import models.HbUtil;
import models.Personnel;

public class PersonnelService implements IPersonnelService {

	@Override
	@POST
	@Path("/Add")
	public int Add(Personnel item) {
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
	public void Update(Personnel item) {
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
		Personnel b = (Personnel)session.get(Personnel.class, id);
		session.beginTransaction();
		session.delete(b);
		session.getTransaction().commit();
		session.close();
	}

	@Override
	@GET
	@Path("/GetOne")
	public Personnel GetOne(@QueryParam("id") int id) {
		Session session = HbUtil.getSessionFactory().openSession();
		Personnel b = (Personnel)session.get(Personnel.class, id);
		return b;
	}

}
