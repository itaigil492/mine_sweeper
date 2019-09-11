import org.jetbrains.annotations.Contract;

class Point {

    private int smbl;
    private boolean revealed = false;
    private boolean flaged = false;

    @Contract(pure = true)
    Point(int smbl) {
        this.smbl = smbl;
    }

    // Gets & Sets

    int getSmbl() {
        return smbl;
    }

    boolean getR() {
        return revealed;
    }

    boolean getF() {
        return flaged;
    }

    void setSmbl(int smbl) {
        this.smbl = smbl;
    }

    void reveal() {
        this.revealed = true;
    }

    void flagIt() {
        this.flaged = !flaged;
    }

}
