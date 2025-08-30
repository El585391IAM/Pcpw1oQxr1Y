// 代码生成时间: 2025-08-30 12:10:44
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

@Path("/configuration")
public class ConfigurationManager {

    private static final String CONFIG_FILE_PATH = "config.properties";

    @GET
    @Path("/{key}")
    @Produces(MediaType.TEXT_PLAIN)
    public Response getConfigValue(@PathParam("key") String key) {
        try {
            Properties props = new Properties();
            props.load(Files.newInputStream(Paths.get(CONFIG_FILE_PATH)));
            return Response.ok(props.getProperty(key)).build();
        } catch (IOException e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error reading configuration file").build();
        }
    }

    @PUT
    @Path("/{key}")
    @Produces(MediaType.TEXT_PLAIN)
    public Response updateConfigValue(@PathParam("key") String key, String value) {
        try {
            Properties props = new Properties();
            try (InputStream input = Files.newInputStream(Paths.get(CONFIG_FILE_PATH));
                 OutputStream output = Files.newOutputStream(Paths.get(CONFIG_FILE_PATH),
                         java.nio.file.StandardOpenOption.WRITE,
                         java.nio.file.StandardOpenOption.TRUNCATE_EXISTING)) {
                props.load(input);
                props.setProperty(key, value);
                props.store(output, null);
            }
            return Response.ok("Configuration updated successfully").build();
        } catch (IOException e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error updating configuration file").build();
        }
    }
}
