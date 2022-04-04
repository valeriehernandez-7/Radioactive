import java.awt.event.*;
import javax.swing.*;
import javax.swing.Timer;
import java.util.*;

public class Radioactive extends JFrame implements ActionListener {
    // ui components
    private JButton playBtn, gameBtn1, gameBtn2, gameBtn3;
    private JLabel logo, background, frame;
    // resources
    private final ImageIcon iconImg = new ImageIcon("src/resources/__icon.png");
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
        System.out.println("\n☢ --- Radioactive --- ☢");
        setIconImage(iconImg.getImage());
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
        playBtn = buttonSetup(playBtn0Img, playBtn1Img, 192, 428, true, true);
        getContentPane().add(playBtn);
        // pink button
        gameBtn1 = buttonSetup(gameBtn0Img, gameBtn0Img, 150, 270, false, false);
        getContentPane().add(gameBtn1);
        // purple button
        gameBtn2 = buttonSetup(gameBtn0Img, gameBtn0Img, 303, 270, false, false);
        getContentPane().add(gameBtn2);
        // blue button
        gameBtn3 = buttonSetup(gameBtn0Img, gameBtn0Img, 227, 404, false, false);
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

    private JButton buttonSetup(ImageIcon source, ImageIcon effect, int posX, int posY, boolean enabled, boolean visible) {
        JButton button = new JButton();
        button.setIcon(source);
        button.setDisabledIcon(effect);
        button.setPressedIcon(effect);
        button.setRolloverIcon(effect);
        button.setBounds(posX, posY, button.getIcon().getIconWidth(), button.getIcon().getIconHeight());
        button.setBorder(BorderFactory.createEmptyBorder());
        button.setContentAreaFilled(false);
        button.setEnabled(enabled);
        button.setVisible(visible);
        button.addActionListener(this);
        return button;
    }

    private void buttonEnabled(JButton button, ImageIcon source, ImageIcon effect) {
        button.setIcon(source);
        button.setDisabledIcon(source);
        button.setPressedIcon(effect);
        button.setRolloverIcon(effect);
        button.setEnabled(true);
    }

    private JLabel labelSetup(ImageIcon source, int posX, int posY, boolean visible) {
        JLabel label = new JLabel(source);
        label.setBounds(posX, posY, label.getIcon().getIconWidth(), label.getIcon().getIconHeight());
        label.setVisible(visible);
        return label;
    }

    private void animation(JButton jButton, ImageIcon imageIcon, int tick) {
        int delay = 2000 * tick;
        // flash the button
        Timer timerON = new Timer(delay + 150, actionEvent -> jButton.setDisabledIcon(imageIcon));
        timerON.setRepeats(false);
        timerON.start();
        // off the button
        Timer timerOFF = new Timer(delay + 300, actionEvent -> jButton.setDisabledIcon(gameBtn0Img));
        timerOFF.setRepeats(false);
        timerOFF.start();
    }

    private void setEnabledUIComponents() {
        buttonEnabled(gameBtn1, gameBtn0Img, gameBtn1Img);
        buttonEnabled(gameBtn2, gameBtn0Img, gameBtn2Img);
        buttonEnabled(gameBtn3, gameBtn0Img, gameBtn3Img);
    }

    private boolean replay() {
        boolean replay = false;
        int input = JOptionPane.showConfirmDialog(this, "Do you need to replay the sequence?",
                "Radioactive", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, iconImg);
        if (input == 0) {
            replay = true;
        }
        return replay;
    }

    private boolean gameOver(String message) {
        boolean play = false;
        int input = JOptionPane.showConfirmDialog(this, message + "Do you want to play another sequence?",
                "Radioactive", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, iconImg);
        if (input == 0) {
            play = true;
        }
        return play;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == playBtn) {
            play();
        } else if (source == gameBtn1) {
            System.out.print(" 1 ");
            userSequence.add(1);
            checkSequence(userSequence.size() - 1, 1);
        } else if (source == gameBtn2) {
            System.out.print(" 2 ");
            userSequence.add(2);
            checkSequence(userSequence.size() - 1, 2);
        } else if (source == gameBtn3) {
            System.out.print(" 3 ");
            userSequence.add(3);
            checkSequence(userSequence.size() - 1, 3);
        }
    }

    private int getRandomInteger(int origin, int bound) {
        Random random = new Random();
        return random.nextInt(origin, bound);
    }

    private void generateSequence() {
        int sequenceSize = getRandomInteger(3, 11);
        for (int i = 1; i < sequenceSize + 1; i++) {
            gameSequence.add(getRandomInteger(1, 4));
        }
    }

    private void showSequence() {
        System.out.print("☢ GAME SEQUENCE ");
        int tick = 0;
        for (Integer flash : gameSequence) {
            tick++;
            switch (flash) {
                case 1 -> animation(gameBtn1, gameBtn1Img, tick);
                case 2 -> animation(gameBtn2, gameBtn2Img, tick);
                case 3 -> animation(gameBtn3, gameBtn3Img, tick);
            }
            System.out.print(" " + flash + " ");
        }
        System.out.println("");
        Timer showReplay = new Timer(tick * 2100, actionEvent -> {
            if (replay()) {
                Timer repeatSequence = new Timer(1000, aE -> showSequence());
                repeatSequence.setRepeats(false);
                repeatSequence.start();
            } else {
                setEnabledUIComponents();
                getUserSequence();
            }
        });
        showReplay.setRepeats(false);
        showReplay.start();
    }

    private void getUserSequence() {
        System.out.print("☢ USER SEQUENCE ");
    }

    private void checkSequence(int index, int value) {
        if (gameSequence.get(index) != value) {
            System.out.println("\n☢ DEFEAT\n☢ -------- ☢ ------- ☢");
            if (gameOver("You lost! ")) {
                this.dispose();
                Radioactive radioactive = new Radioactive();
            } else {
                this.dispose();
            }
        } else if (gameSequence.size() == userSequence.size()) {
            System.out.println("\n☢ VICTORY\n☢ -------- ☢ ------- ☢");
            if (gameOver("You won! ")) {
                this.dispose();
                Radioactive radioactive = new Radioactive();
            } else {
                this.dispose();
            }
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
        Timer timer = new Timer(2000, actionEvent -> showSequence());
        timer.setRepeats(false);
        timer.start();
    }
}