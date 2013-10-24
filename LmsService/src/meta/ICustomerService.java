package meta;
import javax.ws.rs.Path;
import models.Customer;
@Path("/Customer")
public interface ICustomerService extends IGenericService<Customer> {

}
