import java.math.BigDecimal;

public class ShoppingCartTest {
    public static void main(String[] args) {
        Product p1 = new Product(1,"Product 1", BigDecimal.valueOf(15.33));
        Product p2 = new Product(2,"Product 2", BigDecimal.valueOf(56));
        Product p3 = new Product(3,"Product 3", BigDecimal.valueOf(145.67));
        Product p4 = new Product(4,"Product 4", BigDecimal.valueOf(1000));

        ShoppingCart cart = new ShoppingCart();
        cart.addProduct(p1);
        cart.addProduct(p1, 2);
        cart.addProduct(p2);
        cart.addProduct(p3, 4);
        cart.addProduct(p4, 10);
        cart.printReceipt();

        try {
            cart.removeProduct(p3, 2);
            cart.removeProduct(p2);
            cart.printReceipt();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}