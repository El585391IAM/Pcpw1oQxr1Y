// 代码生成时间: 2025-09-01 16:58:02
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.SecureRandom;
import java.util.Base64;

public class PasswordEncryptionService {

    // AES Algorithm
    private static final String ALGORITHM = "AES";
    private static final String TRANSFORMATION = "AES";

    private static final String ENCRYPTION_KEY = "mysecretkey"; // Should be stored securely

    /**
     * Encrypts the given plain text password.
     * @param plainText The plaintext password to be encrypted.
     * @return The encrypted password in Base64 encoding.
     */
    public String encrypt(String plainText) {
        try {
            // Generate AES key from the encryption key
            byte[] key = ENCRYPTION_KEY.getBytes("UTF-8");
            SecretKeySpec secretKeySpec = new SecretKeySpec(key, ALGORITHM);

            // Initialize the cipher for encryption
            Cipher cipher = Cipher.getInstance(TRANSFORMATION);
            cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);

            // Perform encryption
            byte[] encryptedBytes = cipher.doFinal(plainText.getBytes("UTF-8"));

            // Convert encrypted bytes to Base64 string
            return Base64.getEncoder().encodeToString(encryptedBytes);

        } catch (Exception e) {
            throw new RuntimeException("Error occurred while encrypting the password", e);
        }
    }

    /**
     * Decrypts the given encrypted password.
     * @param encryptedText The encrypted password in Base64 encoding.
     * @return The decrypted plaintext password.
     */
    public String decrypt(String encryptedText) {
        try {
            // Generate AES key from the encryption key
            byte[] key = ENCRYPTION_KEY.getBytes("UTF-8");
            SecretKeySpec secretKeySpec = new SecretKeySpec(key, ALGORITHM);

            // Initialize the cipher for decryption
            Cipher cipher = Cipher.getInstance(TRANSFORMATION);
            cipher.init(Cipher.DECRYPT_MODE, secretKeySpec);

            // Perform decryption
            byte[] decryptedBytes = cipher.doFinal(Base64.getDecoder().decode(encryptedText));

            // Convert decrypted bytes to string
            return new String(decryptedBytes, "UTF-8");

        } catch (Exception e) {
            throw new RuntimeException("Error occurred while decrypting the password", e);
        }
    }
}
