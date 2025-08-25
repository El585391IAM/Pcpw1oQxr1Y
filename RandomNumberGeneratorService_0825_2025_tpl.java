// 代码生成时间: 2025-08-25 20:25:27
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.Random;

@Path("/random")
public class RandomNumberGeneratorService {

    // 定义最大随机数和最小随机数
    private final int MAX_RANDOM_NUMBER = 100;
    private final int MIN_RANDOM_NUMBER = 1;

    // 用于生成随机数的Random对象
    private final Random random = new Random();

    /**<ol>
     * 获取一个随机数
     *
     * @return 返回一个随机的整数
     */
    @GET
    @Path("/number")
    @Produces(MediaType.TEXT_PLAIN)
    public int getRandomNumber() {
        try {
            // 生成随机数
            return random.nextInt(MAX_RANDOM_NUMBER - MIN_RANDOM_NUMBER + 1) + MIN_RANDOM_NUMBER;
        } catch (Exception e) {
            // 错误处理
            throw new RuntimeException("Error generating random number", e);
        }
    }

    /**<ol>
     * 获取指定范围内的随机数
     *
     * @param min 最小值
     * @param max 最大值
     * @return 返回指定范围内的随机整数
     */
    @GET
    @Path("/number/{min}/{max}")
    @Produces(MediaType.TEXT_PLAIN)
    public int getRandomNumberInRange(@javax.ws.rs.PathParam("min") int min, @javax.ws.rs.PathParam("max\) int max) {
        try {
            // 检查输入范围是否有效
            if (min >= max) {
                throw new IllegalArgumentException("Invalid range: min must be less than max");
            }
            // 生成指定范围内的随机数
            return random.nextInt(max - min + 1) + min;
        } catch (IllegalArgumentException e) {
            throw new RuntimeException(e.getMessage(), e);
        } catch (Exception e) {
            // 错误处理
            throw new RuntimeException("Error generating random number within range", e);
        }
    }
}
