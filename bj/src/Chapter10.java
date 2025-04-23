import java.io.*;

public class Chapter10 {
    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

    static void p27323() throws IOException {
        int height = Integer.parseInt(reader.readLine());
        int width = Integer.parseInt(reader.readLine());
        writer.write(height*width + "\n");
        writer.flush();
    }
}
