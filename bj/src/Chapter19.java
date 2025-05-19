import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Chapter19 {
    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder stringBuilder = new StringBuilder();
    static StringTokenizer tokenizer;

    static long factorial(long result, int n) {
        if(n<2) return result;
        return factorial(result*n, n-1);
    }
    static void p27433() throws IOException {
        final int input = Integer.parseInt(in.readLine());
        stringBuilder.append(factorial(1,input));
        out.write(stringBuilder.toString());
        out.flush();
    }
    static int fibonacci(int n) {
        if(n==0) return 0;
        else if(n==1) return 1;
        return fibonacci(n-2)+fibonacci(n-1);
    }
    static void p10870() throws IOException {
        final int input = Integer.parseInt(in.readLine());
        stringBuilder.append(fibonacci(input));
        out.write(stringBuilder.toString());
        out.flush();
    }
    static void stringBuildPalindrome(String target, int index) throws IOException {
        if(index>=target.length()/2) {
            stringBuilder.append("1 ").append(index+1).append("\n");
            return;
        }
        if(target.charAt(index)==target.charAt(target.length()-1-index)) stringBuildPalindrome(target, index+1);
        else stringBuilder.append("0 ").append(index+1).append("\n");
    }
    static void p25501() throws IOException {
        final int numberOfInputs = Integer.parseInt(in.readLine());
        for(int n=0; n<numberOfInputs; n++) stringBuildPalindrome(in.readLine(), 0);
        out.write(stringBuilder.toString());
        out.flush();
    }
    static class TeachingAssistant {
        int targetElement, targetSequence, count;
        ArrayList<Integer> elements;
        TeachingAssistant(int targetSequence, ArrayList<Integer> elements) {
            this.targetElement = -1;
            this.targetSequence = targetSequence;
            this.count = 0;
            this.elements = elements;
        }

        void merge(int openingIndex, int closingIndex) {
            int centerIndex = (openingIndex+closingIndex)/2;
            int indexForLeft = openingIndex;
            int indexForRight = centerIndex+1;

            ArrayList<Integer> sorted = new ArrayList<>();
            while(indexForLeft<=centerIndex && indexForRight<=closingIndex) {
                if(elements.get(indexForLeft)<elements.get(indexForRight)) {
                    sorted.add(elements.get(indexForLeft));
                    count++;
                    if(count==targetSequence) targetElement=elements.get(indexForLeft);
                    indexForLeft++;
                }
                else {
                    sorted.add(elements.get(indexForRight));
                    count++;
                    if(count==targetSequence) targetElement=elements.get(indexForRight);
                    indexForRight++;
                }
            }
            while(indexForLeft<=centerIndex) {
                sorted.add(elements.get(indexForLeft));
                count++;
                if(count==targetSequence) targetElement=elements.get(indexForLeft);
                indexForLeft++;
            }
            while(indexForRight<=closingIndex) {
                sorted.add(elements.get(indexForRight));
                count++;
                if(count==targetSequence) targetElement=elements.get(indexForRight);
                indexForRight++;
            }

            int updatingIndex = openingIndex;
            while(updatingIndex<=closingIndex) {
                elements.set(updatingIndex, sorted.get(updatingIndex-openingIndex));
                updatingIndex++;
            }
        }
        void sort(int openingIndex, int closingIndex) {
            if(openingIndex>=closingIndex) return;
            int centerIndex = (openingIndex+closingIndex)/2;
            sort(openingIndex, centerIndex);
            sort(centerIndex+1, closingIndex);
            merge(openingIndex, closingIndex);
        }

    }
    static void p24060() throws IOException {
        tokenizer = new StringTokenizer(in.readLine());
        final int sizeOfArray = Integer.parseInt(tokenizer.nextToken());
        final int targetSequence = Integer.parseInt(tokenizer.nextToken());

        tokenizer = new StringTokenizer(in.readLine());
        ArrayList<Integer> elements = new ArrayList<>();
        for(int index=0; index<sizeOfArray; index++) elements.add(Integer.parseInt(tokenizer.nextToken()));

        Main.Chapter19.TeachingAssistant teachingAssistant = new Main.Chapter19.TeachingAssistant(targetSequence, elements);
        teachingAssistant.sort(0,elements.size()-1);
        stringBuilder.append(teachingAssistant.targetElement);

        out.write(stringBuilder.toString());
        out.flush();
    }
    static class Cantor {
        int exponent, numberOfElements;
        ArrayList<Character> elements;
        Cantor(int exponent) {
            this.exponent = exponent;
            this.numberOfElements = (int)Math.pow(3.0, exponent);
            elements = new ArrayList<>();
            for(int index=0; index<numberOfElements; index++) elements.add('-');
        }
        void process(int openingIndex, int closingIndex) {
            if(openingIndex>=closingIndex) return;
            int centerIndex = (openingIndex+closingIndex)/2;
            int length = closingIndex-openingIndex+1;
            int aThirdOfLength = length/3;
            if(elements.get(centerIndex)=='-') {
                for(int index=openingIndex+aThirdOfLength; index<openingIndex+2*aThirdOfLength; index++) {
                    elements.set(index, ' ');
                }
                process(openingIndex, openingIndex+aThirdOfLength-1);
                process(openingIndex+2*aThirdOfLength, closingIndex);
            }
        }
        String elementAsString() {
            StringBuilder finalResult = new StringBuilder();
            for(int index=0; index<elements.size(); index++) finalResult.append(elements.get(index));
            return finalResult.toString();
        }
    }
    static void p4779() throws IOException {
        int exponent;
        Main.Chapter19.Cantor cantor;
        String result;
        while(true) {
            String read = in.readLine();
            if(read==null) break;
            exponent = Integer.parseInt(read);
            cantor = new Main.Chapter19.Cantor(exponent);
            cantor.process(0, cantor.numberOfElements-1);
            result = cantor.elementAsString();
            stringBuilder.append(result).append("\n");
        }
        out.write(stringBuilder.toString());
        out.flush();
    }
    static char[] star(int targetLength) {
        if(targetLength==1) {
            char[] drawing = new char[targetLength];
            drawing[0] = '*';
            return drawing;
        }
        char[] drawing = new char[targetLength*targetLength];
        for(int index=0; index<targetLength*targetLength; index++) drawing[index]=' ';
        int aThirdOfLength = targetLength/3;
        char[] part = star(aThirdOfLength);
        for(int offsetOnRow=0; offsetOnRow<targetLength; offsetOnRow+=aThirdOfLength) {
            for(int offsetOnColumn=0; offsetOnColumn<targetLength; offsetOnColumn+=aThirdOfLength) {
                if(offsetOnRow==aThirdOfLength && offsetOnColumn==aThirdOfLength) continue;
                for(int indexForPart=0; indexForPart<part.length; indexForPart++) {
                    int index = (offsetOnRow+indexForPart/aThirdOfLength)*targetLength + offsetOnColumn + indexForPart%aThirdOfLength;
                    drawing[index] = part[indexForPart];
                }
            }
        }
        return drawing;
    }
    static void p2447() throws IOException {
        int length = Integer.parseInt(in.readLine());
        char[] drawing = star(length);
        for(int index=0; index<length*length; index++) {
            stringBuilder.append(drawing[index]);
            if(index%length==length-1) stringBuilder.append("\n");
        }
        out.write(stringBuilder.toString());
        out.flush();
    }
    static int hanoi(int numberOfPlates, int from, int to, int station) {
        if(numberOfPlates==1) {
            stringBuilder.append(from+1).append(" ").append(to+1).append("\n");
            return 1;
        }
        int count = 0;
        count += hanoi(numberOfPlates-1, from, station, to);
        count += hanoi(1, from, to, station);
        count += hanoi(numberOfPlates-1, station, to, from);
        return count;
    }
    static void p11729() throws IOException {
        final int numberOfPlates = Integer.parseInt(in.readLine());
        System.out.println(hanoi(numberOfPlates,0,2, 1));
        out.write(stringBuilder.toString());
        out.flush();
    }
}
