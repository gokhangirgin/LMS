package service;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

import org.hibernate.Session;

import meta.IFabricSoftenerService;
import models.HbUtil;
import models.FabricSoftener;

public class FabricSoftenerService implements IFabricSoftenerService {

	@Override
	@POST
	@Path("/Add")
	public int Add(FabricSoftener item) {
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
	public void Update(FabricSoftener item) {
		Session session = HbUtil.getSessionFactory().openSession();
		session.beginTransaction();
		session.update(item);
		session.getTransaction().commit();
		session.close();
	}

	@Override
	@DELETE
	@Path("/Delete")
	public void Delete(@QueryParam("id")int id) {
		Session session = HbUtil.getSessionFactory().openSession();
		FabricSoftener item = (FabricSoftener)session.get(FabricSoftener.class, id);
		session.beginTransaction();
		session.save(item);
		session.getTransaction().commit();
		session.close();
	}

	@Override
	@GET
	@Path("/GetOne")
	public models.FabricSoftener GetOne(int id) {
		Session session = HbUtil.getSessionFactory().openSession();
		models.FabricSoftener f = (models.FabricSoftener)session.get(models.FabricSoftener.class, id);
		return f;
	}

}
