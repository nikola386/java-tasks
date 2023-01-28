import java.math.BigDecimal;

public class DiscountsCalculator {
    private final int MIN_AMOUNT = 10;
    private final double MIN_SUM = 1000f;
    private final double PRODUCT_DISCOUNT = 0.1f;
    private final double CART_DISCOUNT = 0.05f;

    public BigDecimal productDiscount(Product p, int amount) {
        if(amount < MIN_AMOUNT) return BigDecimal.valueOf(0);
        return p.getPrice().multiply(BigDecimal.valueOf(amount)).multiply(BigDecimal.valueOf(PRODUCT_DISCOUNT));
    }

    public BigDecimal cartDiscount(ShoppingCart c) {
        BigDecimal cartTotal = c.calculateTotalPrice();
        if (cartTotal.compareTo(BigDecimal.valueOf(MIN_SUM)) > -1) {
            return cartTotal.multiply(BigDecimal.valueOf(CART_DISCOUNT));
        } else {
            return BigDecimal.valueOf(0);
        }
    }
}
