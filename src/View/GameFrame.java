package View;

import Model.GameSaveAndLoad;

import javax.swing.*;
import java.awt.*;

public class GameFrame extends JFrame {

    private JMenuBar myMenuBar;
    private JMenuItem mySaveItem;
    private JMenuItem myLoadItem;
    private JMenuItem myExitItem;
    private JButton myResumeButton;
    public GameSaveAndLoad myGameSaveLoad;
    GamePanel myGamePanel;

    private static final int BORDER= 15;

    //REFER TO COLOR SWATCH
    private static final Color LIGHTEST_GREEN = new Color(155,188,15);
    private static final Color LIGHTER_GREEN = new Color(139,172,115);
    private static final Color GREEN = new Color(48,98,48);
    private static final Color DARKER_GREEN = new Color(15,56,15);
    private static final Color DARKEST_GREEN = new Color(15,56,15);


    public GameFrame(){
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
//      myGameSaveLoad
//      myGamePanel.musicBackground(0);



    }

    public void endPanel(){

    }

    private void showDialog(final JPanel thePanel) {
        JDialog dialog = new JDialog(this, "Dialog", true);
        dialog.getContentPane().add(thePanel);
        dialog.setUndecorated(true);
        dialog.pack();
        dialog.setLocationRelativeTo(this);
        dialog.setVisible(true);
    }

    private void initMenuBar() {

        myMenuBar = new JMenuBar();

        JMenu myGameMenu = new JMenu("Game");
        JMenu myHelpMenu = new JMenu("Help");
        mySaveItem = new JMenuItem("Save");
        myLoadItem = new JMenuItem("Load");
        myExitItem = new JMenuItem("Exit");
        myMenuBar.add(myGameMenu);
        myMenuBar.add(myHelpMenu);

        myGameMenu.add(mySaveItem);
        myGameMenu.add(myLoadItem);
        myGameMenu.add(myExitItem);
        setJMenuBar(myMenuBar);
        menuBarListener();

    }
    private void menuBarListener() {
        myExitItem.addActionListener(theEvent -> showDialog(new ExitPanel()));
//        mySaveItem.addActionListener(e -> GameSaveAndLoad.saveGame());
//        myLoadItem.addActionListener(e -> GameSaveAndLoad.loadGame());
    }
    class ExitPanel extends JPanel {
        public ExitPanel() {
            setBackground(GREEN);
            JLabel exitLabel1 = new JLabel("CONFIRM EXIT");
            exitLabel1.setForeground(DARKEST_GREEN);
            JLabel exitLabel2 = new JLabel("UNSAVED PROGRESS WILL BE LOST");
            exitLabel2.setForeground(DARKER_GREEN);

            JPanel exitPanel1 = new JPanel();
            exitPanel1.setOpaque(false);
            exitPanel1.add(exitLabel1);
            JPanel exitPanel2 = new JPanel();
            exitPanel2.setOpaque(false);
            exitPanel2.add(exitLabel2);

            setBorder(BorderFactory.createEmptyBorder(BORDER, BORDER, BORDER,BORDER));
            setLayout(new GridLayout(4, 1, 10, 10));
            add(exitPanel1);
            add(exitPanel2);

            JButton exitButton = new JButton("EXIT");
            exitButton.setBackground(LIGHTEST_GREEN);
            exitButton.setForeground(LIGHTER_GREEN);
            exitButton.setBorder(BorderFactory.createLineBorder(DARKEST_GREEN, 2));
            exitButton.addActionListener(theEvent -> dispose());
            add(exitButton);
            add(myResumeButton);
        }
    }

}
