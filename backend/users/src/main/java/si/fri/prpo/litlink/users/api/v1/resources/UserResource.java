package si.fri.prpo.litlink.users.api.v1.resources;

import java.util.*;
import javax.annotation.security.RolesAllowed;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import si.fri.prpo.litlink.dtos.RegisterDto;
import si.fri.prpo.litlink.users.services.UserBean;

@Path("users")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UserResource {

    @Inject
    UserBean userBean;

    @GET
    public Response getUsers() {
        return Response.ok(userBean.getAllUsers()).build();
    }

    @GET
    @Path("{id}")
    public Response getUser( @PathParam("id") int id) {
        return Response.ok(userBean.getUser(id)).build();
    }

     
    @POST
    @Path("register")
    public Response registerUser( RegisterDto reg ) {
        return Response.ok(userBean.addUser(reg)).build();
    }
}
