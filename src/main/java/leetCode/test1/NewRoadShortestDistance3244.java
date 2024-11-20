package leetCode.test1;

/**
 * 给你一个整数n和一个二维整数数组queries
 * 有n个城市，从编号0 - n-1.初始时，每个城市i都有一条单向道路通往城市i+1 (0 <= i < n-1)。
 * 0 -> 1 -> 2 -> ... -> i -> i+1 -> ... -> n-1
 * queries[i] = [ui,vi]表示新建一条从城市ui到城市vi的单向道路。每次查询后，你需要找到从城市0到城市n-1的最短路径的长度
 * 所有查询中不会存在两个查询都满足queries[i][0] < queries[j][0] < queries[i][1] < queries[j][1]。
 * 返回一个数组answer，对于范围[0, queries.length -1]中的每个i，answer[i]是处理完前i+1个查询后，从城市0到城市n-1的最短路径长度。
 */
public class NewRoadShortestDistance3244 {
    public static int[] shortestDistance(int n, int[][] queries) {
        int[] roads = new int[n];
        for (int i = 0; i < roads.length; i++) {
            roads[i] = i + 1;
        }
        int shortestDistance = n - 1;
        int[] answer = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            int k = roads[queries[i][0]];// 当前城市
//            roads[queries[i][0]] = queries[i][1];
            while (k != -1 && k + 1 < roads[queries[i][1]]) {// 如果当前城市已被设置为-1 或者 大等于目的城市 则退出循环
                int t = roads[k];// 下一个城市
                roads[k] = -1;// 将下一个城市设置为-1
                k = t;
                shortestDistance--;
            }
            answer[i] = shortestDistance;
        }
        return answer;
    }
}
