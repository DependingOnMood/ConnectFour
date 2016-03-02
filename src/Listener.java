import java.awt.Color;

/**
 * This is the interface for the GameView, used for observer pattern.
 * 
 * @author Dongbo
 *
 */
public interface Listener {

  /**
   * it shows a view with a text view has the welcome/result info, start/restart and exit.
   */
  void setStartBoard();

  /**
   * start the game, called when user clicked start/restart button, generate the game board
   */
  void gameStart();

  /**
   * Move piece show in the UI
   *
   * @param row
   * @param col
   * @param color
   */
  void gameMove(int row, int col, Color color);

  /**
   * show UI when game is tie
   */
  void gameTie();

  /**
   * show UI when game is not tie, red or black wins.
   * 
   * @param color
   */
  void gameWin(Color color);

  /**
   * Restart the game.
   */
  void gameRestart();

  /**
   * Exit the game.
   */
  void gameExit();
}
