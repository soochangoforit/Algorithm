import java.util.*;

class Recursive {

    public void DFS(int n) {
        if (n == 0) return;
        else {
            DFS(n -1);
            System.out.print(n + " ");
        }
    }

    public static void main(String[] args) {
        Recursive T = new Recursive();
        T.DFS(3);
    }
}