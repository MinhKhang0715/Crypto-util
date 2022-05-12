package com.example.crypto.CryptoAlgorithms;

public class Vigenere {

    public String encrypt(String plainText, String key) {
        StringBuilder cipher = new StringBuilder();
        int strLength = plainText.length();
        for (int i = 0; i < strLength; i++) {
            char ch = key.charAt(i % key.length());
            char d = Character.isLowerCase(plainText.charAt(i)) ?
                    (char) ((plainText.charAt(i) - 'a' + Character.toLowerCase(ch) - 'a') % 26 + 'a') :
                    (char) ((plainText.charAt(i) - 'A' + Character.toUpperCase(ch) - 'A') % 26 + 'A');
            cipher.append(d);
        }
        return cipher.toString();
    }

    public String decrypt(String cipherText, String key) {
        StringBuilder decrypted = new StringBuilder();
        int strLength = cipherText.length(), value, plainCharValue;
        for (int i = 0; i < strLength; i++) {
            char ch = key.charAt(i % key.length());
            if (Character.isLowerCase(cipherText.charAt(i))) {
                value = (cipherText.charAt(i) - 'a') - (Character.toLowerCase(ch) - 'a');
                value += value < 0 ? 26 : 0;
                plainCharValue = value % 26 + 'a';
                decrypted.append((char) plainCharValue);

            }
            else {
                value = (cipherText.charAt(i) - 'A') - (Character.toUpperCase(ch) - 'A');
                value += value < 0 ? 26 : 0;
                plainCharValue = value % 26 + 'A';
                decrypted.append((char) plainCharValue);
            }
        }
        return decrypted.toString();
    }
}
