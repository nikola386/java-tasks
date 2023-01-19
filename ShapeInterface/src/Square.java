class Square implements Shape {
    private final int a;
    public Square(int a) {
        this.a = a;
    }

    @Override
    public double calculateArea() {
        return a * a;
    }

    @Override
    public double calculatePerimeter() {
        return 4 * a;
    }
}