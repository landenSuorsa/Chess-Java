import java.util.ArrayList;

public class Pawn extends Piece {
    boolean facingUp;

    public Pawn(Cell cell, Boolean facingUp) {
        this.setCell(cell);
        this.facingUp = facingUp;
    }
    @Override
    ArrayList<Cell> getPossibleMoves() {
        return null;
    }
}
