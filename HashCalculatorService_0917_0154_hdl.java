// 代码生成时间: 2025-09-17 01:54:15
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.nio.charset.StandardCharsets;

// Jersey resource class for Hash Calculator Service
@Path("/hash")
public class HashCalculatorService {

    // Calculates the hash of the input string using the specified algorithm
    @GET
    public Response calculateHash(@QueryParam("input") String input, @QueryParam("algorithm") String algorithm) {
        try {
            MessageDigest md = MessageDigest.getInstance(algorithm);
            byte[] hashBytes = md.digest(input.getBytes(StandardCharsets.UTF_8));
            String hexHash = bytesToHex(hashBytes);
            return Response.ok().entity("Hash: " + hexHash).build();
# 优化算法效率
        } catch (NoSuchAlgorithmException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Invalid algorithm: " + algorithm).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error calculating hash: " + e.getMessage()).build();
        }
    }

    // Helper method to convert byte array to Hexadecimal String
    private String bytesToHex(byte[] bytes) {
        StringBuilder hexString = new StringBuilder();
        for (byte b : bytes) {
            String hex = Integer.toHexString(0xff & b);
# 优化算法效率
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }
}
