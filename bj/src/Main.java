import java.awt.*;
import java.io.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Stack;
import java.util.StringTokenizer;


class Chapter10 {
    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

    static void p27323() throws IOException {
        int height = Integer.parseInt(reader.readLine());
        int width = Integer.parseInt(reader.readLine());
        writer.write(height*width + "\n");
        writer.flush();
    }
    static void p1085() throws IOException {
        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        int x = Integer.parseInt(tokenizer.nextToken());
        int y = Integer.parseInt(tokenizer.nextToken());
        int width = Integer.parseInt(tokenizer.nextToken());
        int height = Integer.parseInt(tokenizer.nextToken());
        int shortest = -1;

        if(x < width-x) shortest = x;
        else shortest = width-x;

        if(shortest > y) shortest = y;
        if(shortest > height-y) shortest = height-y;
        writer.write(shortest + "\n");
        writer.flush();
    }
    static void p3009() throws IOException {
        StringTokenizer tokenizer;
        tokenizer = new StringTokenizer(reader.readLine());
        int x1 = Integer.parseInt(tokenizer.nextToken());
        int y1 = Integer.parseInt(tokenizer.nextToken());
        tokenizer = new StringTokenizer(reader.readLine());
        int x2 = Integer.parseInt(tokenizer.nextToken());
        int y2 = Integer.parseInt(tokenizer.nextToken());
        tokenizer = new StringTokenizer(reader.readLine());
        int x3 = Integer.parseInt(tokenizer.nextToken());
        int y3 = Integer.parseInt(tokenizer.nextToken());

        int x4, y4;
        if(x1==x2) x4=x3;
        else if(x1==x3) x4=x2;
        else x4=x1;
        if(y1==y2) y4=y3;
        else if(y1==y3) y4=y2;
        else y4=y1;

        writer.write(x4 + " " + y4);
        writer.flush();
    }
}


public class Main {
    public static void main(String[] args) {
        try {
            Chapter10.p3009();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
}