package org.howard.edu.lsp.assignment3;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

/** Applies the assignment's business rules in the required order. */
public class ProductTransformer {

    /**
     * Uppercase name → discount for Electronics → round → recategorize → price range.
     */
    public List<ProductTransformed> transform(List<ProductRaw> input) {
        List<ProductTransformed> out = new ArrayList<>();

        for (ProductRaw r : input) {
            String nameUpper = r.name.toUpperCase();

            boolean isElectronicsOrig = r.category.equals("Electronics")
                    || r.category.equalsIgnoreCase("electronics");

            // 10% discount if original category was Electronics
            BigDecimal finalPrice = r.price;
            if (isElectronicsOrig) {
                finalPrice = r.price.multiply(new BigDecimal("0.90"));
            }

            // round HALF_UP to 2 decimals
            finalPrice = finalPrice.setScale(2, RoundingMode.HALF_UP);

            // recategorize if Electronics and FINAL price > 500.00
            String finalCategory = r.category;
            if (isElectronicsOrig && finalPrice.compareTo(new BigDecimal("500.00")) > 0) {
                finalCategory = "Premium Electronics";
            }

            PriceRange range = PriceRange.fromPrice(finalPrice);

            out.add(new ProductTransformed(r.productId, nameUpper, finalPrice, finalCategory, range));
        }
        return out;
    }
}