import java.util.Scanner;

/**
 * @author Joseph.
 * https://open.kattis.com/problems/bela
 */

enum Card {

    A ('A', 11, 11),
    K ('K', 4, 4),
    Q ('Q', 3, 3),
    J ('J',20, 2),
    T ('T', 10, 10),
    NINE ('9', 14, 0),
    EIGHT ('8', 0, 0),
    SEVEN ('7', 0, 0);

    private char character;
    private int dominant;
    private int notDominant;

    Card(char character, int dominant, int notDominant) {
        this.character = character;
        this.dominant = dominant;
        this.notDominant = notDominant;
    }

    public char getCharacter() { return this.character; }

    public int getValue(boolean dominant) {
        if (dominant) { return this.dominant; }
        return this.notDominant;
    }

    public static Card getByCharacter(char character) {
        for (Card card : Card.values()) {
            if (card.getCharacter() == character) {
                return card;
            }
        }
        return null;
    }
}

public class Bela {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int hands = sc.nextInt();
        char trump = sc.next().charAt(0);
        sc.nextLine();

        int total = 0;
        for (int i = 0; i < (hands * 4); i++) {
            String line = sc.nextLine();

            Card card = Card.getByCharacter(line.charAt(0));
            char suit = line.charAt(1);

            if (suit == trump) {
                total += card.getValue(true);
            } else {
                total += card.getValue(false);
            }
        }

        System.out.println(total);
    }

}