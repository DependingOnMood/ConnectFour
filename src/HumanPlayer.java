import java.awt.Color;

/**
 * This class create a human player that player the game.
 * 
 * @author Dongbo
 *
 */
public class HumanPlayer implements Player {

  private Color playerColor;
  private int dropCol;

  public HumanPlayer(Color color) {
    this.playerColor = color;
  }

  /**
   * This method is used by the gameLogic to denote to the player object where the drop column move
   * has to be made.
   */
  @Override
  public void setMoveCol(int col) {
    this.dropCol = col;
  }

  /**
   * This method returns the column of the last move made by the Human player
   */
  @Override
  public int getMoveCol() {
    return this.dropCol;
  }

  /**
   * make the move.
   */
  @Override
  public void move(GameLogic gameLogic) {
    if (gameLogic == null) {
      throw new IllegalArgumentException("gameLogic is null!");
    }
    gameLogic.makeMove(playerColor, dropCol);
  }

  /**
   * This method returns the Color for this player
   */
  @Override
  public Color getColor() {
    return playerColor;
  }

  /**
   * This method returns the type of the player which is HUMAN
   */
  @Override
  public PlayerType getPlayerType() {
    return PlayerType.HUMAN;
  }

  @Override
  public String toString() {
    String outColor = "";

    if (this.playerColor == Color.red) {
      outColor = "red";
    } else {
      outColor = "black";
    }

    return "HumanPlayer Color: " + outColor + " Column: " + this.dropCol;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 17;
    result = prime * result + ((playerColor == null) ? 0 : playerColor.hashCode());
    result = prime * result + dropCol;
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (!(obj instanceof HumanPlayer)) {
      return false;
    }

    HumanPlayer other = (HumanPlayer) obj;

    if (playerColor == null) {
      if (other.playerColor != null) {
        return false;
      }
    } else {
      if (!playerColor.equals(other.playerColor)) {
        return false;
      }
    }
    if (dropCol != other.dropCol) {
      return false;
    }
    return true;
  }

}
