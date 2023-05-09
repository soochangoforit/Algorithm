

import java.util.*;

class Nodea {
        int data;
        Nodea lt, rt;

        public Nodea(int val) {
            data = val;
            lt=rt=null;
        }
}

public class TreeWithBfs {



    Nodea root;

    public void BFS(Nodea root) {
        Queue<Nodea> Q = new LinkedList<>();
        Q.offer(root);
        int L = 0;

        while(!Q.isEmpty()) {
            int len = Q.size();
            System.out.print(L + " : ");

            for (int i = 0 ; i < len ; i++) {
                Nodea cur = Q.poll();
                System.out.print(cur.data + " ");
                
                if (cur.lt != null) Q.offer(cur.lt);
                if (cur.rt != null) Q.offer(cur.rt);
            }

            L++;
            System.out.println();
        }
    }

    public static void main(String args[]) {
        TreeWithBfs tree = new TreeWithBfs();
        tree.root = new Nodea(1);
        tree.root.lt = new Nodea(2);
        tree.root.rt = new Nodea(3);
        tree.root.lt.lt = new Nodea(4);
        tree.root.lt.rt = new Nodea(5);
        tree.root.rt.lt = new Nodea(6);
        tree.root.rt.rt = new Nodea(7);
        tree.BFS(tree.root);

    }
    
}
