package org.howard.edu.lsp.assignment3;

import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

/**
 * Entry point for Assignment 3: object-oriented ETL.
 * Keeps the same external behavior as Assignment 2.
 */
public class ETLPipelineApp {
    // relative paths (same as A2)
    public static final String INPUT_PATH  = "data/products.csv";
    public static final String OUTPUT_PATH = "data/transformed_products.csv";

    public static void main(String[] args) {
        Path in  = Paths.get(INPUT_PATH);
        Path out = Paths.get(OUTPUT_PATH);

        Counters counters = new Counters();
        CsvExtractor extractor = new CsvExtractor(StandardCharsets.UTF_8);
        ProductTransformer transformer = new ProductTransformer();
        CsvLoader loader = new CsvLoader(StandardCharsets.UTF_8);

        // Extract
        List<ProductRaw> rawRows = extractor.extract(in, counters);
        if (rawRows == null) {
            // Missing file or unrecoverable I/O → extractor prints error; exit.
            return;
        }

        // Transform
        List<ProductTransformed> transformed = transformer.transform(rawRows);

        // Load
        int written = loader.load(transformed, out);

        // Summary (same fields as A2)
        System.out.println("---- run summary ----");
        System.out.println("rows read:        " + counters.rowsRead);
        System.out.println("rows transformed: " + written);
        System.out.println("rows skipped:     " + counters.rowsSkipped);
        System.out.println("output written to: " + OUTPUT_PATH);
    }
}
