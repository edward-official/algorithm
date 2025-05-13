import java.awt.*;
import java.io.*;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;


public class Main {
    static class Chapter16 {
        static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
        static StringBuilder stringBuilder = new StringBuilder();

        static void p28278() throws IOException {
            final int numberOfInstructions = Integer.parseInt(in.readLine());
            StringTokenizer tokenizer;
            Stack<Integer> stackOfIntegers = new Stack<>();
            for(int n=0; n<numberOfInstructions; n++) {
                tokenizer = new StringTokenizer(in.readLine());
                int instruction = Integer.parseInt(tokenizer.nextToken());
                if(instruction==1) stackOfIntegers.push(Integer.parseInt(tokenizer.nextToken()));
                else if(instruction==2) {
                    if(stackOfIntegers.isEmpty()) stringBuilder.append("-1\n");
                    else stringBuilder.append(stackOfIntegers.pop() + "\n");
                }
                else if(instruction==3) {
                    stringBuilder.append(stackOfIntegers.size() + "\n");
                }
                else if(instruction==4) {
                    stringBuilder.append(stackOfIntegers.isEmpty()?"1\n":"0\n");
                }
                else if(instruction==5) {
                    if(stackOfIntegers.isEmpty()) stringBuilder.append("-1\n");
                    else stringBuilder.append(stackOfIntegers.peek() + "\n");
                }
            }
            out.write(stringBuilder.toString());
            out.flush();
        }
    }

    public static void main(String[] args) {
        try {
            Chapter16.p28278();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

}