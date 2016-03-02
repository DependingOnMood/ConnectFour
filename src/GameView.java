import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.Border;

/**
 * This Class is the GameView of the game, which include a start/restart view and the board view
 * 
 * @author Dongbo
 *
 */
public class GameView implements Listener, ActionListener {
  private GameLogic gameLogic;
  private Color playerColor;

  private int row;
  private int col;

  private JFrame startFrame;
  private JFrame gameFrame;

  private JPanel startFrameButtonPanel;
  private JPanel gameFrameBoardPanel;
  private JPanel gameFrameButtonPanel;
  private JPanel[][] gameFrameBoardPanels;

  private JButton startFrameStartButton;
  private JButton startFrameRestartButton;
  private JButton startFrameExitButton;

  private JButton[] gameFrameDropButtons;

  private JTextArea startFrameWelcomeText;
  private JTextArea startFrameResultText;
  private JTextArea gameFrameTurnText;

  private Border line;

  public GameView(GameLogic gameLogic, Color color) {
    this.gameLogic = gameLogic;
    this.gameLogic.addListener(this);

    row = 6;
    col = 7;

    startFrame = new JFrame("Connect 4");
    gameFrame = new JFrame("Connect 4");

    gameFrameBoardPanel = new JPanel(new GridLayout(row, col));
    gameFrameButtonPanel = new JPanel();

    startFrameStartButton = new JButton("Start");
    startFrameRestartButton = new JButton("Restart");
    startFrameExitButton = new JButton("Exit");

    startFrameButtonPanel = new JPanel();

    gameFrameDropButtons = new JButton[col];
    gameFrameBoardPanels = new JPanel[row][col];

    line = BorderFactory.createLineBorder(Color.blue);

    startFrameWelcomeText = new JTextArea();
    startFrameResultText = new JTextArea();
    gameFrameTurnText = new JTextArea();

    this.playerColor = color;

    startFrameStartButton.addActionListener(this);
    startFrameRestartButton.addActionListener(this);
    startFrameExitButton.addActionListener(this);

    setStartBoard();
  }


  /**
   * it shows a view with a startFrameWelcomeText view has the welcome/result info, start/restart
   * and exit.
   */
  @Override
  public void setStartBoard() {
    startFrameWelcomeText.setText("Welcome!");

    gameFrameDropButtons = new JButton[col];
    startFrameStartButton.setPreferredSize(new Dimension(150, 50));
    startFrameRestartButton.setPreferredSize(new Dimension(150, 50));
    startFrameExitButton.setPreferredSize(new Dimension(150, 50));

    startFrameButtonPanel.add(startFrameStartButton);
    startFrameButtonPanel.add(startFrameExitButton);

    startFrame.add(startFrameWelcomeText, BorderLayout.CENTER);
    startFrame.add(startFrameButtonPanel, BorderLayout.SOUTH);

    startFrame.setVisible(true);
    startFrame.setSize(320, 110);
    startFrame.setLocationRelativeTo(null);
    startFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  }

  @Override
  public void actionPerformed(ActionEvent e) {

    if (e.getSource() == startFrameStartButton) {
      gameLogic.fireGameStartEvent();
    } else if (e.getSource() == startFrameRestartButton) {
      gameLogic.fireGameRestartEvent();
    } else if (e.getSource() == startFrameExitButton) {
      gameLogic.fireGameExitEvent();
    } else {

      for (int i = 0; i < col; i++) {
        if (e.getSource() == gameFrameDropButtons[i]) {
          gameLogic.drop(i);
        }
      }
    }

  }


  /**
   * start the game, called when user clicked start/restart button, generate the game board
   */
  @Override
  public void gameStart() {
    startFrame.dispose();

    gameFrameButtonPanel.setLayout(new GridLayout(0, col));

    for (int i = 0; i < col; i++) {
      JButton dropButton = new JButton("X");
      dropButton.addActionListener(this);
      gameFrameButtonPanel.add(dropButton);
      gameFrameDropButtons[i] = dropButton;
    }

    gameFrameBoardPanels = new JPanel[row][col];

    for (int i = 0; i < row; i++) {
      for (int j = 0; j < col; j++) {
        JPanel panel = new JPanel();

        panel.setBorder(line);

        gameFrameBoardPanels[i][j] = panel;
        gameFrameBoardPanel.add(gameFrameBoardPanels[i][j]);
      }
    }

    if (this.playerColor == Color.red) {
      gameFrame.setTitle("Red Player Window");
    } else {
      gameFrame.setTitle("Black Player Window");
    }

    gameFrameTurnText.setText("Red Turn!");

    gameFrame.add(gameFrameTurnText, BorderLayout.SOUTH);
    gameFrame.add(gameFrameBoardPanel, BorderLayout.CENTER);

    gameFrame.setVisible(true);
    gameFrame.setSize(230, 230);

    gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    gameFrame.add(gameFrameButtonPanel, BorderLayout.NORTH);

    gameFrame.setVisible(true);

    if (this.playerColor == Color.red) {
      gameFrame.setEnabled(true);
    } else {
      gameFrame.setEnabled(false);
    }

  }

  /**
   * Move piece show in the UI
   */
  @Override
  public void gameMove(int row, int col, Color color) {

    gameFrameBoardPanels[this.row - row - 1][col].setBackground(color);

    if (color == Color.black) {

      gameFrameTurnText.setText("Red Turn!");

      if (this.playerColor == Color.black) {
        gameFrame.setEnabled(false);
      } else {
        gameFrame.setEnabled(true);
      }

    } else {

      gameFrameTurnText.setText("Black Turn!");

      if (this.playerColor == Color.red) {
        gameFrame.setEnabled(false);
      } else {
        gameFrame.setEnabled(true);
      }

    }

    if (row >= 5) {
      gameFrameDropButtons[col].removeActionListener(this);
    }
  }

  /**
   * show UI when game is tie
   */
  @Override
  public void gameTie() {

    String result = "Tie!";

    setGameEndUI(result);
  }

  /**
   * show UI when game is not tie, red or black wins.
   */
  @Override
  public void gameWin(Color color) {

    String result = "";

    if (color == Color.black) {
      result = "Black Wins!";
    } else {
      result = "Red Wins!";
    }

    setGameEndUI(result);
  }

  /**
   * set up the UI when Game end: remove the drop buttons listener, show result and restart screen
   * 
   * @param result
   */
  public void setGameEndUI(String result) {
    for (JButton button : gameFrameDropButtons) {
      button.removeActionListener(this);
    }

    startFrameResultText.setText(result);
    startFrameButtonPanel.remove(startFrameStartButton);
    startFrameButtonPanel.add(startFrameRestartButton);
    startFrame.remove(startFrameWelcomeText);
    startFrame.add(startFrameResultText, BorderLayout.CENTER);

    startFrame.setVisible(true);
  }

  /**
   * Restart the game.
   */
  @Override
  public void gameRestart() {
    gameFrame.dispose();
    gameFrameButtonPanel.removeAll();
    gameFrameBoardPanel.removeAll();
  }

  /**
   * Exit the game.
   */
  @Override
  public void gameExit() {
    gameLogic.deleteListener(this);
    System.exit(0);
  }


}
