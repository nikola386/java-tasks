import com.github.javafaker.Faker;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SimulateHouseMarketplace {
    public static void main(String[] args) {
//        startUsingThreads()
        startUsingExecutorService();
    }

    private static void startUsingThreads() {
        System.out.println("Creating initial estates");
        HouseMarketplace market = new HouseMarketplace();

        Thread seller = new Thread(new Seller(market), "seller_1");
        Thread seller2 = new Thread(new Seller(market), "seller_2");
        Thread seller3 = new Thread(new Seller(market), "seller_3");
        Thread buyer = new Thread(new Buyer(market), "buyer_1");
        Thread buyer2 = new Thread(new Buyer(market), "buyer_2");
        Thread buyer3 = new Thread(new Buyer(market), "buyer_3");

        seller.start();
        seller2.start();
        seller3.start();
        buyer.start();
        buyer2.start();
        buyer3.start();
    }

    private static void startUsingExecutorService() {
        System.out.println("Creating initial estates");
        HouseMarketplace market = new HouseMarketplace();

        try(ExecutorService executorService = Executors.newFixedThreadPool(10)) {
            executorService.execute(new Seller(market));
            executorService.execute(new Buyer(market));

            executorService.shutdown();
        }
    }

    private static ArrayList<RealEstate> createInitialEstate(int amount) {
        ArrayList<RealEstate> estates = new ArrayList<>();

        Faker faker = new Faker(new Random(24));

        for (int i = 0; i < amount; i++) {
            estates.add(new RealEstate(
                    faker.address().streetAddress(),
                    EstateType.getRandom(),
                    Utils.getRandomInt(20, 500))
            );
        }

        return estates;
    }
}