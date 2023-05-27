import java.util.*;

class Miro {

	public int solution(int[][] board){
	

        int[] dx = new int[]{-1,0,1,0};
        int[] dy = new int[]{0,1,0,-1};

        
        int[][] ch = new int[7][7];
        
        Queue<int[]> Q = new LinkedList<>();
        ch[0][0] = 1;
        Q.offer(new int[]{0,0});

        int moveCount = 0;

        while(!Q.isEmpty()) {
            int size = Q.size();
            
        
            for(int i = 0; i < size; i++) {
                int[] cur = Q.poll();

                for(int k = 0; k < 4; k++) {
                    int next_x = cur[0] + dx[k];
                    int next_y = cur[1] + dy[k];

                    if (next_x == 6 && next_y == 6) return moveCount + 1;


                    if(next_x >= 0 && next_x <= 6 && next_y >= 0 && next_y <= 6 && ch[next_x][next_y] == 0 && board[next_x][next_y] == 0) {
                        ch[next_x][next_y] = 1;
                        Q.offer(new int[]{next_x, next_y});
                    }
                }
            }
            moveCount++;
            
        }

		
		return -1;
	}
		
	public static void main(String[] args){
		Miro T = new Miro();
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