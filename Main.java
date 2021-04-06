package battleship;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        PlayingField player1 = new PlayingField();
        PlayingField player2 = new PlayingField();
        Battle battle = new Battle();
        System.out.println("Player 1, place your ships on the game field");
        player1.showField();
        player1.PositionShips();
        MoveToAnotherPlayer();
        System.out.println("Player 2, place your ships on the game field");
        player2.showField();
        player2.PositionShips();
        battle.Game(player1, player2);
    }

    static void MoveToAnotherPlayer() {
        System.out.println("Press Enter and pass the move to another player");
        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
//        while (sc.hasNext("\n")) {
//            System.out.println("Press Enter and pass the move to another player");
//            String toAnotherPlayer = sc.next();
//        }
    }
}