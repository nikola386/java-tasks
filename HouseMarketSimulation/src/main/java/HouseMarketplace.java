import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Random;

public class HouseMarketplace {
    public static final int AMOUNT_TO_UPDATE_PRICE = 300;
    private static final double INCREASE_PERCENT = 0.15;
    private static final double DECREASE_PERCENT = 0.12;
    private static final int INITIAL_PRICE = 1000;
    private final ArrayList<RealEstate> availableEstates;

    private BigDecimal pricePerSqm;

    public HouseMarketplace() {
        availableEstates = new ArrayList<>();
    }

    public HouseMarketplace(ArrayList<RealEstate> estates) {
        availableEstates = estates;
    }

    public BigDecimal getPricePerSqm() {
        return pricePerSqm;
    }

    public int getAvailableEstates() {
        return availableEstates.size();
    }

    public void addRealEstateForSale(RealEstate estate) {
        availableEstates.add(estate);
        calculateNewPricePerSqm();
    }

    public RealEstate buyRealEstate() {
        int index = new Random().nextInt(availableEstates.size());
        RealEstate estate = availableEstates.remove(index);
        calculateNewPricePerSqm();
        return estate;
    }

    private void calculateNewPricePerSqm() {
        if(availableEstates.size() > AMOUNT_TO_UPDATE_PRICE) {
            pricePerSqm = BigDecimal.valueOf(INITIAL_PRICE).multiply(BigDecimal.valueOf(1 - DECREASE_PERCENT));
        } else {
            pricePerSqm = BigDecimal.valueOf(INITIAL_PRICE).multiply(BigDecimal.valueOf(1 + INCREASE_PERCENT));
        }
    }
}
