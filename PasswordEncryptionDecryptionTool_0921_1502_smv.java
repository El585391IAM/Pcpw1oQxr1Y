// 代码生成时间: 2025-09-21 15:02:55
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.SecureRandom;
import java.util.Base64;

public class PasswordEncryptionDecryptionTool {

    // 密码加密
    public static String encrypt(String password) throws Exception {
        // 生成密钥
        KeyGenerator keyGen = KeyGenerator.getInstance("AES");
        keyGen.init(128, SecureRandom.getInstanceStrong());
        SecretKey secretKey = keyGen.generateKey();

        // 获取密钥的字节形式
        byte[] keyBytes = secretKey.getEncoded();
        // 将密钥转换为AES密钥格式
        SecretKeySpec secretKeySpec = new SecretKeySpec(keyBytes, "AES");

        // 获取Cipher实例
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);

        // 加密密码
        byte[] encryptedBytes = cipher.doFinal(password.getBytes());
        // 将加密后的字节转换为Base64字符串
        return Base64.getEncoder().encodeToString(encryptedBytes);
    }

    // 密码解密
    public static String decrypt(String encryptedPassword) throws Exception {
        // 将Base64字符串转换为字节
        byte[] encryptedBytes = Base64.getDecoder().decode(encryptedPassword);
        // 获取密钥的字节形式
        byte[] keyBytes = new byte[16]; // AES密钥长度为128位，即16字节
        // 假设使用的是相同的密钥，这里直接复制示例密钥
        System.arraycopy(encryptedBytes, 0, keyBytes, 0, 16);

        // 将密钥转换为AES密钥格式
        SecretKeySpec secretKeySpec = new SecretKeySpec(keyBytes, "AES");

        // 获取Cipher实例
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, secretKeySpec);

        // 解密密码
        byte[] decryptedBytes = cipher.doFinal(encryptedBytes, 16, encryptedBytes.length - 16);
        // 将解密后的字节转换为字符串
        return new String(decryptedBytes);
    }

    public static void main(String[] args) {
        try {
            String originalPassword = "password123";
            String encryptedPassword = encrypt(originalPassword);
            System.out.println("Encrypted Password: " + encryptedPassword);

            String decryptedPassword = decrypt(encryptedPassword);
            System.out.println("Decrypted Password: " + decryptedPassword);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
