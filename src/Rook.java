import java.util.ArrayList;

public class Rook extends Piece {
    public Rook(Cell cell) {
        this.setCell(cell);
    }
    @Override
    ArrayList<Cell> getPossibleMoves() {
        return null;
    }
}
