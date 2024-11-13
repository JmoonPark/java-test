package practice;

import lombok.Data;

import java.util.List;

@Data
public class TransactionNode {
    private String sender;
    private String receiver;
    private String dataHash;
    private String sign;
    private List<String> transcationHashList;
}