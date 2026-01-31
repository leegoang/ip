import java.util.Scanner;
import java.util.Arrays;

public class Lego {
    public static void main(String[] args) {
        String line;
        Scanner in = new Scanner(System.in);
        Boolean isRunning = true;
        String[] wordList = new String[100];
        Boolean[] isCheckedList = new Boolean[100];
        Arrays.fill(isCheckedList, false);
        int wordNum = 0;

        String openingText = " Hello! I'm [Lego]\n"
                + " What can I do for you?\n"
                + " Ready to go shopping? Because I am! :D"
                + " Type something and I will record it for you!";
        String closingText = " Bye. Hope to see you again soon!";

        System.out.println(openingText);
        while (isRunning) {
            line = in.nextLine();
            String[] splitCommand = line.split(" ");
            String command = splitCommand[0];
            if (command.toLowerCase().equals("bye")) {
                isRunning = false;
                System.out.println(closingText);
            } else if (command.toLowerCase().equals("list")) {
                for (int i = 0; i < wordNum; i++) {
                    System.out.print(" " + Integer.toString(i + 1) + ".");
                    printListLine(i, isCheckedList, wordList);
                }
            } else if (command.toLowerCase().equals("mark")) {
                int itemIndex = Integer.parseInt(splitCommand[1]) - 1;
                isCheckedList[itemIndex] = true;
                System.out.println("Nice! I've marked this task as done:");
                printListLine(itemIndex, isCheckedList, wordList);
            } else if (command.toLowerCase().equals("unmark")) {
                int itemIndex = Integer.parseInt(splitCommand[1]) - 1;
                isCheckedList[itemIndex] = false;
                System.out.println("OK, I've marked this task as not done yet:");
                printListLine(itemIndex, isCheckedList, wordList);
            } else {
                System.out.println(" added: " + line);
                wordList[wordNum] = line;
                wordNum++;
            }
        }
        in.close();
    }

    public static String applyTick(Boolean isChecked) {
        if (isChecked) {
            return "X";
        }
        return " ";
    }

    public static void printListLine(int num, Boolean[] isCheckedList, String[] wordList) {
        System.out.println(" [" + applyTick(isCheckedList[num]) + "] "
                + wordList[num]);
    }
}
