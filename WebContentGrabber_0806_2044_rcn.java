// 代码生成时间: 2025-08-06 20:44:13
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.server.ResourceConfig;

@Path("/webcontent")
public class WebContentGrabber {

    @GET
    @Produces(MediaType.TEXT_HTML)
    public Response fetchWebContent() {
        try {
            URL url = new URL("http://example.com"); // Replace with the desired URL
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setConnectTimeout(5000);
            conn.setReadTimeout(5000);
            int responseCode = conn.getResponseCode();

            if (responseCode != HttpURLConnection.HTTP_OK) {
                return Response.status(responseCode).entity("Failed to fetch content").build();
            }

            Scanner scanner = new Scanner(url.openStream(), StandardCharsets.UTF_8.name());
            String content = scanner.useDelimiter(