import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import static java.awt.BorderLayout.*;

/**
 * Creates the main menu screen and a new game.
 * @author Landen Suorsa, Johnathan Hall, Christopher Hall
 */
public class GUI {
    private static JFrame mainWindow;

    public static void main(String[] args) {
         // creates the main screen.
         mainWindow = new JFrame("Chess");
         mainWindow.getContentPane().setBackground(Color.GRAY);
         mainWindow.setLayout(new BorderLayout());
         mainWindow.add(new JLabel(new ImageIcon("1King.png")), EAST);

         // creates the new game button
         JButton mainButton = new JButton("New Game");
         mainButton.setBackground(Color.lightGray);
         mainButton.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 35));
         mainButton.addActionListener(e -> newGame());
         mainWindow.add(mainButton, CENTER);

         // creates the exit button
         JButton exitButton = new JButton("Exit");
         exitButton.setBackground(Color.lightGray);
         exitButton.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 35));
         exitButton.addActionListener(e -> System.exit(0));
         mainWindow.add(exitButton, SOUTH);

         mainWindow.add(new JLabel(new ImageIcon("2King.png")), WEST);

         // finishes initializing the main screen.
         mainWindow.pack();
         mainWindow.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
         mainWindow.setSize(400,200);
         mainWindow.setLocationRelativeTo(null);
         mainWindow.setVisible(true);
     }

    /**
     * creates a new chess game.
     */
    public static void newGame() {
         // hides the main screen.
         mainWindow.setVisible(false);

         // creates the game window.
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
         gameWindow.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

         // goes back to the new game window if this window is closed.
         gameWindow.addWindowListener(new WindowAdapter() {
             @Override
             public void windowClosed(WindowEvent e) {
                mainWindow.setVisible(true);
                game.getCheckFrame().setVisible(false);
             }
         });

         // enables the first player's pieces. The rest is done in the board class.
         int player = 1;
         for (Cell[] row : game.getCell2DArray()) {
             for (Cell cell : row) {
                 cell.setEnabled(cell.getPiece() != null && cell.getPiece().getPlayer() == player);
             }
         }
     }
 }

