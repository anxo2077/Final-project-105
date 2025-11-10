import java.util.Scanner;

public class PCAdventureGame {

    private final Scanner scnr = new Scanner(System.in);

    // Collect user data
    private String narratorName;
    private String friendName;
    private String friendPronoun;
    private String city;
    private String storeName;

    private int year;
    private int ageThen;

    private double pcPrice;
    private double moneySaved;
    private double savingsPerMonth;
    private boolean finance; // true = use financing, false = work for it/save
    private double moneyLeft;

    // Job options
    private final String[] jobOptions = {
            "Weekend cashier at a local electronics store (+$160/month).",
            "Pizza delivery driver on Friday nights (+$160/month).",
            "Library assistant and PC setup helper (+$160/month)."
    };

    // Financing offers (interest rates)
    private final double[] financeOffers = { 0.10, 0.15, 0.08 };

    public void run() {
        collectUserData();
        printPrologue();

        finance = getFinanceDecision();

        if (finance) {
            moneyLeft = calculateBalance(pcPrice, moneySaved, true, financeOffers);
            printFinancePathIntro(moneyLeft);
        } else {
            moneyLeft = calculateBalance(pcPrice, moneySaved);
            printWorkPathIntro(moneyLeft);
            // Reward for working: increase savings capacity
            savingsPerMonth += 160.0;
        }

        if (moneyLeft <= 0) {
            printInstantPurchaseChapter();
        } else {
            int months = simulateMonthlyPayments(moneyLeft, savingsPerMonth);
            printChapter2(months);
        }

        System.out.println();
        System.out.println("Thank you for playing PC Adventure!");
        scnr.close();
    }

    // Data Input Methods

    private void collectUserData() {
        System.out.println("=== PC ADVENTURE - INPUT SETUP ===");

        narratorName = getUserName("your");
        friendName = getUserName("your friend's");

        System.out.print("Enter your friend's pronoun (he/she/they): ");
        friendPronoun = scnr.next();

        System.out.print("Enter the name of the city where the story takes place: ");
        city = scnr.next();

        System.out.print("Enter the name of the computer store: ");
        storeName = scnr.next();

        year = getValidInt("Year in which the story took place (e.g., 2015): ");
        ageThen = getValidInt("Enter the age you were that year (e.g., 15): ");

        pcPrice = getValidDouble("Enter the price of the computer (e.g., 1198.56): ");
        moneySaved = getValidDouble("Amount of money saved (e.g., 560.56): ");
        savingsPerMonth = getValidDouble("Amount of money you can save monthly today (e.g., 300.00): ");

        System.out.println();
    }

    private String getUserName(String whose) {
        System.out.print("Enter " + whose + " first name: ");
        return scnr.next();
    }

    private int getValidInt(String prompt) {
        System.out.print(prompt);
        while (!scnr.hasNextInt()) {
            System.out.println("Invalid input. Please enter a whole number.");
            System.out.print(prompt);
            scnr.next();
        }
        return scnr.nextInt();
    }

    private double getValidDouble(String prompt) {
        System.out.print(prompt);
        while (!scnr.hasNextDouble()) {
            System.out.println("Invalid input. Please enter a numeric value.");
            System.out.print(prompt);
            scnr.next();
        }
        return scnr.nextDouble();
    }

    // Initial Storyline

    private void printPrologue() {
        System.out.println("=== PROLOGUE ===");
        System.out.println("In " + year + ", " + narratorName + " and " + friendName +
                " lived in " + city + ". At " + ageThen + " years old,");
        System.out.println("their dream was to buy a powerful gaming PC from " + storeName + ".");
        System.out.println("The total cost of the dream machine was $" +
                String.format("%.2f", pcPrice) + ".");
        System.out.println(narratorName + " had already saved $" +
                String.format("%.2f", moneySaved) +
                ", and could save $" + String.format("%.2f", savingsPerMonth) +
                " per month.");
        System.out.println();
    }

    // Main Decision Point

    private boolean getFinanceDecision() {
        while (true) {
            System.out.print(friendName +
                    " wanted to work part-time to help pay for the computer. "
                    + "Does " + friendName + " actually take a job? (yes/no): ");
            String choice = scnr.next().toLowerCase();

            if (choice.equals("yes")) {
                // Van a trabajar, no usan financiamiento
                return false;
            } else if (choice.equals("no")) {
                // Prefieren financiamiento
                return true;
            } else {
                System.out.println("Invalid response. Please answer 'yes' or 'no'.");
            }
        }
    }

    // Calculations (with overloading)

    private double calculateBalance(double price, double saved) {
        return price - saved;
    }

    private double calculateBalance(double price, double saved,
            boolean useFinance, double[] offers) {
        double base = calculateBalance(price, saved);
        if (!useFinance) {
            return base;
        }

        // Randomly select a financing offer
        int index = (int) (Math.random() * offers.length);
        double rate = offers[index];

        double totalWithInterest = base + (base * rate);
        return totalWithInterest;
    }

    // Each way of proceeding introductions

    private void printWorkPathIntro(double balance) {
        System.out.println("=== CHAPTER 1 - THE JOB HUNT ===");
        System.out.println(friendName + " decides to take a part-time job to avoid financing.");
        System.out.println("Together, you review some options:");
        for (int i = 0; i < jobOptions.length; i++) {
            System.out.println((i + 1) + ") " + jobOptions[i]);
        }
        System.out.println("Regardless of the option, your combined effort adds +$160 to monthly savings.");
        System.out.println("Remaining amount needed for the PC: $" +
                String.format("%.2f", Math.max(0, balance)));
        System.out.println();
    }

    private void printFinancePathIntro(double balance) {
        System.out.println("=== CHAPTER 1 - EASY CREDIT ===");
        System.out.println(friendName + " is tired and suggests financing the PC instead of working.");
        System.out.println("You sign a payment plan with added interest.");
        System.out.println("Total remaining balance after the financing agreement: $" +
                String.format("%.2f", Math.max(0, balance)));
        System.out.println();
    }

    // Monthly Payment Simulation

    private int simulateMonthlyPayments(double remaining, double monthly) {
        if (remaining <= 0) {
            return 0;
        }

        System.out.println("=== PAYMENT JOURNEY ===");
        int months = 0;

        while (remaining > 0) {
            months++;
            remaining -= monthly;
            if (remaining < 0) {
                remaining = 0;
            }
            System.out.println("Month " + months + ": saved/paid $" +
                    String.format("%.2f", monthly) +
                    " | Remaining: $" + String.format("%.2f", remaining));
        }

        return months;
    }

    // ================== Final Chapters ==================

    // Case where it was enough from the start
    private void printInstantPurchaseChapter() {
        System.out.println("=== CHAPTER 2 - READY FROM DAY ONE ===");
        System.out.println("To your surprise, the money you had saved was enough.");
        System.out.println(narratorName + " and " + friendName +
                " walk into " + storeName + " and pay the PC in full.");
        System.out.println("The first boot screen lights up the room, "
                + "and every future game reminds you of that perfect timing.");
    }

    private void printChapter2(int months) {
        System.out.println();
        System.out.println("=== CHAPTER 2 - THE FIRST BOOT ===");

        if (finance) {
            System.out.println("After " + months + " month(s) of strict payments, "
                    + narratorName + " and " + friendName
                    + " finally clear the financed balance.");
            System.out.println("They realize that quick solutions come with extra cost, "
                    + "but discipline turned a risky choice into a hard-earned victory.");
        } else {
            System.out.println("After " + months + " month(s) of working and saving, "
                    + narratorName + " and " + friendName
                    + " walk into " + storeName + " with cash in hand.");
            System.out.println("Every late shift, delivery, and tired weekend "
                    + "comes back when the PC finally powers on.");
            System.out.println("The new PC is more than just a pc—it's proof that patience");
        }
    }
}
