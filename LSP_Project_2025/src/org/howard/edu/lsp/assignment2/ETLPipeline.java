	/**
	 * Name: You Know what  d
	 * ETL pipeline with relative paths.
	 * - reads data/products.csv
	 * - transforms rows (uppercase name, 10% discount for Electronics, recategorize, price range)
	 * - writes data/transformed_products.csv
	 */
	package org.howard.edu.lsp.assignment2;

	import java.io.BufferedReader;
	import java.io.BufferedWriter;
	import java.io.IOException;
	import java.math.BigDecimal;
	import java.math.RoundingMode;
	import java.nio.charset.StandardCharsets;
	import java.nio.file.Files;
	import java.nio.file.Path;
	import java.nio.file.Paths;
	import java.util.ArrayList;
	import java.util.List;

	public class ETLPipeline {

	    private static final String INPUT_PATH  = "data/products.csv";
	    private static final String OUTPUT_PATH = "data/transformed_products.csv";
	    private static final String OUTPUT_HEADER = "ProductID,Name,Price,Category,PriceRange";

	    // ---- bookkeeping for summary ----
	    private static class Counters {
	        int rowsRead = 0;
	        int rowsSkipped = 0;
	    }

	    // ---- simple data holders ----
	    private static class RawRow {
	        int productId;
	        String name;
	        BigDecimal price;   // original price (before discount)
	        String category;

	        RawRow(int id, String name, BigDecimal price, String category) {
	            this.productId = id;
	            this.name = name;
	            this.price = price;
	            this.category = category;
	        }
	    }

	    private static class TransformedRow {
	        int productId;
	        String nameUpper;
	        BigDecimal finalPrice;     // after possible discount, rounded HALF_UP to 2 decimals
	        String finalCategory;      // may become "Premium Electronics"
	        String priceRange;         // Low/Medium/High/Premium

	        TransformedRow(int id, String nameUpper, BigDecimal finalPrice,
	                       String finalCategory, String priceRange) {
	            this.productId = id;
	            this.nameUpper = nameUpper;
	            this.finalPrice = finalPrice;
	            this.finalCategory = finalCategory;
	            this.priceRange = priceRange;
	        }

	        String toCsvLine() {
	            return productId + "," + nameUpper + "," + finalPrice + "," + finalCategory + "," + priceRange;
	        }
	    }

	    public static void main(String[] args) {
	        Path in  = Paths.get(INPUT_PATH);
	        Path out = Paths.get(OUTPUT_PATH);
	        Counters counters = new Counters();

	        // 1) extract
	        List<RawRow> rawRows = extract(in, counters);
	        // if extract failed because file missing, stop early
	        if (rawRows == null) return;

	        // 2) transform
	        List<TransformedRow> transformed = transform(rawRows);

	        // 3) load
	        int written = load(transformed, out);

	        // 4) summary
	        System.out.println("---- run summary ----");
	        System.out.println("rows read:        " + counters.rowsRead);
	        System.out.println("rows transformed: " + written);
	        System.out.println("rows skipped:     " + counters.rowsSkipped);
	        System.out.println("output written to: " + OUTPUT_PATH);
	    }

	    /**
	     * Extract: read input CSV, skip the header, parse rows.
	     * Handles:
	     *   
	     *   - empty file (or header only) -> still return empty list (caller will write header-only output)
	     */
	    private static List<RawRow> extract(Path input, Counters counters) {
	        if (!Files.exists(input)) {
	            System.err.println("error: input file '" + INPUT_PATH + "' does not exist. exiting.");
	            return null; // main will stop
	        }

	        List<RawRow> rows = new ArrayList<>();

	        try (BufferedReader br = Files.newBufferedReader(input, StandardCharsets.UTF_8)) {
	            String line = br.readLine(); // header
	            // if no header at all, treat as empty input
	            if (line == null) return rows;

	            while ((line = br.readLine()) != null) {
	                String trimmed = line.trim();
	                if (trimmed.isEmpty()) continue; // ignore blank lines
	                counters.rowsRead++;

	                String[] parts = trimmed.split(",", -1); // spec says no commas inside fields
	                if (parts.length != 4) {
	                    counters.rowsSkipped++;
	                    continue;
	                }
	                try {
	                    int id = Integer.parseInt(parts[0].trim());
	                    String name = parts[1].trim();
	                    BigDecimal price = new BigDecimal(parts[2].trim());
	                    String category = parts[3].trim();
	                    rows.add(new RawRow(id, name, price, category));
	                } catch (Exception parseErr) {
	                    counters.rowsSkipped++;
	                }
	            }
	        } catch (IOException io) {
	            System.err.println("error reading input: " + io.getMessage());
	            return null; // stop on I/O error
	        }

	        return rows;
	    }

	    /**
	     * Transform order (must match spec):
	     * (1) nal category was "Electronics", set category to "Premium Electronics"
	     * (4) compute PriceRange from FINAL price
	     */
	    private static List<TransformedRow> transform(List<RawRow> rawRows) {
	        List<TransformedRow> out = new ArrayList<>();

	        for (RawRow r : rawRows) {
	            String nameUpper = r.name.toUpperCase();

	            boolean isElectronicsOrig = r.category.equals("Electronics") || r.category.equalsIgnoreCase("electronics");

	            // step (2) discount
	            BigDecimal finalPrice = r.price;
	            if (isElectronicsOrig) {
	                finalPrice = r.price.multiply(new BigDecimal("0.90")); // 10% off
	            }

	            // round HALF_UP to 2 decimals
	            finalPrice = finalPrice.multiply(new BigDecimal("100"))
	                                   .setScale(0, RoundingMode.HALF_UP)
	                                   .divide(new BigDecimal("100"));

	            // step
	            String finalCategory = r.category;
	            if (isElectronicsOrig && finalPrice.compareTo(new BigDecimal("500.00")) > 0) {
	                finalCategory = "Premium Electronics";
	            }

	            // step (4) price range from FINAL price
	            String priceRange = priceRange(finalPrice);

	            out.add(new TransformedRow(r.productId, nameUpper, finalPrice, finalCategory, priceRange));
	        }
	        return out;
	    }

	    // ranges
	    private static String priceRange(BigDecimal p) {
	        BigDecimal ten = new BigDecimal("10.00");
	        BigDecimal hundred = new BigDecimal("100.00");
	        BigDecimal fiveHundred = new BigDecimal("500.00");

	        if (p.compareTo(ten) <= 0) return "Low";
	        if (p.compareTo(ten) > 0 && p.compareTo(hundred) <= 0) return "Medium";
	        if (p.compareTo(hundred) > 0 && p.compareTo(fiveHundred) <= 0) return "High";
	        return "Premium";
	    }

	    /**
	     * Load:
	     * 
	     */
	    private static int load(List<TransformedRow> rows, Path output) {
	        try {
	            if (output.getParent() != null) {
	                Files.createDirectories(output.getParent());
	            }

	            try (BufferedWriter bw = Files.newBufferedWriter(output, StandardCharsets.UTF_8)) {
	                bw.write(OUTPUT_HEADER);
	                bw.newLine();
	                for (TransformedRow tr : rows) {
	                    bw.write(tr.toCsvLine());
	                    bw.newLine();
	                }
	            }
	            return rows.size();
	        } catch (IOException io) {
	            System.err.println("error writing output: " + io.getMessage());
	            return 0;
	        }
	    }
	}
