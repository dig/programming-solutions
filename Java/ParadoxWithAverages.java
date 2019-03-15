/**
 * @author Joseph.
 * https://open.kattis.com/problems/averageseasy
 */
 
import java.util.*;

public class ParadoxWithAverages {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int amountOfCases = sc.nextInt();
        sc.nextLine();

        for (int i = 0; i < amountOfCases; i++) {
            int numberOfCS = sc.nextInt();
            int numberOfEcon = sc.nextInt();

            List<Integer> csIQ = new LinkedList<>();
            List<Integer> econIQ = new LinkedList<>();

            //--- CS students IQ input
            for (int k = 0; k < numberOfCS; k++) {
                csIQ.add(sc.nextInt());
            }

            //--- Economy students IQ input
            for (int k = 0; k < numberOfEcon; k++) {
                econIQ.add(sc.nextInt());
            }

            int result = 0;
            double originalCSAverage = calculateAverage(csIQ);
            double originalEconAverage = calculateAverage(econIQ);

            for (int k = 0; k < csIQ.size(); k++) {
                int test = csIQ.get(k);

                List<Integer> cloned = new LinkedList<>(csIQ);
                cloned.remove(k);
                econIQ.add(test);

                double csAverage = calculateAverage(cloned);
                double econAverage = calculateAverage(econIQ);

                if (csAverage > originalCSAverage && econAverage > originalEconAverage) {
                    result++;
                }

                econIQ.remove(econIQ.size() - 1);

            }

            System.out.println(result);
        }

        sc.close();
    }

    public static double calculateAverage(List<Integer> numbers) {
        if (numbers.size() == 0) { return 0; }

        int sum = 0;
        for (Integer i : numbers) {
            sum += i;
        }

        return (double) sum / numbers.size();
    }

}