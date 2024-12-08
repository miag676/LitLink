package si.fri.prpo.litlink.users.api.v1.resources;

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

import si.fri.prpo.litlink.dtos.RegisterDto;
import si.fri.prpo.litlink.dtos.LoginDto;
import si.fri.prpo.litlink.dtos.UserDto;
import si.fri.prpo.litlink.users.services.UserBean;

@Path("users")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UserResource {

    @Inject
    UserBean userBean;

    @GET
    public Response getUsers() {
        return Response.ok(userBean.getAllUsers()).build();
    }

    @GET
    @Path("{id}")
    public Response getUser( @PathParam("id") int id) {
        return Response.ok(userBean.getUser(id)).build();
    }

     
    @POST
    @Path("register")
    public Response registerUser( RegisterDto reg ) {
        return Response.ok(userBean.addUser(reg)).build();
    }

    @POST
    @Path("login")
    @Operation(summary = "Login a user", description = "Validates the user's credentials and returns user details on success.")
    @APIResponses({
        @APIResponse(
            responseCode = "200",
            description = "Login successful",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserDto.class))
        ),
        @APIResponse(
            responseCode = "401",
            description = "Invalid username or password"
        )
    })
    public Response loginUser(@RequestBody(description = "User login details", required = true, content = @Content(schema = @Schema(implementation = LoginDto.class))) LoginDto loginDto) {
        var user = userBean.login(loginDto.getUserName(), loginDto.getPassword());

        if (user != null) {
            UserDto userDto = new UserDto(user.getId(), user.getName(), user.getLastName(), user.getUserName(), user.getEmail());
            return Response.ok(userDto).build();
        } else {
            return Response.status(Response.Status.UNAUTHORIZED).entity("Invalid username or password").build();
        }
    }
}
