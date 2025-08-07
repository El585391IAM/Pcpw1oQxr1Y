// 代码生成时间: 2025-08-08 04:14:08
 * @author [Your Name]
 * @since 2023-04-01
 */
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
import java.io.UncheckedIOException;

@Path("/config")
public class ConfigFileManager {

    private static final String CONFIG_DIR = "config/"; // Directory where configuration files are stored

    /**
     * Retrieves a configuration file's content
     *
     * @param configName The name of the configuration file
     * @return The content of the configuration file
     */
    @GET
    @Path("/{configName}")
    @Produces(MediaType.TEXT_PLAIN)
    public String getConfigFile(@PathParam("configName") String configName) {
        try {
            String configContent = new String(Files.readAllBytes(Paths.get(CONFIG_DIR + configName)));
            return configContent;
        } catch (IOException e) {
            throw new UncheckedIOException("Error reading configuration file", e);
        }
    }

    /**
     * Updates a configuration file's content
     *
     * @param configName The name of the configuration file to update
     * @param newContent The new content of the configuration file
     * @return A response indicating success or failure
     */
    @PUT
    @Path("/{configName}")
    @Produces(MediaType.TEXT_PLAIN)
    public Response updateConfigFile(@PathParam("configName") String configName, String newContent) {
        try {
            Files.write(Paths.get(CONFIG_DIR + configName), newContent.getBytes());
            return Response.ok("Configuration file updated successfully.").build();
        } catch (IOException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Error updating configuration file").build();
        }
    }
}