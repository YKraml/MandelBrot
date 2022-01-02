package main.java.frame;

import main.java.main.Settings;
import main.java.model.ImaginaryNumber;
import main.java.model.MyMath;
import main.java.runnables.MyLoopRunnable;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class WestPanel extends MyPanel {

    private final List<JLabel> labels;
    private final List<JTextField> fields;


    public WestPanel() {
        labels = new ArrayList<>();
        fields = new ArrayList<>();
    }

    @Override
    protected void init() {

        setBorder(new TitledBorder("Settings"));
        setBackground(Color.white);

        labels.add(new JLabel("Precision"));
        labels.add(new JLabel("Width"));
        labels.add(new JLabel("Height"));
        labels.add(new JLabel("Max Depth"));
        labels.add(new JLabel("Mouse X Pos"));
        labels.add(new JLabel("MOUSE Y Pos"));
        labels.add(new JLabel("World X Offset"));
        labels.add(new JLabel("World Y Offset"));
        labels.add(new JLabel("Zoom X"));
        labels.add(new JLabel("Zoom Y"));

        fields.add(new JTextField(14));
        fields.add(new JTextField(14));
        fields.add(new JTextField(14));
        fields.add(new JTextField(14));
        fields.add(new JTextField(14));
        fields.add(new JTextField(14));
        fields.add(new JTextField(14));
        fields.add(new JTextField(14));
        fields.add(new JTextField(14));
        fields.add(new JTextField(14));

        setLayout(new GridLayout(labels.size() + 2, 2));

        for (int i = 0; i < labels.size(); i++) {
            add(labels.get(i));
            add(fields.get(i));
        }

        new Thread(new MyLoopRunnable() {
            @Override
            protected long getWaitTimeInNano() {
                return 1000 * 1000 * 1000;
            }

            @Override
            protected void toToInLoop() {
                updateStats();
            }
        }).start();


        BigDecimal[] values = new BigDecimal[1000];
        for (int i = 0; i < values.length; i++) {
            String value = String.format("%.2f", i * 0.01 - 5).replace(",", ".");
            values[i] = new BigDecimal(value);
        }


        JCheckBox checkBox = new JCheckBox();
        checkBox.setSelected(true);
        JPanel numberPanel = new JPanel();
        JSpinner spinner1 = new JSpinner(new SpinnerListModel(values));
        JSpinner spinner2 = new JSpinner(new SpinnerListModel(values));
        spinner1.setValue(values[500]);
        spinner2.setValue(values[500]);
        numberPanel.add(spinner1);
        numberPanel.add(new JLabel("+"));
        numberPanel.add(spinner2);
        numberPanel.add(new JLabel("*i"));

        add(new JLabel("C is Variable"));
        add(checkBox);
        add(new JLabel("Non Variable Value"));
        add(numberPanel);

        checkBox.addActionListener(e -> {
            Settings.setCIsVariable(checkBox.isSelected());
            Settings.setChanged(true);
        });

        ChangeListener spinnerLister = e -> {
            Settings.setNonVariable(new ImaginaryNumber((BigDecimal) spinner1.getValue(), (BigDecimal) spinner2.getValue()));
            Settings.setChanged(true);
        };
        spinner1.addChangeListener(spinnerLister);
        spinner2.addChangeListener(spinnerLister);

    }

    private void updateStats() {
        fields.get(0).setText(String.valueOf(Settings.getPRECISION()));
        fields.get(1).setText(String.valueOf(Settings.getWIDTH()));
        fields.get(2).setText(String.valueOf(Settings.getHEIGHT()));
        fields.get(3).setText(String.valueOf(Settings.getMaxDepth()));
        fields.get(4).setText(String.valueOf(Settings.getMouseXPos()));
        fields.get(5).setText(String.valueOf(Settings.getMouseYPos()));
        fields.get(6).setText(String.valueOf(Settings.getWorldXOffset()));
        fields.get(7).setText(String.valueOf(Settings.getWorldYOffset()));
        fields.get(8).setText(MyMath.bigDeziToString(Settings.getZoomX()));
        fields.get(9).setText(MyMath.bigDeziToString(Settings.getZoomY()));
    }
}
