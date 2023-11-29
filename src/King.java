import java.util.ArrayList;

public class King extends Piece {
    boolean check = false;
    public King(Cell cell) {
        this.setCell(cell);
    }

    public void setCheck(boolean check) {this.check = check; }
    public boolean inCheck() {return check; }
    @Override
    ArrayList<Cell> getPossibleMoves() {
        return null;
    }
}
