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
    static class P14888_3 {
        private static int numberOfOperands, kindOfOperators = 4;
        private static int[] operands;
        private static int[] countOfOperators = new int[kindOfOperators+1];
        private static int maxValue = Integer.MIN_VALUE, minValue = Integer.MAX_VALUE;

        private static void recursive(int operatorIndex, int currentValue) {
            if(operatorIndex==numberOfOperands) {
                for(int selector=1; selector<=kindOfOperators; selector++) {
                    if(countOfOperators[selector]==0) continue;
                    if(selector==1) currentValue = currentValue + operands[operatorIndex+1];
                    else if(selector==2) currentValue = currentValue - operands[operatorIndex+1];
                    else if(selector==3) currentValue = currentValue * operands[operatorIndex+1];
                    else if(selector==4) currentValue = currentValue / operands[operatorIndex+1];
                }
                if(currentValue>maxValue) maxValue = currentValue;
                if(currentValue<minValue) minValue = currentValue;
                return;
            }

            for(int selector=1; selector<=kindOfOperators; selector++) {
                if(countOfOperators[selector]==0) continue;
                countOfOperators[selector]--;
                if(selector==1) {
                    recursive(operatorIndex+1, currentValue + operands[operatorIndex+1]);
                }
                else if(selector==2) {
                    recursive(operatorIndex+1, currentValue - operands[operatorIndex+1]);
                }
                else if(selector==3) {
                    recursive(operatorIndex+1, currentValue * operands[operatorIndex+1]);
                }
                else if(selector==4) {
                    recursive(operatorIndex+1, currentValue / operands[operatorIndex+1]);
                }
                countOfOperators[selector]++;
            }
        }
        public static void execute() throws IOException {
            numberOfOperands = Integer.parseInt(in.readLine());
            operands = new int[numberOfOperands+1];
            tokenizer = new StringTokenizer(in.readLine());
            for(int index=1; index<=numberOfOperands; index++) {
                operands[index] = Integer.parseInt(tokenizer.nextToken());
            }
            tokenizer = new StringTokenizer(in.readLine());
            for(int index=1; index<=kindOfOperators; index++) {
                countOfOperators[index] = Integer.parseInt(tokenizer.nextToken());
            }
            recursive(1, operands[1]);

            builder.append(maxValue).append("\n").append(minValue);
            out.write(builder.toString());
            out.flush();
        }
    }
    static class P14889_3 {
        private static int numberOfTotalMembers, numberOfTeamMembers;
        private static int[][] cooperation;
        private static int[] team1, team2;
        private static int minimumDifference = Integer.MAX_VALUE;

        private static int totalAbility(int[] team) {
            int result = 0;
            for(int memberIndex1: team) {
                for(int memberIndex2: team) {
                    result += cooperation[memberIndex1][memberIndex2];
                }
            }
            return result;
        }
        private static void updateTeam2() {
            int indexForTeam2=1;
//            System.out.println(Arrays.toString(team1));
            for(int target=1; target<=numberOfTotalMembers; target++) {
                boolean isTargetInTeam1 = false;
                for(int memberFromTeam1: team1) {
                    if(target==memberFromTeam1) {
                        isTargetInTeam1 = true;
                    }
                }
//                System.out.println(String.format("[%d] isTargetInTeam1: %s", target, isTargetInTeam1));
                if(!isTargetInTeam1) team2[indexForTeam2++] = target;
            }
        }
        private static int evaluateDifference() {
            updateTeam2();
            int result = totalAbility(team1) - totalAbility(team2);
            if(result<0) result = 0 - result;
            return result;
        }
        private static void recursive(int indexForTeam1) {
            if(indexForTeam1>numberOfTeamMembers) {
                int difference = evaluateDifference();
                if(difference<minimumDifference) minimumDifference = difference;
                return;
            }
            for(int selectMember=team1[indexForTeam1-1]+1; selectMember<=numberOfTotalMembers; selectMember++) {
                team1[indexForTeam1] = selectMember;
                recursive(indexForTeam1+1);
            }
        }
        public static void execute() throws IOException {
            numberOfTotalMembers = Integer.parseInt(in.readLine());
            numberOfTeamMembers = numberOfTotalMembers/2;
            cooperation = new int[numberOfTotalMembers+1][numberOfTotalMembers+1];
            team1 = new int[numberOfTeamMembers+1];
            team2 = new int[numberOfTeamMembers+1];
            for(int row=1; row<=numberOfTotalMembers; row++) {
                tokenizer = new StringTokenizer(in.readLine());
                for(int column=1; column<=numberOfTotalMembers; column++) {
                    cooperation[row][column] = Integer.parseInt(tokenizer.nextToken());
                }
            }

            recursive(1);
            builder.append(minimumDifference);
            out.write(builder.toString());
            out.flush();
        }
    }

    public static void main(String[] args) {
        try {
            P14889_3.execute();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
}