import java.awt.event.*;
import javax.swing.*;
import java.util.*;

public class Radioactive extends JFrame implements ActionListener {
    // ui components
    private JButton playBtn, gameBtn1, gameBtn2, gameBtn3;
    private JLabel logo, background, frame;
    private JDialog message;
    // resources
    private final ImageIcon logoImg = new ImageIcon("src/resources/__logo.png");
    private final ImageIcon backgroundImg = new ImageIcon("src/resources/__background.png");
    private final ImageIcon frameImg = new ImageIcon("src/resources/__frame.png");
    private final ImageIcon playBtn0Img = new ImageIcon("src/resources/__button-play-0.png");
    private final ImageIcon playBtn1Img = new ImageIcon("src/resources/__button-play-1.png");
    private final ImageIcon gameBtn0Img = new ImageIcon("src/resources/__button-0.png");
    private final ImageIcon gameBtn1Img = new ImageIcon("src/resources/__button-1.png");
    private final ImageIcon gameBtn2Img = new ImageIcon("src/resources/__button-2.png");
    private final ImageIcon gameBtn3Img = new ImageIcon("src/resources/__button-3.png");
    // game features
    private boolean gameOver = false;
    private ArrayList<Integer> gamePattern = new ArrayList<>();
    private ArrayList<Integer> userPattern = new ArrayList<>();

    public Radioactive() {
        setIconImage(new ImageIcon("__icon.png").getImage());
        setTitle("Radioactive");
        setSize(600, 800);
        setResizable(false);
        setLocationRelativeTo(null);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getUIComponents();
        setVisible(true);
    }

    private void getUIComponents() {
        // --- buttons ---
        // play button
        playBtn = buttonSetup(playBtn0Img, playBtn1Img, 192, 428, true);
        getContentPane().add(playBtn);
        // pink button
        gameBtn1 = buttonSetup(gameBtn0Img, gameBtn1Img, 150, 270, false);
        getContentPane().add(gameBtn1);
        // purple button
        gameBtn2 = buttonSetup(gameBtn0Img, gameBtn2Img, 303, 270, false);
        getContentPane().add(gameBtn2);
        // blue button
        gameBtn3 = buttonSetup(gameBtn0Img, gameBtn3Img, 227, 404, false);
        getContentPane().add(gameBtn3);
        // --- labels ---
        // logo image
        logo = labelSetup(logoImg, 40, 325, true);
        getContentPane().add(logo);
        // frame image
        frame = labelSetup(frameImg, 12, 22, false);
        getContentPane().add(frame);
        // background image
        background = labelSetup(backgroundImg, 0, 0, true);
        getContentPane().add(background);
    }

    private JButton buttonSetup(ImageIcon source, ImageIcon effect, int posX, int posY, boolean visible) {
        JButton button = new JButton();
        button.setIcon(source);
        button.setPressedIcon(effect);
        button.setRolloverIcon(effect);
        button.setBounds(posX, posY, button.getIcon().getIconWidth(), button.getIcon().getIconHeight());
        button.setBorder(BorderFactory.createEmptyBorder());
        button.setContentAreaFilled(false);
        button.setVisible(visible);
        button.addActionListener(this);
        return button;
    }

    private JLabel labelSetup(ImageIcon source, int posX, int posY, boolean visible) {
        JLabel label = new JLabel(source);
        label.setBounds(posX, posY, label.getIcon().getIconWidth(), label.getIcon().getIconHeight());
        label.setVisible(visible);
        return label;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == playBtn) {
            play();
        } else if (source == gameBtn1) {
            System.out.println("▲ PINK (1) PRESSED");
            userPattern.add(1);
        } else if (source == gameBtn2) {
            System.out.println("▲ PURPLE (2) PRESSED");
            userPattern.add(2);
        } else if (source == gameBtn3) {
            System.out.println("▲ BLUE (3) PRESSED");
            userPattern.add(3);
        }
    }

    private void play() {
        gamePattern.clear();
        userPattern.clear();
        System.out.println("▷ PLAY");
        // hide welcome screen components
        logo.setVisible(false);
        playBtn.setVisible(false);
        // show game screen components
        frame.setVisible(true);
        gameBtn1.setVisible(true);
        gameBtn2.setVisible(true);
        gameBtn3.setVisible(true);
        // game pattern creation
        generatePattern();
        // game start
        game();
    }

    private void generatePattern() {
        int patternSize = getRandomInteger(3, 11);
        System.out.println(patternSize);
        for (int i = 1; i < patternSize + 1; i++) {
            gamePattern.add(getRandomInteger(1, 4));
        }
        System.out.println("GAME PATTERN: " + gamePattern);
    }

    private int getRandomInteger(int origin, int bound) {
        Random random = new Random();
        return random.nextInt(origin, bound);
    }

    private void game() {
        for (Integer i : gamePattern) {
            switch (i) {
                case 1 -> gameBtn1.setIcon(gameBtn1Img);
                case 2 -> gameBtn2.setIcon(gameBtn2Img);
                case 3 -> gameBtn3.setIcon(gameBtn3Img);
            }
            gameBtn1.setIcon(gameBtn0Img);
            gameBtn2.setIcon(gameBtn0Img);
            gameBtn3.setIcon(gameBtn0Img);
        }
    }
}