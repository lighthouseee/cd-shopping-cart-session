package com.cdstore.data;

import com.cdstore.model.Product;

import java.util.HashMap;
import java.util.Map;

public class ProductDB {

    private static final Map<String, Product> products = new HashMap<>();

    static {
        products.put(
            "8601",
            new Product(
                "8601",
                "86 (the band) - True Life Songs and Pictures",
                14.95
            )
        );

        products.put(
            "pf01",
            new Product(
                "pf01",
                "Paddlefoot - The first CD",
                12.95
            )
        );

        products.put(
            "pf02",
            new Product(
                "pf02",
                "Paddlefoot - The second CD",
                14.95
            )
        );
    }

    public static Product getProduct(String productCode) {
        return products.get(productCode);
    }
}