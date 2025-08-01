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
    static class P15652_3 {
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
            for(int element=array[index-1]; element<=n; element++) {
                array[index] = element;
                recursive(index+1);
            }
        }
        public static void execute() throws IOException {
            tokenizer = new StringTokenizer(in.readLine());
            n = Integer.parseInt(tokenizer.nextToken());
            m = Integer.parseInt(tokenizer.nextToken());
            array = new int[m+1];
            array[0] = 1;
            recursive(1);
            out.write(builder.toString());
            out.flush();
        }
    }
    static class P9663_3 {
        private static int n, numberOfCases = 0;
        private static int[] board;

        private static void putQueen(int row, int column) {
            board[row] = column;
        }
        private static void removeQueen(int row, int column) {
            board[row] = 0;
        }
        private static boolean isValid(int row, int column) {
            for (int i = 1; i < row; i++) {
                if (board[i] == column || Math.abs(i - row) == Math.abs(board[i] - column)) {
                    return false;
                }
            }
            return true;
        }
        private static void recursive(int row) {
            if (row > n) {
                numberOfCases++;
                return;
            }

            for (int col = 1; col <= n; col++) {
                if (isValid(row, col)) {
                    putQueen(row, col);
                    recursive(row + 1);
                    removeQueen(row, col);
                }
            }
        }

        public static void execute() throws IOException {
            n = Integer.parseInt(in.readLine());
            board = new int[n + 1];
            recursive(1);
            builder.append(numberOfCases);
            out.write(builder.toString());
            out.flush();
        }
    }

    public static void main(String[] args) {
        try {
            P9663_3.execute();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
}