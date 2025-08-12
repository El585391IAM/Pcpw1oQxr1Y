// 代码生成时间: 2025-08-13 05:11:26
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.Response;
import java.util.Set;

@Path("/math")
public class MathToolService extends Application {

    // 计算两个数的加法
    @GET
    @Path("/add")
    public Response add(@QueryParam("a") Double a, @QueryParam("b") Double b) {
        try {
            double result = a + b;
            return Response.ok().entity("Result: " + result).build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Invalid input").build();
        }
    }

    // 计算两个数的减法
    @GET
    @Path("/subtract")
    public Response subtract(@QueryParam("a") Double a, @QueryParam("b\) Double b) {
        try {
            double result = a - b;
            return Response.ok().entity("Result: " + result).build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Invalid input").build();
        }
    }

    // 计算两个数的乘法
    @GET
    @Path("/multiply")
    public Response multiply(@QueryParam("a") Double a, @QueryParam("b") Double b) {
        try {
            double result = a * b;
            return Response.ok().entity("Result: " + result).build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Invalid input").build();
        }
    }

    // 计算两个数的除法
    @GET
    @Path("/divide")
    public Response divide(@QueryParam("a") Double a, @QueryParam("b") Double b) {
        try {
            if (b == 0) {
                throw new ArithmeticException("Division by zero");
            }
            double result = a / b;
            return Response.ok().entity("Result: " + result).build();
        } catch (ArithmeticException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Invalid input").build();
        }
    }

    // 重写getClasses方法，返回资源类集合
    @Override
    public Set<Class<?>> getClasses() {
        return Set.of(MathToolService.class);
    }
}
