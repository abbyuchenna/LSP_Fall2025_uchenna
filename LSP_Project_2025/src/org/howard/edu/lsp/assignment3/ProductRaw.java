package org.howard.edu.lsp.assignment3;

import java.math.BigDecimal;

/** Immutable representation of one input CSV row (after parsing). */
public class ProductRaw {
    public final int productId;
    public final String name;
    public final BigDecimal price;   // original price
    public final String category;    // original category

    public ProductRaw(int productId, String name, BigDecimal price, String category) {
        this.productId = productId;
        this.name = name;
        this.price = price;
        this.category = category;
    }
}