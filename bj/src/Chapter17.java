import java.io.*;
import java.util.StringTokenizer;

public class Chapter17 {
    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder stringBuilder = new StringBuilder();

    static void p15439() throws IOException {
        final int numberOfClothes = Integer.parseInt(in.readLine());
        stringBuilder.append(numberOfClothes*numberOfClothes-numberOfClothes);
        out.write(stringBuilder.toString());
        out.flush();
    }
    static void p24723() throws IOException {
        final int height = Integer.parseInt(in.readLine());
        stringBuilder.append((int)Math.pow(2, height));
        out.write(stringBuilder.toString());
        out.flush();
    }
    static void p10872() throws IOException {
        final int input = Integer.parseInt(in.readLine());
        int result = 1;
        for(int multiplier=2; multiplier<=input; multiplier++) result*=multiplier;
        stringBuilder.append(result);
        out.write(stringBuilder.toString());
        out.flush();
    }
    static void p11050() throws IOException {
        StringTokenizer tokenizer = new StringTokenizer(in.readLine());
        final int numberOfOptions = Integer.parseInt(tokenizer.nextToken());
        final int numberOfSelects = Integer.parseInt(tokenizer.nextToken());

        int result = 1;
        for(int multiplier=numberOfOptions; multiplier>numberOfOptions-numberOfSelects; multiplier--) result*=multiplier;
        for(int divider=2; divider<=numberOfSelects; divider++) result/=divider;

        stringBuilder.append(result);
        out.write(stringBuilder.toString());
        out.flush();
    }
    static int numberOfPossibleSolutions(int numberOfOptions, int numberOfSelects) {
        if(numberOfSelects>numberOfOptions-numberOfSelects) numberOfSelects=numberOfOptions-numberOfSelects;
        long result = 1;
        for(int multiplier=numberOfOptions; multiplier>numberOfOptions-numberOfSelects; multiplier--) result*=multiplier;
        for(int divider=2; divider<=numberOfSelects; divider++) result/=divider;
        return (int)result;
    }
    static void p1010() throws IOException {
        final int numberOfInputs = Integer.parseInt(in.readLine());
        int numberOfLeft, numberOfRight;
        StringTokenizer tokenizer;

        for(int n=0; n<numberOfInputs; n++) {
            tokenizer = new StringTokenizer(in.readLine());
            numberOfLeft = Integer.parseInt(tokenizer.nextToken());
            numberOfRight = Integer.parseInt(tokenizer.nextToken());
            stringBuilder.append(numberOfPossibleSolutions(numberOfRight, numberOfLeft)).append("\n");
        }

        out.write(stringBuilder.toString());
        out.flush();
    }
}