import java.util.*;

public class Combi {
    static int[] pm;
    static int n, m;
    public void DFS(int L) {
        if(L == m) {
            for(int x : pm) System.out.print(x + " ");
            System.out.println();
        }
        else {
            for(int i = 1; i <= n; i++) {
                if (L != 0 && pm[L -1] >= i ) continue;
                pm[L] = i;
                DFS(L+1);
            }
        }

    }

    public static void main (String[] args) {
        Combi T = new Combi();
        Scanner kb = new Scanner(System.in);
        n = kb.nextInt();
        m = kb.nextInt();
        pm = new int[m];
        T.DFS(0);
    }
}
