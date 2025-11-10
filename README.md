# 💻 PC Adventure (Java)

## 📝 Description

Console-based choose-your-own-adventure about buying a PC. This program uses **methods and modularity** to create a clean, maintainable codebase. You enter basic info, choose to work part-time or finance the purchase, and the program simulates monthly payments until the balance reaches zero.

## ⚙️ Requirements

- Java 11+

## 🚀 How to Run

**Quick run** (no separate compile step):

```bash
java Main.java
```

**Or compile and run:**

```bash
javac Main.java && java Main
```

## 📖 Usage

1. Run the program
2. Answer the prompts:
   - Names (yours and your friend's)
   - City, store name
   - Year and age
   - Prices and savings per month
3. Answer **yes/no** about working part-time
   - Input is case-insensitive and validated
   - If **no**, financing is applied automatically
4. The program prints month-by-month progress until the balance reaches zero

## ✨ Features

### **Methods & Modularity:**

- **11+ methods** with clear purposes
- **Input validation methods** for integers and doubles
- **Method overloading** (calculateBalance with different parameters)
- **Methods working with arrays** (job options, finance offers)
- **Non-void methods** that return values
- **Methods with 2+ parameters** for flexible functionality
- **Modular main()** that coordinates program flow

### **Core Features:**

- **Branching logic** with yes/no decision
- **Arrays** for options (jobs and finance offers)
- **Loops** for listing options and monthly simulation
- **Random selection** for finance offers
- **Input validation** prevents crashes from invalid input

## 🔧 Method Structure

### Input Methods:

- `getUserName()` - Gets user names
- `getInput()` - Generic string input
- `getValidInt()` - Validates integer input
- `getValidDouble()` - Validates double input
- `getFinanceDecision()` - Handles yes/no choice with validation

### Calculation Methods:

- `calculateBalance()` - Two versions (overloaded method)
  - With finance array for interest calculation
  - Simple version without finance

### Display Methods:

- `printPrologue()` - Displays story introduction
- `printChapter1()` - Displays main story chapter
- `printJobOptions()` - Lists job applications (works with array)
- `printFinanceOffers()` - Lists finance plans (works with array)

### Simulation Method:

- `simulateMonthlyPayments()` - Runs payment loop and returns total months

## 🔮 Future Improvements

- Final summary with months and total paid
- add possible entry from a web page
