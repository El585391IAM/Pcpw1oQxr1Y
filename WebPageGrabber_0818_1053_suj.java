// 代码生成时间: 2025-08-18 10:53:47
import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.client.ClientProperties;
import org.glassfish.jersey.media.sse.EventInput;
import org.glassfish.jersey.media.sse.InboundEvent;
import org.glassfish.jersey.media.sse.SseFeature;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class WebPageGrabber {

    // 构造方法
    public WebPageGrabber() {
        // 初始化Jersey客户端
        ClientConfig clientConfig = new ClientConfig();
        clientConfig.register(SseFeature.class);
        this.client = ClientBuilder.newClient(clientConfig);
    }

    // 获取网页内容
    public String getWebPageContent(String url) throws IOException {
        // 检查URL是否为空
        if (url == null || url.isEmpty()) {
            throw new IllegalArgumentException("URL cannot be null or empty");
        }

        // 使用URLConnection获取网页内容
        URL urlObj = new URL(url);
        HttpURLConnection httpURLConnection = (HttpURLConnection) urlObj.openConnection();
        httpURLConnection.setRequestMethod("GET");

        // 检查响应状态码
        int statusCode = httpURLConnection.getResponseCode();
        if (statusCode != HttpURLConnection.HTTP_OK) {
            throw new IOException("Failed to retrieve web page content. HTTP status code: " + statusCode);
        }

        // 读取响应流
        try (InputStream inputStream = httpURLConnection.getInputStream();
             java.util.Scanner scanner = new java.util.Scanner(inputStream, StandardCharsets.UTF_8.name())) {

            // 将输入流转换为字符串
            scanner.useDelimiter("\A");
            return scanner.hasNext() ? scanner.next() : "";
        }
    }

    // 主方法，用于测试和演示
    public static void main(String[] args) {
        WebPageGrabber grabber = new WebPageGrabber();
        try {
            String url = "http://example.com";
            String content = grabber.getWebPageContent(url);
            System.out.println("Web page content: 
" + content);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 私有成员变量，Jersey客户端
    private Client client;
}
