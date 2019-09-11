import java.util.Scanner;

public interface Game {

    Scanner in = new Scanner(System.in);

    public static void load() {
        System.out.println();
    }

    public static Board create() {
        System.out.println("Enter the size of the board");
        System.out.print("x:");
        int x = in.nextInt();
        System.out.print("y:");
        int y = in.nextInt();
        Board b = new Board(x, y);
        return b;
    }

    public static void update(Board b) {
        b.draw();
        b.press();
        b.checkWin();

    }

    public static void play(){
        Game.load();
        Board b = Game.create();
        while (!(b.getLose() || b.getWin())) {
            Game.update(b);
        }
        if (b.getLose()) {
            System.out.println("\nYou lost the game \nNext time try more cyber\n");
            b.revealAll();
            System.out.println("The revealed board is:");
            b.draw();
        }
        if (b.getWin()) {
            System.out.println("\nYou are The HackerMan\n");
            b.revealAll();
            System.out.println("The revealed board is:");
            b.draw();
        }

    }

}