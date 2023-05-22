import java.util.*;

class BadukReview {
    int answer;
    int[] ch;
    int diff;
    int size;

	public int solution(int[][] cans){
		answer = 0;
        size = cans.length;

        ch = new int[size];
        diff = Integer.MAX_VALUE;

        DFS(0,0,cans);
        answer = diff;
		
		return answer;
	}

    public void DFS(int Level, int start, int[][] cans) {
        if(Level == size / 2) {
            int whiteSum = 0;
            int blackSum = 0;

            for(int i = 0; i < size; i++){
                if(ch[i] == 1) {
                    whiteSum += cans[i][0];
                }
                else {
                    blackSum += cans[i][1];
                }
            } 
            
            diff = Math.min(diff, Math.abs(whiteSum - blackSum));
        


        }
        else {
            for(int i = start; i < size; i++) {
              
                    ch[i] = 1;
                    DFS(Level +1, i +1, cans);
                    ch[i] = 0;
         
            }
        }

    }

		
	public static void main(String[] args){
		BadukReview T = new BadukReview();
		System.out.println(T.solution(new int[][]{{87, 84}, {66, 78}, {94, 94}, {93, 87}, {72, 92}, {78, 63}}));
		System.out.println(T.solution(new int[][]{{10, 20}, {15, 25}, {35, 23}, {55, 20}}));
		System.out.println(T.solution(new int[][]{{11, 27}, {16, 21}, {35, 21}, {52, 21}, {25, 33},{25, 32}, {37, 59}, {33, 47}}));
	}
}