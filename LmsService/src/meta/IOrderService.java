package meta;

import models.Order;
import javax.ws.rs.Path;
@Path("/Order")
public interface IOrderService extends IGenericService<Order> {
}
