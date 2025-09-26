package org.howard.edu.lsp.assignment3;

import java.math.BigDecimal;

/** Price range computation based on FINAL price (inclusive bounds per spec). */
public enum PriceRange {
    LOW("Low"),
    MEDIUM("Medium"),
    HIGH("High"),
    PREMIUM("Premium");

    public final String label;
    PriceRange(String label) { this.label = label; }

    public static PriceRange fromPrice(BigDecimal p) {
        BigDecimal ten = new BigDecimal("10.00");
        BigDecimal hundred = new BigDecimal("100.00");
        BigDecimal fiveHundred = new BigDecimal("500.00");

        if (p.compareTo(ten) <= 0) return LOW;
        if (p.compareTo(hundred) <= 0) return MEDIUM;     // >10.00 and ≤100.00
        if (p.compareTo(fiveHundred) <= 0) return HIGH;   // >100.00 and ≤500.00
        return PREMIUM;                                   // >500.00
    }
}