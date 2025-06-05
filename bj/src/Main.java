import java.awt.*;
import java.io.*;
import java.lang.annotation.Target;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;


public class Main {
    static class Chapter22 {
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
            private static HashMap<Character, int[]> record = new HashMap<>();

            static int process() throws IOException {
                tokenizer = new StringTokenizer(in.readLine());
                char target = tokenizer.nextToken().charAt(0);
                int from = Integer.parseInt(tokenizer.nextToken());
                int to = Integer.parseInt(tokenizer.nextToken());

                if(record.containsKey(target)) {
                    int count = record.get(target)[to]-record.get(target)[from];
                    if(target==element.charAt(from)) count+=1;
                    return count;
                }

                int[] counts = new int[element.length()];
                if(element.charAt(0)==target) counts[0]=1;
                else counts[0]=0;
                for(int index=1; index<element.length(); index++) {
                    if(element.charAt(index)==target) counts[index]=counts[index-1]+1;
                    else counts[index]=counts[index-1];
                }
                record.put(target,counts);

                int count = counts[to]-counts[from];
                if(target==element.charAt(from)) count+=1;
                return count;
            }
            static void execute() throws IOException {
                element = in.readLine();
                numberOfLoops = Integer.parseInt(in.readLine());
                for(int loop=0; loop<numberOfLoops; loop++) builder.append(process()).append("\n");
                out.write(builder.toString());
                out.flush();
            }
        }
    }

    public static void main(String[] args) {
        try {
            Chapter22.P16139.execute();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
}