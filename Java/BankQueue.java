/**
 * @author Joseph.
 * https://open.kattis.com/problems/bank
 */

import java.util.*;

class Person {

    private int cash;
    private int time;
    private boolean served;

    public Person(int cash, int time) {
        this.cash = cash;
        this.time = time;
        this.served = false;
    }

    public int getCash() {
        return this.cash;
    }

    public int getTime() {
        return this.time;
    }

    public boolean isServed() {
        return this.served;
    }

    public void setServed(boolean served) {
        this.served = served;
    }

    @Override
    public String toString() {
        return "[cash=" + this.cash + ", time=" + this.time + ", served=" + this.served + "]";
    }

}

public class BankQueue {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int amountInQueue = sc.nextInt();
        int timeUntilClose = sc.nextInt();

        List<Person> peopleInQueue = new ArrayList<Person>();
        for (int i = 0; i < amountInQueue; i++) {
            peopleInQueue.add(new Person(sc.nextInt(), sc.nextInt()));
        }

        int result = 0;
        for (int i = timeUntilClose; i >= 0; i--) {
            List<Person> peopleLeft = new ArrayList<Person>();

            for (Person person : peopleInQueue) {
                if (person.getTime() >= i && !person.isServed()) {
                    peopleLeft.add(person);
                }
            }

            if (peopleLeft.size() > 0) {
                //--- Sort based on time, if same then sort by cash
                Collections.sort(peopleLeft, new Comparator<Person>() {
                    @Override
                    public int compare(Person o1, Person o2) {
                        return o2.getCash() - o1.getCash();
                    }
                });

                Person person = peopleLeft.get(0);
                person.setServed(true);
                result += person.getCash();
            }
        }

        System.out.print(result);

        sc.close();
    }
}