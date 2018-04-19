import java.util.*;

public class Ticket {

    static final int TICKET_LENGTH = 4;
    private ArrayList<Integer> numbers = new ArrayList<Integer>();

    boolean filled = false;

    public void add(int input) {
        //if the number we can add a valid number
        if (!filled && input >=1 && input <= 40 && !numbers.contains(input)) {
            numbers.add(input);
            //printNumbers();
        }

        if (numbers.size() == TICKET_LENGTH) {
            filled = true;
        }
    }

    public boolean isFilled() {
        return this.filled;
    }

    public void printNumbers() {
        System.out.println();
        for (int i = 0; i < numbers.size(); i++) {
			System.out.print(numbers.get(i) + " ");
		}
        System.out.println();
        //System.out.println("Not filled: " + notFilled());
        System.out.println();
        System.out.println("Size: " + numbers.size());
        System.out.println();

    }

    public ArrayList getNumbers() {
        return this.numbers;
    }

    public boolean contains(int num) {
        return numbers.contains(num);
    }

    //Warnings are suppressed for this section of code because we already know that ticketToMatch.getNumbers() will return an ArrayList of Integers.
    @SuppressWarnings("unchecked")
    public int match(Ticket ticketToMatch) {

        System.out.println("In Matches!");
        int numberOfMatches = 0;

        ArrayList<Integer> otherNumbers = ticketToMatch.getNumbers();

        //numbers.size() is equaling zero here!
        System.out.println("numbers.size() = " + numbers.size());

        for (int i = 0; i < numbers.size(); i++) {
            if (otherNumbers.contains(numbers.get(i))) {
                numberOfMatches++;
            }
        }

        System.out.println("Number of Matches: " + numberOfMatches);
        return numberOfMatches;
    }
}
