import java.util.*;

public class Main {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Hand hand = new Hand(input.nextLine(), input.nextLine());
        System.out.println(hand);
        System.out.println(hand.getHand());
        System.out.println("Enter river: ");
        hand.add(input.nextLine());
        hand.add(input.nextLine());
        hand.add(input.nextLine());
        System.out.println("Enter turn: ");
        hand.add(input.nextLine());
        System.out.println("Enter river: ");
        hand.add(input.nextLine());
        System.out.println("score: "+hand.getScore());
        System.out.println(hand);
    }
}
