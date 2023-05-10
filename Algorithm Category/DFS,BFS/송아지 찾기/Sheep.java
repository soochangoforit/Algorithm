import java.util.*;

public class Sheep {
    int answer = 0;

    int[] move = {1, -1, 5};
    int[] visited = new int[10001];

    Queue<Integer> Q = new LinkedList<>();

    public int BFS(int start, int end) {
        // �湮�� �ߴٴ� �ǹ�
        visited[start] = 1;
        Q.offer(start);
        int level = 0;

        while(!Q.isEmpty()) {
            int len = Q.size();

            // �ڽ� ��� �� ��ŭ ��ȸ
            for(int i = 0; i < len ; i++){
                int x = Q.poll();

                // �̵� ���� ����
                for(int j = 0; j < 3; j++) {
                    int nx = x + move[j];

                    if(nx == end) return level + 1;

                    // ���� �� && �湮���� �ʾҴٸ�
                    if(nx >= 1 && nx <= 10000 && visited[nx] == 0) {
                        // �ڽ� ��带 �̸� �湮 ó��
                        visited[nx] = 1;
                        // �ڽ� ��带 ��ȸ�ϱ� ���� Q�� �ֱ�
                        Q.offer(nx);
                    }
                }
            }

            // �ڽ� ��带 ��� ��ȸ�����Ƿ� level ����
            level++;
        }

        return 0;

    }


    public static void main(String[] args){
        Sheep T = new Sheep();
        Scanner kb = new Scanner(System.in);
        int start = kb.nextInt();
        int end = kb.nextInt();
        System.out.print(T.BFS(start, end));
    }
}