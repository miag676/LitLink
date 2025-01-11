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

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;

import si.fri.prpo.litlink.dtos.AddBookDto;
import si.fri.prpo.litlink.books.services.BookBean;

@Path("books")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class BookResource {

    @Inject
    BookBean bookBean;

    @GET
    @Operation(summary = "Fetch all books", description = "Retrieves a list of all books available.")
    @APIResponses({
        @APIResponse(
            responseCode = "200",
            description = "List of books",
            content = @Content(mediaType = "application/json", schema = @Schema(type = SchemaType.ARRAY, implementation = AddBookDto.class))
        ),
        @APIResponse(
            responseCode = "500",
            description = "Internal server error"
        )
    })
    public Response getBooks() {
        return Response.ok(bookBean.getAllBooks()).build();
    }

    @GET
    @Path("{id}")
    @Operation(summary = "Fetch book details", description = "Retrieves details for a specific book by its ID.")
    @APIResponses({
        @APIResponse(
            responseCode = "200",
            description = "Book details",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = AddBookDto.class))
        ),
        @APIResponse(
            responseCode = "404",
            description = "Book not found"
        ),
        @APIResponse(
            responseCode = "500",
            description = "Internal server error"
        )
    })
    public Response getBook( @PathParam("id") int id) {
        return Response.ok(bookBean.getBook(id)).build();
    }

     
    @POST
    @Operation(summary = "Add a new book", description = "Adds a new book with the provided details.")
    @APIResponses({
        @APIResponse(
            responseCode = "200",
            description = "Book successfully added",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = AddBookDto.class))
        ),
        @APIResponse(
            responseCode = "400",
            description = "Invalid book data"
        ),
        @APIResponse(
            responseCode = "500",
            description = "Internal server error"
        )
    })
    public Response addBook( AddBookDto book ) {
        return Response.ok(bookBean.addBook(book)).build();
    }
}
