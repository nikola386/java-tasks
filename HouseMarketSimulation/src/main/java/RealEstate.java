public class RealEstate {
    private final String address;
    private final EstateType type;
    private final int sqm;

    public RealEstate(String address, EstateType type, int sqm) {
        this.address = address;
        this.type = type;
        this.sqm = sqm;
    }

    @Override
    public String toString() {
        return String.format("%s - %s - %s%n", type, address, sqm);
    }
}
