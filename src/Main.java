import java.util.*;

public class Main {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.println("Welcome!");
        while(true) {
            System.out.println("Enter your hand: ");
            System.out.print("Card 1: ");
            String c1 = input.nextLine();
            System.out.print("Card 2: ");
            String c2 = input.nextLine();
            Hand hand = new Hand(c1, c2);
            System.out.println("Your hand: " + hand.getHand());
            System.out.print("How many opponents? ");
            int numO = Integer.parseInt(input.nextLine());
            hand.simulate(numO);
            System.out.println("Enter flop: ");
            System.out.print("Card 1: ");
            String r1 = input.nextLine();
            if(r1.equals("gg")){
                continue;
            }
            System.out.print("Card 2: ");
            String r2 = input.nextLine();
            System.out.print("Card 3: ");
            String r3 = input.nextLine();
            hand.add(r1);
            hand.add(r2);
            hand.add(r3);
            System.out.println("Community cards: " + hand.getCommunity());
            System.out.print("How many opponents? ");
            numO = Integer.parseInt(input.nextLine());
            hand.simulate(numO);
            System.out.print("Enter turn: ");
            String t = input.nextLine();
            if(t.equals("gg")){
                continue;
            }
            hand.add(t);
            System.out.println("Community cards: " + hand.getCommunity());
            System.out.print("How many opponents? ");
            numO = Integer.parseInt(input.nextLine());
            hand.simulate(numO);
            System.out.print("Enter river: ");
            String r = input.nextLine();
            if(r.equals("gg")){
                continue;
            }
            hand.add(r);
            System.out.println("Community cards: " + hand.getCommunity());
            System.out.print("How many opponents? ");
            numO = Integer.parseInt(input.nextLine());
            hand.simulate(numO);
            System.out.println("========================\n\nPress Enter to restart:");
            input.nextLine();
            System.out.println("\n\n");
        }
    }
}
