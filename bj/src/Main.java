import java.awt.*;
import java.io.*;
import java.util.*;

public class Main {
    private static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
    private static StringTokenizer tokenizer;
    private static StringBuilder builder = new StringBuilder();

    static class P15649_3 {
        private static int n, m;
        private static int[] array;
        private static boolean[] isUsed;

        private static void recursive(int index) {
            if(index > m) {
                builder.append(array[1]);
                for(int traverse=2; traverse<=m; traverse++) {
                    builder.append(" ").append(array[traverse]);
                }
                builder.append("\n");
                return;
            }

            for(int element=1; element<=n; element++) {
                if(isUsed[element]) continue;
                array[index] = element;
                isUsed[element] = true;
                recursive(index + 1);
                isUsed[element] = false;
            }
        }
        public static void execute() throws IOException {
            tokenizer = new StringTokenizer(in.readLine());
            n = Integer.parseInt(tokenizer.nextToken());
            m = Integer.parseInt(tokenizer.nextToken());
            array = new int[m+1];
            isUsed = new boolean[n+1];
            recursive(1);
            out.write(builder.toString());
            out.flush();
        }
    }
    static class P15650_3 {
        private static int n, m;
        private static int[] array;

        private static void recursive(int index) {
            if(index>m) {
                builder.append(array[1]);
                for(int traverse=2; traverse<=m; traverse++) {
                    builder.append(" ").append(array[traverse]);
                }
                builder.append("\n");
                return;
            }
            for(int element=array[index-1]+1; element<=n; element++) {
                array[index] = element;
                recursive(index+1);
            }
        }
        public static void execute() throws IOException {
            tokenizer = new StringTokenizer(in.readLine());
            n = Integer.parseInt(tokenizer.nextToken());
            m = Integer.parseInt(tokenizer.nextToken());

            array = new int[m+1];
            recursive(1);

            out.write(builder.toString());
            out.flush();
        }
    }
    static class P15651_3 {
        private static int n, m;
        private static int[] array;

        private static void recursive(int index) {
            if(index > m) {
                builder.append(array[1]);
                for(int traverse=2; traverse<=m; traverse++) {
                    builder.append(" ").append(array[traverse]);
                }
                builder.append("\n");
                return;
            }

            for(int element=1; element<=n; element++) {
                array[index] = element;
                recursive(index+1);
            }
        }
        public static void execute() throws IOException {
            tokenizer = new StringTokenizer(in.readLine());
            n = Integer.parseInt(tokenizer.nextToken());
            m = Integer.parseInt(tokenizer.nextToken());
            array = new int[m+1];
            recursive(1);
            out.write(builder.toString());
            out.flush();
        }
    }

    public static void main(String[] args) {
        try {
            P15651_3.execute();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
}