import com.github.javafaker.Faker;

import java.util.Random;

public class Seller implements Runnable {
    private final HouseMarketplace market;
    private final Faker faker;

    public Seller(HouseMarketplace market) {
        this.market = market;
        faker = new Faker(new Random(24));
    }

    @Override
    public void run() {
        while (true) {
            synchronized (market) {
                while (market.getAvailableEstates() > 400) {
                    try {
                        market.wait();
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }

                market.addRealEstateForSale(new RealEstate(
                        faker.address().streetAddress(),
                        EstateType.getRandom(),
                        Utils.getRandomInt(20, 500))
                );
//                System.out.println("Market size: " + market.getAvailableEstates());
                System.out.println(Thread.currentThread().getName() + " - New estate added at price per sqm: " + market.getPricePerSqm() + " Available: " + market.getAvailableEstates());

                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

                market.notifyAll();
            }
        }
    }
}
