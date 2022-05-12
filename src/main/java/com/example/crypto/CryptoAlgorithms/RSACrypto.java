package com.example.crypto.CryptoAlgorithms;

import java.math.BigInteger;
import java.util.Random;

public class RSACrypto {

    public static final int VERSION = 1024;
    public final BigInteger e = new BigInteger("65537");

    private final BigInteger primeProduct;

    private final BigInteger d; // e * d === 1 (mod lambdaPrime)

    public RSACrypto() {
        BigInteger prime1 = BigInteger.probablePrime(VERSION / 2, new Random());
        BigInteger prime2 = BigInteger.probablePrime(VERSION / 2, new Random());
        primeProduct = prime1.multiply(prime2);
        BigInteger lambdaPrime = prime1.subtract(BigInteger.ONE)
                .multiply(prime2.subtract(BigInteger.ONE))
                .divide(prime1.subtract(BigInteger.ONE).gcd(prime2.subtract(BigInteger.ONE)));
        d = e.modInverse(lambdaPrime);
    }

    public BigInteger getE() {
        return e;
    }

    public BigInteger getPrimeProduct() {
        return primeProduct;
    }

    public BigInteger getD() {
        return d;
    }

    public static BigInteger stringCipher(String message) {
        message = message.toUpperCase();
        StringBuilder cipher = new StringBuilder();
        int i = 0, strLength = message.length();
        while (i < strLength) {
            int ch = message.charAt(i);
            cipher.append(ch);
            i++;
        }
        System.out.println(cipher);
        return new BigInteger(cipher.toString());
    }

    public static String cipherToString(BigInteger message) {
        String cipherString = message.toString();
        StringBuilder output = new StringBuilder();
        int i = 0, strLength = cipherString.length();
        while (i < strLength) {
            int temp = Integer.parseInt(cipherString.substring(i, i + 2));
            char ch = (char) temp;
            output.append(ch);
            i += 2;
        }
        return output.toString();
    }

    /**
     * Encrypting with the formula: C = M^e mod n
     * @param message is the message need to encrypt
     * @return String
     */
    public String encrypt(String message) {
        BigInteger cipher = stringCipher(message);
        return cipher.modPow(e, primeProduct).toString();
    }

    /**
     * Decrypting with the formula: M = C^d mod n
     * @param message is the message need to decrypt
     * @return String
     */
    public String decrypt(String message) {
        BigInteger cipher = new BigInteger(message);
        return cipherToString(cipher.modPow(d, primeProduct));
    }

    public static void main(String[] args) {
        String original = "Hello Im Minh Khang, Im a student from Sai Gon University";
        RSACrypto crypto = new RSACrypto();
        String encrypted = crypto.encrypt(original);
        String decrypted = crypto.decrypt(encrypted);
        System.out.println("Original: " + original + "\nEncrypted: " + encrypted + "\nDecrypted: " + decrypted);
    }
}
