package service;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import models.HbUtil;
import models.Item;
import models.OrderView;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;
@Path("/OrderView")
@Produces({MediaType.APPLICATION_JSON})
@Consumes({MediaType.APPLICATION_JSON})
public class OrderViewService {

	
	@SuppressWarnings({ "rawtypes" })
	@GET
	@Path("/Perc")
	public List Perc(@QueryParam("id") int id) {
		Session session = HbUtil.getSessionFactory().openSession();
		Query q = session.createSQLQuery("SELECT oi.OrderId,CAST(((SUM(i.Location) * 0.111) / COUNT(*)) * 100 AS unsigned) as Percentage FROM Item i INNER JOIN orderitems oi ON oi.ItemId = i.Id AND oi.OrderId = (SELECT Id FROM Orders WHERE CustomerId = :customerId) GROUP BY oi.OrderId")			
				.setParameter("customerId", id).setResultTransformer(Transformers.aliasToBean(OrderView.class));
		List results = q.list();
		session.close();
		return results;
	}
	@SuppressWarnings("rawtypes")
	@GET
	@Path("/Items")
	public List<Item> Items(@QueryParam("id") int id) {
		
		Session session = HbUtil.getSessionFactory().openSession();
		Query q = session.createSQLQuery("SELECT i.* FROM Item i INNER JOIN orderitems oi ON oi.ItemId = i.Id AND oi.OrderId = :orderId")
				.setParameter("orderId", id);
		List<Item> itemList = new ArrayList<Item>();
		List items = q.list();
		Object[] row;
		Item i;
		for (Object rows : items) {
			row = (Object[])rows;
			i = new Item();
			i.setId((Integer) row[0]);
			i.setType(models.Type.values()[(Integer)row[1]]);
			i.setMaterial(models.Material.values()[(Integer)row[2]]);
			i.setColor((Boolean) row[3]);
			i.setWeight((Double) row[4]);
			i.setLocation(models.Phase.values()[(Integer)row[5]]);
			i.setWashedCount((Integer) row[6]);
			itemList.add(i);
		}
		session.close();
		return itemList;
	}
}
