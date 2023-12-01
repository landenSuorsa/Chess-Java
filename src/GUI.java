import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
         mainWindow.setSize(400,200);
         mainWindow.setLocationRelativeTo(null);
         mainWindow.setVisible(true);
     }

     public static void newGame() {
         mainWindow.setVisible(false);

         gameWindow = new JFrame("Chess");
         Board game = new Board();
         JPanel boardGrid = new JPanel();
         boardGrid.setLayout(new GridLayout(8,8));
         for (Cell[] row : game.getCell2DArray()) {
             for (Cell cell : row) {
                 boardGrid.add(cell);
             }
         }

         gameWindow.getContentPane().add(boardGrid);
         gameWindow.pack();
         gameWindow.setSize(600,600);
         gameWindow.setLocationRelativeTo(null);
         gameWindow.setVisible(true);

     }
}
