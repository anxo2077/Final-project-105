import javax.swing.*;
import java.awt.*;

public class PCAdventureGame extends JFrame {

    private JTextArea storyArea;
    private JPanel choicesPanel;
    private JLabel headerLabel;

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
    private double moneyLeft;
    private boolean finance;

    private final String[] jobOptions = {
            "Weekend cashier at a local electronics store (+$160/month).",
            "Pizza delivery driver on Friday nights (+$160/month).",
            "Library assistant and PC setup helper (+$160/month)."
    };

    // Financing offers (interest rates)
    private final double[] financeOffers = { 0.10, 0.15, 0.08 };

    public PCAdventureGame() {
        setTitle("PC Adventure");
        setSize(600, 550);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        headerLabel = new JLabel("PC ADVENTURE", SwingConstants.CENTER);
        headerLabel.setFont(new Font("Arial", Font.BOLD, 18));

        storyArea = new JTextArea();
        storyArea.setEditable(false);
        choicesPanel = new JPanel(new FlowLayout());

        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        mainPanel.add(headerLabel, BorderLayout.NORTH);
        mainPanel.add(new JScrollPane(storyArea), BorderLayout.CENTER);
        mainPanel.add(choicesPanel, BorderLayout.SOUTH);

        add(mainPanel);
        setVisible(true);

        showInputForm();
    }

    private void showInputForm() {
        headerLabel.setText("PC ADVENTURE - INPUT SETUP");
        choicesPanel.removeAll();

        JPanel inputPanel = new JPanel(new GridLayout(10, 2, 5, 5));

        JTextField[] fields = new JTextField[10];
        String[] labels = {
                "Your first name:",
                "Friend's first name:",
                "Friend's pronoun (he/she/they):",
                "City name:",
                "Computer store name:",
                "Year (e.g., 2015):",
                "Your age that year:",
                "PC price (e.g., 1198.56):",
                "Money saved (e.g., 560.56):",
                "Monthly savings (e.g., 300.00):"
        };

        for (int i = 0; i < 10; i++) {
            inputPanel.add(new JLabel(labels[i]));
            fields[i] = new JTextField();
            inputPanel.add(fields[i]);
        }

        storyArea.setText("Welcome to PC Adventure!\n\nPlease fill in your information to begin.");

        JButton startBtn = new JButton("Start Adventure");
        startBtn.addActionListener(e -> {
            try {
                narratorName = fields[0].getText().trim();
                friendName = fields[1].getText().trim();
                friendPronoun = fields[2].getText().trim();
                city = fields[3].getText().trim();
                storeName = fields[4].getText().trim();
                year = Integer.parseInt(fields[5].getText().trim());
                ageThen = Integer.parseInt(fields[6].getText().trim());
                pcPrice = Double.parseDouble(fields[7].getText().trim());
                moneySaved = Double.parseDouble(fields[8].getText().trim());
                savingsPerMonth = Double.parseDouble(fields[9].getText().trim());

                if (narratorName.isEmpty() || friendName.isEmpty()) {
                    storyArea.setText("Error: Names cannot be empty.");
                    return;
                }

                showPrologue();
            } catch (NumberFormatException ex) {
                storyArea.setText("Error: Please enter valid numbers.");
            }
        });

        choicesPanel.add(inputPanel);
        choicesPanel.add(startBtn);
        refresh();
    }

    // Initial Storyline
    private void showPrologue() {
        headerLabel.setText("PROLOGUE");
        choicesPanel.removeAll();

        String text = "In " + year + ", " + narratorName + " and " + friendName + " lived in " + city +
                ". At " + ageThen + " years old,\ntheir dream was to buy a powerful gaming PC from " + storeName +
                ".\n\nThe total cost was $" + String.format("%.2f", pcPrice) + ". " + narratorName +
                " had saved $" + String.format("%.2f", moneySaved) + ", and could save $" +
                String.format("%.2f", savingsPerMonth) + " per month.\n\n" + friendName +
                " wanted to work part-time to help. Does " + friendName + " take a job?";

        storyArea.setText(text);

        // Main Decision Point
        JButton yesBtn = new JButton("Yes - Take a job");
        JButton noBtn = new JButton("No - Use financing");

        yesBtn.addActionListener(e -> {
            finance = false;
            moneyLeft = pcPrice - moneySaved;
            savingsPerMonth += 160;
            showWorkPath();
        });

        noBtn.addActionListener(e -> {
            finance = true;
            moneyLeft = calculateFinance();
            showFinancePath();
        });

        choicesPanel.add(yesBtn);
        choicesPanel.add(noBtn);
        refresh();
    }

    // Calculate
    private double calculateFinance() {
        double base = pcPrice - moneySaved;
        int index = (int) (Math.random() * financeOffers.length);
        double rate = financeOffers[index];
        return base + (base * rate);
    }

    private void showWorkPath() {
        headerLabel.setText("CHAPTER 1 - THE JOB HUNT");
        choicesPanel.removeAll();

        StringBuilder sb = new StringBuilder();
        sb.append(friendName + " decides to take a part-time job.\nOptions:\n\n");

        for (int i = 0; i < jobOptions.length; i++) {
            sb.append((i + 1) + ") " + jobOptions[i] + "\n");
        }

        sb.append("\nYour combined effort adds +$160 to monthly savings.\n");
        sb.append("Remaining: $" + String.format("%.2f", moneyLeft));

        storyArea.setText(sb.toString());

        JButton btn = new JButton("Continue saving");
        btn.addActionListener(e -> showPaymentJourney());
        choicesPanel.add(btn);
        refresh();
    }

    private void showFinancePath() {
        headerLabel.setText("CHAPTER 1 - EASY CREDIT");
        choicesPanel.removeAll();

        String text = friendName + " is tired and suggests financing the PC instead of working.\n" +
                "You sign a payment plan with interest.\n\n" +
                "Total balance: $" + String.format("%.2f", moneyLeft);

        storyArea.setText(text);

        JButton btn = new JButton("Start paying");
        btn.addActionListener(e -> showPaymentJourney());
        choicesPanel.add(btn);
        refresh();
    }

    private void showPaymentJourney() {
        headerLabel.setText("PAYMENT JOURNEY");
        choicesPanel.removeAll();

        if (moneyLeft <= 0) {
            showChapter2(0);
            return;
        }

        StringBuilder sb = new StringBuilder();
        double remaining = moneyLeft;
        int months = 0;

        while (remaining > 0) {
            months++;
            remaining -= savingsPerMonth;
            if (remaining < 0) {
                remaining = 0;
            }
            sb.append("Month " + months + ": paid $" + String.format("%.2f", savingsPerMonth) +
                    " | Remaining: $" + String.format("%.2f", remaining) + "\n");
        }

        storyArea.setText(sb.toString());

        int finalMonths = months;
        JButton btn = new JButton("Get the PC!");
        btn.addActionListener(e -> showChapter2(finalMonths));
        choicesPanel.add(btn);
        refresh();
    }

    // Show chapter 2
    private void showChapter2(int months) {
        headerLabel.setText("CHAPTER 2 - THE FIRST BOOT");
        choicesPanel.removeAll();

        String text;
        if (finance) {
            text = "After " + months + " month(s) of payments, " + narratorName + " and " + friendName +
                    " clear the balance.\nQuick solutions come with extra cost, but discipline made it a victory.";
        } else {
            text = "After " + months + " month(s) of saving, " + narratorName + " and " + friendName +
                    " walk into " + storeName
                    + " with cash.\nEvery late shift was worth it. The PC is proof that patience pays off.";
        }

        storyArea.setText(text);

        JButton btn = new JButton("Continue to Chapter 3");
        btn.addActionListener(e -> showChapter3());
        choicesPanel.add(btn);
        refresh();
    }

    // Show chapter 3 with storyNode events
    private void showChapter3() {
        headerLabel.setText("CHAPTER 3 - THE GAME BEGINS");
        choicesPanel.removeAll();

        storyNode[] events = {
                new TaskNode("Download Steam and install Prometian Security Rescue", 30),
                new TaskNode("Set Up Discord", 10),
                new EventNode("Invite friends to join the game night"),
                new TaskNode("Configure settings", 15),
                new MilestoneNode("Game Night Success!"),
                new TaskNode("Order pizza and snacks", 20),
                new EventNode("Enjoy the game night with friends")
        };

        StringBuilder sb = new StringBuilder();
        sb.append("The PC turned on. " + narratorName + " and " + friendName + " planned their first game night.\n\n");

        for (storyNode event : events) {
            sb.append(event.getDisplay() + "\n");
        }

        sb.append("\nSetup took " + countTime(events, 0) + " mins with " + countTasks(events, 0) + " tasks.");

        storyArea.setText(sb.toString());

        JButton b1 = new JButton("Celebrate victory!");
        JButton b2 = new JButton("Play all night");
        JButton b3 = new JButton("Plan next game night");

        b1.addActionListener(e -> showEnding(1));
        b2.addActionListener(e -> showEnding(2));
        b3.addActionListener(e -> showEnding(3));

        choicesPanel.add(b1);
        choicesPanel.add(b2);
        choicesPanel.add(b3);
        refresh();
    }

    private void showEnding(int n) {
        choicesPanel.removeAll();

        String[] titles = { "ENDING 1 - VICTORY", "ENDING 2 - ALL NIGHT", "ENDING 3 - TO BE CONTINUED" };
        String[] texts = {
                narratorName + " and " + friendName + " celebrate!\nAll the hard work paid off.\n\nTHE END",
                narratorName + " and " + friendName
                        + " gamed until sunrise!\nThe best adventures happen after midnight.\n\nTHE END",
                narratorName + " and " + friendName
                        + " plan the next game night.\nThis is just the beginning.\n\nTO BE CONTINUED..."
        };

        headerLabel.setText(titles[n - 1]);
        storyArea.setText(texts[n - 1] + "\n\nThank you for playing PC Adventure!");

        JButton btn = new JButton("Play Again");
        btn.addActionListener(e -> showInputForm());
        choicesPanel.add(btn);
        refresh();
    }

    private void refresh() {
        choicesPanel.revalidate();
        choicesPanel.repaint();
    }

    private int countTime(storyNode[] arr, int i) {
        if (i >= arr.length) {
            return 0;
        }
        return arr[i].getTime() + countTime(arr, i + 1);
    }

    private int countTasks(storyNode[] arr, int i) {
        if (i >= arr.length) {
            return 0;
        }
        if (arr[i].getTime() > 0) {
            return 1 + countTasks(arr, i + 1);
        } else {
            return countTasks(arr, i + 1);
        }
    }
}

abstract class storyNode {
    protected String name;

    public storyNode(String name) {
        this.name = name;
    }

    public abstract String getDisplay();

    public int getTime() {
        return 0;
    }
}

class TaskNode extends storyNode {
    private int min;

    public TaskNode(String name, int min) {
        super(name);
        this.min = min;
    }

    public String getDisplay() {
        return "• " + name + " (" + min + " mins)";
    }

    public int getTime() {
        return min;
    }
}

class EventNode extends storyNode {

    public EventNode(String name) {
        super(name);
    }

    public String getDisplay() {
        return "• " + name;
    }
}

class MilestoneNode extends storyNode {

    public MilestoneNode(String name) {
        super(name);
    }

    public String getDisplay() {
        return "-- " + name + " --";
    }
}