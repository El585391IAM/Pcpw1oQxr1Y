// 代码生成时间: 2025-09-23 12:05:57
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.HashMap;
import java.util.Map;

/**
 * A JAX-RS service class that handles theme switching.
 * It provides a RESTful endpoint to switch between themes.
 */
@Path("/theme")
public class ThemeSwitcherService {

    // A map to store the current theme for each user session
    private Map<String, String> themeMap = new HashMap<>();

    /**
     * GET method to retrieve the current theme for a user session.
     * @param sessionId The session ID of the user.
     * @return A response with the current theme or an error message.
     */
    @GET
    @Path("/current")
    @Produces(MediaType.TEXT_PLAIN)
    public Response getCurrentTheme(String sessionId) {
        if (sessionId == null || sessionId.isEmpty()) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Session ID is required").build();
        }
        String currentTheme = themeMap.get(sessionId);
        if (currentTheme == null) {
            return Response.status(Response.Status.NOT_FOUND).entity("Theme not found for session").build();
        }
        return Response.ok(currentTheme).build();
    }

    /**
     * POST method to switch the theme for a user session.
     * @param sessionId The session ID of the user.
     * @param theme The new theme to be set.
     * @return A response indicating the success or failure of the operation.
     */
    @POST
    @Path("/switch")
    @Produces(MediaType.TEXT_PLAIN)
    public Response switchTheme(String sessionId, String theme) {
        if (sessionId == null || sessionId.isEmpty() || theme == null || theme.isEmpty()) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Session ID and theme are required\).build();
        }
        themeMap.put(sessionId, theme);
        return Response.ok("Theme switched to: " + theme).build();
    }
}
