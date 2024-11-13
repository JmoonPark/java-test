package practice;

import lombok.Data;
import org.apache.commons.codec.binary.Base64;

import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

@Data
public class MyKeyPair {
    private String algorithm;
    private int keySize;
    private String publicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCI0KKOHo1/qa1uNL0J4DJ6Kn77C5+tfoxa6ELWstlWqUkwtMouLSSDLUBCf+5kd2ZX7SfE6EFTfOroXiVqWsFbl7Y7N/acjyJjNb+POwXec5xD5is9ze9h7JpO8qzweHTDFSMEVO85nUKIIVKv5ODDQcV4pLvSF7an56X2AQI19wIDAQAB";
    private String privateKey = "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAIjQoo4ejX+prW40vQngMnoqfvsLn61+jFroQtay2VapSTC0yi4tJIMtQEJ/7mR3ZlftJ8ToQVN86uheJWpawVuXtjs39pyPImM1v487Bd5znEPmKz3N72Hsmk7yrPB4dMMVIwRU7zmdQoghUq/k4MNBxXiku9IXtqfnpfYBAjX3AgMBAAECgYBaWFOPv9M2o0h4RvEyLU+H5roVc1aNTczHVyqT9mSY2o8PT8NW/M5opSATCcHVrBb0JxIk8C37QLkqmsjgU2/s4QhzjusAl/oLQzFktiS+XNc2M8yVrHzV61/GEzUt7Y08FV0xlYbLhKbSpV6IqH3l4Ei/esgWKDRTgXYDNSXzUQJBAMmGxw01gNrEvSt/WEg3yKdyL6DQqoxRkK7QCOeNkb1schoJidiB5tuesDTlTnUEn4ALOku14nSKYK0kmg2Eo/kCQQCty/Qbzwq1tHALBBXZRYODN+i6S9fWvYDu0lPkNy8/2X+MkyuF9Fj3BLZFEXYZZz/iINdhqAMS2Lso0jfv9UVvAkEApCGg/KEW0RZq+4I75JWKVtYo9FD+lknYDIhiuDQU/rHUPGCIZE1oOHaGDI6115hsT1zVndQFG+WX12x4C4p8AQJAEYt9YTFNi3pjtTj8W6rA1AZY4DW33F3VHkbtYSSRCIkUb0SMKEG6mXVIUUCqA3aA5HscQyeK5+QpQL24F+lS0QJBAJB2aPxko/GNbYN691tS8tC1/j4cQ02KUbzB2d9lLfRSchiJveuLaZDHIougzSZQnzqp6frY1GFp8Fu3ae1iQc8=";

    private MyKeyPair(String algorithm, int keySize) {
        this.algorithm = algorithm;
        this.keySize = keySize;
    }

    public static MyKeyPair getInstance(String algorithm, int keySize) {
        return new MyKeyPair(algorithm, keySize);
    }

    public void generateKeyPair() throws NoSuchAlgorithmException {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(this.algorithm);
        keyPairGenerator.initialize(this.keySize);
        KeyPair keyPair = keyPairGenerator.generateKeyPair();
        PublicKey publicKey = keyPair.getPublic();
        this.publicKey = Base64.encodeBase64String(publicKey.getEncoded());
        PrivateKey privateKey = keyPair.getPrivate();
        this.privateKey = Base64.encodeBase64String(privateKey.getEncoded());
    }

    public PublicKey getPublicKeyInstance() throws NoSuchAlgorithmException, InvalidKeySpecException {
        KeyFactory keyFactory = KeyFactory.getInstance(this.algorithm);
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(Base64.decodeBase64(this.publicKey));
        return keyFactory.generatePublic(keySpec);
    }

    public PrivateKey getPrivateKeyInstance() throws InvalidKeySpecException, NoSuchAlgorithmException {
        KeyFactory keyFactory = KeyFactory.getInstance(this.algorithm);
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(Base64.decodeBase64(this.privateKey));
        return keyFactory.generatePrivate(keySpec);
    }
}