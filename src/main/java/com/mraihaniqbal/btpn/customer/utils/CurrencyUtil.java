package com.mraihaniqbal.btpn.customer.utils;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

public class CurrencyUtil {

    public static String indonesiaFormat(Number number){
        DecimalFormat indonesia = (DecimalFormat) DecimalFormat.getCurrencyInstance();
        DecimalFormatSymbols idFormat = new DecimalFormatSymbols();

        idFormat.setCurrencySymbol("Rp ");
        idFormat.setGroupingSeparator('.');
        idFormat.setMonetaryDecimalSeparator(',');
        indonesia.setDecimalFormatSymbols(idFormat);

        return indonesia.format(number);
    }
}
