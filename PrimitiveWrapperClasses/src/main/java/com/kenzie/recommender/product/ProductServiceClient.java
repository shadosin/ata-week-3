package com.kenzie.recommender.product;

import com.kenzie.recommender.ReadOnlyDao;

import com.kenzie.recommender.movie.StorageException;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.charset.Charset;
import java.util.List;

/**
 * Lookup a Product by its id.
 */
public class ProductServiceClient implements ReadOnlyDao<Long, Product> {
    private File productFile;

    /**
     * Instantiates a new Product service client.
     *
     * @param productFile the product file
     */
    public ProductServiceClient(File productFile) {
        this.productFile = productFile;
    }

    /**
     * Get a specific Product by its id.
     *
     * @param key The unique key to lookup a Prouct based on.
     * @return The Product with id, key.
     */
    @Override
    public Product get(Long key) {
        String[] lines = readProductFiles();
        for (String productLine : lines) {
            String[] productData = productLine.split(",");

            long id = Long.parseLong(productData[0].trim());
            if (key.equals(id)) {

                String name = productData[1].trim();
                BigDecimal price = new BigDecimal(productData[2].trim());
                Long mostSimilarProduct = null;
                if (productData.length > 3) {
                    mostSimilarProduct = Long.parseLong(productData[3].trim());
                }

                return Product.builder()
                              .withId(id)
                              .withName(name)
                              .withPrice(price)
                              .withMostSimilarProduct(mostSimilarProduct)
                              .build();
            }
        }

        return null;
    }

    private String[] readProductFiles() {
        try {
            List<String> lines = FileUtils.readLines(productFile, Charset.defaultCharset());
            return lines.toArray(new String[lines.size()]);
        } catch (IOException e) {
            throw new StorageException("Unable to access product data.", e);
        }
    }
}
