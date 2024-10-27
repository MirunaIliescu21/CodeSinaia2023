package JavaBasics.EightQueens;

/**
 * Place eight queens on an 8×8 chessboard such that none of them 
 * attack one another (no two are in the same row, column, or diagonal)
 */
public class Program {

    private static int[][] _board = new int[8][8];

    public static void main(String[] args) {
        if (placeQueen(1)) {
            System.out.printf("Solution FOUND!\n");
            printBoard();
        } else {
            System.out.println("NO solution found!\n");
        }
    }

    private static boolean placeQueen(int nQueen) {
        int rnd = (int)(Math.random() * 8);
        for (int r = 0; r < _board.length; r++) {
            for (int c = 0; c < _board[r].length; c++) {
                int rR = (r + rnd) % 8;
                int rC = (c + rnd) % 8;
                if (_board[rR][rC] == 0) {
                    updateBoard(rR, rC, 1);
                    if  (nQueen == 8 || placeQueen(nQueen+1)) {
                        // trick: as we're on a straight backtrack,
                        // switch this queen's position to its negative 
                        // value to disambiguate from the
                        // other markings when printing the board.
                        _board[rR][rC] = -nQueen;
                        return true;
                    } else {
                        updateBoard(rR, rC, -1);
                    }
                }
            }
        }
        return false;
    }

    private static void updateBoard(int r, int c, int value) {
        _board[r][c]=(value > 0) ? 1 : 0;

        for (int i = -7; i <= 7; i++) {
            if (i == 0) {
                continue;
            }
            updateCell(r + i, c, value);
            updateCell(r, c + i, value);
            updateCell(r + i, c + i, value);
            updateCell(r + i, c - i, value);
        }
    }

    public static void updateCell(int r, int c, int value) {
        if (r >= 0 && r < 8 && c >= 0 && c < 8) {
            _board[r][c] += value;
        }
    }

    public static void printBoard() {
        for (int r = 0; r < _board.length; r++) {
            for (int c = 0; c < _board[r].length; c++) {
                if (_board[r][c] < 0) {
                    System.out.printf("%d ", -_board[r][c]);
                } else {
                    System.out.printf(". ");
                }
            }
            System.out.println();
        }
    }
}
