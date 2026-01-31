import java.util.Scanner;

public class Lego {
    public static void main(String[] args) {
        String line;
        Scanner in = new Scanner (System.in);
        Boolean isRunning = true;

        String openingText = " Hello! I'm [Lego]\n"
                + " What can I do for you?\n"
                + " Type something and I may just copy you! :D";
        String closingText = " Bye. Hope to see you again soon!";

        System.out.println(openingText);
        while (isRunning) {
            line = in.nextLine();
            if (line.toLowerCase().equals("bye")) {
                isRunning = false;
                System.out.println(closingText);
            } else {
                System.out.println(line);
            }
        }
        in.close();
    }
}
