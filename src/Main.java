import java.util.*;

public class Main {

    public static void main(String[] args) {
        System.out.println("Welcome!");
        while (true) {
            try {
                run();
            } catch (Exception e) {
                System.out.println("Invalid entry, please restart\n\n");
            }
        }
    }

    public static void run() {
        Scanner input = new Scanner(System.in);
        while (true) {
            System.out.println("Enter your hand: ");
            System.out.print("Card 1: ");
            String c1 = input.nextLine();
            System.out.print("Card 2: ");
            String c2 = input.nextLine();
            Hand hand = new Hand(c1, c2);
            System.out.println("\nYour hand: " + hand.getHand() + "\n");
            System.out.print("How many opponents? ");
            int numO = Integer.parseInt(input.nextLine());
            System.out.print("Number of simulations (default 100,000): ");
            String numS = input.nextLine();
            if (!numS.isBlank()) {
                hand.setNumSimulations(Integer.parseInt(numS));
            }
            hand.simulate(numO);
            System.out.println("Enter flop: ");
            System.out.print("Card 1: ");
            String r1 = input.nextLine();
            if (r1.equals("gg")) {
                continue;
            }
            System.out.print("Card 2: ");
            String r2 = input.nextLine();
            System.out.print("Card 3: ");
            String r3 = input.nextLine();
            hand.add(r1);
            hand.add(r2);
            hand.add(r3);
            System.out.println("\nYour hand: " + hand.getHand());
            System.out.println("Community cards: " + hand.getCommunity() + "\n");
            System.out.print("How many opponents? ");
            numO = Integer.parseInt(input.nextLine());
            hand.simulate(numO);
            System.out.print("Enter turn: ");
            String t = input.nextLine();
            if (t.equals("gg")) {
                continue;
            }
            hand.add(t);
            System.out.println("\nYour hand: " + hand.getHand());
            System.out.println("Community cards: " + hand.getCommunity() + "\n");
            System.out.print("How many opponents? ");
            numO = Integer.parseInt(input.nextLine());
            hand.simulate(numO);
            System.out.print("Enter river: ");
            String r = input.nextLine();
            if (r.equals("gg")) {
                continue;
            }
            hand.add(r);
            System.out.println("\nYour hand: " + hand.getHand());
            System.out.println("Community cards: " + hand.getCommunity() + "\n");
            System.out.print("How many opponents? ");
            numO = Integer.parseInt(input.nextLine());
            hand.simulate(numO);
            System.out.println("========================\n\nPress Enter to restart:");
            input.nextLine();
            System.out.println("\n\n");
        }
    }
}
