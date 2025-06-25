import java.awt.*;
import java.io.*;
import java.util.*;

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
    static class P9184_2 {
        private static final int offset = 50;
        private static int[][][] record;
        private static boolean[][][] isRecorded;

        private static int compute(int a, int b, int c) {
            if(isRecorded[a+offset][b+offset][c+offset]) return record[a+offset][b+offset][c+offset];
            int result;
            if(a<=0 || b<=0 || c<=0) result = 1;
            else if(a>20 || b>20 || c>20) result = compute(20,20,20);
            else if(a<b && b<c) result = compute(a,b,c-1) + compute(a,b-1,c-1) - compute(a,b-1,c);
            else result = compute(a-1,b,c) + compute(a-1,b-1,c) + compute(a-1,b,c-1) - compute(a-1,b-1,c-1);
            isRecorded[a+offset][b+offset][c+offset] = true;
            record[a+offset][b+offset][c+offset] = result;
            return result;
        }
        static void execute() throws IOException {
            final int side = 101;
            record = new int[side][side][side];
            isRecorded = new boolean[side][side][side];
            while(true) {
                tokenizer = new StringTokenizer(in.readLine());
                int a = Integer.parseInt(tokenizer.nextToken());
                int b = Integer.parseInt(tokenizer.nextToken());
                int c = Integer.parseInt(tokenizer.nextToken());
                if(a==-1 && b==-1 && c==-1) break;
                builder.append(String.format("w(%d, %d, %d) = %d\n", a, b, c, compute(a,b,c)));
            }
            out.write(builder.toString());
            out.flush();
        }
    }

    public static void main(String[] args) {
        try {
            P9184_2.execute();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
}