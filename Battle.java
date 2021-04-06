package battleship;

public class Battle {
    int shipsSank1Player = 0;
    int shipsSank2Player = 0;
    int player = 1;


    void Game(PlayingField player1, PlayingField player2) {
        while (true) {
            if (shipsSank1Player == 5) {
                System.out.println("Player 2 sank the last ship. You won. Congratulations!");
                break;
            } else if (shipsSank2Player == 5) {
                System.out.println("Player 1 sank the last ship. You won. Congratulations!");
                break;
            } else {
                Main.MoveToAnotherPlayer();
                if (player == 1) {
                    player++;
                    player2.showHiddenField();
                    System.out.println("---------------------");
                    player1.showField();
                    System.out.println("Player 1, it's your turn:");
                    shipsSank2Player += Shot(player2);
                    System.out.println("shipsSank2Player = " + shipsSank2Player);
                } else {
                    player--;
                    player1.showHiddenField();
                    System.out.println("---------------------");
                    player2.showField();
                    System.out.println("Player 2, it's your turn:");
                    shipsSank1Player += Shot(player1);
                    System.out.println("shipsSank1Player = " + shipsSank1Player);
                }

            }
        }
            }

    private int Shot(PlayingField fieldOpponent) {
        int shipsSank = 0;
            String shot = Main.sc.next().toUpperCase();
            int y = shot.charAt(0) - 65;
            int x = Integer.parseInt(shot.replaceAll("\\D", "")) - 1;
            if (y < 10 && x < 10 && y >= 0 && x >= 0) {
                char ch = fieldOpponent.getField(y, x);
                switch (ch) {
                    case 'O':
                        fieldOpponent.setField(y, x, 'X');
                        fieldOpponent.setHiddenField(y, x, 'X');
                        if (SankIsShip(fieldOpponent, y, x)) {
                            System.out.println("You sank a ship!");
                            shipsSank++;
                        } else {
                            System.out.println("You hit a ship!");
                        }
                        break;
                    case '~':
                        fieldOpponent.setField(y, x, 'M');
                        System.out.println("You missed!");
                        fieldOpponent.setHiddenField(y, x, 'M');
                        break;
                    default:
                        System.out.println("Error! You entered the wrong coordinates!");
                        break;
                }
            } else {
                System.out.println("Error! You entered the wrong coordinates!");
            }
            return shipsSank;
    }

    private boolean SankIsShip(PlayingField field, int y, int x) {
        boolean sankIsShip = true;

        int minX = x > 1 ? 2 : x > 0 ? 1 : 0;
        int maxX = x < 8 ? 2 : x < 9 ? 1 : 0;
        int minY = y > 1 ? 2 : y > 0 ? 1 : 0;
        int maxY = y < 8 ? 2 : y < 9 ? 1 : 0;

            for (int i = y - minY; i <= y + maxY ; i++) {
                for (int j = x - minX; j < x + maxX; j++) {
                    if (field.getField(i, j) == 'O') {
                        sankIsShip = false;
                    }
                }
            }
            return sankIsShip;
    }




}
