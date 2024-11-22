package wsrest;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import dao.ClienteDao;
import model.Cliente;

@Path("/cliente")
public class ClienteRest {

    @POST
    @Path("/cadastrar")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public Response cadastrar(Cliente cliente) {
        ClienteDao dao = new ClienteDao();
        return Response.status(201).entity(dao.salvar(cliente)).build();

    }

    @GET
    @Path("/listar")
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.APPLICATION_JSON)
    public Response listar() {
        ClienteDao dao = new ClienteDao();
        return Response.status(200).entity(dao.listar()).build();

    }

    @GET
    @Path("/getporid/{id}")
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPorId(@PathParam(value = "id") int id) {
        ClienteDao dao = new ClienteDao();
        return Response.status(200).entity(dao.getPorId(id)).build();

    }

    @PUT
    @Path("/alterar")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public Response alterar(Cliente cliente) {
        ClienteDao dao = new ClienteDao();
        return Response.status(200).entity(dao.salvar(cliente)).build();

    }

    @DELETE
    @Path("/excluir/{id}")
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.TEXT_PLAIN)
    public Response excluir(@PathParam(value = "id") int id) {
        ClienteDao dao = new ClienteDao();
        return Response.status(200).entity(dao.excluir(id)).build();

    }

   


    

}
