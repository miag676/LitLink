package si.fri.prpo.litlink.ratings.api.v1.resources;

import si.fri.prpo.litlink.ratings.services.ReviewsBean;
import si.fri.prpo.litlink.dtos.ReviewDto;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("reviews")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ReviewsResource {

    @Inject
    private ReviewsBean reviewsBean;

    @GET
    public Response getAllReviews() {
        List<ReviewDto> reviews = reviewsBean.getAllReviews();
        return Response.ok(reviews).build();
    }

    @POST
    public Response addReview(ReviewDto reviewDto) {
        ReviewDto created = reviewsBean.addReview(reviewDto);
        return Response.status(Response.Status.CREATED).entity(created).build();
    }

    @DELETE
    @Path("{id}")
    public Response deleteReview(@PathParam("id") Integer id) {
        reviewsBean.deleteReview(id);
        return Response.noContent().build();
    }
}
