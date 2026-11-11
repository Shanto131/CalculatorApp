package com.example.calculatorapp;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

public class CalculatorEngine {
    private static final MathContext PRECISION =
            new MathContext(14, RoundingMode.HALF_UP);
    private static final BigDecimal TWO = new BigDecimal("2");

    private String Display = "0";
    private String Expression = "";
    private String Operator = "";
    private BigDecimal First = null;
    private BigDecimal Memory = BigDecimal.ZERO;
    private boolean HasMemory = false;
    private boolean StartNew = true;
    private boolean Error = false;
    private String LastHistory = null;
    private BigDecimal LastOperand = null;
    private String LastOperator = "";

    public String GetDisplay() { return Display; }
    public String GetExpression() { return Expression; }
    public boolean HasMemory() { return HasMemory; }
    public String GetMemoryText() { return HasMemory ? Format(Memory) : ""; }

    public String ConsumeHistory() {
        String Value = LastHistory;
        LastHistory = null;
        return Value;
    }

    private BigDecimal Current() {
        return new BigDecimal(Display);
    }

    private String Format(BigDecimal Value) {
        if (Value.signum() == 0) return "0";
        return Value.stripTrailingZeros().toPlainString();
    }

    private void SetError(String Message) {
        Display = Message;
        Expression = "";
        First = null;
        Operator = "";
        LastOperand = null;
        LastOperator = "";
        LastHistory = null;
        StartNew = true;
        Error = true;
    }

    public void Digit(String Value) {
        if (Error) Clear();
        if (StartNew || Display.equals("0")) {
            Display = Value;
            StartNew = false;
        } else if (Display.replace("-", "").replace(".", "").length() < 15) {
            Display += Value;
        }
    }

    public void Decimal() {
        if (Error) Clear();
        if (StartNew) {
            Display = "0.";
            StartNew = false;
        } else if (!Display.contains(".")) {
            Display += ".";
        }
    }

    public void Backspace() {
        if (Error) {
            Clear();
            return;
        }
        if (StartNew) return;

        Display = Display.substring(0, Display.length() - 1);
        if (Display.isEmpty() || Display.equals("-")) Display = "0";
    }

    public void ClearEntry() {
        Display = "0";
        StartNew = true;
        Error = false;
    }

    public void Clear() {
        Display = "0";
        Expression = "";
        Operator = "";
        First = null;
        LastOperand = null;
        LastOperator = "";
        LastHistory = null;
        StartNew = true;
        Error = false;
    }

    public void Sign() {
        if (Error || Current().signum() == 0) return;
        Display = Display.startsWith("-")
                ? Display.substring(1)
                : "-" + Display;
    }

    private BigDecimal Apply(BigDecimal Left, BigDecimal Right, String Op) {
        switch (Op) {
            case "+": return Left.add(Right, PRECISION);
            case "−": return Left.subtract(Right, PRECISION);
            case "×": return Left.multiply(Right, PRECISION);
            case "÷": return Left.divide(Right, PRECISION);
            default: return Right;
        }
    }

    public void SetOperator(String NewOperator) {
        if (Error) return;

        try {
            if (First != null && !Operator.isEmpty() && !StartNew) {
                BigDecimal Right = Current();
                BigDecimal Result = Apply(First, Right, Operator);
                String Text = Format(First) + " " + Operator + " "
                        + Format(Right) + " = " + Format(Result);
                LastHistory = Text;
                Display = Format(Result);
            }

            First = Current();
            Operator = NewOperator;
            Expression = Format(First) + " " + Operator;
            StartNew = true;
            LastOperator = "";
        } catch (ArithmeticException Exception) {
            SetError("Cannot divide by zero");
        }
    }

    public void Equals() {
        if (Error) return;

        try {
            BigDecimal Left;
            BigDecimal Right;
            String Op;

            if (First != null && !Operator.isEmpty()) {
                Left = First;
                Right = Current();
                Op = Operator;
            } else if (!LastOperator.isEmpty() && LastOperand != null) {
                Left = Current();
                Right = LastOperand;
                Op = LastOperator;
            } else {
                return;
            }

            BigDecimal Result = Apply(Left, Right, Op);
            String ResultText = Format(Result);
            Expression = Format(Left) + " " + Op + " " + Format(Right) + " =";
            LastHistory = Expression + " " + ResultText;
            Display = ResultText;
            LastOperand = Right;
            LastOperator = Op;
            First = null;
            Operator = "";
            StartNew = true;
        } catch (ArithmeticException Exception) {
            SetError("Cannot divide by zero");
        }
    }

    public void Percent() {
        if (Error) return;

        BigDecimal Value = Current();
        if (First != null && (Operator.equals("+") || Operator.equals("−"))) {
            Value = First.multiply(Value, PRECISION)
                    .divide(new BigDecimal("100"), PRECISION);
        } else {
            Value = Value.divide(new BigDecimal("100"), PRECISION);
        }

        Display = Format(Value);
        StartNew = false;
    }

    /*
     * Calculates a square root using BigDecimal only.
     * This avoids converting large values to double, which can become
     * Infinity and cause a NumberFormatException.
     */
    private BigDecimal SquareRoot(BigDecimal Value) {
        if (Value.signum() == 0) return BigDecimal.ZERO;

        int DecimalMagnitude = Value.precision() - Value.scale() - 1;
        int InitialExponent = Math.floorDiv(DecimalMagnitude, 2);
        BigDecimal Guess = BigDecimal.ONE.scaleByPowerOfTen(InitialExponent);

        for (int Index = 0; Index < 100; Index++) {
            BigDecimal Quotient = Value.divide(Guess, PRECISION);
            BigDecimal Next = Guess.add(Quotient, PRECISION)
                    .divide(TWO, PRECISION);

            if (Next.compareTo(Guess) == 0) {
                return Next;
            }
            Guess = Next;
        }

        return Guess;
    }

    public void Unary(String Operation) {
        if (Error) return;

        try {
            BigDecimal Value = Current();
            BigDecimal Result;
            String Prefix;

            switch (Operation) {
                case "square":
                    Result = Value.multiply(Value, PRECISION);
                    Prefix = "sqr";
                    break;
                case "sqrt":
                    if (Value.signum() < 0) {
                        SetError("Invalid input");
                        return;
                    }
                    Result = SquareRoot(Value);
                    Prefix = "√";
                    break;
                case "reciprocal":
                    Result = BigDecimal.ONE.divide(Value, PRECISION);
                    Prefix = "1/";
                    break;
                default:
                    return;
            }

            Display = Format(Result);
            Expression = Prefix + "(" + Format(Value) + ")";
            LastHistory = Expression + " = " + Display;
            StartNew = true;
        } catch (ArithmeticException Exception) {
            SetError("Cannot divide by zero");
        }
    }

    public void MemoryStore() {
        if (!Error) {
            Memory = Current();
            HasMemory = true;
        }
    }

    public void MemoryRecall() {
        if (HasMemory) {
            Display = Format(Memory);
            StartNew = true;
            Error = false;
        }
    }

    public void MemoryClear() {
        Memory = BigDecimal.ZERO;
        HasMemory = false;
    }

    public void MemoryAdd() {
        if (!Error) {
            Memory = (HasMemory ? Memory : BigDecimal.ZERO)
                    .add(Current(), PRECISION);
            HasMemory = true;
        }
    }

    public void MemorySubtract() {
        if (!Error) {
            Memory = (HasMemory ? Memory : BigDecimal.ZERO)
                    .subtract(Current(), PRECISION);
            HasMemory = true;
        }
    }
}
