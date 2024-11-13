package practice;

import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;
import org.apache.commons.codec.binary.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.util.List;

public class CommonUtils {

    /**
     * 公钥加密(RSA)
     *
     * @param data      原数据
     * @param publicKey 公钥
     * @return 加密后数据(Base64编码后)
     */
    public static String encryptWithRSA(String data, PublicKey publicKey) throws
            NoSuchPaddingException,
            NoSuchAlgorithmException,
            InvalidKeyException,
            BadPaddingException,
            IllegalBlockSizeException {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        byte[] encryptedStr = cipher.doFinal(data.getBytes(StandardCharsets.UTF_8));
        return Base64.encodeBase64String(encryptedStr);
    }

    /**
     * 私钥解密(RSA)
     *
     * @param data       加密数据(Base64编码后)
     * @param privateKey 私钥
     * @return 解密后数据
     */
    public static String decryptWithRSA(String data, PrivateKey privateKey) throws
            NoSuchPaddingException,
            NoSuchAlgorithmException,
            InvalidKeyException,
            BadPaddingException,
            IllegalBlockSizeException {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        byte[] decryptedBytes = cipher.doFinal(Base64.decodeBase64(data));
        return new String(decryptedBytes);
    }

    /**
     * 生成签名
     *
     * @param data       原数据(SHA256算法)
     * @param privateKey 私钥
     * @return 签名字节数组
     */
    public static byte[] generateSignature(String data, PrivateKey privateKey) throws
            NoSuchAlgorithmException,
            InvalidKeyException,
            SignatureException {
        Signature signature = Signature.getInstance("SHA256withRSA");
        signature.initSign(privateKey);
        signature.update(data.getBytes(StandardCharsets.UTF_8));
        return signature.sign();
    }

    /**
     * 验证签名
     *
     * @param originData  原数据
     * @param publicKey   公钥
     * @param signedBytes 签名
     * @return 验证结果
     */
    public static boolean verifySignature(String originData, PublicKey publicKey, byte[] signedBytes) throws
            NoSuchAlgorithmException,
            InvalidKeyException,
            SignatureException {
        Signature signature = Signature.getInstance("SHA256withRSA");
        signature.initVerify(publicKey);
        signature.update(originData.getBytes(StandardCharsets.UTF_8));
        return signature.verify(signedBytes);
    }

    /**
     * 布隆过滤器
     *
     * @param list 初始数据集合
     * @param checkList 匹配集合
     * @return boolean
     */
    public static void bloomFilter(List<String> list, List<String> checkList, double fpp) {
        BloomFilter<CharSequence> filterList =
                BloomFilter.create(Funnels.stringFunnel(StandardCharsets.UTF_8), list.size(), fpp);
        // 初始化数据到过滤器中
        for (String string : list) {
            filterList.put(string);
        }
        // 判断值是否存在过滤器中
        int checkedCount = 0;
        for (String checkString : checkList) {
            if (filterList.mightContain(checkString)) {
                checkedCount++;
            }
        }
        BigDecimal actualFpp = new BigDecimal(checkedCount - list.size())
                .multiply(new BigDecimal(100))
                .divide(new BigDecimal(checkList.size()), 10, BigDecimal.ROUND_HALF_UP);
        System.out.println("预期结果数量 " + list.size());
        System.out.println("已匹配数量 " + checkedCount);
        System.out.println("预期误判率为 " + fpp + ", 实际误判率为 " + actualFpp);
    }
}