package org.howard.edu.lsp.assignment3;

import java.math.BigDecimal;

/** Immutable representation of one transformed output row. */
public class ProductTransformed {
    public final int productId;
    public final String nameUpper;
    public final BigDecimal finalPrice;
    public final String finalCategory;
    public final PriceRange priceRange;

    public ProductTransformed(int productId, String nameUpper, BigDecimal finalPrice,
                              String finalCategory, PriceRange priceRange) {
        this.productId = productId;
        this.nameUpper = nameUpper;
        this.finalPrice = finalPrice;
        this.finalCategory = finalCategory;
        this.priceRange = priceRange;
    }

    /** Output line in assignment-specified column order. */
    public String toCsvLine() {
        return productId + "," + nameUpper + "," + finalPrice + "," + finalCategory + "," + priceRange.label;
    }
}