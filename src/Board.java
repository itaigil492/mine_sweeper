import java.util.Scanner;

class Board {

    private Scanner in = new Scanner(System.in);
    private int x, y;
    private Point[][] board;
    private boolean win = false, lose = false;

    /**
     * constructor
     *
     * @param x - the width
     * @param y - the height
     */
    Board(int x, int y) {
        this.x = x;
        this.y = y;
        this.clearPoints();
        this.bmbRandomize();
        this.numPut();

    }

    // gets and sets

    boolean getWin() {
        return this.win;
    }

    boolean getLose() {
        return this.lose;
    }

    // Point generators

    private void clearPoints() {
        this.board = new Point[this.y][this.x];
        for (int y = 0; y < this.y; y++) {
            for (int x = 0; x < this.x; x++) {
                Point p = new Point(0);
                this.board[y][x] = p;
            }
        }
    }

    private void bmbRandomize() { // generates the bombs
        int x, y;
        double BMBPERC = 0.23;
        for (int i = 0; i < (int) ((this.x * this.y) * BMBPERC)+1; i++) { // when i use the bmbNum the bombs just doesn't
            // generate
            x = (int) (Math.random() * this.x);
            y = (int) (Math.random() * this.y);
            if (this.board[y][x].getSmbl() == 0)
                board[y][x].setSmbl(9);
            else
                i--; // if i didn't put a bomb i shouldn't raise i. so instead doing a while loop i
            // decreased i ;);

        }

    }

    private void numPut() { // generates the numbers using the bombs (checks around each point how many
        // bombs are there)

        for (int y = 0; y < this.y; y++) {
            for (int x = 0; x < this.x; x++) {
                int bmbCounter = 0;
                if (this.board[y][x].getSmbl() != 9) {

                    if (x > 0 && y > 0) // left up
                        if (this.board[y - 1][x - 1].getSmbl() == 9)
                            bmbCounter++;

                    if (y > 0) // up
                        if (this.board[y - 1][x].getSmbl() == 9)
                            bmbCounter++;

                    if (x < this.x - 1 && y > 0) // right up

                        if (this.board[y - 1][x + 1].getSmbl() == 9)

                            bmbCounter++;

                    if (x < this.x - 1)// right
                        if (this.board[y][x + 1].getSmbl() == 9)
                            bmbCounter++;

                    if (x < this.x - 1 && y < this.y - 1) // right down
                        if (this.board[y + 1][x + 1].getSmbl() == 9)
                            bmbCounter++;

                    if (y < this.y - 1) // down
                        if (this.board[y + 1][x].getSmbl() == 9)
                            bmbCounter++;

                    if (x > 0 && y < this.y - 1)// left down
                        if (this.board[y + 1][x - 1].getSmbl() == 9)
                            bmbCounter++;

                    if (x > 0) // left
                        if (this.board[y][x - 1].getSmbl() == 9)
                            bmbCounter++;

                    this.board[y][x].setSmbl(bmbCounter);
                }
            }
        }
    }

    // board drawing

    void draw() {
        System.out.print("   ");
        for (int i = 0; i < this.x; i++) {
            if (i < 10)
                System.out.print(i + "  ");
            if (i >= 10)
                System.out.print(i + " ");
        }
        System.out.println();

        for (int y = 0; y < this.y; y++) {
            if (y < 10)
                System.out.print(y + "  ");
            if (y >= 10)
                System.out.print(y + " ");

            for (int x = 0; x < this.x; x++) {
                if (board[y][x].getR()) {
                    if (board[y][x].getSmbl() == 9)
                        System.out.print("@  ");
                    else
                        System.out.print(board[y][x].getSmbl() + "  ");
                } else if (board[y][x].getF())
                    System.out.print("F  ");
                else
                    System.out.print("*  ");
            }
            System.out.println();
        }

    }

    // points effects

    private void explode(Point p) {
        if (p.getSmbl() == 9)
            this.lose();
    }

    private void press2(int x, int y) {
        if (x < this.x && x >= 0 && y < this.y && y >= 0) {
            if (this.board[y][x].getSmbl() == 9 ) this.explode(this.board[y][x]);
            else if (this.board[y][x].getSmbl()>0) this.board[y][x].reveal();
            else {

                this.board[y][x].reveal();

                if (x > 0 && y > 0 && !this.board[y-1][x-1].getR()) // left up
                    this.press2(x-1,y-1);

                if (y > 0 && !this.board[y-1][x].getR()) // up
                    this.press2(x,y-1);

                if (x < this.x - 1 && y > 0 && !this.board[y-1][x+1].getR()) // right up

                    this.press2(x+1,y-1);

                if (x < this.x - 1 && !this.board[y][x+1].getR())// right
                    this.press2(x+1,y);

                if (x < this.x - 1 && y < this.y - 1 && !this.board[y+1][x+1].getR()) // right down
                    this.press2(x+1,y+1);

                if (y < this.y - 1 && !this.board[y+1][x].getR()) // down
                    this.press2(x,y+1);

                if (x > 0 && y < this.y - 1 && !this.board[y+1][x-1].getR())// left down
                    this.press2(x-1,y+1);

                if (x > 0 && !this.board[y][x-1].getR()) // left
                    this.press2(x-1,y);


            }
        }



    }
    void press() {

        System.out.println();
        System.out.print("x:");
        int x = in.nextInt();
        System.out.print("y:");
        int y = in.nextInt();
        if (x < this.x && x >= 0 && y < this.y && y >= 0) {
            press2(x, y);
        }

        else if (x>=100 && y>=100 && x<this.x+100 && y<this.y+100) {
            board[y-100][x-100].flagIt();
        }

        else
            System.out.println("there is no point like this");

        for (int i = 0; i < this.x + 1; i++) {
            System.out.print("__");
        }
        System.out.println();
        System.out.println();
    }

    // others
    void revealAll() {
        for (int y = 0; y < this.y; y++) {
            for (int x = 0; x < this.x; x++) {
                this.board[y][x].reveal();

            }
        }

    }

    private void win() {
        this.win = true;
    }

    private void lose() {
        this.lose = true;
    }

    // game end

    void checkWin() {
        boolean shouldWin = true;
        for (int y = 0; y < this.y; y++) {
            for (int x = 0; x < this.x; x++) {
                if (!(this.board[y][x].getR()) && this.board[y][x].getSmbl() != 9)
                    shouldWin = false;
            }
        }
        if (shouldWin)
            this.win();

    }

}