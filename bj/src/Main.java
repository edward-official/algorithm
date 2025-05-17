import java.awt.*;
import java.io.*;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;


public class Main {
    static class Chapter18 {
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
    }

    public static void main(String[] args) {
        try {
            Chapter18.p2108();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
}