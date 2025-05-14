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
        static void p10773() throws IOException {
            final int numberOfInputs = Integer.parseInt(in.readLine());
            Stack<Integer> stackForSum = new Stack<>();
            for(int n=0; n<numberOfInputs; n++) {
                int input = Integer.parseInt(in.readLine());
                if(input==0) stackForSum.pop();
                else stackForSum.push(input);
            }
            int sum = 0;
            for(int index=0; index<stackForSum.size(); index++) sum+=stackForSum.get(index);
            out.write(sum + "\n");
            out.flush();
        }
        static boolean isVPS(String target) {
            final int length = target.length();
            int count = 0;
            for(int index=0; index<length; index++) {
                if(target.charAt(index)=='(') count++;
                else if(target.charAt(index)==')') count--;
                if(count<0) return false;
            }
            if(count==0) return true;
            return false;
        }
        static void p9012() throws IOException {
            final int numberOfInputs = Integer.parseInt(in.readLine());
            for(int n=0; n<numberOfInputs; n++) {
                if(isVPS(in.readLine())) stringBuilder.append("YES\n");
                else stringBuilder.append("NO\n");
            }
            out.write(stringBuilder.toString());
            out.flush();
        }
        static boolean isBalanced(String target) {
            Stack<Character> estimation = new Stack<>();
            for(int index=0; index<target.length(); index++) {
                if(target.charAt(index)=='(') estimation.push('(');
                else if(target.charAt(index)=='[') estimation.push('[');
                else if(target.charAt(index)==')') {
                    if(!estimation.isEmpty() && estimation.peek()=='(') estimation.pop();
                    else return false;
                }
                else if(target.charAt(index)==']') {
                    if(!estimation.isEmpty() && estimation.peek()=='[') estimation.pop();
                    else return false;
                }
            }
            if(estimation.isEmpty()) return true;
            else return false;
        }
        static void p4949() throws IOException {
            while(true) {
                String target = in.readLine();
                if(target.equals(".")) break;

                if(isBalanced(target)) stringBuilder.append("yes\n");
                else stringBuilder.append("no\n");
            }
            out.write(stringBuilder.toString());
            out.flush();
        }
        static void p12789() throws IOException {
            final int numberOfPeople = Integer.parseInt(in.readLine());
            Stack<Integer> waitingGroup = new Stack<>();
            int numberToPass = 1;

            StringTokenizer tokenizer = new StringTokenizer(in.readLine());
            for(int n=0; n<numberOfPeople; n++) {
                int number = Integer.parseInt(tokenizer.nextToken());
                if(number==numberToPass) numberToPass++;
                else waitingGroup.push(number);
                while(!waitingGroup.isEmpty() && waitingGroup.peek()==numberToPass) {
                    waitingGroup.pop();
                    numberToPass++;
                }
            }
            while(!waitingGroup.isEmpty()) {
                if(waitingGroup.pop()!=numberToPass) break;
                numberToPass++;
            }
            if(numberOfPeople+1==numberToPass) out.write("Nice\n");
            else out.write("Sad\n");
            out.flush();
        }
    }

    public static void main(String[] args) {
        try {
            Chapter16.p12789();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

}