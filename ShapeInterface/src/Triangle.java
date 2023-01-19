class Triangle implements Shape {
    private final int a;
    private final int b;
    private final int c;

    public Triangle(int a, int b, int c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    @Override
    public double calculateArea() {
        int s = (this.a + this.b + this.c)/2;
        return Math.sqrt(s*(s-this.a)*s*(s-this.b)*s*(s-this.c));

    }

    @Override
    public double calculatePerimeter() {
        return this.a + this.b + this.c;
    }
}