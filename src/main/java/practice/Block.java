package practice;

import lombok.Data;

@Data
public class Block {
    private String preBlockHash;
    private String merkleRootHash;
    private String nonce;
    private String target;
    private String bitVersion;
}