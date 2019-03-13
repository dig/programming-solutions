/**
 * @author Joseph.
 * https://open.kattis.com/problems/different
 */

import java.math.BigInteger;
import java.util.Scanner;

public class ADifferentProblem {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (sc.hasNext()) {
            BigInteger firstNum = sc.nextBigInteger();
            BigInteger secondNum = sc.nextBigInteger();

            if (firstNum.compareTo(secondNum) >= 0) {
                System.out.println(firstNum.subtract(secondNum));
            } else {
                System.out.println(secondNum.subtract(firstNum));
            }
        }

        sc.close();
    }

}