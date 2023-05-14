import java.util.*;
class MostBigNumRefactor {
	int answer, target, m;
	ArrayList<Integer> nums;
	int[] ch;
	boolean flag;

	public int solution(int n){
		answer = 0;
		flag = false; // 답을 찾았는지 여부를 나타내는 flag
		nums = new ArrayList<>(); // input으로 들어온 숫자들을 각 자리 수로 분열하여 저장할 ArrayList
		target = n; // input으로 들어온 숫자를 저장할 변수
		int tmp = n;

		// 처음으로 들어온 숫자를 각자리 수 분열
		while(tmp > 0){
			int t = tmp % 10;
			nums.add(t);
			tmp = tmp / 10;
		}

		// nums에는 input으로 들어온 숫자들이 오름차순으로 정렬된다.
		// 자리 수 만큼 만들어지는 숫자가 오름차순으로 순열을 만들기 위해 정렬
		nums.sort((a, b) -> a - b);

		// 방문한 의미의 ch 배열
		m = nums.size();
		ch = new int[m];

		DFS(0, 0);

		if(flag == false) return -1;

		return answer;
	}

	public void DFS(int L, int number){
		// 답을 찾아서 flag가 true인 경우, dfs를 탈출하기 위해
		if(flag) return;

		if(L == m){
			if(number > target){
				answer = number;
				flag = true;
			}
		}

		else{
			for(int i = 0; i < m; i++){
				// 중복으로는 숫자를 만들 수 없기에 ch[i]가 0일 때만
				if(ch[i] == 0){
					ch[i] = 1;
					// DFS 함수에서 number를 넣을 땐 뒤에 들어오는 숫자가 
					DFS(L + 1, number * 10 + nums.get(i));
					ch[i] = 0;
				}
			}
		}

	}
		
	public static void main(String[] args){
        MostBigNumRefactor T = new MostBigNumRefactor();
		System.out.println(T.solution(123));
		System.out.println(T.solution(321));
		System.out.println(T.solution(20573));
		System.out.println(T.solution(27711));
		System.out.println(T.solution(54312));
	}
}