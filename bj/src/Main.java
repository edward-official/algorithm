import java.awt.*;
import java.io.*;
import java.util.*;


public class Main {
    static class Chapter12 {
        static Scanner in = new Scanner(System.in);

        static void p2798() throws IOException {
            int numberOfCards = in.nextInt(), maxmimum = in.nextInt();
            int[] cards = new int[numberOfCards];
            for(int i=0; i<numberOfCards; i++) cards[i] = in.nextInt();
            Arrays.sort(cards);

            int optimal = -1;
            for(int select1=0; select1<numberOfCards-2; select1++) {
                for(int select2=select1+1; select2<numberOfCards-1; select2++) {
                    for(int select3=select2+1; select3<numberOfCards; select3++) {
                        if(cards[select1]+cards[select2]+cards[select3] <= maxmimum) {
                            if(optimal < cards[select1]+cards[select2]+cards[select3]) {
                                optimal = cards[select1]+cards[select2]+cards[select3];
                            }
                        }
                        else break;
                    }
                }
            }

            System.out.println(optimal);
        }
        static int getNumberOfDigits(int item) {
            int numberOfDigits = 0;
            while(item>0) {
                numberOfDigits++;
                item/=10;
            }
            return numberOfDigits;
        }
        static int getSumOfDigits(int item) {
            int result = 0;
            while(item > 0) {
                result += item%10;
                item /= 10;
            }
            return result;
        }
        static int findGenerator(int generated) {
            int numberOfDigits = getNumberOfDigits(generated);
            for(int item=generated-numberOfDigits*9; item<generated; item++) {
                if(item + getSumOfDigits(item) == generated) return item;
            }
            return 0;
        }
        static void p2231() throws IOException {
            System.out.println(findGenerator(in.nextInt()));
            /*
            int numberOfTestCases = in.nextInt();
            for(int i=0; i<numberOfTestCases; i++) {
                int item = in.nextInt();
                System.out.println(findGenerator(item));
            }
             */
        }
        static void p19532() throws IOException {
            /*
            ax+by=c
            dx+ey=f

            aex+bey=ce
            bdx+bey=bf
            (ae-bd)x=ce-bf
            x=(c*e-b*f)/(a*e-b*d);

            adx+bdy=cd
            adx+aey=af
            (bd-ae)y=cd-af
            y=(c*d-a*f)/(b*d-a*e);

             */
            final int loop = 6;
            int[] constants = new int[loop];
            for(int i=0; i<loop; i++) constants[i] = in.nextInt();
            int x = (constants[2]*constants[4]-constants[1]*constants[5])/(constants[0]*constants[4]-constants[1]*constants[3]);
            int y = (constants[2]*constants[3]-constants[0]*constants[5])/(constants[1]*constants[3]-constants[0]*constants[4]);
            System.out.println(x + " " + y);
        }
        static void boardInTerminal(char[][] board, int sizeOfBoard, int startingRowIndex, int startingColumnIndex) {
            for(int rowIndex=startingRowIndex; rowIndex<startingRowIndex+sizeOfBoard; rowIndex++) {
                for(int columnIndex=startingColumnIndex; columnIndex<startingColumnIndex+sizeOfBoard; columnIndex++) {
                    System.out.printf(board[rowIndex][columnIndex] + "");
                }
                System.out.println();
            }
        }
        static void p1018() throws IOException {
            final int sizeOfBoard = 8;
            StringTokenizer tokenizer = new StringTokenizer(in.nextLine());
            int sizeOfRow = Integer.parseInt(tokenizer.nextToken()), sizeOfColumn = Integer.parseInt(tokenizer.nextToken());
            char[][] entireBoard = new char[sizeOfRow][sizeOfColumn];
            for(int rowIndex=0; rowIndex<sizeOfRow; rowIndex++) {
                String line = in.nextLine();
                for(int columnIndex=0; columnIndex<sizeOfColumn; columnIndex++) {
                    entireBoard[rowIndex][columnIndex] = line.charAt(columnIndex);
                }
            }

            int optimal = sizeOfBoard*sizeOfBoard;
            for(int startingRowIndex=0; startingRowIndex<=sizeOfRow-sizeOfBoard; startingRowIndex++) {
                for(int startingColumnIndex=0; startingColumnIndex<=sizeOfColumn-sizeOfBoard; startingColumnIndex++) {
                    int count = 0;
                    for(int rowIndex=startingRowIndex; rowIndex<startingRowIndex+sizeOfBoard; rowIndex++) {
                        char criteriaItem = entireBoard[startingRowIndex][startingColumnIndex];
                        int sumOfCriteriaIndex = startingRowIndex+startingColumnIndex;
                        for(int columnIndex=startingColumnIndex; columnIndex<startingColumnIndex+sizeOfBoard; columnIndex++) {
                            int sumOfIndex = rowIndex+columnIndex;
                            if(sumOfIndex%2==sumOfCriteriaIndex%2 && entireBoard[rowIndex][columnIndex]!=criteriaItem) count++;
                            else if(sumOfIndex%2!=sumOfCriteriaIndex%2 && entireBoard[rowIndex][columnIndex]==criteriaItem) count++;
                        }
                    }
                    if(count>sizeOfBoard*sizeOfBoard/2) count = sizeOfBoard*sizeOfBoard-count;
                    if(count<optimal) optimal=count;
//                    System.out.printf("[" + startingRowIndex + ", " + startingColumnIndex + " ... " + count + ']');
                }
//                System.out.println();
            }
            System.out.println(optimal);
        }
        static void p1436() throws IOException {
            HashSet<String> numbersAsStringSet = new HashSet<>();
            int n = in.nextInt();
            String numberAsString = "666";
            while(true) {
                if(numberAsString.contains("666")) {
                    numbersAsStringSet.add(numberAsString);
                    if(numbersAsStringSet.size()==n) break;
                }
                numberAsString = Integer.toString(Integer.parseInt(numberAsString)+1);
            }
            int[] numbers = numbersAsStringSet.stream().mapToInt(Integer::parseInt).toArray();
            Arrays.sort(numbers);
            System.out.println(numbers[n-1]);
        }
    }

    public static void main(String[] args) {
        try {
            Chapter12.p1436();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
}