/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package supermercado.util;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.util.Locale;

/**
 *
 * @author KillerWorkstation
 */
public class MathUtil {

    public static double round(double value, int places) {
        if (places < 0) {
            throw new IllegalArgumentException();
        }

        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

    public static class Currency {
        public static String format(BigDecimal valor) {
            NumberFormat df = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
            return df.format(valor);
        }
    }
}
