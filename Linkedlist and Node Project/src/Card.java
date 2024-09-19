// Standard French-style cards
public class Card {

    // Suites
    public enum suites {
        NULL, SPADES, CLUBS, DIAMONDS, HEARTS
    }

    // Ranks
    public enum ranks {
        NULL, two, three, four, five, six, seven, eight, nine, ten, jack, king, queen, ace
    }

    private suites suit;
    private ranks rank;

    Card(){
        suit = suites.NULL;
        rank = ranks.NULL;
    }

    Card(suites s, ranks r){
        suit = s;
        rank = r;
    }
    // getter method to return the ordinal value of the rank
    public int getRankOrdinal() {
        return this.rank.ordinal();
    }

    public void print_card(){
        System.out.print(suit + ": " + rank);
    }

}
