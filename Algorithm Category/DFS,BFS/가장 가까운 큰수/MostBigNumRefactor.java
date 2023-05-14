import java.util.*;
class MostBigNumRefactor {
	int answer, target, m;
	ArrayList<Integer> nums;
	int[] ch;
	boolean flag;

	public int solution(int n){
		answer = 0;
		flag = false; // ���� ã�Ҵ��� ���θ� ��Ÿ���� flag
		nums = new ArrayList<>(); // input���� ���� ���ڵ��� �� �ڸ� ���� �п��Ͽ� ������ ArrayList
		target = n; // input���� ���� ���ڸ� ������ ����
		int tmp = n;

		// ó������ ���� ���ڸ� ���ڸ� �� �п�
		while(tmp > 0){
			int t = tmp % 10;
			nums.add(t);
			tmp = tmp / 10;
		}

		// nums���� input���� ���� ���ڵ��� ������������ ���ĵȴ�.
		// �ڸ� �� ��ŭ ��������� ���ڰ� ������������ ������ ����� ���� ����
		nums.sort((a, b) -> a - b);

		// �湮�� �ǹ��� ch �迭
		m = nums.size();
		ch = new int[m];

		DFS(0, 0);

		if(flag == false) return -1;

		return answer;
	}

	public void DFS(int L, int number){
		// ���� ã�Ƽ� flag�� true�� ���, dfs�� Ż���ϱ� ����
		if(flag) return;

		if(L == m){
			if(number > target){
				answer = number;
				flag = true;
			}
		}

		else{
			for(int i = 0; i < m; i++){
				// �ߺ����δ� ���ڸ� ���� �� ���⿡ ch[i]�� 0�� ����
				if(ch[i] == 0){
					ch[i] = 1;
					// DFS �Լ����� number�� ���� �� �ڿ� ������ ���ڰ� 
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