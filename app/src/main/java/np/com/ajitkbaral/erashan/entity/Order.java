package np.com.ajitkbaral.erashan.entity;

import java.io.Serializable;

public class Order implements Serializable {

    private int id;
    private Cart cart;
    private String message;

    public Order() {
    }

    public Order(int id, Cart cart, String message) {
        this.id = id;
        this.cart = cart;
        this.message = message;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
