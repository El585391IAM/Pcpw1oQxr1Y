// 代码生成时间: 2025-09-24 00:36:29
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.Test;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import java.util.Random;

// 测试数据生成器 RESTful 服务
@Path("/generator")
public class TestDataGenerator extends JerseyTest {

    // 定义测试数据生成的 API
    @GET
    @Path("/data")
    @Produces(MediaType.TEXT_PLAIN)
    public String generateTestData() {
        try {
            // 生成随机测试数据
            String testData = "Random data: " + new Random().nextInt(100);
            return testData;
        } catch (Exception e) {
            // 错误处理
            return "Error generating test data: " + e.getMessage();
        }
    }

    // 配置 JAX-RS 应用
    @Override
    protected Application configure() {
        return new ResourceConfig(TestDataGenerator.class);
    }

    // 测试用例
    @Test
    public void testDataGeneration() {
        String response = target("generator/data").request().get(String.class);
        assertNotNull("Response should not be null", response);
    }
}
