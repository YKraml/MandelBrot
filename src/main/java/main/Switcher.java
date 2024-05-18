package main.java.main;

import main.java.model.Calculator;
import main.java.model.CalculatorBigDecimal;
import main.java.model.CalculatorDouble;

import java.math.BigDecimal;

public class Switcher {

    private final Settings<BigDecimal> settingsBigDecimal = new SettingsBigDecimal();
    private final Calculator<BigDecimal> calculatorBigDecimal = new CalculatorBigDecimal(settingsBigDecimal);

    private final Settings<Double> settingsDouble = new SettingsDouble();
    private final Calculator<Double> calculatorDouble = new CalculatorDouble(settingsDouble);

}
