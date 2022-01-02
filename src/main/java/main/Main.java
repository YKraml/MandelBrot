package main.java.main;


import main.java.frame.MainFrame;
import main.java.frame.MainPanel;
import main.java.model.Graph;
import main.java.runnables.DrawComponentsRunnable;

public class Main {

    private final static long FPS = 10;

    public static void main(String[] args) {

        Graph graph = new Graph();

        MainPanel mainPanel = new MainPanel(graph);
        mainPanel.initAll();

        MainFrame myFrame = new MainFrame();
        myFrame.add(mainPanel);
        myFrame.pack();
        myFrame.setVisible();
        myFrame.setLocationRelativeTo(null);

        new Thread(new DrawComponentsRunnable(mainPanel.getComponentsToDraw(), FPS)).start();

    }



}
