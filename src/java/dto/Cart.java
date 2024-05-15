/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

/**
 *
 * @author PC
 */
public class Cart {
    Product product;
    int cartquantity;

    public Cart(Product product, int quantity) {
        this.product = product;
        this.cartquantity = cartquantity;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getCartquantity() {
        return cartquantity;
    }

    public void setCartquantity(int cartquantity) {
        this.cartquantity = cartquantity;
    }
    
}
