/**
 * This class implements the game we all love to
 * not play.
 *
 * @author (M Rasamny)
 * @version (03 / 13 / 2018)
 */
public class TicTacToe {
    // instance variables
    private GamePiece[][] board;
    private GamePiece[] player;
    private int nextPlayerIndex;
    private int numOfMoves;

    public TicTacToe(GamePiece p1, GamePiece p2) {
        board = new GamePiece[3][3];
        clear();
        player = new GamePiece[2];
        player[0] = new GamePiece(p1);
        player[1] = new GamePiece(p2);
        nextPlayerIndex = 0;
        numOfMoves = 0;
    }


    /**
     * Returns true if the location is an integer that represents one of the squares on the board ; false otherwise
     *
     * @param location the integer representation of the square.
     * @return true if the location is an integer that represents one of the squares on the board ; false otherwise
     */
    public boolean isValid(int location) {
        // TODO: add code here
        if (location >= 0 && location <= 8) {
            return true;
        }
        return false;
    }

    /**
     * Returns true if the location is NOT occupied by a game piece; false otherwise
     *
     * @param location the integer representation of the square.
     * @return true if the location is NOT occupied by a game piece; false otherwise
     */
    public boolean isEmpty(int location) {
        // TODO: add code here
        if (!isValid(location)) {
            throw new IllegalArgumentException("Invalid location");
        }
        int row = location / board[0].length;
        int col = location % board[0].length;
        return board[row][col] == null;
    }

    /**
     * Returns the number of moves remaining on the board
     *
     * @return the number of moves remaining on the board
     */
    public int movesRemaining() {
        // TODO: add code here
        return board.length * board[0].length - numOfMoves;
    }

    /**
     * Returns the game piece at the provided location
     *
     * @param location the integer representation of the square
     * @return the game piece at the provided location
     */
    public GamePiece getPiece(int location) {
        // TODO: add code here
        if (!isValid(location)) {
            throw new IllegalArgumentException("Invalid location");
        }
        int row = location / board[0].length;
        int col = location % board[0].length;
        return new GamePiece(board[row][col]);
    }

    /**
     * Returns the winner's GamePiece or null if there is no winner at the time the method is invoked
     *
     * @return the winner's GamePiece or null if there is no winner at the time the method is invoked
     */
    public GamePiece getWinner() {
        GamePiece winner = null;
        int[][] combos = {
                {0, 1, 2}, {3, 4, 5}, {6, 7, 8}, // horizontal winning combinations
                {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, // vertical winning combinations
                {0, 4, 8}, {2, 4, 6}             // diagonal winning combinations
        };
        // TODO: add code here

        for (int[] combo : combos) {
            GamePiece firstPiece = board[combo[0] / board.length][combo[0] % board.length];
            if (firstPiece == null) {
                continue;
            }

            boolean hasWinner = true;
            for (int i = 1; i < combo.length; i++) {
                GamePiece currentPiece = board[combo[i] / board.length][combo[i] % board.length];
                if (currentPiece == null || !currentPiece.equals(firstPiece)) {
                    hasWinner = false;
                    break;
                }
            }

            if (hasWinner) {
                winner = new GamePiece(firstPiece);
                break;
            }
        }

        return winner;
    }

    /**
     * Indicates the current player by returning the current player's game piece
     * @return the current player's game piece.
     */
    public GamePiece getCurrentPlayer() {
        return new GamePiece(player[nextPlayerIndex]);
    }

    /**
     * Places a game piece at the provided location if and only if the location is valid and is empty.  Returns
     * true if the operation is successful; false otherwise
     * @param location the integer representation of the square.
     * @return true if it is able to place a game piece at the specified location; false otherwise
     */
    public boolean add(int location) {
        if (isValid(location) && isEmpty(location)) {
            board[location / board.length][location % board.length] = player[nextPlayerIndex];
            nextPlayerIndex = nextPlayerIndex > 0 ? 0 : 1;
            numOfMoves++;
            return true;
        }
        return false;
    }

    /**
     * Clears the board of all game pieces
     */
    public void clear() {
        // set all elements of 2d array to null
        // TODO: add code here
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                board[i][j] = null;
            }
        }
    }

    /**
     * Returns a string representation of the board
     * @return a string representation of the board
     */
    @Override
    public String toString() {

        // TODO: add code here

        StringBuilder s = new StringBuilder();
        for (int i = 0; i < board.length; i++) {
            s.append("+---+---+---+\n");
            for (int j = 0; j < board[0].length; j++) {
                s.append("| ");
                int location = i * board[0].length + j;
                if (isEmpty(location)) {
                    s.append(location);
                } else {
                    s.append(board[i][j]);
                }
                s.append(" ");
            }
            s.append("|\n");
        }
        s.append("+---+---+---+\n");
        return s.toString();
    }
}