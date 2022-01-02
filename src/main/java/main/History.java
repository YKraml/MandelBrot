package main.java.main;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class History {

    private static final List<BufferedImage> renderedImages = Collections.synchronizedList(new ArrayList<>());

    public static void addImage(BufferedImage image) {
        renderedImages.add(image);
    }

    public static List<BufferedImage> getRenderedImages() {
        return Collections.unmodifiableList(renderedImages);
    }
}
