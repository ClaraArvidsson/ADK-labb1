import java.util.Arrays;
import java.util.Scanner;
import java.util.Stack;

public class Main {
    static boolean exit = false;

    public static void interpretUserInput(String[] tokenSplit, Stack<Tree> treeStack) {
        int numTokens = tokenSplit.length;
        // System.out.println(Arrays.toString(tokenSplit));
        // System.out.println(numTokens);

        switch (numTokens) {
            case 0:
                // System.out.println("No command entered");
                break;
            case 1:
                interpretSingleInputCommand(tokenSplit, treeStack);
                break;
            case 2:
                interpretDualInputCommand(tokenSplit, treeStack);
                break;
            case 3:
                interpretTripleInputCommand(tokenSplit, treeStack);
                break;
            default:
                // System.out.println("Command length too long");
                break;
        }
    }

    private static void interpretSingleInputCommand(String[] tokenSplit, Stack<Tree> treeStack) {
        String command = tokenSplit[0].toLowerCase();

        switch (command) {
            // case "exit":
            // System.out.println("Program exited");
            // exit = true;
            // break;
            case "unset":
                if (treeStack.size() > 1)
                    treeStack.pop();
                break;
            default:
                // System.out.println("Not a valid command");
                break;
        }
    }

    private static void interpretDualInputCommand(String[] tokenSplit, Stack<Tree> treeStack) {
        String commandType = tokenSplit[0].toLowerCase();

        switch (commandType) {
            case "get":
                try {
                    int i = Integer.parseInt(tokenSplit[1]);
                    System.out.println(Tree.get(treeStack.peek(), i));
                } catch (IllegalArgumentException e) {
                    // System.out.println("Not a valid command");
                }
                break;
            default:
                // System.out.println("Not a valid command");
                break;
        }
    }

    private static void interpretTripleInputCommand(String[] tokenSplit, Stack<Tree> treeStack) {
        String commandType = tokenSplit[0].toLowerCase();

        switch (commandType) {
            case "set":
                try {
                    int i = Integer.parseInt(tokenSplit[1]);
                    int value = Integer.parseInt(tokenSplit[2]);
                    Tree tree = Tree.set(treeStack.peek(), i, value);
                    treeStack.push(tree);
                } catch (IllegalArgumentException e) {
                    // System.out.println("Not a valid command");
                }
                break;
            case "maxininterval":
                try {
                    int left = Integer.parseInt(tokenSplit[1]);
                    int right = Integer.parseInt(tokenSplit[2]);
                    System.out.println(Tree.maxininterval(treeStack.peek(), left, right));
                } catch (IllegalArgumentException e) {
                    // System.out.println("Not a valid command");
                }
                break;
            default:
                // System.out.println("Not a valid command");
                break;
        }
    }

    public static void main(String[] args) {
        Stack<Tree> treeStack = new Stack<>();

        Scanner scanner = new Scanner(System.in);
        // System.out.println("Commands:\n" +
        // "1. set <index> <value> - Set the value at the specified index.\n" +
        // "2. unset - Undo the last set operation.\n" +
        // "3. get <index> - Get the value at the specified index.\n" +
        // "4. maxininterval <left> <right> - Get the maximum value in the specified
        // interval.\n" +
        // "5. exit - Exit the program.");

        while (scanner.hasNext()) {
            if (treeStack.empty()) {
                Tree tree = new Tree();
                treeStack.push(tree);
            }
            String userInput = scanner.nextLine();
            String[] tokenSplit = userInput.split(" ");

            // System.out.print("\033[1A\033[2K");

            interpretUserInput(tokenSplit, treeStack);
            if (exit == true)
                break;
        }
        scanner.close();
    }

}
