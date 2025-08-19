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
    static class P1904 {
        private static int n;
        private static int[] memo;

        public static void execute() throws IOException {
            n = Integer.parseInt(in.readLine());
            int numberOfPossibleAnswers;
            if(n==1) numberOfPossibleAnswers=1;
            else if(n==2) numberOfPossibleAnswers=2;
            else {
                memo = new int[n+1];
                memo[1] = 1;
                memo[2] = 2;
                for(int index=3; index<=n; index++) {
                    memo[index] = (memo[index-1] + memo[index-2]) % 15746;
                }
                numberOfPossibleAnswers = memo[n];
            }
            builder.append(numberOfPossibleAnswers);
            out.write(builder.toString());
            out.flush();

        }
    }
    static class P9461 {
        private static int closingRange = 100;
        private static long[] padovan = new long[closingRange+1];

        private static void updatePadovan() {
            padovan[1] = 1;
            padovan[2] = 1;
            padovan[3] = 1;
            padovan[4] = 2;
            padovan[5] = 2;
            for(int index=6; index<=closingRange; index++) {
                padovan[index] = padovan[index-1] + padovan[index-5];
            }
        }
        public static void execute() throws IOException {
            updatePadovan();
            int repetition = Integer.parseInt(in.readLine());
            int n;
            for(int r=1; r<=repetition; r++) {
                n = Integer.parseInt(in.readLine());
                builder.append(padovan[n]).append("\n");
            }
            out.write(builder.toString());
            out.flush();
        }
    }
    static class P1912_HELPED {
        private static int quantityOfElements;
        private static int[] element;
        private static int[] inclusiveMaxValue;

        public static void execute() throws IOException {
            quantityOfElements = Integer.parseInt(in.readLine());
            element = new int[quantityOfElements + 1];
            inclusiveMaxValue = new int[quantityOfElements + 1];
            tokenizer = new StringTokenizer(in.readLine());
            int absoluteMaximum = Integer.MIN_VALUE;
            for(int index=1; index<=quantityOfElements; index++) {
                element[index] = Integer.parseInt(tokenizer.nextToken());
                inclusiveMaxValue[index] = Math.max(inclusiveMaxValue[index - 1] + element[index], element[index]);
                if(absoluteMaximum < inclusiveMaxValue[index]) absoluteMaximum = inclusiveMaxValue[index];
            }
            builder.append(absoluteMaximum);
            out.write(builder.toString());
            out.flush();
        }
    }
    static class P1149 {
        private static int numberOfHouses;
        private final static int numberOfColors = 3;
        private static int[][] costs;
        private static int[][] accumulative;

        public static void execute() throws IOException {
            numberOfHouses = Integer.parseInt(in.readLine());
            costs = new int[numberOfHouses+1][numberOfColors];
            accumulative = new int[numberOfHouses+1][numberOfColors];
            for(int index=1; index<=numberOfHouses; index++) {
                tokenizer = new StringTokenizer(in.readLine());
                for(int color=0; color<numberOfColors; color++) {
                    costs[index][color] = Integer.parseInt(tokenizer.nextToken());
                }
                for(int color=0; color<numberOfColors; color++) {
                    accumulative[index][color] = costs[index][color];
                    accumulative[index][color] += Math.min(accumulative[index-1][(color+1)%numberOfColors], accumulative[index-1][(color+2)%numberOfColors]);
                }
            }
            int result = Integer.MAX_VALUE;
            for(int color=0; color<numberOfColors; color++) {
                int temp = accumulative[numberOfHouses][color];
                if(temp<result) result = temp;
            }
            builder.append(result);
            out.write(builder.toString());
            out.flush();
        }
    }
    static class P1932 {
        private static int numberOfRows;
        private static int[][] triangle;
        private static int[][] accumulative;

        public static void execute() throws IOException {
            numberOfRows = Integer.parseInt(in.readLine());
            triangle = new int[numberOfRows+1][numberOfRows+1];
            accumulative = new int[numberOfRows+1][numberOfRows+1];
            for(int row=1; row<=numberOfRows; row++) {
                tokenizer = new StringTokenizer(in.readLine());
                for(int column=1; column<=row; column++) {
                    triangle[row][column] = Integer.parseInt(tokenizer.nextToken());
                    accumulative[row][column] = triangle[row][column];
                    accumulative[row][column] += Math.max(accumulative[row-1][column-1], accumulative[row-1][column]);
                }
            }
            int result = Integer.MIN_VALUE;
            for(int column=1; column<=numberOfRows; column++) {
                int temp = accumulative[numberOfRows][column];
                if(result<temp) result = temp;
            }
            builder.append(result);
            out.write(builder.toString());
            out.flush();
        }
    }
    static class P2579 {
        private static int numberOfStairs;
        private static int[] stairs;
        private static int[] accumulative;

        public static void execute() throws IOException {
            numberOfStairs = Integer.parseInt(in.readLine());
            stairs = new int[numberOfStairs + 1];
            accumulative = new int[numberOfStairs + 1];

            for(int index=1; index<=numberOfStairs; index++) {
                stairs[index] = Integer.parseInt(in.readLine());
            }
            accumulative[1] = stairs[1];
            if(numberOfStairs>=2) accumulative[2] = stairs[1] + stairs[2];
            if(numberOfStairs>=3) {
                for(int index=3; index<=numberOfStairs; index++) {
                    int candidate1 = accumulative[index - 2] + stairs[index];
                    int candidate2 = accumulative[index - 3] + stairs[index - 1] + stairs[index];
                    accumulative[index] = Math.max(candidate1, candidate2);
                }
            }

            builder.append(accumulative[numberOfStairs]);
            out.write(builder.toString());
            out.flush();

            /*
            OXOO
            OOXO
            XOOX
            OXOX
             */
        }
    }
    static class P1463 {
        private static int[] accumulative;
        private static int targetElement;

        public static void execute() throws IOException {
            targetElement = Integer.parseInt(in.readLine());
            accumulative = new int[targetElement + 1];
            accumulative[1] = 0;
            for(int element=2; element<=targetElement; element++) {
                accumulative[element] = accumulative[element - 1] + 1;
                if(element%3==0) {
                    int candidate = accumulative[element / 3] + 1;
                    if(candidate < accumulative[element]) accumulative[element] = candidate;
                }
                if(element%2==0) {
                    int candidate = accumulative[element / 2] + 1;
                    if(candidate < accumulative[element]) accumulative[element] = candidate;
                }
            }
            builder.append(accumulative[targetElement]);
            out.write(builder.toString());
            out.flush();
        }
    }
    static class P10844 {
        private static int numberOfDigits;
        private static long[][] accumulative;
        private static final int ten = 10;
        private static final int modulator = 1_000_000_000;

        public static void execute() throws IOException {
            numberOfDigits = Integer.parseInt(in.readLine());
            accumulative = new long[numberOfDigits + 1][ten + 2];
            for(int element=1; element<ten; element++) {
                accumulative[1][element + 1] = 1;
            }
            for(int index=2; index<=numberOfDigits; index++) {
                for(int element=0; element<ten; element++) {
                    accumulative[index][element + 1] =
                            (accumulative[index - 1][element] + accumulative[index - 1][element + 2]) % modulator;
                }
            }
            long result = 0;
            for(int element=0; element<ten; element++) {
                result += accumulative[numberOfDigits][element + 1];
            }
//            for(int index=1; index<=numberOfDigits; index++) {
//                System.out.println(Arrays.toString(accumulative[index]));
//            }

            result %= modulator;
            builder.append(result);
            out.write(builder.toString());
            out.flush();
        }
    }
    static class P2156 {
        private static int numberOfGlasses;
        private static int[] glasses;
        private static int[] accumulative;

        public static void execute() throws IOException {
            numberOfGlasses = Integer.parseInt(in.readLine());
            glasses = new int[numberOfGlasses + 1];
            accumulative = new int[numberOfGlasses + 1];

            for(int index=1; index<=numberOfGlasses; index++) {
                glasses[index] = Integer.parseInt(in.readLine());
            }
            if(numberOfGlasses>=1) accumulative[1] = glasses[1];
            if(numberOfGlasses>=2) accumulative[2] = glasses[1] + glasses[2];
            for(int index=3; index<=numberOfGlasses; index++) {
                int candidate1 = glasses[index] + Math.max(accumulative[index - 2],
                        accumulative[index - 3] + glasses[index - 1]);
                int candidate2 = accumulative[index - 1];
                accumulative[index] = Math.max(candidate1, candidate2);
            }
            builder.append(accumulative[numberOfGlasses]);
            out.write(builder.toString());
            out.flush();
            /*
            _____XO
            ____XOO
            ______X
             */
        }
    }
    static class P11053 {
        private static int numberOfElements;
        private static int[] elements;
        private static int[] inclusive;
        private static int finalAnswer = 0;

        public static void execute() throws IOException {
            numberOfElements = Integer.parseInt(in.readLine());
            elements = new int[numberOfElements + 1];
            inclusive = new int[numberOfElements + 1];

            tokenizer = new StringTokenizer(in.readLine());
            for(int index=1; index<=numberOfElements; index++) {
                elements[index] = Integer.parseInt(tokenizer.nextToken());
                int initializer = 1;
                for(int traverse=1; traverse<index; traverse++) {
                    if(elements[traverse] < elements[index]) {
                        int temp = inclusive[traverse] + 1;
                        if(initializer < temp) initializer = temp;
                    }
                }
                inclusive[index] = initializer;
                if(finalAnswer < initializer) finalAnswer = initializer;
            }

            builder.append(finalAnswer);
            out.write(builder.toString());
            out.flush();
        }
    }
    static class P11054 {
        private static int numberOfElements;
        private static int[] elements;
        private static int[] accumulativeForward;
        private static int[] accumulativeReverse;

        private static int bitonic() {
            int finalAnswer = 0;
            for(int index=1; index<=numberOfElements; index++) {
                int temp = accumulativeForward[index] + accumulativeReverse[index] - 1;
                if(finalAnswer < temp) finalAnswer = temp;
            }
            return finalAnswer;
        }
        private static void configuration() {
            for(int index=1; index<=numberOfElements; index++) {
                int assignForward = 1;
                for(int traverse=1; traverse<index; traverse++) {
                    if(elements[traverse] < elements[index]) {
                        int temp = accumulativeForward[traverse] + 1;
                        if(assignForward < temp) assignForward = temp;
                    }
                }
                accumulativeForward[index] = assignForward;
            }
            for(int index=numberOfElements; index>=1; index--) {
                int assignReverse = 1;
                for(int traverse=numberOfElements; traverse>index; traverse--) {
                    if(elements[traverse] < elements[index]) {
                        int temp = accumulativeReverse[traverse] + 1;
                        if(assignReverse < temp) assignReverse = temp;
                    }
                }
                accumulativeReverse[index] = assignReverse;
            }
        }
        public static void execute() throws IOException {
            numberOfElements = Integer.parseInt(in.readLine());
            elements = new int[numberOfElements + 1];
            accumulativeForward = new int[numberOfElements + 1];
            accumulativeReverse = new int[numberOfElements + 1];

            tokenizer = new StringTokenizer(in.readLine());
            for(int index=1; index<=numberOfElements; index++) {
                elements[index] = Integer.parseInt(tokenizer.nextToken());
            }

            configuration();
            builder.append(bitonic());
            out.write(builder.toString());
            out.flush();
        }
    }
    static class P2565 {
        private static int numberOfWires;
        private static final int range = 500;
        private static int[] wires = new int[range + 1];
        private static int[] accumulative = new int[range + 1];

        public static void execute() throws IOException {
            numberOfWires = Integer.parseInt(in.readLine());
            for(int read=1; read<=numberOfWires; read++) {
                tokenizer = new StringTokenizer(in.readLine());
                int from = Integer.parseInt(tokenizer.nextToken());
                int to = Integer.parseInt(tokenizer.nextToken());
                wires[from] = to;
            }

            int longest = Integer.MIN_VALUE;
            for(int index=1; index<=range; index++) {
                if(wires[index]==0) continue;
                int assign = 1;
                for(int traverse=1; traverse<index; traverse++) {
                    if(wires[traverse] == 0) continue;
                    if(wires[traverse] < wires[index]) {
                        int temp = accumulative[traverse] + 1;
                        if(assign < temp) assign = temp;
                    }
                }
                accumulative[index] = assign;
                if(longest < assign) longest = assign;
            }

            builder.append(numberOfWires - longest);
            out.write(builder.toString());
            out.flush();
        }
    }
    static class P9251_ImproperTimeComplexity {
        private static String element1, element2;
        private static int finalAnswer = 0;

        private static void recursive(int openingIndex1, int openingIndex2, int length) {
            if(openingIndex1>=element1.length() || openingIndex2>=element2.length()) {
                if(finalAnswer < length) finalAnswer = length;
                return;
            }
//            System.out.println(String.format("checking %s[%d], %s[%d]", element1, openingIndex1, element2, openingIndex2));
            for(int index2=openingIndex2; index2<element2.length(); index2++) {
                if(element1.charAt(openingIndex1)==element2.charAt(index2)) {
                    recursive(openingIndex1 + 1, index2 + 1, length + 1);
                }
            }
            recursive(openingIndex1 + 1, openingIndex2, length);
        }
        public static void execute() throws IOException {
            element1 = in.readLine();
            element2 = in.readLine();
            recursive(0,0,0);
            builder.append(finalAnswer);
            out.write(builder.toString());
            out.flush();
        }
    }
    static class P9251 {
        private static String sequence1, sequence2;
        private static int size1, size2;
        private static int[][] accumulative;

        public static void execute() throws IOException {
            sequence1 = in.readLine();
            sequence2 = in.readLine();
            size1 = sequence1.length();
            size2 = sequence2.length();
            accumulative = new int[size1 + 1][size2 + 1];

            for(int index1=1; index1<=size1; index1++) {
                for(int index2=1; index2<=size2; index2++) {
                    if (sequence1.charAt(index1 - 1) == sequence2.charAt(index2 - 1)) {
                        accumulative[index1][index2] = accumulative[index1 - 1][index2 - 1] + 1;
                    } else {
                        accumulative[index1][index2] = Math.max(accumulative[index1 - 1][index2],
                                accumulative[index1][index2 - 1]);
                    }
                }
            }

            builder.append(accumulative[size1][size2]);
            out.write(builder.toString());
            out.flush();
        }
    }

    public static void main(String[] args) {
        try {
            P9251.execute();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
}