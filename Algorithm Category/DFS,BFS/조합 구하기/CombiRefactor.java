import java.util.*;

public class CombiRefactor {
    static int[] pm;
    static int n, m;

    public void DFS(int L, int s) {
        if(L == m) {
            for(int x : pm) System.out.print(x + " ");
            System.out.println();
        }
        else {
            for(int i = s; i <= n; i++) {
                pm[L] = i;
                DFS(L+1, i+1);
            }
        }

    }

    public static void main (String[] args) {
        CombiRefactor T = new CombiRefactor();
        Scanner kb = new Scanner(System.in);
        n = kb.nextInt();
        m = kb.nextInt();
        pm = new int[m];
        T.DFS(0, 1);
    }
    
}
