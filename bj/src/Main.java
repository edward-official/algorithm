import java.awt.*;
import java.io.*;
import java.lang.annotation.Target;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;


public class Main {
    private static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
    private static StringTokenizer tokenizer;
    private static StringBuilder builder = new StringBuilder();
    static class P24416_2 {
        private static int count1 = 0;
        private static int count2 = 0;
        private static int[] fibonacci;

        private static int fibonacci1(int n) {
            if(n==1 || n==2) {
                count1++;
                return 1;
            }
            return fibonacci1(n-2)+fibonacci1(n-1);
        }
        private static int fibonacci2(int n) {
            fibonacci[1]=1;
            fibonacci[2]=1;
            for(int index=3; index<=n; index++) {
                count2++;
                fibonacci[index] = fibonacci[index-2] + fibonacci[index-1];
            }
            return fibonacci[n];
        }

        static void execute() throws IOException {
            int n = Integer.parseInt(in.readLine());
            fibonacci = new int[n+1];
            fibonacci1(n);
            fibonacci2(n);
            builder.append(count1).append(" ").append(count2);
            out.write(builder.toString());
            out.flush();
        }
    }

    public static void main(String[] args) {
        try {
            P24416_2.execute();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
}