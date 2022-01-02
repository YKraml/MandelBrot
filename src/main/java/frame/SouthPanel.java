package main.java.frame;

import main.java.main.History;
import main.java.runnables.MyLoopRunnable;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SouthPanel extends MyPanel {

    private final List<BufferedImage> addedImages;

    public SouthPanel() {
        addedImages = Collections.synchronizedList(new ArrayList<>());
    }

    @Override
    protected void init() {

        setBackground(Color.white);
        setBorder(new TitledBorder("History"));
        setLayout(new GridLayout(1, 1));

        JPanel scrollPanel = new JPanel();
        scrollPanel.setBackground(Color.white);

        JScrollPane scrollPane = new JScrollPane(scrollPanel);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
        add(scrollPane);

        History.addImage(new BufferedImage(100, 100, BufferedImage.TYPE_INT_RGB));

        new Thread(new MyLoopRunnable() {
            @Override
            protected long getWaitTimeInNano() {
                return 1000 * 1000 * 1000;
            }

            @Override
            protected void toToInLoop() {
                History.getRenderedImages().stream()
                        .filter(image -> !addedImages.contains(image))
                        .forEach(image -> scrollPanel.add(new MiniDrawPanel(image), 0));
                addedImages.addAll(History.getRenderedImages());

                scrollPanel.revalidate();
                scrollPanel.repaint();

                scrollPane.revalidate();
                scrollPane.repaint();

                repaint();
                revalidate();
            }
        }).start();

    }
}
