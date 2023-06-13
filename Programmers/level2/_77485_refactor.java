import java.util.*;

class Solution {
    public int[] solution(int rows, int columns, int[][] queries) {
        int[] answer = new int[queries.length]; // ȸ�� ���� ���� �ּҰ��� ������ �迭�� �ʱ�ȭ
        int[][] matrix = new int[rows][columns]; // �־��� ��� ���� ����� �ʱ�ȭ

        // ����� �ʱ�ȭ�մϴ�. �� ��ġ�� �ش��ϴ� ���� �Ҵ�.
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                matrix[i][j] = i * columns + j + 1;
            }
        }

        // �� ������ ���� ȸ�� ������ �����ϰ� ��������� ���� �ּҰ��� answer �迭�� �����մϴ�.
        for (int i = 0; i < queries. length; i++) {
            answer[i] = rotate(matrix, queries[i]);
        }

        return answer; // �ּҰ� �迭�� ��ȯ�մϴ�.
    }

    /**
     * ��� ���� �迭�� �����ϴ� �迭 ���� ���� �õ�
     * 
     * 1 �õ� : startY,startX ���� �����ؼ� endY, endX���� �ð�������� ������
     *        : ������ �ش��ϴ� ������ �迭�� ������ ������, (startY,startX)�� ��ġ�ϴ� ���� ��� ȸ���� �����ϰ� ����Ǵ� ���� �߻�
     *
     * 2 �õ� : �ð�������� ȸ���� �ϴ���, ���ʺ� ������ �� ������ index �� ��ġ�ϴ� ����
     *        : ���ʺ� �� ������ index ���� - 1(��ĭ) ���� ������ �Ҵ� �õ�
     *        : �׷��� ���� �� �𼭸� ���� ȸ���ϸ鼭 �ߺ������� �Ҵ�Ǵ� ���� �߻�
     *
     * 3 �õ� : (����)
     *        : ȸ���� ������ ���� �ӽ÷� ����
     *        : �ð� �ݴ� �������� �����ϸ鼭 ȸ��
     *        : �ð� �ݴ� �������� index�� �����ϸ鼭 ������ ���� �Ҵ�
     *        : �׷��� ����, ������ startY�� startX + 1�� ��ġ�ϴ� ����
     *        : startY+1, startX�� ��ġ�ϴ� ������ �Ҵ�Ǵ� ���� �߻�
     *        : ����, ������ ���� ȸ���� ����(startY,startX+1)�� �Ҵ�
     */
    public int rotate(int[][] matrix, int[] query) {
        int Y1 = query[0] - 1;
        int X1 = query[1] - 1;
        int Y2 = query[2] - 1;
        int X2 = query[3] - 1;

        // (�߿�)
        int temp = matrix[Y1][X1]; // ȸ���� ������ ���� �ӽ÷� ����

        int min = temp; // ȸ�� �߿� �߰ߵ� �ּҰ��� ����

        // ���� ���� ȸ��
        for (int i = Y1; i < Y2; i++) {
            matrix[i][X1] = matrix[i + 1][X1];
            min = Math.min(min, matrix[i][X1]);
        }

        // �Ʒ��� ���� ȸ��
        for (int i = X1; i < X2; i++) {
            matrix[Y2][i] = matrix[Y2][i + 1];
            min = Math.min(min, matrix[Y2][i]);
        }

        // ������ ���� ȸ��
        for (int i = Y2; i > Y1; i--) {
            matrix[i][X2] = matrix[i - 1][X2];
            min = Math.min(min, matrix[i][X2]);
        }

        // ���� ���� ȸ��
        for (int i = X2; i > X1; i--) {
            matrix[Y1][i] = matrix[Y1][i - 1];
            min = Math.min(min, matrix[Y1][i]);
        }

        // (�߿�)
        matrix[Y1][X1 + 1] = temp; // �ӽ÷� �����ص� ������ ���� ȸ���� ������ �Ҵ��մϴ�.

        return min; // ȸ�� �߿� �߰ߵ� �ּҰ��� ��ȯ
    }
}
