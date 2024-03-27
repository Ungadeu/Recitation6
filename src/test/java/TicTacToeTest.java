import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;


import static org.junit.jupiter.api.Assertions.*;

class TicTacToeTest {
    private TicTacToe board;
    private GamePiece[] player;


    @BeforeEach
    void setup() {
        player = new GamePiece[2];
        player[0] = new GamePiece('X');
        player[1] = new GamePiece('O');
        board = new TicTacToe(player[0],player[1]);
    }

    @Test
    @DisplayName("[1] test isValid")
    void isValid() {
        for(int loc = -5; loc < 15; loc++){
            boolean actual = board.isValid(loc);
            if (loc < 0 || loc > 8)
                assertFalse(actual,"Expecting: False\nActual: "+actual);
            else assertTrue(actual,"Expecting: True\nActual: "+actual);
        }
    }

    @Test
    @DisplayName("[1] test isEmpty")
    void isEmpty() {
        for(int loc = 0; loc < 8; loc++) {
            boolean actual = board.isEmpty(loc);
            assertTrue(actual, "Expecting: True\nActual: " + actual);
            board.add(loc);
            actual = board.isEmpty(loc);
            assertFalse(actual, "Expecting: False\nActual: " + actual);
        }
    }

    @Test
    @DisplayName("[1] test movesRemaining")
    void movesRemaining() {
        int actual = board.movesRemaining();
        assertEquals(9,actual,"Expecting: 9\n Actual: "+ actual);
        for(int loc = 0; loc < 9; loc++) {
            board.add(loc);
            actual = board.movesRemaining();
            int expected = 8-loc;
            assertEquals(expected,actual,"Expecting: 0\n Actual: "+ actual);
        }
    }

    @Test
    @DisplayName("[1] test getPiece")
    void getPiece() {
        for(int loc = 0; loc < 9; loc++) {
            board.add(loc);
            GamePiece piece = board.getPiece(loc);
            if (loc % 2 == 0){
                char expected = 'X';
                char actual = piece.getPiece();
                assertEquals(expected,actual,"Expected: "+expected+"\nActual: "+actual);
            } else {
                char expected = 'O';
                char actual = piece.getPiece();
                assertEquals(expected,actual,"Expected: "+expected+"\nActual: "+actual);
            }
            GamePiece actual2 = board.getPiece(loc);
            assertNotSame(piece,actual2,"Are you sure you getPiece() is returning a copy of the GamePiece?");
        }
    }

    @Test
    @DisplayName("[5] test getWinner")
    void getWinner() {
        int[][] combos = {
                {0,1,2,3,4,5,6},{2,1,0,5,4,3,8}, // diagonals
                {0,1,3,4,6},{1,0,4,3,7},{2,0,5,3,8}, //verticals
                {0,3,1,4,2},{3,0,4,1,5},{6,3,7,4,8}, // horizontals
                {0,1,3,4,2,7},{1,0,4,3,8,6},
                {0,3,1,4,6,5},{3,0,4,1,6,2}
        };
        for(int numOfChecks=0; numOfChecks < combos.length; numOfChecks++){
            for(int locIndex=0; locIndex < combos[numOfChecks].length; locIndex++){
                board.add(combos[numOfChecks][locIndex]);
            }
            int loc = combos[numOfChecks][combos[numOfChecks].length-1];
            char expecting = board.getPiece(loc).getPiece();
            GamePiece piece = board.getWinner();
            assertNotNull(piece,"Expecting a winner, but got no winner!");
            char actual = piece.getPiece();
            assertEquals(expecting,actual,"Expecting: "+expecting+"\nActual: "+actual+numOfChecks);
            board.clear();
        }
    }

    @Test
    @DisplayName("[2] test clear")
    void clear() {
        for(int loc = 0; loc < 9; loc++) {
            board.add(loc);
            assertFalse(board.isEmpty(loc));
        }
        board.clear();
        for(int loc = 0; loc < 9; loc++) {
            assertTrue(board.isEmpty(loc),"Board is supposed to be empty after a clear!");
        }
    }
}