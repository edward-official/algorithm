import java.io.*;
import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.Stack;
import java.util.StringTokenizer;

public class Chapter16 {
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
    static void p18258() throws IOException {
        final int numberOfLines = Integer.parseInt(in.readLine());
        LinkedList<Integer> queue = new LinkedList<>();
        StringTokenizer tokenizer;
        for(int n=0; n<numberOfLines; n++) {
            tokenizer = new StringTokenizer(in.readLine());
            String operation = tokenizer.nextToken();
            if(operation.equals("push")) {
                queue.add(Integer.parseInt(tokenizer.nextToken()));
            }
            else if(operation.equals("pop")) {
                if(queue.isEmpty()) stringBuilder.append("-1\n");
                else stringBuilder.append(queue.poll() + "\n");
            }
            else if(operation.equals("size")) {
                stringBuilder.append(queue.size() + "\n");
            }
            else if(operation.equals("empty")) {
                if(queue.isEmpty()) stringBuilder.append("1\n");
                else stringBuilder.append("0\n");
            }
            else if(operation.equals("front")) {
                if(queue.isEmpty()) stringBuilder.append("-1\n");
                else stringBuilder.append(queue.peekFirst() + "\n");
            }
            else if(operation.equals("back")) {
                if(queue.isEmpty()) stringBuilder.append("-1\n");
                else stringBuilder.append(queue.peekLast() + "\n");
            }
        }
        out.write(stringBuilder.toString());
        out.flush();
    }
    static void p2164() throws IOException {
        final int numberOfCards = Integer.parseInt(in.readLine());
        ArrayDeque<Integer> queue = new ArrayDeque<>();
        for(int element=1; element<=numberOfCards; element++) queue.add(element);
        while(queue.size()>1) {
            queue.poll();
            if(queue.size()==1) break;
            queue.add(queue.poll());
        }
        out.write(queue.element() + "\n");
        out.flush();
    }
    static void p11866() throws IOException {
        StringTokenizer tokenizer = new StringTokenizer(in.readLine());
        final int numberOfPeople = Integer.parseInt(tokenizer.nextToken());
        final int increment = Integer.parseInt(tokenizer.nextToken());
        LinkedList<Integer> queue = new LinkedList<>();
        for(int n=1; n<=numberOfPeople; n++) queue.add(n);

        int pollingIndex = increment-1;
        stringBuilder.append("<" + queue.remove(pollingIndex));
        while(!queue.isEmpty()) {
            pollingIndex = (pollingIndex+increment-1) % queue.size();
            stringBuilder.append(", " + queue.remove(pollingIndex));
        }
        stringBuilder.append(">\n");

        out.write(stringBuilder.toString());
        out.flush();
    }
    static void p28279() throws IOException {
        final int numberOfInstructions = Integer.parseInt(in.readLine());
        StringTokenizer tokenizer;
        ArrayDeque<Integer> arrayDeque = new ArrayDeque<>();
        for(int n=0; n<numberOfInstructions; n++) {
            tokenizer = new StringTokenizer(in.readLine());
            int instruction = Integer.parseInt(tokenizer.nextToken());
            if(instruction==1) arrayDeque.addFirst(Integer.parseInt(tokenizer.nextToken()));
            else if(instruction==2) arrayDeque.addLast(Integer.parseInt(tokenizer.nextToken()));
            else if(instruction==3) {
                if(arrayDeque.isEmpty()) stringBuilder.append("-1\n");
                else stringBuilder.append(arrayDeque.pollFirst() + "\n");
            }
            else if(instruction==4) {
                if(arrayDeque.isEmpty()) stringBuilder.append("-1\n");
                else stringBuilder.append(arrayDeque.pollLast() + "\n");
            }
            else if(instruction==5) stringBuilder.append(arrayDeque.size() + "\n");
            else if(instruction==6) {
                if(arrayDeque.isEmpty()) stringBuilder.append("1\n");
                else stringBuilder.append("0\n");
            }
            else if(instruction==7) {
                if(arrayDeque.isEmpty()) stringBuilder.append("-1\n");
                else stringBuilder.append(arrayDeque.peekFirst() + "\n");
            }
            else if(instruction==8) {
                if(arrayDeque.isEmpty()) stringBuilder.append("-1\n");
                else stringBuilder.append(arrayDeque.peekLast() + "\n");
            }
        }
        out.write(stringBuilder.toString());
        out.flush();
    }
    static class Balloon {
        int index;
        int increment;
        Balloon(int index, int increment) {
            this.index = index;
            this.increment = increment;
        }
    }
    static void p2346() throws IOException {
        final int numberOfBalloons = Integer.parseInt(in.readLine());
        StringTokenizer tokenizer = new StringTokenizer(in.readLine());
        LinkedList<Balloon> balloons = new LinkedList<>();

        for(int index=1; index<=numberOfBalloons; index++) balloons.add(new Balloon(index, Integer.parseInt(tokenizer.nextToken())));
        int removingIndex=0, increment;
        while(!balloons.isEmpty()) {
            Balloon temp = balloons.remove(removingIndex);
            increment = temp.increment;
            stringBuilder.append(String.format("%d ", temp.index));
            if(balloons.isEmpty()) break;
            if(increment>0) removingIndex = (removingIndex+increment-1) % balloons.size();
            else removingIndex = (removingIndex+increment) % balloons.size();
            if(removingIndex<0) removingIndex+=balloons.size();
        }

        out.write(stringBuilder.toString());
        out.flush();
    }
    static void p24511() throws IOException {
        final int numberOfStages = Integer.parseInt(in.readLine());

        int[] typeOfStages = new int[numberOfStages];
        StringTokenizer tokenizer = new StringTokenizer(in.readLine());
        for(int index=0; index<numberOfStages; index++) typeOfStages[index]=Integer.parseInt(tokenizer.nextToken());

        ArrayDeque<Integer> mutatedList = new ArrayDeque<>();
        tokenizer = new StringTokenizer(in.readLine());
        for(int index=0; index<numberOfStages; index++) {
            int element = Integer.parseInt(tokenizer.nextToken());
            if(typeOfStages[index]==0) mutatedList.addFirst(element);
        }

        final int numberOfInputs = Integer.parseInt(in.readLine());
        tokenizer = new StringTokenizer(in.readLine());
        for(int n=0; n<numberOfInputs; n++) {
            int input = Integer.parseInt(tokenizer.nextToken());
            mutatedList.addLast(input);
            int poppedElement = mutatedList.pop();
            stringBuilder.append(poppedElement).append(" ");
            //                stringBuilder.append(String.format("%d ", poppedElement));
        }

        out.write(stringBuilder.toString());
        out.flush();
    }
}
