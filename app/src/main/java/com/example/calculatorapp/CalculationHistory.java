package com.example.calculatorapp;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CalculationHistory {
    private static final int MAX_ENTRIES = 100;

    private final List<String> Entries = new ArrayList<>();

    public void Add(String Entry) {
        if (Entry == null || Entry.trim().isEmpty()) {
            return;
        }

        Entries.add(0, Entry);

        // Keep the newest entries and prevent unlimited memory growth.
        if (Entries.size() > MAX_ENTRIES) {
            Entries.remove(Entries.size() - 1);
        }
    }

    public List<String> GetEntries() {
        return Collections.unmodifiableList(Entries);
    }

    public void Clear() {
        Entries.clear();
    }
}
