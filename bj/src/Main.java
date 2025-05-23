import java.awt.*;
import java.io.*;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;


public class Main {
    static class Chapter21 {
        static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
        static StringBuilder builder = new StringBuilder();
        static StringTokenizer tokenizer;

        static class P24416 {
            static int count1 = 0, count2 = 0;
            static int fibonacciRecursive(int n) {
                if(n==1 || n==2) {
                    count1++;
                    return 1;
                }
                return fibonacciRecursive(n-1)+fibonacciRecursive(n-2);
            }
            static int fibonacciDynamic(int n) {
                HashMap<Integer, Integer> fibonacci = new HashMap<>();
                fibonacci.put(1,1);
                fibonacci.put(2,1);
                for(int key=3; key<=n; key++) {
                    fibonacci.put(key, fibonacci.get(key-1)+fibonacci.get(key-2));
                    count2++;
                }
                return fibonacci.get(n);
            }

            static void run() throws IOException {
                final int n = Integer.parseInt(in.readLine());
                fibonacciRecursive(n);
                fibonacciDynamic(n);

                builder.append(count1).append(" ").append(count2);
                out.write(builder.toString());
                out.flush();
            }
        }
        static class P9184 {
            static int size = 21;
            static int[][][] memoization = new int[size][size][size];

            static int w(int a, int b, int c) {
                int result;

                if(a<=0 || b<=0 || c<=0) return 1;
                else if(a>20 || b>20 || c>20) return w(20,20,20);
                else if(memoization[a][b][c]!=0) {
                    result = memoization[a][b][c];
                    return result;
                }
                else if(a<b && b<c) {
                    result = w(a,b,c-1) + w(a,b-1,c-1) - w(a,b-1,c);
                    memoization[a][b][c] = result;
                    return result;
                }
                else {
                    result = w(a-1,b,c) + w(a-1,b-1,c) + w(a-1,b,c-1) - w(a-1,b-1,c-1);
                    memoization[a][b][c] = result;
                    return result;
                }
            }
            static void run() throws IOException {
                int a, b, c, result;
                while(true) {
                    tokenizer = new StringTokenizer(in.readLine());
                    a = Integer.parseInt(tokenizer.nextToken());
                    b = Integer.parseInt(tokenizer.nextToken());
                    c = Integer.parseInt(tokenizer.nextToken());
                    if(a==-1 && b==-1 && c==-1) break;

                    result = w(a,b,c);
                    builder.append(String.format("w(%d, %d, %d) = %d\n", a, b, c, result));
                }

                out.write(builder.toString());
                out.flush();
            }
        }
        static class P1904 {
            private static HashMap<Integer,Integer> memoization = new HashMap<>();

            private static int dp(int size) {
                if(size==0) return 0;
                else if(size==1) return 1;
                else if(size==2) return 2;
                memoization.put(1,1);
                memoization.put(2,2);

                for(int n=3; n<=size; n++) {
                    int result = (memoization.get(n-1) + memoization.get(n-2)) % 15746;
                    memoization.put(n, result);
                }
                return memoization.get(size);
            }
            static void run() throws IOException {
                final int input = Integer.parseInt(in.readLine());
                builder.append(dp(input));
                out.write(builder.toString());
                out.flush();
            }
        }
        static class P9461 {
            static final int capacity = 101;
            static long[] memoization = new long[capacity];

            static long dp(int number) {
                if(memoization[number]!=0) return memoization[number];
                long result = dp(number-1) + dp(number-5);
                memoization[number] = result;
                return result;
            }
            static void run() throws IOException {
                final int numberOfInputs = Integer.parseInt(in.readLine());
                memoization[1] = 1;
                memoization[2] = 1;
                memoization[3] = 1;
                memoization[4] = 2;
                memoization[5] = 2;

                int input;
                for(int n=0; n<numberOfInputs; n++) {
                    input = Integer.parseInt(in.readLine());
                    builder.append(dp(input)).append("\n");
                }

                out.write(builder.toString());
                out.flush();
            }
        }
    }

    public static void main(String[] args) {
        try {
            Chapter21.P9461.run();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
}