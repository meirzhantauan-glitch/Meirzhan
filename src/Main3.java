import java.util.ArrayList;
import java.util.List;

// Product
class Product implements Cloneable {
    String name;
    double price;

    public Product(String name, double price) {
        this.name = name; this.price = price;
    }

    @Override
    public Product clone() {
        return new Product(this.name, this.price);
    }
}

// Order
class Order implements Cloneable {
    List<Product> products = new ArrayList<>();
    double deliveryCost;
    double discount;
    String paymentMethod;

    public void addProduct(Product p) { products.add(p); }

    @Override
    public Order clone() {
        Order clone = new Order();
        for (Product p : this.products) {
            clone.products.add(p.clone());
        }
        clone.deliveryCost = this.deliveryCost;
        clone.discount = this.discount;
        clone.paymentMethod = this.paymentMethod;
        return clone;
    }

    public void showOrder() {
        System.out.println("Order:");
        for (Product p : products) {
            System.out.println("- " + p.name + " : " + p.price);
        }
        System.out.println("Delivery: " + deliveryCost);
        System.out.println("Discount: " + discount);
        System.out.println("Payment: " + paymentMethod);
    }
}

public class Main3 {
    public static void main(String[] args) {
        Order baseOrder = new Order();
        baseOrder.addProduct(new Product("Телефон", 150000));
        baseOrder.addProduct(new Product("Құлаққап", 20000));
        baseOrder.deliveryCost = 1000;
        baseOrder.discount = 0.05;
        baseOrder.paymentMethod = "Карта";

        // Clone order
        Order order2 = baseOrder.clone();
        order2.paymentMethod = "Қолма-қол";

        baseOrder.showOrder();
        System.out.println("\nКлон:");
        order2.showOrder();
    }
}
