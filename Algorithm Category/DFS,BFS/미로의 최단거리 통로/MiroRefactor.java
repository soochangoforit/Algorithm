import java.util.*;

class MiroRefactor {

	public int solution(int[][] board){
	

        int[] dx = new int[]{-1,0,1,0};
        int[] dy = new int[]{0,1,0,-1};

        
        int[][] dist = new int[7][7];
        
        Queue<int[]> Q = new LinkedList<>();
        Q.offer(new int[]{0,0});

        int moveCount = 0;

        while(!Q.isEmpty()) {
            int size = Q.size();
            
            moveCount++;
        
            for(int i = 0; i < size; i++) {
                int[] cur = Q.poll();

                for(int k = 0; k < 4; k++) {
                    int next_x = cur[0] + dx[k];
                    int next_y = cur[1] + dy[k];

                    if(next_x >= 0 && next_x <= 6 && next_y >= 0 && next_y <= 6 && board[next_x][next_y] == 0) {
                        board[next_x][next_y] = 1;
                        Q.offer(new int[]{next_x, next_y});
                        dist[next_x][next_y] = moveCount;
                    }
                }
            }
        }

		if (dist[6][6] == 0) return -1;
        else return dist[6][6];
	}
		
	public static void main(String[] args){
		MiroRefactor T = new MiroRefactor();
		int[][] arr={{0, 0, 0, 0, 0, 0, 0}, 
			{0, 1, 1, 1, 1, 1, 0}, 
			{0, 0, 0, 1, 0, 0, 0}, 
			{1, 1, 0, 1, 0, 1, 1}, 
			{1, 1, 0, 1, 0, 0, 0}, 
			{1, 0, 0, 0, 1, 0, 0}, 
			{1, 0, 1, 0, 0, 0, 0}};
		System.out.println(T.solution(arr));
	}
}