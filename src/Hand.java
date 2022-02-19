import java.util.*;

public class Hand {
    /*
    A = 1
    2 = 2
    3 = 3
    ...
    J = 11
    Q = 12
    K = 13
    A = 14


    Spade = 4
    Heart = 3
    Club = 2
    Diamond = 1
     */
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_WHITE_BACKGROUND = "\033[48;5;15m";
    public static final String blue = "\033[38;5;27m";
    public static final String red = "\033[38;5;196m";
    public static final String green = "\033[38;5;34m";
    private static final String[] names = new String[]{"invalid", "Ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King", "Ace"};
    private static final String[] shortnames = new String[]{"", "A", "2", "3", "4", "5", "6", "7", "8", "9", "T", "J", "Q", "K", "A"};
    private static final String[] suitnames = new String[]{"", "Diamonds", "Clubs", "Hearts", "Spades"};
    private static final String[] shortsuits = new String[]{"", "D", "C", "H", "S"};
    private static final String[] suiticons = new String[]{"", "♦", "♣", "♥", "♠"};
    private final boolean[][] arr = new boolean[15][5];
    private int c1;
    private int c2;
    private Integer[] community;
    private long score;
    private int numCommunity = 0;
    private String status = "unknown";
    public static Random random = new Random();
    private ArrayDeque<Integer> deck = new ArrayDeque<>();

    public Hand(String card1, String card2) {
        this.c1 = cardStringToInt(card1);
        this.c2 = cardStringToInt(card2);
        add(this.c1);
        add(this.c2);
        community = new Integer[5];
        this.score = -1L;

    }

    public Hand() {
        community = new Integer[5];
        this.score = -1L;
    }

    public void makeDeck(){
        deck.clear();
        List <Integer> com = Arrays.asList(community);
        ArrayList<Integer> cards = new ArrayList<>();
        for(int value = 2; value <= 14; value++){
            for(int suit = 1; suit <= 4; suit++){
                int card = value * 10 + suit;
                if(c1 == card || c2 == card || com.contains(card)){
                    continue;
                }
                cards.add(card);
            }
        }
        Collections.shuffle(cards);
        deck.addAll(cards);
    }

    public void addToCommunity(int card) {
        community[numCommunity] = card;
        numCommunity++;
    }

    public void add(String card) {
        add(cardStringToInt(card));
        addToCommunity(cardStringToInt(card));
    }

    public void add(boolean[][] arr, int card){
        int val = card / 10;
        int suit = card % 10;
        arr[val][suit] = true;
        if(val == 14){
            arr[1][suit] = true;
        }
    }

    public void remove(boolean[][] arr, int card){
        int val = card / 10;
        int suit = card % 10;
        arr[val][suit] = false;
        if(val == 14){
            arr[1][suit] = false;
        }
    }

    public void add(int card) {
        int val = card / 10;
        int suit = card % 10;
        this.arr[val][suit] = true;
        if(val == 14){
            this.arr[1][suit] = true;
        }
    }

    private void remove(int card) {
        int val = card / 10;
        int suit = card % 10;
        this.arr[val][suit] = false;
        if(val == 14){
            this.arr[1][suit] = false;
        }
    }

    public int cardStringToInt(String card) {
        int num;
        num = java.util.Arrays.asList(shortnames).lastIndexOf(card.substring(0, 1).toUpperCase()) * 10;
        num += java.util.Arrays.asList(shortsuits).indexOf(card.substring(1).toUpperCase());
        return num;
    }

    public String getSuitColor(int suitindex) {
        switch (suitindex) {
            case 4:
                return ANSI_BLACK;
            case 3:
                return red;
            case 2:
                return green;
            case 1:
                return blue;
            default:
                return "";
        }
    }

    public void inputHand(String card1, String card2) {
        this.c1 = cardStringToInt(card1);
        this.c2 = cardStringToInt(card2);

    }

    public String cardIntToShortString(int card) {
        return ANSI_WHITE_BACKGROUND + getSuitColor(card % 10) + shortnames[card / 10] + suiticons[card % 10] + ANSI_RESET;
    }

    public String cardIntToString(int card) {
        return ANSI_WHITE_BACKGROUND + getSuitColor(card % 10) + suiticons[card % 10] + ANSI_RESET + names[card / 10] + " of " + suitnames[card % 10] + ANSI_RESET;
    }

    public String getHand(boolean longform) {
        if (longform) {
            return cardIntToString(this.c1) + "\n" + cardIntToString(this.c2);
        }
        return getHand();
    }

    public String getHand() {
        return cardIntToShortString(this.c1) + " " + cardIntToShortString(this.c2);
    }

    public String getCommunity(){
        String result = "";
        for(int x = 0; x < community.length; x++){
            result += cardIntToShortString(community[x]) + " ";
        }
        return result;
    }

    public String getStatus(){
        return status;
    }

    public long getScore(){
        return getScore(this.arr);
    }

    public long getScore(boolean[][] arr){

        long temp;
        temp = Sets.hasRoyalFlush(arr);
        if(temp != -1){
            status = "Royal Flush";
            score = temp;
            return temp;
        }
        temp = Sets.hasStraightFlush(arr);
        if(temp != -1){
            status = "Straight Flush";
            score = temp;
            return temp;
        }
        temp = Sets.has4ofAKind(arr);
        if(temp != -1){
            status = "4 of a Kind";
            score = temp;
            return temp;
        }
        temp = Sets.hasFullHouse(arr);
        if(temp != -1){
            status = "Full House";
            score = temp;
            return temp;
        }
        temp = Sets.hasFlush(arr);
        if(temp != -1){
            status = "Flush";
            score = temp;
            return temp;
        }
        temp = Sets.hasStraight(arr);
        if(temp != -1){
            status = "Straight";
            score = temp;
            return temp;
        }
        temp = Sets.has3ofAKind(arr);
        if(temp != -1){
            status = "3 of a Kind";
            score = temp;
            return temp;
        }
        temp = Sets.hasTwoPair(arr);
        if(temp != -1){
            status = "Two Pair";
            score = temp;
            return temp;
        }
        temp = Sets.hasPair(arr);
        if(temp != -1){
            status = "One Pair";
            score = temp;
            return temp;
        }
        temp = Sets.highCard(arr);
        status = "High Card";
        score = temp;
        return temp;
    }

    public int[] monteCarlo(int numOpponents, int numRounds){

        int[] results = new int[3];
        boolean[][] prevcopy = copyOf(arr);
        for(int i = 0; i < numRounds; i++){
            makeDeck();
            int[][] hands = new int[numOpponents][2];
            for(int x = numCommunity; x < community.length; x++){
                community[x] = deck.pop();
                add(prevcopy, community[x]);
            }
            long myscore = getScore(prevcopy);
            remove(prevcopy, c1);
            remove(prevcopy, c2);
            long best = 0;
            for(int n = 0; n < hands.length; n++){
                hands[n][0] = deck.pop();
                hands[n][1] = deck.pop();
            }
            for(int n = 0; n < hands.length; n++){
                add(prevcopy, hands[n][0]);
                add(prevcopy, hands[n][1]);
                long score = getScore(prevcopy);
                if(score >= best){
                    best = score;
                }
                remove(prevcopy, hands[n][0]);
                remove(prevcopy, hands[n][1]);
            }
            if(myscore > best){
                results[0]++;
            }
            else if(myscore < best){
                results[2]++;
            }
            else{
                results[1]++;
            }
            add(prevcopy, c1);
            add(prevcopy, c2);
            for(int x = numCommunity; x < community.length; x++){
                remove(prevcopy, community[x]);
                community[x] = 0;
            }
        }
        return results;
    }

    private static double round (double value, int precision) {
        int scale = (int) Math.pow(10, precision);
        return (double) Math.round(value * scale) / scale;
    }

    public void simulate(int numOpponents){
        long start = System.nanoTime();
        int[] results = monteCarlo(numOpponents, 100000);
        long end = System.nanoTime();
        System.out.println("Win: "+round(results[0] / 1000.0, 1) + "%");
        System.out.println("Tie: "+round(results[1] / 1000.0, 1) + "%");
        System.out.println("Lose: "+round(results[2] / 1000.0, 1) + "%");
        System.out.println("Time taken (seconds): "+round((end - start) / 1000000000.0, 3));
    }


    public boolean[][] copyOf(boolean[][] arr){
        boolean[][] b = new boolean[arr.length][arr[0].length];
        for(int x = 0; x < arr.length; x++){
            for(int y = 0; y < arr[0].length; y++){
                b[x][y] = arr[x][y];
            }
        }
        return b;
    }

    public void addRandom(int num){
        for(int x = 0; x < num; x++){
            addRandom(this.arr);
        }
    }

    public void addRandom(boolean[][] arr, int num){
        for(int x = 0; x < num; x++){
            addRandom(arr);
        }
    }

    public int addRandom(boolean[][] arr){
        int value = random.nextInt(13) + 2;
        int suit = random.nextInt(4) + 1;
        while(arr[value][suit]){
            value = random.nextInt(13) + 2;
            suit = random.nextInt(4) + 1;
        }
        arr[value][suit] = true;
        return value*10 + suit;
    }

    @Override
    public String toString() {
        return getHand() + "\n" + getCommunity() + "\n" + getStatus();
    }
}
