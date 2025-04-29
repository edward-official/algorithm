import java.io.IOException;
import java.util.Scanner;

public class Chapter11 {
    static Scanner in = new Scanner(System.in);

    static void p24262() throws IOException {
        System.out.println("1");
        System.out.println("0");
    }
    static void p24263() throws IOException {
        System.out.println(in.nextInt());
        System.out.println("1");
    }
    static void p24264() throws IOException {
        long n = in.nextLong();
        System.out.println(n*n);
        System.out.println("2");
    }
    static void p24265() throws IOException {
        long n = in.nextLong();
        System.out.println((n-1)*n/2);
        System.out.println(2);
    }
    static void p24266() throws IOException {
        long n = in.nextLong();
        System.out.println(n*n*n);
        System.out.println(3);
    }
    static void p24267() throws IOException {
        long n = in.nextLong();
            /*

            n==3: 1
            n==4: (2+1) + 1 == O(n-1) + (n-2)(n-1)/2
            n==5: (3+2+1) + (2+1) + 1
            n==6: (4+3+2+1) + (3+2+1) + (2+1) + 1

            range: 1 - (n-2)
            n*(n+1)/2
            (n*n + n)/2
            ((n-2)*(n-1)*(2n-3)/6 + (n-2)(n-1)/2)/2
             */
        System.out.println(((n-2)*(n-1)*(2*n-3)/6 + (n-2)*(n-1)/2)/2);
        System.out.println("3");
    }
    static void p24313() throws IOException {
            /*
            positive * x >= positive * x + positive (when the x is smaller, right side is strong)
            if slope c is bigger than slope a1, we can only check n0
            else if slopes of both line are equal, a0 must be smaller than zero
            else if slope c is smaller than slope a1, it's always false

             */
        int a1 = in.nextInt(), a0 = in.nextInt();
        int c = in.nextInt();
        int n0 = in.nextInt();
        if(c>a1) {
            if(c*n0 >= a1*n0+a0) System.out.println("1");
            else System.out.println("0");
        }
        else if(c==a1) {
            if(a0 <= 0) System.out.println("1");
            else System.out.println("0");
        }
        else System.out.println("0");
    }
}
