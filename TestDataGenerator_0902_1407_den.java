// 代码生成时间: 2025-09-02 14:07:55
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.Random;

@Path("/test-data")
public class TestDataGenerator {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String generateTestData() {
        try {
            // Generate a random number between 1 and 100
            Random random = new Random();
            int randomNumber = random.nextInt(100) + 1;

            // Create a JSON object as the response
            String response = String.format("{"randomNumber": %d}", randomNumber);

            return response;
        } catch (Exception e) {
            // Handle any exceptions that occur during the generation of test data
            return String.format("Error generating test data: %s", e.getMessage());
        }
    }

    /**
     * Main method to start the Jersey application.
     * @param args Command line arguments
     */
    public static void main(String[] args) {
        // Start the Jersey application
        // This can be done using a framework like Grizzly or embedded Jetty
    }
}
