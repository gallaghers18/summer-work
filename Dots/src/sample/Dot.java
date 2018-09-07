package sample;

public class Dot {
    private double x;
    private double y;
    private boolean isStuck;

    public Dot(int x, int y) {
        this.x = x;
        this.y = y;
        this.isStuck = false;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public boolean isStuck() {
        return this.isStuck;
    }

    public void hasGottenStuck() {
        this.isStuck = true;
    }
}
