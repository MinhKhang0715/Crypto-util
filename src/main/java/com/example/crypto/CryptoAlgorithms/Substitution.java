package com.example.crypto.CryptoAlgorithms;

public class Substitution {

    static String plainAlphabet = "abcdefghijklmnopqrstuvwxyz";

    static String cipher(String input, String oldAlphabet, String newAlphabet) {
        StringBuilder output = new StringBuilder();
        if (oldAlphabet.length() != newAlphabet.length())
            return "False";
        int strLength = input.length();
        for (int i = 0; i < strLength; i++) {
            int oldCharIndex = oldAlphabet.indexOf(Character.toLowerCase(input.charAt(i)));
            if (oldCharIndex >= 0) {
                char d = Character.isUpperCase(input.charAt(i)) ? Character.toUpperCase(newAlphabet.charAt(oldCharIndex)) :
                        newAlphabet.charAt(oldCharIndex);
                output.append(d);
            }
            else output.append(input.charAt(i));
        }
        return output.toString();
    }

    public String encrypt(String input, String cipherAlphabet) {
        return cipher(input, plainAlphabet, cipherAlphabet);
    }

    public String decrypt(String input, String cipherAlphabet) {
        return cipher(input, cipherAlphabet, plainAlphabet);
    }
}
