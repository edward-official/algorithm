import java.awt.*;
import java.io.*;
import java.util.*;

public class Main {
    private static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
    private static StringTokenizer tokenizer;
    private static StringBuilder builder = new StringBuilder();

    static class P24416 {
        private static int countRecursive, countDynamic;
        private static int recursive(int n) {
            if(n==1 || n==2) {
                countRecursive++;
                return 1;
            }
            return recursive(n-1) + recursive(n-2);
        }
        private static int dynamic(int n) {
            if(n==1) return 1;

            int[] memo = new int[n + 1];
            memo[1] = 1;
            memo[2] = 1;
            for(int index=3; index<=n; index++) {
                memo[index] = memo[index-1] + memo[index-2];
                countDynamic++;
            }
            return memo[n];
        }


        private static void execute() throws IOException {
            countRecursive = 0;
            countDynamic = 0;

            int n = Integer.parseInt(in.readLine());
            recursive(n);
            dynamic(n);
            builder.append(countRecursive).append(" ").append(countDynamic);
            out.write(builder.toString());
            out.flush();
        }
    }

    public static void main(String[] args) {
        try {
            P24416.execute();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
}