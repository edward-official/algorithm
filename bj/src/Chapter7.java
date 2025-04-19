import java.io.*;
import java.util.Stack;
import java.util.StringTokenizer;

public class Chapter7 {
    static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));


    static void p2738() {
        try {
            StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");
            int n = Integer.parseInt(stringTokenizer.nextToken()), m = Integer.parseInt(stringTokenizer.nextToken());
            int[][] array = new int[n][m];
            for(int loop=0; loop<2; loop++) {
                for(int i=0; i<n; i++) {
                    stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");
                    for(int j=0; j<m; j++) {
                        array[i][j] += Integer.parseInt(stringTokenizer.nextToken());
                    }
                }
            }
            for(int i=0; i<n; i++) {
                for(int j=0; j<m; j++) {
                    bufferedWriter.write(array[i][j] + " ");
                }
                bufferedWriter.newLine();
            }
            bufferedWriter.flush();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
    static void p2566() {
        final int size = 9;
        int maximum=-1, index1=-1, index2=-1;
        try {
            StringTokenizer stringTokenizer;
            for(int i=0; i<size; i++) {
                stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");
                for(int j=0; j<size; j++) {
                    int temp = Integer.parseInt(stringTokenizer.nextToken());
                    if(maximum<temp) {
                        maximum = temp;
                        index1 = i+1;
                        index2 = j+1;
                    }
                }
            }
            bufferedWriter.write("" + maximum);
            bufferedWriter.newLine();
            bufferedWriter.write(index1 + " " + index2);
            bufferedWriter.flush();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
    static void p10798() {
        final int loop = 5;
        Stack<String> texts = new Stack<>();
        int longestLength = -1;
        try {
            for(int i=0; i<loop; i++) {
                texts.push(bufferedReader.readLine());
                if(longestLength < texts.peek().length()) {
                    longestLength = texts.peek().length();
                }
            }
            for(int column=0; column<longestLength; column++) {
                for(int row=0; row<loop; row++) {
                    if(column>=texts.get(row).length()) continue;
                    else bufferedWriter.write(texts.get(row).charAt(column));
                }
            }
            bufferedWriter.flush();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
    static void p2563() {
        final int boardSize = 100, paperSize = 10;
        boolean[][] board = new boolean[boardSize][boardSize];
        try {
            int n = Integer.parseInt(bufferedReader.readLine()), count = 0;
            for(int i=0; i<n; i++) {
                StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");
                int index1 = Integer.parseInt(stringTokenizer.nextToken()), index2 = Integer.parseInt(stringTokenizer.nextToken());
                for(int row=index1; row<index1+paperSize; row++) {
                    for(int column=index2; column<index2+paperSize; column++) {
                        if(board[row][column]==false) {
                            board[row][column]=true;
                            count++;
                        }
                    }
                }
            }
            bufferedWriter.write("" + count);
            bufferedWriter.flush();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
}