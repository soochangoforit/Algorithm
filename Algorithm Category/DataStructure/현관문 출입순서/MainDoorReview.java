import java.util.*;

class MainDoorReview {
	public int[] solution(int[] arrival, int[] state){
		

        
        // ������ �������� �̿��� ����
        int prev = 1;
        // ������ �̿��Ϸ��� ��� ��
        int n = arrival.length;
        int[] answer = new int[n];

        // ������ ��⿭
        Queue<Integer> exit = new LinkedList<>();
        // ������ ��⿭
        Queue<Integer> enter = new LinkedList<>();

        // cntOfEscape �� for���� �����ϱ� ���� �ʿ��� ���� (t�� �ð�, human�� ��� ��ȣ, cntOfEscape �� ����� Ż���� Ƚ���� �ǹ��Ѵ�. n ������(������)��ŭ Ż�������� for�� �����Ѵ�.)
        // t �ð��� �����鼭, arrival �迭�� ��ȸ�� index �� �ʿ��ϴ� => human (t �ʿ� ���ÿ� �������� ������ ������� ã�� ���� index)
        // t�� human �� ��� �ؼ� ������ų �����̱⿡, cntOfEscape ������ ���� for���� Ż���ϰ��� �Ѵ�.
        for(int t = 0, human = 0 , cntOfEscape = 0 ;  ; t++ ){

            
            // ���� t �ð��� ������ ������ arrival[human] ���� �ð����� ���� �̸����
            // �ð��� ���� ����� �������� ������ �ð����� ���� �̵��Ѵ�.
            // �׸��� t �ð��� �̵��ϰ� ������ �� �ٷ� 1�� ���� �������� �̿��� ����� ���⿡ prev 1�� ����
            if(enter.isEmpty() && exit.isEmpty() && t < arrival[human]){
                // t�� ���� �������� �̿��� ������� �Ŀ� �پ���� �� �ִ� ���� ������, "��⿭�� �ƹ��� ������� �����ϴ�"
                // ���� ��⿭�� �������� �ִ� ���·� �Ŀ� �ǳʶٸ� �̸� ��⿭�� �ִ� ����� �������� �̿��� �ð� t�� answer�� ������ �� �߸��� ��� ���� ���� �ȴ�.
                // ����, enter.isEmpty() , exit.isEmpty()�� �ݵ�� �ʿ��ϴ�.
                t = arrival[human];
                prev = 1;
            }

            
            // t �ð��� ���� enter Ȥ�� exit�� ���� �־�� �Ѵ�.
            // human ���� ��� ������Ű��, indexOutOfBound ������ �߻��� �� �ֱ⿡ human < n ������ �־��ش�.
            while(human < n && arrival[human] <= t) {
                // ���� ��(t)�� �Ѿ�� ���� ��� index�� �������� t �ð��� ������ �ٸ� ����� ã�´�.
                if(state[human] == 1){
                    exit.offer(human);
                    human++;
                } 

                else{
                    enter.offer(human);
                    human++;
                }
            }

            // ���� ������ ����� �̿��� (1�ʴ� �Ѹ��� �̿밡��)
            if(prev == 1){

                // ���� ���� ������ ����� �־��⿡, ���� ���ʵ� ������ ����� ���� �켱�� 
                // �������� �ϴ� ����� ������
                if(!exit.isEmpty()){
                    int goingToExitHuman = exit.poll();
                    // t �ʿ� �����ٴ� �ǹ̷� ���� �־��ش�.
                    answer[goingToExitHuman] = t;

                    // ���� �̿��� ����� ���� ����̶� prev ������ �ٲ��ش�.
                    prev = 1;
                }
                // ���� ������ ����� �̿�������, ���� �������� �ϴ� ����� ���⿡ ������ ��� �켱�� (1�ʴ� �Ѹ��� �̿밡��)
                else{
                    int goingToEnterHuman = enter.poll();
                    // t �ʿ� ���Դٴ� �ǹ̷� ���� �־��ش�.
                    answer[goingToEnterHuman] = t;

                    // ���� �̿��� ����� ���� ����̶� prev ���� �ٲ��ش�.
                    prev = 0;
                }
            }
            // ���� ������ ����� �̿��� (1�ʴ� �Ѹ��� �̿밡��)
            else if(prev == 0){

                // ���� ������ ����� �־��⿡, ���� ���ʵ� ������ ����� ���� �켱�� (1�ʴ� �Ѹ��� �̿밡��)
                if(!enter.isEmpty()) {
                    int goingToEnterHuman = enter.poll();
                    answer[goingToEnterHuman] = t;
                    // ���� �̿��� ����� ���� ����̶� prev ������ �ٲ��ش�.
                    prev = 0;
                }
                // ���� ������ ����� �̿�������, ���� �������� ����� ���⿡ ������ ��� �켱�� (1�ʴ� �Ѹ��� �̿밡��)
                else {
                    int goingToExitHuman = exit.poll();
                    answer[goingToExitHuman] = t;
                    prev = 1;
                }
            }

            // for�� Ż���� ���� ���� (cntOfExcape => Ż���� Ƚ���� �����ϰ� �ִ�. 1�ʿ� �ѻ���� Ż���Ѵ�.)
            cntOfEscape++;
            if(cntOfEscape == n) break;
            
        }

		
		return answer;
	}
		
	public static void main(String[] args){
		MainDoorReview T = new MainDoorReview();
		System.out.println(Arrays.toString(T.solution(new int[]{0, 1, 1, 1, 2, 3, 8, 8}, new int[]{1, 0, 0, 1, 0, 0, 1, 0})));
		System.out.println(Arrays.toString(T.solution(new int[]{3, 3, 4, 5, 5, 5}, new int[]{1, 0, 1, 0, 1, 0})));
		System.out.println(Arrays.toString(T.solution(new int[]{2, 2, 2, 3, 4, 8, 8, 9, 10, 10}, new int[]{1, 0, 0, 0, 1, 1, 0, 1, 1, 0})));
	}
}