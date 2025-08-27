// 代码生成时间: 2025-08-28 04:49:25
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

@Path("/crawler")
public class WebPageCrawler {

    // 定义资源路径
    @GET
    @Path("/fetch")
    @Produces(MediaType.TEXT_HTML)
    public Response fetchWebPageContent(@QueryParam("url") String urlString) {
        HttpURLConnection connection = null;
        Scanner scanner = null;
        try {
            URL url = new URL(urlString);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);
            connection.connect();

            int responseCode = connection.getResponseCode();
            if (responseCode != HttpURLConnection.HTTP_OK) {
                return Response.status(Response.Status.NOT_FOUND).entity("Page not found").build();
            }

            scanner = new Scanner(url.openStream(), "UTF-8").useDelimiter("\A");
            String content = scanner.hasNext() ? scanner.next() : "";
            return Response.ok(content).build();
        } catch (IOException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error fetching web page").build();
        } finally {
            if (scanner != null) {
                scanner.close();
            }
            if (connection != null) {
                connection.disconnect();
            }
        }
    }
}
