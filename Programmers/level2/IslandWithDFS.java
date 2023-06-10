import java.util.*;

class IslandWithDFS {

    // 1. 이동 가능한 좌표 설정
    int[] dx = { -1, 0, 1, 0 };
    int[] dy = { 0, 1, 0, -1 };

    // 2. 방문 여부 표시
    boolean[][] visited;

    // 입력 2차원 배열
    char[][] map;

    // 섬의 크기를 저장할 리스트 (오름차순 정렬 필요)
    List<Integer> islands;
    int ySize;
    int xSize;

    public int[] solution(String[] maps) {
        ySize = maps.length;
        xSize = maps[0].length();

        map = new char[ySize][xSize];

        visited = new boolean[ySize][xSize];

        islands = new ArrayList<>();

        // 3. 입력에서 한 라인씩 입력 받고, char 배열로 변환
        for (int i = 0; i < ySize; i++) {
            map[i] = maps[i].toCharArray();
        }

        // 4. 전체 2차원 배열을 순회하고, 섬을 만날 경우 DFS 수행
        for (int y = 0; y < ySize; y++) {
            for (int x = 0; x < xSize; x++) {
                // 섬을 만났고, 아직 방문하지 않은 섬인 경우
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
        // dfs 탈출 조건 : 전체 2차원 배열 map을 벗어나고, 아직 방문하지 않았고, 섬이 아닌 경우
        if (y < 0 || y >= ySize || x < 0 || x >= xSize || visited[y][x] || map[y][x] == 'X') {
            // 현 dfs 를 종료한다.
            return 0;
        } else {
            // 섬으로 이동할 수 있는 조건을 만족하면, 방문 여부를 true로 변경
            visited[y][x] = true;
            int islandSum = Character.getNumericValue(map[y][x]);
            
            for (int i = 0; i < 4; i++) {
                islandSum += dfs(y + dy[i], x + dx[i]);
            }

            // dfs 종료 후, 섬의 크기를 저장한다.
            return islandSum;
        }

    }
}