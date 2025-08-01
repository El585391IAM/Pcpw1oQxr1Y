// 代码生成时间: 2025-08-02 06:47:10
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.IOException;
import java.util.Map;
import java.util.HashMap;

@Path("/config")
public class ConfigurationManager {

    // Method to load configuration from a file
    @GET
    @Path("/load")
    @Produces(MediaType.APPLICATION_JSON)
    public Map<String, String> loadConfiguration(@QueryParam("file") String configFile) {
        Map<String, String> config = new HashMap<>();
        try {
            // Check if the configuration file exists
            if (Files.exists(Paths.get(configFile))) {
                // Read the configuration file line by line
                Files.lines(Paths.get(configFile))
                        .forEach(line -> {
                            try {
                                // Split each line into key-value pairs
                                String[] keyValue = line.split("\=", 2);
                                if (keyValue.length == 2) {
                                    config.put(keyValue[0].trim(), keyValue[1].trim());
                                }
                            } catch (Exception e) {
                                // Handle any exceptions that occur during line processing
                                System.out.println("Error processing line: " + line);
                            }
                        });
            } else {
                // Return an error if the configuration file does not exist
                throw new WebApplicationException(
                        Response.status(Response.Status.NOT_FOUND).entity("Configuration file not found.").build());
            }
        } catch (IOException e) {
            // Handle I/O errors
            throw new WebApplicationException(
                    Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error reading configuration file.").build());
        }
        return config;
    }

    // Method to save configuration to a file
    @POST
    @Path("/save")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public String saveConfiguration(Map<String, String> config, @QueryParam("file") String configFile) {
        try {
            // Write the configuration to the file
            Files.write(Paths.get(configFile), config.entrySet().stream()
                    .map(e -> e.getKey() + "=" + e.getValue())
                    .collect(Collectors.toList()));
            return "Configuration saved successfully.";
        } catch (IOException e) {
            // Handle I/O errors
            throw new WebApplicationException(
                    Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error saving configuration file.").build());
        }
    }
}
