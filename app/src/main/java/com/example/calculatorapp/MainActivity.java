
package com.example.calculatorapp;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private final CalculatorEngine Engine = new CalculatorEngine();
    private final CalculationHistory History = new CalculationHistory();

    private TextView DisplayText;
    private TextView ExpressionText;
    private TextView MemoryIndicator;

    private LinearLayout HistoryPanel;
    private LinearLayout HistoryEntries;

    private TextView EmptyHistory;

    private boolean ShowingHistory = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DisplayText = findViewById(R.id.displayText);
        ExpressionText = findViewById(R.id.expressionText);
        MemoryIndicator = findViewById(R.id.memoryIndicator);

        HistoryPanel = findViewById(R.id.historyPanel);
        HistoryEntries = findViewById(R.id.historyEntries);
        EmptyHistory = findViewById(R.id.emptyHistory);

        findViewById(R.id.historyToggle).setOnClickListener(
                v -> ToggleHistory()
        );

        findViewById(R.id.clearHistory).setOnClickListener(v -> {
            History.Clear();
            RefreshHistory();
        });

        int[] ButtonIds = {
                R.id.btn_percent,
                R.id.btn_ce,
                R.id.btn_c,
                R.id.btn_back,

                R.id.btn_reciprocal,
                R.id.btn_square,
                R.id.btn_sqrt,
                R.id.btn_divide,

                R.id.btn_7,
                R.id.btn_8,
                R.id.btn_9,
                R.id.btn_multiply,

                R.id.btn_4,
                R.id.btn_5,
                R.id.btn_6,
                R.id.btn_subtract,

                R.id.btn_1,
                R.id.btn_2,
                R.id.btn_3,
                R.id.btn_add,

                R.id.btn_sign,
                R.id.btn_0,
                R.id.btn_decimal,
                R.id.btn_equals,

                R.id.btn_mc,
                R.id.btn_mr,
                R.id.btn_mplus,
                R.id.btn_mminus,
                R.id.btn_ms,
                R.id.btn_mview
        };

        for (int ButtonId : ButtonIds) {
            findViewById(ButtonId).setOnClickListener(
                    v -> HandleButton(v.getId())
            );
        }

        Refresh();
    }

    private void HandleButton(int Id) {

        if (Id == R.id.btn_0) Engine.Digit("0");
        else if (Id == R.id.btn_1) Engine.Digit("1");
        else if (Id == R.id.btn_2) Engine.Digit("2");
        else if (Id == R.id.btn_3) Engine.Digit("3");
        else if (Id == R.id.btn_4) Engine.Digit("4");
        else if (Id == R.id.btn_5) Engine.Digit("5");
        else if (Id == R.id.btn_6) Engine.Digit("6");
        else if (Id == R.id.btn_7) Engine.Digit("7");
        else if (Id == R.id.btn_8) Engine.Digit("8");
        else if (Id == R.id.btn_9) Engine.Digit("9");

        else if (Id == R.id.btn_decimal) Engine.Decimal();
        else if (Id == R.id.btn_back) Engine.Backspace();
        else if (Id == R.id.btn_ce) Engine.ClearEntry();
        else if (Id == R.id.btn_c) Engine.Clear();
        else if (Id == R.id.btn_sign) Engine.Sign();

        else if (Id == R.id.btn_add) Engine.SetOperator("+");
        else if (Id == R.id.btn_subtract) Engine.SetOperator("−");
        else if (Id == R.id.btn_multiply) Engine.SetOperator("×");
        else if (Id == R.id.btn_divide) Engine.SetOperator("÷");

        else if (Id == R.id.btn_equals) Engine.Equals();
        else if (Id == R.id.btn_percent) Engine.Percent();

        else if (Id == R.id.btn_square) Engine.Unary("square");
        else if (Id == R.id.btn_sqrt) Engine.Unary("sqrt");
        else if (Id == R.id.btn_reciprocal) {
            Engine.Unary("reciprocal");
        }

        else if (Id == R.id.btn_mc) Engine.MemoryClear();
        else if (Id == R.id.btn_mr) Engine.MemoryRecall();
        else if (Id == R.id.btn_mplus) Engine.MemoryAdd();
        else if (Id == R.id.btn_mminus) Engine.MemorySubtract();
        else if (Id == R.id.btn_ms) Engine.MemoryStore();

        else if (Id == R.id.btn_mview) {
            ShowMemory();
            return;
        }

        String Entry = Engine.ConsumeHistory();

        if (Entry != null) {
            History.Add(Entry);
            RefreshHistory();
        }

        Refresh();
    }

    private void Refresh() {

        DisplayText.setText(Engine.GetDisplay());
        ExpressionText.setText(Engine.GetExpression());

        MemoryIndicator.setText(
                Engine.HasMemory()
                        ? getString(R.string.memory_indicator)
                        : ""
        );
    }

    private void ToggleHistory() {

        ShowingHistory = !ShowingHistory;

        HistoryPanel.setVisibility(
                ShowingHistory ? View.VISIBLE : View.GONE
        );

        RefreshHistory();
    }

    private void ShowMemory() {

        ShowingHistory = true;

        HistoryPanel.setVisibility(View.VISIBLE);

        HistoryEntries.removeAllViews();

        EmptyHistory.setVisibility(View.VISIBLE);

        EmptyHistory.setText(
                Engine.HasMemory()
                        ? getString(
                        R.string.memory_value,
                        Engine.GetMemoryText()
                )
                        : getString(R.string.memory_empty)
        );
    }

    private void RefreshHistory() {

        HistoryEntries.removeAllViews();

        EmptyHistory.setText(R.string.history_empty);

        EmptyHistory.setVisibility(
                History.GetEntries().isEmpty()
                        ? View.VISIBLE
                        : View.GONE
        );

        for (String Entry : History.GetEntries()) {

            TextView Item = new TextView(this);

            Item.setText(Entry);
            Item.setTextSize(17);

            Item.setTextColor(
                    getColor(R.color.text_primary)
            );

            Item.setPadding(12, 16, 12, 16);

            HistoryEntries.addView(Item);
        }
    }
}
