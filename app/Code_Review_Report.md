# MADT Lab #4 — CalculatorApp Code Review

## Files reviewed
- `MainActivity.java`
- `CalculatorEngine.java`
- `CalculationHistory.java`

The uploaded files did not include `activity_main.xml`, `strings.xml`, the manifest, Gradle configuration, or drawable/color resources. Therefore, those files and the rendered UI could not be verified in this review.

## Findings

### MainActivity.java
- UI event handling is centralized in `HandleButton`, and calculation work is delegated to `CalculatorEngine`, which is a useful separation of concerns.
- Runtime history is updated by consuming the engine's latest history entry after button actions.
- `ShowMemory()` reuses the history panel to show the memory value. This works as a simple display approach, but the UI should be tested to ensure the panel's contents and empty-state label are restored correctly when switching back to history.
- `getColor()` requires API 23 or newer. The app's `minSdk` was not supplied, so compatibility cannot be confirmed.
- Naming uses PascalCase for fields, parameters and methods. This follows the stated lab requirement, although conventional Java style normally uses lowerCamelCase for methods and variables.

### CalculatorEngine.java
- Addition, subtraction, multiplication and division use `BigDecimal` with a 14-digit `MathContext`; this avoids many binary floating-point representation errors.
- Division by zero is caught and shown as an error.
- Negative square roots are rejected.
- Memory store, recall, clear, add and subtract are implemented.
- The original square-root implementation converted a `BigDecimal` to `double` to create its initial estimate. Very large values can convert to positive infinity, which cannot be passed to the `BigDecimal(double, MathContext)` constructor and can crash the app. This is the highest-priority calculation defect.
- `SetError()` did not clear the repeated-equals operands/operator or pending history text. Clearing these stale values makes the error state safer and more predictable.

### CalculationHistory.java
- The returned history list is unmodifiable, which prevents callers from directly changing internal history.
- The original list had no size limit. A long-running session could accumulate entries indefinitely.

## Two implemented improvements

### 1. BigDecimal-only square-root calculation and safer error reset
Replaced the `double`-based initial estimate with a decimal-magnitude estimate and a BigDecimal Newton iteration. This avoids overflow through `double` infinity and keeps the calculation within the app's decimal arithmetic approach. Error reset now also clears stale repeated-equals and pending-history state.

### 2. Bounded runtime history
History now retains at most the 100 newest entries and ignores null/blank entries. This prevents unbounded list growth during long sessions while preserving the runtime-history feature.

## Requirements that appear supported by the supplied Java files
- Arithmetic operations: `+`, `−`, `×`, `÷`
- Square root, square and reciprocal
- Backspace, clear entry, clear and sign change
- Percent
- Memory store, recall, clear, add and subtract
- Runtime calculation history
- No third-party calculation library is used; arithmetic is implemented with Java's `BigDecimal`.

## Not verified due to missing files
- The original layout hardcoded calculator button labels and two content descriptions; generated updated XML and string resources now externalize these values.
- The layout uses a ConstraintLayout, a six-row/four-column GridLayout keypad, and a history overlay. Visual appearance still needs emulator/device verification.
- Whether all view IDs used by `MainActivity` exist
- Theme, colors, accessibility labels/content descriptions and minimum SDK compatibility
- Whether the full project builds successfully

## Test checklist for Android Studio
1. `8 + 2 =` → `10`
2. `8 − 2 =` → `6`
3. `8 × 2 =` → `16`
4. `8 ÷ 2 =` → `4`
5. `8 ÷ 0 =` → error message; app remains open
6. `√9` → `3`
7. `√(-9)` → invalid-input message; app remains open
8. `1 ÷ 0` using reciprocal → division-by-zero message
9. Store `25` in memory, clear display, recall → `25`
10. M+ and M− update the stored value correctly
11. Perform more than 100 calculations → history retains only the newest 100
12. Clear history → history panel shows its empty state
13. Rotate the device and test the layout and runtime state behavior

**Important:** These changes are generated from the three supplied source files. A successful build and device/emulator test have not yet been performed. Please replace the corresponding files through your AI coding workflow, then build and run the app in Android Studio.


## Additional UI/resource compliance update
The supplied `activity_main.xml` hardcoded keypad labels and the History/Clear History content descriptions. The generated replacement moves these values into `strings.xml`. This supports the lab requirement that button labels come from resources and makes accessibility descriptions localizable. No change was made to the calculator's visual arrangement.

## Build configuration reviewed
The supplied `build.gradle.kts` uses namespace/application ID `com.example.calculatorapp`, minSdk 24, targetSdk 37, and Java 11. These values are internally consistent with the supplied Java package. Dependencies include AndroidX/Material components and JUnit/Espresso test libraries. No calculation-specific third-party library is present.
