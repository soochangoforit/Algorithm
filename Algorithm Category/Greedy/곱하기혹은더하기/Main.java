package 곱하기혹은더하기;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc  = new Scanner(System.in);
        String str = sc.next();

        int result = str.charAt(0) - '0';
        for (int i = 1; i < str.length(); i++) {
            
            int num = str.charAt(i) - '0';
            if (num <= 1 || result <= 1 ){
                result += num;
            } 
            else {
                result *= num;
            }
        }

        System.out.println(result);
    }
}
