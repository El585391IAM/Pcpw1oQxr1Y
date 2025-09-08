// 代码生成时间: 2025-09-09 07:53:42
 * This class provides functionality to convert JSON data into different formats.
 * It uses the JERSEY framework for RESTful service implementation.
 * 
 * @author Your Name
 * @version 1.0
 */

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.json.JSONObject;
import java.util.Map;
import java.util.HashMap;

@Path("/converter")
public class JsonDataConverter {

    // The entry point for the REST service
    @GET
    @Path("/convert")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response convertJsonData(String jsonData) {
        try {
            // Parse the input JSON data
            JSONObject jsonInput = new JSONObject(jsonData);

            // Convert the JSON object to a Map
            Map<String, Object> convertedData = convertToJsonMap(jsonInput);

            // Return the converted data as a JSON object
            return Response.ok(new JSONObject(convertedData).toString()).build();
        } catch (Exception e) {
            // Handle any errors during the conversion process
            return Response
                    .status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Error converting JSON data: " + e.getMessage())
                    .build();
        }
    }

    // Helper method to convert a JSON object to a Map
    private Map<String, Object> convertToJsonMap(JSONObject jsonObject) throws Exception {
        Map<String, Object> map = new HashMap<>();
        for (String key : jsonObject.keySet()) {
            Object value = jsonObject.get(key);
            if (value instanceof JSONObject) {
                map.put(key, convertToJsonMap((JSONObject) value));
            } else if (value instanceof JSONArray) {
                // Handle JSONArray conversion if necessary
                // For simplicity, let's just convert it to a string representation
                map.put(key, value.toString());
            } else {
                map.put(key, value);
            }
        }
        return map;
    }
}
