package np.com.ajitkbaral.erashan.entity;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

public class Cart implements Serializable {
    private int id;
    private HashMap<String, Product> productList;
    private double totalPrice;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public HashMap<String, Product> getProductList() {
        return productList;
    }

    public void setProductList(HashMap<String, Product> productList) {
        this.productList = productList;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "id=" + id +
                ", productList=" + productList +
                ", totalPrice=" + totalPrice +
                '}';
    }
}
