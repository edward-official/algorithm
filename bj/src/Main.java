import java.awt.*;
import java.io.*;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;


public class Main {
    static class Chapter20 {
        static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
        static StringBuilder builder = new StringBuilder();
        static StringTokenizer tokenizer;

        static class P15649 {
            static int numberOfOptions;
            static int numberOfSelects;
            static int[] result;
            static boolean[] isUsed;

            static void recursive(int sequence) {
                if(sequence==numberOfSelects) {
                    for(int index=0; index<numberOfSelects; index++) builder.append(result[index]).append(" ");
                    builder.append("\n");
                    return;
                }
                for(int element=1; element<=numberOfOptions; element++) {
                    if(!isUsed[element]) {
                        result[sequence] = element;
                        isUsed[element] = true;
                        recursive(sequence+1);
                        isUsed[element] = false;
                    }
                }
            }
            static void run() throws IOException {
                tokenizer = new StringTokenizer(in.readLine());
                numberOfOptions = Integer.parseInt(tokenizer.nextToken());
                numberOfSelects = Integer.parseInt(tokenizer.nextToken());
                result = new int[numberOfSelects];
                isUsed = new boolean[numberOfOptions+1];
                recursive(0);
                out.write(builder.toString());
                out.flush();
            }
        }
        static class P15650 {
            static int numberOfOptions;
            static int numberOfSelects;
            static int[] result;
            static boolean[] isUsed;

            static void recursive(int sequence) {
                if(sequence==numberOfSelects) {
                    for(int index=0; index<numberOfSelects; index++) builder.append(result[index]).append(" ");
                    builder.append("\n");
                    return;
                }
                for(int element=(sequence==0)?1:result[sequence-1]+1; element<=numberOfOptions; element++) {
                    if(!isUsed[element]) {
                        result[sequence] = element;
                        isUsed[element] = true;
                        recursive(sequence+1);
                        isUsed[element] = false;
                    }
                }
            }
            static void run() throws IOException {
                tokenizer = new StringTokenizer(in.readLine());
                numberOfOptions = Integer.parseInt(tokenizer.nextToken());
                numberOfSelects = Integer.parseInt(tokenizer.nextToken());
                result = new int[numberOfSelects];
                isUsed = new boolean[numberOfOptions+1];
                recursive(0);
                out.write(builder.toString());
                out.flush();
            }
        }
        static class P15651 {
            static int numberOfOptions;
            static int numberOfSelects;
            static int[] result;

            static void recursive(int sequence) {
                if(sequence==numberOfSelects) {
                    for(int index=0; index<numberOfSelects; index++) builder.append(result[index]).append(" ");
                    builder.append("\n");
                    return;
                }
                for(int element=1; element<=numberOfOptions; element++) {
                    result[sequence] = element;
                    recursive(sequence+1);
                }
            }
            static void run() throws IOException {
                tokenizer = new StringTokenizer(in.readLine());
                numberOfOptions = Integer.parseInt(tokenizer.nextToken());
                numberOfSelects = Integer.parseInt(tokenizer.nextToken());
                result = new int[numberOfSelects];
                recursive(0);
                out.write(builder.toString());
                out.flush();
            }
        }
        static class P15652 {
            static int numberOfOptions;
            static int numberOfSelects;
            static int[] result;

            static void recursive(int sequence) {
                if(sequence==numberOfSelects) {
                    for(int index=0; index<numberOfSelects; index++) builder.append(result[index]).append(" ");
                    builder.append("\n");
                    return;
                }
                for(int element=(sequence==0)?1:result[sequence-1]; element<=numberOfOptions; element++) {
                    result[sequence] = element;
                    recursive(sequence+1);
                }
            }
            static void run() throws IOException {
                tokenizer = new StringTokenizer(in.readLine());
                numberOfOptions = Integer.parseInt(tokenizer.nextToken());
                numberOfSelects = Integer.parseInt(tokenizer.nextToken());
                result = new int[numberOfSelects];
                recursive(0);
                out.write(builder.toString());
                out.flush();
            }
        }
        static class P9663 {
            static int width, numberOfSolutions;
            static boolean[] isRowOccupied;
            static boolean[] isIncreasingDiagonalOccupied;
            static boolean[] isDecreasingDiagonalOccupied;
            static int[] selectedRow;

            static boolean isAvailable(int row, int column) {
                if(isRowOccupied[row]) return false;
                else if(isIncreasingDiagonalOccupied[row+column]) return false;
                else if(isDecreasingDiagonalOccupied[row-column+width-1]) return false;
                return true;
            }
            static void setOccupation(boolean value, int row, int column) {
                isRowOccupied[row] = value;
                isIncreasingDiagonalOccupied[row+column] = value;
                isDecreasingDiagonalOccupied[row-column+width-1] = value;
            }
            static void recursive(int column) {
                if(column==width) {
//                    for(int index=0; index<width; index++) builder.append(selectedRow[index]+1).append(" ");
//                    builder.append("\n");
                    numberOfSolutions++;
                    return;
                }
                for(int row=0; row<width; row++) {
                    if(isAvailable(row, column)) {
                        selectedRow[column] = row;
                        setOccupation(true, row, column);
                        recursive(column+1);
                        setOccupation(false, row, column);
                        for(int updatingColumn=0; updatingColumn<column; updatingColumn++) {
                            setOccupation(true, selectedRow[updatingColumn], updatingColumn);
                        }
                    }
                }
            }
            static void run() throws IOException {
                width = Integer.parseInt(in.readLine());
                numberOfSolutions = 0;
                isRowOccupied = new boolean[width];
                isIncreasingDiagonalOccupied = new boolean[width*2-1];
                isDecreasingDiagonalOccupied = new boolean[width*2-1];
                selectedRow = new int[width];
                recursive(0);
                builder.append(numberOfSolutions);
                out.write(builder.toString());
                out.flush();
            }
        }
        static class P2580 {
            static final int width = 9;
            static final int aThirdOfWidth = width/3;
            static int[][] board;
            static ArrayList<Integer> blankedRow = new ArrayList<>();
            static ArrayList<Integer> blankedColumn = new ArrayList<>();
            static int numberOfBlanked;
            static boolean isFirstSolutionFound = false;

            private static void terminalBoard() {
                for(int row=0; row<width; row++) {
                    if(row%aThirdOfWidth==0) System.err.println();
                    for(int column=0; column<width; column++) {
                        if(column%aThirdOfWidth==0) System.err.printf(" ");
                        System.err.printf(board[row][column] + " ");
                    }
                    System.err.println();
                }
                System.err.println();
            }
            private static boolean isElementAvailable(int element, int row, int column) {
                for(int searchingIndex=0; searchingIndex<width; searchingIndex++) {
                    if(board[searchingIndex][column]==element) return false;
                    else if(board[row][searchingIndex]==element) return false;
                }
                int offsetOfRow = row/aThirdOfWidth*aThirdOfWidth;
                int offsetOfColumn = column/aThirdOfWidth*aThirdOfWidth;
                for(int searchingIndexForRow=offsetOfRow; searchingIndexForRow<offsetOfRow+aThirdOfWidth; searchingIndexForRow++) {
                    for(int searchingIndexForColumn=offsetOfColumn; searchingIndexForColumn<offsetOfColumn+aThirdOfWidth; searchingIndexForColumn++) {
                        if(board[searchingIndexForRow][searchingIndexForColumn]==element) return false;
                    }
                }
                return true;
            }
            private static void recursive(int sequence) {
                if(isFirstSolutionFound) return;
                else if(sequence==numberOfBlanked) {
                    isFirstSolutionFound = true;
                    for(int row=0; row<width; row++) {
                        for(int column=0; column<width; column++) {
                            builder.append(board[row][column]).append(" ");
                        }
                        builder.append("\n");
                    }
                    return;
                }

                int currentRow = blankedRow.get(sequence);
                int currentColumn = blankedColumn.get(sequence);
                for(int element=1; element<=width; element++) {
                    if(isElementAvailable(element, currentRow, currentColumn)) {
//                        System.err.println("sequence: " + sequence + ", element[" + currentRow + "][" + currentColumn + "]: " + element);
                        board[currentRow][currentColumn] = element;
//                        terminalBoard();
                        recursive(sequence+1);
                        board[currentRow][currentColumn] = 0;
                    }
                }
            }
            static void run() throws IOException {
                board = new int[width][width];
                for(int row=0; row<width; row++) {
                    tokenizer = new StringTokenizer(in.readLine());
                    for(int column=0; column<width; column++) {
                        board[row][column] = Integer.parseInt(tokenizer.nextToken());
                        if(board[row][column]==0) {
                            blankedRow.add(row);
                            blankedColumn.add(column);
                        }
                    }
                }
                numberOfBlanked = blankedRow.size();
                recursive(0);
                out.write(builder.toString());
                out.flush();
            }
        }
        static class P14888 {
            private static int numberOfElements;
            private static int[] elements;
            private static int numberOfOperators;
            private static char[] operators;
            private static char[] operatorsInSequence;
            private static boolean[] isOperatorUsed;
            private static int max, min;
            private static boolean isFirstResult = true;

            private static int calculate() {
                int result = elements[0];
                for(int indexForOperatorInSequence=0; indexForOperatorInSequence<numberOfOperators; indexForOperatorInSequence++) {
                    if(operatorsInSequence[indexForOperatorInSequence]=='+') result+=elements[indexForOperatorInSequence+1];
                    else if(operatorsInSequence[indexForOperatorInSequence]=='-') result-=elements[indexForOperatorInSequence+1];
                    else if(operatorsInSequence[indexForOperatorInSequence]=='*') result*=elements[indexForOperatorInSequence+1];
                    else if(operatorsInSequence[indexForOperatorInSequence]=='/') result/=elements[indexForOperatorInSequence+1];
                }
                return result;
            }
            private static void recursive(int sequence) {
                if(sequence==numberOfOperators) {
                    int result = calculate();
                    if(isFirstResult) {
                        max = result;
                        min = result;
                        isFirstResult = false;
                    }
                    else {
                        if(max<result) max=result;
                        else if(result<min) min=result;
                    }
                }
                for(int index=0; index<numberOfOperators; index++) {
                    if(!isOperatorUsed[index]) {
                        operatorsInSequence[sequence] = operators[index];
                        isOperatorUsed[index] = true;
                        recursive(sequence+1);
                        isOperatorUsed[index] = false;
                    }
                }
            }
            static void run() throws IOException {
                numberOfElements = Integer.parseInt(in.readLine());
                elements = new int[numberOfElements];
                numberOfOperators = numberOfElements-1;
                operators = new char[numberOfOperators];
                operatorsInSequence = new char[numberOfOperators];
                isOperatorUsed = new boolean[numberOfOperators];

                tokenizer = new StringTokenizer(in.readLine());
                for(int index=0; index<numberOfElements; index++) {
                    elements[index] = Integer.parseInt(tokenizer.nextToken());
                }
                tokenizer = new StringTokenizer(in.readLine());
                int indexForOperators = 0;
                for(int typeOfOperator=0; typeOfOperator<4; typeOfOperator++) {
                    char opeator;
                    if(typeOfOperator==0) opeator='+';
                    else if(typeOfOperator==1) opeator='-';
                    else if(typeOfOperator==2) opeator='*';
                    else opeator='/';
                    int closingRepetition = Integer.parseInt(tokenizer.nextToken());
                    for(int n=0; n<closingRepetition; n++) operators[indexForOperators++]=opeator;
                }

                recursive(0);
                builder.append(max).append("\n");
                builder.append(min).append("\n");
                out.write(builder.toString());
                out.flush();
            }
        }
    }

    public static void main(String[] args) {
        try {
            Chapter20.P14888.run();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
}