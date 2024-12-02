package si.fri.prpo.litlink.api.v1.viri;

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
import si.fri.prpo.litlink.zrna.UporabnikiZrno;

@Path("users")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UporabnikiVir {

    @Inject
    UporabnikiZrno uporabnikiZrno;

    @GET
    public Response pridobiUporabnike() {
        return Response.ok(uporabnikiZrno.pridobiUporabnike()).build();
    }

    @GET
    @Path("{id}")
    public Response pridobiUporabnika( @PathParam("id") int id) {
        return Response.ok(uporabnikiZrno.pridobiUporabnika(id)).build();
    }

    @POST
    @Path("register")
    public Response registerUser( RegisterDto reg ) {
        return Response.ok(uporabnikiZrno.dodajUporabnika(reg)).build();
    }
}
