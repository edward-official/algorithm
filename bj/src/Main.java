import java.awt.*;
import java.io.*;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;


public class Main {
    static class Chapter13 {
        static Scanner in = new Scanner(System.in);
        static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        static void p2750() throws IOException {
            final int sizeOfArray = in.nextInt();
            int[] integers = new int[sizeOfArray];
            for(int index=0; index<sizeOfArray; index++) integers[index] = in.nextInt();
            Arrays.sort(integers);
            for(int index=0; index<sizeOfArray; index++) System.out.println(integers[index]);
        }
        static void p2587() throws IOException {
            int sum = 0;
            final int lengthOfArray = 5;
            int[] values = new int[lengthOfArray];
            for(int index=0; index<lengthOfArray; index++) {
                int t = in.nextInt();
                sum+=t;
                values[index]=t;
            }
            List<Integer> sortedValues = Arrays.stream(values).boxed().sorted().collect(Collectors.toList());
            System.out.println(sum/lengthOfArray);
            System.out.println(sortedValues.get(lengthOfArray/2));
        }
        static void p25305() throws IOException {
            final int numberOfStudents = in.nextInt();
            final int numberOfAwards = in.nextInt();
            int[] grades = new int[numberOfStudents];
            for(int index=0; index<numberOfStudents; index++) grades[index]=in.nextInt();
            List<Integer> sortedGrades = Arrays.stream(grades).boxed().sorted().collect(Collectors.toList());
            System.out.println(sortedGrades.get(numberOfStudents-numberOfAwards));
        }
        static void merge(int[] array, int leftmost, int rightmost) {
            int center = (leftmost+rightmost)/2;
            int[] secondArray = new int[rightmost-leftmost+1];
            int indexForSecondArray = 0;
            int indexForLeftPart = leftmost;
            int indexForRightPart = center+1;

            while(indexForLeftPart<=center && indexForRightPart<=rightmost) {
                if(array[indexForLeftPart] <= array[indexForRightPart]) secondArray[indexForSecondArray++] = array[indexForLeftPart++];
                else secondArray[indexForSecondArray++] = array[indexForRightPart++];
            }
            while(indexForLeftPart<=center) secondArray[indexForSecondArray++] = array[indexForLeftPart++];
            while(indexForRightPart<=rightmost) secondArray[indexForSecondArray++] = array[indexForRightPart++];

            for(int index=leftmost; index<=rightmost; index++) array[index]=secondArray[index-leftmost];
        }
        static void mergesort(int[] array, int leftmost, int rightmost) {
            if(leftmost>=rightmost) return;
            int center = (leftmost+rightmost)/2;

            mergesort(array, leftmost, center);
            mergesort(array, center+1, rightmost);
            merge(array, leftmost, rightmost);
        }
        static void p2751() throws IOException {
            final int sizeOfInput = Integer.parseInt(reader.readLine());
            int[] inputs = new int[sizeOfInput];
            for(int index=0; index<sizeOfInput; index++) inputs[index]=Integer.parseInt(reader.readLine());
            mergesort(inputs, 0, sizeOfInput-1);
            for(int element: inputs) System.out.println(element);
        }
        static void p10989() throws IOException {
            final int sizeOfInput = Integer.parseInt(reader.readLine());
            int[] inputs = new int[sizeOfInput];
            for(int index=0; index<sizeOfInput; index++) inputs[index]=Integer.parseInt(reader.readLine());
            Arrays.sort(inputs);
            StringBuilder stringBuilder = new StringBuilder();
            for(int element: inputs) stringBuilder.append(element + "\n");
            System.out.println(stringBuilder);
        }
    }

    public static void main(String[] args) {
        try {
            Chapter13.p10989();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
}