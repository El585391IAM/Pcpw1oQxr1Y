// 代码生成时间: 2025-08-29 00:10:15
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URL;
import java.net.MalformedURLException;

@Path("/url-validator")
public class UrlValidatorService {

    /**
     * Validates the URL provided as a query parameter.
     *
     * @param url The URL to be validated.
     * @return A response indicating whether the URL is valid.
     */
    @GET
    public Response validateUrl(@QueryParam("url") String url) {
        try {
            // Create a URL object to validate
            URL urlObj = new URL(url);
            // Open and close the URL stream to further validate it
            urlObj.openStream().close();
            // If no exception is thrown, the URL is valid
            return Response.ok()
                    .type(MediaType.APPLICATION_JSON)
                    .entity("{"valid":true, "reason":"URL is valid."}")
                    .build();
        } catch (MalformedURLException e) {
            // Handle incorrect URL format
            return Response.status(Response.Status.BAD_REQUEST)
                    .type(MediaType.APPLICATION_JSON)
                    .entity("{"valid":false, "reason":"Malformed URL."}")
                    .build();
        } catch (Exception e) {
            // Handle other exceptions that may occur
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .type(MediaType.APPLICATION_JSON)
                    .entity("{"valid":false, "reason":"An error occurred while validating the URL."}")
                    .build();
        }
    }
}
