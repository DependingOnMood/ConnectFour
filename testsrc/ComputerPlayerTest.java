import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.awt.Color;

import org.junit.Test;


public class ComputerPlayerTest {

  @Test
  public void testGetColorBlack() {
    ComputerPlayer computerPlayer = new ComputerPlayer(Color.black);
    Color expectedColor = Color.black;
    Color actualColor = computerPlayer.getColor();
    assertEquals(expectedColor, actualColor);
  }

  @Test
  public void testGetColorRed() {
    ComputerPlayer computerPlayer = new ComputerPlayer(Color.red);
    Color expectedColor = Color.red;
    Color actualColor = computerPlayer.getColor();
    assertEquals(expectedColor, actualColor);
  }

  @Test
  public void testGetPlayerType() {
    ComputerPlayer computerPlayer = new ComputerPlayer(Color.black);
    PlayerType expectedType = PlayerType.COMPUTER;
    PlayerType actualType = computerPlayer.getPlayerType();
    assertEquals(expectedType, actualType);
  }

  @Test
  public void testSetAndGetMoveCol() {
    ComputerPlayer computerPlayer = new ComputerPlayer(Color.black);
    computerPlayer.setMoveCol(2);
    int expectedCol = 2;
    int actualCol = computerPlayer.getMoveCol();
    assertEquals(expectedCol, actualCol);
  }

  @Test
  public void testToStringBlack() {
    ComputerPlayer computerPlayer = new ComputerPlayer(Color.black);
    computerPlayer.setMoveCol(3);
    String expected = "ComputerPlayer Color: black Column: 3";
    String actual = computerPlayer.toString();
    assertEquals(expected, actual);
  }

  @Test
  public void testToStringRed() {
    ComputerPlayer computerPlayer = new ComputerPlayer(Color.red);
    computerPlayer.setMoveCol(3);
    String expected = "ComputerPlayer Color: red Column: 3";
    String actual = computerPlayer.toString();
    assertEquals(expected, actual);
  }

  @Test
  public void testEquals() {
    ComputerPlayer computerPlayer1 = new ComputerPlayer(Color.black);
    ComputerPlayer computerPlayer2 = new ComputerPlayer(Color.black);
    computerPlayer1.setMoveCol(3);
    computerPlayer2.setMoveCol(3);
    assertTrue(computerPlayer1.equals(computerPlayer2) && computerPlayer2.equals(computerPlayer1));
  }

  @Test
  public void testNotEqualsColor() {
    ComputerPlayer computerPlayer1 = new ComputerPlayer(Color.red);
    ComputerPlayer computerPlayer2 = new ComputerPlayer(Color.black);
    computerPlayer1.setMoveCol(3);
    computerPlayer2.setMoveCol(3);
    assertTrue(!computerPlayer1.equals(computerPlayer2) && !computerPlayer2.equals(computerPlayer1));
  }

  @Test
  public void testNotEqualsCol() {
    ComputerPlayer computerPlayer1 = new ComputerPlayer(Color.black);
    ComputerPlayer computerPlayer2 = new ComputerPlayer(Color.black);
    computerPlayer1.setMoveCol(3);
    computerPlayer2.setMoveCol(4);
    assertTrue(!computerPlayer1.equals(computerPlayer2) && !computerPlayer2.equals(computerPlayer1));
  }

  @Test
  public void testNotEqualsNull() {
    ComputerPlayer computerPlayer1 = new ComputerPlayer(Color.black);
    ComputerPlayer computerPlayer2 = new ComputerPlayer(null);
    computerPlayer1.setMoveCol(3);
    computerPlayer2.setMoveCol(4);
    assertTrue(!computerPlayer1.equals(computerPlayer2) && !computerPlayer2.equals(computerPlayer1));
  }

  @Test
  public void testEqualsHashCode() {
    ComputerPlayer computerPlayer1 = new ComputerPlayer(Color.black);
    ComputerPlayer computerPlayer2 = new ComputerPlayer(Color.black);
    computerPlayer1.setMoveCol(3);
    computerPlayer2.setMoveCol(3);
    assertTrue(computerPlayer1.hashCode() == computerPlayer2.hashCode());
  }

  @Test
  public void testNotEqualsHashCodeColor() {
    ComputerPlayer computerPlayer1 = new ComputerPlayer(Color.red);
    ComputerPlayer computerPlayer2 = new ComputerPlayer(Color.black);
    computerPlayer1.setMoveCol(3);
    computerPlayer2.setMoveCol(3);
    assertTrue(computerPlayer1.hashCode() != computerPlayer2.hashCode());
  }

  @Test
  public void testNotEqualsHashCodeCol() {
    ComputerPlayer computerPlayer1 = new ComputerPlayer(Color.black);
    ComputerPlayer computerPlayer2 = new ComputerPlayer(Color.black);
    computerPlayer1.setMoveCol(3);
    computerPlayer2.setMoveCol(4);
    assertTrue(computerPlayer1.hashCode() != computerPlayer2.hashCode());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testMoveWithNull() {
    ComputerPlayer computerPlayer = new ComputerPlayer(Color.black);
    computerPlayer.setMoveCol(3);
    computerPlayer.move(null);
  }

}
