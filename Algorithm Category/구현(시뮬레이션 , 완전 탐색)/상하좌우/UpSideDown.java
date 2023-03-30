import java.util.*;

public class UpSideDown {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        sc.nextLine();
        String[] plan = sc.nextLine().split(" ");
        int[] dx = {0, 0, -1, 1};
        int[] dy = {-1, 1, 0, 0};
        char[] moveTypes = {'L', 'R', 'U', 'D'};
        int x = 1;
        int y = 1;

        for (String p : plan) {
            int nx = -1;
            int ny = -1;
            for (int i = 0; i < 4; i++) {
                if (p.charAt(0) == moveTypes[i]) {
                    nx = x + dx[i];
                    ny = y + dy[i];
                }
            }

            if (nx < 1 || ny < 1 || nx > n || ny > n) continue;
            x = nx;
            y = ny;
        }

        System.out.println(x + " " + y);
        sc.close();
    }

}