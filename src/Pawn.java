/**
 * Extends piece, adding an evolution function for when the pawn reaches the end.
 * @author Landen Suorsa, Johnathan Hall, Christopher Hall
 */
public class Pawn extends Piece {
    private boolean enPassantWindow = false;
    /**
     * Creates a new pawn.
     * @param player 1 for white, 2 for black
     */
    public Pawn(int player) {
        super("Pawn", player);
    }

    /**
     * Creates a deep copy of the pawn for simulation purposes.
     * @param pawn
     */
    public Pawn(Pawn pawn) {
        super("Pawn", pawn.getPlayer());
    }

    public boolean inEnPassantWindow() {
        return enPassantWindow;
    }

    public void setEnPassantWindow(boolean enPassantWindow) {
        this.enPassantWindow = enPassantWindow;
    }

    /**
     * Changes the typing of the Pawn.
     * @param type
     */
    public void evolve(String type) {
        this.type = type;
    }
}
