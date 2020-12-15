package battleship;

public class Board {
    String alphabet = "ABCDEFGHIJ";
    String[] number = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};

    Cell[][] board;

    // to initialize the whole board
    public Board() {
        board = new Cell[11][11];
        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 11; j++) {
                board[i][j] = new Cell();
                if (i == 0 && j != 0) {
                    board[i][j].setValue(number[j - 1]);
                    board[i][j].setFog(number[j - 1]);
//                    board[i][j].setMark(true);
                } else if (i != 0 && j == 0) {
                    board[i][j].setValue(alphabet.split("")[i - 1]);
                    board[i][j].setFog(alphabet.split("")[i - 1]);
//                    board[i][j].setMark(true);
                }
            }
        }
        board[0][0].setValue(" ");
        board[0][0].setFog(" ");
        initializeBoard();
    }

    // to print the board
    public void printBoard() {
        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 11; j++) {
                System.out.print(board[i][j].getValue());
                System.out.print(" ");
            }
            System.out.println();
        }
    }

    public void printFogOfWarBoard() {
        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 11; j++) {
                System.out.print(board[i][j].getFog());
                System.out.print(" ");
            }
            System.out.println();
        }
    }

    // Give names to each cell in the board eg. A1,A2
    public void initializeBoard() {
        for (int i = 1; i < 11; i++) {
            for (int j = 1; j < 11; j++) {
                board[i][j].setName(board[i][0].getValue().concat(board[0][j].getValue()));
            }
        }
    }

    public Cell getCell(String name) {
        for (int i = 1; i < 11; i++) {
            for (int j = 1; j < 11; j++) {
                if (board[i][j].getName().equals(name)) {
                    return board[i][j];
                }
            }
        }
        return null;
    }

    // Get cell row number based on the letter
    public int getCellRow(char letter) {
        for (int i = 1; i < 11; i++) {
            if (board[i][0].getValue().equals(String.valueOf(letter))) {
                return i;
            }
        }
        return 0;
    }

    // Check for close ships around ship being placed, returns true if there are no surrounding ships
    public boolean checkCloseShip(char startLetter, String startNumber, char endLetter, String endNumber) {
        int temp = 0;
        int startRow = getCellRow(startLetter);
        int endRow = getCellRow(endLetter);
        int startCol = Integer.parseInt(startNumber);
        int endCol = Integer.parseInt(endNumber);
        if (startRow > endRow) {
            temp = startRow;
            startRow = endRow;
            endRow = temp;
        }

        if (startCol > endCol) {
            temp = startCol;
            startCol = endCol;
            endCol = temp;
        }


        for (int i = Math.max(1, startRow - 1); i <= Math.min(endRow + 1, 10); i++) {
            for (int j = Math.max(1, startCol - 1); j <= Math.min(endCol + 1, 10); j++) {
                if (board[i][j].isMark()) {
                    return false;
                }
            }
        }
        return true;
    }

    // Check if ship has fully sunk, returns true if ship has fully sunk
    public boolean checkSunkShip(String name) {
        int row = getCellRow(name.charAt(0));
        int col = Integer.parseInt(name.split("(?<=\\D)(?=\\d)")[1]);
        for (int i = Math.max(1, row - 1); i <= Math.min(row + 1, 10); i++) {
            for (int j = Math.max(1, col - 1); j <= Math.min(col + 1, 10); j++) {
                if (!board[i][j].isHit()) {
                    return false;
                }
            }
        }
        return true;
    }

    // Check if game is finished
    public boolean finishGame() {
        for (Cell[] cells : board
        ) {
            for (Cell cell : cells
            ) {
                if (!cell.isHit()) {
                    return false;
                }
            }
        }
        return true;
    }
}
