// 代码生成时间: 2025-08-19 16:20:09
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

@Path("/api")
public class UnitTestFrameworkApp {

    @GET
    @Path("/test")
    @Produces(MediaType.TEXT_PLAIN)
    public String getTest() {
        return "Hello, World!";
    }

    public static class TestSuite extends JerseyTest {

        @Override
        protected ResourceConfig configure() {
            return new ResourceConfig(UnitTestFrameworkApp.class);
        }

        @Test
        public void testGetTest() {
            String response = target("/api/test").request().get(String.class);
            assertEquals("Hello, World!", response);
        }
    }
}
