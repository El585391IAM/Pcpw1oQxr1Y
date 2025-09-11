// 代码生成时间: 2025-09-12 06:26:32
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.glassfish.jersey.server.ResourceConfig;

@Path("/validate")
public class UrlValidatorService {

    private static final String INVALID_URL_MSG = "Invalid URL provided";

    @GET
    @Path("/url")
    @Produces(MediaType.APPLICATION_JSON)
    public Response validateUrl(@QueryParam("url") String urlStr) {
        try {
            if (urlStr == null || urlStr.isEmpty()) {
                return Response.status(Response.Status.BAD_REQUEST)
                             .entity("
{"error": "URL cannot be empty"}
")
                             .build();
            }

            // Validate the URL using a simple regex pattern
            if (!urlStr.matches("^(https?|ftp)://[^\" ]+$")) {
                return Response.status(Response.Status.BAD_REQUEST)
                             .entity("
{"error": "Invalid URL provided"}
")
                             .build();
            }

            // Here you can add additional URL validation logic if needed
            // For example, checking if the URL is reachable, etc.

            // If the URL is valid, return a success response
            return Response.ok("
{"message": "URL is valid"}
").build();

        } catch (Exception e) {
            // Handle any unexpected errors
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                         .entity("
{"error": "Internal server error: " + e.getMessage() + ""}
")
                         .build();
        }
    }

    // You can add more methods to handle different URL validation scenarios

}

// ResourceConfig class to register the URLValidatorService with Jersey
public class UrlValidatorApp extends ResourceConfig {
    public UrlValidatorApp() {
        packages("com.example.urlvalidator"); // Replace with your actual package name
    }
}