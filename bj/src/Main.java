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
    }

    public static void main(String[] args) {
        try {
            Chapter22.P11659.execute();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
}