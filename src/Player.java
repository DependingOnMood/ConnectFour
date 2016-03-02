import java.awt.Color;

/**
 * this interface is used for create players. It has some method needed for a player to play, and
 * some helper methods for other classes.
 * 
 * @author Dongbo
 *
 */
public interface Player {

  /**
   * make the move.
   * 
   * @param gameLogic
   */
  public void move(GameLogic gameLogic);

  /**
   * set the col
   * 
   * @param col
   */
  public void setMoveCol(int col);

  /**
   * 
   * @return the column of the last move made by player
   */
  public int getMoveCol();

  /**
   * 
   * @return returns player's color
   */
  public Color getColor();

  /**
   * 
   * @return the player's type
   */
  public PlayerType getPlayerType();


}
