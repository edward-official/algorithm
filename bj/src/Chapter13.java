import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

public class Chapter13 {
    static Scanner in = new Scanner(System.in);
    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));


    static class Point {
        int x, y;
        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public String toString() {
            return x + " " + y;
        }
    }
    static class AscendingComparatorXFirst implements Comparator<Point> {
        @Override
        public int compare(Point point1, Point point2) {
            if(point1.x==point2.x) return point1.y-point2.y;
            else return point1.x-point2.x;
        }
    }
    static class AscendingComparatorYFirst implements Comparator<Point> {
        @Override
        public int compare(Point point1, Point point2) {
            if(point1.y==point2.y) return point1.x-point2.x;
            else return point1.y-point2.y;
        }
    }
    static class StringComparator implements Comparator<String> {
        @Override
        public int compare(String item1, String item2) {
            if(item1.length()!=item2.length()) return item1.length()-item2.length();
            else {
                int index=0;
                while(true) {
                    if(item1.charAt(index)!=item2.charAt(index)) return item1.charAt(index)-item2.charAt(index);
                    index++;
                }
            }
        }
    }
    static class Person implements Comparable<Person> {
        int age;
        String name;
        Person(int age, String name) {
            this.age = age;
            this.name = name;
        }

        @Override
        public int compareTo(Person other) {
            return age-other.age;
        }

        @Override
        public String toString() {
            return age + " " + name;
        }
    }


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
    static void increasingMerge(int[] array, int startingIndex, int finalIndex) {
        int center = (startingIndex+finalIndex)/2;
        int[] secondArray = new int[finalIndex-startingIndex+1];
        int indexForSecondArray = 0;
        int indexForLeftPart = startingIndex;
        int indexForRightPart = center+1;

        while(indexForLeftPart<=center && indexForRightPart<=finalIndex) {
            if(array[indexForLeftPart] <= array[indexForRightPart]) secondArray[indexForSecondArray++] = array[indexForLeftPart++];
            else secondArray[indexForSecondArray++] = array[indexForRightPart++];
        }
        while(indexForLeftPart<=center) secondArray[indexForSecondArray++] = array[indexForLeftPart++];
        while(indexForRightPart<=finalIndex) secondArray[indexForSecondArray++] = array[indexForRightPart++];

        for(int index=startingIndex; index<=finalIndex; index++) array[index]=secondArray[index-startingIndex];
    }
    static void increasingMergesort(int[] array, int startingIndex, int finalIndex) {
        if(startingIndex>=finalIndex) return;
        int center = (startingIndex+finalIndex)/2;

        increasingMergesort(array, startingIndex, center);
        increasingMergesort(array, center+1, finalIndex);
        increasingMerge(array, startingIndex, finalIndex);
    }
    static void p2751() throws IOException {
        final int sizeOfInput = Integer.parseInt(reader.readLine());
        int[] points = new int[sizeOfInput];
        for(int index=0; index<sizeOfInput; index++) points[index]=Integer.parseInt(reader.readLine());
        increasingMergesort(points, 0, sizeOfInput-1);
        for(int element: points) System.out.println(element);
    }
    static void p10989() throws IOException {
        final int sizeOfInput = Integer.parseInt(reader.readLine());
        int[] points = new int[sizeOfInput];
        for(int index=0; index<sizeOfInput; index++) points[index]=Integer.parseInt(reader.readLine());
        Arrays.sort(points);
        StringBuilder stringBuilder = new StringBuilder();
        for(int element: points) stringBuilder.append(element + "\n");
        System.out.println(stringBuilder);
    }
    static void decreasingMerge(int[] array, int startingIndex, int finalIndex) {
        int[] assistingArray = new int[finalIndex-startingIndex+1];
        int centerIndex = (startingIndex+finalIndex)/2;
        int indexForAssistingArray = 0;
        int indexForLeftPart = startingIndex;
        int indexForRightPart = centerIndex+1;
        while(indexForLeftPart<=centerIndex && indexForRightPart<=finalIndex) {
            if(array[indexForLeftPart]>=array[indexForRightPart]) assistingArray[indexForAssistingArray++] = array[indexForLeftPart++];
            else assistingArray[indexForAssistingArray++] = array[indexForRightPart++];
        }
        while(indexForLeftPart<=centerIndex) assistingArray[indexForAssistingArray++] = array[indexForLeftPart++];
        while(indexForRightPart<=finalIndex) assistingArray[indexForAssistingArray++] = array[indexForRightPart++];
        int traversingIndex = 0;
        while(traversingIndex<assistingArray.length) {
            array[startingIndex+traversingIndex] = assistingArray[traversingIndex];
            traversingIndex++;
        }
    }
    static void decreasingMergeSort(int[] array, int startingIndex, int finalIndex) {
        if(startingIndex>=finalIndex) return;
        int centerIndex = (startingIndex+finalIndex)/2;

        decreasingMergeSort(array,startingIndex,centerIndex);
        decreasingMergeSort(array,centerIndex+1,finalIndex);
        decreasingMerge(array,startingIndex,finalIndex);
    }
    static void p1427() throws IOException {
        String numberAsString = reader.readLine();
        final int numberOfDigits = numberAsString.length();
        int[] digits = new int[numberOfDigits];
        for(int index=0; index<numberOfDigits; index++) digits[index] = numberAsString.charAt(index) - '0';
        decreasingMergeSort(digits,0,numberOfDigits-1);
        StringBuilder stringBuilder = new StringBuilder();
        for(int item: digits) stringBuilder.append(item);
        System.out.println(stringBuilder);
    }
    static void p11650() throws IOException {
        final int numberOfInputs = Integer.parseInt(reader.readLine());
        Point[] points = new Point[numberOfInputs];
        StringTokenizer tokenizer;
        for(int index=0; index<numberOfInputs; index++) {
            tokenizer = new StringTokenizer(reader.readLine());
            int x = Integer.parseInt(tokenizer.nextToken()), y = Integer.parseInt(tokenizer.nextToken());
            points[index] = new Point(x,y);
        }
        Arrays.sort(points, new AscendingComparatorXFirst());
        for(Point item: points) System.out.println(item);
    }
    static void p11651() throws IOException {
        final int numberOfInputs = Integer.parseInt(reader.readLine());
        Point[] points = new Point[numberOfInputs];
        StringTokenizer tokenizer;
        for(int index=0; index<numberOfInputs; index++) {
            tokenizer = new StringTokenizer(reader.readLine());
            int x = Integer.parseInt(tokenizer.nextToken()), y = Integer.parseInt(tokenizer.nextToken());
            points[index] = new Point(x,y);
        }
        Arrays.sort(points, new AscendingComparatorYFirst());
        for(Point item: points) System.out.println(item);
    }
    static void p1181() throws IOException {
        final int numberOfInputs = Integer.parseInt(reader.readLine());
        HashSet<String> items = new HashSet<>();
        for(int index=0; index<numberOfInputs; index++) {
            items.add(reader.readLine());
        }
        List<String> itemsAsList = items.stream().sorted(new StringComparator()).collect(Collectors.toList());
        StringBuilder stringBuilder = new StringBuilder();
        for(String item: itemsAsList) stringBuilder.append(item).append("\n");
        System.out.println(stringBuilder);
    }
    static void merge(Person[] array, int openingIndex, int closingIndex) {
        Person[] helpingArray = new Person[closingIndex-openingIndex+1];
        int centerIndex = (openingIndex+closingIndex)/2;
        int indexForLeftPart = openingIndex;
        int indexForRightPart = centerIndex+1;
        int indexForHelpingArray = 0;
        while(indexForLeftPart<=centerIndex && indexForRightPart<=closingIndex) {
            if(array[indexForLeftPart].compareTo(array[indexForRightPart])<=0) helpingArray[indexForHelpingArray++] = array[indexForLeftPart++];
            else helpingArray[indexForHelpingArray++] = array[indexForRightPart++];
        }
        while(indexForLeftPart<=centerIndex) helpingArray[indexForHelpingArray++] = array[indexForLeftPart++];
        while(indexForRightPart<=closingIndex) helpingArray[indexForHelpingArray++] = array[indexForRightPart++];
        for(int traverse=0; traverse<helpingArray.length; traverse++) array[openingIndex+traverse]=helpingArray[traverse];
    }
    static void mergeSort(Person[] array, int openingIndex, int closingIndex) {
        if(openingIndex>=closingIndex) return;
        int centerIndex = (openingIndex+closingIndex)/2;

        mergeSort(array,openingIndex,centerIndex);
        mergeSort(array,centerIndex+1,closingIndex);
        merge(array,openingIndex,closingIndex);
    }
    static void p10814() throws IOException {
        final int numberOfInputs = Integer.parseInt(reader.readLine());
        Person[] members = new Person[numberOfInputs];
        StringTokenizer tokenizer;
        for(int index=0; index<numberOfInputs; index++) {
            tokenizer = new StringTokenizer(reader.readLine());
            members[index] = new Person(Integer.parseInt(tokenizer.nextToken()), tokenizer.nextToken());
        }
        mergeSort(members, 0, numberOfInputs-1);

        StringBuilder stringBuilder = new StringBuilder();
        for(Person member: members) stringBuilder.append(member).append("\n");
        System.out.println(stringBuilder);
    }
    static void p18870() throws IOException {
        final int numberOfInputs = Integer.parseInt(reader.readLine());
        int[] points = new int[numberOfInputs];
        StringTokenizer stringTokenizer = new StringTokenizer(reader.readLine());
        for(int index=0; index<numberOfInputs; index++) points[index]=Integer.parseInt(stringTokenizer.nextToken());

        // remove duplicates and sort in ascending order
        List<Integer> organisedPoints = Arrays.stream(points).boxed().distinct().sorted().collect(Collectors.toList());
        HashMap<Integer, Integer> countingMap = new HashMap<>();
        for(int index=0; index<organisedPoints.size(); index++) {
            countingMap.put(organisedPoints.get(index), index);
        }

        StringBuilder stringBuilder = new StringBuilder();
        for(int item: points) stringBuilder.append(countingMap.get(item) + " ");
        System.out.println(stringBuilder);
    }
}
