// 代码生成时间: 2025-08-23 20:59:44
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import java.io.IOException;

@Path("/api")
public class JsonDataConverter {

    // Endpoint to convert JSON data to a String
    @POST
    @Path("/convert")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public Response convertJsonToString(String jsonData) {
        try {
            // Convert JSON data to a formatted string
            String formattedJson = convertToJsonFormat(jsonData);
            // Return the formatted JSON string
            return Response.ok(formattedJson).build();
        } catch (JsonProcessingException e) {
            // Handle JSON processing exception
            return Response
                .status(Response.Status.INTERNAL_SERVER_ERROR)
                .entity("Error processing JSON: " + e.getMessage())
                .build();
        } catch (Exception e) {
            // Handle any other exceptions
            return Response
                .status(Response.Status.INTERNAL_SERVER_ERROR)
                .entity("Unexpected error: " + e.getMessage())
                .build();
        }
    }

    /**
     * Converts a JSON string to a formatted JSON string.
     * @param jsonData The JSON string to be formatted.
     * @return A formatted JSON string.
     * @throws JsonProcessingException If there is an error processing the JSON.
     */
    private String convertToJsonFormat(String jsonData) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        // Parse the JSON string into an object
        Object jsonNode = objectMapper.readTree(jsonData);
        // Convert the object back to a formatted JSON string
        return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(jsonNode);
    }
}
