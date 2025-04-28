import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Chapter10 {
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
    static void p15894() throws IOException {
        writer.write("" + (Long.parseLong(reader.readLine())*4));
        writer.flush();
    }
    static void p9063() throws IOException {
        int loop = Integer.parseInt(reader.readLine());
        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        int xMinimum = Integer.parseInt(tokenizer.nextToken());
        int yMinimum = Integer.parseInt(tokenizer.nextToken());
        int xMaximum = xMinimum;
        int yMaximum = yMinimum;

        for(int i=1; i<loop; i++) {
            tokenizer = new StringTokenizer(reader.readLine());
            int x = Integer.parseInt(tokenizer.nextToken());
            int y = Integer.parseInt(tokenizer.nextToken());
            if(x<xMinimum) xMinimum=x;
            else if(xMaximum<x) xMaximum=x;
            if(y<yMinimum) yMinimum=y;
            else if(yMaximum<y) yMaximum=y;
        }

        int area = (xMaximum-xMinimum) * (yMaximum-yMinimum);
        writer.write(area + "\n");
        writer.flush();
    }
    static void p10101() throws IOException {
        final int numberOfAngles = 3;
        int[] angles = new int[numberOfAngles];
        int sumOfAngle = 0, index = 0;
        for(int i=0; i<numberOfAngles; i++) {
            int angle = Integer.parseInt(reader.readLine());
            angles[index++] = angle;
            sumOfAngle += angle;
        }
        if(sumOfAngle!=180) writer.write("Error");
        else {
            if(angles[0]==60 && angles[1]==60 && angles[2]==60) writer.write("Equilateral");
            else if(angles[0]==angles[1] || angles[1]==angles[2] || angles[2]==angles[0]) writer.write("Isosceles");
            else writer.write("Scalene");
        }
        writer.flush();
    }
    static void p5073() throws IOException {
        StringTokenizer tokenizer;
        int side1, side2, side3;
        while(true) {
            tokenizer = new StringTokenizer(reader.readLine());
            side1 = Integer.parseInt(tokenizer.nextToken());
            side2 = Integer.parseInt(tokenizer.nextToken());
            side3 = Integer.parseInt(tokenizer.nextToken());

            int longest, sumOfOthers;
            if(side1>side2 && side1>side3) {
                longest = side1;
                sumOfOthers = side2 + side3;
            }
            else if(side2>side1 && side2>side3) {
                longest = side2;
                sumOfOthers = side1 + side3;
            }
            else {
                longest = side3;
                sumOfOthers = side1 + side2;
            }

            if(side1==0 && side2==0 && side3==0) break;
            else if(longest>=sumOfOthers) writer.write("Invalid\n");
            else if(side1==side2 && side1==side3) writer.write("Equilateral\n");
            else if(side1==side2 || side2==side3 || side3==side1) writer.write("Isosceles\n");
            else writer.write("Scalene\n");
        }
        writer.flush();
    }
    static void p14215() throws IOException {
        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        int[] sticks = {Integer.parseInt(tokenizer.nextToken()), Integer.parseInt(tokenizer.nextToken()), Integer.parseInt(tokenizer.nextToken())};
        Arrays.sort(sticks);
        while(sticks[2]>=sticks[0]+sticks[1]) sticks[2]--;
        writer.write("" + (sticks[0]+sticks[1]+sticks[2]));
        writer.flush();
    }
}
