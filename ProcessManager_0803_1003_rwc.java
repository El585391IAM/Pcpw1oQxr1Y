// 代码生成时间: 2025-08-03 10:03:36
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;
import java.util.List;
import java.util.Map;

/**
 * 进程管理器 REST 服务
 * 提供关于当前 Java 进程的基本信息
 */
@Path("/process-manager")
public class ProcessManager {

    /**
     * 获取当前 Java 进程信息
     *
     * @return 当前 Java 进程的信息
     */
    @GET
    @Path("/info")
    @Produces(MediaType.APPLICATION_JSON)
    public Map<String, Object> getProcessInfo() {
        RuntimeMXBean runtimeMXBean = ManagementFactory.getRuntimeMXBean();
        Map<String, Object> processInfo = runtimeMXBean.getSystemProperties();

        // 添加进程启动时间
        processInfo.put("startTime", runtimeMXBean.getStartTime());

        return processInfo;
    }

    /**
     * 重启当前 Java 进程
     *
     * @return 重启结果
     */
    @GET
    @Path("/restart")
    @Produces(MediaType.TEXT_PLAIN)
    public String restartProcess() {
        try {
            // 模拟重启进程
            // 在实际应用中，这里可以调用系统命令或者其他机制来重启进程
            System.out.println("Process is restarted.");
            return "Process restarted successfully.";
        } catch (Exception e) {
            // 错误处理
            return "Failed to restart process: " + e.getMessage();
        }
    }

    /**
     * 获取当前 Java 进程的输入参数
     *
     * @return 当前 Java 进程的输入参数
     */
    @GET
    @Path("/arguments")
    @Produces(MediaType.APPLICATION_JSON)
    public List<String> getProcessArguments() {
        RuntimeMXBean runtimeMXBean = ManagementFactory.getRuntimeMXBean();
        return runtimeMXBean.getInputArguments();
    }
}
