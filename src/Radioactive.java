import java.awt.event.*;
import javax.swing.*;
import javax.swing.Timer;
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
    private final ArrayList<Integer> gameSequence = new ArrayList<>();
    private final ArrayList<Integer> userSequence = new ArrayList<>();

    public Radioactive() {
        setIconImage(new ImageIcon("src/resources/__icon.png").getImage());
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
            userSequence.add(1);
        } else if (source == gameBtn2) {
            System.out.println("▲ PURPLE (2) PRESSED");
            userSequence.add(2);
        } else if (source == gameBtn3) {
            System.out.println("▲ BLUE (3) PRESSED");
            userSequence.add(3);
        }
    }

    private void play() {
        // clear stored sequences
        gameSequence.clear();
        userSequence.clear();
        System.out.println("▷ PLAY");
        // hide welcome screen components
        logo.setVisible(false);
        playBtn.setVisible(false);
        // show game screen components
        frame.setVisible(true);
        gameBtn1.setVisible(true);
        gameBtn2.setVisible(true);
        gameBtn3.setVisible(true);
        // game sequence creation
        generateSequence();
        // game start
        game();
    }

    private void generateSequence() {
        int sequenceSize = getRandomInteger(3, 11);
        for (int i = 1; i < sequenceSize + 1; i++) {
            gameSequence.add(getRandomInteger(1, 4));
        }
        System.out.println("☢ GAME SEQUENCE " + gameSequence);
    }

    private int getRandomInteger(int origin, int bound) {
        Random random = new Random();
        return random.nextInt(origin, bound);
    }

    private void game() {
        Timer timer = new Timer(2000, actionEvent -> showSequence());
        timer.setRepeats(false);
        timer.start();
    }

    private void showSequence() {
        System.out.print("☢ SHOW SEQUENCE [");
        for (Integer flash : gameSequence) {
            switch (flash) {
                case 1 -> animation(gameBtn1, gameBtn1Img);
                case 2 -> animation(gameBtn2, gameBtn2Img);
                case 3 -> animation(gameBtn3, gameBtn3Img);
            }
            System.out.print(" " + flash + " ");
        }
        System.out.print("]");
    }

    private void animation(JButton jButton, ImageIcon imageIcon) {
        // flash the button
        Timer timerON = new Timer(1000, actionEvent -> jButton.setIcon(imageIcon));
        timerON.setRepeats(false);
        timerON.start();
        // off the button
        Timer timerOFF = new Timer(2000, actionEvent -> jButton.setIcon(gameBtn0Img));
        timerOFF.setRepeats(false);
        timerOFF.start();
    }
}