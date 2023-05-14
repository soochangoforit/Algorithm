import java.util.*;
import java.util.stream.Collectors;

class MostBigNum {
	private static int min;

    public int solution(int n) {
        int[] inputDigits = intToDigitsArray(n);
        boolean[] visited = new boolean[inputDigits.length];
        min = Integer.MAX_VALUE;
        dfs(inputDigits, visited, 0, "");
        return min == Integer.MAX_VALUE ? -1 : min;
    }

    private void dfs(int[] inputDigits, boolean[] visited, int depth, String current) {
        if (depth == inputDigits.length) {
            int num = Integer.parseInt(current);
            if (num > digitsArrayToInt(inputDigits) && num < min) {
                min = num;
				return;
            }
        }

        for (int i = 0; i < inputDigits.length; i++) {
            if (!visited[i]) {
                visited[i] = true;
                dfs(inputDigits, visited, depth + 1, current + inputDigits[i]);
                visited[i] = false;
            }
        }
    }

    private int[] intToDigitsArray(int n) {
        return Arrays.stream(String.valueOf(n).split(""))
                .mapToInt(Integer::parseInt)
                .toArray();
    }

    private int digitsArrayToInt(int[] digits) {
        return Integer.parseInt(Arrays.stream(digits)
                .mapToObj(String::valueOf)
                .collect(Collectors.joining("")));
    }
	


		
	public static void main(String[] args){
		MostBigNum T = new MostBigNum();
		System.out.println(T.solution(123));
		System.out.println(T.solution(321));
		System.out.println(T.solution(20573));
		System.out.println(T.solution(27711));
		System.out.println(T.solution(54312));
	}
}