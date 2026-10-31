# CalculatorApp – MADT Lab #4

## 1. Project Overview

CalculatorApp is a simple Android calculator application developed using Java and Android Studio as part of Mobile Application Development Technologies (MADT) Lab #4.

The application allows users to perform basic arithmetic calculations as well as several additional mathematical operations. It also includes memory functions and a calculation history panel.

The main purpose of this project is to practise Android application development, Java programming, user interface design, and handling user interactions.

## 2. Features

The application includes the following features:

- Simple and user-friendly calculator interface.
- Number input from 0 to 9.
- Decimal number support.
- Basic arithmetic calculations.
- Additional mathematical operations.
- Positive and negative number conversion.
- Backspace functionality.
- Clear Entry (CE) and Clear (C) options.
- Calculator memory functions.
- Calculation history panel.
- Clear calculation history.
- Display of the current mathematical expression.
- Memory indicator when a value is stored.

## 3. Calculator Operations

The calculator supports the following mathematical operations:

| Operation | Description |
|---|---|
| Addition (+) | Adds two numbers. |
| Subtraction (−) | Subtracts one number from another. |
| Multiplication (×) | Multiplies two numbers. |
| Division (÷) | Divides one number by another. |
| Square (x²) | Calculates the square of a number. |
| Square Root (√) | Calculates the square root of a number. |
| Percentage (%) | Calculates a percentage value. |
| Reciprocal (1/x) | Calculates the reciprocal of a number. |
| Sign Change (+/−) | Changes a positive number to negative or vice versa. |

Additional input controls:

- **CE:** Clears the current number entry.
- **C:** Resets the current calculation.
- **Backspace:** Removes the last entered digit.
- **Decimal (.):** Allows decimal number input.
- **Equals (=):** Calculates and displays the result.

## 4. Memory Functions

The application provides six memory-related buttons:

| Button | Function |
|---|---|
| MS | Stores the current displayed number in memory. |
| MR | Recalls the stored number. |
| MC | Clears the stored memory value. |
| M+ | Adds the current displayed number to memory. |
| M− | Subtracts the current displayed number from memory. |
| Mv | Displays the current stored memory value in the information panel. |

A memory indicator is also displayed when a value is stored.

## 5. Calculation History

CalculatorApp includes a calculation history feature.

Users can:

- Open and close the history panel.
- View previous calculations.
- See calculations with their expressions and results.
- Clear all saved history entries.
- View the most recent calculations first.

The calculation history is stored in memory while the application is running. It is not saved permanently after the application is closed.

## 6. Technologies Used

The following technologies and tools were used to develop this application:

- **Programming Language:** Java
- **Platform:** Android
- **IDE:** Android Studio
- **User Interface:** XML
- **Layout:** Android ConstraintLayout and GridLayout
- **Calculation:** Java BigDecimal
- **Build System:** Gradle
- **Version Control:** Git and GitHub

## 7. Project Structure

The main source files are organised as follows:

```text
CalculatorApp/
│
├── app/
│   └── src/
│       └── main/
│           ├── java/
│           │   └── com/
│           │       └── example/
│           │           └── calculatorapp/
│           │               ├── MainActivity.java
│           │               ├── CalculatorEngine.java
│           │               └── CalculationHistory.java
│           │
│           ├── res/
│           │   ├── layout/
│           │   │   └── activity_main.xml
│           │   ├── values/
│           │   │   ├── strings.xml
│           │   │   └── colors.xml
│           │   └── ...
│           │
│           └── AndroidManifest.xml
│
├── build.gradle
├── settings.gradle
└── README.md
```

### Main Java Classes

- **MainActivity.java:** Handles the calculator interface, button clicks, display updates, memory indicator, and calculation history panel.
- **CalculatorEngine.java:** Contains the main calculation logic, mathematical operations, number input handling, and memory functions.
- **CalculationHistory.java:** Manages the calculation history entries, including adding, retrieving, and clearing entries.

## 8. How to Run the Application

Follow these steps to run CalculatorApp:

1. Install Android Studio on your computer.
2. Clone or download this GitHub repository.
3. Open Android Studio.
4. Select **Open** and choose the CalculatorApp project folder.
5. Wait for Gradle Sync to finish.
6. Create or select an Android Virtual Device (AVD), or connect an Android phone with USB debugging enabled.
7. Click the **Run** button in Android Studio.
8. Select your device and wait for the application to install.
9. Open CalculatorApp and start performing calculations.

## 9. Screenshots

Screenshots of the application can be added here to demonstrate its user interface and functionality.

### Calculator Interface

![Calculator Interface](screenshots/calculator-home.png)

### Calculation History

![Calculation History](screenshots/calculation-history.png)

### Memory Functions

![Memory Functions](screenshots/calculator-memory.png)

**Note:** Replace the image paths above with your actual screenshot filenames. Create a `screenshots` folder in the repository and upload the corresponding images.

## 10. Author

**Name:** MD Shanto

**University:** Vilnius University

**Faculty:** Kaunas Faculty

**Course:** Mobile Application Development Technologies (MADT)

**Practical Work:** Lab #4 – CalculatorApp

---

*This project was developed for educational purposes as part of a university practical assignment.*
