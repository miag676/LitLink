package si.fri.prpo.litlink.ratings.api.v1.resources;

import si.fri.prpo.litlink.ratings.services.RatingsBean;
import si.fri.prpo.litlink.dtos.RatingDto;

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
import java.util.stream.Collectors;

@Path("ratings")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class RatingsResource {

    @Inject
    private RatingsBean ratingsBean;

    @GET
    @Operation(summary = "Get all ratings", description = "Retrieves a list of all ratings.")
    @APIResponses({
        @APIResponse(
            responseCode = "200",
            description = "List of ratings retrieved successfully.",
            content = @Content(mediaType = "application/json", schema = @Schema(type = SchemaType.ARRAY, implementation = RatingDto.class))
        ),
        @APIResponse(
            responseCode = "500",
            description = "Internal server error."
        )
    })
    public Response getAllRatings() {
        List<RatingDto> ratings = ratingsBean.getAllRatings();
        return Response.ok(ratings).build();
    }

    @POST
    @Operation(summary = "Add a new rating", description = "Creates a new rating.")
    @APIResponses({
        @APIResponse(
            responseCode = "201",
            description = "Rating successfully created.",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = RatingDto.class))
        ),
        @APIResponse(
            responseCode = "400",
            description = "Invalid rating data."
        ),
        @APIResponse(
            responseCode = "500",
            description = "Internal server error."
        )
    })
    public Response addRating(RatingDto ratingDto) {
        RatingDto created = ratingsBean.addRating(ratingDto);
        return Response.status(Response.Status.CREATED).entity(created).build();
    }

    @DELETE
    @Path("{id}")
    @Operation(summary = "Delete a rating", description = "Deletes a rating by its ID.")
    @APIResponses({
        @APIResponse(
            responseCode = "204",
            description = "Rating successfully deleted."
        ),
        @APIResponse(
            responseCode = "404",
            description = "Rating not found."
        ),
        @APIResponse(
            responseCode = "500",
            description = "Internal server error."
        )
    })
    public Response deleteRating(@PathParam("id") Integer id) {
        ratingsBean.deleteRating(id);
        return Response.noContent().build();
    }

    @GET
    @Path("global")
    @Operation(summary = "Get global average ratings", description = "Retrieves the global average ratings for all books.")
    @APIResponses({
        @APIResponse(
            responseCode = "200",
            description = "Global average ratings retrieved successfully.",
            content = @Content(mediaType = "application/json", schema = @Schema(type = SchemaType.OBJECT, example = "{\"1\": 4.5, \"2\": 3.8}"))
        ),
        @APIResponse(
            responseCode = "500",
            description = "Internal server error."
        )
    })
    public Response getGlobalAverageRatings() {
        List<Object[]> globalRatings = ratingsBean.getGlobalAverageRatings();

        // Convert to a map or list of DTOs for readability
        var result = globalRatings.stream()
                                  .collect(Collectors.toMap(
                                      r -> (Integer) r[0], // Book ID
                                      r -> (Double) r[1]  // Average Rating
                                  ));

        return Response.ok(result).build();
    }

    @GET
    @Path("user/{userId}")
    @Operation(summary = "Get ratings by user", description = "Retrieves all ratings made by a specific user.")
    @APIResponses({
        @APIResponse(
            responseCode = "200",
            description = "List of ratings by the user retrieved successfully.",
            content = @Content(mediaType = "application/json", schema = @Schema(type = SchemaType.ARRAY, implementation = RatingDto.class))
        ),
        @APIResponse(
            responseCode = "404",
            description = "User not found."
        ),
        @APIResponse(
            responseCode = "500",
            description = "Internal server error."
        )
    })
    public Response getRatingsByUser(@PathParam("userId") Integer userId) {
        List<RatingDto> userRatings = ratingsBean.getRatingsByUser(userId);
        return Response.ok(userRatings).build();
    }




}
