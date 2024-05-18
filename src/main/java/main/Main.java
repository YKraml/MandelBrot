package main.java.main;


import main.java.frame.MainFrame;
import main.java.frame.MainPanel;
import main.java.model.Calculator;
import main.java.model.CalculatorDouble;
import main.java.model.Graph;
import main.java.runnables.DrawComponentsRunnable;

public class Main {

    private final static long FPS = 10;

    public static void main(String[] args) {
        //Settings<BigDecimal> settings = new SettingsBigDecimal();
        //Calculator<BigDecimal> calculator = new CalculatorBigDecimal(settings);
        //Graph<BigDecimal> graph = new Graph<>(calculator, settings);

        Settings<Double> settings = new SettingsDouble();
        Calculator<Double> calculator = new CalculatorDouble(settings);
        Graph<Double> graph = new Graph<>(calculator, settings);

        MainPanel mainPanel = new MainPanel(graph, calculator, settings);
        mainPanel.initAll();

        MainFrame myFrame = new MainFrame();
        myFrame.add(mainPanel);
        myFrame.pack();
        myFrame.setVisible();
        myFrame.setLocationRelativeTo(null);

        new Thread(new DrawComponentsRunnable(mainPanel.getComponentsToDraw(), FPS)).start();
    }


}
