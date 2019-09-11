public class Point {

    private int x;
    private int y;
    private int smbl;
    private boolean revealed = false;
    private boolean flaged = false;

    public Point (int x, int y, int smbl) {
        this.x = x;
        this.y = y;
        this.smbl = smbl;
    }

    // Gets & Sets

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getSmbl() {
        return smbl;
    }

    public boolean getR() {
        return revealed;
    }

    public boolean getF() {
        return flaged;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setSmbl(int smbl) {
        this.smbl = smbl;
    }

    public void setRevealed(boolean b) {
        this.revealed = b;
    }

    public void setFlaged(boolean b) {
        this.revealed = b;
    }

    public void reveal() {
        this.revealed = true;
    }

    public void flagIt() {
        this.flaged = !flaged;
    }

}
