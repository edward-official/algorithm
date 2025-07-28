import java.io.*;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Chapter21 {
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
    static class P1932_2 {
        private static int side;
        private static int[][] triangle;
        private static int[][] optimal;

        static void execute() throws IOException {
            side = Integer.parseInt(in.readLine());
            triangle = new int[side+1][side+1];
            optimal = new int[side+1][side+1];
            for(int row=1; row<=side; row++) {
                tokenizer = new StringTokenizer(in.readLine());
                for(int column=1; column<=row; column++) {
                    triangle[row][column] = Integer.parseInt(tokenizer.nextToken());
                    optimal[row][column] = Math.max(optimal[row-1][column],optimal[row-1][column-1]) + triangle[row][column];
                }
            }
            int result = Integer.MIN_VALUE;
            for(int column=1; column<=side; column++) {
                int value = optimal[side][column];
                if(result<value) result=value;
            }
            builder.append(result);
            out.write(builder.toString());
            out.flush();
        }
    }
    static class P2579_2 {
        private static int numberOfSteps;
        private static int[] points;
        private static int[] optimal;

        static void execute() throws IOException {
            /*
            ______
            __OXOO
            ___OXO

            ___XOO
            ___OXO
             */
            numberOfSteps = Integer.parseInt(in.readLine());
            points = new int[numberOfSteps+1];
            optimal = new int[numberOfSteps+1];
            for(int index=1; index<=numberOfSteps; index++) {
                points[index] = Integer.parseInt(in.readLine());
            }
            if(numberOfSteps>=1) optimal[1] = points[1];
            if(numberOfSteps>=2) optimal[2] = points[1] + points[2];
            if(numberOfSteps>=3) optimal[3] = points[3] + Math.max(points[1],points[2]);
            for(int index=4; index<=numberOfSteps; index++) {
                int candidate1 = points[index] + points[index-1] + optimal[index-3];
                int candidate2 = points[index] + optimal[index-2];
                optimal[index] = Math.max(candidate1,candidate2);
            }
            builder.append(optimal[numberOfSteps]);
            out.write(builder.toString());
            out.flush();
        }
    }
    static class P1463_2 {
        private static int unprocessedNumber;
        private static int[] optimum;

        private static boolean isInRange(int number) {
            if(1<=number && number<=unprocessedNumber) return true;
            else return false;
        }
        static void execute() throws IOException {
            unprocessedNumber = Integer.parseInt(in.readLine());
            optimum = new int[unprocessedNumber+1];
            for(int number=unprocessedNumber; number>=1; number--) {
                if(isInRange(number*3)) {
                    int candidate1 = optimum[number*3] + 1;
                    int candidate2 = optimum[number*2] + 1;
                    int candidate3 = optimum[number+1] + 1;
                    optimum[number] = Math.min(Math.min(candidate1,candidate2),candidate3);
                }
                else if(isInRange(number*2)) {
                    int candidate1 = optimum[number*2] + 1;
                    int candidate2 = optimum[number+1] + 1;
                    optimum[number] = Math.min(candidate1,candidate2);
                }
                else if(isInRange(number+1)) {
                    optimum[number] = optimum[number+1] + 1;
                }
            }
            builder.append(optimum[1]);
            out.write(builder.toString());
            out.flush();
        }
    }
    static class P10844_2 {
        private static final int closingDigit = 9;
        private static final int divider = 1_000_000_000;
        private static int targetLength;
        private static int[][] numberOfCases;

        static void execute() throws IOException {
            targetLength = Integer.parseInt(in.readLine());
            numberOfCases = new int[targetLength+1][closingDigit+1];
            for(int digit=1; digit<=closingDigit; digit++) {
                numberOfCases[1][digit]=1;
            }
            for(int index=2; index<=targetLength; index++) {
                numberOfCases[index][0] = numberOfCases[index-1][1];
                for(int digit=1; digit<closingDigit; digit++) {
                    numberOfCases[index][digit] = numberOfCases[index-1][digit-1] + numberOfCases[index-1][digit+1];
                    numberOfCases[index][digit] %= divider;
                }
                numberOfCases[index][9] = numberOfCases[index-1][8];
            }
            int numberOfTotalCases = 0;
            for(int digit=0; digit<=closingDigit; digit++) {
                numberOfTotalCases += numberOfCases[targetLength][digit];
                numberOfTotalCases %= divider;
            }
            builder.append(numberOfTotalCases);
            out.write(builder.toString());
            out.flush();
        }
    }
    static class P2156_2 {
        private static int numberOfGlasses;
        private static int[] volumes;
        private static int[] optimum;

        static void execute() throws IOException {
            numberOfGlasses = Integer.parseInt(in.readLine());
            volumes = new int[numberOfGlasses+1];
            optimum = new int[numberOfGlasses+1];
            for(int index=1; index<=numberOfGlasses; index++) {
                volumes[index] = Integer.parseInt(in.readLine());
            }
            if(numberOfGlasses>=1) optimum[1] = volumes[1];
            if(numberOfGlasses>=2) optimum[2] = volumes[2] + volumes[1];
            if(numberOfGlasses>=3) {
                int candidate1 = volumes[2] + volumes[3];
                int candidate2 = volumes[3] + volumes[1];
                int candidate3 = volumes[1] + volumes[2];
                optimum[3] = Math.max(Math.max(candidate1,candidate2),candidate3);
            }
            for(int index=4; index<=numberOfGlasses; index++) {
                /*
                ___XOO
                ____XO
                _____X
                 */
                int candidate1 = volumes[index] + volumes[index-1] + optimum[index-3];
                int candidate2 = volumes[index] + optimum[index-2];
                int candidate3 = optimum[index-1];
                optimum[index] = Math.max(Math.max(candidate1,candidate2),candidate3);
            }
            builder.append(optimum[numberOfGlasses]);
            out.write(builder.toString());
            out.flush();
        }
    }
    static class P11053_2 {
        private static int numberOfElements;
        private static int[] elements;
        private static int[] memoization;
        private static int maximumLength = -1;

        public static void execute() throws IOException {
            numberOfElements = Integer.parseInt(in.readLine());
            elements = new int[numberOfElements+1];
            memoization = new int[numberOfElements+1];

            tokenizer = new StringTokenizer(in.readLine());
            for(int index=1; index<=numberOfElements; index++) {
                elements[index] = Integer.parseInt(tokenizer.nextToken());
                for(int traverse=0; traverse<index; traverse++) {
                    if(elements[traverse] < elements[index] && memoization[traverse]+1 > memoization[index]) {
                        memoization[index] = memoization[traverse] + 1;
                    }
                }
                if(maximumLength < memoization[index]) {
                    maximumLength = memoization[index];
                }
            }

            builder.append(maximumLength);
            out.write(builder.toString());
            out.flush();
        }
    }
    static class P11054_2 {
        private static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        private static StringTokenizer tokenizer;
        private static int numberOfTotal;
        private static int[] elements;

        public static void execute() throws IOException {
            numberOfTotal = Integer.parseInt(in.readLine());
            elements = new int[numberOfTotal + 1];
            tokenizer = new StringTokenizer(in.readLine());
            for (int index = 1; index <= numberOfTotal; index++) {
                elements[index] = Integer.parseInt(tokenizer.nextToken());
            }

            int[] lis = new int[numberOfTotal + 1];
            int[] lds = new int[numberOfTotal + 1];

            for (int i = 1; i <= numberOfTotal; i++) {
                lis[i] = 1;
                for (int j = 1; j < i; j++) {
                    if (elements[j] < elements[i]) {
                        lis[i] = Math.max(lis[i], lis[j] + 1);
                    }
                }
            }

            for (int i = numberOfTotal; i >= 1; i--) {
                lds[i] = 1;
                for (int j = numberOfTotal; j > i; j--) {
                    if (elements[j] < elements[i]) {
                        lds[i] = Math.max(lds[i], lds[j] + 1);
                    }
                }
            }

            int maxLength = 0;
            for (int i = 1; i <= numberOfTotal; i++) {
                maxLength = Math.max(maxLength, lis[i] + lds[i] - 1);
            }

            System.out.println(maxLength);
        }
    }
//    second try: incomplete
}