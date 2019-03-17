/**
 * @author Joseph.
 * https://open.kattis.com/problems/alphabet
 * WIP
 */

import java.util.*;

public class Alphabet {

    public static String CHARACTERS = "abcdefghijklmnopqrstuvwxyz";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        sc.close();

        int splitMethod = splitMethod(input);
        int gapMethod = gapMethod(input);

        int result = 0;
        if (splitMethod < gapMethod) {
            result = splitMethod;
        } else {
            result = gapMethod;
        }

        // System.out.println(result);
        System.out.println("splitMethod = " + splitMethod);
        System.out.println("gapMethod = " + gapMethod);
    }

    public static int splitMethod(String input) {
        int minimum = -1;

        char[] array = input.toCharArray();
        for (int i = 0; i < array.length; i++) {
            int set = array.length - i;
            char[] update = new char[set];

            for (int k = 0; k < array.length; k++) {
                if (k < set) {
                    update[k] = array[k];
                }
            }

            minimum = splitMethodCheck(update, minimum);
        }

        for (int i = array.length - 1; i >= 0; i--) {
            int set = array.length - i;
            char[] update = new char[set];

            for (int k = 0; k < array.length; k++) {
                if (k < set) {
                    update[k] = array[array.length - k - 1];
                }
            }

            update = new StringBuilder(new String(update)).reverse().toString().toCharArray();
            minimum = splitMethodCheck(update, minimum);
        }

        return minimum;
    }

    public static int splitMethodCheck(char[] update, int minimum) {
        if (Alphabet.CHARACTERS.contains(new String(update))) {
            int found = Alphabet.CHARACTERS.length() - update.length;
            if (minimum == -1 || found < minimum) {
                return found;
            }
        }
        return minimum;
    }

    public static int gapMethod(String input) {
        int minimum = -1;

        char[] array = input.toCharArray();

        for (int i = 0; i < array.length; i++) {
            List<Character> characterList = new LinkedList<>();

            for (int k = 0; k < array.length; k++) {
                if (k >= i) {
                    char character = array[k];
                    int index = Alphabet.CHARACTERS.indexOf(character);

                    if (characterList.size() > 0) {
                        char lastChar = characterList.get(characterList.size() - 1);
                        int lastIndex = Alphabet.CHARACTERS.indexOf(lastChar);

                        if (index > lastIndex) {
                            characterList.add(character);
                            if (k >= array.length - 1) {
                                if (minimum == -1 || (Alphabet.CHARACTERS.length() - characterList.size()) < minimum) {
                                    minimum = Alphabet.CHARACTERS.length() - characterList.size();
                                }
                            }
                        } else {
                            if (minimum == -1 || (Alphabet.CHARACTERS.length() - characterList.size()) < minimum) {
                                minimum = Alphabet.CHARACTERS.length() - characterList.size();
                            }
                            break;
                        }
                    } else {
                        characterList.add(character);
                    }
                }
            }
        }

        return minimum;
    }

}