import java.util.*;

public class DoublePermutation {
    static int[] pm;
    static int n, m;
    public void DFS(int L) {
        if(L== m) {
            // Ż�� ����
            for(int x : pm) System.out.print(x + " ");
            System.out.println();
        }
        else {
            for(int i = 1; i <= n; i++) {
                pm[L] = i;
                DFS(L+1);
            }
        }


    }

    public static void main(String[] args){
        DoublePermutation T = new DoublePermutation();
        Scanner kb = new Scanner(System.in);
        n = kb.nextInt();
        m = kb.nextInt();
        pm = new int[m];
        T.DFS(0);
    }
}
