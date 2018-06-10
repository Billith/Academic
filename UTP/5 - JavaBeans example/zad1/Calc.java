/**
 *
 *  @author Dyduch Łukasz S15599
 *
 */

package zad1;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BinaryOperator;

public class Calc {

    public String doCalc(String arg) {
        try {
            String[] splitted = arg.split("\\s+");
            BigDecimal num1 = new BigDecimal(splitted[0]);
            String op = splitted[1];
            BigDecimal num2 = new BigDecimal(splitted[2]);

            Map<String, BinaryOperator<BigDecimal>> operations = new HashMap<>();
            operations.put("+", (n1, n2) -> n1.add(n2));
            operations.put("-", (n1, n2) -> n1.subtract(n2));
            operations.put("*", (n1, n2) -> n1.multiply(n2));
            operations.put("/", (n1, n2) -> n1.divide(n2, 7, RoundingMode.HALF_UP));

            return operations.get(op).apply(num1, num2).toString();
        } catch (Exception e) {
            return "Invalid command to calc";
        }
    }
}
