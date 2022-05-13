package com.example.crypto.CryptoAlgorithms;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
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

    /**
     * Encrypting with the formula: C = M^e mod n
     * @param message is the message need to encrypt
     * @return String
     */
    public String encryptMessage(String message) {
        return Base64.getEncoder().encodeToString(
                (new BigInteger(message.getBytes(StandardCharsets.UTF_8)))
                        .modPow(e, primeProduct).toByteArray()
        );
    }

    /**
     * Decrypting with the formula: M = C^d mod n
     * @param message is the message need to decrypt
     * @return String
     */
    public String decryptMessage(String message) {
        byte[] messageBytes = Base64.getDecoder().decode(message);
        return new String((new BigInteger(messageBytes)).modPow(d, primeProduct).toByteArray());
    }
}
