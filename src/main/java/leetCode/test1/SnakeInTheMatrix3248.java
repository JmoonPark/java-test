package leetCode.test1;

/**
 * 大小为n x n的矩阵grid中有一条蛇，蛇可以朝四个可能的方向移动。矩阵中的每个单元格都使用位置进行标识：grid[i][j] = (i * n) + j。
 * 蛇从单元格0开始，并遵循一系列命令移动。
 * 给你一个整数n表示grid的大小，另给你一个字符串数组commands，其中包括"UP"、"RIGHT"、"DOWN"、"LEFT"。
 * 题目测评数据保证蛇在移动过程中将始终位于grid边界内。
 * 返回执行commands后蛇所停留的最终单元格的位置。
 */
public class SnakeInTheMatrix3248 {
    public static int move(int n, String[] commands) {
        int answer = 0;
        for (String command : commands) {
            if (command.charAt(0) == 'U') {
                answer -= n;
            } else if (command.charAt(0) == 'D') {
                answer += n;
            } else if (command.charAt(0) == 'L') {
                answer--;
            } else {
                answer++;
            }
        }
        return answer;
    }
}
