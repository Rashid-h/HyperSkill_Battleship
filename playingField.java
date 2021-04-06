package battleship;

import java.util.Arrays;

class PlayingField {
    private final char[][] field;
    private final char[][] hiddenField;
    private final String[] ships = {"Aircraft Carrier (5 cells)", "Battleship (4 cells)", "Submarine (3 cells)", "Cruiser (3 cells)", "Destroyer (2 cells)"};
    private final int[] sizeShips = {5, 4 ,3 , 3, 2};
    private int startY = 0;
    private int startX = 0;
    private int endY = 0;
    private int endX = 0;

    public PlayingField() {
        field = new char[10][10];
        hiddenField = new char[10][10];
        creatingField();
    }

    public void setField(int y, int x, char site) {
        field[y][x] = site;
    }

    public void setHiddenField(int y, int x, char site) {
        hiddenField[y][x] = site;
    }

    public char getField(int y, int x) {
        return field[y][x];
    }

    void PositionShips() {
        int s = 0;
        boolean bol = true;
        while (s != ships.length) {
            if (bol) {
                System.out.printf("Enter the coordinates of the %s:\n", ships[s]);
            }

            StringBuilder startShip =  new StringBuilder(Main.sc.next().toUpperCase());
            StringBuilder endShip = new StringBuilder(Main.sc.next().toUpperCase());
            int size = CoordinatesShips(startShip, endShip);
            bol = false;

            if (startShip.charAt(0) == endShip.charAt(0) || startShip.charAt(1) == endShip.charAt(1)) {
                if (sizeShips[s] - size == 0) {
                    if (isPlaceFree()) {
                        ShipsInField();
                        showField();
                        s++;
                        bol = true;
                    } else {
                        System.out.println("Error! You placed it too close to another one. Try again:");
                    }
                } else {
                    System.out.printf("Error! Wrong length of the %s! Try again:\n", ships[s]);
                }
            } else {
                System.out.println("Error! Wrong ship location! Try again:");
            }
        }

    }

    private int CoordinatesShips(StringBuilder start, StringBuilder end) {
        startY = start.charAt(0) - 65;
        endY = end.charAt(0) - 65;
        endX = Integer.parseInt(end.toString().replaceAll("\\D",""));
        startX = Integer.parseInt(start.toString().replaceAll("\\D",""));
        return (startX >= endX ? startX - endX : endX - startX) + (startY >= endY ? startY - endY : endY - startY) + 1;

    }

    private boolean isPlaceFree() {
        boolean isFree = true;
        int xS = startX > 1 ? 1 : 0;
        int xE = endX < 9 ? 1 : 0;
        int yS = startY > 0 ? 1 : 0;
        int yE = endY < 9 ? 1 : 0;
        if (startX == endX) {
            if (startY < endY) {
                for (int i = startY - yS; i <= endY + yE; i++) {
                    if (field[i][startX - xS] == 'O' ||
                            field[i][startX] == 'O' ||
                            field[i][startX + xE] == 'O') {
                        isFree = false;
                    }
                }
            } else {
                yS = startY < 9 ? 1 : 0;
                yE = endY > 0 ? 1 : 0;
                for (int i = endY - yE; i <= startY + yS; i++) {
                    if (field[i][startX - xS] == 'O' ||
                            field[i][startX] == 'O' ||
                            field[i][startX + xE] == 'O') {
                        isFree = false;
                    }
                }
            }
        } else if (startY == endY) {
            if (startX < endX) {
                for (int i = startX - xS - 1; i <= endX + xE - 1; i++) {
                    if (field[startY - yS][i] == 'O' ||
                            field[startY][i] == 'O' ||
                            field[startY + yE][i] == 'O') {
                        isFree = false;
                    }
                }
            } else {
                xE = endX > 0 ? 1 : 0;
                xS = startX < 9 ? 1 : 0;
                for (int i = endX - xE - 1; i <= startX + xS - 1; i++) {
                    if (field[startY - yS][i] == 'O' ||
                            field[startY][i] == 'O' ||
                            field[startY + yE][i] == 'O') {
                        isFree = false;
                    }
                }
            }
        }
        return isFree;
    }

    private void ShipsInField() {
            if (startX == endX) {
                if (startY < endY) {
                    for (int i = startY; i <= endY; i++) {
                        field[i][startX - 1] = 'O';
                    }
                } else {
                    for (int i = endY; i <= startY; i++) {
                        field[i][startX - 1] = 'O';
                    }
                }
            } else {
                if (startX < endX) {
                    Arrays.fill(field[startY], startX - 1,endX, 'O');
                } else {
                    Arrays.fill(field[startY], endX - 1,startX, 'O');
                }
            }
    }

    private void creatingField() {
        for (char[] chars : field) {
            Arrays.fill(chars, '~');
        }
        for (char[] chars : hiddenField) {
            Arrays.fill(chars, '~');
        }
    }

    void showField() {
        char ch = 'A';
        System.out.println("\n  1 2 3 4 5 6 7 8 9 10");
        for (char[] chars : field) {
            System.out.print(ch + " ");
            for (char aChar : chars) {
                System.out.print(aChar + " ");
            }
            System.out.println();
            ch++;
        }
        System.out.println();
    }

    void showHiddenField() {
        char ch = 'A';
        System.out.println("\n  1 2 3 4 5 6 7 8 9 10");
        for (char[] chars : hiddenField) {
            System.out.print(ch + " ");
            for (char aChar : chars) {
                System.out.print(aChar + " ");
            }
            System.out.println();
            ch++;
        }
        System.out.println();
    }

}
