import leetCode.test1.GoodNode3249;
import leetCode.test1.NewRoadShortestDistance3244;
import org.apache.commons.codec.digest.DigestUtils;
import practice.CommonUtils;
import practice.MyKeyPair;
import practice.TransactionNode;

import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainPractice {
    public static void main(String[] args) {
        test4();
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
        List<String> list = new ArrayList<>();
        List<String> checkList = new ArrayList<>();
        for (int i = 0; i < 1000000; i++) {
            list.add("" + i);
        }
        for (int i = 0; i < 1010000; i++) {
            checkList.add("" + i);
        }
        CommonUtils.bloomFilter(list, checkList, 0.03D);
    }

    /**
     * 测试GoodNode3249
     */
    public static void test3() {
        int[][] edges = new int[12][2];
        edges[0] = new int[]{0, 1};
        edges[1] = new int[]{1, 2};
        edges[2] = new int[]{1, 3};
        edges[3] = new int[]{1, 4};
        edges[4] = new int[]{0, 5};
        edges[5] = new int[]{5, 6};
        edges[6] = new int[]{6, 7};
        edges[7] = new int[]{7, 8};
        edges[8] = new int[]{0, 9};
        edges[9] = new int[]{9, 10};
        edges[10] = new int[]{9, 12};
        edges[11] = new int[]{10, 11};
        int i = new GoodNode3249().countGoodNodes(edges);
        System.out.println("计算结果为：" + i);
    }

    public static void test4() {
        int[][] queries = new int[2][2];
        queries[0] = new int[]{0, 3};
        queries[1] = new int[]{0, 2};
//        queries[2] = new int[]{0, 4};
        int[] ints = NewRoadShortestDistance3244.shortestDistance(4, queries);
        System.out.println("计算结果为：" + Arrays.toString(ints));
    }
}