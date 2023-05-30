import java.util.*;

class Solution {

	public int solution(int[][] board){
		int answer = 0;

        int[] dx = {-1, 0, 1, 0};
        int[] dy = {0, 1, 0, -1};

        int targetNumber = 3;

        int humanY = 0;
        int humanX = 0;


        // board의 가로, 세로 길이
        int ySize = board.length;
        int xSize = board[0].length;

        // 사람의 좌표 찾기
        for(int y = 0; y < ySize; y++) {
            for(int x = 0; x < xSize; x++) {
                if(board[y][x] == 2){
                    humanY = y;
                    humanX = x;
                }
            }
        }

        // 초기 좌표로 사람의 좌표를 넣는다. (처음에는 산딸기를 갖고 있지 않아서 0으로 넣어준다.)
        Queue<int[]> Q = new LinkedList<>();
        Q.offer(new int[]{humanY, humanX, 0});
        int moveCount = 0;


        while(!Q.isEmpty()){
            int Qsize = Q.size();

            for(int i = 0; i < Qsize; i++) {
                int[] cur = Q.poll();
                
                for(int dir = 0; dir < 4; dir++) {
                    int ny = cur[0] + dy[dir];
                    int nx = cur[1] + dx[dir];
                    int hasFruit = cur[2];

                    // 우선 움직일 수 있는 좌표면 이동을 하고자 한다.
                    if(ny >= 0 && ny < ySize && nx >= 0 && nx < xSize && board[ny][nx] != 1) {

                        // cur 좌표 기준으로 다음 ny, nx가 산딸기를 가지고 있고, 기사를 만났을 경우
                        if(board[ny][nx] == 3 && hasFruit > 0) {
                            // 현재는 cur 기준이지만, 다음 좌표인 ny nx 에 대해 조건을 만족하기에 +1을 해준다.
                            answer = moveCount + 1;
                            return answer;
                        }

                        // 다음 좌표가 기사는 아니고, 산딸기를 가질 수 있는 경우 
                        if(board[ny][nx] == 4) {
                            // 산딸기를 가졌기에, hasFruit를 1로 바꿔준다.
                            Q.offer(new int[]{ny, nx, 1});
                            // 산딸기를 가져갔으니, 해당 좌표는 0으로 바꿔준다.
                            // 만약 0으로 바꿔주지 않으면 무한루프에 빠질 수 있다.
                            // 산딸기를 가져가기 위해 위로 올라갔다가 다시 내려왔는데, 또 위에 산딸기가 있어 또 올라가는 무한루프에 빠질 수 있다.
                            // 또한 가장 짧은 거리로 산딸기를 찾아서 기사를 만나야 하는데 아무런 방문 배열을 활용하고 있지 않다.
                            // 방문 배열을 활용하지 않아야지, 산딸기를 얻기 위해 지나간 길을 돌아오는 길로써 접근할 수 있다.
                            // 그리고 산딸기를 얻기 위해 빙~ 돌아서 2번 찾아서 기사를 찾는 경우가 분명 발생하지만,
                            // 이미 하나라도 산딸기를 얻고 기사를 방문하는 경우 이른 return을 통해서 바로 짧은 거리를 찾을 수 있다.
                            board[ny][nx] = 0;
                        } else {
                            // 다음 좌표가 기사도 아니고, 산딸기도 없는 경우 이동을 할 수 있고, 현재 산딸기를 가지고 있는 상태로 접근한다.
                            Q.offer(new int[]{ny, nx, hasFruit});
                        }
                    
                    }
                }
            }
            // 한번 이동을 했기에 moveCount를 증가시켜준다. root 노드에서 자식 노드들이 해당 ++moveCount를 지나고 나서 Q에서 뽑아진다.
            // 이렇게 하면 cur 기준으로 if문을 통해 return 할 경우 바로 return moveCount 해주면 된다.
            moveCount++;
        }
		
		return answer;
	}
		
	public static void main(String[] args){
		Solution T = new Solution();
		System.out.println(T.solution(new int[][]{{4, 1, 0, 0, 0, 0, 1, 0},
			{0, 0, 0, 1, 0, 1, 0, 0}, 
			{0, 2, 1, 1, 3, 0, 4, 0},
			{0, 0, 0, 4, 1, 1, 1, 0}}));
		System.out.println(T.solution(new int[][]{{3, 0, 0, 0, 1, 4, 4, 4}, 
			{0, 1, 1, 0, 0, 0, 1, 0}, 
			{0, 1, 4, 0, 1, 0, 0, 0}, 
			{0, 0, 0, 1, 0, 0, 0, 0}, 
			{1, 0, 1, 0, 0, 1, 1, 0}, 
			{4, 0, 0, 0, 1, 0, 0, 0}, 
			{4, 1, 0, 0, 1, 0, 0, 0}, 
			{4, 0, 0, 0, 0, 0, 1, 2}}));
		System.out.println(T.solution(new int[][]{{4, 1, 0, 1, 0}, 
			{0, 1, 0, 1, 0}, 
			{0, 0, 2, 3, 4}, 
			{0, 1, 0, 1, 0}}));
	}
}