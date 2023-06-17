import java.util.*;

class Solution {

    private List<List<Integer>> adjList = new ArrayList<>();

    public int solution(int n, int[][] wires) {
        int answer = Integer.MAX_VALUE;

        // 1. ����� ����Ʈ�� �ǹ��ϴ� adjList �ʱ�ȭ
        for(int i = 0; i <= n; i++) {
            adjList.add(new ArrayList<>());
        }

        // 2. �Է� �� wires�� ���ؼ� ����� ����
        for(int[] wire : wires) {
            int startPoint = wire[0];
            int endPoint = wire[1];

            adjList.get(startPoint).add(endPoint);
            adjList.get(endPoint).add(startPoint);
        }

        // 3. �Է����� �־��� wires�� �ϳ��� ��ȸ �ϸ鼭 ������ ��带 root ����� �Ǵ��ϰ�,
        // ���� Ʈ������ ����� ������ ���Ѵ�.
        for(int[] wire : wires) {
            int root1 = wire[0];
            int root2 = wire[1];

            int subNodesFromRoot1 = dfsNodeCount(root1, root2);
            int subNodesFromRoot2 = dfsNodeCount(root2, root1);

            answer = Math.min(answer, Math.abs(subNodesFromRoot1 - subNodesFromRoot2));
            
        }


        return answer;
    }

    private int dfsNodeCount(int current, int parent) {
        int count = 1;

        for(int neighbor : adjList.get(current)) {
            if (neighbor != parent) {
                count += dfsNodeCount(neighbor, current);
            }
        }

        return count;
    }
}
