package main.java.frame;

import main.java.main.Settings;
import main.java.model.Calculator;
import main.java.model.Graph;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;

public class CenterPanel<K extends Number> extends MyPanel {

    private final Graph<K> graph;
    private final Calculator<K> calculator;
    private final Settings<K> settings;

    public CenterPanel(Graph<K> graph, Calculator<K> calculator, Settings<K> settings) {
        this.graph = graph;
        this.calculator = calculator;
        this.settings = settings;
    }

    public void init() {
        DrawPanel drawPanel = new DrawPanel(graph, calculator, settings);
        setBackground(Color.white);

        MouseAdapter mouseAdapter = new MyMouseAdapter<>(calculator, settings);
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
