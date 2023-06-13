import java.util.*;

class Solution {
    public int[] solution(int rows, int columns, int[][] queries) {
        int[] answer = {};

        // 1. rows * columns 크기의 배열을 만든다.
        int[][] board = new int[rows][columns];
        int num = 1;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++)
                board[i][j] = num++;
        }

        // 2. queries의 각 행마다 회전을 시킨다.
        answer = new int[queries.length];
        int count = 0;

        // 2-1. 회전을 시키기 위한 임시 배열을 만든다.
        // 임시배열은 queries에 담긴 실행과 함께 공유되어야 한다.
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

            // 2-3. 윗변 오른쪽으로 이동
            for (int x = startX; x < endX; x++) {
                tempBoard[startY][x + 1] = board[startY][x];
                min = Math.min(min, board[startY][x]);
            }

            // 2-4. 우측변 아래쪽으로 이동
            for (int y = startY; y < endY; y++) {
                tempBoard[y + 1][endX] = board[y][endX];
                min = Math.min(min, board[y][endX]);
            }

            // 2-5. 밑변 왼쪽으로 이동
            for (int x = endX; x > startX; x--) {
                tempBoard[endY][x - 1] = board[endY][x];
                min = Math.min(min, board[endY][x]);
            }

            // 2-6. 좌측변 위쪽으로 이동
            for (int y = endY; y > startY; y--) {
                tempBoard[y - 1][startX] = board[y][startX];
                min = Math.min(min, board[y][startX]);
            }

            // 최소값을 정답 배열에 넣기
            answer[count] = min;
            count++;

            // 2-7. tempBoard를 board로 복사 (다음 querie가 실행될 때 tempBoard를 사용해야 하므로)
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < columns; j++)
                    board[i][j] = tempBoard[i][j];
            }
        }

        return answer;
    }
}