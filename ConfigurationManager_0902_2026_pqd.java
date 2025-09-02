// 代码生成时间: 2025-09-02 20:26:24
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.ServerProperties;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.Set;
import java.util.HashSet;
import java.util.Collections;

/**
 * 配置文件管理器，用于管理配置文件的RESTful服务。
 */
@ApplicationPath("/config")
public class ConfigurationManager extends ResourceConfig {

    /**
     * 构造函数，初始化资源和属性。
     */
    public ConfigurationManager() {
        // 注册资源类
        packages("com.example.config.resources");

        // 允许客户端跨域请求
        property(ServerProperties.RESPONSE_SET_STATUS_OVER_SEND_ERROR, true);
    }

    /**
     * 获取配置文件的资源类集合。
     *
     * @return 配置文件资源类的集合。
     */
    public Set<Class<?>> getClasses() {
        // 返回注册的资源类集合
        return Collections.unmodifiableSet(new HashSet<>(super.getClasses()));
    }

    /**
     * 主方法，用于启动Jersey服务器。
     *
     * @param args 命令行参数。
     */
    public static void main(String[] args) {
        try {
            // 实例化配置文件管理器
            ConfigurationManager configManager = new ConfigurationManager();

            // 启动Jersey服务器
            org.glassfish.jersey.server.ResourceConfig config = new org.glassfish.jersey.server.ResourceConfig(ConfigurationManager.class);
            org.glassfish.grizzly.http.server.HttpServer server = org.glassfish.grizzly.http.server.HttpServer.createSimpleServer("/", 8080);
            server.start();
            System.out.println("Configuration Manager is running...");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}