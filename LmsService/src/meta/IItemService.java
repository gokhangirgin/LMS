package meta;
import models.Item;
import javax.ws.rs.Path;
@Path("/Item")
public interface IItemService extends IGenericService<Item> {

}
