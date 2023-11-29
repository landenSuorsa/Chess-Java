import java.util.ArrayList;

abstract class Piece {
    Cell cell;

    public Cell getCell() {return cell; }
    public void setCell(Cell cell) {this.cell = cell; }
    abstract ArrayList<Cell> getPossibleMoves();
    public void move(Cell cell) {
        getCell().setPiece(null);
        setCell(cell);
        cell.setPiece(this);
    }
}
