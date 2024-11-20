package leetCode.test1;

import java.util.ArrayList;
import java.util.List;

/**
 * 给定一个无向树，求得该树中的好节点数量
 * 二维数组形式的数：数组中的每个数组由两个整数组成，这两个整数为树中两个相邻节点的值。
 * 好节点：当一个节点的每个子树的节点数量相同时，该节点为好节点。
 */
public class GoodNode3249 {
    int res = 0;
    List<Integer>[] nodeList;
    public int countGoodNodes(int[][] edges) {
        int nodeCount = edges.length + 1;
        nodeList = new List[nodeCount];
        for (int i = 0; i < nodeCount; i++) {
            nodeList[i] = new ArrayList<>();
        }
        for (int[] edge : edges) {
            nodeList[edge[0]].add(edge[1]);
//            nodeList[edge[1]].add(edge[0]);
        }
        dfs(0, -1);
        return res;
    }
    private int dfs(int node, int parent) {
        boolean valid = true;
        int treeSize = 0;
        int subTreeSize = 0;
        for (Integer child : nodeList[node]) {
//            if (child != parent) {
                int size = dfs(child, node);
                if (subTreeSize == 0) {
                    subTreeSize = size;
                } else if (size != subTreeSize) {
                    valid = false;
                }
                treeSize += size;
//            }
        }
        if (valid) {
            res++;
        }
        return treeSize + 1;
    }
}
