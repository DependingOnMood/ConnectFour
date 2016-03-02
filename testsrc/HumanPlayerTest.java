import static org.junit.Assert.*;

import java.awt.Color;

import org.junit.Test;


public class HumanPlayerTest {

  @Test
  public void testGetColorRed() {
    HumanPlayer humanPlayer = new HumanPlayer(Color.red);
    Color expectedColor = Color.red;
    Color actualColor = humanPlayer.getColor();
    assertEquals(expectedColor, actualColor);
  }

  @Test
  public void testGetColorBlack() {
    HumanPlayer humanPlayer = new HumanPlayer(Color.black);
    Color expectedColor = Color.black;
    Color actualColor = humanPlayer.getColor();
    assertEquals(expectedColor, actualColor);
  }

  @Test
  public void testGetPlayerType() {
    HumanPlayer humanPlayer = new HumanPlayer(Color.red);
    PlayerType expectedType = PlayerType.HUMAN;
    PlayerType actualType = humanPlayer.getPlayerType();
    assertEquals(expectedType, actualType);
  }

  @Test
  public void testSetAndGetMoveCol() {
    HumanPlayer humanPlayer = new HumanPlayer(Color.red);
    humanPlayer.setMoveCol(2);
    int expectedCol = 2;
    int actualCol = humanPlayer.getMoveCol();
    assertEquals(expectedCol, actualCol);
  }

  @Test
  public void testToStringRed() {
    HumanPlayer humanPlayer = new HumanPlayer(Color.red);
    humanPlayer.setMoveCol(1);
    String expected = "HumanPlayer Color: red Column: 1";
    String actual = humanPlayer.toString();
    assertEquals(expected, actual);
  }

  @Test
  public void testToStringBlack() {
    HumanPlayer humanPlayer = new HumanPlayer(Color.black);
    humanPlayer.setMoveCol(3);
    String expected = "HumanPlayer Color: black Column: 3";
    String actual = humanPlayer.toString();
    assertEquals(expected, actual);
  }

  @Test
  public void testEquals() {
    HumanPlayer humanPlayer1 = new HumanPlayer(Color.red);
    HumanPlayer humanPlayer2 = new HumanPlayer(Color.red);
    humanPlayer1.setMoveCol(3);
    humanPlayer2.setMoveCol(3);
    assertTrue(humanPlayer1.equals(humanPlayer2) && humanPlayer2.equals(humanPlayer1));
  }

  @Test
  public void testNotEqualsColor() {
    HumanPlayer humanPlayer1 = new HumanPlayer(Color.red);
    HumanPlayer humanPlayer2 = new HumanPlayer(Color.black);
    humanPlayer1.setMoveCol(3);
    humanPlayer2.setMoveCol(3);
    assertTrue(!humanPlayer1.equals(humanPlayer2) && !humanPlayer2.equals(humanPlayer1));
  }

  @Test
  public void testNotEqualsCol() {
    HumanPlayer humanPlayer1 = new HumanPlayer(Color.red);
    HumanPlayer humanPlayer2 = new HumanPlayer(Color.red);
    humanPlayer1.setMoveCol(3);
    humanPlayer2.setMoveCol(4);
    assertTrue(!humanPlayer1.equals(humanPlayer2) && !humanPlayer2.equals(humanPlayer1));
  }

  @Test
  public void testNotEqualsNull() {
    HumanPlayer humanPlayer1 = new HumanPlayer(Color.red);
    HumanPlayer humanPlayer2 = new HumanPlayer(null);
    humanPlayer1.setMoveCol(3);
    humanPlayer2.setMoveCol(4);
    assertTrue(!humanPlayer1.equals(humanPlayer2) && !humanPlayer2.equals(humanPlayer1));
  }

  @Test
  public void testEqualsHashCode() {
    HumanPlayer humanPlayer1 = new HumanPlayer(Color.red);
    HumanPlayer humanPlayer2 = new HumanPlayer(Color.red);
    humanPlayer1.setMoveCol(3);
    humanPlayer2.setMoveCol(3);
    assertTrue(humanPlayer1.hashCode() == humanPlayer2.hashCode());
  }

  @Test
  public void testNotEqualsHashCodeColor() {
    HumanPlayer humanPlayer1 = new HumanPlayer(Color.red);
    HumanPlayer humanPlayer2 = new HumanPlayer(Color.black);
    humanPlayer1.setMoveCol(3);
    humanPlayer2.setMoveCol(3);
    assertTrue(humanPlayer1.hashCode() != humanPlayer2.hashCode());
  }

  @Test
  public void testNotEqualsHashCodeCol() {
    HumanPlayer humanPlayer1 = new HumanPlayer(Color.red);
    HumanPlayer humanPlayer2 = new HumanPlayer(Color.red);
    humanPlayer1.setMoveCol(3);
    humanPlayer2.setMoveCol(4);
    assertTrue(humanPlayer1.hashCode() != humanPlayer2.hashCode());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testMoveWithNull() {
    HumanPlayer humanPlayer = new HumanPlayer(Color.red);
    humanPlayer.setMoveCol(3);
    humanPlayer.move(null);
  }


}
