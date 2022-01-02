package main.java.frame;

import main.java.model.Graph;

import java.awt.*;

import static java.awt.BorderLayout.*;

public class MainPanel extends MyPanel {

    private final Graph graph;

    public MainPanel(Graph graph) {
        this.graph = graph;
    }


    public void init() {

        CenterPanel centerPanel = new CenterPanel(graph);
        WestPanel westPanel = new WestPanel();
        SouthPanel southPanel = new SouthPanel();

        this.setLayout(new BorderLayout());
        this.add(centerPanel, CENTER);
        this.add(westPanel, WEST);
        this.add(southPanel, SOUTH);

        this.addInnerMyPanel(centerPanel);
        this.addInnerMyPanel(westPanel);
        this.addInnerMyPanel(southPanel);

    }
}
