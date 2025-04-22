import java.io.*;
import java.util.Stack;
import java.util.StringTokenizer;

public class Chapter9 {
    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

    static void p5086() throws IOException {
        StringTokenizer tokenizer;
        while(true) {
            tokenizer = new StringTokenizer(reader.readLine());
            int leftValue = Integer.parseInt(tokenizer.nextToken());
            int rightValue = Integer.parseInt(tokenizer.nextToken());

            if(leftValue==0 && rightValue==0) break;
            else if(leftValue > rightValue) {
                if(leftValue%rightValue==0) writer.write("multiple");
                else writer.write("neither");
            }
            else if(leftValue < rightValue) {
                if(rightValue%leftValue==0) writer.write("factor");
                else writer.write("neither");
            }
            writer.newLine();
            writer.flush();
        }
    }
    static void p2501() throws IOException {
        StringTokenizer tokenizer = new StringTokenizer(reader.readLine(), " ");
        int n = Integer.parseInt(tokenizer.nextToken()), k = Integer.parseInt(tokenizer.nextToken()), count = 0;
        for(int i=1; i<=n; i++) {
            if(n%i==0) {
                if(++count==k) {
                    writer.write("" + i);
                    writer.flush();
                    return;
                }
            }
        }
        writer.write("0");
        writer.flush();
    }
    static void isPerfectNumber(int target) throws IOException {
        Stack<Integer> factors = new Stack<>();
        int sumOfFactors = 0;
        for(int divider=1; divider*2<=target; divider++) {
            if(target%divider==0) {
                factors.push(divider);
                sumOfFactors += divider;
            }
        }
        if(sumOfFactors==target) {
            writer.write(target + " = " + factors.get(0));
            for(int index=1; index<factors.size(); index++) {
                writer.write(" + " + factors.get(index));
            }
        }
        else writer.write(target + " is NOT perfect.");
        writer.newLine();
        writer.flush();

    }
    static void p9506() throws IOException {
        while(true) {
            int target = Integer.parseInt(reader.readLine());
            if(target==-1) break;
            else if(2<target && target<100_000) isPerfectNumber(target);
        }
    }
    static void updatePrimeNumbers(Stack<Integer> primeNumbers, int biggest) {
        if(biggest==2) {
            primeNumbers.push(2);
        }
        else if(biggest>2) {
            primeNumbers.push(2);
            primeNumbers.push(3);
            int item = 5;
            boolean isItemPrime;
            /*
            prime
            1. no even prime numbers bigger than 2
            2. checks until square root
            3. divide only with prime numbers
             */
            while(true) {
                if(item>biggest) break;
                isItemPrime = true;
                for(int index=1; index<primeNumbers.size(); index++) {
                    if(primeNumbers.get(index)*primeNumbers.get(index)>item) break;
                    if(item%primeNumbers.get(index)==0) {
                        isItemPrime = false;
                        break;
                    }
                }
                if(isItemPrime) primeNumbers.push(item);
                item+=2;
            }
        }
    }
    static void p1978() throws IOException {
        final int nItems = Integer.parseInt(reader.readLine());
        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        int biggestItem = -1;
        int[] items = new int[nItems];
        for(int index=0; index<nItems; index++) {
            items[index] = Integer.parseInt(tokenizer.nextToken());
            if(biggestItem<items[index]) biggestItem = items[index];
        }

        Stack<Integer> primeNumbers = new Stack<>();
        updatePrimeNumbers(primeNumbers, biggestItem);
        int primeNumberCounts = 0;
        for(int index=0; index<nItems; index++) {
            if(primeNumbers.contains(items[index])) primeNumberCounts++;
        }
        writer.write("" + primeNumberCounts);
        writer.flush();
    }
    static void p2581() throws IOException {
        int startingRange = Integer.parseInt(reader.readLine());
        int endingRange = Integer.parseInt(reader.readLine());
        Stack<Integer> primeNumbers = new Stack<>();
        updatePrimeNumbers(primeNumbers, endingRange);
        int sum = 0;
        int minimum = -1;
        if(startingRange==2) {
            sum+=2;
            minimum=2;
            startingRange=3;
        }
        else if(startingRange==1 && 1<endingRange) {
            sum+=2;
            minimum=2;
            startingRange=3;
        }
        else if(startingRange%2==0) startingRange++;
        for(int item=startingRange; item<=endingRange; item+=2) {
            if(primeNumbers.contains(item)) {
                if(minimum==-1) minimum = item;
                sum += item;
            }
        }
        if(minimum==-1) {
            writer.write("-1");
            writer.flush();
            return;
        }
        writer.write("" + sum);
        writer.newLine();
        writer.write("" + minimum);
        writer.flush();
    }
    static void addNextPrimeNumber(Stack<Integer> primeNumbers) {
        if(primeNumbers.peek()==2) {
            primeNumbers.push(3);
            return;
        }
        int target = primeNumbers.peek() + 2;
        while(true) {
            boolean flag = true;
            for(int index=1; index<primeNumbers.size(); index+=2) {
                if(target%primeNumbers.get(index)==0) {
                    flag = false;
                    break;
                }
                else if(primeNumbers.get(index)*primeNumbers.get(index)>target) {
                    break;
                }
            }
            if(flag) {
                primeNumbers.push(target);
                break;
            }
            else target+=2;
        }
    }
    static void p11653() throws IOException {
        int target = Integer.parseInt(reader.readLine());
        while(target%2==0) {
            target/=2;
            writer.write(2 + "\n");
        }
        int currentDivider = 3;
        while(true) {
            if(target==1) break;
            if(target%currentDivider==0) {
                writer.write(currentDivider + "\n");
                target/=currentDivider;
            }
            else currentDivider+=2;
        }
        writer.flush();
    }
}