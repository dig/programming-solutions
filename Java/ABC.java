/**
 * @author Joseph.
 * https://open.kattis.com/problems/abc
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class ABC {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int[] numbers = new int[3];
        for (int i = 0; i < numbers.length; i++)
            numbers[i] = sc.nextInt();
        Arrays.sort(numbers);

        List<Integer> output = new ArrayList<>();

        String order = sc.next();
        for (char cur : order.toCharArray()) {
            output.add(numbers[((int) cur) - 65]);
        }

        System.out.println(
                output.stream()
                        .map(n -> n.toString())
                        .collect(Collectors.joining(" "))
        );

        sc.close();
    }
}