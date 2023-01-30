import java.util.Arrays;
import java.util.Scanner;

public class Main {
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        int[] adventurer = new int[n];

        for (int i = 0; i < n; i++) {
            adventurer[i] = sc.nextInt();
        }

        // 오름차순 정렬
        Arrays.sort(adventurer);

        // 그룹에 포함된 사람의 수
        int count = 0;

        // 그룹의 결과
        int result = 0;
        for (int adv : adventurer) {
            count += 1;
            if (count >= adv) {
                result += 1;
                count = 0;
            }
        }

        System.out.println(result);
        
    }
}