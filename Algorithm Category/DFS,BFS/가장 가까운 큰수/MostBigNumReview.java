import java.util.*;

class MostBigNumReview {

    // 메서드를 분리해서 사용할 때는 변수를 밖에서 선언하고, 메서드 안에서 초기화 진행
    ArrayList<Integer> nums;
    int target;
    int m;
    int[] ch;
    int answer;
    int tmp;
    boolean flag;

	public int solution(int n){
		answer = -1;
        flag = false;
        nums = new ArrayList<>();
        target = n;
        tmp = n;
        

        // n으로 들어온 숫자를 일의 자리부터 차례대로 넣어준다.
        while(tmp > 0) {
            int num = tmp % 10;
            nums.add(num);
            tmp = tmp / 10;
        }

        // 오름 차순 정렬
        nums.sort((a,b) -> a - b);

        m = nums.size();
        ch = new int[m];

        DFS(0,0);

		return answer;
	}

    public void DFS(int L, int number) {

        if(flag) return;

        if(L == m) {
            if(number > target) {
                answer = number;
                flag = true;
            }
        }
        else {
            for (int i = 0; i < m; i++){
                if (ch[i] == 0){
                    ch[i] = 1;
                    DFS(L + 1, number * 10 + nums.get(i));
                    ch[i] = 0;
                }
            }
        }
    }
		
	public static void main(String[] args){
		MostBigNumReview T = new MostBigNumReview();
		System.out.println(T.solution(123));
		System.out.println(T.solution(321));
		System.out.println(T.solution(20573));
		System.out.println(T.solution(27711));
		System.out.println(T.solution(54312));
	}
}