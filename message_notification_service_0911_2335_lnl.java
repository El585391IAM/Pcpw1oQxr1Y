// 代码生成时间: 2025-09-11 23:35:08
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

// MessageNotificationService is the main class handling message notifications.
@Path("/notifications")
public class MessageNotificationService {

    private Map<String, String> notifications = new HashMap<>();

    // Constructor
    public MessageNotificationService() {
        // Initialize the notifications map
        notifications = new HashMap<>();
    }

    // Endpoint to create a new notification
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response createNotification(String message) {
        if (message == null || message.isEmpty()) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Message cannot be empty").build();
        }
        
        String id = UUID.randomUUID().toString();
        notifications.put(id, message);
        return Response.status(Response.Status.CREATED).entity(
            "{"id": "