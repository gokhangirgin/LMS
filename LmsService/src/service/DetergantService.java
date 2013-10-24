package service;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

import org.hibernate.Session;

import meta.IDetergantService;
import models.Detergant;
import models.HbUtil;

public class DetergantService implements IDetergantService {

	@Override
	@POST
	@Path("/Add")
	public int Add(Detergant item) {
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
	public void Update(Detergant item) {
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
		Detergant item = (Detergant)session.get(Detergant.class, id);
		session.beginTransaction();
		session.delete(item);
		session.getTransaction().commit();
		session.close();
	}

	@Override
	@GET
	@Path("/GetOne")
	public Detergant GetOne(@QueryParam("id") int id) {
		Session session = HbUtil.getSessionFactory().openSession();
		Detergant d = (Detergant)session.get(Detergant.class, id);
		return d;
	}

}
