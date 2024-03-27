import java.util.Objects;

/**
 * The class represents ability to create a game piece for
 * any kind of game. It will be extended later to represent 
 * a generic game piece capable of representing pieces of
 * different types.
 *
 * @author (Marwan Rasamny)
 * @version (0.1)
 */
public class GamePiece
{
    private static final char DEFAULT_PIECE='X';

    // instance variables
    private char piece;

    /**
     * Creates a game piece with a default character or X.
     */
    public GamePiece()
    {
        setPiece(DEFAULT_PIECE);
    }

    /**
     * Creates a game piece with a character given by piece.
     *
     * @param piece a character representing the game piece.
     */
    public GamePiece(char piece)
    {
        setPiece(piece);
    }

    /**
     * Copy constructor
     */
    public GamePiece(GamePiece gamePiece)
    {
        this.piece = gamePiece.piece;
    }

    /**
     * Returns the character representing this game piece.
     *
     * @return Returns the character representing this game piece.
     */
    public char getPiece()
    {
        return piece;
    }

    /**
     * Sets the representation of this game piece to the character
     * represented by piece.
     *
     * @param piece the character representing this piece.
     */
    public void setPiece(char piece)
    {
        this.piece = piece;
    }

    /**
     * Returns the string representing this piece.
     *
     * @return Returns the string representing this piece.
     */
    public String toString()
    {
        return ""+piece;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GamePiece gamePiece = (GamePiece) o;
        return piece == gamePiece.piece;
    }

    @Override
    public int hashCode() {
        return Objects.hash(piece);
    }

}