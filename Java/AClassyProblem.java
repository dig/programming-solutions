/**
 * @author Joseph.
 * https://open.kattis.com/problems/classy
 */

import java.util.*;

enum Class {

    LOWER,
    MIDDLE,
    UPPER;

    public int getWorth() {
        return this.ordinal();
    }

}

class Person {

    private String name;
    private List<Class> classes;

    public Person(String name, String classString) {
        this.name = name;
        this.classes = new ArrayList<>();
        this.parse(classString);
    }

    private void parse(String classString) {
        //--- Remove last 6 characters - " class"
        classString = classString.substring(0, classString.length() - 6);

        //--- Split classes
        String[] split = classString.split("-");
        for (String clazz : split) {
            this.classes.add(Class.valueOf(clazz.toUpperCase()));
        }
    }

    public String getName() {
        return this.name;
    }

    public int getClassRating() {
        char[] output = new char[12];
        Arrays.fill(output, Character.forDigit(1,10));

        for (int i = 12 - classes.size(); i < output.length; i++)
            output[i] = Character.forDigit(classes.get(i - (12 - classes.size())).getWorth(),10);

        String finalOutput = new StringBuilder(new String(output)).reverse().toString();
        return Integer.parseInt(finalOutput, 3);
    }
}

public class AClassyProblem {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        //--- Loop total cases
        int totalCases = sc.nextInt();
        for (int i = 0; i < totalCases; i++) {
            int numOfPeople = sc.nextInt();
            sc.nextLine();

            //--- Loop num of people in the case
            List<Person> people = new ArrayList<>();
            for (int k = 0; k < numOfPeople; k++) {
                String[] split = sc.nextLine().split(": ");
                people.add(new Person(split[0], split[1]));
            }

            //--- Sort list
            Collections.sort(people, new Comparator<Person>() {
                @Override
                public int compare(Person o1, Person o2) {
                    int o1Rating = o1.getClassRating();
                    int o2Rating = o2.getClassRating();

                    //--- If same class rating.
                    if (o1Rating == o2Rating) {
                        return o1.getName().compareTo(o2.getName());
                    }

                    return o2Rating - o1Rating;
                }
            });

            //--- Output results
            for (Person person : people) {
                System.out.println(person.getName());
            }
            System.out.println("==============================");
        }

        sc.close();
    }

}