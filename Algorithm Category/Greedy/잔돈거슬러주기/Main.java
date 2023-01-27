package 잔돈거슬러주기;
 public class Main {

    public static void main(String[] args) {
        int n = 1260;
        int cnt = 0;

        int[] coinTypes = {500,100,50,10};

        for (int coin : coinTypes) {
            cnt += n / coin; // 몫을 넣어준다.
            n %= coin; // 나머지를 넣어준다.
        }

        System.out.println(cnt);
    }
 }