package practice;

import java.security.*;

import org.apache.commons.codec.digest.DigestUtils;

public class MainPractice {
    public static void main(String[] args) {
//        test();
        test2();
    }

    /**
     * 测试公私钥生成、签名生成及验证
     */
    public static void test() {
        try {
            String data = "我是逆蝶";
            String dataHash = DigestUtils.sha256Hex(data);
            TransactionNode transactionNode = new TransactionNode();
            transactionNode.setDataHash(dataHash);
            transactionNode.setSender("逆蝶");
            transactionNode.setReceiver("你");
            transactionNode.setSign("逆蝶");

            String transactionHash = DigestUtils.sha256Hex(transactionNode.toString());
            System.out.println("transactionHash = " + transactionHash);
            // 获取公私钥
            MyKeyPair myKeyPair = MyKeyPair.getInstance("RSA", 1024);
            myKeyPair.generateKeyPair();
            PublicKey publicKey = myKeyPair.getPublicKeyInstance();
            PrivateKey privateKey = myKeyPair.getPrivateKeyInstance();
            // 生成签名
            byte[] signedBytes = CommonUtils.generateSignature(transactionHash, privateKey);
            // 验证签名
            boolean verified = CommonUtils.verifySignature(transactionHash, publicKey, signedBytes);
            System.out.println("verified = " + verified);
            // 对明文加密
            String encryptedData = CommonUtils.encryptWithRSA(transactionHash, publicKey);
            System.out.println("encryptedData = " + encryptedData);
            // 对密文解密
            String decryptedStr = CommonUtils.decryptWithRSA(encryptedData, privateKey);
            System.out.println("decryptedStr = " + decryptedStr);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 测试布隆过滤器
     */
    public static void test2() {
        CommonUtils.bloomFilter();
    }
}