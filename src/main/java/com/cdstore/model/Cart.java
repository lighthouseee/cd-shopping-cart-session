package com.cdstore.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Cart implements Serializable {

    private List<CartItem> items;

    public Cart() {
        items = new ArrayList<>();
    }

    public List<CartItem> getItems() {
        return items;
    }

    public void addItem(Product product) {

        for (CartItem item : items) {

            if (item.getProduct().getCode().equals(product.getCode())) {
                item.setQuantity(item.getQuantity() + 1);
                return;
            }
        }

        items.add(new CartItem(product, 1));
    }

    public void updateItem(String productCode, int quantity) {

        CartItem foundItem = null;

        for (CartItem item : items) {

            if (item.getProduct().getCode().equals(productCode)) {
                foundItem = item;
                break;
            }
        }

        if (foundItem != null) {

            if (quantity > 0) {
                foundItem.setQuantity(quantity);
            } else {
                items.remove(foundItem);
            }
        }
    }

    public void removeItem(String productCode) {

        items.removeIf(
            item -> item.getProduct().getCode().equals(productCode)
        );
    }

    public double getTotal() {

        double total = 0;

        for (CartItem item : items) {
            total += item.getAmount();
        }

        return total;
    }
}
