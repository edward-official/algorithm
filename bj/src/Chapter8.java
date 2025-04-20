import java.io.*;
import java.util.Stack;
import java.util.StringTokenizer;

public class Chapter8 {
    static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));

    static void p2745() {
        try {
            StringTokenizer tokenizer = new StringTokenizer(bufferedReader.readLine(), " ");
            String number = tokenizer.nextToken();
            int radix = Integer.parseInt(tokenizer.nextToken()), result = 0;
            for(int i=0; i<number.length(); i++) {
                if('A'<=number.charAt(i) && number.charAt(i)<='Z') result += (int)Math.pow(radix, number.length()-1-i) * (number.charAt(i)-'A'+10);
                else result += (int)Math.pow(radix, number.length()-1-i) * Integer.parseInt(Character.toString(number.charAt(i)));
            }
            bufferedWriter.write("" + result);
            bufferedWriter.flush();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
    static void p11005() {
        try {
            StringTokenizer tokenizer = new StringTokenizer(bufferedReader.readLine());
            int decimalNumber = Integer.parseInt(tokenizer.nextToken());
            int radix = Integer.parseInt(tokenizer.nextToken());
            Stack<Character> digits = new Stack<>();
            while(decimalNumber!=0) {
                digits.push(Character.toUpperCase(Character.forDigit(decimalNumber%radix, radix)));
                decimalNumber/=radix;
            }
            for(int i=digits.size()-1; i>=0; i--) bufferedWriter.write(digits.elementAt(i));
            bufferedWriter.flush();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
    static String getChange(int change) {
        String result = "";
        result += change/25 + " ";
        change %= 25;
        result += change/10 + " ";
        change %= 10;
        result += change/5 + " ";
        change %= 5;
        result += change;
        return result;
    }
    static void p2720() {
        try {
            int loop = Integer.parseInt(bufferedReader.readLine());
            for(int i=0; i<loop; i++) {
                bufferedWriter.write(getChange(Integer.parseInt(bufferedReader.readLine())));
                bufferedWriter.newLine();
            }
            bufferedWriter.flush();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
    static void p2903() {
        /*
        1: (2+1) square
        2: (4+1) square
        3: (8+1) square
         */
        try {
            int n = Integer.parseInt(bufferedReader.readLine());
            bufferedWriter.write("" + (int)Math.pow((int)Math.pow(2,n)+1,2));
            bufferedWriter.flush();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
    static void p2292() {
        try {
            int destination = Integer.parseInt(bufferedReader.readLine()), count = 0;
            while(true) {
                if(destination <= 1+6*count*(count+1)/2) break;
                count++;
            }
            bufferedWriter.write("" + (count+1));
            bufferedWriter.flush();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
    static void p1193() {
        try {
            int n = Integer.parseInt(bufferedReader.readLine());
            int nDiagonal = 0, maximum, offset;
            while(true) {
                nDiagonal++;
                maximum = nDiagonal*(nDiagonal+1)/2;
                if(maximum-nDiagonal<n && n<=maximum) {
                    offset = n-maximum+nDiagonal;
                    break;
                }
            }
            if(nDiagonal%2==1) bufferedWriter.write((nDiagonal+1-offset) + "/" + offset);
            else bufferedWriter.write(offset + "/" + (nDiagonal+1-offset));
            bufferedWriter.flush();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
    static void p2869() {
        try {
            StringTokenizer tokenizer = new StringTokenizer(bufferedReader.readLine());
            int forward = Integer.parseInt(tokenizer.nextToken());
            int backward = Integer.parseInt(tokenizer.nextToken());
            int height = Integer.parseInt(tokenizer.nextToken());

            int day = (int)Math.ceil((double)(height-backward)/(forward-backward));
            bufferedWriter.write("" + day);
            bufferedWriter.flush();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
}