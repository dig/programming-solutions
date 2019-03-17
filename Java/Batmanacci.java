/**
 * @author Joseph.
 * https://open.kattis.com/problems/batmanacci
 */

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

class Sequence {

    private char s1;
    private char s2;

    public Sequence(char s1, char s2) {
        this.s1 = s1;
        this.s2 = s2;
    }

    public String get(int index) {
        List<String> sequence = new LinkedList<>();
        sequence.add(String.valueOf(s1));
        sequence.add(String.valueOf(s2));

        //--- Loop all combinations until index
        for (int i = 2; i < index + 2; i++) {
            sequence.add(sequence.get(i - 2) + sequence.get(i - 1));
        }

        return sequence.get(sequence.size() - 1);
    }

}

public class Batmanacci {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int sequenceNum = sc.nextInt();
        int letterNum = sc.nextInt();
        sc.close();

        Sequence sequence = new Sequence('N', 'A');
        System.out.println(sequence.get(sequenceNum).charAt(letterNum - 1));
    }

}