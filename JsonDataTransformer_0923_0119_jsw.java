// 代码生成时间: 2025-09-23 01:19:19
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.mvc.freemarker.FreemarkerMvcFeature;
import org.glassfish.jersey.media.multipart.MultiPartFeature;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.json.JSONObject;

/**
 * Jersey RESTful Application to transform JSON data.
 */
@ApplicationPath("/api")
public class JsonDataTransformer extends ResourceConfig {

    /**
     * Default constructor.
     */
    public JsonDataTransformer() {
        register(MultiPartFeature.class);
        register(FreemarkerMvcFeature.class);
        packages("com.example.jsontransformer.resources");
    }

    @Path("/jsontransformer")
    public static class JsonTransformerResource {

        @GET
        @Produces(MediaType.TEXT_PLAIN)
        public String sayHello() {
            return "Hello, this is a JSON data transformer service!";
        }

        /**
         * Transform JSON data.
         *
         * @param jsonData JSON data string to be transformed.
         * @return Transformed JSON data as a string.
         */
        @POST
        @Path("/transform")
        @Produces(MediaType.APPLICATION_JSON)
        public Response transformJson(String jsonData) {
            try {
                JSONObject jsonObject = new JSONObject(jsonData);
                // Perform transformation logic here.
                // For simplicity, we just return the input JSON data.
                String transformedData = jsonObject.toString();
                return Response.ok(transformedData, MediaType.APPLICATION_JSON).build();
            } catch (Exception e) {
                // Handle errors and return appropriate response.
                return Response.status(Response.Status.BAD_REQUEST).entity("Invalid JSON data provided.").build();
            }
        }
    }
}
