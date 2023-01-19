class Circle implements Shape {
    private final int radius;

    public Circle(int r) {
        this.radius = r;
    }
    @Override
    public double calculateArea() {
        return Math.PI * this.radius * this.radius;
    }

    @Override
    public double calculatePerimeter() {
        return 2 * Math.PI * this.radius;
    }
}