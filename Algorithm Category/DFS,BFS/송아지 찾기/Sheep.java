import java.util.*;

public class Sheep {
    int answer = 0;

    int[] move = {1, -1, 5};
    int[] visited = new int[10001];

    Queue<Integer> Q = new LinkedList<>();

    public int BFS(int start, int end) {
        // 방문을 했다는 의미
        visited[start] = 1;
        Q.offer(start);
        int level = 0;

        while(!Q.isEmpty()) {
            int len = Q.size();

            // 자식 노드 수 만큼 순회
            for(int i = 0; i < len ; i++){
                int x = Q.poll();

                // 이동 가능 범위
                for(int j = 0; j < 3; j++) {
                    int nx = x + move[j];

                    if(nx == end) return level + 1;

                    // 범위 값 && 방문하지 않았다면
                    if(nx >= 1 && nx <= 10000 && visited[nx] == 0) {
                        // 자식 노드를 미리 방문 처리
                        visited[nx] = 1;
                        // 자식 노드를 순회하기 위해 Q에 넣기
                        Q.offer(nx);
                    }
                }
            }

            // 자식 노드를 모두 순회했으므로 level 증가
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