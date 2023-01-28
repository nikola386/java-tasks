import com.nikola.logger.Logger;
import com.nikola.logger.LoggerFactory;

public class LoggerTest {
    public void run() {
        Logger logger = LoggerFactory.getLogger(LoggerTest.class);

        logger.severe("Severe message");
        logger.severe("Severe exception", new RuntimeException());

        logger.warning("Warning message");
        logger.warning("Warning exception", new Exception());

        logger.info("Info message");
        logger.config("Config message");
        logger.fine("Fine message");
        logger.finer("Finer message");
        logger.finest("Finest message");
    }
    public static void main(String[] args) {
        LoggerTest t = new LoggerTest();
        t.run();
    }
}