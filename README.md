# PC Adventure (Java)

## Description

GUI-based choose-your-own-adventure about buying a PC. You enter basic info, choose to work part-time or finance the purchase, and the program simulates monthly payments until the balance reaches zero.

It was originally created as a SWDV-105 final project and has been **refactored for SWDV-110 Final Project** to:

- Use object-oriented design with GUI.
- Improve code structure and readability.
- Add a new, integrated story chapter showing the outcome of the player's choices.

## Requirements

- Java 8+

## How to Run

```bash
javac Main.java PCAdventureGame.java
java Main
```

## Usage

1. Run the program
2. Fill the blanks at the GUI:
   - Names (yours and your friend's)
   - Friend's pronoun (he/she/they)
   - City, store name
   - Year and age
   - PC price, money saved, and savings per month
3. Click **yes/no** about your friend working part-time
   - If **no**, financing is applied automatically
   - If **yes**, you avoid financing and get extra monthly savings
4. The program shows month-by-month progress until the balance reaches zero
5. Choose 1 of the 3 endings

## Features

- **GUI Design** using Java Swing
- **Branching logic** with button decisions
- **Arrays** for job options and finance offers
- **Loops** for monthly simulation
- **Random selection** for finance offers
- **Input validation** prevents crashes from invalid input
- **Different story paths** based on choices
- **3 different endings**
- **Abstract class** (storyNode) with polymorphism
- **Recursion** (countTime, countTasks methods)

## Future Improvements

- Save game progress to file
- Add more job options with different pay rates
