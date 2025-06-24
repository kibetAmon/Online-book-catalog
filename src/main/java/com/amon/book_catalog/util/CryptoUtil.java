package com.amon.book_catalog.util;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

public class CryptoUtil {

    private static final String SECRET_KEY = "MySuperSecretKey";  // Must be 16 bytes for AES-128

    private static SecretKeySpec getKeySpec() {
        byte[] keyBytes = new byte[16];
        byte[] secretBytes = SECRET_KEY.getBytes();
        System.arraycopy(secretBytes, 0, keyBytes, 0, Math.min(secretBytes.length, keyBytes.length));
        return new SecretKeySpec(keyBytes, "AES");
    }




    public static String encrypt(String data) {
        try {
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");  // Explicit padding
            cipher.init(Cipher.ENCRYPT_MODE, getKeySpec());
            byte[] encrypted = cipher.doFinal(data.getBytes());
            return Base64.getEncoder().encodeToString(encrypted);
        } catch (Exception exception) {
            throw new RuntimeException("Error encrypting walahi", exception);
        }
    }

    public static String decrypt(String encryptedData) {
        try {
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");  // Explicit padding
            cipher.init(Cipher.DECRYPT_MODE, getKeySpec());
            byte[] decoded = Base64.getDecoder().decode(encryptedData);
            byte[] decrypted = cipher.doFinal(decoded);
            return new String(decrypted);
        } catch (Exception exception) {
            throw new RuntimeException("Error decrypting walahi", exception);
        }
    }
}