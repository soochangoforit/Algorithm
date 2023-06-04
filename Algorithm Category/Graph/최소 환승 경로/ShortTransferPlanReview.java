import java.util.*;

class ShortTransferPlanReview {
	public int solution(int[][] routes, int s, int e){
		int answer = 0;
    
        // key : ��, value : {�ش� ���� ���� �뼱}
        HashMap<Integer, HashSet<Integer>> liens = new HashMap<>();

        // �ʱ� �ڷ� ���� ����
        for(int line = 0; line < routes.length; line++){
            for(int station : routes[line]) {
                liens.putIfAbsent(station, new HashSet<>());
                liens.get(station).add(line);
            }
        }

        Queue<Integer> Q = new LinkedList<>();
        Q.offer(s);

        int[] ch = new int[routes.length];

        int rideOfSubway = 0;

        while(!Q.isEmpty()) {
            int Qsize = Q.size();

            // Q �����ŭ �ݺ�
            for(int i = 0; i < Qsize; i++) {
                int curStation = Q.poll();

                // ���� curStation ���� ���� ��� �뼱 ������ �����´�.
                HashSet<Integer> curLine = liens.get(curStation);

                // curStation�� ���� ���� ��� �뼱 ������ ��ȸ�Ѵ�.
                for(int line : curLine) {

                    // �ش� �뼱�� ���� Q�� ���� ���� ��쿡���� �����Ѵ�.
                    if (ch[line] == 0) {
                        // �� Q�� �ְ�, �ش� �뼱�� Q�� �����ν� ���� �� �̻� �湮���� �ʱ� ���� check ���ش�
                        ch[line] = 1;

                        // curStation�� ���� ��� �뼱�� �ִ� ������ Q�� �־��ش�.
                        for(int station : routes[line]) {

                            // ����, Q�� �ִµ� end�� ���� ���̶�� ���� return �Ѵ�.
                            if(station == e) return rideOfSubway;

                            Q.offer(station);
                        }

                    }
                }

            }
            rideOfSubway++;
        }

		
		return -1;
	}
		
	public static void main(String[] args){
		ShortTransferPlanReview T = new ShortTransferPlanReview();
		System.out.println(T.solution(new int[][]{{1, 2, 3, 4, 5, 6, 19}, {2, 7, 8, 13}, {5, 9, 10}, {9, 11, 12, 18}, {13, 14, 15}, {14, 12, 16, 17}}, 1, 12));
		System.out.println(T.solution(new int[][]{{1, 3, 5, 7}, {9, 3, 12}, {6, 5, 8}, {2, 8, 14, 15}, {2, 14, 16}}, 1, 14));
		System.out.println(T.solution(new int[][]{{7, 12},{5, 19},{7, 19},{9, 12, 13},{9, 5, 15}}, 9, 19));
		System.out.println(T.solution(new int[][]{{1, 2, 3, 4, 5},{9, 7, 10},{7, 6, 3, 8}, {5, 11, 8, 12}}, 1, 10));
	}
}