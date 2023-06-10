package Programmers.level2;

import java.util.*;

class Solution {

    int[] dx = {-1, 0, 1, 0};
    int[] dy = {0, 1, 0, -1};
    boolean[][] visited;
    char[][] map;
    List<Integer> islands;
    int ySize;
    int xSize;

    public int[] solution(String[] maps) {
        ySize= maps.length;
        xSize = maps[0].length();
        
        map = new char[ySize][xSize];
        visited = new boolean[ySize][xSize];
        islands = new ArrayList<>();

        // 1. map을 2차원 배열로 변환
        for(int y = 0; y < ySize; y++) {
            map[y] = maps[y].toCharArray();
        }

        // 2. map을 순회하면서 섬을 찾는다.
        for(int y = 0; y < ySize; y++) {
            for(int x = 0; x < xSize; x++) {
                if(map[y][x] != 'X' && !visited[y][x]) {
                    bfs(y, x);
                }
            }
        }



        if(islands.size() == 0) {
            return new int[]{-1};
        }

        int[] answer = new int[islands.size()];
        for(int i = 0; i < islands.size(); i++) {
            answer[i] = islands.get(i);
        }
        Arrays.sort(answer);
        return answer;
    }

    private void bfs(int y, int x) {
        Queue<int[]> Q = new LinkedList<>();
        Q.offer(new int[]{y,x});
        visited[y][x] = true;
        int islandSum = Character.getNumericValue(map[y][x]);

        while(!Q.isEmpty()) {
            int[] cur = Q.poll();
            for(int i = 0; i < 4; i++) {
                int ny = cur[0] + dy[i];
                int nx = cur[1] + dx[i];
                if(nx >= 0 && nx < xSize && ny >= 0 && ny < ySize && !visited[ny][nx] && map[ny][nx] != 'X') {
                    Q.add(new int[]{ny, nx});
                    visited[nx][ny] = true;
                    islandSum += Character.getNumericValue(map[ny][nx]);
                }
            }
        }
        islands.add(islandSum);
    }



}