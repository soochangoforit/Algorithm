import java.util.*;

class IslandWithDFS {

    // 1. �̵� ������ ��ǥ ����
    int[] dx = { -1, 0, 1, 0 };
    int[] dy = { 0, 1, 0, -1 };

    // 2. �湮 ���� ǥ��
    boolean[][] visited;

    // �Է� 2���� �迭
    char[][] map;

    // ���� ũ�⸦ ������ ����Ʈ (�������� ���� �ʿ�)
    List<Integer> islands;
    int ySize;
    int xSize;

    public int[] solution(String[] maps) {
        ySize = maps.length;
        xSize = maps[0].length();

        map = new char[ySize][xSize];

        visited = new boolean[ySize][xSize];

        islands = new ArrayList<>();

        // 3. �Է¿��� �� ���ξ� �Է� �ް�, char �迭�� ��ȯ
        for (int i = 0; i < ySize; i++) {
            map[i] = maps[i].toCharArray();
        }

        // 4. ��ü 2���� �迭�� ��ȸ�ϰ�, ���� ���� ��� DFS ����
        for (int y = 0; y < ySize; y++) {
            for (int x = 0; x < xSize; x++) {
                // ���� ������, ���� �湮���� ���� ���� ���
                if (map[y][x] != 'X' && !visited[y][x]) {
                    int islandSum = dfs(y, x);
                    islands.add(islandSum);
                }
            }
        }

        if (islands.size() == 0) {
            return new int[] { -1 };
        }

        int[] answer = new int[islands.size()];
        for (int i = 0; i < islands.size(); i++) {
            answer[i] = islands.get(i);
        }
        Arrays.sort(answer);
        return answer;
    }

    public int dfs(int y, int x) {
        // dfs Ż�� ���� : ��ü 2���� �迭 map�� �����, ���� �湮���� �ʾҰ�, ���� �ƴ� ���
        if (y < 0 || y >= ySize || x < 0 || x >= xSize || visited[y][x] || map[y][x] == 'X') {
            // �� dfs �� �����Ѵ�.
            return 0;
        } else {
            // ������ �̵��� �� �ִ� ������ �����ϸ�, �湮 ���θ� true�� ����
            visited[y][x] = true;
            int islandSum = Character.getNumericValue(map[y][x]);
            
            for (int i = 0; i < 4; i++) {
                islandSum += dfs(y + dy[i], x + dx[i]);
            }

            // dfs ���� ��, ���� ũ�⸦ �����Ѵ�.
            return islandSum;
        }

    }
}