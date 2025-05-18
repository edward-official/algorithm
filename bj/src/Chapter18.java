import java.io.*;
import java.util.*;

public class Chapter18 {
    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder stringBuilder = new StringBuilder();
    static StringTokenizer tokenizer;

    static void p1037() throws IOException {
        final int numberOfRealFactors = Integer.parseInt(in.readLine());
        tokenizer = new StringTokenizer(in.readLine());
        int factor = Integer.parseInt(tokenizer.nextToken());
        int minimum=factor, maximum=factor;
        for(int n=1; n<numberOfRealFactors; n++) {
            factor = Integer.parseInt(tokenizer.nextToken());
            if(factor<minimum) minimum=factor;
            if(maximum<factor) maximum=factor;
        }
        stringBuilder.append(maximum*minimum).append("\n");
        out.write(stringBuilder.toString());
        out.flush();
    }
    static void p25192() throws IOException {
        final int numberOfLines = Integer.parseInt(in.readLine());
        HashSet<String> names = new HashSet<>();
        int count = 0;
        for(int n=0; n<numberOfLines; n++) {
            String line = in.readLine();
            if(line.equals("ENTER")) {
                count += names.size();
                names.clear();
            }
            else names.add(line);
        }
        count += names.size();

        stringBuilder.append(count);
        out.write(stringBuilder.toString());
        out.flush();
    }
    static void p26069() throws IOException {
        final int numberOfLines = Integer.parseInt(in.readLine());
        HashSet<String> dancers = new HashSet<>();
        dancers.add("ChongChong");

        for(int n=0; n<numberOfLines; n++) {
            tokenizer = new StringTokenizer(in.readLine());
            String person1 = tokenizer.nextToken();
            String person2 = tokenizer.nextToken();
            if(!dancers.contains(person1) && !dancers.contains(person2)) continue;
            dancers.add(person1);
            dancers.add(person2);
        }

        stringBuilder.append(dancers.size()).append("\n");
        out.write(stringBuilder.toString());
        out.flush();
    }
    static void p2108() throws IOException {
        final int numberOfElements = Integer.parseInt(in.readLine());
        LinkedList<Integer> elements = new LinkedList<>();
        TreeMap<Integer, Integer> countOfElements = new TreeMap<>();
        int sum = 0, highestFrequency = 0;

        // average(sorted list), center(sorted list), frequent, range(sorted list)
        for(int n=0; n<numberOfElements; n++) {
            int element = Integer.parseInt(in.readLine());
            elements.add(element);
            if(!countOfElements.containsKey(element)) countOfElements.put(element, 1);
            else countOfElements.put(element, countOfElements.get(element)+1);
            if(highestFrequency<countOfElements.get(element)) highestFrequency=countOfElements.get(element);
            sum+=element;
        }
        elements.sort(Comparator.naturalOrder());
        boolean isFirst = true;
        int mode = elements.get(0);
        for(Map.Entry<Integer,Integer> entry: countOfElements.entrySet()) {
            if(entry.getValue()==highestFrequency) {
                if(isFirst) {
                    mode = entry.getKey();
                    isFirst = false;
                }
                else {
                    mode = entry.getKey();
                    break;
                }
            }
        }

        int mean = (int)Math.round((double)sum/numberOfElements);
        stringBuilder.append(mean).append("\n");
        int median = elements.get(numberOfElements/2);
        stringBuilder.append(median).append("\n");
        stringBuilder.append(mode).append("\n");
        int range = elements.get(numberOfElements-1)-elements.get(0);
        stringBuilder.append(range).append("\n");

        out.write(stringBuilder.toString());
        out.flush();
    }
    static class Vocabulary {
        HashMap<String, Integer> hashMap;
        ArrayList<String> elements;
        Vocabulary(HashMap<String,Integer> hashMap, ArrayList<String> elements) {
            this.hashMap = hashMap;
            this.elements = elements;
        }

        int compareByVocabularyOrder(String element1, String element2) {
            if(hashMap.get(element1)!=hashMap.get(element2)) return hashMap.get(element2)-hashMap.get(element1);
            if(element1.length()!=element2.length()) return element2.length()-element1.length();
            int index=0;
            while(index<element1.length()) {
                if(element1.charAt(index) != element2.charAt(index)) return element1.charAt(index)-element2.charAt(index);
                index++;
            }
            return 0;
        }
        void merge(int openingIndex, int closingIndex) {
            ArrayList<String> sorted = new ArrayList<>();
            int centerIndex = (openingIndex+closingIndex)/2;
            int indexForLeft = openingIndex;
            int indexForRight = centerIndex+1;

            while(indexForLeft<=centerIndex && indexForRight<=closingIndex) {
                if(compareByVocabularyOrder(elements.get(indexForLeft), elements.get(indexForRight))>0) {
                    sorted.add(elements.get(indexForRight));
                    indexForRight++;
                }
                else {
                    sorted.add(elements.get(indexForLeft));
                    indexForLeft++;
                }
            }
            while(indexForLeft<=centerIndex) {
                sorted.add(elements.get(indexForLeft));
                indexForLeft++;
            }
            while(indexForRight<=closingIndex) {
                sorted.add(elements.get(indexForRight));
                indexForRight++;
            }

            int copyingIndex = openingIndex;
            while(copyingIndex<=closingIndex) {
                elements.set(copyingIndex, sorted.get(copyingIndex-openingIndex));
                copyingIndex++;
            }
        }
        void sort(int openingIndex, int closingIndex) {
            if(openingIndex>=closingIndex) return;
            int centerIndex = (openingIndex+closingIndex)/2;
            sort(openingIndex, centerIndex);
            sort(centerIndex+1, closingIndex);
            merge(openingIndex, closingIndex);
        }
        void terminal() throws IOException {
            for(int index=0; index<elements.size(); index++) stringBuilder.append(elements.get(index)).append("\n");
            out.write(stringBuilder.toString());
            out.flush();
        }
    }
    static void p20920() throws IOException {
        tokenizer = new StringTokenizer(in.readLine());
        final int numberOfItems = Integer.parseInt(tokenizer.nextToken());
        final int minimumLength = Integer.parseInt(tokenizer.nextToken());

        HashMap<String, Integer> hashMap = new HashMap<>();
        for(int n=0; n<numberOfItems; n++) {
            String key = in.readLine();
            if(key.length()<minimumLength) continue;
            if(hashMap.containsKey(key)) hashMap.put(key, hashMap.get(key)+1);
            else hashMap.put(key, 1);
        }
        ArrayList<String> keys = new ArrayList<>(hashMap.keySet());
        Vocabulary vocabulary = new Vocabulary(hashMap, keys);
        vocabulary.sort(0, keys.size()-1);
        vocabulary.terminal();
    }
}
