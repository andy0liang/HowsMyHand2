import java.util.*;

public class Main {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Hand hand = new Hand();
        hand.addRandom(5);
        System.out.println(hand.getScore());
    }
}
