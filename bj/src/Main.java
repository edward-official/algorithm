import java.awt.*;
import java.io.*;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;


public class Main {
    static class Chapter15 {
        static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));

        static long getGCD(long big, long small) {
            if(big<small) return -1;
            while(small!=0) {
                long temp = big;
                big = small;
                small = temp%small;
            }
            return big;
        }
        static long getLCM(long big, long small) {
            if(big<small) return -1;
            return big*small/getGCD(big,small);
        }
        static void p1934() throws IOException {
            final int numberOfLoops = Integer.parseInt(in.readLine());
            StringTokenizer tokenizer;
            StringBuilder builder = new StringBuilder();
            for(int loop=0; loop<numberOfLoops; loop++) {
                tokenizer = new StringTokenizer(in.readLine());
                int number1 = Integer.parseInt(tokenizer.nextToken());
                int number2 = Integer.parseInt(tokenizer.nextToken());
                if(number1>number2) builder.append(getLCM(number1,number2) + "\n");
                else builder.append(getLCM(number2,number1) + "\n");
            }
            out.write(builder.toString());
            out.flush();
        }
        static void p13241() throws IOException {
            StringTokenizer tokenizer = new StringTokenizer(in.readLine());
            long number1 = Integer.parseInt(tokenizer.nextToken());
            long number2 = Integer.parseInt(tokenizer.nextToken());

            if(number1>number2) out.write(getLCM(number1,number2) + "\n");
            else out.write(getLCM(number2,number1) + "\n");
            out.flush();
        }
    }

    public static void main(String[] args) {
        try {
            Chapter15.p13241();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

}