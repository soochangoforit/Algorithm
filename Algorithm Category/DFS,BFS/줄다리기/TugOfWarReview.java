import java.util.*;

class TugOfWarReview {
	int answer;
    int[][] relation;
    int[] check;
    Stack<Integer> pm;

	public int solution(int[][] fight) {
		answer = 0;
        relation = new int[8][8];
        pm = new Stack<>();
        
        for(int[] f : fight){
            relation[f[0]][f[1]] = 1;
            relation[f[1]][f[0]] = 1;
        }
        
        check = new int[8];
        
        DFS(0);
        
        return answer;
	}

    
	public void DFS(int L) {
        if(L == 7) {
            answer++;
        }
        else {
            for(int i = 1; i < 8; i++) {
                if(!pm.isEmpty() && relation[pm.peek()][i] == 1) continue;
                if(check[i] == 0) {
                    check[i] = 1;
                    pm.push(i);
                    DFS(L + 1);
                    pm.pop();
                    check[i] = 0;
                }
            }

        }

		

	}
		
	public static void main(String[] args){
		TugOfWarReview T = new TugOfWarReview();
		System.out.println(T.solution(new int[][]{{1, 3}, {5, 7}, {4, 2}}));
		System.out.println(T.solution(new int[][]{{3, 2}, {3, 5}, {5, 2}, {7, 3}}));
		System.out.println(T.solution(new int[][]{{1, 2}, {1, 5}, {1, 7}, {1, 3}}));
		System.out.println(T.solution(new int[][]{{1, 7}}));
		System.out.println(T.solution(new int[][]{{1, 2}, {2, 3}, {3, 4}, {4, 5}, {5, 6}, {6, 7}}));
	}
}