public class Main {
    public static void main(String[] args) {
        for(int i = 1; i <= 7; i++){
            String day = convertNumberToDay(i);
            System.out.println(day);
        }

        String day = convertNumberToDay(0);
        System.out.println(day);

        day = convertNumberToDay(-1);
        System.out.println(day);

        day = convertNumberToDay(10);
        System.out.println(day);
    }

    private static String convertNumberToDay(int day) {
        switch(day) {
            case 1: {
                return "Monday";
            }
            case 2: {
                return "Tuesday";
            }
            case 3: {
                return "Wednesday";
            }
            case 4: {
                return "Thursday";
            }
            case 5: {
                return "Friday";
            }
            case 6: {
                return "Saturday";
            }
            case 7: {
                return "Sunday";
            }
            default: {
                return "Invalid number";
            }
        }
    }
}