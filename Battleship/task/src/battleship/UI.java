package battleship;

import java.util.Scanner;

public class UI {
    Scanner scanner = new Scanner(System.in);
    String input;
    String start;
    String end;
    char startLetter;
    String startNumber;
    char endLetter;
    String endNumber;
    boolean pass = false;
    Board board;

    public UI() {
        board = new Board();
    }

    // Game itself
    public void showOverallUI(String name) {
        System.out.println(name + ", place your ships to the game field.");
        for (SHIPS ship : SHIPS.values()
        ) {
            board.printBoard();
            pass = false;
            do {
                showUI(ship);
            } while (!pass);
        }
        board.printBoard();
        passTurn();
    }

    public String cellToMark() {
        Cell cell;
        do {
            input = scanner.next();
            cell = board.getCell(input);
            if (cell == null) {
                System.out.println("Error! You entered the wrong coordinates! Try again");
            }
        } while (cell == null);
        return cell.getName();
    }

    public void markBoard(Cell cell) {
        if (cell.isMark()) {
            cell.setFog("X");
            cell.setValue("X");
            cell.setHit(true);
            if (board.finishGame()) {
                System.out.println("You sank the last ship. You won. Congratulations!");
            } else if (board.checkSunkShip(input)) {
                System.out.println("You sank a ship! Specify a new target:");
            } else {
                System.out.println("You hit a ship!");
            }
        } else {
            cell.setFog("M");
            cell.setValue("M");
            System.out.println("You missed.");
        }
    }

    // Set your ships on battlefield
    public void showUI(SHIPS ships) {
        System.out.printf("Enter the coordinates of the %s (%d cells):\n", ships.getName(), ships.getLength());
        input = scanner.nextLine();
        start = input.split(" ")[0];
        end = input.split(" ")[1];
        if (checkInput(start, end, ships)) {
            if (startLetter == endLetter) {
                if (Integer.parseInt(startNumber) > Integer.parseInt(endNumber)) {
                    for (int i = Integer.parseInt(startNumber); i >= Integer.parseInt(endNumber); i--) {
                        board.board[board.getCellRow(startLetter)][i].mark();
                        board.board[board.getCellRow(startLetter)][i].setHit(false);
                        pass = true;
                    }
                } else {
                    for (int i = Integer.parseInt(startNumber); i <= Integer.parseInt(endNumber); i++) {
                        board.board[board.getCellRow(startLetter)][i].mark();
                        board.board[board.getCellRow(startLetter)][i].setHit(false);
                        pass = true;
                    }
                }
            } else {
                if (board.getCellRow(startLetter) > board.getCellRow(endLetter)) {
                    for (int i = board.getCellRow(startLetter); i >= board.getCellRow(endLetter); i--) {
                        board.board[i][Integer.parseInt(startNumber)].mark();
                        board.board[i][Integer.parseInt(startNumber)].setHit(false);
                        pass = true;
                    }
                } else {
                    for (int i = board.getCellRow(startLetter); i <= board.getCellRow(endLetter); i++) {
                        board.board[i][Integer.parseInt(startNumber)].mark();
                        board.board[i][Integer.parseInt(startNumber)].setHit(false);
                        pass = true;
                    }
                }
            }
        }
    }

    // Check valid input
    public boolean checkInput(String start, String end, SHIPS ships) {
        if (start.length() > 3 || end.length() > 3) {
            System.out.println("Please enter valid coordinates. It should only be 2 letters. Eg. A1, D4.");
            return false;
        } else {
            startLetter = start.toCharArray()[0];
            startNumber = start.split("(?<=\\D)(?=\\d)")[1];
            endLetter = end.toCharArray()[0];
            endNumber = end.split("(?<=\\D)(?=\\d)")[1];
            if (!(board.alphabet.contains(Character.toString(startLetter)) && (Integer.parseInt(startNumber) > 0 && Integer.parseInt(startNumber) < 11) && board.alphabet.contains(Character.toString(endLetter)) && (Integer.parseInt(endNumber) > 0 && Integer.parseInt(endNumber) < 11))) {
                System.out.println("Please only enter letters from A to J and numbers from 1 to 10");
                return false;
            } else if (startLetter != endLetter && !startNumber.equals(endNumber)) {
                System.out.println("Error! Wrong ship location! Try again:");
                return false;
            } else if ((startLetter == endLetter && (Math.abs(Integer.parseInt(endNumber) - Integer.parseInt(startNumber)) + 1 != ships.getLength())) || (startNumber.equals(endNumber) && (Math.abs(board.getCellRow(endLetter) - board.getCellRow(startLetter)) + 1 != ships.getLength()))) {
                System.out.println("Error! Wrong length of the " + ships.toString() + "! Try again:");
                return false;
            } else if (!board.checkCloseShip(startLetter, startNumber, endLetter, endNumber)) {
                System.out.println("Error! You placed it too close to another one. Try again:");
                return false;
            } else {
                return true;
            }
        }
    }

    public void passTurn() {
        Scanner scanner1 = new Scanner(System.in);
        System.out.println("Please press enter and pass the move to another player.");
        scanner1.nextLine();
        scanner1.close();
    }
}
