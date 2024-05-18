package main.java.frame;

import javax.swing.*;

public class MainFrame extends JFrame {

    private static final String TITLE = "Game Of Live";
    private static final boolean VISIBILITY = true;
    private static final boolean ALWAYS_ON_TOP = false;

    public MainFrame() {
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setTitle(TITLE);
        this.setAlwaysOnTop(ALWAYS_ON_TOP);
    }

    public void setVisible() {
        this.setVisible(VISIBILITY);
    }

}
