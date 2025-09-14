// 代码生成时间: 2025-09-14 15:53:20
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/api") // 定义API的基础路径
public class RestfulApiExample {

    // 定义一个GET请求的方法，返回欢迎信息
    @GET
    @Path("/welcome")
    @Produces(MediaType.TEXT_PLAIN) // 指定返回数据的类型
    public Response welcomeMessage() {
        return Response.ok("Welcome to the RESTful API!").build();
    }

    // 定义一个GET请求的方法，返回JSON格式的用户信息
    @GET
    @Path("/user")
    @Produces(MediaType.APPLICATION_JSON) // 指定返回数据的类型为JSON
    public Response getUserInfo() {
        // 模拟用户数据
        String userJson = "{"name": "John Doe", "age": 30}";
        return Response.ok(userJson).build();
    }

    // 定义一个GET请求的方法，返回具体的错误信息
    @GET
    @Path("/error")
    @Produces(MediaType.TEXT_PLAIN) // 指定返回数据的类型
    public Response error() {
        // 模拟一个错误条件
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Internal Server Error").build();
    }

    // 其他API端点可以在这里继续添加

}
