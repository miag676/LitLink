package si.fri.prpo.litlink.lists.api.v1.resources;

import si.fri.prpo.litlink.lists.services.ListsBean;
import si.fri.prpo.litlink.dtos.ListDto;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("lists")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ListsResource {

    @Inject
    private ListsBean listsBean;

    @GET
    public Response getAllLists() {
        return Response.ok(listsBean.getAllLists()).build();
    }

    @POST
    public Response createList(ListDto listDto) {
        return Response.status(Response.Status.CREATED)
                       .entity(listsBean.createList(listDto))
                       .build();
    }

    @POST
    @Path("{id}/books/{bookId}")
    public Response addBookToList(@PathParam("id") Integer listId, @PathParam("bookId") Integer bookId) {
        listsBean.addBookToList(listId, bookId);
        return Response.noContent().build();
    }

    @DELETE
    @Path("{id}/books/{bookId}")
    public Response removeBookFromList(@PathParam("id") Integer listId, @PathParam("bookId") Integer bookId) {
        listsBean.removeBookFromList(listId, bookId);
        return Response.noContent().build();
    }

    @DELETE
    @Path("{id}")
    public Response deleteList(@PathParam("id") Integer listId) {
        listsBean.deleteList(listId);
        return Response.noContent().build();
    }
}
