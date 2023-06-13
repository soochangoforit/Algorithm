import java.util.*;

class Solution {
    public int[] solution(int rows, int columns, int[][] queries) {
        int[] answer = {};

        // 1. rows * columns ũ���� �迭�� �����.
        int[][] board = new int[rows][columns];
        int num = 1;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++)
                board[i][j] = num++;
        }

        // 2. queries�� �� �ึ�� ȸ���� ��Ų��.
        answer = new int[queries.length];
        int count = 0;

        // 2-1. ȸ���� ��Ű�� ���� �ӽ� �迭�� �����.
        // �ӽù迭�� queries�� ��� ����� �Բ� �����Ǿ�� �Ѵ�.
        int[][] tempBoard = new int[rows][columns];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++)
                tempBoard[i][j] = board[i][j];
        }

        for (int[] querie : queries) {
            int startY = querie[0] - 1;
            int startX = querie[1] - 1;
            int endY = querie[2] - 1;
            int endX = querie[3] - 1;

            int min = Integer.MAX_VALUE;

            // 2-3. ���� ���������� �̵�
            for (int x = startX; x < endX; x++) {
                tempBoard[startY][x + 1] = board[startY][x];
                min = Math.min(min, board[startY][x]);
            }

            // 2-4. ������ �Ʒ������� �̵�
            for (int y = startY; y < endY; y++) {
                tempBoard[y + 1][endX] = board[y][endX];
                min = Math.min(min, board[y][endX]);
            }

            // 2-5. �غ� �������� �̵�
            for (int x = endX; x > startX; x--) {
                tempBoard[endY][x - 1] = board[endY][x];
                min = Math.min(min, board[endY][x]);
            }

            // 2-6. ������ �������� �̵�
            for (int y = endY; y > startY; y--) {
                tempBoard[y - 1][startX] = board[y][startX];
                min = Math.min(min, board[y][startX]);
            }

            // �ּҰ��� ���� �迭�� �ֱ�
            answer[count] = min;
            count++;

            // 2-7. tempBoard�� board�� ���� (���� querie�� ����� �� tempBoard�� ����ؾ� �ϹǷ�)
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < columns; j++)
                    board[i][j] = tempBoard[i][j];
            }
        }

        return answer;
    }
}