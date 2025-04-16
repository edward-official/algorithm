import java.io.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Chapter6 {
    static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));

    static void p25083() {
        try {
            bufferedWriter.write("         ,r'\"7\n");
            bufferedWriter.write("r`-_   ,'  ,/\n");
            bufferedWriter.write(" \\. \". L_r'\n");
            bufferedWriter.write("   `~\\/\n");
            bufferedWriter.write("      |\n");
            bufferedWriter.write("      |\n");
            bufferedWriter.flush();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
    static void p3003() {
        final int[] validSet = {1,1,2,2,2,8};
        try {
            StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");
            int temp;
            for(int i=0; i<validSet.length; i++) {
                temp = Integer.parseInt(stringTokenizer.nextToken());
                bufferedWriter.write(validSet[i]-temp + " ");
            }
            bufferedWriter.flush();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
    static void p2444() {
        try {
            int starSize = Integer.parseInt(bufferedReader.readLine());
            for(int i=0; i<starSize; i++) {
                for(int j=0; j<starSize-i-1; j++) bufferedWriter.write(" ");
                for(int j=0; j<2*i+1; j++) bufferedWriter.write("*");
                bufferedWriter.newLine();
            }
            for(int i=starSize-2; i>=0; i--) {
                for(int j=0; j<starSize-i-1; j++) bufferedWriter.write(" ");
                for(int j=0; j<2*i+1; j++) bufferedWriter.write("*");
                bufferedWriter.newLine();
            }
            bufferedWriter.flush();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
    static void p10988() {
        try {
            String text = bufferedReader.readLine(), outTerminal;
            outTerminal = "1";
            for(int i=0; i<text.length()/2; i++) {
                if(text.charAt(i)!=text.charAt(text.length()-1-i)) {
                    outTerminal = "0";
                    break;
                }
            }
            bufferedWriter.write(outTerminal);
            bufferedWriter.flush();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
    static void p1157() {
        try {
            String text = bufferedReader.readLine();
            text = text.toUpperCase();
            HashMap<Character, Integer> map = new HashMap<>();
            int maxCount = 0;
            Character maxCharacter = ' ';
            for(int i=0; i<text.length(); i++) {
                if(!map.containsKey(text.charAt(i))) {
                    if(maxCount==0) {
                        maxCount++;
                        maxCharacter = text.charAt(i);
                    }
                    map.put(text.charAt(i), 1);
                }
                else {
                    Character temp = text.charAt(i);
                    map.put(temp, map.get(temp)+1);
                    if(maxCount<map.get(temp)) {
                        maxCount = map.get(temp);
                        maxCharacter = temp;
                    }
                }
            }
            for(int i=0; i<text.length(); i++) {
                if(map.get(text.charAt(i))==maxCount && text.charAt(i)!=maxCharacter) {
                    bufferedWriter.write("?");
                    bufferedWriter.flush();
                    return;
                }
            }
            bufferedWriter.write(maxCharacter);
            bufferedWriter.flush();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
    static void p2941() {
        try {
            String text = bufferedReader.readLine();
            int count = 0;
            for(int i=0; i<text.length(); i++) {
                if(i+1==text.length()) {}
                else if(text.charAt(i)=='c') {
                    if(text.charAt(i+1)=='=') i++;
                    else if(text.charAt(i+1)=='-') i++;
                }
                else if(text.charAt(i)=='d') {
                    if(i+2<text.length() && text.charAt(i+1)=='z' && text.charAt(i+2)=='=') i+=2;
                    else if(text.charAt(i+1)=='-') i++;
                }
                else if(text.charAt(i)=='l' && text.charAt(i+1)=='j') i++;
                else if(text.charAt(i)=='n' && text.charAt(i+1)=='j') i++;
                else if(text.charAt(i)=='s' && text.charAt(i+1)=='=') i++;
                else if(text.charAt(i)=='z' && text.charAt(i+1)=='=') i++;
                count++;
            }
            bufferedWriter.write("" + count);
            bufferedWriter.flush();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
    static boolean isGroupWord(String word) {
        if(word.length()<=2) return true;
        HashSet<Character> alphabets = new HashSet<>();
        int index = 0;
        Character recentlyAdded = word.charAt(index);
        alphabets.add(recentlyAdded);
        while(true) {
            if(++index==word.length()) break;
            if(recentlyAdded!=word.charAt(index)) {
                if(!alphabets.contains(word.charAt(index))) alphabets.add(word.charAt(index));
                else return false;
                recentlyAdded = word.charAt(index);
            }
        }
        return true;
    }
    static void p1316() {
        try {
            int loop = Integer.parseInt(bufferedReader.readLine()), count = 0;
            for(int i=0; i<loop; i++) {
                String temp = bufferedReader.readLine();
                if(isGroupWord(temp)) count++;
            }
            bufferedWriter.write("" + count);
            bufferedWriter.flush();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
    static void p25206() {
        try {
            String line, grade;
            StringTokenizer stringTokenizer;
            double totalCredits = 0.0, totalGrade = 0.0, credit;
            for(int i=0; i<20; i++) {
                line = bufferedReader.readLine();
                stringTokenizer = new StringTokenizer(line, " ");
                stringTokenizer.nextToken();
                credit = Double.parseDouble(stringTokenizer.nextToken());
                totalCredits += credit;
                grade = stringTokenizer.nextToken();
                if(grade.equals("A+")) totalGrade += 4.5 * credit;
                else if(grade.equals("A0")) totalGrade += 4.0 * credit;
                else if(grade.equals("B+")) totalGrade += 3.5 * credit;
                else if(grade.equals("B0")) totalGrade += 3.0 * credit;
                else if(grade.equals("C+")) totalGrade += 2.5 * credit;
                else if(grade.equals("C0")) totalGrade += 2.0 * credit;
                else if(grade.equals("D+")) totalGrade += 1.5 * credit;
                else if(grade.equals("D0")) totalGrade += 1.0 * credit;
                else if(grade.equals("P")) totalCredits -= credit;
            }
            bufferedWriter.write("" + totalGrade/totalCredits);
            bufferedWriter.flush();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
}
