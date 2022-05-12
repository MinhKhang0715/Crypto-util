package com.example.crypto.CryptoAlgorithms;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class RSA {

    public static int prime1 = 37, prime2 = 23,
            primeProduct = prime1 * prime2,
            phiPrime = ((prime1 - 1) * (prime2 - 1)) / gcd(prime1 - 1, prime2 - 1),
            e = 13, d = modInverse(e, phiPrime);

    static int gcd(int a, int b) {
        int temp;
        while (b != 0) {
            temp = a % b;
            a = b;
            b = temp;
        }
        return a;
    }

    static int modInverse(int number, int modulo) {
        int m0 = modulo;
        int y = 0, x = 1;
        if (modulo == 1) return 0;
        while (number > 1) {
            int quotient = number / modulo;
            int temp = modulo;
            modulo = number % modulo;
            number = temp;
            temp = y;
            y = x - quotient * y;
            x = temp;
        }
        x += x < 0 ? m0 : 0;
        return x;
    }

    static int modExponent(int number, int exponent, int modulo) {
        int r = 1;
        while (exponent > 0) {
            if (exponent % 2 == 1)
                r = ((r * number) % modulo);
            number = (number * number) % modulo;
            exponent /= 2;
        }
        return r;
    }

    public int[] encrypt(String plainText) {

        String strBytes = Arrays.toString(plainText.getBytes(StandardCharsets.UTF_8));
        String[] string = strBytes.replaceAll("\\[", "").replaceAll("]", "").split(", ");
        int[] plainBytes = new int[string.length];
        int[] cipher = new int[string.length];
        for (int i = 0; i < string.length; i++) {
            plainBytes[i] = Integer.parseInt(string[i]);
            cipher[i] = modExponent(plainBytes[i], e, primeProduct);
        }
        System.out.println(intArrToHex(cipher));
        return cipher;
    }

    public int[] decrypt(int[] cipherText) {
//        byte[] cipherBytes = cipherText.getBytes(StandardCharsets.UTF_8);
        int[] plain = new int[cipherText.length];
        for (int i = 0; i < cipherText.length; i++)
            plain[i] = modExponent(cipherText[i], d, primeProduct);
        return plain;
    }

    public static String byteToUnsignedHex(int i) {
        StringBuilder hex = new StringBuilder(Integer.toHexString(i));
        while(hex.length() < 8){
            hex.insert(0, "0");
        }
        return hex.toString();
    }

    public static String intArrToHex(int[] arr) {
        StringBuilder builder = new StringBuilder(arr.length * 8);
        for (int b : arr) {
            builder.append(byteToUnsignedHex(b));
        }
        return builder.toString();
    }

    public static void main(String[] args) {

        String origin = "My name is Khang";
        System.out.println("Origin: " + Arrays.toString(origin.getBytes(StandardCharsets.UTF_8)));
        RSA rsa = new RSA();

        System.out.println("Public: " + RSA.e + ", " + RSA.primeProduct);
        System.out.println("Private: " + RSA.d + ", " + RSA.primeProduct);
        System.out.println("Phi prime: " + RSA.phiPrime);
        int[] encrypted = rsa.encrypt(origin);
        System.out.println("Encrypted string: " + Arrays.toString(encrypted));
        int[] decrypted = rsa.decrypt(encrypted);
        System.out.println("Decrypted string: " + Arrays.toString(decrypted));
    }

}
