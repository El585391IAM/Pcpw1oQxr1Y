// 代码生成时间: 2025-10-01 03:05:23
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.HashMap;
import java.util.Map;

@Path("/api")
public class ApiResponseFormatter {

    @GET
    @Path("/format-response")
    @Produces(MediaType.APPLICATION_JSON)
    public Response formatResponse() {
        try {
            Map<String, Object> response = new HashMap<>();
            response.put("message", "This is a formatted response");
            response.put("status", "success");
            return Response.ok(response).build();
        } catch (Exception e) {
            // Handle unexpected errors and return a formatted error response
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("message", "An error occurred");
            errorResponse.put("status", "error");
            errorResponse.put("errorDetail", e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(errorResponse).build();
        }
    }
}
