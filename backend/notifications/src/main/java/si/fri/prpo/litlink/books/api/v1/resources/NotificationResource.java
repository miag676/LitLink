package si.fri.prpo.litlink.notifications.api.v1.resources;

import si.fri.prpo.litlink.notifications.services.NotificationBean;

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

@Path("notifications")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class NotificationResource {

    @Inject
    private NotificationBean notificationBean;

    @POST
    @Path("add")
    @Consumes(MediaType.TEXT_PLAIN)
    @Operation(summary = "Add notification for all users", description = "Sends a notification message to all registered users.")
    @APIResponses({
        @APIResponse(
            responseCode = "200",
            description = "Notification successfully sent to all users.",
            content = @Content(mediaType = "text/plain", schema = @Schema(type = SchemaType.STRING, example = "Notification sent to all users."))
        ),
        @APIResponse(
            responseCode = "500",
            description = "Internal server error."
        )
    })
    public Response addNotification(String message) {
        notificationBean.addNotificationForAllUsers(message);
        return Response.ok("Notification sent to all users.").build();
    }

    @GET
    @Path("user/{userId}")
    @Operation(summary = "Get notifications for a user", description = "Fetches all notifications for the specified user.")
    @APIResponses({
        @APIResponse(
            responseCode = "200",
            description = "List of notifications for the user.",
            content = @Content(mediaType = "application/json", schema = @Schema(type = SchemaType.ARRAY, implementation = String.class))
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
    public Response getNotifications(@PathParam("userId") Integer userId) {
        return Response.ok(notificationBean.getNotificationsByUserId(userId)).build();
    }

    @DELETE
    @Path("user/{userId}")
    @Operation(summary = "Clear notifications for a user", description = "Deletes all notifications for the specified user.")
    @APIResponses({
        @APIResponse(
            responseCode = "200",
            description = "All notifications cleared for the user.",
            content = @Content(mediaType = "text/plain", schema = @Schema(type = SchemaType.STRING, example = "Notifications cleared for user ID: 1"))
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
    public Response clearNotifications(@PathParam("userId") Integer userId) {
        notificationBean.clearNotificationsByUserId(userId);
        return Response.ok("Notifications cleared for user ID: " + userId).build();
    }
}
