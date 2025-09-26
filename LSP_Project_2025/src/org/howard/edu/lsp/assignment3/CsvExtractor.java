package org.howard.edu.lsp.assignment3;

import java.io.BufferedReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

/**
 * Reads products from CSV using the assignment's guarantees
 * (comma delimiter, no quoted/embedded commas).
 */
public class CsvExtractor {
    private final Charset charset;

    public CsvExtractor(Charset charset) {
        this.charset = charset;
    }

    /**
     * @return list of parsed rows; empty list for empty-input case; null if file missing or
     *         unrecoverable I/O (caller exits gracefully).
     */
    public List<ProductRaw> extract(Path input, Counters counters) {
        if (!Files.exists(input)) {
            System.err.println("error: input file '" + input + "' does not exist. exiting.");
            return null;
        }

        List<ProductRaw> rows = new ArrayList<>();

        try (BufferedReader br = Files.newBufferedReader(input, charset)) {
            String line = br.readLine(); // header
            if (line == null) return rows; // empty file → still write header later

            while ((line = br.readLine()) != null) {
                String trimmed = line.trim();
                if (trimmed.isEmpty()) continue;

                String[] parts = trimmed.split(",", -1);
                if (parts.length != 4) {
                    counters.rowsSkipped++;
                    continue;
                }

                try {
                    int id = Integer.parseInt(parts[0].trim());
                    String name = parts[1].trim();
                    BigDecimal price = new BigDecimal(parts[2].trim());
                    String category = parts[3].trim();
                    rows.add(new ProductRaw(id, name, price, category));
                    counters.rowsRead++;
                } catch (Exception parseErr) {
                    counters.rowsSkipped++;
                }
            }
        } catch (IOException io) {
            System.err.println("error reading input: " + io.getMessage());
            return null;
        }
        return rows;
    }
}