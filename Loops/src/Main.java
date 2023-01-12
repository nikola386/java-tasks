public class Main {
    static final int TO = 99;
    public static void main(String[] args) {
        usingFor(TO);
        usingWhile(TO);
        usingDoWhile(TO);
    }

    private static void usingFor(int to) {
        for(int i = 0; i <= to; i++) {
            System.out.println(i);
        }
    }
    private static void usingWhile(int to) {
        int i = 0;
        while(i <= to) {
            System.out.println(i);
            i++;
        }
    }

    private static void usingDoWhile(int to) {
        int i = 0;
        do {
            System.out.println(i);
            i++;
        }
        while(i <= to);

    }
}