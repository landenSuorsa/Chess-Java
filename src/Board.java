import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Stack;

public class Board {
    Cell[][] cell2DArray = new Cell[8][8];
    King whiteKing;
    King blackKing;
    Stack<Piece> whitePiecesTaken;
    Stack<Piece> blackPiecesTaken;

    public Board() {
        //TODO: initialize pieces
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                cell2DArray[i][j] = new Cell();
            }
        }
    }
    public Cell getCellAt(int row, int col) {
        return cell2DArray[row][col];
    }
    public Cell[][] getCell2DArray() {return cell2DArray; }

    public boolean checkmate() {
        return false;
    } //TODO: make this function return if the board is in state of checkmate.

    public boolean stalemate() {
        return false;
    } //TODO: make this function return if the board is in a state of stalemate.

    public ArrayList<Cell> getPossibleMoves(Cell cell) {
        ArrayList<Cell> moves = new ArrayList<>();
        switch (cell.getPiece().getType()) {
            case "Pawn":
                moves = pawnMoves(cell);
            case "Bishop":
                moves = bishopMoves(cell);
            case "Rook":
                moves = rookMoves(cell);
            case "Queen":
                moves = queenMoves(cell);
            case "King":
                moves = kingMoves(cell);
            case "Knight":
                moves = knightMoves(cell);
            default:
                throw new RuntimeException();
        }

        if ((cell.getPiece().getPlayer() == 1 && whiteKing.inCheck()) || (cell.getPiece().getPlayer() == 2 && blackKing.inCheck())) {
            //TODO: Remove moves that don't protect the King
        }

        return moves;
    }

    public void move(Cell start, Cell end) {
        //TODO: take the piece on Cell start and move it to Cell end. If it lands on enemy, take the enemy's piece.
    }

    public ArrayList<Cell> pawnMoves(Cell cell) {
        //TODO: write this function to return an arraylist of all possible moves for a pawn on Cell cell.
        return null;
    }
    public ArrayList<Cell> bishopMoves(Cell cell) {
        //TODO: write this function to return an arraylist of all possible moves for a bishop on Cell cell.
        return null;
    }
    public ArrayList<Cell> rookMoves(Cell cell) {
        //TODO: write this function to return an arraylist of all possible moves for a rook on Cell cell.
        return null;
    }
    public ArrayList<Cell> queenMoves(Cell cell) {
        //TODO: write this function to return an arraylist of all possible moves for a queen on Cell cell.
        return null;
    }
    public ArrayList<Cell> kingMoves(Cell cell) {
        //TODO: write this function to return an arraylist of all possible moves for a king on Cell cell.
        return null;
    }
    public ArrayList<Cell> knightMoves(Cell cell) {
        //TODO: write this function to return an arraylist of all possible moves for a knight on Cell cell.
        return null;
    }

}
