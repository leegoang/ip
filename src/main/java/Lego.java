import java.util.Scanner;

public class Lego {
    public static void main(String[] args) {
        String line;
        Scanner in = new Scanner (System.in);
        Boolean isRunning = true;
        String[] wordList = new String[100];
        int wordNum = 0;

        String openingText = " Hello! I'm [Lego]\n"
                + " What can I do for you?\n"
                + " Type something and I will record it for you! :D";
        String closingText = " Bye. Hope to see you again soon!";

        System.out.println(openingText);
        while (isRunning) {
            line = in.nextLine();
            if (line.toLowerCase().equals("bye")) {
                isRunning = false;
                System.out.println(closingText);
            } 
            else if (line.toLowerCase().equals("list")) {
                for (int i = 0; i < wordNum; i++) {
                    System.out.println(" " + Integer.toString(i + 1) + ". " + wordList[i]);
                }
            } else {
                System.out.println(" added: " + line);
                wordList[wordNum] = line;
                wordNum++;
            }
        }
        in.close();
    }
}
