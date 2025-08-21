// 代码生成时间: 2025-08-21 12:18:10
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.Map;

/**
 * JSON数据格式转换器服务，用于处理JSON数据的转换请求。
 */
@Path("/jsonConverter")
public class JsonDataConverter {

    /**
     * POST请求处理方法，接受JSON数据并返回转换后的JSON数据。
     *
     * @param jsonData 传入的JSON字符串
     * @return 转换后的JSON数据
     */
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response convertJson(String jsonData) {
        try {
            // 使用Jackson库将JSON字符串转换为Map对象
            ObjectMapper mapper = new ObjectMapper();
            Map<String, Object> jsonMap = mapper.readValue(jsonData, Map.class);

            // 将Map对象重新转换回JSON字符串
            String convertedJson = mapper.writeValueAsString(jsonMap);

            // 返回转换后的JSON数据
            return Response.ok(convertedJson).build();
        } catch (IOException e) {
            // 错误处理：如果转换过程中发生异常，返回错误信息
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Error converting JSON: " + e.getMessage())
                    .build();
        }
    }
}
