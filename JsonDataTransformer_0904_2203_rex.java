// 代码生成时间: 2025-09-04 22:03:58
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import java.io.IOException;

@Path("/transform")
public class JsonDataTransformer {

    // POST endpoint to receive JSON data and convert it
    @POST
    @Path("/json")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response convertJson(String jsonData) {
        try {
            // Convert the received JSON data
            String convertedJson = convertJsonData(jsonData);
            return Response.ok().entity(convertedJson).build();
        } catch (JsonProcessingException | IOException e) {
            // Handle exceptions and return an error message
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Error processing JSON data: " + e.getMessage()).build();
        }
    }

    // GET endpoint to display a simple message
    @GET
    @Path("/message")
    @Produces(MediaType.TEXT_PLAIN)
    public Response getMessage() {
        return Response.ok("Welcome to the JSON Data Transformer!").build();
    }

    /*
     * Converts the input JSON data to a new JSON format
     *
     * @param jsonData The JSON data to convert
     * @return The converted JSON data as a String
     * @throws JsonProcessingException If there is an error processing the JSON data
     * @throws IOException If there is an error reading the JSON data
     */
    private String convertJsonData(String jsonData) throws JsonProcessingException, IOException {
        // Use Jackson ObjectMapper to parse and convert JSON data
        ObjectMapper mapper = new ObjectMapper();
        Object parsedJson = mapper.readValue(jsonData, Object.class);
        return mapper.writeValueAsString(parsedJson);
    }
}
