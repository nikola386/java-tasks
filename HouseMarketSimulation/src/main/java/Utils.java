import java.util.Random;

public class Utils {
    public static int getRandomInt(int min, int max) {
        Random random = new Random();
        return random.nextInt(max - min) + min;
    }
}
