// 代码生成时间: 2025-08-28 15:24:51
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

// HashValueCalculator 是一个 RESTful 服务，用于计算哈希值
@Path("/hash")
public class HashValueCalculator {

    // 计算字符串的哈希值并返回 Base64 编码的哈希字符串
    @GET
    public Response calculateHashValue(@QueryParam("input") String input, @QueryParam("algorithm") String algorithm) {
        try {
            // 检查输入是否为空
            if (input == null || input.isEmpty()) {
                return Response.status(Response.Status.BAD_REQUEST).entity("Input string is required.").build();
            }

            // 获取指定的哈希算法实例
            MessageDigest md = MessageDigest.getInstance(algorithm);

            // 更新哈希计算的数据
            md.update(input.getBytes(StandardCharsets.UTF_8));

            // 计算哈希值
            byte[] digest = md.digest();

            // 将哈希值转换为 Base64 编码的字符串
            String encodedHash = Base64.getEncoder().encodeToString(digest);

            // 返回哈希值
            return Response.ok(encodedHash).build();
        } catch (NoSuchAlgorithmException e) {
            // 返回错误信息，如果算法不存在
            return Response.status(Response.Status.BAD_REQUEST).entity("Hash algorithm not supported: " + algorithm).build();
        }
    }
}
