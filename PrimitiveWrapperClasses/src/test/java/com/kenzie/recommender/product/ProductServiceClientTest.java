package com.kenzie.recommender.product;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class ProductServiceClientTest {
    private File kidsProducts = new File(ClassLoader.getSystemResource("kidsproducts.csv").getPath());
    private ProductServiceClient productServiceClient;

    @BeforeEach
    public void setUp() {
        productServiceClient = new ProductServiceClient(kidsProducts);
    }

    @Test
    public void get_nonExistentProduct_returnsNull() {
        // GIVEN
        long nonExistentProductId = 89;

        // WHEN
        Product product = productServiceClient.get(nonExistentProductId);

        // THEN
        assertNull(product, "Null should be returned when productId does not exist");
    }

    @Test
    public void get_productWithoutRelated_returnsDeserializedProduct() {
        // GIVEN
        long productId = 11;
        String expectedName = "American Girl WellieWishers Kendall Doll";
        BigDecimal expectedPrice = new BigDecimal("60.00");

        // WHEN
        Product product = productServiceClient.get(productId);

        // THEN
        assertEquals(productId, product.getId(), "Expected productId " + productId);
        assertEquals(expectedName, product.getName(), "Expected name " + expectedName);
        assertEquals(expectedPrice, product.getPrice(), "Expected price " + expectedPrice);
        assertNull(product.getMostSimilarId(), "No product is similar to this product.");
    }

    @Test
    public void get_productWithRelated_returnsDeserializedProduct() {
        // GIVEN
        long productId = 4;
        String expectedName = "UNO Card Game";
        BigDecimal expectedPrice = new BigDecimal("5.44");
        long expectedMostSimilarId = 3;

        // WHEN
        Product product = productServiceClient.get(productId);

        // THEN
        assertEquals(productId, product.getId(), "Expected productId " + productId);
        assertEquals(expectedName, product.getName(), "Expected name " + expectedName);
        assertEquals(expectedPrice, product.getPrice(), "Expected price " + expectedPrice);
        assertEquals(expectedMostSimilarId, product.getMostSimilarId(),
                     "Expected most similar ID " + expectedMostSimilarId);
    }
}
