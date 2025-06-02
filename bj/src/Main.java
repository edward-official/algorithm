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
            how to solve the problem of finding recordOfLongest increasing subset
            we have several options for selecting first element
            how to determine the recordOfLongest length of sequence when the first element is decided?
            this means that choosing the second element is not that simple
            1 5 2 3 4 100 | 6 7 8 9 10
            1 5 2 3 4 100 | 6 7 8 9 10
             */


            private static int recursive(int index) {
                int recordOfLongest = 0;
                if(memoization.containsKey(index)) return memoization.get(index);
                for(int nextIndex=index+1; nextIndex<size; nextIndex++) {
                    if(elements[index]<elements[nextIndex]) {
                        int temp = 1 + recursive(nextIndex);
                        if(recordOfLongest<temp) recordOfLongest = temp;
                    }
                }
                memoization.put(index,recordOfLongest);
                return recordOfLongest;
            }
            static void run() throws IOException {
                size = Integer.parseInt(in.readLine());
                elements = new int[size];
                tokenizer = new StringTokenizer(in.readLine());
                for(int index=0; index<size; index++) elements[index] = Integer.parseInt(tokenizer.nextToken());

                int recordOfLongest = 0;
                for(int firstIndex=0; firstIndex<size; firstIndex++) {
                    if(recordOfLongest>size-firstIndex) break;
                    int temp = recursive(firstIndex) + 1;
                    if(recordOfLongest<temp) recordOfLongest=temp;
                }

                builder.append(recordOfLongest);
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
                    int recordLongest1 = 1;
                    for(int index=0; index<closingIndex; index++) {
                        if(elements[index]<elements[closingIndex]) {
                            int temp = memo1[index] + 1;
                            if(recordLongest1<temp) recordLongest1=temp;
                        }
                    }
                    memo1[closingIndex] = recordLongest1;
                }
                for(int openingIndex=size-2; openingIndex>=0; openingIndex--) {
                    int recordLongest2 = 1;
                    for(int index=size-1; index>openingIndex; index--) {
                        if(elements[openingIndex]>elements[index]) {
                            int temp = memo2[index] + 1;
                            if(recordLongest2<temp) recordLongest2=temp;
                        }
                    }
                    memo2[openingIndex] = recordLongest2;
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
                int recordOfLongest = memo1[0]+memo2[0]-1;
                for(int index=1; index<size; index++) {
                    int temp = memo1[index]+memo2[index]-1;
                    if(recordOfLongest<temp) recordOfLongest=temp;
                }

                builder.append(recordOfLongest);
                out.write(builder.toString());
                out.flush();
            }
        }
        static class P2565 {
            private static int size;
            private static int[] telegraph1;
            private static int[] telegraph2;
            private static int[] recordOfLongest;
            private static int finalLongestLength = -1;

            private static void merge(int openingIndex, int closingIndex) {
                int centerIndex = (openingIndex+closingIndex)/2;
                int index1=openingIndex, index2=centerIndex+1;

                int[] temp1 = new int[closingIndex-openingIndex+1];
                int[] temp2 = new int[closingIndex-openingIndex+1];
                int indexForTemp = 0;

                while(index1<=centerIndex && index2<=closingIndex) {
                    if(telegraph1[index1]<telegraph1[index2]) {
                        temp1[indexForTemp]=telegraph1[index1];
                        temp2[indexForTemp]=telegraph2[index1];
                        indexForTemp++;
                        index1++;
                    }
                    else {
                        temp1[indexForTemp]=telegraph1[index2];
                        temp2[indexForTemp]=telegraph2[index2];
                        indexForTemp++;
                        index2++;
                    }
                }
                while(index1<=centerIndex) {
                    temp1[indexForTemp]=telegraph1[index1];
                    temp2[indexForTemp]=telegraph2[index1];
                    indexForTemp++;
                    index1++;
                }
                while(index2<=closingIndex) {
                    temp1[indexForTemp]=telegraph1[index2];
                    temp2[indexForTemp]=telegraph2[index2];
                    indexForTemp++;
                    index2++;
                }
                for(int offset=0; offset<=closingIndex-openingIndex; offset++) {
                    telegraph1[openingIndex+offset] = temp1[offset];
                    telegraph2[openingIndex+offset] = temp2[offset];
                }
            }
            private static void sort(int openingIndex, int closingIndex) {
                if(openingIndex>=closingIndex) return;
                int centerIndex = (openingIndex+closingIndex)/2;
                sort(openingIndex,centerIndex);
                sort(centerIndex+1,closingIndex);
                merge(openingIndex,closingIndex);
            }
            private static void updateRecordOfLongest(int range) {
//                System.err.println(String.format("\nEntered method of range %d", range));
                int max = 1;
                for(int index=0; index<range; index++) {
                    if(telegraph2[index] < telegraph2[range]) {
                        int temp = recordOfLongest[index] + 1;
                        if(max<temp) {
                            max = temp;
                            if(finalLongestLength<max) finalLongestLength = max;
                        }
                    }
                }
                recordOfLongest[range] = max;
//                System.err.println(Arrays.toString(telegraph2));
//                System.err.println(Arrays.toString(recordOfLongest));
            }
            static void run() throws IOException {
                size = Integer.parseInt(in.readLine());
                telegraph1 = new int[size];
                telegraph2 = new int[size];
                recordOfLongest = new int[size];

                for(int index=0; index<size; index++) {
                    tokenizer = new StringTokenizer(in.readLine());
                    int from = Integer.parseInt(tokenizer.nextToken());
                    int to = Integer.parseInt(tokenizer.nextToken());
                    telegraph1[index] = from;
                    telegraph2[index] = to;
                }

                sort(0,size-1);
                recordOfLongest[0] = 1;
                for(int range=1; range<size; range++) updateRecordOfLongest(range);

//                System.err.println("\nafter sorting");
//                System.err.println(Arrays.toString(telegraph1));
//                System.err.println(Arrays.toString(telegraph2));

                builder.append(size-finalLongestLength);
                out.write(builder.toString());
                out.flush();
            }
        }
        static class P9251 {
            private static String element1;
            private static String element2;
            private static int size1;
            private static int size2;
            private static int[][] dp;

            static void run() throws IOException {
                element1 = in.readLine();
                size1 = element1.length();
                element2 = in.readLine();
                size2 = element2.length();
                dp = new int[size1+1][size2+1];

                /*
                definition of dp: the longest length when considering only range[0,index1], range[0,index2]
                 */
                for(int index1=1; index1<=size1; index1++) {
                    for(int index2=1; index2<=size2; index2++) {
                        if(element1.charAt(index1-1)==element2.charAt(index2-1)) dp[index1][index2] = dp[index1-1][index2-1] + 1;
                        else {
                            int c1 = dp[index1-1][index2];
                            int c2 = dp[index1][index2-1];
                            dp[index1][index2] = Math.max(c1,c2);
                        }
                    }
                }

                builder.append(dp[size1][size2]);
                out.write(builder.toString());
                out.flush();
            }
        }
        static class P12865 {
            private static int limitOfWeight;
            private static int numberOfElements;
            private static int[] weights;
            private static int[] values;
            private static int[][] dp;

            static void run() throws IOException {
                tokenizer = new StringTokenizer(in.readLine());
                numberOfElements = Integer.parseInt(tokenizer.nextToken());
                limitOfWeight = Integer.parseInt(tokenizer.nextToken());
                weights = new int[numberOfElements+1];
                values = new int[numberOfElements+1];
                dp = new int[numberOfElements+1][limitOfWeight+1];

                for(int index=1; index<=numberOfElements; index++) {
                    tokenizer = new StringTokenizer(in.readLine());
                    weights[index] = Integer.parseInt(tokenizer.nextToken());
                    values[index] = Integer.parseInt(tokenizer.nextToken());
                }
                for(int targetIndex=1; targetIndex<=numberOfElements; targetIndex++) {
                    for(int weight=1; weight<=limitOfWeight; weight++) {
                        if(weights[targetIndex]>weight) dp[targetIndex][weight] = dp[targetIndex-1][weight];
                        else {
                            int c1 = dp[targetIndex-1][weight];
                            int c2 = dp[targetIndex-1][weight-weights[targetIndex]] + values[targetIndex];
                            dp[targetIndex][weight] = Math.max(c1,c2);
                        }
                    }
                }

                builder.append(dp[numberOfElements][limitOfWeight]);
                out.write(builder.toString());
                out.flush();
            }
        }
    }

    public static void main(String[] args) {
        try {
            Chapter21.P9251.run();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
}