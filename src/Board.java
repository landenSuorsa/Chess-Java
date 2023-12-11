import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Board {
    Cell[][] cell2DArray = new Cell[8][8];
    King whiteKing;
    King blackKing;
    JPanel whiteTakenPieces;
    JPanel blackTakenPieces;
    Cell clickedCell = null;
    Color brown = new Color(104,80,40);
    Color lightBrown = new Color(130,100,60);
    boolean isSimulation = false;

    public Board() {
        String[] pieceNames = {"Rook", "Knight", "Bishop", "Queen", "King", "Bishop", "Knight", "Rook"};
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                int ifin = i;
                int jfin = j;
                cell2DArray[i][j] = new Cell(i, j);
                cell2DArray[i][j].addActionListener(e -> clickCell(cell2DArray[ifin][jfin]));
                if ((i % 2 == 1 && j % 2 == 0) || (i % 2 == 0 && j % 2 == 1)) {
                    cell2DArray[i][j].setBackground(brown);
                } else {
                    cell2DArray[i][j].setBackground(lightBrown);
                }
            }
        }

        for (int i = 0; i < 8; i++) {
            cell2DArray[0][i].setPiece(new Piece(pieceNames[i],2));
            cell2DArray[1][i].setPiece(new Pawn(false,2));
            cell2DArray[7][i].setPiece(new Piece(pieceNames[i],1));
            cell2DArray[6][i].setPiece(new Pawn(true,1));
        }

        cell2DArray[0][4].setPiece(new King(2));
        cell2DArray[7][4].setPiece(new King(1));
        blackKing = (King)cell2DArray[0][4].getPiece();
        whiteKing = (King)cell2DArray[7][4].getPiece();

        whiteTakenPieces = new JPanel();
        whiteTakenPieces.setLayout(new GridLayout(2,8));
        whiteTakenPieces.setPreferredSize(new Dimension(500,100));
        blackTakenPieces = new JPanel();
        blackTakenPieces.setLayout(new GridLayout(2,8));
        blackTakenPieces.setPreferredSize(new Dimension(500,100));
    }

    public Board(Board board) {
        this.cell2DArray = new Cell[8][8];
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                this.cell2DArray[i][j] = new Cell(board.getCell2DArray()[i][j]);
                if (board.getCell2DArray()[i][j].getPiece() instanceof King) {
                    if (this.getCell2DArray()[i][j].getPiece().getPlayer() == 1) whiteKing = (King)this.getCell2DArray()[i][j].getPiece();
                    else blackKing = (King)this.getCell2DArray()[i][j].getPiece();
                }
            }
        }
        isSimulation = true;
    }
    public Cell[][] getCell2DArray() {
        return cell2DArray;
    }

    public JPanel getWhiteTakenPieces() {
        return whiteTakenPieces;
    }

    public JPanel getBlackTakenPieces() {return blackTakenPieces; }

    public boolean checkmate(int player) {
        if (!check(player)) return false;
        else return returnTotalPossibleMoves(player).size() == 0;
    }

    public boolean check(int player) {
        int enemy = (player == 1) ? 2 : 1;
        for (Cell move : returnTotalPossibleMoves(enemy)) {
            if (move.getPiece() == whiteKing || move.getPiece() == blackKing) {
                return true;
            }
        }
        return false;
    }

    public boolean stalemate(int player) {
        if (check(player)) return false;
        else return returnTotalPossibleMoves(player).size() == 0;
    }

    public Set<Cell> returnTotalPossibleMoves(int player) {
        Set<Cell> moves = new HashSet<Cell>();

        for (Cell[] row : cell2DArray) {
            for (Cell c : row) {
                if (c.getPiece() != null && c.getPiece().getPlayer() == player) {
                    ArrayList<Cell> m = returnPossibleMoves(c);
                    moves.addAll(m);
                }
            }
        }

        return moves;
    }

    public ArrayList<Cell> returnPossibleMoves(Cell cell) {
        ArrayList<Cell> moves = new ArrayList<>();
        switch (cell.getPiece().getType()) {
            case "Pawn":
                moves = pawnMoves(cell);
                break;
            case "Bishop":
                moves = bishopMoves(cell);
                break;
            case "Rook":
                moves = rookMoves(cell);
                break;
            case "Queen":
                moves = queenMoves(cell);
                break;
            case "King":
                moves = kingMoves(cell);
                break;
            case "Knight":
                moves = knightMoves(cell);
        }

        if (!isSimulation) {
            ArrayList<Cell> badMove = new ArrayList<>();
            for (Cell move : moves) {
                Board simulation = new Board(this);
                simulation.getCell2DArray()[move.getRow()][move.getCol()].setPiece(simulation.getCell2DArray()[cell.getRow()][cell.getCol()].getPiece());
                simulation.getCell2DArray()[cell.getRow()][cell.getCol()].setPiece(null);
                if (simulation.check(cell.getPiece().getPlayer())) {
                    badMove.add(move);
                }
            }
            moves.removeAll(badMove);
        }
        return moves;
    }

    public void showPossibleMoves(Cell cell) {
        for (Cell[] row : cell2DArray) {
            for (Cell c : row) {
                c.setEnabled(false);
            }
        }

        ArrayList<Cell> moves = returnPossibleMoves(cell);

        cell.setEnabled(true);
        if (moves.size() != 0) {
            for (Cell move : moves) {
                move.setEnabled(true);
            }
        }
    }

    public ArrayList<Cell> pawnMoves(Cell cell) {// does the cell have something that i can get the position or do i need to add something(Cell[][] board)
        //TODO: write this function to return an arraylist of all possible moves for a pawn on Cell cell.

        //how to write checks that doesnt let you go past the board perimeter***
/*
        ArrayList<Cell> legalMoves = new ArrayList<Cell>();
        Cell[][] currBoard = getCell2DArray();
        for(int i = 1; i < 8; ++){
           if(cell == currBoard[2[i]){//does the cell have coordinates or not
               legalMoves.add(
        }
*/
        return new ArrayList<Cell>();
    }

    public ArrayList<Cell> bishopMoves(Cell cell) {
        //TODO: write this function to return an arraylist of all possible moves for a bishop on Cell cell.
        ArrayList<Cell> possibleMoves = new ArrayList<>();

        int currentRow = cell.getRow();
        int currentCol = cell.getCol();

        // Define the possible directions for a bishop (diagonals)
        int[][] directions = { {-1, -1}, {-1, 1}, {1, -1}, {1, 1} };

        // Iterate through each direction
        for (int[] direction : directions) {
            int row = currentRow + direction[0];
            int col = currentCol + direction[1];

            // Continue in the current direction until we reach the edge of the board
            while (isValidMove(row, col) && cell2DArray[row][col].getPiece() == null) {
                possibleMoves.add(cell2DArray[row][col]);
                row += direction[0];
                col += direction[1];
            }

            // Check if the last cell in the direction has an opponent's piece
            if (isValidMove(row, col) && cell2DArray[row][col].getPiece() != null && (cell2DArray[row][col].getPiece().getPlayer() != cell2DArray[currentRow][currentCol].getPiece().getPlayer())) {
                possibleMoves.add(cell2DArray[row][col]);
            }
        }

        return possibleMoves;
    }

    private boolean isValidMove(int row, int col) {
        // Assuming the chessboard is an 8x8 grid
        return row >= 0 && row < 8 && col >= 0 && col < 8;
    }
    public ArrayList<Cell> rookMoves(Cell cell) {
        ArrayList<Cell> moves = new ArrayList<>();

        int[] directions = {-1, 0, 1};

        for (int i : directions) {
            for (int j : directions) {
                if (i == 0 || j == 0) {
                    for (int step = 1; step < 8; step++) {
                        int newRow = cell.getRow() + step * i;
                        int newCol = cell.getCol() + step * j;

                        if (isValidMove(newRow, newCol)) {
                            Cell newCell = cell2DArray[newRow][newCol];

                            if (newCell.getPiece() == null) {
                                moves.add(newCell);
                            } else if (newCell.getPiece().getPlayer() != cell.getPiece().getPlayer()) {
                                moves.add(newCell);
                                break;
                            } else {
                                break;
                            }
                        } else {
                            break;
                        }
                    }
                }
            }
        }

        return moves;
    }

    public ArrayList<Cell> queenMoves(Cell cell) {
        ArrayList<Cell> moves = rookMoves(cell);
        if (bishopMoves(cell) != null) {
            for (Cell c : bishopMoves(cell)) {
                moves.add(c);
            }
        }
        return moves;
    }

    public ArrayList<Cell> kingMoves(Cell cell) {
        ArrayList<Cell> moves = new ArrayList<>();

        int[] directions = {-1, 0, 1};
        int currentRow = cell.getRow();
        int currentCol = cell.getCol();

        for (int i : directions) {
            for (int j : directions) {
                int newRow = currentRow + i;
                int newCol = currentCol + j;

                if (isValidMove(newRow, newCol) && (i != 0 || j != 0)) {
                    Cell newCell = cell2DArray[newRow][newCol];

                    if (newCell.getPiece() == null || newCell.getPiece().getPlayer() != cell.getPiece().getPlayer()) {
                        moves.add(newCell);
                    }
                }
            }
        }

        return moves;
    }

    public ArrayList<Cell> knightMoves(Cell cell) {
        ArrayList<Cell> moves = new ArrayList<>();

        int[] rowMoves = {1, 2, 2, 1, -1, -2, -2, -1};
        int[] colMoves = {2, 1, -1, -2, -2, -1, 1, 2};

        int currentRow = cell.getRow();
        int currentCol = cell.getCol();

        for (int i = 0; i < 8; i++) {
            int newRow = currentRow + rowMoves[i];
            int newCol = currentCol + colMoves[i];

            if (isValidMove(newRow, newCol)) {
                Cell newCell = cell2DArray[newRow][newCol];

                if (newCell.getPiece() == null || newCell.getPiece().getPlayer() != cell.getPiece().getPlayer()) {
                    moves.add(newCell);
                }
            }
        }

        return moves;
    }

    public void clickCell(Cell cell) {
        if (cell.getPiece() != null && clickedCell == null) {
            // case for if no cell has been pressed yet.
            clickedCell = cell;
            showPossibleMoves(cell);
        } else if (cell.equals(clickedCell)) {
            // case for if the same cell is clicked twice, unselecting it.
            clickedCell = null;
            enablePlayersPieces(cell.getPiece().getPlayer());
        } else if (clickedCell != null) {
            // cases for moving a piece
            if (cell.getPiece() == null) {
                // case for if nothing is in the spot the piece is going.
                cell.setPiece(clickedCell.getPiece());
                clickedCell.setPiece(null);
            } else if (cell.getPiece().getPlayer() != clickedCell.getPiece().getPlayer()) {
                // cases for if a piece is in the spot it is going
                if (cell.getPiece().getPlayer() == 1) {
                    // puts the piece taken in the respective spot
                    ImageIcon icon = new ImageIcon("1" + cell.getPiece().getType() + ".png");
                    icon = new ImageIcon(icon.getImage().getScaledInstance(50,50,Image.SCALE_SMOOTH));
                    whiteTakenPieces.add(new JLabel(icon));
                } else {
                    // puts the piece taken in the respective spot
                    ImageIcon icon = new ImageIcon("2" + cell.getPiece().getType() + ".png");
                    icon = new ImageIcon(icon.getImage().getScaledInstance(50,50,Image.SCALE_SMOOTH));
                    blackTakenPieces.add(new JLabel(icon));
                }

                // moves the piece.
                cell.setPiece(clickedCell.getPiece());
                clickedCell.setPiece(null);

                if (clickedCell.getPiece() instanceof Pawn && clickedCell.getPiece().getType().equals("Pawn")) {
                    if ((cell.getRow() == 0 && cell.getPiece().getPlayer() == 1) || (cell.getRow() == 7 && cell.getPiece().getPlayer() == 2)) {
                        pawnEvolution(((Pawn)cell.getPiece()));
                        cell.updateIcon();
                    }
                }
            }

            int nextPlayer = (cell.getPiece().getPlayer() == 1) ? 2 : 1;
            enablePlayersPieces(nextPlayer);
            clickedCell = null;

            if (checkmate(nextPlayer)) {
                System.out.println("Checkmate");
            }
            else if (check(nextPlayer)) {
                System.out.println("Check");
            }
            else if (stalemate(nextPlayer)) {
                System.out.println("Stalemate");
            }

        } else {
            // shouldn't be reached.
            throw new RuntimeException();
        }
    }

    public void enablePlayersPieces(int player) {
        for (Cell[] row : cell2DArray) {
            for (Cell cell : row) {
                if (cell.getPiece() != null && cell.getPiece().getPlayer() == player) {
                    cell.setEnabled(true);
                } else {
                    cell.setEnabled(false);
                }
            }
        }
    }

    public void pawnEvolution(Pawn pawn) {
        JFrame popUp = new JFrame("Pawn Evolution");
        popUp.setLayout(new FlowLayout());
        JButton option1 = new JButton("Pawn");
        option1.addActionListener(e -> {pawn.evolve(option1.getText()); popUp.setVisible(false); });
        JButton option2 = new JButton("Rook");
        option1.addActionListener(e -> {pawn.evolve(option2.getText()); popUp.setVisible(false); });
        JButton option3 = new JButton("Bishop");
        option1.addActionListener(e -> {pawn.evolve(option3.getText()); popUp.setVisible(false); });
        JButton option4 = new JButton("Knight");
        option1.addActionListener(e -> {pawn.evolve(option4.getText()); popUp.setVisible(false); });
        JButton option5 = new JButton("Queen");
        option1.addActionListener(e -> {pawn.evolve(option5.getText()); popUp.setVisible(false); });
        popUp.add(option1);
        popUp.add(option2);
        popUp.add(option3);
        popUp.add(option4);
        popUp.add(option5);

        popUp.setVisible(true);
        popUp.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
    }
}
