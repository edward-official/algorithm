import java.io.*;
import java.util.StringTokenizer;

public class Chapter5 {
    static void p27866() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));

        String text = bufferedReader.readLine();
        int index = Integer.parseInt(bufferedReader.readLine()) - 1;

        bufferedWriter.write(text.charAt(index));

        bufferedReader.close();
        bufferedWriter.close();
    }
    static void p2743() throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));

        String text = in.readLine();
        out.write(Integer.toString(text.length()));

        in.close();
        out.close();
    }
    static void p9086() throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));

        int loop = Integer.parseInt(in.readLine());
        for(int i=0; i<loop; i++) {
            String text = in.readLine();
            out.write(Character.toString(text.charAt(0)) + Character.toString(text.charAt(text.length()-1)) + "\n");
        }

        in.close();
        out.close();
    }
    static void p11654() throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));

        String text = in.readLine();
        out.write("" + (int)text.charAt(0));

        in.close();
        out.close();
    }
    static void p11720() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(bufferedReader.readLine()), sum = 0;
        String text = bufferedReader.readLine();
        for(int i=0; i<n; i++) {
            sum += Integer.parseInt(Character.toString(text.charAt(i)));
        }
        bufferedWriter.write("" + sum);

        bufferedReader.close();
        bufferedWriter.close();
    }
    static void p10809() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));

        String text = bufferedReader.readLine();
        final int size = 'z'-'a'+1;
        int[] array = new int[size];
        for(int i=0; i<size; i++) array[i] = -1;
        for(int i=0; i<text.length(); i++) if(array[text.charAt(i)-'a']==-1) array[text.charAt(i)-'a']=i;
        for(int i=0; i<size; i++) bufferedWriter.write(array[i] + " ");

        bufferedReader.close();
        bufferedWriter.close();
    }
    static void p2675() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));

        int loop = Integer.parseInt(bufferedReader.readLine());
        StringTokenizer stringTokenizer;
        for(int i=0; i<loop; i++) {
            stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");
            int repeat = Integer.parseInt(stringTokenizer.nextToken());
            String target = stringTokenizer.nextToken();
            for(int idx=0; idx<target.length(); idx++) {
                for(int j=0; j<repeat; j++) bufferedWriter.write(target.charAt(idx));
            }
            bufferedWriter.write("\n");
        }

        bufferedWriter.close();
        bufferedReader.close();
    }
    static void p1152() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        int count = 0;
        while(stringTokenizer.hasMoreTokens()) {
            count++;
            stringTokenizer.nextToken();
        }
        bufferedWriter.write("" + count);

        bufferedReader.close();
        bufferedWriter.close();
    }
    static void p2908() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");
        String number1AsString = stringTokenizer.nextToken(), number2AsString = stringTokenizer.nextToken();
        StringBuilder stringBuilder = new StringBuilder(number1AsString);
        number1AsString = stringBuilder.reverse().toString();
        number2AsString = stringBuilder.delete(0,stringBuilder.length()).append(number2AsString).reverse().toString();
        int number1 = Integer.parseInt(number1AsString), number2 = Integer.parseInt(number2AsString);
        if(number1>number2) bufferedWriter.write("" + number1);
        else bufferedWriter.write("" + number2);

        bufferedReader.close();
        bufferedWriter.close();
    }
    static void p5622() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));

        String text = bufferedReader.readLine();
        int sum = 0;
        for(int i=0; i<text.length(); i++) {
            if('A' <= text.charAt(i) && text.charAt(i) <= 'C') sum += 3;
            else if('D' <= text.charAt(i) && text.charAt(i) <= 'F') sum += 4;
            else if('G' <= text.charAt(i) && text.charAt(i) <= 'I') sum += 5;
            else if('J' <= text.charAt(i) && text.charAt(i) <= 'L') sum += 6;
            else if('M' <= text.charAt(i) && text.charAt(i) <= 'O') sum += 7;
            else if('P' <= text.charAt(i) && text.charAt(i) <= 'S') sum += 8;
            else if('T' <= text.charAt(i) && text.charAt(i) <= 'V') sum += 9;
            else if('W' <= text.charAt(i) && text.charAt(i) <= 'Z') sum += 10;
        }
        bufferedWriter.write("" + sum);

        bufferedReader.close();
        bufferedWriter.close();
    }
    static void p11718() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));

        while(true) {
            String text = bufferedReader.readLine();
            if(text==null) break;
            bufferedWriter.write(text + "\n");
        }

        bufferedReader.close();
        bufferedWriter.close();
    }
}
