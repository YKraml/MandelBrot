package main.java.frame;

import main.java.main.Settings;
import main.java.main.Wrapper;
import main.java.model.ImaginaryNumber;
import main.java.model.MyMath;
import main.java.runnables.MyLoopRunnable;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.math.BigDecimal;
import java.util.List;
import java.util.*;

public class WestPanel extends MyPanel {

    private final List<Wrapper> wrappers;
    private final Map<JTextField, Wrapper> coupledPairs;

    public WestPanel() {
        wrappers = Collections.synchronizedList(new ArrayList<>());
        coupledPairs = Collections.synchronizedMap(new HashMap<>());
    }

    @Override
    protected void init() {

        setBorder(new TitledBorder("Settings"));
        setBackground(Color.white);

        wrappers.add(Settings.getImageWidthWrapper());
        wrappers.add(Settings.getImageHeightWrapper());
        wrappers.add(Settings.getPrecisionWrapper());
        wrappers.add(Settings.getMaxRecursionDepthWrapper());
        wrappers.add(Settings.getMouseXPosWrapper());
        wrappers.add(Settings.getMouseYPosWrapper());
        wrappers.add(Settings.getWorldXOffsetWrapper());
        wrappers.add(Settings.getWorldYOffsetWrapper());
        wrappers.add(Settings.getZoomXWrapper());
        wrappers.add(Settings.getZoomYWrapper());

        setLayout(new GridLayout(wrappers.size() + 2, 2));

        wrappers.forEach(wrapper -> {
            JLabel jLabel = new JLabel(wrapper.getPairedNamed());
            JTextField jTextField = new JTextField(wrapper.toString());
            add(jLabel);
            add(jTextField);
            coupledPairs.put(jTextField, wrapper);
        });


        coupledPairs.forEach((jTextField, wrapper) -> jTextField.addActionListener(e -> {
            Settings.setChanged(true);

            String value = jTextField.getText();

            if (wrapper.getValue() instanceof Integer) {
                Number v = Integer.parseInt(value);
                wrapper.setValue(v);
            }

            if (wrapper.getValue() instanceof BigDecimal) {
                BigDecimal v = new BigDecimal(value);
                wrapper.setValue(v);
            }

            System.out.println(Settings.getImageWidth());


        }));

        new Thread(new MyLoopRunnable() {
            @Override
            protected long getWaitTimeInNano() {
                return 1000 * 1000 * 1000;
            }

            @Override
            protected void toToInLoop() {
                coupledPairs.forEach((textField, wrapper) -> {
                    if (!textField.hasFocus()) {
                        textField.setText(MyMath.formatNumber(wrapper.getValue()));
                    }
                });
            }
        }).start();


        BigDecimal[] values = new BigDecimal[1000];
        for (int i = 0; i < values.length; i++) {
            String value = String.format("%.2f", (double) i / values.length * 10 - 5).replace(",", ".");
            values[i] = new BigDecimal(value);
        }


        JCheckBox checkBox = new JCheckBox();
        checkBox.setSelected(true);
        JPanel numberPanel = new JPanel();
        JSpinner spinner1 = new JSpinner(new SpinnerListModel(values));
        JSpinner spinner2 = new JSpinner(new SpinnerListModel(values));
        spinner1.setValue(values[values.length / 2]);
        spinner2.setValue(values[values.length / 2]);
        numberPanel.add(spinner1);
        numberPanel.add(new JLabel("+"));
        numberPanel.add(spinner2);
        numberPanel.add(new JLabel("*i"));

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


        add(new JLabel("C is Variable"));
        add(checkBox);
        add(new JLabel("Non Variable Value"));
        add(numberPanel);

    }

}
