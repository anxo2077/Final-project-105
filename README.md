# PC Adventure (Java)

## Description

Console-based choose-your-own-adventure about buying a PC. This program uses **methods and modularity** to create a clean, maintainable codebase. You enter basic info, choose to work part-time or finance the purchase, and the program simulates monthly payments until the balance reaches zero.

It was originally created as a SWDV-105 final project and has been **refactored for SWDV-110 Module 1** to:

- Use object-oriented design.
- Improve code structure and readability.
- Add a new, integrated story chapter showing the outcome of the player’s choices.

## Requirements

- Java 8+

## How to Run

**Compile and run (Windows/Cross-platform compatible):**

```bash
javac --release 8 Main.java PCAdventureGame.java
java Main
```

**Alternative (standard compilation):**

```bash
javac Main.java PCAdventureGame.java
java Main
```

## Usage

1. Run the program
2. Answer the prompts:
   - Names (yours and your friend's)
   - Friend's pronoun (he/she/they)
   - City, store name
   - Year and age
   - PC price, money saved, and savings per month
3. Answer **yes/no** about your friend working part-time
   - Input is case-insensitive and validated
   - If **no**, financing is applied automatically
   - If **yes**, you avoid financing and get extra monthly savings
4. The program prints month-by-month progress until the balance reaches zero

## Features

### **Methods & Modularity:**

- **15+ methods** with clear purposes
- **Input validation methods** for integers and doubles
- **Method overloading** (calculateBalance with different parameters)
- **Methods working with arrays** (job options, finance offers)
- **Non-void methods** that return values
- **Methods with 2+ parameters** for flexible functionality
- **Modular run()** method that coordinates program flow
- **Separate class** (PCAdventureGame) for better organization

### **Features:**

- **Branching logic** with yes/no decision
- **Arrays** for job options and finance offers
- **Loops** for monthly simulation and input validation
- **Random selection** for finance offers
- **Input validation** prevents crashes from invalid input
- **Different story paths** based on choices

## Method Structure

### Input Methods:

- `collectUserData()` - Gathers all user information
- `getUserName()` - Gets user names with custom prompts
- `getValidInt()` - Validates integer input with error handling
- `getValidDouble()` - Validates double input with error handling
- `getFinanceDecision()` - Handles yes/no choice with validation

### Calculation Methods:

- `calculateBalance()` - Two versions (overloaded method)
  - Basic version for simple balance calculation
  - Extended version with finance array for interest calculation

### Display Methods:

- `printPrologue()` - Displays story introduction
- `printWorkPathIntro()` - Shows job path chapter
- `printFinancePathIntro()` - Shows financing path chapter
- `printInstantPurchaseChapter()` - Special case when money is enough
- `printChapter2()` - Final chapter with different endings

### Simulation Method:

- `simulateMonthlyPayments()` - Runs payment loop and returns total months

## Future Improvements

- Final summary with months and total paid
- Save game progress to file
- Add more job options with different pay rates
