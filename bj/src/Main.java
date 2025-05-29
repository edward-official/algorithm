import java.awt.*;
import java.io.*;
import java.lang.annotation.Target;
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
        static class P1912 {
            private static int[] elements;
            private static int capacity;
            private static int max;
            private static int[] memoization;

            static void dp() {
                int candidate;
                for(int closingIndex=1; closingIndex<capacity; closingIndex++) {
                    candidate = memoization[closingIndex-1] + elements[closingIndex];
                    if(candidate < elements[closingIndex]) memoization[closingIndex] = elements[closingIndex];
                    else memoization[closingIndex] = candidate;
                    if(max < memoization[closingIndex]) max = memoization[closingIndex];
                }
            }
            static void run() throws IOException {
                capacity = Integer.parseInt(in.readLine());
                elements = new int[capacity];
                memoization = new int[capacity];

                tokenizer = new StringTokenizer(in.readLine());
                for(int index=0; index<capacity; index++) elements[index] = Integer.parseInt(tokenizer.nextToken());
                max = elements[0];
                memoization[0] = elements[0];
                dp();

                builder.append(max);
                out.write(builder.toString());
                out.flush();
            }
        }
        static class P1149 {
            private static final int numberOfColors = 3;
            private static int numberOfHouses;
            private static int[][] expences;
            private static int[][] memoization;

            static void run() throws IOException {
                numberOfHouses = Integer.parseInt(in.readLine());
                expences = new int[numberOfHouses][numberOfColors];
                memoization = new int[numberOfHouses][numberOfColors];

                for(int indexOfHouse=0; indexOfHouse<numberOfHouses; indexOfHouse++) {
                    tokenizer = new StringTokenizer(in.readLine());
                    for(int indexOfColor=0; indexOfColor<numberOfColors; indexOfColor++) {
                        expences[indexOfHouse][indexOfColor] = Integer.parseInt(tokenizer.nextToken());
                    }
                }
                for(int indexForColor=0; indexForColor<numberOfColors; indexForColor++) {
                    memoization[0][indexForColor] = expences[0][indexForColor];
                }

                for(int index=1; index<numberOfHouses; index++) {
                    for(int color=0; color<numberOfColors; color++) {
                        memoization[index][color] = expences[index][color];
                        if(memoization[index-1][(color+1) % numberOfColors] < memoization[index-1][(color+2) % numberOfColors]) memoization[index][color] += memoization[index-1][(color+1) % numberOfColors];
                        else memoization[index][color] += memoization[index-1][(color+2) % numberOfColors];
                    }
                }
                int min = memoization[numberOfHouses-1][0];
                for(int color=1; color<=2; color++) {
                    if(memoization[numberOfHouses-1][color]<min) min = memoization[numberOfHouses-1][color];
                }

                builder.append(min);
                out.write(builder.toString());
                out.flush();
            }
        }
        static class P1932 {
            private static int height;
            private static int numberOfElements;
            private static int[] costs;
            private static int[] memoization;

            static void run() throws IOException {
                height = Integer.parseInt(in.readLine());
                numberOfElements = height * (height+1) / 2;
                costs = new int[numberOfElements];
                memoization = new int[numberOfElements];

                int indexForInput = 0;
                for(int sizeOfInput=1; sizeOfInput<=height; sizeOfInput++) {
                    tokenizer = new StringTokenizer(in.readLine());
                    for(int n=0; n<sizeOfInput; n++) costs[indexForInput++] = Integer.parseInt(tokenizer.nextToken());
                }

                /*
                0
                12
                345
                6789
                 */
                memoization[0] = costs[0];
                int startingIndex = 0;
                for(int sizeOfLevel=1; sizeOfLevel<height; sizeOfLevel++) {
                    memoization[startingIndex+sizeOfLevel] = memoization[startingIndex] + costs[startingIndex+sizeOfLevel];
                    for(int index=startingIndex; index<startingIndex+sizeOfLevel; index++) memoization[index+sizeOfLevel+1] = memoization[index] + costs[index+sizeOfLevel+1];
                    for(int index=startingIndex; index<startingIndex+sizeOfLevel; index++) {
                        int temp = memoization[index] + costs[index+sizeOfLevel];
                        if(memoization[index+sizeOfLevel]<temp) memoization[index+sizeOfLevel]=temp;
                    }
                    startingIndex += sizeOfLevel;
                }

                int max = memoization[startingIndex];
                for(int index=startingIndex; index<startingIndex+height; index++) if(max<memoization[index]) max = memoization[index];

                builder.append(max);
                out.write(builder.toString());
                out.flush();
            }
        }
        static class P2579 {
            private static int[] points;
            private static int[] memoization;
            private static int size;

            private static int dp() {
                if(size==1) return points[0];
                else if(size==2) return points[0] + points[1];

                /*
                there can't be two skips in a row
                two skips should have one or two steps in between them

                ___O
                OXOO: memoization[n-3] + points[n-1] + points[n]
                _OXO: memoization[n-2] + points[n]
                 */
                memoization[0] = points[0];
                memoization[1] = points[0] + points[1];
                if(points[0]<points[1]) memoization[2] = points[1] + points[2];
                else memoization[2] = points[0] + points[2];
                for(int index=3; index<size; index++) {
                    int value1 = memoization[index-3] + points[index-1] + points[index];
                    int value2 = memoization[index-2] + points[index];
                    if(value1<value2) memoization[index] = value2;
                    else memoization[index] = value1;
                }
                return memoization[size-1];
            }
            static void run() throws IOException {
                size = Integer.parseInt(in.readLine());
                points = new int[size];
                memoization = new int[size];
                for(int index=0; index<size; index++) points[index] = Integer.parseInt(in.readLine());

                builder.append(dp());
                out.write(builder.toString());
                out.flush();
            }
        }
        static class P1463 {
            private static int target;
            private static int[] memoization;

            private static int selectMin(int c1, int c2, int c3) {
                if(c1<=c2 && c1<=c3) return c1;
                else if(c2<=c1 && c2<=c3) return c2;
                else return c3;
            }
            private static void dp() {
                for(int item=4; item<=target; item++) {
                    int c1 = 1 + item%3 + memoization[item/3];
                    int c2 = 1 + item%2 + memoization[item/2];
                    int c3 = 1 + memoization[item-1];
                    memoization[item] = selectMin(c1,c2,c3);
                }
            }
            static void run() throws IOException {
                target = Integer.parseInt(in.readLine());
                if(target==1) builder.append(0);
                else if(target==2) builder.append(1);
                else if(target==3) builder.append(1);
                else {
                    memoization = new int[target+1];
                    memoization[1] = 0;
                    memoization[2] = 1;
                    memoization[3] = 1;
                    dp();
                    builder.append(memoization[target]);
                }

                out.write(builder.toString());
                out.flush();
            }
        }
        static class P10844 {
            private static long[][] memoization;
            private static int length;
            private final static int numberOfItems = 10;
            private static long divider = 1000000000;

            static void run() throws IOException {
                length = Integer.parseInt(in.readLine());
                memoization = new long[length][numberOfItems];

                for(int index=1; index<numberOfItems; index++) memoization[0][index] = 1;
                for(int index1=1; index1<length; index1++) {
                    memoization[index1][0] = memoization[index1-1][1];
                    for(int index2=1; index2<numberOfItems-1; index2++) memoization[index1][index2] = (memoization[index1-1][index2-1] + memoization[index1-1][index2+1]) % divider;
                    memoization[index1][numberOfItems-1] = memoization[index1-1][numberOfItems-2];
                }

                long count = 0;
                for(int index=0; index<numberOfItems; index++) count = (count + memoization[length-1][index]) % divider;

                builder.append(count);
                out.write(builder.toString());
                out.flush();
            }
        }
        static class P2156 {
            private static int size;
            private static int[] elements;
            private static int[] memoization;

            static void run() throws IOException {
                size = Integer.parseInt(in.readLine());
                elements = new int[size];
                memoization = new int[size];

                for(int index=0; index<size; index++) {
                    elements[index] = Integer.parseInt(in.readLine());
                }
                if(size==1) builder.append(elements[0]);
                else if(size==2) builder.append(elements[0] + elements[1]);
                else if(size==3) builder.append(Math.max(elements[0] + elements[1],Math.max(elements[1]+elements[2],elements[0]+elements[2])));
                else {
                    memoization[0] = elements[0];
                    memoization[1] = elements[0] + elements[1];
                    memoization[2] = Math.max(elements[1]+elements[2],elements[0]+elements[2]);
                    memoization[3] = Math.max(elements[0]+elements[1]+elements[3],elements[0]+elements[2]+elements[3]);
                    /*
                    [OXOO]
                    [OOXO]
                     */

                    int max = Math.max(memoization[2],memoization[3]);
                    for(int index=4; index<size; index++) {
                        /*
                        [____O]
                        [OXXOO]
                        [_OXOO]
                        [_OXXO]
                        [__OXO]
                         */
                        int c1 = memoization[index-4] + elements[index-1] + elements[index];
                        int c2 = memoization[index-3] + elements[index-1] + elements[index];
                        int c3 = memoization[index-3] + elements[index];
                        int c4 = memoization[index-2] + elements[index];

                        memoization[index] = Math.max(Math.max(Math.max(c1,c2),c3),c4);
                        if(max<memoization[index]) max = memoization[index];
//                        System.err.println(String.format("selected: memoization[%d] = %d (%d,%d,%d,%d)",index,memoization[index],c1,c2,c3,c4));
                    }
                    builder.append(max);
                }

                out.write(builder.toString());
                out.flush();
            }
        }
        static class P11053 {
            private static int size;
            private static int[] elements;
            private static HashMap<Integer,Integer> memoization = new HashMap<>();
            /*
            how to solve the problem of finding longest increasing subset
            we have several options for selecting first element
            how to determine the longest length of sequence when the first element is decided?
            this means that choosing the second element is not that simple
            1 5 2 3 4 100 | 6 7 8 9 10
            1 5 2 3 4 100 | 6 7 8 9 10
             */


            private static int recursive(int index) {
                int longest = 0;
                if(memoization.containsKey(index)) return memoization.get(index);
                for(int nextIndex=index+1; nextIndex<size; nextIndex++) {
                    if(elements[index]<elements[nextIndex]) {
                        int temp = 1 + recursive(nextIndex);
                        if(longest<temp) longest = temp;
                    }
                }
                memoization.put(index,longest);
                return longest;
            }
            static void run() throws IOException {
                size = Integer.parseInt(in.readLine());
                elements = new int[size];
                tokenizer = new StringTokenizer(in.readLine());
                for(int index=0; index<size; index++) elements[index] = Integer.parseInt(tokenizer.nextToken());

                int longest = 0;
                for(int firstIndex=0; firstIndex<size; firstIndex++) {
                    if(longest>size-firstIndex) break;
                    int temp = recursive(firstIndex) + 1;
                    if(longest<temp) longest=temp;
                }

                builder.append(longest);
                out.write(builder.toString());
                out.flush();
            }
        }
        static class P11054 {
            private static int size;
            private static int[] elements;
            private static int[] memo1;//increasing
            private static int[] memo2;//decreasing

            private static void dp() {
                for(int closingIndex=1; closingIndex<size; closingIndex++) {
                    int longest1 = 1;
                    for(int index=0; index<closingIndex; index++) {
                        if(elements[index]<elements[closingIndex]) {
                            int temp = memo1[index] + 1;
                            if(longest1<temp) longest1=temp;
                        }
                    }
                    memo1[closingIndex] = longest1;
                }
                for(int openingIndex=size-2; openingIndex>=0; openingIndex--) {
                    int longest2 = 1;
                    for(int index=size-1; index>openingIndex; index--) {
                        if(elements[openingIndex]>elements[index]) {
                            int temp = memo2[index] + 1;
                            if(longest2<temp) longest2=temp;
                        }
                    }
                    memo2[openingIndex] = longest2;
                }
            }
            static void run() throws IOException {
                size = Integer.parseInt(in.readLine());
                elements = new int[size];
                memo1 = new int[size];
                memo1[0] = 1;
                memo2 = new int[size];
                memo2[size-1] = 1;

                tokenizer = new StringTokenizer(in.readLine());
                for(int index=0; index<size; index++) elements[index] = Integer.parseInt(tokenizer.nextToken());

                dp();
                int longest = memo1[0]+memo2[0]-1;
                for(int index=1; index<size; index++) {
                    int temp = memo1[index]+memo2[index]-1;
                    if(longest<temp) longest=temp;
                }

                builder.append(longest);
                out.write(builder.toString());
                out.flush();
            }
        }
    }

    public static void main(String[] args) {
        try {
            Chapter21.P11054.run();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
}