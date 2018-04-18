package sample;

public class NewPointEvent {
    //wlasne zdarzenie na znalezienie nowego punktu

    private double x;
    private double y;
    private boolean isInside;
    private int counter;

    public NewPointEvent(double x, double y, boolean isInside, int counter) {
        this.x = x;
        this.y = y;
        this.isInside = isInside;
        this.counter = counter;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public boolean isInside() {
        return isInside;
    }

    public int getCounter() {
        return counter;
    }
}
