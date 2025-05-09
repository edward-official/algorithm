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
        static void p1735() throws IOException {
            StringTokenizer tokenizer;

            tokenizer = new StringTokenizer(in.readLine());
            int numerator1 = Integer.parseInt(tokenizer.nextToken());
            int denominator1 = Integer.parseInt(tokenizer.nextToken());

            tokenizer = new StringTokenizer(in.readLine());
            int numerator2 = Integer.parseInt(tokenizer.nextToken());
            int denominator2 = Integer.parseInt(tokenizer.nextToken());

            int numeratorOfSum = numerator1*denominator2 + numerator2*denominator1;
            int denominatorOfSum = denominator1*denominator2;
            while(numeratorOfSum%2==0 && denominatorOfSum%2==0) {
                numeratorOfSum/=2;
                denominatorOfSum/=2;
            }

            int divider = 3;
            while(divider<=numeratorOfSum && divider<=denominatorOfSum) {
                if(numeratorOfSum%divider==0 && denominatorOfSum%divider==0) {
                    numeratorOfSum/=divider;
                    denominatorOfSum/=divider;
                }
                else divider+=2;
            }

            out.write(numeratorOfSum + " " + denominatorOfSum + "\n");
            out.flush();
        }
        static void p2485() throws IOException {
            final int numberOfTrees = Integer.parseInt(in.readLine());
            ArrayList<Integer> locationOfTrees = new ArrayList<>();
            for(int index=0; index<numberOfTrees; index++) {
                locationOfTrees.add(Integer.parseInt(in.readLine()));
            }

            int currentGCD = locationOfTrees.get(1)-locationOfTrees.get(0);
            for(int index=2; index<numberOfTrees; index++) {
                int interval = locationOfTrees.get(index)-locationOfTrees.get(index-1);
                if(currentGCD<interval) currentGCD = (int)getGCD(interval, currentGCD);
                else currentGCD = (int)getGCD(currentGCD, interval);
            }

            int count = 0;
            for(int index=1; index<numberOfTrees; index++) {
                int interval = locationOfTrees.get(index)-locationOfTrees.get(index-1);
                count += (interval/currentGCD)-1;
            }

            out.write(count + "\n");
            out.flush();
        }
        static boolean isPrime(long item) {
            if(item<2) return false;
            else if(item==2 || item==3) return true;
            else if(item%2==0 || item%3==0) return false;
            for(long divider=5; divider*divider<=item; divider+=6) {
                if(item%divider==0 || item%(divider+2)==0) return false;
            }
            return true;
        }
        static long getMinimumPrimeNumber(long startingRange) {
            long returningValue;
            if(startingRange<0) return -1;
            else if(startingRange==0 || startingRange==1 || startingRange==2) returningValue=2;
            else if(startingRange%2==0) {
                returningValue = startingRange+1;
                while(!isPrime(returningValue)) returningValue+=2;
            }
            else {
                returningValue = startingRange;
                while(!isPrime(returningValue)) returningValue+=2;
            }
            return returningValue;
        }
        static void p4134() throws IOException {
            final int numberOfInputs = Integer.parseInt(in.readLine());
            StringBuilder stringBuilder = new StringBuilder();
            for(int n=0; n<numberOfInputs; n++) {
                long itemToAppend = getMinimumPrimeNumber(Long.parseLong(in.readLine()));
                stringBuilder.append(itemToAppend + "\n");
            }
            out.write(stringBuilder.toString());
            out.flush();
        }
    }

    public static void main(String[] args) {
        try {
            Chapter15.p4134();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

}