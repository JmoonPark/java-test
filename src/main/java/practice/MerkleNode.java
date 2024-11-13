package practice;

import lombok.Data;

@Data
public class MerkleNode {
    private String leftNodeHash;
    private String rightNodeHash;
}