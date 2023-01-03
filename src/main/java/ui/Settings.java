package ui;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;

public class Settings extends JFrame {
    private final int settingsHeight = 330;
    private final int settingsWidth = 300;
    private final int minWinLength = 3;
    private final int minFieldSize = 3;
    private final int maxFieldSize = 10;

    private JRadioButton humanVsAi;
    private JRadioButton humanVsHuman;
    private JSlider fieldSize;
    private JSlider winLength;
    private GameFrame gameFrame;
    public Settings(GameFrame gameFrame) throws HeadlessException {
        this.gameFrame = gameFrame;
        setTitle("Choose the game settings");
        setLocationRelativeTo(gameFrame);
        setSize(settingsWidth,settingsHeight);
        setResizable(false);
        setLayout(new GridLayout(10,1));
        add(new JLabel("Chose game mode:"));
        humanVsAi = new JRadioButton("Human versus Ai ");
        humanVsHuman = new JRadioButton("Human versus Human ");
        ButtonGroup gameMode = new ButtonGroup();
        gameMode.add(humanVsAi);
        gameMode.add(humanVsHuman);
        add(humanVsAi);
        add(humanVsHuman);
        JLabel fieldSizeLabel = new JLabel("Field size:" + minFieldSize);
        JLabel winLengthLabel = new JLabel("Win length:" + minWinLength);
        add(fieldSizeLabel);
        fieldSize = new JSlider(minFieldSize, maxFieldSize, minFieldSize);
        winLength = new JSlider(minFieldSize, maxFieldSize, minFieldSize);
        add(fieldSize);
        add(winLengthLabel);
        add(winLength);
        fieldSize.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                int currentValue = fieldSize.getValue();
                fieldSizeLabel.setText("Field size" + currentValue);
                winLength.setMaximum(currentValue);
            }
        });
        winLength.addChangeListener(e -> winLengthLabel.setText("Win length" + winLength.getValue()));
        JButton btnStart = new JButton("Start new game");
        btnStart.addActionListener(e -> submitSettings(gameFrame));
        add(btnStart);
    }
    private void submitSettings(GameFrame gameFrame) {
        int gameMode;
        if (humanVsAi.isSelected()) {
            gameMode = GameMap.modeVsAi;
        }
        else {
            gameMode = GameMap.modeVsHuman;
        }
        int fSize = fieldSize.getValue();
        int wLength = winLength.getValue();
        gameFrame.startGame(gameMode, fSize, wLength);
        setVisible(false);
    }
}
