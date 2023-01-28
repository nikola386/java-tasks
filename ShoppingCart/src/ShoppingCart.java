
import java.math.BigDecimal;
import java.util.HashMap;

public class ShoppingCart {
    private final HashMap<Product, Integer> items;

    public ShoppingCart() {
        this.items = new HashMap<>();
    }

    public void addProduct(Product p) {
        addProduct(p, 1);
    }

    public void addProduct(Product p, int amount) {
        Integer existingProduct = this.items.get(p);
        if(existingProduct != null) {
            items.put(p, existingProduct + amount);
        } else {
            items.put(p, amount);
        }
    }

    public void removeProduct(Product p) throws Exception {
        removeProduct(p, 1);
    }

    public void removeProduct(Product p, int amount) throws Exception {
        Integer existingProduct = this.items.get(p);
        if(existingProduct != null) {
            int newAmount = existingProduct - amount;
            if(newAmount > 0) {
                items.put(p, newAmount);
            } else {
                items.remove(p);
            }
        } else {
           throw new Exception("Product already removed");
        }
    }

    public BigDecimal calculateDiscount() {
        final BigDecimal[] totalDiscount = {BigDecimal.valueOf(0)};
        DiscountsCalculator discountsCalculator = new DiscountsCalculator();

        items.forEach((product, amount) ->
                totalDiscount[0] = totalDiscount[0].add(discountsCalculator.productDiscount(product, amount)));

        totalDiscount[0] = totalDiscount[0].add(discountsCalculator.cartDiscount(this));

        return totalDiscount[0];
    }

    public BigDecimal calculateTotalPrice() {
        final BigDecimal[] total = {BigDecimal.valueOf(0)};

        items.forEach((product, amount) ->
                total[0] = total[0].add(product.getPrice().multiply(BigDecimal.valueOf(amount))));

        return total[0];
    }

    public void printReceipt() {
        System.out.printf("product - amount x price: total%n");
        items.forEach((product, amount) -> {
            BigDecimal price = product.getPrice();
            System.out.printf("%s - %d x %#1.2f: %#1.2f%n", product.getName(), amount, price, price.multiply(BigDecimal.valueOf(amount)));
        });

        BigDecimal total = calculateTotalPrice();
        System.out.printf("before discount: %#1.2f%n", total);

        BigDecimal discount = calculateDiscount();
        System.out.printf("discount: %#1.2f%n", discount);

        System.out.printf("total: %#1.2f%n", total.subtract(discount));
    }
}
