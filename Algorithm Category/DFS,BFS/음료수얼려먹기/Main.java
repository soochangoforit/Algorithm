import java.util.Scanner;

public class Main {

    public static int n, m;
    public static int[][] graph = new int[1000][1000];


    public static boolean dfs (int x, int y) {
        // 범위를 벗어나는 즉시, 종료
        if (x <= -1 || x >= n || y <= -1 || y >= m) {
            return false;
        }
        
        if (graph[x][y] == 0){
            graph[x][y] = 1;
            // 상, 하, 좌, 우의 위치들도 모두 재귀적으로 호출
            dfs(x-1,y);
            dfs(x,y-1);
            dfs(x+1,y);
            dfs(x,y+1);
            return true;
        }

        return false;

    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // n, m 을 공백을 기준으로 구분하여 입력 받기
        // n은 가로, m은 세로
        n = sc.nextInt();
        m = sc.nextInt();
        sc.nextLine();

        // 2차원 리스트의 맵 정보 입력 받기
        for (int i = 0; i < n ; i++) {
            String str = sc.nextLine();
            for (int j = 0; j < m; i++) {
                graph[i][j] = str.charAt(j) - '0';
            }
        }

        // 모든 노드에 대하여 음료수 채우기
        int result = 0;
        for (int i = 0; i < n; i++){
            for (int j = 0; i< m; j++) {

                if (dfs(i,j)){
                    result += 1;
                }
            }
        }

        System.out.println(result);
        sc.close();
        
    }
    
}
