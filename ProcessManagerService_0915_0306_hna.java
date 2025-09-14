// 代码生成时间: 2025-09-15 03:06:44
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@Path("/process-manager")
public class ProcessManagerService {

    // 获取当前运行的进程列表
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listProcesses() {
        try {
            // 执行命令获取进程列表
            String command = "ps aux";
            Process process = Runtime.getRuntime().exec(command);
            BufferedReader reader = new BufferedReader(
                new InputStreamReader(process.getInputStream()));

            // 读取进程信息
            String line;
            List<String> processes = new ArrayList<>();
            while ((line = reader.readLine()) != null) {
                processes.add(line);
            }

            // 返回进程列表
            return Response.ok(processes).build();
        } catch (Exception e) {
            // 错误处理
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                .entity("Error retrieving process list: " + e.getMessage()).build();
        }
    }

    // 启动一个新的进程
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response startProcess(String command) {
        try {
            // 执行命令启动进程
            Process process = Runtime.getRuntime().exec(command);

            // 返回成功消息
            return Response.ok("Process started: " + command).build();
        } catch (Exception e) {
            // 错误处理
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                .entity("Error starting process: " + e.getMessage()).build();
        }
    }
}
