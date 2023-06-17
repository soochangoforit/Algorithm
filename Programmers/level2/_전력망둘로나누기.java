import java.util.*;

class Solution {

    private List<List<Integer>> adjList = new ArrayList<>();

    public int solution(int n, int[][] wires) {
        int answer = Integer.MAX_VALUE;

        // 1. 양방향 리스트를 의미하는 adjList 초기화
        for(int i = 0; i <= n; i++) {
            adjList.add(new ArrayList<>());
        }

        // 2. 입력 값 wires를 통해서 양방향 매핑
        for(int[] wire : wires) {
            int startPoint = wire[0];
            int endPoint = wire[1];

            adjList.get(startPoint).add(endPoint);
            adjList.get(endPoint).add(startPoint);
        }

        // 3. 입력으로 주어진 wires를 하나씩 순회 하면서 각각의 노드를 root 노드라고 판단하고,
        // 서브 트리에서 노드의 개수를 구한다.
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
