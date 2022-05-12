package com.example.crypto.CryptoAlgorithms;

public class Caesar {

    static char cipher(char ch, int key) {
        if (Character.isLetter(ch)) {
            char d = Character.isUpperCase(ch) ? 'A' : 'a';
            return (char) ((((ch + key) - d) % 26) + d);
        }
        else return ch;
    }

    public String encrypt(String plainText, int key) {
        StringBuilder cipherText = new StringBuilder();
        int strLength = plainText.length();
        for (int i = 0 ; i < strLength; i++)
            cipherText.append(cipher(plainText.charAt(i), key));
        return cipherText.toString();
    }

    public String decrypt(String cipherText, int key) {
        return encrypt(cipherText, 26 - key);
    }
}
