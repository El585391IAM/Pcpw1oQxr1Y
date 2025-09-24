// 代码生成时间: 2025-09-24 13:35:24
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * NetworkStatusChecker is a RESTful web service that checks the network connection status
 * to a specified URL.
 */
@Path("/check")
public class NetworkStatusChecker {

    /**
     * Checks the network connection status for a given URL.
     *
     * @param url The URL to check the network connection status for.
     * @return A response indicating the network connection status.
     */
    @GET
    @Path("/status")
    @Produces(MediaType.TEXT_PLAIN)
    public Response checkNetworkStatus(@QueryParam("url") String url) {
        if (url == null || url.isEmpty()) {
            // Return a bad request response if the URL is not provided
            return Response.status(Response.Status.BAD_REQUEST).entity("URL is required").build();
        }

        try {
            URL urlObj = new URL(url);
            HttpURLConnection conn = (HttpURLConnection) urlObj.openConnection();
            conn.setRequestMethod("HEAD"); // We only need to check the connection, not the content
            conn.connect();

            int responseCode = conn.getResponseCode();
            conn.disconnect();

            if (responseCode == HttpURLConnection.HTTP_OK) {
                // Return a success response if the network connection is established
                return Response.status(Response.Status.OK).entity("Network connection is established").build();
            } else {
                // Return an error response if the network connection is not established
                return Response.status(Response.Status.SERVICE_UNAVAILABLE)
                        .entity("Network connection failed with status code: " + responseCode).build();
            }
        } catch (Exception e) {
            // Return an internal server error response if an exception occurs
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error checking network connection: " + e.getMessage()).build();
        }
    }
}
