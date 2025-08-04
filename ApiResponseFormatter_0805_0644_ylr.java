// 代码生成时间: 2025-08-05 06:44:28
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import java.util.HashMap;
import java.util.Map;

// ApiResponseFormatter class is a RESTful web service resource class.
@Path("/api")
public class ApiResponseFormatter {

    // The getFormattedResponse method returns a formatted API response with a status.
    // It takes a message and a status code as parameters.
    @GET
    @Path("/formattedResponse")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getFormattedResponse(
            @QueryParam("message") String message,
            @QueryParam("status") int status) {

        try {
            // Create a map to hold the response data.
            Map<String, Object> response = new HashMap<>();

            // Add the message and status to the response map.
            response.put("message", message);
            response.put("status", status);

            // Return the response as a JSON object with the provided status code.
            return Response.status(status).entity(response).build();
        } catch (Exception e) {
            // Handle any exceptions that may occur and return an error response.
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("error", e.getMessage());
            errorResponse.put("status", 500);
            return Response.status(500).entity(errorResponse).build();
        }
    }

    // The getErrorResponse method returns an error formatted API response.
    // It takes an error message as a parameter.
    @GET
    @Path("/errorResponse")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getErrorResponse(@QueryParam("errorMessage") String errorMessage) {

        try {
            // Create a map to hold the error response data.
            Map<String, Object> errorResponse = new HashMap<>();

            // Add the error message to the response map.
            errorResponse.put("error", errorMessage);
            errorResponse.put("status", 500);

            // Return the error response as a JSON object with a 500 status code.
            return Response.status(500).entity(errorResponse).build();
        } catch (Exception e) {
            // In case of an exception, return a generic error message.
            Map<String, Object> internalErrorResponse = new HashMap<>();
            internalErrorResponse.put("error", "An internal error occurred.");
            internalErrorResponse.put("status", 500);
            return Response.status(500).entity(internalErrorResponse).build();
        }
    }
}
