public class Lab6 {

    public static LinkedList initialize_deck() {

        LinkedList deck = new LinkedList();

        // populate linked list with a single deck of cards
        for (Card.suites s : Card.suites.values()) {
            for(Card.ranks r : Card.ranks.values()) {
                if (r != Card.ranks.NULL && s != Card.suites.NULL) {
                    Card newCard = new Card(s, r);
                    //newCard.print_card();
                    deck.add_at_tail(newCard);
                }
            }
        }

        return deck;
    }

    private static void play_blind_mans_bluff(LinkedList player1, LinkedList computer, LinkedList deck) {
        System.out.println("\nStarting Blind mans Bluff \n");
        int consecutiveLosses = 0; // To track consecutive losses of player1

        // for loop to run the game at most five times
        for (int round = 0; round < 5; round++) {
            if (player1.size == 0 || computer.size == 0) {
                System.out.println("Not enough cards to continue. Ending game.");
                break;
            }

            Card player1Card = player1.remove_from_head();
            Card computerCard = computer.remove_from_head();
            System.out.println("Round " + (round + 1) + ":");
            System.out.print("Player 1 draws ");
            player1Card.print_card();
            System.out.println();

            System.out.print("Computer draws ");
            computerCard.print_card();
            System.out.println();

            int comparisonResult = compareCards(player1Card, computerCard);

            if (comparisonResult > 0) {
                System.out.println("Player 1 wins this round!\n");
                consecutiveLosses = 0; // Reset consecutive loss count
            } else if (comparisonResult < 0) {
                System.out.println("Computer wins this round!\n");
                consecutiveLosses++;
                if (consecutiveLosses == 3) {
                    // Player 1 has lost 3 times in a row
                    rage_quit(); // Exit the game
                    return; // Ensure the method exits after rage_quit is called
                }
            } else {
                System.out.println("It's a tie!\n");

            }

            // Add the played cards back to the bottom of the deck
            deck.add_at_tail(player1Card);
            deck.add_at_tail(computerCard);
        }

        // Shuffle the deck after all rounds are done
        deck.shuffle(100);
    }
    // helper method to compare the card ranks
    public static int compareCards(Card card1, Card card2){
        int rank1 = card1.getRankOrdinal();
        int rank2 = card2.getRankOrdinal();

        return Integer.compare(rank1, rank2);
    }
    // rage quitting the game after consecutively losing for three time
    public static void rage_quit() {
        System.out.println("\nPlayer 1 has rage quit the game after losing 3 times in a row. Game over.");
        System.exit(0); // Terminate the program
    }


    public static void main(String[] args) {

        // create a deck (in order)
        LinkedList deck = initialize_deck();
        deck.print();
        deck.sanity_check(); // because we can all use one

        // shuffle the deck (random order)
        deck.shuffle(512);
        deck.print();
        deck.sanity_check(); // because we can all use one

        // cards for player 1 (hand)
        LinkedList player1 = new LinkedList();
        // cards for player 2 (hand)
        LinkedList computer = new LinkedList();

        int num_cards_dealt = 5;
        for (int i = 0; i < num_cards_dealt; i++) {
            // player removes a card from the deck and adds to their hand
            player1.add_at_tail(deck.remove_from_head());
            computer.add_at_tail(deck.remove_from_head());
        }

        // let the games begin!
        play_blind_mans_bluff(player1, computer, deck);
    }
}
