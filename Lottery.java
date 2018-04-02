import java.util.*;
import javax.swing.*;
import java.util.concurrent.ThreadLocalRandom;

public class Lottery {

    //Initialize all variables for the code
    static int totalMatched = 0;
    static int matched = 0;
    static double totalPrizeMoney = 0.0;
    static double prizeMoney = 0.0;

    //Robust code to handle getting numbers from the user.
    public static int getNumber(String message, int min, int max) {



        String input;
        Integer answer = -1;

        message += "\nEnter a number between " + min + " and " + max;

        do { //Show the user an input dialog with the message
            input = JOptionPane.showInputDialog(null, message);

            try { //Attempt to convert the input string into an integer
                answer = Integer.valueOf(input);
            } catch (NumberFormatException e) {
                //in the console window, print out an error message
                System.err.println("NumberFormatException: " + e.getMessage());
                //If we can't typecast to an Integer, tell the user that they can only enter numbers
                JOptionPane.showMessageDialog(null, "You didn't enter a number!");
            }
        } while (answer < min || answer > max); //Repeat until the integer is within the correct bounds

        return answer;
    }

    //Generates a random integer with the specified range according to standard Java practices
    public static int getRandomIntBetween(int min, int max) {
        return ThreadLocalRandom.current().nextInt(min, max + 1);
    }

    public static void main(String[] args) {



        Ticket userTicket = new Ticket();
        Ticket computerTicket = new Ticket();

        //Fill the user ticket with their inputs. Using a while loop allows for arbitrarily long tickets (ticket length changed with the TICKET_LENGTH constant in the Ticket class)
        while (!userTicket.isFilled()) {
            int input = getNumber("Enter in your choice", 1, 40);
            System.out.println("user input" + input);
            userTicket.add(input);
        }

        //Fill the computer ticket with random numbers.
        while (!computerTicket.isFilled()) {
            int input = getRandomIntBetween(1, 40);
            System.out.println("Random Number: " + input);
            computerTicket.add(input);
        }

        //used to test comparison code
        /*
        computerTicket.add(1);
        computerTicket.add(2);
        computerTicket.add(3);
        computerTicket.add(4);
        */

    }
}
