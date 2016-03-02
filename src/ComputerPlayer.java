import java.awt.Color;
import java.security.SecureRandom;

/**
 * This class create a computer player that player the game.
 * 
 * @author Dongbo
 *
 */
public class ComputerPlayer implements Player {
  private Color playerColor;
  private Color againstColor;
  private int dropCol;

  public ComputerPlayer(Color color) {
    this.playerColor = color;
  }

  /**
   * make the move.
   */
  @Override
  public void move(GameLogic gameLogic) {
    if (gameLogic == null) {
      throw new IllegalArgumentException("gameLogic is null!");
    }

    if (playerColor == Color.red) {
      againstColor = Color.black;
    } else {
      againstColor = Color.red;
    }

    int i = 0;

    while (i < 7) {
      if (gameLogic.isWinningngMove(i, playerColor)) {
        setMoveCol(i);
        gameLogic.makeMove(playerColor, dropCol);
        break;
      }
      i++;
    }

    if (i == 7) {
      int j = 0;

      while (j < 7) {
        if (gameLogic.isWinningngMove(j, againstColor)) {
          setMoveCol(j);
          gameLogic.makeMove(playerColor, dropCol);
          break;
        }
        j++;
      }

      if (j == 7) {
        SecureRandom random = new SecureRandom();

        while (true) {
          int rand = random.nextInt(7);
          if (gameLogic.canDrop(rand)) {
            setMoveCol(rand);
            gameLogic.makeMove(playerColor, dropCol);
            break;
          }
        }

      }

    }
  }

  /**
   * set the col
   */
  @Override
  public void setMoveCol(int col) {
    this.dropCol = col;
  }

  /**
   * returns the column of the last move made by player
   */
  @Override
  public int getMoveCol() {
    return this.dropCol;
  }

  /**
   * returns player's color
   */
  @Override
  public Color getColor() {
    return playerColor;
  }

  /**
   * returns the player's type
   */
  @Override
  public PlayerType getPlayerType() {
    return PlayerType.COMPUTER;
  }

  @Override
  public String toString() {
    String outColor = "";

    if (this.playerColor == Color.red) {
      outColor = "red";
    } else {
      outColor = "black";
    }
    return "ComputerPlayer Color: " + outColor + " Column: " + this.dropCol;
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
    if (!(obj instanceof ComputerPlayer)) {
      return false;
    }
    ComputerPlayer other = (ComputerPlayer) obj;
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
