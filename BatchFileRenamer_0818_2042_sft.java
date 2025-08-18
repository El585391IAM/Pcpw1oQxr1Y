// 代码生成时间: 2025-08-18 20:42:20
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.ServerProperties;
import org.glassfish.jersey.jackson.JacksonFeature;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

// 主类，包含REST资源
@Path("/rename")
public class BatchFileRenamer {

    // 重命名文件的方法
    @POST
    @Path("/files")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public List<String> renameFiles(List<FileDetails> files) {
        List<String> results = new ArrayList<>();
        for (FileDetails file : files) {
            try {
                // 创建源文件路径和目标文件路径
                Path sourcePath = Paths.get(file.getSourcePath());
                Path targetPath = Paths.get(file.getTargetPath());

                // 检查源文件是否存在
                if (Files.exists(sourcePath)) {
                    // 重命名文件
                    Files.move(sourcePath, targetPath);
                    results.add("Renamed: " + file.getSourcePath() + " to " + file.getTargetPath());
                } else {
                    results.add("Error: File not found - " + file.getSourcePath());
                }
            } catch (Exception e) {
                // 添加错误处理
                results.add("Error: Unable to rename file - " + file.getSourcePath() + ". Error: " + e.getMessage());
            }
        }
        return results;
    }
}

// 辅助类，用于存储文件重命名的详细信息
class FileDetails {
    private String sourcePath;
    private String targetPath;

    public FileDetails(String sourcePath, String targetPath) {
        this.sourcePath = sourcePath;
        this.targetPath = targetPath;
    }

    public String getSourcePath() {
        return sourcePath;
    }

    public void setSourcePath(String sourcePath) {
        this.sourcePath = sourcePath;
    }

    public String getTargetPath() {
        return targetPath;
    }

    public void setTargetPath(String targetPath) {
        this.targetPath = targetPath;
    }
}

// Jersey配置类
public class BatchFileRenamerApplication extends Application {
    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> classes = new HashSet<>();
        classes.add(BatchFileRenamer.class);
        return classes;
    }

    @Override
    public Set<Object> getSingletons() {
        Set<Object> singletons = new HashSet<>();
        singletons.add(new JacksonFeature());
        return singletons;
    }
}

// 启动类
public class BatchFileRenamerServer {
    public static void main(String[] args) {
        // 启动Jersey服务器
        new BatchFileRenamerServer().start();
    }

    public void start() {
        try {
            // 创建Jersey配置
            ResourceConfig config = new ResourceConfig(BatchFileRenamerApplication.class);
            // 配置CORS
            config.property(ServerProperties.RESPONSE_SET_STATUS_OVER_SEND_ERROR, true);
            new ResourceConfig().register(new BatchFileRenamerApplication());
            // 启动服务器
            new org.glassfish.jersey.server.jaxrs Grün("http://localhost:8080/", true, config);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}