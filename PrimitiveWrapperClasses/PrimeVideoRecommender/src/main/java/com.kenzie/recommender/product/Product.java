package main.java.recommender.product;

import java.math.BigDecimal;

/**
 * Product purchased on Amazon.
 */
public class Product {

    /**
     * The unique identifying number of this product.
     */
    private long id;
    /**
     * The human readable name of the product.
     */
    private String name;
    /**
     * The price in USD of the product.
     */
    private BigDecimal price;
    /**
     * The identifier of the most similar product on Amazon to this product.
     */
    private Long mostSimilarProduct;

    private Product() {
    }

    /**
     * Builder builder.
     *
     * @return the builder
     */
    public static Builder builder() {
        return new Builder();
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public Long getMostSimilarId() {
        return mostSimilarProduct;
    }

    /**
     * Builder for constructing a Product.
     */
    public static class Builder {
        private long id;
        private String name;
        private BigDecimal price;
        private Long mostSimilarProduct;

        /**
         * With id builder.
         *
         * @param pId the id
         * @return the builder
         */
        public Builder withId(long pId) {
            this.id = pId;
            return this;
        }

        /**
         * With name builder.
         *
         * @param pName the name
         * @return the builder
         */
        public Builder withName(String pName) {
            this.name = pName;
            return this;
        }

        /**
         * With price builder.
         *
         * @param pPrice the price
         * @return the builder
         */
        public Builder withPrice(BigDecimal pPrice) {
            this.price = pPrice;
            return this;
        }

        /**
         * With most similar product builder.
         *
         * @param pMostSimilarProduct the most similar product
         * @return the builder
         */
        public Builder withMostSimilarProduct(Long pMostSimilarProduct) {
            this.mostSimilarProduct = pMostSimilarProduct;
            return this;
        }

        /**
         * Build product.
         *
         * @return the product
         */
        public Product build() {
            Product product = new Product();
            product.id = id;
            product.name = name;
            product.price = price;
            product.mostSimilarProduct = mostSimilarProduct;
            return product;
        }
    }
}
