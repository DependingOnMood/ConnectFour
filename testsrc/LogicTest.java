import static org.junit.Assert.*;

import java.awt.Color;
import org.junit.Test;

public class LogicTest {

  @Test
  public void testCheckHorizontalYes() {
    GameLogic gameLogic = GameLogic.getModelInstance();
    gameLogic.setBoard(1, 1, Color.red);
    gameLogic.setBoard(1, 2, Color.red);
    gameLogic.setBoard(1, 3, Color.red);
    gameLogic.setBoard(1, 4, Color.red);
    assertTrue(gameLogic.checkHorizontal(1, 4));
  }

  @Test
  public void testCheckHorizontalYesRow3() {
    GameLogic gameLogic = GameLogic.getModelInstance();
    gameLogic.setBoard(3, 1, Color.red);
    gameLogic.setBoard(3, 2, Color.red);
    gameLogic.setBoard(3, 3, Color.red);
    gameLogic.setBoard(3, 4, Color.red);
    assertTrue(gameLogic.checkHorizontal(3, 1));
  }

  @Test
  public void testCheckHorizontalNo() {
    GameLogic gameLogic = GameLogic.getModelInstance();
    gameLogic.setBoard(2, 1, Color.red);
    gameLogic.setBoard(2, 2, Color.black);
    gameLogic.setBoard(2, 3, Color.red);
    gameLogic.setBoard(2, 4, Color.red);
    assertTrue(!gameLogic.checkHorizontal(2, 4));
  }

  @Test
  public void testCheckVerticalYes() {
    GameLogic gameLogic = GameLogic.getModelInstance();
    gameLogic.setBoard(1, 1, Color.red);
    gameLogic.setBoard(2, 1, Color.red);
    gameLogic.setBoard(3, 1, Color.red);
    gameLogic.setBoard(4, 1, Color.red);
    assertTrue(gameLogic.checkVertical(3, 1));
  }

  @Test
  public void testCheckVerticalYesCol2() {
    GameLogic gameLogic = GameLogic.getModelInstance();
    gameLogic.setBoard(1, 2, Color.red);
    gameLogic.setBoard(2, 2, Color.red);
    gameLogic.setBoard(3, 2, Color.red);
    gameLogic.setBoard(4, 2, Color.red);
    assertTrue(gameLogic.checkVertical(1, 2));
  }

  @Test
  public void testCheckVerticalNo() {
    GameLogic gameLogic = GameLogic.getModelInstance();
    gameLogic.setBoard(1, 4, Color.red);
    gameLogic.setBoard(2, 4, Color.red);
    gameLogic.setBoard(3, 4, Color.red);
    gameLogic.setBoard(4, 4, Color.black);
    assertTrue(!gameLogic.checkVertical(4, 4));
  }

  @Test
  public void testCheckDiagonalLeftRightYes() {
    GameLogic gameLogic = GameLogic.getModelInstance();
    gameLogic.setBoard(1, 1, Color.red);
    gameLogic.setBoard(2, 2, Color.red);
    gameLogic.setBoard(3, 3, Color.red);
    gameLogic.setBoard(4, 4, Color.red);
    assertTrue(gameLogic.checkDiagonal(1, 1));
  }

  @Test
  public void testCheckDiagonalLeftRightNo() {
    GameLogic gameLogic = GameLogic.getModelInstance();
    gameLogic.setBoard(1, 1, Color.black);
    gameLogic.setBoard(2, 2, Color.red);
    gameLogic.setBoard(3, 3, Color.red);
    gameLogic.setBoard(4, 4, Color.red);
    assertTrue(!gameLogic.checkDiagonal(1, 1));
  }

  @Test
  public void testCheckDiagonalRightLeftYes() {
    GameLogic gameLogic = GameLogic.getModelInstance();
    gameLogic.setBoard(1, 5, Color.black);
    gameLogic.setBoard(2, 4, Color.black);
    gameLogic.setBoard(3, 3, Color.black);
    gameLogic.setBoard(4, 2, Color.black);
    assertTrue(gameLogic.checkDiagonal(3, 3));
  }

  @Test
  public void testCheckDiagonalRightLeftNo() {
    GameLogic gameLogic = GameLogic.getModelInstance();
    gameLogic.setBoard(1, 5, Color.red);
    gameLogic.setBoard(2, 4, Color.black);
    gameLogic.setBoard(3, 3, Color.black);
    gameLogic.setBoard(4, 2, Color.black);
    assertTrue(!gameLogic.checkDiagonal(1, 5));
  }

  @Test
  public void testGameEndWithHorizontal() {
    GameLogic gameLogic = GameLogic.getModelInstance();
    gameLogic.setBoard(1, 1, Color.red);
    gameLogic.setBoard(1, 2, Color.red);
    gameLogic.setBoard(1, 3, Color.red);
    gameLogic.setBoard(1, 4, Color.red);
    assertTrue(gameLogic.checkGameEnd(1, 4));
  }

  @Test
  public void testGameEndWithHorizontalRow4() {
    GameLogic gameLogic = GameLogic.getModelInstance();
    gameLogic.setBoard(4, 1, Color.red);
    gameLogic.setBoard(4, 2, Color.red);
    gameLogic.setBoard(4, 3, Color.red);
    gameLogic.setBoard(4, 4, Color.red);
    assertTrue(gameLogic.checkGameEnd(4, 1));
  }

  @Test
  public void testGameEndWithHorizontalNo() {
    GameLogic gameLogic = GameLogic.getModelInstance();
    gameLogic.setBoard(1, 1, Color.red);
    gameLogic.setBoard(1, 2, Color.red);
    gameLogic.setBoard(1, 3, Color.red);
    gameLogic.setBoard(1, 4, Color.black);
    assertTrue(!gameLogic.checkGameEnd(1, 4));
  }

  @Test
  public void testGameEndWithVerticalYes() {
    GameLogic gameLogic = GameLogic.getModelInstance();
    gameLogic.setBoard(3, 1, Color.red);
    gameLogic.setBoard(2, 1, Color.red);
    gameLogic.setBoard(4, 1, Color.red);
    gameLogic.setBoard(5, 1, Color.red);
    assertTrue(gameLogic.checkGameEnd(4, 1));
  }

  @Test
  public void testGameEndWithVerticalYesCol3() {
    GameLogic gameLogic = GameLogic.getModelInstance();
    gameLogic.setBoard(3, 3, Color.red);
    gameLogic.setBoard(2, 3, Color.red);
    gameLogic.setBoard(4, 3, Color.red);
    gameLogic.setBoard(5, 3, Color.red);
    assertTrue(gameLogic.checkGameEnd(5, 3));
  }

  @Test
  public void testGameEndWithDiagonal() {
    GameLogic gameLogic = GameLogic.getModelInstance();
    gameLogic.setBoard(1, 1, Color.red);
    gameLogic.setBoard(2, 2, Color.red);
    gameLogic.setBoard(3, 3, Color.red);
    gameLogic.setBoard(4, 4, Color.red);
    assertTrue(gameLogic.checkGameEnd(4, 4));
  }

  @Test
  public void testIsBoardFullRed() {
    GameLogic gameLogic = GameLogic.getModelInstance();
    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 7; j++) {
        gameLogic.setBoard(i, j, Color.red);
      }
    }
    assertTrue(!gameLogic.isBoardFull());
  }

  @Test
  public void testIsBoardFullBlack() {
    GameLogic gameLogic = GameLogic.getModelInstance();
    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 7; j++) {
        gameLogic.setBoard(i, j, Color.black);
      }
    }
    assertTrue(!gameLogic.isBoardFull());
  }

  @Test
  public void testCanDropWithYes() {
    GameLogic gameLogic = GameLogic.getModelInstance();
    gameLogic.setRows(1, 4);
    assertTrue(gameLogic.canDrop(1));
  }

  @Test
  public void testCanDropWithNo() {
    GameLogic gameLogic = GameLogic.getModelInstance();
    gameLogic.setRows(1, 5);
    assertTrue(!gameLogic.canDrop(1));
  }

  @Test
  public void testIsWinWithFullColumn() {
    GameLogic gameLogic = GameLogic.getModelInstance();
    gameLogic.setRows(1, 5);
    assertTrue(!gameLogic.isWinningngMove(1, Color.red));

  }

  @Test
  public void testIsWinWithNo() {
    GameLogic gameLogic = GameLogic.getModelInstance();
    gameLogic.setRows(5, 5);
    gameLogic.setBoard(2, 2, Color.red);
    gameLogic.setBoard(3, 3, Color.red);
    gameLogic.setBoard(4, 4, Color.black);
    assertTrue(!gameLogic.isWinningngMove(3, Color.red));
  }

  @Test
  public void testIsWinWithYes() {
    GameLogic gameLogic = GameLogic.getModelInstance();
    gameLogic.setRows(5, 5);
    gameLogic.setBoard(2, 2, Color.red);
    gameLogic.setBoard(3, 3, Color.red);
    gameLogic.setBoard(4, 4, Color.red);
    assertTrue(gameLogic.isWinningngMove(1, Color.red));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testCanDropWithNegativeColumn() {
    GameLogic gameLogic = GameLogic.getModelInstance();
    gameLogic.canDrop(-1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testCanDropWithGreaterColumn() {
    GameLogic gameLogic = GameLogic.getModelInstance();
    gameLogic.canDrop(10);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testIsWinningMoveWithNegativeColumn() {
    GameLogic gameLogic = GameLogic.getModelInstance();
    gameLogic.isWinningngMove(-1, Color.red);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testIsWinningMoveWithGreaterColumn() {
    GameLogic gameLogic = GameLogic.getModelInstance();
    gameLogic.isWinningngMove(10, Color.red);
  }
}
