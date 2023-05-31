import java.util.*;

class MiroReview {
	public int solution(int[][] board){
		int answer = 0;

        int[] dy = {-1,0,1,0};
        int[] dx = {0,1,0,-1};

        int[][] ch = new int[7][7];

        Queue<int[]> Q = new LinkedList<>();
        Q.offer(new int[]{0,0});
        ch[0][0] = 1;

        int moveCount = 0;

        while(!Q.isEmpty()) {
            int Qsize = Q.size();

            for(int i = 0; i < Qsize; i++) {
                int[] cur = Q.poll();

                for(int k = 0; k < 4; k++) {
                    int ny = cur[0] + dy[k];
                    int nx = cur[1] + dx[k];

                    if(ny == 6 && nx == 6) return moveCount + 1;

                    if(ny >= 0 && ny < 7 && nx >= 0 && nx < 7 && ch[ny][nx] == 0) {
                        ch[ny][nx] = 1;
                        Q.offer(new int[]{ny,nx});
                    }
                }
            }
            moveCount++;
        }

		
		return -1;
	}
		
	public static void main(String[] args){
		MiroReview T = new MiroReview();
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