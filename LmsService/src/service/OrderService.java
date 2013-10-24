package service;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import org.hibernate.Session;
import meta.IOrderService;
import models.HbUtil;
import models.Order;
public class OrderService implements IOrderService {

	@Override
	@POST
	@Path("/Add")
	public int Add(Order item) {
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
	public void Update(Order item) {
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
		Order b = (Order)session.get(Order.class, id);
		session.beginTransaction();
		session.delete(b);
		session.getTransaction().commit();
		session.close();
	}

	@Override
	@GET
	@Path("/GetOne")
	public Order GetOne(@QueryParam("id") int id) {
		Session session = HbUtil.getSessionFactory().openSession();
		Order b = (Order)session.get(Order.class, id);
		return b;
	}




}
