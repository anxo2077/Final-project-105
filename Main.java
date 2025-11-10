
/* 
 PC Adventure
 Purpose: Choose-your-own-adventure with methods and modularity.
 Input: Names, city, year, age, PC price, money saved, savings per month, yes/no decision.
 Output: Monthly progress and a final message when the balance reaches zero.
*/
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scnr = new Scanner(System.in);

        // Collect user data
        String narratorName = getUserName(scnr, "your");
        String friendName = getUserName(scnr, "your friend's");
        String friendPronoun = getInput(scnr, "Enter your friend's pronoun: ");
        String city = getInput(scnr, "Enter the name of the city where the story takes place: ");
        String storeName = getInput(scnr, "Enter the name of the computer store: ");

        int year = getValidInt(scnr, "Year in which the story took place (numbers only, e.g., 2015): ");
        int ageThen = getValidInt(scnr, "Enter the age you were that year (numbers only, e.g., 15): ");

        double pcPrice = getValidDouble(scnr, "Enter the price of the computer (numbers only, e.g., 1198.56): ");
        double moneySaved = getValidDouble(scnr, "Amount of money saved (numbers only, e.g., 560.56): ");
        double savingsPerMonth = getValidDouble(scnr,
                "Amount of money you can save monthly today (numbers only, e.g., 300.00): ");

        // Define options
        String[] jobOptions = { "Weekend cashier at local store", "Pizza delivery on Saturdays",
                "Library assistant after school" };
        double[] financeOffers = { 0.10, 0.15, 0.08 };

        // Print prologue
        printPrologue(narratorName, friendName, friendPronoun, city, storeName, year, ageThen, pcPrice, moneySaved,
                savingsPerMonth);

        // Get user decision
        boolean finance = getFinanceDecision(scnr, friendName);

        // Calculate money left
        double moneyLeft = calculateBalance(pcPrice, moneySaved, finance, financeOffers);

        // Print Chapter 1
        printChapter1(narratorName, friendName, finance, jobOptions, financeOffers, moneyLeft);

        // Adjust savings if working
        if (!finance) {
            savingsPerMonth += 160;
        }

        // Simulate monthly payments
        int months = simulateMonthlyPayments(moneyLeft, savingsPerMonth);

        System.out.println("After " + months + " month(s), they were finally ready to get the computer.");

        scnr.close();
    }

    // Method: Get user name with validation
    public static String getUserName(Scanner scnr, String whose) {
        System.out.print("Enter " + whose + " first name: ");
        return scnr.next();
    }

    // Method: Get generic string input
    public static String getInput(Scanner scnr, String prompt) {
        System.out.print(prompt);
        return scnr.next();
    }

    // Method: Get valid integer with input validation
    public static int getValidInt(Scanner scnr, String prompt) {
        System.out.print(prompt);
        while (!scnr.hasNextInt()) {
            System.out.println("Invalid input. Please enter a number.");
            System.out.print(prompt);
            scnr.next();
        }
        return scnr.nextInt();
    }

    // Method: Get valid double with input validation
    public static double getValidDouble(Scanner scnr, String prompt) {
        System.out.print(prompt);
        while (!scnr.hasNextDouble()) {
            System.out.println("Invalid input. Please enter a number.");
            System.out.print(prompt);
            scnr.next();
        }
        return scnr.nextDouble();
    }

    // Method: Print prologue section
    public static void printPrologue(String narratorName, String friendName, String friendPronoun,
            String city, String storeName, int year, int ageThen,
            double pcPrice, double moneySaved, double savingsPerMonth) {
        double moneyLeft = pcPrice - moneySaved;

        System.out.println();
        System.out.println("PROLOGUE");
        System.out.println("---------");
        System.out.println(narratorName + " remembers the year " + year + " in " + city + ", at age " + ageThen + ".");
        System.out.println("Back then, " + narratorName + " and " + friendName + " (" + friendPronoun
                + ") dreamed of buying a computer at " + storeName + ".");
        System.out.println("The PC cost $" + String.format("%.2f", pcPrice) + ", while savings were $"
                + String.format("%.2f", moneySaved) + ".");
        System.out.println();
        System.out.println("Saving $" + String.format("%.2f", savingsPerMonth) + " per month became the plan.");
        System.out.println("The amount still missing was $" + String.format("%.2f", moneyLeft) + ".");
        System.out.println("That is where this story begins.");
        System.out.println();
    }

    // Method: Get finance decision with input handling (yes/no validation)
    public static boolean getFinanceDecision(Scanner scnr, String friendName) {
        String choice;
        while (true) {
            System.out.print(
                    friendName + " wanted to work part-time to help pay for the computer. (Type 'yes' or 'no'): ");
            choice = scnr.next().toLowerCase();

            if (choice.equals("yes")) {
                return false; // No finance
            } else if (choice.equals("no")) {
                return true; // Finance
            } else {
                System.out.println("Invalid response. Please answer 'yes' or 'no'.");
            }
        }
    }

    // Method: Calculate balance with interest if financing (works with array)
    public static double calculateBalance(double pcPrice, double moneySaved, boolean finance, double[] financeOffers) {
        double moneyLeft = pcPrice - moneySaved;

        if (finance) {
            int chosenOffer = (int) (Math.random() * financeOffers.length);
            moneyLeft = moneyLeft + (moneyLeft * financeOffers[chosenOffer]);
        }

        return moneyLeft;
    }

    // Overloaded method: Calculate balance without finance options
    public static double calculateBalance(double pcPrice, double moneySaved) {
        return pcPrice - moneySaved;
    }

    // Method: Print Chapter 1 with branching logic
    public static void printChapter1(String narratorName, String friendName, boolean finance,
            String[] jobOptions, double[] financeOffers, double moneyLeft) {
        System.out.println("CHAPTER 1");
        System.out.println("---------");
        System.out.println("A story that was not easy at all, because " + narratorName + " and " + friendName
                + " had to figure out how to get the money.");
        System.out.println("After much thought, " + narratorName + " suggested to " + friendName
                + " that they get a part-time job to get money faster and not just to rely on savings.");

        if (finance) {
            System.out.println(friendName
                    + "'s answer was: did not agree with the idea and instead proposed financing at the store.");
            System.out.println();
            printFinanceOffers(financeOffers, moneyLeft);
        } else {
            System.out.println(friendName + "'s answer was: agreed with the idea of working.");
            System.out.println();
            printJobOptions(jobOptions);
        }
    }

    // Method: Print job options (works with array)
    public static void printJobOptions(String[] jobOptions) {
        System.out.println("They decided to send a few applications:");
        for (int i = 0; i < jobOptions.length; i++) {
            System.out.println("  Job application #" + (i + 1) + ": " + jobOptions[i]);
        }
        System.out.println("One reply looked promising for weekends.");
    }

    // Method: Print finance offers (works with array)
    public static void printFinanceOffers(double[] financeOffers, double moneyLeft) {
        int chosenOffer = (int) (Math.random() * financeOffers.length);

        System.out.println("They compared several store offers before choosing a plan:");
        for (int i = 0; i < financeOffers.length; i++) {
            System.out.println("  Store offer #" + (i + 1) + " - Interest rate: " + (financeOffers[i] * 100) + "%");
        }
        System.out.println("They chose store offer #" + (chosenOffer + 1) + " with "
                + (financeOffers[chosenOffer] * 100) + "% interest.");
        System.out.println("The new amount to pay is $" + String.format("%.2f", moneyLeft) + ".");
    }

    // Method: Simulate monthly payments and return number of months (returns value)
    public static int simulateMonthlyPayments(double moneyLeft, double savingsPerMonth) {
        int months = 0;

        while (moneyLeft > 0) {
            months++;
            moneyLeft -= savingsPerMonth;
            if (moneyLeft < 0) {
                moneyLeft = 0;
            }
            System.out.println("  Month #" + months + " they saved $" + savingsPerMonth + ". Remaining: $"
                    + String.format("%.2f", moneyLeft) + ".");
        }

        return months;
    }
}