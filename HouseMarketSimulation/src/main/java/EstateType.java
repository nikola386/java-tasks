import java.util.Random;

public enum EstateType {
    Residential,
    Commercial,
    Land;

    private static final Random PRNG = new Random();

    public static EstateType getRandom()  {
        EstateType[] directions = values();
        return directions[PRNG.nextInt(directions.length)];
    }
}
