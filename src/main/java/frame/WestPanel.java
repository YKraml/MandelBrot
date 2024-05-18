package main.java.frame;

import main.java.main.Settings;
import main.java.main.Wrapper;
import main.java.model.Calculator;
import main.java.model.ImaginaryNumberBigDecimal;
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

    private final Calculator calculator;
    private final Settings settings;

    public WestPanel(Calculator calculator, Settings settings) {
        this.calculator = calculator;
        this.settings = settings;
        wrappers = Collections.synchronizedList(new ArrayList<>());
        coupledPairs = Collections.synchronizedMap(new HashMap<>());
    }

    @Override
    protected void init() {

        setBorder(new TitledBorder("settings"));
        setBackground(Color.white);

        wrappers.add(settings.getImageWidthWrapper());
        wrappers.add(settings.getImageHeightWrapper());
        wrappers.add(settings.getPrecisionWrapper());
        wrappers.add(settings.getMaxRecursionDepthWrapper());
        wrappers.add(settings.getMouseXPosWrapper());
        wrappers.add(settings.getMouseYPosWrapper());
        wrappers.add(settings.getWorldXOffsetWrapper());
        wrappers.add(settings.getWorldYOffsetWrapper());
        wrappers.add(settings.getZoomXWrapper());
        wrappers.add(settings.getZoomYWrapper());

        setLayout(new GridLayout(wrappers.size() + 3, 2));

        wrappers.forEach(wrapper -> {
            JLabel jLabel = new JLabel(wrapper.getPairedNamed());
            JTextField jTextField = new JTextField(wrapper.toString());
            add(jLabel);
            add(jTextField);
            coupledPairs.put(jTextField, wrapper);
        });


        coupledPairs.forEach((jTextField, wrapper) -> jTextField.addActionListener(e -> {
            settings.setChanged(true);

            String value = jTextField.getText();

            if (wrapper.getValue() instanceof Integer) {
                Number v = Integer.parseInt(value);
                wrapper.setValue(v);
            }

            if (wrapper.getValue() instanceof BigDecimal) {
                BigDecimal v = new BigDecimal(value);
                wrapper.setValue(v);
            }

            System.out.println(settings.getImageWidth());


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
                        textField.setText(calculator.formatNumber(wrapper.getValue()));
                    }
                });
            }
        }).start();


        BigDecimal[] values = new BigDecimal[1000];
        for (int i = 0; i < values.length; i++) {
            String value = String.format("%.2f", (double) i / values.length * 10 - 5).replace(",", ".");
            values[i] = new BigDecimal(value);
        }


        JCheckBox checkBox1 = new JCheckBox();
        checkBox1.setSelected(true);
        JCheckBox checkBox2 = new JCheckBox();
        checkBox1.setSelected(false);
        JPanel numberPanel = new JPanel();
        JSpinner spinner1 = new JSpinner(new SpinnerListModel(values));
        JSpinner spinner2 = new JSpinner(new SpinnerListModel(values));
        spinner1.setValue(values[values.length / 2]);
        spinner2.setValue(values[values.length / 2]);
        numberPanel.add(spinner1);
        numberPanel.add(new JLabel("+"));
        numberPanel.add(spinner2);
        numberPanel.add(new JLabel("*i"));

        checkBox1.addActionListener(e -> {
            settings.setCIsVariable(checkBox1.isSelected());
            settings.setChanged(true);
        });

        checkBox2.addActionListener(e -> {
            settings.setUseLargeDoubles(checkBox2.isSelected());
            settings.setChanged(true);
        });

        ChangeListener spinnerLister = e -> {
            settings.setNonVariable(new ImaginaryNumberBigDecimal((BigDecimal) spinner1.getValue(), (BigDecimal) spinner2.getValue(), settings));
            settings.setChanged(true);
        };
        spinner1.addChangeListener(spinnerLister);
        spinner2.addChangeListener(spinnerLister);


        add(new JLabel("C is Variable"));
        add(checkBox1);
        add(new JLabel("Non Variable Value"));
        add(numberPanel);
        add(new JLabel("Use large doubles"));
        add(checkBox2);

    }

}
