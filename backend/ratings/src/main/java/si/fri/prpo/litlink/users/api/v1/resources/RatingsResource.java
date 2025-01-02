package si.fri.prpo.litlink.ratings.api.v1.resources;

import si.fri.prpo.litlink.ratings.services.RatingsBean;
import si.fri.prpo.litlink.dtos.RatingDto;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("ratings")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class RatingsResource {

    @Inject
    private RatingsBean ratingsBean;

    @GET
    public Response getAllRatings() {
        List<RatingDto> ratings = ratingsBean.getAllRatings();
        return Response.ok(ratings).build();
    }

    @POST
    public Response addRating(RatingDto ratingDto) {
        RatingDto created = ratingsBean.addRating(ratingDto);
        return Response.status(Response.Status.CREATED).entity(created).build();
    }

    @DELETE
    @Path("{id}")
    public Response deleteRating(@PathParam("id") Integer id) {
        ratingsBean.deleteRating(id);
        return Response.noContent().build();
    }
}
