import java.awt.Color;

/**
 * this is a factory for creating a player by getting the type and color
 * 
 * @author Dongbo
 *
 */
public class PlayerCreater {

  /**
   * it creates a player by getting the type and color
   * 
   * @param type
   * @param color
   * @return Player object created, null if nothing created
   */
  public static Player createPlayer(PlayerType type, Color color) {
    if (type == null || color == null) {

      throw new IllegalArgumentException("play type or color is null");
    }

    if (type == PlayerType.HUMAN) {

      return new HumanPlayer(color);

    } else if (type == PlayerType.COMPUTER) {

      return new ComputerPlayer(color);
    }
    return null;
  }
}
