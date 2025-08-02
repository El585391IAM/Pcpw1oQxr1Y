// 代码生成时间: 2025-08-02 17:34:40
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.HashMap;
import java.util.Map;

// UserAuthenticationService类负责处理用户登录验证
@Path("/auth")
public class UserAuthenticationService {

    // 模拟的用户存储，实际应用中应使用数据库
    private static final Map<String, String> userDatabase = new HashMap<>();
    static {
        // 预设用户
        userDatabase.put("user1", "password1");
        userDatabase.put("user2", "password2");
    }

    // 登录API接口
    @POST
    @Path("/login")
    @Produces(MediaType.APPLICATION_JSON)
    public Response login(String credentials) {
        try {
            // 将credentials字符串以":"分割为用户名和密码
            String[] parts = credentials.split(":");
            if (parts.length != 2) {
                return Response.status(Response.Status.BAD_REQUEST).entity("Invalid credentials format").build();
            }
            String username = parts[0];
            String password = parts[1];

            // 验证用户名和密码
            if (userDatabase.containsKey(username) && userDatabase.get(username).equals(password)) {
                return Response.ok("Login successful").build();
            } else {
                return Response.status(Response.Status.UNAUTHORIZED).entity("Invalid username or password").build();
            }
        } catch (Exception e) {
            // 错误处理
            return Response.serverError().entity("Error during login: " + e.getMessage()).build();
        }
    }

    // 注册API接口
    @POST
    @Path("/register")
    @Produces(MediaType.APPLICATION_JSON)
    public Response register(String credentials) {
        try {
            // 将credentials字符串以":"分割为用户名和密码
            String[] parts = credentials.split(":");
            if (parts.length != 2) {
                return Response.status(Response.Status.BAD_REQUEST).entity("Invalid credentials format").build();
            }
            String username = parts[0];
            String password = parts[1];

            // 检查用户名是否已存在
            if (userDatabase.containsKey(username)) {
                return Response.status(Response.Status.CONFLICT).entity("Username already exists").build();
            }

            // 注册新用户
            userDatabase.put(username, password);
            return Response.ok("Registration successful").build();
        } catch (Exception e) {
            // 错误处理
            return Response.serverError().entity("Error during registration: " + e.getMessage()).build();
        }
    }
}
