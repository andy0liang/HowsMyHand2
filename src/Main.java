import java.util.*;

public class Main {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Hand hand = new Hand(input.nextLine(), input.nextLine());
        System.out.println(hand);
        System.out.println(hand.getHand());
    }
}
