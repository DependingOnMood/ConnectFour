import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * This class give users to game mode selection view, which has two buttons, one is single player
 * mode and one is double player mode that user can choose
 * 
 * @author Dongbo
 *
 */
public class ModeView implements ActionListener {
  private JButton singlePlayerModeButton;
  private JButton doublePlayerModeButton;
  private JFrame selectionModeFrame;

  public ModeView() {
    singlePlayerModeButton = new JButton("Single Player Mode");
    doublePlayerModeButton = new JButton("Double Players Mode");
    selectionModeFrame = new JFrame("Connect 4");

    singlePlayerModeButton.addActionListener(this);
    doublePlayerModeButton.addActionListener(this);

    setView();
  }

  /**
   * This method give users to game mode selection view, which has two buttons, one is single player
   * mode and one is double player mode that user can choose
   */
  public void setView() {
    JPanel selectionPanel = new JPanel();

    singlePlayerModeButton.setPreferredSize(new Dimension(150, 50));
    doublePlayerModeButton.setPreferredSize(new Dimension(150, 50));

    selectionPanel.add(singlePlayerModeButton);
    selectionPanel.add(doublePlayerModeButton);

    selectionModeFrame.add(selectionPanel, BorderLayout.SOUTH);
    selectionModeFrame.setVisible(true);
    selectionModeFrame.setSize(320, 90);
    selectionModeFrame.setLocationRelativeTo(null);
    selectionModeFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

  }

  @Override
  public void actionPerformed(ActionEvent e) {

    if (e.getSource() == singlePlayerModeButton) {
      GameLogic gameLogic = GameLogic.getModelInstance();
      new GameView(gameLogic, Color.red);
      gameLogic.createPlayers(GameMode.SINGLE);

      selectionModeFrame.dispose();
    }

    if (e.getSource() == doublePlayerModeButton) {
      GameLogic gameLogic = GameLogic.getModelInstance();
      new GameView(gameLogic, Color.red);
      new GameView(gameLogic, Color.black);
      gameLogic.createPlayers(GameMode.DOUBLE);

      selectionModeFrame.dispose();
    }

  }
}
