package main.java.frame;

import main.java.main.Settings;
import main.java.model.Calculator;
import main.java.model.Graph;

import java.awt.*;

import static java.awt.BorderLayout.CENTER;
import static java.awt.BorderLayout.WEST;

public class MainPanel extends MyPanel {

    private final Graph graph;
    private final Calculator calculator;
    private final Settings settings;

    public MainPanel(Graph graph, Calculator calculator, Settings settings) {
        this.graph = graph;
        this.calculator = calculator;
        this.settings = settings;
    }


    public void init() {

        CenterPanel centerPanel = new CenterPanel(graph, calculator, settings);
        WestPanel westPanel = new WestPanel(calculator, settings);
        //SouthPanel southPanel = new SouthPanel();

        this.setLayout(new BorderLayout());
        this.add(centerPanel, CENTER);
        this.add(westPanel, WEST);
        //this.add(southPanel, SOUTH);

        this.addInnerMyPanel(centerPanel);
        this.addInnerMyPanel(westPanel);
        //this.addInnerMyPanel(southPanel);
    }
}
