/**
 * @author Joseph.
 * https://open.kattis.com/problems/batmanacci
 */

import java.util.Scanner;

class Sequence {

    private char s1;
    private char s2;

    public Sequence(char s1, char s2) {
        this.s1 = s1;
        this.s2 = s2;
    }

    public char get(int index, int letter) {
        int[] seq = new int[index];
        seq[0] = 0;
        seq[1] = 1;
        seq[2] = 1;

        for (int i = 3; i < index; i++) {
            seq[i] = seq[i - 1] + seq[i - 2];
        }

        while (index > 2) {
            if (letter > seq[index - 2]) {
                letter -= seq[index - 2];
                index -= 1;
            } else {
                index -= 2;
            }
        }

        if (index == 1) {
            return s1;
        }

        return s2;
    }

}

public class Batmanacci {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int sequenceNum = sc.nextInt();
        int letterNum = sc.nextInt();
        sc.close();

        Sequence sequence = new Sequence('N', 'A');
        System.out.println(sequence.get(sequenceNum, letterNum));
    }

}