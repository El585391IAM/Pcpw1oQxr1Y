// 代码生成时间: 2025-09-30 21:18:39
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.HashMap;
import java.util.Map;

// 定义物流跟踪系统资源
@Path("/logistics")
public class LogisticsTrackingSystem {

    // 模拟的物流跟踪信息
    private static final Map<String, String> trackingInfo = new HashMap<>();
    static {
        // 初始化一些模拟数据
        trackingInfo.put("1001", "Package picked up");
        trackingInfo.put("1002", "In transit");
        trackingInfo.put("1003", "Delivered");
    }

    // 获取物流跟踪信息
    @GET
    @Path("/track/{trackingId}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getTrackingInfo(@PathParam("trackingId") String trackingId) {
        try {
            // 检查跟踪ID是否存在
            if (!trackingInfo.containsKey(trackingId)) {
                // 如果跟踪ID不存在，返回错误信息
                return "Error: Tracking ID not found";
            }
            // 返回跟踪信息
            return trackingInfo.get(trackingId);
        } catch (Exception e) {
            // 处理异常情况
            return "Error: An unexpected error occurred";
        }
    }
}
