package battleship;


public class Main {

    public static void main(String[] args) {
        // Write your code here
        UI player1 = new UI();
        UI player2 = new UI();
        Cell cell;
        int choice = 1;

        player1.showOverallUI("Player 1");
        player2.showOverallUI("Player 2");

        do {
            switch (choice) {
                case 1:
                    player2.board.printFogOfWarBoard();
                    System.out.println("---------------------");
                    player1.board.printBoard();
                    System.out.println("Player 1, it's your turn:");
                    cell = player2.board.getCell(player2.cellToMark());
                    player2.markBoard(cell);
                    player1.passTurn();
                    choice = 2;
                    break;
                case 2:
                    player1.board.printFogOfWarBoard();
                    System.out.println("---------------------");
                    player2.board.printBoard();
                    System.out.println("Player 2, it's your turn:");
                    cell = player1.board.getCell(player1.cellToMark());
                    player1.markBoard(cell);
                    player2.passTurn();
                    choice = 1;
                    break;
            }
        } while (!player1.board.finishGame() || !player2.board.finishGame());
    }
}
