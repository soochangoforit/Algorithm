import java.util.*;

class ShortTransferPlan {

	public int solution(int[][] routes, int s, int e){
		int answer = 0;

        // key : ��, value = {�ش� ���� ������ �뼱 ��ȣ}
        HashMap<Integer, HashSet<Integer>> graph = new HashMap<>();

        // ��ü �뼱�� ����
        int countOfLine = routes.length;

        // graph ���� ������ ���� ��� �뼱�� ���ϴ��� �� ������ �����Ѵ�.
        // �������� ���ϴ� ���� ��� ���ؼ��� Ư�� ���� ����������, �ش� ���� ���� �뼱�� ��� �� ������ Q�� �־�� �Ѵ�.
        // �׷����� �ش� ���� � �뼱�� ���ϴ��� �˾ƾ� �Ѵ�.
        for(int line = 0; line < countOfLine; line++){
			for(int station : routes[line]){
				graph.putIfAbsent(station, new HashSet<Integer>());
				graph.get(station).add(line);
			}
		}

        // ������ �뼱�� Ÿ�� ����, �ش� �뼱�� ������ üũ�ϴ� �迭
        int[] ch = new int[countOfLine];

        // Q���� �� ���� ������ �뼱�� ��� �� ������ �ִ´�.
        Queue<Integer> Q = new LinkedList<>();
        Q.offer(s);
        int rideOfLine = 0;

        while(!Q.isEmpty()) {
            int Qsize = Q.size();

            for(int i = 0; i < Qsize; i++) {
                int station = Q.poll();

                // start ���� ���� ��� �뼱���� �����´�.
                HashSet<Integer> lines = graph.get(station);

                // �ش� �뼱�� �湮���� ���� ��쿡���� �����Ѵ�.
                for(int line : lines) {
                    if(ch[line] == 0) {
                        ch[line] = 1;
                        // �ش� �뼱�� ���� ��� �� ������ �����´�.
                        for(int nextStation : routes[line]) {
                            // ���� ���� ������ ���
                            if(nextStation == e) {
                                return rideOfLine;
                            }
                            // ���� �������� �������� ���� ���� Q�� �ִ´�.
                            Q.offer(nextStation);
                        }

                    }
                }
            }
            // �ڽ� �� ������ �� ��ȸ�߱⿡, ���� �뼱�� Ÿ���Ѵ�.
            rideOfLine++;
        }
		
        // �ش� ���� �湮�� ���� ���ϴ� ���� -1�� return �Ѵ�.
		return -1;
	}
		
	public static void main(String[] args){
		ShortTransferPlan T = new ShortTransferPlan();
		System.out.println(T.solution(new int[][]{{1, 2, 3, 4, 5, 6, 19}, {2, 7, 8, 13}, {5, 9, 10}, {9, 11, 12, 18}, {13, 14, 15}, {14, 12, 16, 17}}, 1, 12));
		System.out.println(T.solution(new int[][]{{1, 3, 5, 7}, {9, 3, 12}, {6, 5, 8}, {2, 8, 14, 15}, {2, 14, 16}}, 1, 14));
		System.out.println(T.solution(new int[][]{{7, 12},{5, 19},{7, 19},{9, 12, 13},{9, 5, 15}}, 9, 19));
		System.out.println(T.solution(new int[][]{{1, 2, 3, 4, 5},{9, 7, 10},{7, 6, 3, 8}, {5, 11, 8, 12}}, 1, 10));
	}
}