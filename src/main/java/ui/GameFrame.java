package ui;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class GameFrame extends JFrame {
    private final int  windowWidth = 600;
    private final int windowHeight = 640;
    private GameMap gameMap;
    public GameFrame() throws HeadlessException, IOException {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(windowWidth, windowHeight);
        setLocationRelativeTo(null);
        setTitle("Tic Tac Toe");
        setResizable(false);
        setVisible(true);
        JButton btnStart = new JButton("Start");
        JButton btnExit = new JButton("Exit");
        JPanel btnPanel = new JPanel();
        btnPanel.setLayout(new GridLayout(1,2));
        btnPanel.add(btnStart);
        btnPanel.add(btnExit);
        add(btnPanel,BorderLayout.SOUTH);
        gameMap = new GameMap();
        Settings settings = new Settings(this);
        add(gameMap, BorderLayout.CENTER);
        btnStart.addActionListener(e -> settings.setVisible(true));
        btnExit.addActionListener(e -> System.exit(0));
    }
    public  void  startGame(int gameMode, int fieldSize, int winLength) {
        gameMap.startNewGame(gameMode,fieldSize,winLength);
        System.out.println("GM:" + gameMode + "FS:" + fieldSize +"WL:" + winLength);
    }
}
