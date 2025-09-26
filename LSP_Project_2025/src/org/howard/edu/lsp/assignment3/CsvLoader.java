package org.howard.edu.lsp.assignment3;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

/** Writes transformed rows to CSV with required header and order. */
public class CsvLoader {
    public static final String OUTPUT_HEADER = "ProductID,Name,Price,Category,PriceRange";
    private final Charset charset;

    public CsvLoader(Charset charset) {
        this.charset = charset;
    }

    /** @return number of data rows written (excludes header). */
    public int load(List<ProductTransformed> rows, Path output) {
        try {
            if (output.getParent() != null) {
                Files.createDirectories(output.getParent());
            }

            try (BufferedWriter bw = Files.newBufferedWriter(output, charset)) {
                bw.write(OUTPUT_HEADER);
                bw.newLine();
                for (ProductTransformed tr : rows) {
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
