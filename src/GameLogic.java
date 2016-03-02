import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * This is the gameLogic class for the game, it has the methods that player need for make a move and
 * fire methods send action to the views
 * 
 * @author Dongbo
 *
 */
public class GameLogic {
  private List<Listener> listeners = new ArrayList<Listener>();

  private Color[][] board = new Color[6][7];
  private int[] numOfPieceInRows = new int[7];

  private int row;

  private GameMode mode;

  private Player redPlayer;
  private Player blackPlayer;

  private Color turn;

  private static final GameLogic gameLogic = new GameLogic();

  private GameLogic() {}

  /**
   * This method is used to return the only one instance of the GameLogic.
   * 
   * @return
   */
  public static GameLogic getModelInstance() {
    return gameLogic;
  }

  /**
   * add the listener for views
   * 
   * @param listerner
   */
  public void addListener(Listener listener) {
    listeners.add(listener);
  }

  /**
   * remove the listener for views
   * 
   * @param listener
   */
  public void deleteListener(Listener listener) {
    listeners.remove(listener);
  }

  /**
   * initialize the parameters for the game
   */
  public void initialGame() {
    for (int i = 0; i < 7; i++) {
      numOfPieceInRows[i] = 0;
    }

    for (int i = 0; i < 6; i++) {
      for (int j = 0; j < 7; j++) {
        board[i][j] = null;
      }
    }

    turn = Color.red;
  }

  /**
   * when the player makes a move, drop a piece on the move col
   * 
   * @param col
   */
  public void drop(int col) {
    if (numOfPieceInRows[col] >= 6) {
      throw new IllegalArgumentException("This column if full!");
    }

    if (mode == GameMode.SINGLE) {
      redPlayer.setMoveCol(col);
      redPlayer.move(this);
      blackPlayer.move(this);

    } else if (mode == GameMode.DOUBLE) {

      if (turn == Color.red) {
        redPlayer.setMoveCol(col);
        redPlayer.move(this);
        turn = Color.black;
      } else {
        blackPlayer.setMoveCol(col);
        blackPlayer.move(this);
        turn = Color.red;
      }
    }
  }

  /**
   * check if the col is full or not, if it still can drop pieces
   * 
   * @param col
   * @return true if can drop else false.
   */
  public boolean canDrop(int col) {
    if (col < 0 || col > 6) {
      throw new IllegalArgumentException("col number not expected: " + col);
    }
    int i = numOfPieceInRows[col];
    i++;

    if (i >= 6) {
      return false;
    } else {
      return true;
    }
  }

  /**
   * makeMove method, should call by player and make the move
   * 
   * @param color
   * @param col
   */
  public void makeMove(Color color, int col) {

    row = numOfPieceInRows[col]++;

    board[row][col] = color;

    fireGameMoveEvent(row, col, color);
    if (checkGameEnd(row, col)) {
      fireGameWinEvent(color);
    }
    if (isBoardFull()) {
      fireGameTieEvent();
    }
  }

  /**
   * get the mode choose, to create either human vs human players or human vs computer players
   * 
   * @param mode
   */
  public void createPlayers(GameMode mode) {
    if (mode == GameMode.SINGLE) {
      redPlayer = PlayerCreater.createPlayer(PlayerType.HUMAN, Color.red);
      blackPlayer = PlayerCreater.createPlayer(PlayerType.COMPUTER, Color.black);

    } else if (mode == GameMode.DOUBLE) {
      redPlayer = PlayerCreater.createPlayer(PlayerType.HUMAN, Color.red);
      blackPlayer = PlayerCreater.createPlayer(PlayerType.HUMAN, Color.black);
    }

    turn = Color.red;
    this.mode = mode;
  }


  /**
   * Check if there is a horizontal win
   * 
   * @param row
   * @param col
   * @return true if horizontal win else false
   */
  public boolean checkHorizontal(int row, int col) {
    int win = 0;
    int i = col;
    Color c = board[row][col];
    while (i <= 6 && board[row][i] == c && win < 4) {
      win++;
      i++;
    }
    i = col;
    while (i > 0 && board[row][i - 1] == c && win < 4) {
      win++;
      i--;
    }
    if (win == 4) {
      return true;
    }
    return false;
  }

  /**
   * Check if there is a vertical win
   * 
   * @param row
   * @param col
   * @return true if vertical win else false
   */
  public boolean checkVertical(int row, int col) {
    int win = 0;
    int i = row;
    Color c = board[row][col];
    while (i <= 5 && board[i][col] == c && win < 4) {
      win++;
      i++;
    }
    
    i = row;
    while (i > 0 && board[i - 1][col] == c && win < 4) {
      win++;
      i--;
    }
    
    if (win == 4) {
      return true;
    }
    return false;
  }

  /**
   * Check if there is a diagonal win
   * 
   * @param row
   * @param col
   * @return true if diagonal win else false
   */
  public boolean checkDiagonal(int row, int col) {
    int winForward = 0;
    int winBackward = 0;
    int i = row;
    int j = col;
    Color c = board[i][j];

    // downward forward
    while (i < 6 && j < 7 && board[i][j] == c && winForward < 4) {
      winForward++;
      i++;
      j++;
    }
    i = row;
    j = col;

    // upward backward
    while (i > 0 && j > 0 && board[i - 1][j - 1] == c && winForward < 4) {
      winForward++;
      i--;
      j--;
    }
    i = row;
    j = col;

    // upward forward
    while (i > 0 && j < 6 && board[i - 1][j + 1] == c && winBackward < 4) {
      winBackward++;
      i--;
      j++;
    }
    i = row;
    j = col;

    // downward backward
    while (i < 6 && j >= 0 && board[i][j] == c && winBackward < 4) {
      winBackward++;
      i++;
      j--;
    }
    if (winForward == 4 || winBackward == 4) {
      return true;
    }
    return false;
  }

  /**
   * Check if it's the end of game.
   * 
   * @param row
   * @param col
   * @return true if it's the end of game else false.
   */
  public boolean checkGameEnd(int row, int col) {

    if (checkHorizontal(row, col)) {
      return true;
    } else if (checkVertical(row, col)) {
      return true;
    } else if (checkDiagonal(row, col)) {
      return true;
    } else {
      return false;
    }
  }

  /**
   * Checks if the board is full
   * 
   * @return true if board full else false.
   */
  public boolean isBoardFull() {
    for (int i = 0; i < 6; i++) {
      for (int j = 0; j < 7; j++) {
        if (board[i][j] == null) {
          return false;
        }
      }
    }
    return true;
  }

  /**
   * called by the computer player to check if there is a winning move to for win
   * 
   * @param col
   * @param color
   * @return true if there is win else false.
   */
  public boolean isWinningngMove(int col, Color color) {
    if (col < 0 || col > 6) {
      throw new IllegalArgumentException("col is over 6 or less than 0: " + col);
    }
    int i = numOfPieceInRows[col];

    if (i >= 6) {
      return false;
    }

    board[i][col] = color;

    if (checkGameEnd(i, col)) {
      board[i][col] = null;
      return true;
    } else {
      board[i][col] = null;
      return false;
    }
  }

  /**
   * notifies the views that the game has those a mode.
   */
  public void fireSetStartBoardEvent() {
    for (Listener listener : listeners) {
      listener.setStartBoard();
    }
  }

  /**
   * notifies the views that the game has start.
   */
  public void fireGameStartEvent() {
    for (Listener listener : listeners) {
      listener.gameStart();
    }
  }

  /**
   * notifies the views that the game has restarted.
   */
  public void fireGameRestartEvent() {
    this.initialGame();
    for (Listener listener : listeners) {
      listener.gameRestart();
      listener.gameStart();
    }
  }

  /**
   * notifies the views that the game made a move.
   * 
   * @param row
   * @param col
   * @param color
   */
  public void fireGameMoveEvent(int row, int col, Color color) {
    for (Listener listener : listeners) {
      listener.gameMove(row, col, color);
    }
  }

  /**
   * notifies the views that which color win.
   * 
   * @param color
   */
  public void fireGameWinEvent(Color color) {
    for (Listener listener : listeners) {
      listener.gameWin(color);
    }
  }

  /**
   * notifies the views that the game is tie.
   */
  public void fireGameTieEvent() {
    for (Listener listener : listeners) {
      listener.gameTie();
    }
  }

  /**
   * notifies the views that the game is exit.
   */
  public void fireGameExitEvent() {
    for (Listener listener : listeners) {
      listener.gameExit();
    }
  }

  /**
   * This is for unit test.
   * 
   * @param row
   * @param col
   * @param color
   */
  public void setBoard(int row, int col, Color color) {
    this.board[row][col] = color;
  }

  /**
   * This is for unit test.
   * 
   * @param col
   * @param row
   */
  public void setRows(int col, int row) {
    numOfPieceInRows[col] = row;
  }

}
