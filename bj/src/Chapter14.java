import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Chapter14 {
    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));

    static void p10815() throws IOException {
        StringBuilder builder = new StringBuilder();
        final int numberOfCards = Integer.parseInt(in.readLine());
        HashSet<Integer> cards = new HashSet<>();
        StringTokenizer tokenizer = new StringTokenizer(in.readLine());
        for(int n=0; n<numberOfCards; n++) cards.add(Integer.parseInt(tokenizer.nextToken()));

        final int numberOfSearches = Integer.parseInt(in.readLine());
        tokenizer = new StringTokenizer(in.readLine());
        for(int n=0; n<numberOfSearches; n++) {
            int targetOfSearch = Integer.parseInt(tokenizer.nextToken());
            if(cards.contains(targetOfSearch)) builder.append("1 ");
            else builder.append("0 ");
        }
        out.write(builder.toString());
        out.flush();
    }
    static void p14425() throws IOException {
        StringTokenizer tokenizer = new StringTokenizer(in.readLine());
        final int numberOfTexts = Integer.parseInt(tokenizer.nextToken());
        final int numberOfSearches = Integer.parseInt(tokenizer.nextToken());

        HashSet<String> texts = new HashSet<>();
        int count = 0;
        for(int n=0; n<numberOfTexts; n++) texts.add(in.readLine());
        for(int n=0; n<numberOfSearches; n++) if(texts.contains(in.readLine())) count++;
        out.write(count + "\n");
        out.flush();
    }
    static void p7785() throws IOException {
        final int numberOfLines = Integer.parseInt(in.readLine());
        HashSet<String> names = new HashSet<>();
        StringTokenizer tokenizer;
        for(int n=0; n<numberOfLines; n++) {
            tokenizer = new StringTokenizer(in.readLine());
            String name = tokenizer.nextToken();
            String action = tokenizer.nextToken();
            if(action.equals("enter")) names.add(name);
            else if(action.equals("leave")) names.remove(name);
        }

        List<String> sortedNames = names.stream().sorted(Comparator.reverseOrder()).collect(Collectors.toList());
        StringBuilder stringBuilder = new StringBuilder();
        for(int index=0; index<sortedNames.size(); index++) {
            stringBuilder.append(sortedNames.get(index) + "\n");
        }
        out.write(stringBuilder.toString());
        out.flush();
    }
    static void p1620() throws IOException {
        StringTokenizer tokenizer = new StringTokenizer(in.readLine());
        final int numberOfMonsters = Integer.parseInt(tokenizer.nextToken());
        final int numberOfSearches = Integer.parseInt(tokenizer.nextToken());

        HashMap<String, String> mapOfMonsters = new HashMap<>();
        for(int index=0; index<numberOfMonsters; index++) {
            String nameOfMonster = in.readLine();
            mapOfMonsters.put(nameOfMonster, Integer.toString(index+1));
            mapOfMonsters.put(Integer.toString(index+1), nameOfMonster);
        }

        StringBuilder stringBuilder = new StringBuilder();
        for(int n=0; n<numberOfSearches; n++) {
            stringBuilder.append(mapOfMonsters.get(in.readLine()) + "\n");
        }
        out.write(stringBuilder.toString());
        out.flush();
    }
    static void p10816() throws IOException {
        HashMap<Integer, Integer> mapOfCards = new HashMap<>();

        final int numberOfCards = Integer.parseInt(in.readLine());
        StringTokenizer stringTokenizer = new StringTokenizer(in.readLine());
        for(int index=0; index<numberOfCards; index++) {
            int card = Integer.parseInt(stringTokenizer.nextToken());
            if(mapOfCards.containsKey(card)) mapOfCards.put(card, mapOfCards.get(card)+1);
            else mapOfCards.put(card, 1);
        }

        StringBuilder stringBuilder = new StringBuilder();
        final int numberOfSearches = Integer.parseInt(in.readLine());
        stringTokenizer = new StringTokenizer(in.readLine());
        for(int n=0; n<numberOfSearches; n++) {
            int targetOfSearch = Integer.parseInt(stringTokenizer.nextToken());
            if(mapOfCards.containsKey(targetOfSearch)) stringBuilder.append(mapOfCards.get(targetOfSearch) + " ");
            else stringBuilder.append("0 ");
        }
        out.write(stringBuilder.toString());
        out.flush();
    }
    static void p1764() throws IOException {
        StringTokenizer tokenizer = new StringTokenizer(in.readLine());
        final int numberOfNeverHeard = Integer.parseInt(tokenizer.nextToken());
        final int numberOfNeverSeen = Integer.parseInt(tokenizer.nextToken());

        HashSet<String> setOfNeverHeard = new HashSet<>();
        for(int n=0; n<numberOfNeverHeard; n++) setOfNeverHeard.add(in.readLine());

        ArrayList<String> listOfNeverBoth = new ArrayList<>();
        int countOfNeverHeardAndSeen = 0;
        for(int n=0; n<numberOfNeverSeen; n++) {
            String targetOfSearch = in.readLine();
            if(setOfNeverHeard.contains(targetOfSearch)) {
                listOfNeverBoth.add(targetOfSearch);
                countOfNeverHeardAndSeen++;
            }
        }

        listOfNeverBoth.sort(Comparator.naturalOrder());
        StringBuilder stringBuilder = new StringBuilder();
        for(int index=0; index<listOfNeverBoth.size(); index++) {
            stringBuilder.append(listOfNeverBoth.get(index) + "\n");
        }

        out.write(countOfNeverHeardAndSeen + "\n" + stringBuilder);
        out.flush();
    }
    static void p1269() throws IOException {
        StringTokenizer stringTokenizer = new StringTokenizer(in.readLine());
        final int numberOfSet1 = Integer.parseInt(stringTokenizer.nextToken());
        final int numberOfSet2 = Integer.parseInt(stringTokenizer.nextToken());

        stringTokenizer = new StringTokenizer(in.readLine());
        HashSet<Integer> set1 = new HashSet<>();
        for(int n=0; n<numberOfSet1; n++) {
            set1.add(Integer.parseInt(stringTokenizer.nextToken()));
        }

        stringTokenizer = new StringTokenizer(in.readLine());
        int numberOfIntersect = 0;
        for(int n=0; n<numberOfSet2; n++) {
            if(set1.contains(Integer.parseInt(stringTokenizer.nextToken()))) numberOfIntersect++;
        }

        out.write((numberOfSet1+numberOfSet2-2*numberOfIntersect) + "\n");
        out.flush();
    }
    static void p11478() throws IOException {
        String text = in.readLine();
        HashSet<String> setOfSubText = new HashSet<>();
        for(int length=1; length<=text.length(); length++) {
            for(int startingIndex=0; startingIndex<text.length()-length+1; startingIndex++) {
                setOfSubText.add(text.substring(startingIndex, startingIndex+length));
            }
        }
        out.write(setOfSubText.size() + "");
        out.flush();
    }
}
