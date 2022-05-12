package com.example.crypto.CryptoAlgorithms;

public class Affine {

    static int modInverse(int number) {
        for (int i = 0; i < 26; i++)
            if ((number * i) % 26 == 1)
                return i;
        return -1;
    }

    public String encrypt(String plainText, int key1, int key2) {
        StringBuilder cipher = new StringBuilder();
        int strLength = plainText.length();
        for (int i = 0; i < strLength; i++) {
            if (Character.isLetter(plainText.charAt(i))) {
                char d = Character.isUpperCase(plainText.charAt(i)) ? 'A' : 'a';
                cipher.append((char) ((key1 * (plainText.charAt(i) - d) + key2) % 26 + d));
            }
            else cipher.append(plainText.charAt(i));
        }
        return cipher.toString();
    }

    public String decrypt(String cipherText, int key1, int key2) {
        StringBuilder decrypted = new StringBuilder();
        int keyInverse = modInverse(key1);
        if (keyInverse == -1) return "AFFINE ALGORITHM: Key inverse not exists";
        int strLength = cipherText.length();
        for (int i = 0; i < strLength; i++) {
            if (Character.isLetter(cipherText.charAt(i))) {
                char d = Character.isUpperCase(cipherText.charAt(i)) ? 'A' : 'a';
                decrypted.append((char) (((((cipherText.charAt(i) - d - key2) % 26 + 26) % 26) * keyInverse) % 26 + d));
            }
            else decrypted.append(cipherText.charAt(i));
        }
        return decrypted.toString();
    }
}
