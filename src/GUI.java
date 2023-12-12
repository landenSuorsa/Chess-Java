import javax.swing.*;
import java.awt.*;

/**
 * Creates the main menu screen.
 * @author Landen Suorsa, Johnathan Hall, Christopher Hall
 */
public class GUI {
 // general UI for starting game and playing game
    private static JFrame mainWindow;

    public static void main(String[] args) {
         mainWindow = new JFrame("Chess");
         mainWindow.add(new JButton("New Game"));
         ((JButton)mainWindow.getContentPane().getComponent(0)).addActionListener(e -> newGame());

         mainWindow.pack();
         mainWindow.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
         mainWindow.setSize(400,200);
         mainWindow.setLocationRelativeTo(null);
         mainWindow.setVisible(true);
     }

     public static void newGame() {
         mainWindow.setVisible(false);

         JFrame gameWindow = new JFrame("Chess");
         gameWindow.setSize(400,600);
         gameWindow.setResizable(false);
         gameWindow.setLayout(new BorderLayout());
         Board game = new Board();
         JPanel boardGrid = new JPanel();
         boardGrid.setPreferredSize(new Dimension(400,400));
         boardGrid.setLayout(new GridLayout(8,8));
         for (Cell[] row : game.getCell2DArray()) {
             for (Cell cell : row) {
                 boardGrid.add(cell);
             }
         }

         gameWindow.add(game.getWhiteTakenPieces(), BorderLayout.NORTH);
         gameWindow.add(boardGrid, BorderLayout.CENTER);
         gameWindow.add(game.getBlackTakenPieces(), BorderLayout.SOUTH);
         gameWindow.revalidate(); gameWindow.pack();
         gameWindow.setLocationRelativeTo(null);
         gameWindow.setVisible(true);
         gameWindow.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

         int player = 1;
         for (Cell[] row : game.getCell2DArray()) {
             for (Cell cell : row) {
                 cell.setEnabled(cell.getPiece() != null && cell.getPiece().getPlayer() == player);
             }
         }
     }
 }

