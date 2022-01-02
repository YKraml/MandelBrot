package main.java.frame;

import main.java.main.Settings;
import main.java.model.Graph;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;

public class CenterPanel extends MyPanel {

    private final Graph graph;

    public CenterPanel(Graph graph) {
        this.graph = graph;
    }

    public void init() {
        DrawPanel drawPanel = new DrawPanel(graph);
        setBackground(Color.white);

        MouseAdapter mouseAdapter = new MyMouseAdapter();
        drawPanel.addMouseMotionListener(mouseAdapter);
        drawPanel.addMouseListener(mouseAdapter);
        drawPanel.addMouseWheelListener(mouseAdapter);

        JScrollPane scrollPane = new JScrollPane(drawPanel);
        scrollPane.setPreferredSize(new Dimension(700, 500));
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        this.add(scrollPane);

        this.setBorder(BorderFactory.createTitledBorder("Board"));
        this.addComponentToDraw(drawPanel);
        this.addInnerMyPanel(drawPanel);
    }
}
