package main.java.frame;

import javax.swing.*;
import java.util.*;

public abstract class MyPanel extends JPanel {

    private final Collection<MyPanel> innerMyPanels;
    private final Collection<JComponent> componentsToDraw;

    public MyPanel() {
        this.innerMyPanels = Collections.synchronizedSet(new HashSet<>());
        this.componentsToDraw = Collections.synchronizedSet(new HashSet<>());
    }

    public final Collection<JComponent> getComponentsToDraw() {
        this.innerMyPanels.forEach(myPanel -> componentsToDraw.addAll(myPanel.getComponentsToDraw()));
        return Collections.unmodifiableCollection(componentsToDraw);
    }

    public final void initAll() {
        this.init();
        this.innerMyPanels.forEach(MyPanel::initAll);
    }

    protected abstract void init();

    protected final void addInnerMyPanel(MyPanel myPanel) {
        this.innerMyPanels.add(myPanel);
    }

    protected final void addComponentToDraw(JComponent jComponent) {
        this.componentsToDraw.add(jComponent);
    }


}
