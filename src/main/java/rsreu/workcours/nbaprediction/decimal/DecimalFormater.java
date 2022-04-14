package rsreu.workcours.nbaprediction.decimal;

import java.text.DecimalFormat;

public class DecimalFormater {
    public static double aroundDoubleToOnePlace(double val) {
        DecimalFormat decimalFormat = new DecimalFormat("#######.#");
        return Double.parseDouble(decimalFormat.format(val).replaceAll(",", "."));
    }
}
