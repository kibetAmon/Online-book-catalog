package com.amon.book_catalog.util;

public class PasswordEncryptor {
    public static void main(String[] args) {
        String password = "Amon1234";
        String encrypted = CryptoUtil.encrypt(password);
        System.out.println("Encrypted password: " + encrypted);

        String decrypted = CryptoUtil.decrypt(encrypted);
        System.out.println("Decrypted password: " + decrypted);
    }

}
