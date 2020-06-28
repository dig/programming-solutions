/**
 * @author Joseph.
 * https://open.kattis.com/problems/addingwords
 */

import java.util.*;

enum Operation {

    DIVIDE ('/'),
    MULTIPLY ('*'),
    ADD ('+'),
    SUBTRACT ('-');

    private char symbol;
    Operation(char symbol) {
        this.symbol = symbol;
    }

    public char getSymbol() {
        return this.symbol;
    }

    public int calculate(int firstNum, int secondNum) {
        switch (this) {
            case DIVIDE:
                return firstNum / secondNum;
            case MULTIPLY:
                return firstNum * secondNum;
            case ADD:
                return firstNum + secondNum;
            case SUBTRACT:
                return firstNum - secondNum;
        }

        return firstNum;
    }

    @Override
    public String toString() {
        return String.valueOf(this.symbol);
    }

    public static Operation get(int index) {
        return Operation.values()[index];
    }

}

enum Command {

    DEFINITION ("def"),
    CALCULATION ("calc"),
    CLEAR ("clear"),
    VALUE ("value");

    private String cmd;
    Command (String cmd) {
        this.cmd = cmd;
    }

    public String getCmd() {
        return this.cmd;
    }

    public static Command getByCmd(String commandLine) {
        for (Command cmd : Command.values()) {
            if (commandLine.startsWith(cmd.getCmd())) {
                return cmd;
            }
        }

        return null;
    }

}

public class AddingWords {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        HashMap<String, Integer> variables = new HashMap<>();

        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            Command command = Command.getByCmd(line);

            switch (command) {
                case DEFINITION:
                    String[] split = line.substring(command.getCmd().length() + 1).split(" ");
                    variables.put(split[0], Integer.parseInt(split[1]));
                    break;
                case CALCULATION:
                    String parseLine = line.substring(command.getCmd().length() + 1, line.length() - 2);
                    parseLine = parseLine.replaceAll("\\s+","");

                    Queue<String> variableOrder = new LinkedList<>();
                    Queue<Operation> operationOrder = new LinkedList<>();

                    // Find all variables
                    for (String var : parseLine.split("\\+|-|\\*|\\/")) {
                        variableOrder.add(var);
                    }

                    // Find all operations
                    for (char character : parseLine.toCharArray()) {
                        for (Operation operation : Operation.values()) {
                            if (operation.getSymbol() == character) {
                                operationOrder.add(operation);
                            }
                        }
                    }

                    boolean exists = true;
                    int result = 0;

                    String firstVariable = variableOrder.poll();
                    if (variables.containsKey(firstVariable)) {
                        result = variables.get(firstVariable);
                    } else {
                        exists = false;
                    }

                    while (!variableOrder.isEmpty() && !operationOrder.isEmpty()) {
                        String var = variableOrder.poll();
                        Operation operation = operationOrder.poll();

                        if (variables.containsKey(var)) {
                            result = operation.calculate(result, variables.get(var));
                        } else {
                            exists = false;
                        }
                    }

                    String output = "unknown";
                    if (exists && variables.values().contains(result)) {
                        for (String var : variables.keySet()) {
                            int value = variables.get(var);
                            if (value == result) {
                                output = var;
                            }
                        }
                    }

                    System.out.println(line.substring(command.getCmd().length() + 1) + " " + (exists ? output : "unknown"));
                    break;
                case VALUE:
                    String remaining = line.substring(command.getCmd().length() + 1);
                    if (variables.containsKey(remaining)) {
                        System.out.println(variables.get(remaining));
                    } else {
                        System.out.println("unknown");
                    }
                    break;
                case CLEAR:
                default:
                    variables.clear();
                    break;
            }
        }

        sc.close();
    }

}