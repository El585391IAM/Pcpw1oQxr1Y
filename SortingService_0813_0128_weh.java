// 代码生成时间: 2025-08-13 01:28:59
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

// 定义一个REST服务来返回排序后的数字列表
@Path("/sorting")
public class SortingService {

    // 使用JERSEY的GET注解来定义一个GET请求的方法
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Integer> sortNumbers(@QueryParam("numbers") String numbers) {
        // 错误处理，检查输入是否为空
        if (numbers == null || numbers.trim().isEmpty()) {
            throw new IllegalArgumentException("Input numbers cannot be null or empty.");
        }

        try {
            // 将输入的字符串分割成数字数组
            String[] numberStrings = numbers.split(",");
            Integer[] numbersArray = Arrays.stream(numberStrings)
                    .map(Integer::valueOf)
                    .toArray(Integer[]::new);

            // 使用Collections.sort方法对数组进行排序
            Arrays.sort(numbersArray);

            // 返回排序后的数字列表
            return Arrays.asList(numbersArray);
        } catch (Exception e) {
            // 错误处理，捕获并记录异常
            throw new RuntimeException("Error sorting numbers: " + e.getMessage(), e);
        }
    }
}
