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
    static class P1904_2 {
        private static int numberOfTiles;
        private static int[] memoization;

        private static int dp(int targetLength) {
            if(memoization[targetLength]>0) return memoization[targetLength];
            else if(targetLength<=1) return 1;
            else if(targetLength==2) return 2;
            //____O|O____
            //_____|_____
            if(targetLength%2==0) {
                int halfLength = targetLength/2;
                memoization[targetLength] += dp(halfLength) * dp(halfLength);
                memoization[targetLength] += dp(halfLength-1) * dp(halfLength-1);
                memoization[targetLength] %= 15746;
                return memoization[targetLength];
            }
            else {
                int length1 = targetLength/2;
                int length2 = targetLength/2+1;
                memoization[targetLength] += dp(length1) * dp(length2);
                memoization[targetLength] += dp(length1-1) * dp(length2-1);
                memoization[targetLength] %= 15746;
                return memoization[targetLength];
            }
        }
        static void execute() throws IOException {
            numberOfTiles = Integer.parseInt(in.readLine());
            memoization = new int[numberOfTiles+1];
            builder.append(dp(numberOfTiles));
            out.write(builder.toString());
            out.flush();
        }
    }
    static class P9461_2 {
        private static HashMap<Integer,Long> memoization = new HashMap<>();
        private static long dp(int index) {
            if(memoization.containsKey(index)) return memoization.get(index);
            else if(index<=3) return 1;
            else if(index<=5) return 2;
            else {
                long value = dp(index-1) + dp(index-5);
                memoization.put(index,value);
                return value;
            }
        }
        static void execute() throws IOException {
            int closingLoop = Integer.parseInt(in.readLine());
            for(int loop=0; loop<closingLoop; loop++) {
                int targetIndex = Integer.parseInt(in.readLine());
                builder.append(dp(targetIndex)).append("\n");
            }
            out.write(builder.toString());
            out.flush();
        }
    }
    static class P1912_2 {
        private static int[] elements;
        private static int numberOfElements;
        private static int[] dp;

        static void execute() throws IOException {
            numberOfElements = Integer.parseInt(in.readLine());
            elements = new int[numberOfElements+1];
            dp = new int[numberOfElements+1];
            tokenizer = new StringTokenizer(in.readLine());
            int max = Integer.MIN_VALUE;
            for(int index=1; index<=numberOfElements; index++) {
                elements[index] = Integer.parseInt(tokenizer.nextToken());
                dp[index] = Math.max(dp[index-1]+elements[index],elements[index]);
                if(max<dp[index]) max=dp[index];
            }
            builder.append(max);
            out.write(builder.toString());
            out.flush();
        }
    }
    static class P1149_2 {
        private static int numberOfHouses;
        private static final int numberOfColors = 3;
        private static int costs[][];
        private static int optimum[][];

        private static void detailsOnTerminal() {
            for(int house=1; house<=numberOfHouses; house++) {
                System.err.println(String.format("COST : [%4d,%4d,%4d]",costs[house][1],costs[house][2],costs[house][3]));
                System.err.println(String.format("TOTAL: [%4d,%4d,%4d]",optimum[house][1],optimum[house][2],optimum[house][3]));
            }
        }
        static void execute() throws IOException {
            numberOfHouses = Integer.parseInt(in.readLine());
            optimum = new int[numberOfHouses+1][numberOfColors+1];
            costs = new int[numberOfHouses+1][numberOfColors+1];
            for(int house=1; house<=numberOfHouses; house++) {
                tokenizer = new StringTokenizer(in.readLine());
                for(int color=1; color<=numberOfColors; color++) {
                    costs[house][color] = Integer.parseInt(tokenizer.nextToken());
                    int differentColotIndex1 = (color-1+1)%numberOfColors+1;
                    int differentColotIndex2 = (color-1+2)%numberOfColors+1;
                    optimum[house][color] = costs[house][color] + Math.min(optimum[house-1][differentColotIndex1],optimum[house-1][differentColotIndex2]);
                }
            }
            int optimalCost = Integer.MAX_VALUE;
            for(int color=1; color<=numberOfColors; color++) {
                int tentative = optimum[numberOfHouses][color];
                if(tentative<optimalCost) optimalCost = tentative;
            }
//            detailsOnTerminal();
            builder.append(optimalCost);
            out.write(builder.toString());
            out.flush();
        }
    }

    public static void main(String[] args) {
        try {
            P1149_2.execute();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
}