import java.util.*;
import javax.swing.*;
import java.util.concurrent.ThreadLocalRandom;

public class Lottery {

    //Initialize all variables for the code
    static int totalMatched = 0;
    static double totalPrizeMoney = 0.0;
    static int[] timesMatched = new int[5];
    static int gamesPlayed = 0;

    static final String WELCOME_MESSAGE =
    "Welcome to the Pick 4 Lottery Program!\n" +
    "This program simulates a pick 4 lottery.\n" +
    "Choose four unique numbers and the computer will do the same.\n\n" +
    "You'll gain money based on how many numbers are the same!";

    static final String MENU = "Do you want to enter in a ticket (yes or no)?";

    static HashMap<Integer, Double> matchTable = new HashMap<Integer, Double>();

    static {
        matchTable.put(0, 0.0);
        matchTable.put(1, 100.00);
        matchTable.put(2, 1000.00);
        matchTable.put(3, 50000.00);
        matchTable.put(4, 500000.00);
    }

    //Robust code to handle getting numbers from the user.
    public static int getNumber(String message, int min, int max) {

        String input;
        Integer answer = -1;

        message += "\n\nEnter a number between " + min + " and " + max;

        do { //Show the user an input dialog with the message
            input = JOptionPane.showInputDialog(null, message);

            try { //Attempt to convert the input string into an integer
                answer = Integer.valueOf(input);
            } catch (NumberFormatException e) {
                //in the console window, print out an error message
                System.err.println("NumberFormatException: " + e.getMessage());
                //If we can't typecast to an Integer, tell the user that they can only enter numbers
                print("You didn't enter a number!");
            }

            if (answer < min || answer > max) {
                print("The number wasn't within the bounds!");
            }

        } while (answer < min || answer > max); //Repeat until the integer is within the correct bounds

        return answer;
    }

    //Generates a random integer with the specified range according to standard Java practices
    public static int getRandomIntBetween(int min, int max) {
        return ThreadLocalRandom.current().nextInt(min, max + 1);
    }

    public static double rewardMoney(Integer results) { //Rewards money and updates stats for the end of the game. Returns the money won this round of the game.
        totalMatched += results;
        timesMatched[results]++;
        gamesPlayed++;
        System.out.println(Arrays.toString(timesMatched));
        System.out.println("results " + results + " this round!");
        Double currentMoney = matchTable.get(results);
        totalPrizeMoney += currentMoney;

        System.out.println("You won " + currentMoney + " this round!");
        System.out.println("You won " + totalPrizeMoney + " overall!");

        return currentMoney;
    }

    static void print (String output) {
        JOptionPane.showMessageDialog(null, output);
    }

    static void exit() {
        //calculate and print everything
        String output = "";
        output += "*******************************\n";
        output += " Number of games played: " + gamesPlayed + "\n";
        output += "*******************************\n\n";
        for (int i = 0; i < timesMatched.length; i++) {
            output += "You matched " + i + " numbers " + timesMatched[i] + " times\n";
            output += "Which means you matched " + i + " numbers about " + Math.round(((double) timesMatched[i] / (double) gamesPlayed)*100.00) + "% of the time\n\n";
        }
        output += "You won $" + totalPrizeMoney + " overall!";

        print(output);
    }

    public static String shouldContinue(String message) {
        String answer = "";

        do { //Show the user an input dialog with the message
            answer = JOptionPane.showInputDialog(null, message).toLowerCase();

            System.out.println("answer was: " + answer);

            if (!answer.equals("yes") && !answer.equals("no")) {
                print("You didn't enter \"yes\" or \"no\"!");
            }

        } while (!answer.equals("yes") && !answer.equals("no")); //Repeat until we get a solid choice

        return answer;
    }

    public static void main(String[] args) {

        //System.out.println(matchTable);
        print(WELCOME_MESSAGE);

        String choice = "";

        do {
                choice = shouldContinue(MENU);

                if (choice.equals("no")) { //the user chooses to exit
                    exit();
                    break;
                } else if (choice.equals("yes")) { //the user chooses to play
                    Ticket userTicket = new Ticket();
                    Ticket computerTicket = new Ticket();

                    //Fill the user ticket with their inputs. Using a while loop allows for arbitrarily long tickets (ticket length changed with the TICKET_LENGTH constant in the Ticket class)
                    while (!userTicket.isFilled()) {
                        int input = getNumber("What number do you want (you can choose 4 for this ticket)?", 1, 40);
                        userTicket.add(input);
                    }


                    //Fill the computer ticket with random numbers. Makes sure they're all unique

                    while (!computerTicket.isFilled()) {
                        int input = getRandomIntBetween(1, 40);

                        if((!computerTicket.contains(input))) {
                            computerTicket.add(input);
                            System.out.println("Random Number added: " + input);
                        }
                    }

                    //used to test comparison code
                    /*
                    computerTicket.add(1);
                    computerTicket.add(2);
                    computerTicket.add(3);
                    computerTicket.add(4);
                    */

                    Integer results = computerTicket.match(userTicket);
                    rewardMoney(results);
                }

        } while (choice.equals("yes"));
    }
}
