import java.util.ArrayList;

public class Queen extends Piece {
    public Queen(Cell cell) {
        this.setCell(cell);
    }
    @Override
    ArrayList<Cell> getPossibleMoves() {
        return null;
    }
}
