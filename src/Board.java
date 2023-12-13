import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Creates a new chess board and contains all the movement patterns for the pieces.
 * @author Landen Suorsa, Jonathan Hall, Christopher Hall
 */
public class Board {
    private Cell[][] cell2DArray = new Cell[8][8]; // the board
    private Piece whiteKing;
    private Piece blackKing;
    private JPanel whiteTakenPieces; // holds display of taken white pieces.
    private JPanel blackTakenPieces; // holds display of taken black pieces.
    private Cell clickedCell = null; // used within clickCell()
    private Color brown = new Color(104,80,40);
    private Color lightBrown = new Color(130,100,60);
    private boolean isSimulation = false; // is set to true in deep copies of the board.
    private JFrame checkFrame = new JFrame("Check"); // used in checkPopUp()

    /**
     * Creates a new chess Board with the default layout of the pieces.
     */
    public Board() {
        String[] pieceNames = {"Rook", "Knight", "Bishop", "Queen", "King", "Bishop", "Knight", "Rook"};
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                int ifin = i;
                int jfin = j;
                cell2DArray[i][j] = new Cell(i, j); // creates a cell for spot {i, j}
                cell2DArray[i][j].addActionListener(e -> clickCell(cell2DArray[ifin][jfin])); //Adds an action listener to cell.
                // creates the iconic checkered pattern for the board.
                if ((i % 2 == 1 && j % 2 == 0) || (i % 2 == 0 && j % 2 == 1)) {
                    cell2DArray[i][j].setBackground(brown);
                } else {
                    cell2DArray[i][j].setBackground(lightBrown);
                }
            }
        }

        // sets the pieces
        for (int i = 0; i < 8; i++) {
            cell2DArray[0][i].setPiece(new Piece(pieceNames[i], 2));
            cell2DArray[1][i].setPiece(new Pawn(2));
            cell2DArray[7][i].setPiece(new Piece(pieceNames[i], 1));
            cell2DArray[6][i].setPiece(new Pawn(1));
        }

        // sets the king variables to reference the king pieces.
        blackKing = cell2DArray[0][4].getPiece();
        whiteKing = cell2DArray[7][4].getPiece();

        // creates the display for taken enemy pieces.
        whiteTakenPieces = new JPanel();
        whiteTakenPieces.setLayout(new GridLayout(2, 8));
        whiteTakenPieces.setPreferredSize(new Dimension(500, 100));
        blackTakenPieces = new JPanel();
        blackTakenPieces.setLayout(new GridLayout(2, 8));
        blackTakenPieces.setPreferredSize(new Dimension(500, 100));
    }

    /**
     * Creates a deep copy of the board for simulation purposes.
     * @param board
     */
    public Board(Board board) {
        this.cell2DArray = new Cell[8][8];
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                this.cell2DArray[i][j] = new Cell(board.getCell2DArray()[i][j]);
                if (board.getCell2DArray()[i][j].getPiece() != null && board.getCell2DArray()[i][j].getPiece().getType().equals("King")) {
                    if (this.getCell2DArray()[i][j].getPiece().getPlayer() == 1) whiteKing = this.getCell2DArray()[i][j].getPiece();
                    else blackKing = this.getCell2DArray()[i][j].getPiece();
                }
            }
        }
        isSimulation = true; // prevents infinite simulations.
    }

    /**
     * @return an 8x8 Cell array.
     */
    public Cell[][] getCell2DArray() {
        return cell2DArray;
    }

    /**
     * @return gives the display of taken white pieces.
     */
    public JPanel getWhiteTakenPieces() {
        return whiteTakenPieces;
    }

    /**
     * @return gives the display of taken black pieces.
     */
    public JPanel getBlackTakenPieces() {return blackTakenPieces; }

    /**
     * @return frame that tells user who is in check.
     */
    public JFrame getCheckFrame() {return checkFrame; }

    /**
     * @param player
     * @return true if player is in checkmate, false otherwise
     */
    public boolean checkmate(int player) {
        if (!check(player)) return false;
        else return returnTotalPossibleMoves(player).size() == 0;
    }

    /**
     * @param player
     * @return true if player is in check, false otherwise
     */
    public boolean check(int player) {
        int enemy = (player == 1) ? 2 : 1;
        for (Cell move : returnTotalPossibleMoves(enemy)) {
            if (move.getPiece() == whiteKing || move.getPiece() == blackKing) {
                return true;
            }
        }
        return false;
    }

    /**
     * @param player
     * @return true if player is in stalemate (has no possible moves). False otherwise
     */
    public boolean stalemate(int player) {
        if (check(player)) return false;
        else return returnTotalPossibleMoves(player).size() == 0;
    }

    /**
     * @param player
     * @return Every cell that player can make a move to.
     */
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

    /**
     * @param cell
     * @return the Cells the piece on Cell cell can move to
     */
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

        // removes moves that endanger the King or leave the King endangered.
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

    /**
     * Enables all cells that a piece can move onto. Also enables Cell cell.
     * @param cell
     */
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

    /**
     * @param cell
     * @return A list of cells that a pawn on Cell cell can move to.
     */
    public ArrayList<Cell> pawnMoves(Cell cell) {// does the cell have something that i can get the position or do i need to add something(Cell[][] board)
        //TODO: write this function to return an arraylist of all possible moves for a pawn on Cell cell.
ArrayList<Cell> possibleMoves = new ArrayList<>();

        int currentRow = cell.getRow();
        int currentCol = cell.getCol();
        int direction = (board[currentRow][currentCol].isWhite()) ? 1 : -1;

        // 1. Single move forward
        int newRow = currentRow + direction;
        if (isValidMove(newRow, currentCol) && board[newRow][currentCol] == null) {
            possibleMoves.add(new Cell(newRow, currentCol));

            // 2. Initial double move from starting position
            if (isAtStartingPosition(currentRow) && board[newRow + direction][currentCol] == null) {
                possibleMoves.add(new Cell(newRow + direction, currentCol));
            }
        }

        // 3. Capture diagonally
        int[] captureCols = {currentCol - 1, currentCol + 1};
        for (int col : captureCols) {
            if (isValidMove(newRow, col) && board[newRow][col] != null && board[newRow][col].isWhite() != board[currentRow][currentCol].isWhite()) {
                possibleMoves.add(new Cell(newRow, col));
            }
        }

        return possibleMoves;
    }

    /**
     * @param cell
     * @return A list of cells that a bishop on Cell cell can move to.
     */
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

    /**
     * @param row
     * @param col
     * @return checks to see if both row and col are in range 0-7.
     */
    private boolean isValidMove(int row, int col) {
        // Assuming the chessboard is an 8x8 grid
        return row >= 0 && row < 8 && col >= 0 && col < 8;
    }

    /**
     * @param cell
     * @return A list of cells that a rook on Cell cell can move to.
     */
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

    /**
     * @param cell
     * @return A list of cells that a queen on Cell cell can move to.
     */
    public ArrayList<Cell> queenMoves(Cell cell) {
        ArrayList<Cell> moves = rookMoves(cell);
        if (bishopMoves(cell) != null) {
            for (Cell c : bishopMoves(cell)) {
                moves.add(c);
            }
        }
        return moves;
    }

    /**
     * @param cell
     * @return A list of cells that a King on Cell cell can move to.
     */
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

    /**
     * @param cell
     * @return A list of cells that a Knight on Cell cell can move to.
     */
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

    /**
     * First click selects a piece. Next time it is called it moves the piece, taking whatever piece was on that spot.
     * @param cell the cell that is clicked.
     */
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

                // pawn evolution case
                if (clickedCell.getPiece() instanceof Pawn && clickedCell.getPiece().getType().equals("Pawn")) {
                    if ((cell.getRow() == 0 && cell.getPiece().getPlayer() == 1) || (cell.getRow() == 7 && cell.getPiece().getPlayer() == 2)) {
                        pawnEvolution(((Pawn)cell.getPiece()));
                        cell.updateIcon();
                    }
                }
            }

            // switches players
            checkFrame.setVisible(false);
            int nextPlayer = (cell.getPiece().getPlayer() == 1) ? 2 : 1;
            enablePlayersPieces(nextPlayer);
            clickedCell = null;

            // checks for certain end game conditions.
            if (checkmate(nextPlayer)) {
                endOfGamePopUp("checkmate", nextPlayer);
            }
            else if (check(nextPlayer)) {
                checkPopUp(nextPlayer);
            }
            else if (stalemate(nextPlayer)) {
                endOfGamePopUp("stalemate", nextPlayer);
            }

        } else {
            // shouldn't be reached.
            throw new RuntimeException();
        }
    }

    /**
     * Enables all cells that contain a piece that belongs to the player
     * @param player
     */
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

    /**
     * Creates a popup for evolving a pawn.
     * @param pawn
     */
    public void pawnEvolution(Pawn pawn) {
        JFrame popUp = new JFrame("Pawn Evolution");
        popUp.setLayout(new FlowLayout());
        JButton option1 = new JButton("Pawn");
        option1.addActionListener(e -> {pawn.evolve(option1.getText()); popUp.dispose(); });
        JButton option2 = new JButton("Rook");
        option1.addActionListener(e -> {pawn.evolve(option2.getText()); popUp.dispose(); });
        JButton option3 = new JButton("Bishop");
        option1.addActionListener(e -> {pawn.evolve(option3.getText()); popUp.dispose(); });
        JButton option4 = new JButton("Knight");
        option1.addActionListener(e -> {pawn.evolve(option4.getText()); popUp.dispose(); });
        JButton option5 = new JButton("Queen");
        option1.addActionListener(e -> {pawn.evolve(option5.getText()); popUp.dispose(); });
        popUp.add(option1);
        popUp.add(option2);
        popUp.add(option3);
        popUp.add(option4);
        popUp.add(option5);

        popUp.setVisible(true);
        popUp.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
    }

    /**
     * Throws up a popup when an end of game condition is reached.
     * @param condition
     * @param player
     */
    public void endOfGamePopUp(String condition, int player) {
        JFrame popUp = new JFrame("Game End");
        JLabel text = new JLabel();
        if (condition.equals("checkmate")) {
            if (player == 1) {
                text = new JLabel("Black Wins");
                text.setForeground(Color.BLACK);
                popUp.getContentPane().setBackground(Color.white);
            } else {
                text = new JLabel("White Wins");
                text.setForeground(Color.WHITE);
                popUp.getContentPane().setBackground(Color.black);
            }
        } else if (condition.equals("stalemate")) {
            text = new JLabel("Stalemate. Both lose. ");
        }
        text.setHorizontalAlignment(SwingConstants.CENTER);
        text.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 50));
        popUp.add(text);

        enablePlayersPieces(0);
        popUp.setVisible(true);
        popUp.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        popUp.pack();
        popUp.setSize(400,200);
        popUp.setLocationRelativeTo(null);
    }

    /**
     * Throws up a popUp when a player is in check.
     * @param player
     */
    public void checkPopUp(int player) {
        checkFrame = new JFrame("Check");
        JLabel text;
        checkFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        Point center = GraphicsEnvironment.getLocalGraphicsEnvironment().getCenterPoint();

        if (player == 1) {
            text = new JLabel("IN CHECK");
            text.setForeground(Color.BLACK);
            checkFrame.getContentPane().setBackground(Color.white);
            checkFrame.setLocation(new Point((int)center.getX() - 350, (int)center.getY() + 250));
        } else {
            text = new JLabel("IN CHECK");
            text.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 50));
            text.setForeground(Color.WHITE);
            checkFrame.getContentPane().setBackground(Color.black);
            checkFrame.setLocation(new Point((int)center.getX() - 350, (int)center.getY() - 275));
        }
        text.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));

        checkFrame.add(text);
        checkFrame.setUndecorated(true);
        checkFrame.setVisible(true);
        checkFrame.pack();
        checkFrame.setSize(100,50);
    }
}
