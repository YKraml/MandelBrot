package main.java.frame;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class MiniDrawPanel extends JPanel {

    private final BufferedImage image;

    public MiniDrawPanel(BufferedImage image) {
        this.image = image;

        double width = (double) 100 / image.getHeight() * image.getWidth();
        int height = 100;

        setPreferredSize(new Dimension((int) width, height));
        setSize((int) width, height);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.drawImage(image, 0, 0, (img1, infoflags, x, y, width, height) -> false);

        g.drawImage(image,
                0, 0, getWidth(), getHeight(),
                0, 0, image.getWidth(), image.getHeight(),
                (img, infoflags, x, y, width, height) -> false);

    }

}
