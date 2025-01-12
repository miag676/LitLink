package si.fri.prpo.litlink.lists.api.v1.resources;

import si.fri.prpo.litlink.lists.services.ListsBean;
import si.fri.prpo.litlink.dtos.ListDto;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;


import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("lists")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ListsResource {

    @Inject
    private ListsBean listsBean;

    @GET
    @Operation(summary = "Fetch all lists", description = "Retrieves all lists.")
    @APIResponses({
        @APIResponse(
            responseCode = "200",
            description = "List of all lists",
            content = @Content(mediaType = "application/json", schema = @Schema(type = SchemaType.ARRAY, implementation = ListDto.class))
        ),
        @APIResponse(
            responseCode = "500",
            description = "Internal server error"
        )
    })
    public Response getAllLists() {
        return Response.ok(listsBean.getAllLists()).build();
    }
    @POST
    @Operation(summary = "Create a new list", description = "Creates a new list based on the provided details.")
    @APIResponses({
        @APIResponse(
            responseCode = "201",
            description = "List created successfully",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ListDto.class))
        ),
        @APIResponse(
            responseCode = "400",
            description = "Invalid list data"
        ),
        @APIResponse(
            responseCode = "500",
            description = "Internal server error"
        )
    })
    public Response createList(ListDto listDto) {
        return Response.status(Response.Status.CREATED)
                       .entity(listsBean.createList(listDto))
                       .build();
    }

    @POST
    @Path("{id}/books/{bookId}")
    @Operation(summary = "Add a book to a list", description = "Adds a book to the specified list.")
    @APIResponses({
        @APIResponse(
            responseCode = "204",
            description = "Book added to list successfully"
        ),
        @APIResponse(
            responseCode = "404",
            description = "List or book not found"
        ),
        @APIResponse(
            responseCode = "500",
            description = "Internal server error"
        )
    })
    public Response addBookToList(@PathParam("id") Integer listId, @PathParam("bookId") Integer bookId) {
        listsBean.addBookToList(listId, bookId);
        return Response.noContent().build();
    }

    @DELETE
    @Path("{id}/books/{bookId}")
    @Operation(summary = "Remove a book from a list", description = "Removes a book from the specified list.")
    @APIResponses({
        @APIResponse(
            responseCode = "204",
            description = "Book removed from list successfully"
        ),
        @APIResponse(
            responseCode = "404",
            description = "List or book not found"
        ),
        @APIResponse(
            responseCode = "500",
            description = "Internal server error"
        )
    })
    public Response removeBookFromList(@PathParam("id") Integer listId, @PathParam("bookId") Integer bookId) {
        listsBean.removeBookFromList(listId, bookId);
        return Response.noContent().build();
    }

    @DELETE
    @Path("{id}")
    @Operation(summary = "Delete a list", description = "Deletes the specified list.")
    @APIResponses({
        @APIResponse(
            responseCode = "204",
            description = "List deleted successfully"
        ),
        @APIResponse(
            responseCode = "404",
            description = "List not found"
        ),
        @APIResponse(
            responseCode = "500",
            description = "Internal server error"
        )
    })
    public Response deleteList(@PathParam("id") Integer listId) {
        listsBean.deleteList(listId);
        return Response.noContent().build();
    }

    @GET
    @Path("user/{userId}")
    @Operation(summary = "Fetch lists for a user", description = "Retrieves all lists belonging to the specified user.")
    @APIResponses({
        @APIResponse(
            responseCode = "200",
            description = "List of user's lists",
            content = @Content(mediaType = "application/json", schema = @Schema(type = SchemaType.ARRAY, implementation = ListDto.class))
        ),
        @APIResponse(
            responseCode = "404",
            description = "User not found"
        ),
        @APIResponse(
            responseCode = "500",
            description = "Internal server error"
        )
    })
    public Response getUserLists(@PathParam("userId") Integer userId) {
        List<ListDto> userLists = listsBean.getUserLists(userId); // Add logic in ListsBean
        return Response.ok(userLists).build();
    }

    @GET
    @Path("user/{userId}/books")
    @Operation(summary = "Fetch books for a user", description = "Retrieves all books belonging to the lists of the specified user.")
    @APIResponses({
        @APIResponse(
            responseCode = "200",
            description = "List of book IDs",
            content = @Content(
                mediaType = "application/json",
                schema = @Schema(type = SchemaType.ARRAY, example = "[101, 102, 103]")
            )
        ),
        @APIResponse(
            responseCode = "404",
            description = "User not found"
        ),
        @APIResponse(
            responseCode = "500",
            description = "Internal server error"
        )
    })
    public Response getBooksByUser(@PathParam("userId") Integer userId) {
        List<Integer> books = listsBean.getBooksByUser(userId);
        return Response.ok(books).build();
    }

    @GET
    @Path("readiness")
    public Response Ready() {
        boolean dbResponse = listsBean.checkDBConn();
        if (dbResponse)
            return Response.ok().build();
        else
            return Response.serverError().header("Error message", "DB is not responding").build();
    }

    @GET
    @Path("healthz")
    public Response Alive() {
        return Response.ok().build();
    }

}
