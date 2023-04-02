import java.util.*;

class Fruit {

    public boolean isMinUnique(int[] fruit) {
        // 우선 최소값 찾기
        int min = Arrays.stream(fruit).min().getAsInt();

        // 최소값이 유니크한지 확인
        int count = 0;
        for (int i = 0; i < fruit.length; i++) {
            if (fruit[i] == min) count++;
        }

        // 1이면 유니크, 그렇지 않으면 유니크하지 않음
        if (count == 1) return true;
        else return false;
    }

    public int getMinIndex(int[] fruit) {
        // 최소값 찾기
        int min = Arrays.stream(fruit).min().getAsInt();

        // 최소값의 index 찾기
        int index = 0;
        for (int i = 0; i < fruit.length; i++) {
            if (fruit[i] == min) {
                index = i;
                break;
            }
        }

        return index;
    }




	public int solution(int[][] fruit){
		int answer = 0;
        int n = fruit.length;
        int[] changed = new int[n];

        for(int i = 0; i < n ; i++){
            // 이미 교환을 했는지 확인
            if(changed[i] == 1) continue;
            // 최소 과일의 개수가 유니크한지 확인하고, 유니크하지 않으면 교환할 의미가 없음
            if(isMinUnique(fruit[i])  == false) continue;

            // 본격적인 비교 시작
            for (int j = i+1; j <n; j++) {
                // 비교 대상도 이미 교환을 했는지 확인
                if(changed[j] == 1) continue;

                // 비교 대상의 최소 과일의 개수가 유니크한지 확인하고, 유니크하지 않으면 교환할 의미가 없음
                if(isMinUnique(fruit[j])  == false) continue;

                // 비교 대상의 교환할 과일 index와 다른지 본격적 확인
                int iFruitMinIndex = getMinIndex(fruit[i]);
                int jFruitMinIndex = getMinIndex(fruit[j]);

                // 1개씩 교환을 하기 위해서 a, b가 서로 다른 index를 가져야 하고, 과일 개수는 0보다 커야 함 (문제에서 0도 포함한다.)
                if ( iFruitMinIndex != jFruitMinIndex && fruit[i][iFruitMinIndex] > 0 && fruit[j][jFruitMinIndex] > 0) {
                    
                    // 자신이 가지고 있는 과일을 내어주고, 받으면서 해당 과일들의 개수가 처음에 제일 작은 과일과 같아야 한다.
                    if(fruit[i][iFruitMinIndex] + 1 <= fruit[i][jFruitMinIndex] - 1 && fruit[j][jFruitMinIndex] + 1 <= fruit[j][iFruitMinIndex] - 1) {
                        fruit[i][iFruitMinIndex] += 1;
                        fruit[i][jFruitMinIndex] -= 1;
                        fruit[j][jFruitMinIndex] += 1;
                        fruit[j][iFruitMinIndex] -= 1;
                        
                        // 최종적으로 교환을 했으므로, 교환 했다는 표시를 해준다.
                        changed[i] = 1;
                        changed[j] = 1;

                        // 제일 번호가 낮은 순부터 교환을 진행 했으면, 더 이상 교환이 불가능
                        break;
                    }
                }


            }
        }


        // 최종적으로 최소한으로 가지고 있는 과일의 개수를 더해준다.
        for(int[] x : fruit){
            answer += Arrays.stream(x).min().getAsInt();
        }
        

		return answer;
	}


	public static void main(String[] args){
		Fruit T = new Fruit();
		System.out.println(T.solution(new int[][]{{10, 20, 30}, {12, 15, 20}, {20, 12, 15}, {15, 20, 10}, {10, 15, 10}}));
		System.out.println(T.solution(new int[][]{{10, 9, 11}, {15, 20, 25}}));	
		System.out.println(T.solution(new int[][]{{0, 3, 27}, {20, 5, 5}, {19, 5, 6}, {10, 10, 10}, {15, 10, 5}, {3, 7, 20}}));
		System.out.println(T.solution(new int[][]{{3, 7, 20}, {10, 15, 5}, {19, 5, 6}, {10, 10, 10}, {15, 10, 5}, {3, 7, 20}, {12, 12, 6}, {10, 20, 0}, {5, 10, 15}}));
	}
}
