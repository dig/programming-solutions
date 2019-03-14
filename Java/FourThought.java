/**
 * @author Joseph.
 * https://open.kattis.com/problems/4thought
 */

import java.util.*;
import java.util.List;

enum Operation {

    DIVIDE ('/'),
    MULTIPLY ('*'),
    ADD ('+'),
    SUBTRACT ('-');

    private char symbol;
    Operation(char symbol) {
        this.symbol = symbol;
    }

    @Override
    public String toString() {
        return String.valueOf(this.symbol);
    }

    public static Operation get(int index) {
        return Operation.values()[index];
    }

}

class Combination {

    private List<Operation> operations;
    public Combination(List<Operation> operations) {
        this.operations = operations;
    }

    public int compute() {
        int result = 0;

        String line = "4";
        for (Operation operation : this.operations) {
            line += operation.toString() + "4";
        }

        try {
            result = ((Double) ExpressionEvaluator.evaluate(line)).intValue();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    @Override
    public String toString() {
        String result = "4";

        for (Operation operation : this.operations) {
            result += " " + operation.toString() + " 4";
        }

        result += " = " + this.compute();
        return result;
    }

}

public class FourThought {

    public static void main(String[] args) {
        HashMap<Integer, Combination> combinationHashMap = new HashMap<>();

        //--- Build all possible combinations.
        for (int i = 0; i < Operation.values().length; i++) {
            for (int j = 0; j < Operation.values().length; j++) {
                for (int k = 0; k < Operation.values().length; k++) {
                    Combination combination = new Combination(
                            Arrays.asList(Operation.get(i), Operation.get(j), Operation.get(k))
                    );

                    combinationHashMap.put(combination.compute(), combination);
                }
            }
        }

        Scanner sc = new Scanner(System.in);

        while (sc.hasNext()) {
            int target = sc.nextInt();

            if (combinationHashMap.containsKey(target)) {
                System.out.println(combinationHashMap.get(target).toString());
            } else {
                System.out.println("no solution");
            }
        }

        sc.close();
    }

}

class ExpressionEvaluator {

    private static final char MULTIPLICATION_OPERATOR = '*';
    private static final char DIVISION_OPERATOR = '/';
    private static final char ADDITION_OPERATOR = '+';
    private static final char SUBTRACTION_OPERATOR = '-';
    private static final char DECIMAL_CHAR = '.';
    private static final char LOWER_NUM = '0';
    private static final char UPPER_NUM = '9';

    private final String expression;
    private int index = -1;
    private int character;

    public ExpressionEvaluator(String expression) {
        this.expression = expression;
    }

    public static double evaluate(String expression) throws Exception {
        return new ExpressionEvaluator(expression).parse();
    }

    private void nextChar() {
        character = (++index < expression.length()) ? expression.charAt(index) : -1;
    }

    private boolean eat(int charToEat) {
        while (character == ' ') {
            nextChar();
        }
        if (character == charToEat) {
            nextChar();
            return true;
        }
        return false;
    }

    private double parse() throws Exception {
        nextChar();
        double x = parseExpression();
        if (index < expression.length()) {
            throw new Exception();
        }
        return x;
    }

    private double parseExpression() throws Exception {
        double x = parseTerm();
        while (true) {
            if (eat(ADDITION_OPERATOR)) {
                x += parseTerm();
            } else if (eat(SUBTRACTION_OPERATOR)) {
                x -= parseTerm();
            } else {
                return x;
            }
        }
    }

    private double parseTerm() throws Exception {
        double x = parseFactor();
        while (true) {
            if (eat(MULTIPLICATION_OPERATOR)) {
                x *= parseFactor();
            } else if (eat(DIVISION_OPERATOR)) {
                x /= parseFactor();
            } else {
                return x;
            }
        }
    }

    private double parseFactor() throws Exception {
        if (eat(ADDITION_OPERATOR)) {
            return parseFactor();
        }
        if (eat(SUBTRACTION_OPERATOR)) {
            return -parseFactor();
        }
        double x;
        int startPos = index;
        if ((character >= LOWER_NUM && character <= UPPER_NUM) || character == DECIMAL_CHAR) {
            while ((character >= LOWER_NUM && character <= UPPER_NUM) || character == DECIMAL_CHAR) nextChar();
            x = Double.parseDouble(expression.substring(startPos, this.index));
        } else {
            throw new Exception();
        }
        return x;
    }
}