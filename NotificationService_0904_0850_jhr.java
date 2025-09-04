// 代码生成时间: 2025-09-04 08:50:00
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.mvc.freemarker.FreemarkerMvcFeature;

@Path("/notification")
public class NotificationService {

    // 发送通知的方法
    @GET
    @Path("/send")
    @Produces(MediaType.TEXT_PLAIN)
    public Response sendNotification() {
        try {
            // 模拟通知发送逻辑
            System.out.println("Sending notification...");
            // 假设通知发送成功
            String message = "Notification sent successfully.";
            return Response.ok(message).build();
        } catch (Exception e) {
            // 错误处理
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error sending notification: " + e.getMessage()).build();
        }
    }

    // 此方法用于Jersey框架的资源注册
    public static class NotificationResourceConfig extends ResourceConfig {
        public NotificationResourceConfig() {
            packages("com.example.notification"); // 假设NotificationService位于com.example.notification包下
            register(FreemarkerMvcFeature.class); // 可选，如果需要使用Freemarker模板
        }
    }
}
