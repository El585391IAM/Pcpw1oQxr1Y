// 代码生成时间: 2025-10-14 03:25:27
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.security.*;
import java.security.spec.*;
import java.util.Base64;

@Path("/signature")
public class DigitalSignatureTool {

    // 生成密钥对
    private static KeyPair generateKeyPair() throws NoSuchAlgorithmException {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(2048);
        return keyPairGenerator.generateKeyPair();
    }

    // 使用私钥对消息进行签名
    private static String signMessage(String message, PrivateKey privateKey) throws SignatureException, InvalidKeyException {
        Signature signature = Signature.getInstance("SHA256withRSA");
        signature.initSign(privateKey);
        signature.update(message.getBytes());
        byte[] signedBytes = signature.sign();
        return Base64.getEncoder().encodeToString(signedBytes);
    }

    // 使用公钥验证签名
    private static boolean verifySignature(String message, String signature, PublicKey publicKey)
            throws SignatureException, InvalidKeyException, NoSuchAlgorithmException {
        Signature signatureVerification = Signature.getInstance("SHA256withRSA");
        signatureVerification.initVerify(publicKey);
        signatureVerification.update(message.getBytes());
        return signatureVerification.verify(Base64.getDecoder().decode(signature));
    }

    // 获取签名接口
    @GET
    @Path("/sign")
    @Produces(MediaType.APPLICATION_JSON)
    public String sign(@QueryParam("message") String message) {
        try {
            KeyPair keyPair = generateKeyPair();
            String signature = signMessage(message, keyPair.getPrivate());
            return "{\"message\":\"" + message + "\",\"signature\":\"" + signature + "\"}";
        } catch (Exception e) {
            return "{\"error\":\"