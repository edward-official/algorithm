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

        private static boolean isPossible(int row, int column) {
            for(int traverse=1; traverse<row; traverse++) {
                if(board[traverse]==column) return false;
                else if(board[traverse]==row+column-traverse) return false;
                else if(board[traverse]==column-row+traverse) return false;
            }
            return true;
        }
        private static void recursive(int row) {
            if(row>n) {
                numberOfCases++;
                return;
            }
            for(int column=1; column<=n; column++) {
                if(isPossible(row, column)) {
                    board[row] = column;
                    recursive(row+1);
                }
            }
        }
        public static void execute() throws IOException {
            n = Integer.parseInt(in.readLine());
            board = new int[n+1];
            recursive(1);
            builder.append(numberOfCases);
            out.write(builder.toString());
            out.flush();
        }
    }
    static class P2580_3 {
        private static int length = 9;
        private static int[][] board;
        private static int[] checkRows;
        private static int[] checkColumns;
        private static int numberOfCheckPoints = 0;
        private static boolean didFound = false;

        private static void boardOnTerminal() throws IOException {
            for(int row=1; row<=length; row++) {
                builder.append(board[row][1]);
                for(int column=2; column<=length; column++) {
                    builder.append(" ").append(board[row][column]);
                }
                builder.append("\n");
            }
            out.write(builder.toString());
            out.flush();
        }
        private static boolean isPossible(int element, int row, int column) {
            for(int traverse=1; traverse<=length; traverse++) {
                if(board[traverse][column]==element) return false;
                else if(board[row][traverse]==element) return false;
            }
            int closingRow = ((row-1)/3+1)*3;
            int closingColumn = ((column-1)/3+1)*3;
            for(int r=closingRow-2; r<=closingRow; r++) {
                for(int c=closingColumn-2; c<=closingColumn; c++) {
                    if(board[r][c]==element) return false;
                }
            }
            return true;
        }
        private static void recursive(int index) throws IOException {
            if(didFound) return;
            if(index>numberOfCheckPoints) {
                didFound = true;
                boardOnTerminal();
                return;
            }
            for(int element=1; element<=length; element++) {
                if(isPossible(element,checkRows[index],checkColumns[index])) {
                    board[checkRows[index]][checkColumns[index]] = element;
                    recursive(index+1);
                    board[checkRows[index]][checkColumns[index]] = 0;
                }
            }
        }
        public static void execute() throws IOException {
            board = new int[length+1][length+1];
            checkRows = new int[length*length+1];
            checkColumns = new int[length*length+1];

            for(int row=1; row<=length; row++) {
                tokenizer = new StringTokenizer(in.readLine());
                for(int column=1; column<=length; column++) {
                    board[row][column] = Integer.parseInt(tokenizer.nextToken());
                    if(board[row][column]==0) {
                        numberOfCheckPoints++;
                        checkRows[numberOfCheckPoints] = row;
                        checkColumns[numberOfCheckPoints] = column;
                    }
                }
            }

            recursive(1);
        }
    }

    public static void main(String[] args) {
        try {
            P2580_3.execute();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
}