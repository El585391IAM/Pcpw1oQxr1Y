// 代码生成时间: 2025-09-22 05:59:42
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

// Assuming we have a simple resource class for demonstration purposes
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/example")
public class ExampleResource {
    
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getExample() {
        return "Hello, World!";
    }
}

// Unit test class
class UnitTestingExample {
    
    @Test
    void testGetExample() {
        // Create an instance of the resource class
        ExampleResource resource = new ExampleResource();

        // Call the method and assert the expected result
        String result = resource.getExample();
        assertEquals("Hello, World!", result, "The result should be 'Hello, World!'");
    }
}
