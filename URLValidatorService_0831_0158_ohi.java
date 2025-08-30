// 代码生成时间: 2025-08-31 01:58:35
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.HttpURLConnection;
import java.net.URL;

// URL链接有效性验证服务
@Path("/urlValidator")
public class URLValidatorService {

    // 验证URL链接有效性的GET方法
    @GET
    public Response validateURL(@QueryParam("url") String urlString) {
        // 响应状态码
        int status;
        try {
            // 创建URL对象
            URL url = new URL(urlString);
            // 打开连接
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            // 设置连接超时时间为5秒
            connection.setConnectTimeout(5000);
            // 设置读取超时时间为5秒
            connection.setReadTimeout(5000);
            // 获取响应状态码
            status = connection.getResponseCode();
            // 如果状态码为200，则返回有效的响应
            if (status == HttpURLConnection.HTTP_OK) {
                return Response.ok().entity("URL is valid").build();
            } else {
                // 否则返回无效的响应和状态码
                return Response.status(status).entity("URL is invalid").build();
            }
        } catch (Exception e) {
            // 捕获异常并返回错误响应
            return Response.status(Response.Status.BAD_REQUEST).entity("Invalid URL provided").build();
        }
    }
}
