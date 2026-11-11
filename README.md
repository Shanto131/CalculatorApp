# CalculatorApp

**MADT Lab #4 – Mobile Application Development**

## 1. Project Overview

CalculatorApp is an Android calculator application developed using Java and Android Studio as part of the Mobile Application Development (MADT) course.

The application is designed with an interface similar to the Windows Standard Calculator. It supports basic arithmetic operations, additional mathematical functions, memory operations, and calculation history.

The main purpose of this project is to practise Android application development, Java programming, user interface design, and implementing calculation logic.

## 2. Features

The application includes the following features:

- Simple calculator interface inspired by Windows Standard Calculator.
- Basic arithmetic calculations.
- Additional mathematical operations.
- Functional memory buttons.
- Calculation history during application runtime.
- Clear and backspace functionality.
- Positive and negative number conversion.
- Custom calculation logic implemented in Java.
- Button labels managed using `strings.xml`.

## 3. Calculator Operations

The application supports the following mathematical operations:

| Operation | Description |
|---|---|
| Addition (+) | Adds two numbers. |
| Subtraction (−) | Subtracts one number from another. |
| Multiplication (×) | Multiplies two numbers. |
| Division (÷) | Divides one number by another. |
| Square Root (√) | Calculates the square root of a number. |
| Square (x²) | Calculates the square of a number. |
| Percentage (%) | Calculates percentage values. |
| Reciprocal (1/x) | Calculates the reciprocal of a number. |
| Sign Change (±) | Changes a number between positive and negative. |
| Backspace (⌫) | Deletes the last entered digit. |
| CE | Clears the current entry. |
| C | Resets the current calculation. |

## 4. Memory Functions

The calculator provides memory functions that allow users to store and manage numerical values.

| Button | Function |
|---|---|
| MC | Clears the stored memory value. |
| MR | Recalls the stored memory value. |
| MS | Stores the current value in memory. |
| M+ | Adds the current value to the stored memory value. |
| M− | Subtracts the current value from the stored memory value. |
| Mv | Displays the stored memory value. |

These functions make it easier to reuse values without entering them repeatedly.

## 5. Calculation History

The application includes a calculation history feature.

- Displays previous calculations during application runtime.
- Allows users to review their calculation results.
- Stores up to 100 calculation entries.
- When the limit is reached, the oldest entries are removed and the newest calculations are retained.

The calculation history is maintained while the application is running.

## 6. Technologies Used

The following technologies and tools were used to develop this application:

- **Programming Language:** Java
- **Platform:** Android
- **IDE:** Android Studio
- **User Interface:** XML
- **Layout:** ConstraintLayout and GridLayout
- **Minimum Development Environment:** Android SDK
- **Version Control:** Git and GitHub

## 7. Project Structure

The main project files are organised as follows:

```text
CalculatorApp/
│
├── app/
│   └── src/
│       └── main/
│           ├── java/
│           │   └── com/example/calculatorapp/
│           │       ├── MainActivity.java
│           │       ├── CalculatorEngine.java
│           │       └── CalculationHistory.java
│           │
│           ├── res/
│           │   ├── layout/
│           │   │   └── activity_main.xml
│           │   │
│           │   └── values/
│           │       └── strings.xml
│           │
│           └── AndroidManifest.xml
│
├── build.gradle
└── README.md
```

### Main Java Classes

- **MainActivity.java:** Handles the application interface and user interactions.
- **CalculatorEngine.java:** Contains the calculator's mathematical operations and calculation logic.
- **CalculationHistory.java:** Manages the calculation history.

## 8. How to Run the Application

Follow these steps to run the application:

1. Clone or download this repository from GitHub.
2. Open Android Studio.
3. Select **Open** and choose the downloaded project folder.
4. Wait for Gradle to sync and complete the project setup.
5. Connect an Android device or start an Android Emulator.
6. Click the **Run** button in Android Studio.
7. The CalculatorApp will be installed and launched on your device or emulator.

### Clone Repository

```bash
git clone https://github.com/Shanto131/CalculatorApp.git
```

## 9. Screenshots

Screenshots of the application can be added below to demonstrate its interface and functionality.

<!-- Add your actual application screenshots here. -->

Example:

```markdown
![Calculator Main Screen](screenshots/calculator-main.png)

![Calculator History](screenshots/calculator-history.png)
```

## 10. Author

**MD Shanto**

- GitHub: [Shanto131](https://github.com/Shanto131)
- Project: CalculatorApp
- Course: Mobile Application Development (MADT)
- Assignment: Lab #4

---

**Developed as part of the Mobile Application Development course using Java and Android Studio.**
