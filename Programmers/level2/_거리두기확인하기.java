class Solution {
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    static boolean[][] visit;

    static int[] answer;

    // ��ȸ�ϰ� �ִ� ���� ��ȣ, ����� ��, ����� ��, Ž�� ����, ���� ����
    public void dfs(int num, int x, int y, int count, String[] places){
        // Ž�� ���̰� 2���� �Ѿ�� �Ÿ��α⸦ �� �ؼ��� ���.
        if (count > 2) return;

        // Ž�� ���̰� 1~2 ������ ��� �ٸ� �����ڰ� ���� ��� �Ÿ��α� ���ؼ��� 0ó��
        if (count > 0 && count <= 2 && places[x].charAt(y) == 'P'){
            //2ĭ �������� �ٸ� �����ڰ� ���� ��� �Ÿ��α� ���ؼ��� 0ó��
            answer[num] = 0;
            return;
        }

        // ����� ��ǥ�������� 4���� Ž��
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            //�迭 ���� ������ �ʰ��ϴ��� ���� �˻�, 
            // �ʱ⿡ �� ���״ٴ� �ǹ̷� 1�� �ʱ�ȭ�� �߱⿡, (�߿�) �츮�� ������ ��Ƽ���� ���� ��쿡 ���ؼ� �Ÿ��α⸦ �� ���״��� Ȯ���ϴ� ���̴�.
            if (nx >= 0 && nx < 5 && ny >= 0 && ny < 5 && places[nx].charAt(ny) != 'X') {
                if (visit[nx][ny]) continue; //�̹� �湮�� ���� ��� ����
                visit[nx][ny] = true;
                dfs(num, nx, ny, count + 1, places); 
                visit[nx][ny] = false;
            }
        }
    }

    public int[] solution(String[][] places) {
        answer = new int[places.length];

		// (�߿�) ��� �Ÿ��α⸦ �� ���״ٰ� �Ǵ�. : �� �������� ����� ���� �� �۴�. (�� Ű������ �ʴ� 0�� ��찡 �� ����.)
        // �׷���, ����� ���� ���� 1�� �⺻������ ���� �ϰ� �� �������� ���� ����� ���� ���� 0�� ������ 0���� ����
        for (int i = 0; i < places.length; i++) {
            answer[i] = 1;
        }

        // ������ ������ŭ �ݺ�
        for (int i = 0; i < places.length; i++) {
            // �ϳ��� ������ �������� �湮 �迭 ����
            visit = new boolean[5][5];
            // ������ ���� �������� �ݺ� (�ϳ��� ���� ��� ��ȸ)
            for (int j = 0; j < 5; j++) {
                for (int k = 0; k < 5; k++) {
                    // ��ȸ�� �ϸ鼭 ����� �߰��� ���
                    if (places[i][j].charAt(k) == 'P'){
                        // ����� �湮 üũ
                        visit[j][k] = true;

                        // ���� ��ȣ, ����� ��, ����� ��, Ž�� ����, ���� ����
                        dfs(i, j, k, 0, places[i]);

                        // ��� �湮 üũ ����
                        visit[j][k] = false;
                    }
                }
            }
        }

        return answer;
    }
}