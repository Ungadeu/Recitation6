/**
 * This class implements the game we all love to
 * not play.
 *
 * @author (M Rasamny)
 * @version (03/13/2018)
 */

import java.util.Scanner;

public class TicTacToeGame {

    public static void main(String[] argv)
    {
        Scanner input = new Scanner(System.in);
        TicTacToe ttt = new TicTacToe(new GamePiece('X'),new GamePiece('O'));
        int location;

        while(ttt.movesRemaining() > 0 && ttt.getWinner() == null){
            System.out.println(ttt);
            System.out.println("Player "+ttt.getCurrentPlayer()+", choose a square:");
            location = input.nextInt();
            if (!ttt.isValid(location)){
                System.out.println("Please select a valid square!");
            } else if (!ttt.isEmpty(location)){
                System.out.println("That square is already taken!");
            } else {
                ttt.add(location);
            }
        }
        System.out.println(ttt);
        if (ttt.getWinner() != null){
            System.out.println(ttt.getWinner()+" is the winner!");
        } else {
            System.out.println("Game ended in a draw!");
        }
    }
}
