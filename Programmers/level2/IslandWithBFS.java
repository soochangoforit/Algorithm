

import java.util.*;

public class IslandWithBFS {

    int[] dx = { -1, 0, 1, 0 };
    int[] dy = { 0, 1, 0, -1 };

    boolean[][] visited;

    char[][] map;

    List<Integer> islands;

    public int[] solution(String[] maps) {
        int ySize = maps.length;
        int xSize = maps[0].length();

        map = new char[ySize][xSize];
        visited = new boolean[ySize][xSize];

        islands = new ArrayList<>();

        for (int y = 0; y < ySize; y++) {
            map[y] = maps[y].toCharArray();
        }

        for (int y = 0; y < ySize; y++) {
            for (int x = 0; x < xSize; x++) {
                if (map[y][x] != 'X' && !visited[y][x]) {

                    Queue<int[]> Q = new LinkedList<>();
                    Q.add(new int[] {y,x});
                    visited[y][x] = true;

                    int islandSum = Character.getNumericValue(map[y][x]);

                    while (!Q.isEmpty()) {
                        int[] cur = Q.poll();

                        int cur_y = cur[0];
                        int cur_x = cur[1];

                        for (int i = 0; i < 4; i++) {
                            int ny = cur_y + dy[i];
                            int nx = cur_x + dx[i];

                            if (ny >= 0 && ny < ySize && nx >= 0 && nx < xSize && visited[ny][nx] == false && map[ny][nx] != 'X') {
                                visited[ny][nx] = true;
                                Q.add(new int[] {ny, nx});
                                islandSum += Character.getNumericValue(map[ny][nx]);
                            }
                        }
                    }
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
}