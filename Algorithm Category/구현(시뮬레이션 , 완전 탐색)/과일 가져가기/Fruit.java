import java.util.*;

class Fruit {

    public boolean isMinUnique(int[] fruit) {
        // �켱 �ּҰ� ã��
        int min = Arrays.stream(fruit).min().getAsInt();

        // �ּҰ��� ����ũ���� Ȯ��
        int count = 0;
        for (int i = 0; i < fruit.length; i++) {
            if (fruit[i] == min) count++;
        }

        // 1�̸� ����ũ, �׷��� ������ ����ũ���� ����
        if (count == 1) return true;
        else return false;
    }

    public int getMinIndex(int[] fruit) {
        // �ּҰ� ã��
        int min = Arrays.stream(fruit).min().getAsInt();

        // �ּҰ��� index ã��
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
            // �̹� ��ȯ�� �ߴ��� Ȯ��
            if(changed[i] == 1) continue;
            // �ּ� ������ ������ ����ũ���� Ȯ���ϰ�, ����ũ���� ������ ��ȯ�� �ǹ̰� ����
            if(isMinUnique(fruit[i])  == false) continue;

            // �������� �� ����
            for (int j = i+1; j <n; j++) {
                // �� ��� �̹� ��ȯ�� �ߴ��� Ȯ��
                if(changed[j] == 1) continue;

                // �� ����� �ּ� ������ ������ ����ũ���� Ȯ���ϰ�, ����ũ���� ������ ��ȯ�� �ǹ̰� ����
                if(isMinUnique(fruit[j])  == false) continue;

                // �� ����� ��ȯ�� ���� index�� �ٸ��� ������ Ȯ��
                int iFruitMinIndex = getMinIndex(fruit[i]);
                int jFruitMinIndex = getMinIndex(fruit[j]);

                // 1���� ��ȯ�� �ϱ� ���ؼ� a, b�� ���� �ٸ� index�� ������ �ϰ�, ���� ������ 0���� Ŀ�� �� (�������� 0�� �����Ѵ�.)
                if ( iFruitMinIndex != jFruitMinIndex && fruit[i][iFruitMinIndex] > 0 && fruit[j][jFruitMinIndex] > 0) {
                    
                    // �ڽ��� ������ �ִ� ������ �����ְ�, �����鼭 �ش� ���ϵ��� ������ ó���� ���� ���� ���ϰ� ���ƾ� �Ѵ�.
                    if(fruit[i][iFruitMinIndex] + 1 <= fruit[i][jFruitMinIndex] - 1 && fruit[j][jFruitMinIndex] + 1 <= fruit[j][iFruitMinIndex] - 1) {
                        fruit[i][iFruitMinIndex] += 1;
                        fruit[i][jFruitMinIndex] -= 1;
                        fruit[j][jFruitMinIndex] += 1;
                        fruit[j][iFruitMinIndex] -= 1;
                        
                        // ���������� ��ȯ�� �����Ƿ�, ��ȯ �ߴٴ� ǥ�ø� ���ش�.
                        changed[i] = 1;
                        changed[j] = 1;

                        // ���� ��ȣ�� ���� ������ ��ȯ�� ���� ������, �� �̻� ��ȯ�� �Ұ���
                        break;
                    }
                }


            }
        }


        // ���������� �ּ������� ������ �ִ� ������ ������ �����ش�.
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
