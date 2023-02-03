public class Buyer implements Runnable {
    private final HouseMarketplace market;

    public Buyer(HouseMarketplace market) {
        this.market = market;
    }

    @Override
    public void run() {
        while (true) {
            synchronized (market) {
                while (market.getAvailableEstates() <= Utils.getRandomInt(1, 200)) { //market.getAvailableEstates() == 0
                    try {
                        market.wait();
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }

                RealEstate estate = market.buyRealEstate();
                System.out.println(Thread.currentThread().getName() + " - Estate " + estate + " sold at price per sqm: " + market.getPricePerSqm());
                System.out.println("Market size: " + market.getAvailableEstates());
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
