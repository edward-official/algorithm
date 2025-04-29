import java.awt.*;
import java.io.*;
import java.util.*;


public class Main {
    static class Chapter11 {
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
    }
    public static void main(String[] args) {
        try {
            Chapter11.p24267();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
}