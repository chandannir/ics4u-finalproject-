// Utility class for computing circuit totals and voltage drops.
package com.mycompany.ohmwards;

import java.util.Collection;

public class Calculations {

    // Indicates whether the circuit is treated as series or parallel for calculations.
    public enum CircuitType {
        SERIES,
        PARALLEL
    }

    // Holds the aggregate results of circuit calculations.
    public static class Result {
        private final double totalVoltage;
        private final double totalResistance;
        private final double totalCurrent;
        private final double totalPower;

        // Constructs a result with total voltage, resistance, current, and power.
        public Result(double totalVoltage, double totalResistance, double totalCurrent, double totalPower) {
            this.totalVoltage = totalVoltage;
            this.totalResistance = totalResistance;
            this.totalCurrent = totalCurrent;
            this.totalPower = totalPower;
        }

        // Returns the total voltage of the circuit.
        public double getTotalVoltage() {
            return totalVoltage;
        }

        // Returns the equivalent resistance of the circuit.
        public double getTotalResistance() {
            return totalResistance;
        }

        // Returns the total current flowing in the circuit.
        public double getTotalCurrent() {
            return totalCurrent;
        }

        // Returns the total power consumed by the circuit.
        public double getTotalPower() {
            return totalPower;
        }
    }

    // Calculates totals assuming a series circuit by default.
    public static Result calculateTotals(Collection<Component> components) {
        return calculateTotals(components, CircuitType.SERIES);
    }

    // Calculates voltage, resistance, current, and power for the given circuit type.
    public static Result calculateTotals(Collection<Component> components, CircuitType type) {
        double totalVoltage = 0.0;
        double seriesResistance = 0.0;
        double reciprocalSum = 0.0;
        int resistorCount = 0;

        if (components != null) {
            for (Component comp : components) {
                if (comp instanceof PowerSource) {
                    totalVoltage += ((PowerSource) comp).getVoltageOut();
                }
                if (comp instanceof Resistor) {
                    double r = ((Resistor) comp).getResistance();
                    seriesResistance += r;
                    if (r > 0) {
                        reciprocalSum += 1.0 / r;
                        resistorCount++;
                    }
                }
            }
        }

        double totalResistance;
        if (type == CircuitType.PARALLEL && reciprocalSum > 0.0 && resistorCount > 0) {
            totalResistance = 1.0 / reciprocalSum;
        } else {
            totalResistance = seriesResistance;
        }

        double totalCurrent = 0.0;
        double totalPower = 0.0;

        if (totalResistance > 0.0) {
            totalCurrent = totalVoltage / totalResistance;
        }
        totalPower = totalVoltage * totalCurrent;

        return new Result(totalVoltage, totalResistance, totalCurrent, totalPower);
    }

    // Calculates the voltage drop across a single component in the circuit.
    public static double calculateVoltageDrop(Component component, Collection<Component> allComponents, CircuitType type) {
        if (component == null || allComponents == null) {
            return 0.0;
        }

        double totalVoltage = 0.0;
        double totalResistance = 0.0;
        double componentResistance = 0.0;

        for (Component comp : allComponents) {
            if (comp instanceof PowerSource) {
                totalVoltage += ((PowerSource) comp).getVoltageOut();
            }
            if (comp instanceof Resistor) {
                double r = ((Resistor) comp).getResistance();
                totalResistance += r;
                if (comp == component) {
                    componentResistance = r;
                }
            }
        }

        if (type == CircuitType.PARALLEL) {
            return totalVoltage;
        } else {
            if (totalResistance > 0.0 && componentResistance > 0.0) {
                return (componentResistance / totalResistance) * totalVoltage;
            }
        }

        return 0.0;
    }
}


