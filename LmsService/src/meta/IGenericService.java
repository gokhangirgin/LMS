package meta;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
@Produces({MediaType.APPLICATION_JSON})
@Consumes({MediaType.APPLICATION_JSON})
public interface IGenericService<T> {
	@POST
	@Path("/Add")
	public int Add(T item);
	@PUT
	@Path("/Update")
	public void Update(T item);
	@DELETE
	@Path("/Delete")
	public void Delete(@QueryParam("id") int id);
	@GET
	@Path("/GetOne")
	public T GetOne(@QueryParam("id") int id);
}
