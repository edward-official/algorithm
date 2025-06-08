import java.io.*;
import java.util.StringTokenizer;

public class Chapter22 {
    private static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
    private static StringBuilder builder = new StringBuilder();
    private static StringTokenizer tokenizer;

    static class P11659 {
        private static int numberOfElements;
        private static int numberOfLoops;
        private static int[] elements;
        private static int[] accumulated;

        static void execute() throws IOException {
            tokenizer = new StringTokenizer(in.readLine());
            numberOfElements = Integer.parseInt(tokenizer.nextToken());
            numberOfLoops = Integer.parseInt(tokenizer.nextToken());
            elements = new int[numberOfElements];
            accumulated = new int[numberOfElements];
            tokenizer = new StringTokenizer(in.readLine());
            for(int index=0; index<numberOfElements; index++) {
                elements[index] = Integer.parseInt(tokenizer.nextToken());
            }

            accumulated[0] = elements[0];
            for(int index=1; index<numberOfElements; index++) {
                accumulated[index] = elements[index] + accumulated[index-1];
            }

            for(int loop=0; loop<numberOfLoops; loop++) {
                tokenizer = new StringTokenizer(in.readLine());
                int from = Integer.parseInt(tokenizer.nextToken())-1;
                int to = Integer.parseInt(tokenizer.nextToken())-1;
                builder.append(accumulated[to]-accumulated[from]+elements[from]);
                builder.append("\n");
            }

            out.write(builder.toString());
            out.flush();
        }
    }
    static class P2559 {
        private static int quantity;
        private static int streak;
        private static int[] elements;

        static void execute() throws IOException {
            tokenizer = new StringTokenizer(in.readLine());
            quantity = Integer.parseInt(tokenizer.nextToken());
            streak = Integer.parseInt(tokenizer.nextToken());
            elements = new int[quantity];
            tokenizer = new StringTokenizer(in.readLine());
            for(int index=0; index<quantity; index++) elements[index]=Integer.parseInt(tokenizer.nextToken());

            int answer = 0;
            for(int index=0; index<streak; index++) answer+=elements[index];
            for(int startingIndex=1; startingIndex<=quantity-streak; startingIndex++) {
                int temp = 0;
                for(int offset=0; offset<streak; offset++) temp+=elements[startingIndex+offset];
                if(answer<temp) answer=temp;
            }

            builder.append(answer);
            out.write(builder.toString());
            out.flush();
        }
    }
    static class P16139 {
        private static String element;
        private static int numberOfLoops;
        private static int[][] record;

        static void execute() throws IOException {
            element = in.readLine();
            numberOfLoops = Integer.parseInt(in.readLine());

            record = new int['z'-'a'+1][element.length()];
            for(int index1=0; index1<'z'-'a'+1; index1++) {
                char target = (char)('a'+index1);
                if(element.charAt(0)==target) record[index1][0]=1;
                else record[index1][0]=0;
                for(int index2=1; index2<element.length(); index2++) {
                    if(element.charAt(index2)==target) record[index1][index2] = record[index1][index2-1]+1;
                    else record[index1][index2] = record[index1][index2-1];
                }
            }

            for(int loop=0; loop<numberOfLoops; loop++) {
                tokenizer = new StringTokenizer(in.readLine());
                char target = tokenizer.nextToken().charAt(0);
                int from = Integer.parseInt(tokenizer.nextToken());
                int to = Integer.parseInt(tokenizer.nextToken());
                int result = record[target-'a'][to] - record[target-'a'][from];
                if(element.charAt(from)==target) result+=1;
                builder.append(result).append("\n");
            }
            out.write(builder.toString());
            out.flush();
        }
    }
    static class P10986 {
        private static int length;
        private static int target;
        private static long[] cumulative;
        private static long[] countsOfRemainder;
        private static long answer;

        static void execute() throws IOException {
            tokenizer = new StringTokenizer(in.readLine());
            length = Integer.parseInt(tokenizer.nextToken());
            target = Integer.parseInt(tokenizer.nextToken());
            cumulative = new long[length+1];
            countsOfRemainder = new long[target];

            tokenizer = new StringTokenizer(in.readLine());
            for(int index=1; index<=length; index++) {
                cumulative[index] = (cumulative[index-1] + Long.parseLong(tokenizer.nextToken()));
                countsOfRemainder[(int)(cumulative[index] % target)]++;
            }

            answer += countsOfRemainder[0];
            for(int index=0; index<target; index++) {
                answer += countsOfRemainder[index] * (countsOfRemainder[index]-1) / 2;
            }

            builder.append(answer);
            out.write(builder.toString());
            out.flush();
        }
    }
    static class P11660 {
        private static int size;
        private static int numberOfLoops;
        private static int[][] cumulative;

        static void execute() throws IOException {
            tokenizer = new StringTokenizer(in.readLine());
            size = Integer.parseInt(tokenizer.nextToken());
            numberOfLoops = Integer.parseInt(tokenizer.nextToken());

            cumulative = new int[size+1][size+1];
            for(int column=1; column<=size; column++) {
                tokenizer = new StringTokenizer(in.readLine());
                for(int row=1; row<=size; row++) {
                    cumulative[column][row] = cumulative[column][row-1] + Integer.parseInt(tokenizer.nextToken());
                }
            }

            for(int loop=0; loop<numberOfLoops; loop++) {
                int answer = 0;
                tokenizer = new StringTokenizer(in.readLine());
                int column1 = Integer.parseInt(tokenizer.nextToken());
                int row1 = Integer.parseInt(tokenizer.nextToken());
                int column2 = Integer.parseInt(tokenizer.nextToken());
                int row2 = Integer.parseInt(tokenizer.nextToken());
                for(int column=column1; column<=column2; column++) {
                    answer += cumulative[column][row2] - cumulative[column][row1-1];
                }
                builder.append(answer).append("\n");
            }

            out.write(builder.toString());
            out.flush();
        }
    }
    static class P25682 {
        private static int size1;
        private static int size2;
        private static int size;
        private static char[][] table;
        private static int[][] cumulative;

        static void execute() throws IOException {
            tokenizer = new StringTokenizer(in.readLine());
            size1 = Integer.parseInt(tokenizer.nextToken());
            size2 = Integer.parseInt(tokenizer.nextToken());
            size = Integer.parseInt(tokenizer.nextToken());
            table = new char[size1+1][size2+1];
            for(int index1=1; index1<=size1; index1++) {
                String temp = in.readLine();
                for(int index2=1; index2<=size2; index2++) {
                    table[index1][index2] = temp.charAt(index2-1);
                }
            }

            cumulative = new int[size1+1][size2+1]; //'B' on the even
            for(int index1=1; index1<=size1; index1++) {
                for(int index2=1; index2<=size2; index2++) {
                    char expected = ((index1 + index2) % 2 == 0) ? 'B' : 'W';
                    cumulative[index1][index2] = (table[index1][index2] == expected) ? 0 : 1;
                    cumulative[index1][index2] += cumulative[index1-1][index2] + cumulative[index1][index2-1] - cumulative[index1-1][index2-1];
                }
            }

            int answer = size*size;
            for(int closingX=size; closingX<=size1; closingX++) {
                for(int closingY=size; closingY<=size2; closingY++) {
                    int openingX = closingX - size + 1;
                    int openingY = closingY - size + 1;

                    int count = cumulative[closingX][closingY] - cumulative[openingX-1][closingY] - cumulative[closingX][openingY-1] + cumulative[openingX-1][openingY-1];
                    int min = Math.min(count, size * size - count);
                    answer = Math.min(answer, min);
                }
            }

            builder.append(answer);
            out.write(builder.toString());
            out.flush();
        }
    }
}