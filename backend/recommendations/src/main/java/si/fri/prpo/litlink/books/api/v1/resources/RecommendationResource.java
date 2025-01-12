package si.fri.prpo.litlink.recommendations.api.v1.resources;

import si.fri.prpo.litlink.dtos.RecommendationDto;
import si.fri.prpo.litlink.recommendations.services.RecommendationBean;

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

@Path("recommendations")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class RecommendationResource {

    @Inject
    private RecommendationBean recommendationBean;

    @GET
    @Path("{userId}")
    @Operation(summary = "Fetch recommendations for a user", description = "Retrieves a list of book recommendations for the specified user.")
    @APIResponses({
        @APIResponse(
            responseCode = "200",
            description = "List of recommendations retrieved successfully.",
            content = @Content(mediaType = "application/json", schema = @Schema(type = SchemaType.ARRAY, implementation = RecommendationDto.class))
        ),
        @APIResponse(
            responseCode = "404",
            description = "User not found or no recommendations available."
        ),
        @APIResponse(
            responseCode = "500",
            description = "Internal server error."
        )
    })
    public Response getRecommendations(@PathParam("userId") Integer userId) {
        List<RecommendationDto> recommendations = recommendationBean.getRecommendationsForUser(userId);
        return Response.ok(recommendations).build();
    }

    @GET
    @Path("readiness")
    public Response Ready() {
        String resMessage = recommendationBean.checkReady();
        if (resMessage.equals("ok"))
            return Response.ok().build();
        else
           return Response.serverError().entity(resMessage).build();
    }

    @GET
    @Path("healthz")
    public Response Alive() {
        return Response.ok().build();
    }
}
