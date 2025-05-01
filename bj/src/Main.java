import java.awt.*;
import java.io.*;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;


public class Main {
    static class Chapter13 {
        static Scanner in = new Scanner(System.in);

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
    }

    public static void main(String[] args) {
        try {
            Chapter13.p25305();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
}