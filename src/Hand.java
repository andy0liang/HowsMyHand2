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


    Spade = 4
    Heart = 2
    Club = 1
    Diamond = 0
     */
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_WHITE_BACKGROUND = "\033[48;5;15m";
    public static final String blue = "\033[38;5;27m";
    public static final String red = "\033[38;5;196m";
    public static final String green = "\033[38;5;34m";
    private static final String[] names = new String[]{"invalid", "Ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King", "Ace"};
    private static final String[] shortnames = new String[]{"", "A", "2", "3", "4", "5", "6", "7", "8", "9", "T", "J", "Q", "K"};
    private static final String[] suitnames = new String[]{"Diamonds", "Clubs", "Hearts", "Spades"};
    private static final String[] shortsuits = new String[]{"D", "C", "H", "S"};
    private static final String[] suiticons = new String[]{"♦", "♣", "♥", "♠"};
    private final boolean[][] arr = new boolean[14][4];
    private int c1;
    private int c2;
    private final int[] community;

    public Hand(String card1, String card2) {
        this.c1 = cardStringToInt(card1);
        this.c2 = cardStringToInt(card2);
        community = new int[5];
    }

    public Hand() {
        community = new int[5];
    }

    public void addToCommunity(int card) {
        for (int x = 0; x < community.length; x++) {
            if (community[x] == 0) {
                community[x] = card;
                return;
            }
        }
    }

    public void add(String card) {
        add(cardStringToInt(card));
        addToCommunity(cardStringToInt(card));
    }

    public void add(int card) {
        int val = card / 10;
        int suit = card % 10;
        this.arr[val][suit] = true;
    }

    private void remove(int card) {
        int val = card / 10;
        int suit = card % 10;
        this.arr[val][suit] = false;
    }

    public int cardStringToInt(String card) {
        int num;
        num = java.util.Arrays.asList(shortnames).indexOf(card.substring(0, 1).toUpperCase()) * 10;
        num += java.util.Arrays.asList(shortsuits).indexOf(card.substring(1).toUpperCase());
        return num;
    }

    public String getSuitColor(int suitindex) {
        switch (suitindex) {
            case 0:
                return ANSI_BLACK;
            case 1:
                return red;
            case 2:
                return green;
            case 3:
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

    @Override
    public String toString() {
        return getHand(true);
    }
}
