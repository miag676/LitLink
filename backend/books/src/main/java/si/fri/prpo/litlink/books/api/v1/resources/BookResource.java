package si.fri.prpo.litlink.books.api.v1.resources;

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

import si.fri.prpo.litlink.dtos.AddBookDto;
import si.fri.prpo.litlink.books.services.BookBean;

@Path("books")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class BookResource {

    @Inject
    BookBean bookBean;

    @GET
    public Response getBooks() {
        return Response.ok(bookBean.getAllBooks()).build();
    }

    @GET
    @Path("{id}")
    public Response getBook( @PathParam("id") int id) {
        return Response.ok(bookBean.getBook(id)).build();
    }

     
    @POST
    public Response addBook( AddBookDto book ) {
        return Response.ok(bookBean.addBook(book)).build();
    }
}
