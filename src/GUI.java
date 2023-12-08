import javax.swing.*;
import java.awt.*;

public class GUI {
 // general UI for starting game and playing game
    private static JFrame mainWindow;
    private static JFrame gameWindow;
     public static void main(String[] args) {
         mainWindow = new JFrame("Chess");
         JButton newGameButton = new JButton("New Game");
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

         gameWindow = new JFrame("Chess");
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

         JPanel whiteTakenPieces = new JPanel();
         whiteTakenPieces.setLayout(new GridLayout(2,8));
         whiteTakenPieces.setPreferredSize(new Dimension(500, 100));
         gameWindow.add(whiteTakenPieces, BorderLayout.NORTH);
         gameWindow.add(boardGrid, BorderLayout.CENTER);
         JPanel blackTakenPieces = new JPanel();
         blackTakenPieces.setLayout(new GridLayout(2,8));
         blackTakenPieces.setPreferredSize(new Dimension(500, 100));
         gameWindow.add(blackTakenPieces, BorderLayout.SOUTH);
         gameWindow.revalidate(); gameWindow.pack();
         gameWindow.setLocationRelativeTo(null);
         gameWindow.setVisible(true);
         gameWindow.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

         int player = 1;
         for (Cell[] row : game.getCell2DArray()) {
             for (Cell cell : row) {
                 if (cell.getPiece() != null && cell.getPiece().getPlayer() == player) {
                     cell.setEnabled(true);
                 } else {
                     cell.setEnabled(false);
                 }
             }
         }
     }
 }

