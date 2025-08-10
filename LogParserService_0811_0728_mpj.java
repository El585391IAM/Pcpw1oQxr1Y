// 代码生成时间: 2025-08-11 07:28:13
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

// 日志解析服务
@Path("/logparser")
public class LogParserService {

    // 解析日志文件并返回解析结果
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String parseLogFile() {
        List<String> logEntries = new ArrayList<>();
        try {
            // 读取日志文件
            BufferedReader reader = new BufferedReader(new FileReader("log.txt"));
            String line;
            while ((line = reader.readLine()) != null) {
                logEntries.add(line);
            }
            reader.close();

            // 返回解析结果的JSON字符串
            return toJson(logEntries);
        } catch (IOException e) {
            // 错误处理
            return "Error: " + e.getMessage();
        }
    }

    // 将日志条目列表转换为JSON字符串
    private String toJson(List<String> logEntries) {
        StringBuilder jsonBuilder = new StringBuilder();
        jsonBuilder.append("[
");
        for (int i = 0; i < logEntries.size(); i++) {
            jsonBuilder.append("  "" + logEntries.get(i) + """);
            if (i < logEntries.size() - 1) {
                jsonBuilder.append(",
");
            }
        }
        jsonBuilder.append(
        "]");
        return jsonBuilder.toString();
    }
}