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
    static class P9184 {
        private static int range = 20;
        private static int[][][] memo = new int[range+1][range+1][range+1];
        private static boolean[][][] isMemoValid = new boolean[range+1][range+1][range+1];

        private static int w(int a, int b, int c) {
            int result;
            if(a<=0 || b<=0 || c<=0) return  1;
            else if(a>20 || b>20 || c>20) return w(20,20,20);
            else if(isMemoValid[a][b][c]) return memo[a][b][c];
            else if(a < b && b < c) result = w(a, b, c-1) + w(a, b-1, c-1) - w(a, b-1, c);
            else result = w(a-1, b, c) + w(a-1, b-1, c) + w(a-1, b, c-1) - w(a-1, b-1, c-1);
            isMemoValid[a][b][c] = true;
            memo[a][b][c] = result;
            return result;
        }
        private static void execute() throws IOException {
            int a, b, c;
            while(true) {
                tokenizer = new StringTokenizer(in.readLine());
                a = Integer.parseInt(tokenizer.nextToken());
                b = Integer.parseInt(tokenizer.nextToken());
                c = Integer.parseInt(tokenizer.nextToken());
                if(a==-1 && b==-1 && c==-1) break;
                else {
                    int result = w(a,b,c);
                    builder.append(String.format("w(%d, %d, %d) = %d\n", a,b,c,result));
                }
            }
            out.write(builder.toString());
            out.flush();
        }
    }

    public static void main(String[] args) {
        try {
            P9184.execute();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
}