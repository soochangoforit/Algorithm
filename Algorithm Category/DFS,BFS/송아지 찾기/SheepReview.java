import java.util.*;

public class SheepReview {
    int answer = 0;

    int[] move = {1, -1, 5};
    int[] visited = new int[10001];

    Queue<Integer> Q = new LinkedList<>();

    public int BFS(int start, int end) {
        
        visited[start] = 1;
        Q.offer(start);
        int level = 0;

        while(!Q.isEmpty()){
            
            int len = Q.size();

            for (int i = 0; i < len ; i++){
                // 부모 노드 꺼내기
                int x = Q.poll();

                // 부모 노드의 자식 노드 순회
                for(int j = 0; j < 3; j++) {
                    int nx = x + move[j];

                    if(nx == end) return level + 1;

                    if (nx >=1 && nx <= 10000 && visited[nx] == 0) {
                        visited[nx] = 1;
                        Q.offer(nx);
                    }
                }
            }

            level++;
        }

        return 0;

    }


    public static void main(String[] args){
        SheepReview T = new SheepReview();
        Scanner kb = new Scanner(System.in);
        int start = kb.nextInt();
        int end = kb.nextInt();
        System.out.print(T.BFS(start, end));
    }
}