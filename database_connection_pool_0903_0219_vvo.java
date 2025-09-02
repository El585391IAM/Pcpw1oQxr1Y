// 代码生成时间: 2025-09-03 02:19:48
import javax.sql.DataSource;
import org.apache.commons.dbcp2.BasicDataSource;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.ServerProperties;
import org.glassfish.jersey.server.mvc.freemarker.FreemarkerMvcFeature;
import org.glassfish.hk2.utilities.binding.AbstractBinder;
import java.util.logging.Logger;
import javax.inject.Singleton;

// 数据库连接池管理类
public class DatabaseConnectionPool {
    private static final Logger LOGGER = Logger.getLogger(DatabaseConnectionPool.class.getName());
    private BasicDataSource dataSource;

    // 构造方法初始化数据库连接池
    public DatabaseConnectionPool() {
        dataSource = new BasicDataSource();
        configureDataSource(dataSource);
    }

    // 配置数据源
    private void configureDataSource(BasicDataSource dataSource) {
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver"); // 设置数据库驱动
        dataSource.setUrl("jdbc:mysql://localhost:3306/your_database"); // 设置数据库URL
        dataSource.setUsername("your_username"); // 设置数据库用户名
        dataSource.setPassword("your_password"); // 设置数据库密码
        dataSource.setMaxTotal(20); // 设置最大连接数
        dataSource.setMaxIdle(10); // 设置最大空闲连接数
        dataSource.setMinIdle(5); // 设置最小空闲连接数
        dataSource.setInitialSize(5); // 设置初始连接数
        dataSource.setTestOnBorrow(true); // 设置是否在获取连接时检查有效性
        dataSource.setValidationQuery("SELECT 1"); // 设置验证SQL
        try {
            dataSource.setMinEvictableIdleTimeMillis(60000); // 设置空闲连接的最小存活时间
            dataSource.setTimeBetweenEvictionRunsMillis(30000); // 设置连接池中空闲连接的检测时间间隔
        } catch (Exception e) {
            LOGGER.severe("Error configuring data source: " + e.getMessage());
        }
    }

    // 获取数据源
    public DataSource getDataSource() {
        return dataSource;
    }
}

// Jersey配置类
public class JerseyConfig extends ResourceConfig {
    public JerseyConfig() {
        packages("com.example.resources"); // 扫描资源包
        register(FreemarkerMvcFeature.class); // 注册Freemarker MVC特性
        property(ServerProperties.WADL_FEATURE_DISABLE, true); // 禁用WADL
        register(new AbstractBinder() {
            @Override
            protected void configure() {
                bindAsContract(DatabaseConnectionPool.class).to(DataSource.class); // 注册数据库连接池
            }
        });
    }
}